import { Body, Controller, Post } from "@nestjs/common";
import { StudentProgressMigrationRequest } from "./dto/student.progress.migration.request";
import { StudentProgressMigrationResponse } from "./dto/student.progress.migration.response";
import { StudentProgressService } from "./student.progress.service";

@Controller("api/v1/student-progress")
export class StudentProgressController {
  constructor(
    private readonly studentProgressService: StudentProgressService,
  ) {}

  @Post("/migrate")
  async migrate(
    @Body() param: StudentProgressMigrationRequest,
  ): Promise<StudentProgressMigrationResponse> {
    return this.studentProgressService.migrate(param);
  }
}
