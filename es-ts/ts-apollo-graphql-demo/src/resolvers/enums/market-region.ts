import { registerEnumType } from "type-graphql";

enum MarketRegion {
  CHINA = "CHINA",
  RUSSIA = "RUSSIA",
  Indonesia = "Indonesia",
}

registerEnumType(MarketRegion, {
  name: "MarketRegion",
  description: "The business Market Region",
});

export { MarketRegion };
