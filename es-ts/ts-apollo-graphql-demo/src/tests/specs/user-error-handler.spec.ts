import * as uut from "../../dto/user-error/user-error-handler";

describe("user error", () => {
  it("handleErrors() should handle single error", () => {
    const input = {
      name: "ValidationError",
      path: "content",
      inner: [],
      message: "content must be at least 1 characters",
    };

    const expectedResult = [
      {
        message: "content must be at least 1 characters",
        path: "content",
      },
    ];

    const result = uut.handleErrors(input);
    expect(result).toStrictEqual(expectedResult);
  });

  it("handleErrors() should handle multiple errors", () => {
    const input = {
      name: "ValidationError",
      inner: [
        {
          name: "ValidationError",
          path: "id",
          message: "id must be UUID",
        },
        {
          name: "ValidationError",
          path: "content",
          message: "content must be at least 1 characters",
        },
        {
          name: "ValidationError",
          path: "content",
          message: "content is a required field",
        },
      ],
      message: "3 errors occurred",
    };

    const expectedResult = [
      {
        message: "id must be UUID",
        path: "id",
      },
      {
        message: "content must be at least 1 characters",
        path: "content",
      },
      {
        message: "content is a required field",
        path: "content",
      },
    ];

    const result = uut.handleErrors(input);
    expect(result).toStrictEqual(expectedResult);
  });
});
