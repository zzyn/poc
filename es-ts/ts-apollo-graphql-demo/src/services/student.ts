import { Service } from "typedi";
import { Student } from "../dto/vo/student";
import { GraphQLCustomResolversContext } from "../graphql/context";

@Service()
export class StudentSerivice {
  async getStudent(
    studentId: string,
    ctx: GraphQLCustomResolversContext,
  ): Promise<Student> {
    const student = await ctx.dataSources.studentInfoApi
      .getStudentProfile(studentId)
      .then((t) => {
        return {
          id: t.uid,
          localName: `${t.localFirstName || ""} ${
            t.localLastName || ""
          }`.trim(),
          englishName: `${t.englishFirstName || ""} ${
            t.englishLastName || ""
          }`.trim(),
        } as Student;
      });
    return Promise.resolve(student);
  }
}
