module.exports = {
  plugins: [
    "@typescript-eslint",
    "eslint-comments",
    "jest",
    "promise",
    "unicorn",
  ],
  extends: [
    "airbnb-typescript",
    "plugin:@typescript-eslint/recommended",
    "plugin:eslint-comments/recommended",
    "plugin:jest/recommended",
    "plugin:promise/recommended",
    "plugin:unicorn/recommended",
    "prettier",
    "prettier/@typescript-eslint",
  ],
  settings: {
    "import/resolver": {
      node: {
        extensions: [".js", ".ts", ".tsx", ".d.ts"],
        paths: ["node_modules/", "node_modules/@types/"],
      },
    },
  },
  env: {
    node: true,
    browser: true,
    jest: true,
    es6: true,
  },
  ignorePatterns: ["**/node_modules", "**/generated", "**/build", "**/dist"],
  rules: {
    "max-depth": ["error", 4],
    "max-lines": [
      "error",
      {
        max: 300,
        skipBlankLines: true,
        skipComments: true,
      },
    ],
    "max-len": [
      1,
      80,
      2,
      {
        ignorePattern: "^import\\s.+\\sfrom\\s.+;$",
        ignoreUrls: true,
        ignoreStrings: true,
        ignoreTemplateLiterals: true,
        ignoreComments: true,
      },
    ],
    // Too restrictive, writing ugly code to defend against a very unlikely scenario: https://eslint.org/docs/rules/no-prototype-builtins
    "no-prototype-builtins": "off",
    // https://basarat.gitbooks.io/typescript/docs/tips/defaultIsBad.html
    "import/prefer-default-export": "off",
    "import/no-default-export": "error",
    // Use function hoisting to improve code readability
    "no-use-before-define": [
      "error",
      { functions: false, classes: true, variables: true },
    ],
    "@typescript-eslint/no-use-before-define": [
      "error",
      { functions: false, classes: true, variables: true, typedefs: true },
    ],
    // Common abbreviations are known and readable
    "unicorn/prevent-abbreviations": "off",
    // Too restrictive
    "eslint-comments/disable-enable-pair": "off",
    "@typescript-eslint/explicit-function-return-type": "off",
    radix: "off",
    // Conflicts with ramda placeholder,
    "no-underscore-dangle": "off",
    "no-restricted-globals": "off",
    // Doesn't work in mono-repo
    "import/no-extraneous-dependencies": "off",
    "class-methods-use-this": "off",
  },
};
