import { Module } from "@nestjs/common";
import { ContentModule } from "./content/content.module";
import { HealthModule } from "./health/health.module";
import { StudentProgressModule } from "./student-progress/student.progress.module";

@Module({
  imports: [HealthModule, ContentModule, StudentProgressModule],
})
export class ApiModule {}
