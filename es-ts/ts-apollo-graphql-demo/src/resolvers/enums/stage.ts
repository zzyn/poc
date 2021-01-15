import { registerEnumType } from "type-graphql";

enum Stage {
  EXAM = 0,
  PRETEST = 1,
  LESSION = 2,
  PRACTICE = 3,
  ONLINECLASS = 4,
  TEST = 5,
  PLACEMENTTEST = 6,
}

registerEnumType(Stage, {
  name: "Stage",
  description: "The basic Stage",
});

export { Stage };
