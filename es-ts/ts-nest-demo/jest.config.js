module.exports = {
  preset: "ts-jest",
  testEnvironment: "node",
  rootDir: "./src",
  coveragePathIgnorePatterns: [
    "/src/sdks/",
    "src/db/",
    "/src/apis/hello/",
    "src/filter",
  ],
};
