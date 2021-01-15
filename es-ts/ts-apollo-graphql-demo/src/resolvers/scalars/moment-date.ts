import { GraphQLScalarType, Kind, ValueNode } from "graphql";
import * as moment from "moment";

class MomentDate extends Number {}

const MomentDateScalar = new GraphQLScalarType({
  name: "MomentDate",
  description: "moment date scalar type",
  parseValue(value: string) {
    return moment.utc(value);
  },
  parseLiteral(ast: ValueNode) {
    if (ast.kind === Kind.STRING) {
      return moment.utc(ast.value);
    }
    return null;
  },
  serialize(value: number) {
    return moment.utc(value).toISOString();
  },
});

export { MomentDate, MomentDateScalar };
