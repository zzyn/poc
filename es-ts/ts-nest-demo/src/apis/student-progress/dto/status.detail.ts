export interface PTSkill {
  code: string;
  score?: number;
  totalScore?: number;
}

export interface PTResult {
  score?: number;
  skills: PTSkill[];
}

export enum PTStatusDetailType {
  digital = "digital",
  traditional = "traditional",
}

export interface PTStatusDetail {
  type: PTStatusDetailType;
  result: PTResult;
}
