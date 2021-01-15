export enum PTContentSkillCode {
  grammar = "grammar",
  vocabulary = "vocabulary",
  listening = "listening",
  reading = "reading",
  speaking = "speaking",
  writing = "writing",
}

export function mapStringToPTContentSkillCode(
  code: string,
): PTContentSkillCode {
  switch (code.toLocaleLowerCase()) {
    case "grammar": {
      return PTContentSkillCode.grammar;
    }
    case "vocabulary": {
      return PTContentSkillCode.vocabulary;
    }
    case "listening": {
      return PTContentSkillCode.listening;
    }
    case "reading": {
      return PTContentSkillCode.reading;
    }
    case "speaking": {
      return PTContentSkillCode.speaking;
    }
    case "writing": {
      return PTContentSkillCode.writing;
    }
    default:
      throw new Error("Invalid Skill Code");
  }
}

export interface ActivityRef {
  contentId: string;
  contentRevision: string;
  schemaVersion: number;
  activitySequence: number;
}

export interface PTContentSkill {
  code: PTContentSkillCode;
  activityRefs: ActivityRef[];
}

export interface PTContent {
  title: string;
  duration: number;
  skills: PTContentSkill[];
}
