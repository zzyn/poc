/* eslint-disable max-lines */
import { Injectable } from "@nestjs/common";
import { EntityManager } from "typeorm";
import { ContentMigrationMappingEntityRepository } from "../../db/content.migration.mapping.repository";
import { ContentMigrationMappingEntity, MapType } from "../../db/entity";
import { ContentRepoService } from "../../sdks/content-repo/apis";
import {
  ActivityRef,
  PTContent,
  PTContentSkill,
  PTContentSkillCode,
} from "../../sdks/content-repo/dto/pt.content.dto";
import {
  SaveContentRequestActivityDto,
  SaveContentRequestDto,
} from "../../sdks/content-repo/dto/save.content.request.dto";
import { generateContentId } from "../../sdks/content-repo/utils/content.repo.utils";
import { OspContentService } from "../../sdks/osp/apis";
import {
  OspActivity,
  OspActivityBody,
  OspActivityDataQuestionBodyOption,
  OspActivityDataQuestionBodyTag,
  OspActivityDataQuestionBodyTest,
  OspActivityQuestions,
  OspActivityStimulus,
  OspPTContentDto,
  OspResource,
  PTTestKind,
} from "../../sdks/osp/dto";
import { constructContentPath } from "../../utils/content.path.utils";
import {
  ContentMetaRequest,
  ContentMetaResponse,
  ContentMigrationRequest,
  ContentMigrationResponse,
} from "./dto";
import {
  ActivityData,
  ActivityDataBody,
  ActivityDataBodyTag,
  ActivityDataBodyTheme,
  ActivityDataMediaAudio,
  ActivityDataMediaImage,
  ActivityDataMediaVideo,
  ActivityDataQuestion,
  ActivityDataQuestionBody,
  ActivityDataQuestionBodyOption,
  ActivityDataQuestionBodyTag,
  ActivityDataQuestionBodyTest,
  ActivityDataStimulus,
  ActivityDataStimulusBody,
  ActivityDataStimulusBodyItem,
} from "./vo/template";

@Injectable()
export class ContentService {
  constructor(
    private entityManager: EntityManager,
    private ospContentService: OspContentService,
    private contentRepoService: ContentRepoService,
  ) {}

  async findOneContentMeta(
    param: ContentMetaRequest,
  ): Promise<ContentMetaResponse | undefined> {
    console.log(param);

    const record = await this.entityManager
      .getCustomRepository(ContentMigrationMappingEntityRepository)
      .findOneContentMeta(param.contentPath, param.mapType);

    if (record === undefined) {
      return undefined;
    }
    return {
      ospKey: record.ospKey,
      mapType: record.mapType,
      contentId: record.contentId,
      contentRevision: record.contentRevision,
      contentPath: record.contentPath,
      schemaVersion: 1,
    };
  }

  async migrate(
    param: ContentMigrationRequest,
  ): Promise<ContentMigrationResponse> {
    console.log(param);

    const hasMigrated = await this.entityManager
      .getCustomRepository(ContentMigrationMappingEntityRepository)
      .findMigratedMapping(param.progressTestKey, param.contentRevision);

    if (hasMigrated) {
      throw new Error("It has been migrated.");
    }

    const contentPathData = await this.ospContentService.getPTContentPath(
      param.progressTestKey,
    );
    const mapType = this.mapPTTestKindToMapType(contentPathData.TestKind);

    const contentPath = constructContentPath({
      mapType,
      course: contentPathData.CourseCode.toString(),
      book: contentPathData.BookCode,
      unit: contentPathData.UnitCode,
    });

    const ospContent = await this.ospContentService.getPTContent(
      param.progressTestKey,
    );

    const contentToSave: SaveContentRequestDto = {
      releaseRevision: param.contentRevision,
      forceUpdate: false,
      activities: [],
    };
    const mappingsToSave: ContentMigrationMappingEntity[] = [];

    const activityMappings: {
      [ospKey: string]: { contentId: string; contentRevision: string };
    } = {};

    const ospResources: OspResource[] = ospContent.Resources;
    // map activities
    // eslint-disable-next-line no-restricted-syntax
    for (const a of ospContent.Activities) {
      // eslint-disable-next-line no-await-in-loop
      let mapping = await this.entityManager
        .getCustomRepository(ContentMigrationMappingEntityRepository)
        .findMigratedMapping(a.Key, param.contentRevision);

      if (!mapping) {
        mapping = this.buildContentMigrationMappingEntity(
          MapType.Activity,
          a.Key,
          param.contentRevision,
          contentPath,
        );

        mappingsToSave.push(mapping);

        contentToSave.activities.push(
          this.buildPTActivity(
            a,
            ospResources,
            mapping.contentId,
            mapping.contentRevision,
          ),
        );
      }

      activityMappings[a.Key] = {
        contentId: mapping.contentId,
        contentRevision: mapping.contentRevision,
      };
    }

    // map progress test
    const ptMapping = this.buildContentMigrationMappingEntity(
      mapType,
      param.progressTestKey,
      param.contentRevision,
      contentPath,
    );

    mappingsToSave.push(ptMapping);

    contentToSave.activities.push(
      this.buildPTContent(
        ospContent,
        activityMappings,
        contentPathData.TestKind,
        ptMapping.contentId,
        ptMapping.contentRevision,
      ),
    );

    // save all to content-repo
    const saveContentResponseDto = await this.contentRepoService.saveContent(
      contentToSave,
    );
    console.debug(saveContentResponseDto);

    // save mapping history
    const saveMappingResult = await this.entityManager
      .getCustomRepository(ContentMigrationMappingEntityRepository)
      .save(mappingsToSave);
    console.debug(saveMappingResult);

    return {
      items: mappingsToSave.map((m) => {
        return {
          mapType: `${m.mapType}`,
          ospKey: m.ospKey,
          contentId: m.contentId,
          contentRevision: m.contentRevision,
        };
      }),
    };
  }

