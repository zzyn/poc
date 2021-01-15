import { HttpModule } from "@nestjs/common";
import { ConfigService } from "@nestjs/config";
import { Test, TestingModule } from "@nestjs/testing";
import { EntityManager } from "typeorm";
import { ContentMigrationMappingEntity } from "../../db";
import { ContentMigrationMappingEntityRepository } from "../../db/content.migration.mapping.repository";
import { ContentRepoService } from "../../sdks/content-repo/apis";
import { SaveContentResponseDto } from "../../sdks/content-repo/dto";
import { OspContentService } from "../../sdks/osp/apis";
import {
  OspActivity,
  OspActivityBody,
  OspActivityBodyTags,
  OspCourse,
  OspPTContentDto,
  PTContentPathDto,
  PTTestKind,
} from "../../sdks/osp/dto";
import { ContentController } from "./content.controller";
import { ContentService } from "./content.service";

describe("ContentService", () => {
  let service: ContentService;
  let ospContentService: OspContentService;
  let contentRepoService: ContentRepoService;
  let entityManager: EntityManager;
  const mockRepository = new ContentMigrationMappingEntityRepository();

  const mockOspContent = () => {
    jest.spyOn(ospContentService, "getPTContentPath").mockResolvedValue({
      PTKey: "e1ce973b-14c2-48a9-aef4-9d6096ba38ed",
      TestKind: PTTestKind.UnitQuiz,
      CourseCode: OspCourse.HF,
      BookKey: "8adf3abf-276e-41ee-8502-87e2c8aa4f84",
      BookCode: "HFD",
      UnitKey: "eedec15f-24dc-4563-9a9f-be461195cffa",
      UnitCode: "unit-6",
    } as PTContentPathDto);

    jest.spyOn(ospContentService, "getPTContent").mockResolvedValue({
      Info: {
        title: "Progress Test",
        duration: 30,
        partsInfo: [
          {
            title: "grammar",
            count: 2,
          },
          {
            title: "vocabulary",
            count: 2,
          },
          {
            title: "listening",
            count: 2,
          },
          {
            title: "reading",
            count: 2,
          },
        ],
      },
      Activities: [
        {
          Key: "85b9fd93-d69c-49eb-a2bd-091f4054fb14",
          Body: {
            tags: {
              SkillType: "grammar",
            } as OspActivityBodyTags,
          } as OspActivityBody,
        } as OspActivity,
        {
          Key: "4dfb7f4f-d8a3-4b04-9afb-a5837e90789d",
          Body: {
            tags: {
              SkillType: "grammar",
            } as OspActivityBodyTags,
          } as OspActivityBody,
        } as OspActivity,
        {
          Key: "c5a4bac9-8a09-4452-83ff-fdfaa10f4a49",
          Body: {
            tags: {
              SkillType: "vocabulary",
            } as OspActivityBodyTags,
          } as OspActivityBody,
        } as OspActivity,
        {
          Key: "cdde4c78-40c5-4506-a55a-28330dbef210",
          Body: {
            tags: {
              SkillType: "vocabulary",
            } as OspActivityBodyTags,
          } as OspActivityBody,
        } as OspActivity,
        {
          Key: "4ced088f-6025-4cc6-b0a8-3d07990729ef",
          Body: {
            tags: {
              SkillType: "reading",
            } as OspActivityBodyTags,
          } as OspActivityBody,
        } as OspActivity,
        {
          Key: "f33616d6-9572-4769-a448-5077e799ce71",
          Body: {
            tags: {
              SkillType: "listening",
            } as OspActivityBodyTags,
          } as OspActivityBody,
        } as OspActivity,
        {
          Key: "cf3e7a84-b95e-4dbc-a200-e17d91f2d0d4",
          Body: {
            tags: {
              SkillType: "listening",
            } as OspActivityBodyTags,
          } as OspActivityBody,
        } as OspActivity,
        {
          Key: "adf76b9d-7406-4967-8364-fa02c43c0c0d",
          Body: {
            tags: {
              SkillType: "reading",
            } as OspActivityBodyTags,
          } as OspActivityBody,
        } as OspActivity,
      ],
      Resources: [],
    } as OspPTContentDto);
  };

  const mockContentRepo = () => {
    jest
      .spyOn(contentRepoService, "saveContent")
      .mockResolvedValue({} as SaveContentResponseDto);
  };

  const mockDbOperation = () => {
    jest
      .spyOn(mockRepository, "findMigratedMapping")
      .mockResolvedValue(undefined);

    jest.spyOn(mockRepository, "save").mockImplementation();
  };
  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      imports: [HttpModule],
      controllers: [ContentController],
      providers: [
        ContentService,
        ConfigService,
        EntityManager,
        ContentRepoService,
        OspContentService,
      ],
    }).compile();

    service = module.get<ContentService>(ContentService);
    ospContentService = module.get<OspContentService>(OspContentService);
    contentRepoService = module.get<ContentRepoService>(ContentRepoService);
    entityManager = module.get<EntityManager>(EntityManager);
    jest
      .spyOn(entityManager, "getCustomRepository")
      .mockImplementation(() => mockRepository);
  });

  it("migrate success", async () => {
    mockOspContent();
    mockContentRepo();
    mockDbOperation();

    const response = await service.migrate({
      progressTestKey: "e1ce973b-14c2-48a9-aef4-9d6096ba38ed",
      contentRevision: "20201211-1130-xxxxxx",
    });

    expect(response.items).toHaveLength(9);
  });

  it("migrate failure, has migrated once", async () => {
    jest
      .spyOn(mockRepository, "findMigratedMapping")
      .mockResolvedValue({} as ContentMigrationMappingEntity);

    // eslint-disable-next-line jest/valid-expect
    expect(() =>
      service.migrate({
        progressTestKey: "e1ce973b-14c2-48a9-aef4-9d6096ba38ed",
        contentRevision: "20201211-1130-xxxxxx",
      }),
    ).rejects.toThrow();
  });
});
