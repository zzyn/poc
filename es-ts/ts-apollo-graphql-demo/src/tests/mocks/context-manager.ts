import { ExpressContext } from "apollo-server-express/dist/ApolloServer";
import { GraphQLCustomContext } from "../../graphql/context";

const buildContextManager = (user: any, access: any) => {
  const contextManager = (
    expressContext: ExpressContext,
  ): GraphQLCustomContext => {
    const { req, res } = expressContext;

    return {
      req,
      res,
      user,
      access,
    };
  };

  return contextManager;
};

export { buildContextManager };
