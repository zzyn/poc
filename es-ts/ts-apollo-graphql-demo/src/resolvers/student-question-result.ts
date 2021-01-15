import {
  Arg,
  Ctx,
  FieldResolver,
  Int,
  Query,
  Resolver,
  Root,
} from "type-graphql";
import { Service } from "typedi";
import { StudentQuestionResult } from "../dto/vo/student-question-result";
import { GraphQLCustomResolversContext } from "../graphql/context";
import { StudentSerivice } from "../services/student";
import { StudentResultService } from "../services/student-result-service";

@Service()
@Resolver(() => StudentQuestionResult)
export class StudentQuestionResultResolver {
  constructor(
    private readonly studentResultService: StudentResultService,
    private readonly studentSerivice: StudentSerivice,
  ) {}

  @Query(() => [StudentQuestionResult], { nullable: false })
  async queryStudentResult(
    @Arg("contentPath") contentPath: string,
    @Arg("productModule", () => Int) productModule: number,
    @Arg("studentIds", () => [String]) studentIds: string[],
    @Ctx() ctx: GraphQLCustomResolversContext,
  ): Promise<StudentQuestionResult[]> {
    return this.studentResultService.getStudentResult(
      contentPath,
      productModule,
      studentIds,
      ctx,
    );
  }

  @FieldResolver()
  async student(
    @Root() studentQuestionResult: StudentQuestionResult,
    @Ctx() ctx: GraphQLCustomResolversContext,
  ) {
    const student = this.studentSerivice.getStudent(
      studentQuestionResult.student.id,
      ctx,
    );

    return Promise.resolve(student);
  }
}
