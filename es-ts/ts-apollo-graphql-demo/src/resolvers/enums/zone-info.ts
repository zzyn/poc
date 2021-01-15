import { registerEnumType } from "type-graphql";

enum ZoneInfo {
  zhCN = "zh-CN",
  enUS = "en-US",
  in = "in",
  ruRU = "ru-RU",
}

registerEnumType(ZoneInfo, {
  name: "ZoneInfo",
  description: "Zone Information",
});

export { ZoneInfo };
