import { registerEnumType } from "type-graphql";

enum State {
  UNSTART = 0,
  PROCESSING = 1,
  FINISH = 2,
}

registerEnumType(State, {
  name: "State",
  description: "The basic State",
});

export { State };
