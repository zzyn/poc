enum ProductModule {
  UnitQuiz = 16,
  MidTermExam = 2048,
  FinalExam = 4096,
}

enum MapType {
  UnitQuiz = 2,
  MidTermExam = 3,
  FinalExam = 4,
}

export function mapProductModuleToMapType(
  productModule: ProductModule,
): MapType {
  switch (productModule) {
    case ProductModule.UnitQuiz: {
      return MapType.UnitQuiz;
    }
    case ProductModule.MidTermExam: {
      return MapType.MidTermExam;
    }
    case ProductModule.FinalExam: {
      return MapType.FinalExam;
    }
    default: {
      throw new Error("invalid product type");
    }
  }
}

export { ProductModule, MapType };