  private buildActivityDataMediaImage(
    key: string,
    ospResources: OspResource[],
  ): ActivityDataMediaImage {
    const id = key.replace("resource://", "");
    const rs = ospResources.find((x) => x.Identifier === id);
    const item = {
      id,
      url: `resource/${id}`,
      sha1: "",
      mimeType: "",
      size: 0,
      height: 0,
      width: 0,
    } as ActivityDataMediaImage;

    if (rs) {
      item.sha1 = rs.Hash;
      item.mimeType = rs.Mime;
      item.size = rs.Length ?? 0;
      item.height = rs.Height ?? 0;
      item.width = rs.Width ?? 0;
    }
    return item;
  }

  private buildActivityDataMediaAudio(
    key: string,
    ospResources: OspResource[],
  ): ActivityDataMediaAudio {
    const id = key.replace("resource://", "");
    const rs = ospResources.find((x) => x.Identifier === id);
    const item = {
      id,
      url: `resource/${id}`,
      sha1: "",
      mimeType: "",
      size: 0,
      duration: 0,
    } as ActivityDataMediaAudio;

    if (rs) {
      item.sha1 = rs.Hash;
      item.mimeType = rs.Mime;
      item.size = rs.Length ?? 0;
      item.duration = rs.Duration ?? 0;
    }
    return item;
  }

  private buildActivityDataMediaVideo(
    key: string,
    ospResources: OspResource[],
  ): ActivityDataMediaVideo {
    const id = key.replace("resource://", "");
    const rs = ospResources.find((x) => x.Identifier === id);
    const item = {
      id,
      url: `resource/${id}`,
      sha1: "",
      mimeType: "",
      size: 0,
      duration: 0,
      language: "",
    } as ActivityDataMediaVideo;

    if (rs) {
      item.sha1 = rs.Hash;
      item.mimeType = rs.Mime;
      item.size = rs.Length ?? 0;
      item.duration = rs.Duration ?? 0;
      item.language = rs.CultureCode ?? "";
    }
    return item;
  }

  private buildActivityDataQuestionBodyTest(
    ospQuestionTest: OspActivityDataQuestionBodyTest,
    ospResources: OspResource[],
  ): ActivityDataQuestionBodyTest {
    const data = new ActivityDataQuestionBodyTest();
    if (ospQuestionTest.text) {
      data.text = ospQuestionTest.text;
    }
    if (ospQuestionTest.image) {
      data.image = this.buildActivityDataMediaImage(
        ospQuestionTest.image,
        ospResources,
      );
    }

    if (ospQuestionTest.audio) {
      data.audio = this.buildActivityDataMediaAudio(
        ospQuestionTest.audio,
        ospResources,
      );
    }

    if (ospQuestionTest.videos) {
      data.videos = [];
      ospQuestionTest.videos.forEach((v) => {
        data.videos?.push(this.buildActivityDataMediaVideo(v, ospResources));
      });
    }
    return data;
  }

