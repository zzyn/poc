# Getting Started

Clone the repo, then run

Then run `yarn start` to start all packages in the development environment,

## Prerequisites

You need to install NodeJs, NPM, and Yarn in order to work on the project. It's recommended to install the [NVM](https://github.com/nvm-sh/nvm) through [Homebrew](https://brew.sh/). [This blog post](https://pawelgrzybek.com/install-nodejs-installer-vs-homebrew-vs-nvm/#nvm-node-version-manager) has the installation steps.

## Git

We integrate with [git-flow](https://danielkummer.github.io/git-flow-cheatsheet/) for creating branches. The naming convention is `feature/<task-id>-<name>`. To create a branch with git-flow

```bash
git flow feature start <task-id>-<name>
```

For example,

```bash
git flow feature start XX-1366-setup-project
```

This will create a branch `feature/XX-1366-setup-project` from the develop branch.

## Installing packages

To install an npm package (e.g. Ramda) run

```bash
yarn add ramda

// add to dev dependency
yarn add ramda -D
```

## Uninstalling packages

Go to the package.json file and remove the reference of the package you want to remove, and then run

```bash
yarn
```

This will check the package.json files and remove packages.

## Running the tests

Run `yarn test` to run all jest tests in all packages.

## Format and linting

Run `yarn format` to format the codebase.
Run `yarn lint` to lint the codebase.

## Switch Environment

```bash
echo 'env=qa' > .env
```

## Date Utils

- use [moment](https://momentjs.com/) parsing, validating, manipulating, and formatting dates
- use [typescript moment](http://momentjs.cn/docs/use-it/typescript.html)

## Server Documents

- [typegraphql](https://michallytek.github.io/type-graphql/docs/introduction.html)

## Code Standard

- [Typescript Guidance](https://github.com/Microsoft/TypeScript/wiki/Coding-guidelines)
- [Javascript Guidance](https://standardjs.com/)

## Module Standard

- :star: [CommonJs](http://wiki.commonjs.org/wiki/CommonJS)([out of date](https://github.com/nodejs/node-v0.x-archive/issues/5132#issuecomment-15432598)) support following syntax:()
  ```js
  const { XXX } = require("...");
  ```
- :star: ESNext/TypeScript(recommended) support following syntax:
  ```js
  import { XXX } from "./XXX";
  ```

## Performance

- :question: [clinic](https://github.com/nearform/node-clinic)

## Deployment

- :star: [pm2](https://pm2.keymetrics.io/)
- :star: [docker](https://hub.docker.com/)
- :star: [nginx](https://www.nginx.com/)

## CI/CD

- :star: jenkins

## Apollo Server Moniter/Metrics/Tracing

- :star: [apollo-opentracing](https://github.com/DanielMSchmidt/apollo-opentracing) + [jager](https://www.jaegertracing.io/)
- :star: [azure application insights](https://portal.azure.com/)
- :star: [health check](http://localhost:4000/.well-known/apollo/server-health)

## Online Tools

- :star: [graphql-playground](https://www.apollographql.com/docs/apollo-server/testing/graphql-playground/)>[localhost](http://localhost:4000/graphql)
- :star: [graphql-voyager](https://github.com/APIs-guru/graphql-voyager)>[localhost](http://localhost:4000/voyager)

## Publish npm package to Nexus3

```
npm login –-registry=http://127.0.0.1/repository/npm-hosted/
npm config get registry
npm config set registry http://127.0.0.1/repository/npm-hosted/
npm config set registry https://registry.npmjs.org/
npm publish packages/validators
```

## Docker Build

```
sh docker-build.sh -i bff-server:1.0.1
docker run -e MAIN_ARGS="--env qa" -t -d -p 4001:4000 bff-server:1.0.1 /bin/sh
docker exec -it {container_id} /bin/sh
```
