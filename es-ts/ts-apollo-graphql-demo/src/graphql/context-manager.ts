import { AuthenticationError } from "apollo-server-core";
import { ExpressContext } from "apollo-server-express/dist/ApolloServer";
import * as fs from "fs";
import jwt from "jsonwebtoken";
import path from "path";
import R from "ramda";
import { getEnvConfig } from "../config/env";
import { GraphQLCustomContext } from "./context";

const { authentication } = getEnvConfig();

export const HEADER_X_EF_ID = "X-EF-ID";
export const HEADER_X_EF_ACCESS = "X-EF-ACCESS";

const publicKey = fs.readFileSync(path.join(process.cwd(), "public_key.pem"));

const decodeJwt = (pk: string, token: string) => {
  if (!token) throw new AuthenticationError("token is empty.");

  let payload;
  let jwtError;
  jwt.verify(token.toString(), pk, (err, decoded) => {
    jwtError = err;
    payload = decoded;
  });

  if (jwtError) throw new AuthenticationError("It is not a valid JWT.");

  return payload;
};

const contextManager = (
  expressContext: ExpressContext,
): GraphQLCustomContext => {
  const { req, res } = expressContext;
  let user;
  let access;

  if (authentication) {
    const getJwt = R.compose(R.partial(decodeJwt, [publicKey]), (h: string) =>
      req.header(h),
    );

    user = getJwt(HEADER_X_EF_ID);
    access = getJwt(HEADER_X_EF_ACCESS);
  }

  // const getAcl = (): Promise<AclItem[]> =>
  //   new Auth2Api().getAcl(req.header(HEADER_X_EF_ACCESS) as string);

  return {
    req,
    res,
    user,
    access,
    // getAcl,
  };
};

export { contextManager };