  private buildActivityDataQuestionBodyOption(
    ospQuestionOption: OspActivityDataQuestionBodyOption,
    ospResources: OspResource[],
  ): ActivityDataQuestionBodyOption {
    const data = new ActivityDataQuestionBodyOption();
    data.id = ospQuestionOption.id;
    if (ospQuestionOption.text) {
      data.text = ospQuestionOption.text;
    }

    if (ospQuestionOption.image) {
      data.image = this.buildActivityDataMediaImage(
        ospQuestionOption.image,
        ospResources,
      );
    }

    if (ospQuestionOption.audio) {
      data.audio = this.buildActivityDataMediaAudio(
        ospQuestionOption.audio,
        ospResources,
      );
    }

    if (ospQuestionOption.type) {
      data.type = ospQuestionOption.type;
    }

    if (ospQuestionOption.colIndex === 0 || ospQuestionOption.colIndex) {
      data.colIndex = ospQuestionOption.colIndex;
    }

    if (ospQuestionOption.rowIndex === 0 || ospQuestionOption.rowIndex) {
      data.rowIndex = ospQuestionOption.rowIndex;
    }

    if (ospQuestionOption.texts) {
      data.texts = ospQuestionOption.texts;
    }

    if (ospQuestionOption.images) {
      data.images = [];
      ospQuestionOption.images.forEach((i) => {
        data.images?.push(this.buildActivityDataMediaImage(i, ospResources));
      });
    }

    return data;
  }

  private buildActivityDataQuestionBodyTag(
    ospQuestionTag: OspActivityDataQuestionBodyTag,
  ): ActivityDataQuestionBodyTag {
    const data = new ActivityDataQuestionBodyTag();

    if (ospQuestionTag.CompassTags) {
      data.compassTags = ospQuestionTag.CompassTags;
    }

    if (ospQuestionTag.compassTags) {
      data.compassTags = ospQuestionTag.compassTags;
    }

    if (ospQuestionTag.SecondaryCompassTags) {
      data.secondaryCompassTags = ospQuestionTag.SecondaryCompassTags;
    }

    if (ospQuestionTag.secondaryCompassTags) {
      data.secondaryCompassTags = ospQuestionTag.secondaryCompassTags;
    }

    if (ospQuestionTag.SubSkillSet) {
      data.subSkillSet = ospQuestionTag.SubSkillSet;
    }

    if (ospQuestionTag.subSkillSet) {
      data.subSkillSet = ospQuestionTag.subSkillSet;
    }

    if (ospQuestionTag.Vocabulary) {
      data.vocabulary = ospQuestionTag.Vocabulary;
    }

    if (ospQuestionTag.vocabulary) {
      data.vocabulary = ospQuestionTag.vocabulary;
    }

    if (ospQuestionTag.Rows === 0 || ospQuestionTag.Rows) {
      data.rows = ospQuestionTag.Rows;
    }

    if (ospQuestionTag.rows === 0 || ospQuestionTag.rows) {
      data.rows = ospQuestionTag.rows;
    }

    if (ospQuestionTag.Cols === 0 || ospQuestionTag.Cols) {
      data.cols = ospQuestionTag.Cols;
    }

    if (ospQuestionTag.cols === 0 || ospQuestionTag.cols) {
      data.cols = ospQuestionTag.cols;
    }

    return data;
  }

  private buildActivityDataQuestion(
    ospQuestion: OspActivityQuestions,
    ospResources: OspResource[],
  ): ActivityDataQuestion {
    const data = new ActivityDataQuestion();
    data.key = ospQuestion.Key;
    data.body = new ActivityDataQuestionBody();
    data.body.version = ospQuestion.Body.version;
    data.body.answers = ospQuestion.Body.answers;

    data.body.tests = [];
    if (ospQuestion.Body.tests) {
      ospQuestion.Body.tests.forEach((t) => {
        data.body.tests.push(
          this.buildActivityDataQuestionBodyTest(t, ospResources),
        );
      });
    }

    data.body.options = [];
    if (ospQuestion.Body.options) {
      ospQuestion.Body.options.forEach((o) => {
        data.body.options.push(
          this.buildActivityDataQuestionBodyOption(o, ospResources),
        );
      });
    }

    if (ospQuestion.Body.tags) {
      data.body.tags = [];
      ospQuestion.Body.tags.forEach((t) => {
        data.body.tags?.push(this.buildActivityDataQuestionBodyTag(t));
      });
    }

    data.body.additionalLength = ospQuestion.Body.additionalLength;

    return data;
  }

