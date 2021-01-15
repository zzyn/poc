import { Arg, Ctx, Query, Resolver } from "type-graphql";
import { Service } from "typedi";
import { Student } from "../dto/vo/student";
import { GraphQLCustomResolversContext } from "../graphql/context";
import { StudentSerivice } from "../services/student";

@Service()
@Resolver(() => Student)
export class StudentResolver {
  constructor(private readonly studentSerivice: StudentSerivice) {}

  @Query(() => Student)
  async student(
    @Arg("id") id: string,
    @Ctx() ctx: GraphQLCustomResolversContext,
  ): Promise<Student> {
    return this.studentSerivice.getStudent(id, ctx);
  }
}
