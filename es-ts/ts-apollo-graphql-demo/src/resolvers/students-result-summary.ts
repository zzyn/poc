import {
  Arg,
  Ctx,
  FieldResolver,
  Int,
  Mutation,
  Query,
  Resolver,
  Root,
} from "type-graphql";
import { Service } from "typedi";
import { handleErrors } from "../dto";
import { MigrateStudentsResultInput } from "../dto/vo/mutation/migrate-students-result-input";
import { MigrateStudentsResultPayload } from "../dto/vo/mutation/migrate-students-result-payload";
import { Student } from "../dto/vo/student";
import { StudentsResultSummary } from "../dto/vo/students-result-summary";
import { GraphQLCustomResolversContext } from "../graphql/context";
import { StudentSerivice } from "../services/student";
import { StudentResultService } from "../services/student-result-service";

@Service()
@Resolver(() => StudentsResultSummary)
export class StudentsResultSummaryResolver {
  constructor(
    private readonly studentResultService: StudentResultService,
    private readonly studentSerivice: StudentSerivice,
  ) {}

  @Query(() => StudentsResultSummary, { nullable: false })
  async queryStudentsResultSummary(
    @Arg("contentPath") contentPath: string,
    @Arg("productModule", () => Int) productModule: number,
    @Arg("studentIds", () => [String]) studentIds: string[],
    @Ctx() ctx: GraphQLCustomResolversContext,
  ): Promise<StudentsResultSummary> {
    return this.studentResultService.getStudentResultSummary(
      contentPath,
      productModule,
      studentIds,
      ctx,
    );
  }

  @FieldResolver()
  async students(
    @Root() studentResultSummary: StudentsResultSummary,
    @Ctx() ctx: GraphQLCustomResolversContext,
  ) {
    const studentPromises = studentResultSummary.students.reduce<
      Promise<Student>[]
    >((retVal, student) => {
      const detailedStudent = this.studentSerivice.getStudent(student.id, ctx);
      return retVal.concat(detailedStudent);
    }, []);

    const students = await Promise.all(studentPromises).then((result) => {
      return result.reduce<Student[]>((retVal, element) => {
        return retVal.concat(element);
      }, []);
    });

    return Promise.resolve(students);
  }

  @Mutation(() => MigrateStudentsResultPayload)
  async migrateStudentsResult(
    @Arg("input", () => MigrateStudentsResultInput)
    input: MigrateStudentsResultInput,
    @Ctx() ctx: GraphQLCustomResolversContext,
  ): Promise<MigrateStudentsResultPayload> {
    try {
      const migrateData = await ctx.dataSources.migrationPTApi.migrate({
        studentIds: input.studentIds,
        progressTestKey: input.progressTestKey,
      });
      console.log(`${migrateData.contentPath} ${migrateData.productModule}`);

      const studentsResultSummary = await this.studentResultService.getStudentResultSummary(
        migrateData.contentPath,
        migrateData.productModule,
        input.studentIds,
        ctx,
      );

      return {
        studentsResultSummary,
        userErrors: [],
      };
    } catch (error) {
      return {
        userErrors: handleErrors(error),
      };
    }
  }
}
