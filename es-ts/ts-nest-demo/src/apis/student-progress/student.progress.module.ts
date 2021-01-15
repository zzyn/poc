import { HttpModule, Module } from "@nestjs/common";
import { ContentRepoService } from "../../sdks/content-repo/apis";
import { GeneralTestService } from "../../sdks/general-test/apis";
import {
  OspContentService,
  OspStudentProgressService,
} from "../../sdks/osp/apis";
import { StudentProgressController } from "./student.progress.controller";
import { StudentProgressService } from "./student.progress.service";

@Module({
  imports: [HttpModule],
  controllers: [StudentProgressController],
  providers: [
    ContentRepoService,
    StudentProgressService,
    OspContentService,
    OspStudentProgressService,
    GeneralTestService,
  ],
})
export class StudentProgressModule {}
