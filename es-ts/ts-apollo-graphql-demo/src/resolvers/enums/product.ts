import { registerEnumType } from "type-graphql";

enum Product {
  MT = 32,
  PT = 64,
  GT = 128,
}

registerEnumType(Product, {
  name: "product",
  description: "The product",
});

export { Product };
