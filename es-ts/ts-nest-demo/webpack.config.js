const path = require("path");

const externals = _externals();

module.exports = {
  mode: "none",
  entry: "./src/main.ts",
  devtool: "inline-source-map",
  externals,
  target: "node",
  output: {
    path: path.resolve(__dirname, "dist/"),
    filename: "bundle.js",
  },
  node: {
    __dirname: true,
  },
  module: {
    rules: [
      {
        test: /\.tsx?$/,
        use: "ts-loader",
        exclude: /node_modules/,
      },
    ],
  },
  optimization: {
    minimize: false,
  },
  resolve: {
    extensions: [".tsx", ".ts", ".js"],
  },
};

function _externals() {
  // eslint-disable-next-line global-require
  const manifest = require("./package.json");
  const { dependencies } = manifest;
  // eslint-disable-next-line @typescript-eslint/no-shadow
  const externals = {};
  // eslint-disable-next-line guard-for-in, no-restricted-syntax, prefer-const
  for (let p in dependencies) {
    externals[p] = `commonjs ${p}`;
  }
  return externals;
}
