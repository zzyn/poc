/* eslint-disable max-lines */
import {
  ActivityDataBodyInstruction,
  ActivityDataBodyMapping,
  ActivityDataQuestionBodyOptionTextsItem,
  ActivityDataStimulusBodyTag,
} from "../../../apis/content/vo/template";

export interface OspActivityBodyTags {
  Key?: string;
  SkillType?: string;
  AgeGroupL?: number;
  AgeGroupH?: number;
  ActivityType?: string;
  ActivitySubType?: string;
  CefrLevels?: string[];
  CompassTags?: string[];
  SecondaryCompassTags?: string[];
  TotalCompassTags?: string[];
  LearningFocus?: string;

  key?: string;
  skillType?: string;
  ageGroupL?: number;
  ageGroupH?: number;
  activityType?: string;
  activitySubType?: string;
  cefrLevels?: string[];
  compassTags?: string[];
  secondaryCompassTags?: string[];
  totalCompassTags?: string[];
  learningFocus?: string;
}

export interface OspActivityDataBodyTheme {
  BackGroundImages?: string[];
  Summary: any;
  IntroPage: any;
}

export interface OspActivityBody {
  instruction?: ActivityDataBodyInstruction;
  mappings?: ActivityDataBodyMapping[];
  theme?: OspActivityDataBodyTheme;
  tags?: OspActivityBodyTags;
}

export interface OspResource {
  ResourceId?: string;
  CultureCode: string;
  Quality: string;
  Hash: string;
  CreatedStamp?: Date;
  LastUpdatedStamp?: Date;
  State: number;
  CreatedBy: string;
  OwnerKey?: string;
  Name: string;
  Mime: string;
  Length?: number;
  Width?: number;
  Height?: number;
  Duration?: number;
  Container: string;
  Identifier: string;
}

export interface OspPTPartInfo {
  title: string;
  count: number;
}

export interface OspPTInfo {
  duration: number;
  title: string;
  partsInfo: OspPTPartInfo[];
}

export interface OspActivityDataQuestionBodyTest {
  text?: string;
  videos?: string[];
  image?: string;
  audio?: string;
}

export interface OspActivityDataQuestionBodyTag {
  CompassTags?: string[];
  SecondaryCompassTags?: string[];
  SubSkillSet?: string;
  Vocabulary?: string[];
  Rows?: number;
  Cols?: number;
  compassTags?: string[];
  secondaryCompassTags?: string[];
  subSkillSet?: string;
  vocabulary?: string[];
  rows?: number;
  cols?: number;
}

export interface OspActivityDataQuestionBodyOption {
  id: string;
  text?: string;
  audio?: string;
  image?: string;
  type?: string;
  rowIndex?: number;
  colIndex?: number;
  texts?: ActivityDataQuestionBodyOptionTextsItem[];
  images?: string[];
}

export interface OspActivityQuestionsBody {
  version: number;
  tags?: OspActivityDataQuestionBodyTag[];
  tests: OspActivityDataQuestionBodyTest[];
  answers: string[][];
  options: OspActivityDataQuestionBodyOption[];
  additionalLength?: number;
}

export interface OspActivityQuestions {
  Key: string;
  Body: OspActivityQuestionsBody;
}

export interface OspActivityDataStimulusBodyItem {
  text?: string;
  video?: string;
  image?: string;
  audio?: string;
}

// export interface OspActivityDataStimulusBodyTag {
//   CompassTags?: string[];
//   SecondaryCompassTags?: string[];
//   Text?: ActivityDataStimulusTag;
//   Image?: ActivityDataStimulusTag;
//   Audio?: ActivityDataStimulusTag;
//   Video?: ActivityDataStimulusTag;
// }

export interface OspActivityStimulusBody {
  item?: OspActivityDataStimulusBodyItem;
  tags?: ActivityDataStimulusBodyTag;
  // tags?: OspActivityDataStimulusBodyTag;
}

export interface OspActivityStimulus {
  Key?: string;
  Body?: OspActivityStimulusBody;
}

export interface OspActivity {
  Body: OspActivityBody;
  ContentKey: any;
  IsDynamicallyOrganized: boolean;
  Key: string;
  Questions: OspActivityQuestions[];
  Stimulus: OspActivityStimulus[];
  Tags: any[];
  Title: string;
  Type: string;
}
export interface OspPTContentDto {
  Activities: OspActivity[];
  Info: OspPTInfo;
  Resources: OspResource[];
}
