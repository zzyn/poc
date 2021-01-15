export enum PTStatus {
  softDeleted = -1,
  ready = 1,
  waiting = 3,
  done = 4,
}

export interface PTSkill {
  code: PTSkillCode;
  score?: number;
  totalScore?: number;
}

export enum PTSkillCode {
  grammar = "grammar",
  vocabulary = "vocabulary",
  listening = "listening",
  reading = "reading",
  speaking = "speaking",
  writing = "writing",
}

export interface PTResult {
  score?: number;
  skills: PTSkill[];
}

export interface PTStatusDetail {
  type: PTSTatusDetailType;
  result: PTResult;
}

export enum PTSTatusDetailType {
  digital = "digital",
  tranditional = "traditional",
}

export interface PTDetailedResult {
  actualScore: number;
  duration: number;
  expectedScore: number;
  route: unknown;
  details: PTResultDetail[];
  extension: unknown;
}

export interface PTResultDetail {
  activityKey: string;
  activityVersion: string;
  questionKey: string;
  questionVersion: string;
  expectedScore: number;
  actualScore: number;
  answer: PTResultAnswer;
  duration: number;
  extension: PTResultDetailExtension;
}

export interface PTResultAnswer {
  modelData: unknown;
}

export interface PTResultDetailExtension {
  activityKey: string;
  skillCode: PTSkillCode;
}

export function mapStringToPTContentSkillCode(codeString: string): PTSkillCode {
  switch (codeString) {
    case PTSkillCode.grammar:
      return PTSkillCode.grammar;
    case PTSkillCode.vocabulary:
      return PTSkillCode.vocabulary;
    case PTSkillCode.listening:
      return PTSkillCode.listening;
    case PTSkillCode.reading:
      return PTSkillCode.reading;
    case PTSkillCode.writing:
      return PTSkillCode.writing;
    case PTSkillCode.speaking:
      return PTSkillCode.speaking;

    default:
      throw new Error(`no mapping from ${codeString}`);
  }
}
