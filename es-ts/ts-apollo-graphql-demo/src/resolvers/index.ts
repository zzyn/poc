import { buildSchema } from "type-graphql";
import { Container } from "typedi";
import { ContentResolver } from "./content";
import { MomentDate, MomentDateScalar } from "./scalars/moment-date";
import { StudentResolver } from "./student";
import { StudentQuestionResultResolver } from "./student-question-result";
import { StudentsResultSummaryResolver } from "./students-result-summary";
import { UserResolver } from "./user";

export const createSchema = () =>
  buildSchema({
    resolvers: [
      UserResolver,
      StudentResolver,
      StudentsResultSummaryResolver,
      StudentQuestionResultResolver,
      ContentResolver,
    ],
    scalarsMap: [{ type: MomentDate, scalar: MomentDateScalar }],
    validate: false,
    container: Container,
    dateScalarMode: "isoDate", // "timestamp" or "isoDate"
  });