  private buildActivityDataStimulus(
    ospStimulus: OspActivityStimulus,
    ospResources: OspResource[],
  ): ActivityDataStimulus {
    const data = new ActivityDataStimulus();

    if (ospStimulus.Key) {
      data.key = ospStimulus.Key;
    }

    if (ospStimulus.Body) {
      data.body = new ActivityDataStimulusBody();
      if (ospStimulus.Body.item) {
        data.body.item = new ActivityDataStimulusBodyItem();
        if (ospStimulus.Body.item.text) {
          data.body.item.text = ospStimulus.Body.item.text;
        }

        if (ospStimulus.Body.item.image) {
          data.body.item.image = this.buildActivityDataMediaImage(
            ospStimulus.Body.item.image,
            ospResources,
          );
        }

        if (ospStimulus.Body.item.audio) {
          data.body.item.audio = this.buildActivityDataMediaAudio(
            ospStimulus.Body.item.audio,
            ospResources,
          );
        }

        if (ospStimulus.Body.item.video) {
          data.body.item.video = this.buildActivityDataMediaVideo(
            ospStimulus.Body.item.video,
            ospResources,
          );
        }
      }

      if (ospStimulus.Body.tags) {
        data.body.tags = ospStimulus.Body.tags;
      }
    }

    return data;
  }

  private buildActivityDataBody(
    ospActivityBody: OspActivityBody,
    ospResources: OspResource[],
  ): ActivityDataBody {
    const data = new ActivityDataBody();
    data.instruction = ospActivityBody.instruction;
    data.mappings = ospActivityBody.mappings;

    if (ospActivityBody.theme?.BackGroundImages) {
      // resource map
      const images: ActivityDataMediaImage[] = [];
      ospActivityBody.theme?.BackGroundImages.forEach((image) => {
        images.push(this.buildActivityDataMediaImage(image, ospResources));
      });
      data.theme = new ActivityDataBodyTheme();
      data.theme.backgroundImages = images;
    }

    if (ospActivityBody.tags) {
      const bodyTags = ospActivityBody.tags;
      data.tags = new ActivityDataBodyTag();
      if (bodyTags.Key) {
        data.tags.key = bodyTags.Key;
      }
      if (bodyTags.key) {
        data.tags.key = bodyTags.key;
      }

      if (bodyTags.SkillType) {
        data.tags.skillType = bodyTags.SkillType;
      }

      if (bodyTags.skillType) {
        data.tags.skillType = bodyTags.skillType;
      }

      if (bodyTags.ActivityType) {
        data.tags.activityType = bodyTags.ActivityType;
      }

      if (bodyTags.activityType) {
        data.tags.activityType = bodyTags.activityType;
      }

      if (bodyTags.ActivitySubType) {
        data.tags.activitySubType = bodyTags.ActivitySubType;
      }

      if (bodyTags.activitySubType) {
        data.tags.activitySubType = bodyTags.activitySubType;
      }

      if (bodyTags.AgeGroupH === 0 || bodyTags.AgeGroupH) {
        data.tags.ageGroupH = bodyTags.AgeGroupH;
      }

      if (bodyTags.ageGroupH === 0 || bodyTags.ageGroupH) {
        data.tags.ageGroupH = bodyTags.ageGroupH;
      }

      if (bodyTags.AgeGroupL === 0 || bodyTags.AgeGroupL) {
        data.tags.ageGroupL = bodyTags.AgeGroupL;
      }

      if (bodyTags.ageGroupL === 0 || bodyTags.ageGroupL) {
        data.tags.ageGroupL = bodyTags.ageGroupL;
      }

      if (bodyTags.CefrLevels) {
        data.tags.cefrLevels = bodyTags.CefrLevels;
      }

      if (bodyTags.cefrLevels) {
        data.tags.cefrLevels = bodyTags.cefrLevels;
      }

      if (bodyTags.CompassTags) {
        data.tags.compassTags = bodyTags.CompassTags;
      }

      if (bodyTags.compassTags) {
        data.tags.compassTags = bodyTags.compassTags;
      }

      if (bodyTags.SecondaryCompassTags) {
        data.tags.secondaryCompassTags = bodyTags.SecondaryCompassTags;
      }

      if (bodyTags.secondaryCompassTags) {
        data.tags.secondaryCompassTags = bodyTags.secondaryCompassTags;
      }

      if (bodyTags.TotalCompassTags) {
        data.tags.totalCompassTags = bodyTags.TotalCompassTags;
      }

      if (bodyTags.totalCompassTags) {
        data.tags.totalCompassTags = bodyTags.totalCompassTags;
      }

      if (bodyTags.LearningFocus) {
        data.tags.learningFocus = bodyTags.LearningFocus;
      }

      if (bodyTags.learningFocus) {
        data.tags.learningFocus = bodyTags.learningFocus;
      }
    }

    return data;
  }

