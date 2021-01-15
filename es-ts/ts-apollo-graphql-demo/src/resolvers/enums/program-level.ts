import { registerEnumType } from "type-graphql";

enum ProgramLevel {
  L1 = 0,
  L2 = 1,
  L3 = 2,
}

registerEnumType(ProgramLevel, {
  name: "ProgramLevel",
  description: "The basic Level",
});

export { ProgramLevel };
