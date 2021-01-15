import { registerEnumType } from "type-graphql";
import { PTSkillCode } from "../../data-sources/general-test/model/general-test-pt-components";

export enum PartType {
  PT_GRAMMAR = "PT_GRAMMAR",
  PT_VOCABULARY = "PT_VOCABULARY",
  PT_LISTENING = "PT_LISTENING",
  PT_READING = "PT_READING",
  PT_WRITING = "PT_WRITING",
  PT_SPEAKING = "PT_SPEAKING",
}

export function mapPartType(skillCode: PTSkillCode): PartType {
  switch (skillCode) {
    case PTSkillCode.grammar:
      return PartType.PT_GRAMMAR;
    case PTSkillCode.vocabulary:
      return PartType.PT_VOCABULARY;
    case PTSkillCode.listening:
      return PartType.PT_LISTENING;
    case PTSkillCode.reading:
      return PartType.PT_READING;
    case PTSkillCode.writing:
      return PartType.PT_WRITING;
    case PTSkillCode.speaking:
      return PartType.PT_SPEAKING;

    default:
      throw new Error(`no mapping from ${skillCode}`);
  }
}

registerEnumType(PartType, {
  name: "PartType",
  description: "type of part in summary",
});
