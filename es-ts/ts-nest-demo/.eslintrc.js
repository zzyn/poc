const baseConfig = require("./config/eslintrc.base");

module.exports = {
  ...baseConfig,
  overrides: [
    {
      files: ["config/**/*.js"],
      rules: {
        "import/no-extraneous-dependencies": "off",
        "spaced-comment": "off",
      },
    },
    {
      files: ["**/*.js"],
      rules: {
        "@typescript-eslint/no-var-requires": "off",
        "@typescript-eslint/no-unused-expressions": "off",
        "@typescript-eslint/triple-slash-reference": "off",
      },
    },
    {
      files: ["**/*.spec.ts"],
      rules: {
        "@typescript-eslint/no-explicit-any": "off",
      },
    },
  ],
};