  private buildActivityData(
    ospActivity: OspActivity,
    ospResources: OspResource[],
  ): ActivityData {
    const data = new ActivityData();
    data.Key = ospActivity.Key;
    data.Title = ospActivity.Title;
    data.Type = ospActivity.Type;
    // Body
    data.Body = this.buildActivityDataBody(ospActivity.Body, ospResources);

    // Question
    data.Questions = [];
    if (ospActivity.Questions) {
      ospActivity.Questions.forEach((q) => {
        data.Questions.push(this.buildActivityDataQuestion(q, ospResources));
      });
    }

    // Stimulus
    if (ospActivity.Stimulus) {
      data.Stimulus = [];
      ospActivity.Stimulus.forEach((s) => {
        data.Stimulus?.push(this.buildActivityDataStimulus(s, ospResources));
      });
    }

    return data;
  }

  private buildPTActivity(
    ospActivity: OspActivity,
    ospResources: OspResource[],
    contentId: string,
    contentRevision: string,
  ): SaveContentRequestActivityDto {
    return {
      schemaVersion: 1,
      contentRevision,
      contentId,
      data: this.buildActivityData(ospActivity, ospResources),
      // data: Object.keys(ospActivity).reduce((data: any, key) => {
      //   // eslint-disable-next-line no-param-reassign
      //   data[key] = toCamelCaseKeyObject((ospActivity as any)[key]);
      //   return data;
      // }, {}),
      domainType: "ProgressTest",
      entityType: "Activity",
    };
  }

  private buildPTContent(
    ospPTContentDto: OspPTContentDto,
    activityMappings: {
      [ospKey: string]: { contentId: string; contentRevision: string };
    },
    testKind: PTTestKind,
    contentId: string,
    contentRevision: string,
  ): SaveContentRequestActivityDto {
    const getPTContentSkill = (code: PTContentSkillCode) => {
      let sequence = 0;
      const activityRefs: ActivityRef[] = ospPTContentDto.Activities.filter(
        (a) => a.Body.tags?.SkillType === code,
      ).map((a) => {
        sequence += 1;
        return Object.assign(activityMappings[a.Key], {
          schemaVersion: 1,
          activitySequence: sequence,
        }) as ActivityRef;
      });

      return {
        code,
        activityRefs,
      } as PTContentSkill;
    };

    const ptContent: PTContent = {
      title: ospPTContentDto.Info.title,
      duration: ospPTContentDto.Info.duration,
      skills: [
        getPTContentSkill(PTContentSkillCode.grammar),
        getPTContentSkill(PTContentSkillCode.vocabulary),
        getPTContentSkill(PTContentSkillCode.listening),
        getPTContentSkill(PTContentSkillCode.reading),
        getPTContentSkill(PTContentSkillCode.writing),
        getPTContentSkill(PTContentSkillCode.speaking),
      ],
    };

    return {
      schemaVersion: 1,
      contentRevision,
      contentId,
      data: ptContent,
      domainType: "ProgressTest",
      entityType: testKind,
    };
  }

  private buildContentMigrationMappingEntity(
    mapType: MapType,
    ospKey: string,
    contentRevision: string,
    contentPath: string,
  ): ContentMigrationMappingEntity {
    return {
      mapType,
      ospKey,
      contentId: generateContentId(),
      contentRevision,
      contentPath,
      createdBy: "migration user",
      createdTimestamp: new Date(),
    } as ContentMigrationMappingEntity;
  }

  private mapPTTestKindToMapType(kind: PTTestKind): MapType {
    switch (kind) {
      case PTTestKind.UnitQuiz:
        return MapType.UnitQuiz;
      case PTTestKind.MidTermExam:
        return MapType.MidTermExam;
      case PTTestKind.FinalExam:
        return MapType.FinalExam;
      default:
        throw new Error(`unknown testKind ${kind}`);
    }
  }
}
