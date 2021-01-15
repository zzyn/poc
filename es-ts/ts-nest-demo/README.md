# Nest Framework

<p align="center">
  <a href="http://nestjs.com/" target="blank"><img src="https://nestjs.com/img/logo_text.svg" width="320" alt="Nest Logo" /></a>
</p>

[circleci-image]: https://img.shields.io/circleci/build/github/nestjs/nest/master?token=abc123def456
[circleci-url]: https://circleci.com/gh/nestjs/nest

  <p align="center">A progressive <a href="http://nodejs.org" target="_blank">Node.js</a> framework for building efficient and scalable server-side applications.</p>
    <p align="center">
<a href="https://www.npmjs.com/~nestjscore" target="_blank"><img src="https://img.shields.io/npm/v/@nestjs/core.svg" alt="NPM Version" /></a>
<a href="https://www.npmjs.com/~nestjscore" target="_blank"><img src="https://img.shields.io/npm/l/@nestjs/core.svg" alt="Package License" /></a>
<a href="https://www.npmjs.com/~nestjscore" target="_blank"><img src="https://img.shields.io/npm/dm/@nestjs/common.svg" alt="NPM Downloads" /></a>
<a href="https://circleci.com/gh/nestjs/nest" target="_blank"><img src="https://img.shields.io/circleci/build/github/nestjs/nest/master" alt="CircleCI" /></a>
<a href="https://coveralls.io/github/nestjs/nest?branch=master" target="_blank"><img src="https://coveralls.io/repos/github/nestjs/nest/badge.svg?branch=master#9" alt="Coverage" /></a>
<a href="https://discord.gg/G7Qnnhy" target="_blank"><img src="https://img.shields.io/badge/discord-online-brightgreen.svg" alt="Discord"/></a>
<a href="https://opencollective.com/nest#backer" target="_blank"><img src="https://opencollective.com/nest/backers/badge.svg" alt="Backers on Open Collective" /></a>
<a href="https://opencollective.com/nest#sponsor" target="_blank"><img src="https://opencollective.com/nest/sponsors/badge.svg" alt="Sponsors on Open Collective" /></a>
  <a href="https://paypal.me/kamilmysliwiec" target="_blank"><img src="https://img.shields.io/badge/Donate-PayPal-ff3f59.svg"/></a>
    <a href="https://opencollective.com/nest#sponsor"  target="_blank"><img src="https://img.shields.io/badge/Support%20us-Open%20Collective-41B883.svg" alt="Support us"></a>
  <a href="https://twitter.com/nestframework" target="_blank"><img src="https://img.shields.io/twitter/follow/nestframework.svg?style=social&label=Follow"></a>
</p>
  <!--[![Backers on Open Collective](https://opencollective.com/nest/backers/badge.svg)](https://opencollective.com/nest#backer)
  [![Sponsors on Open Collective](https://opencollective.com/nest/sponsors/badge.svg)](https://opencollective.com/nest#sponsor)-->

## Description

[Nest](https://github.com/nestjs/nest) framework TypeScript starter repository.

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
git flow feature start XXXX-1234-setup-project
```

This will create a branch `feature/XXXX-1234-setup-project` from the develop branch.

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
export RUNNING_NODE_ENV=qa
```

## Date Utils

- use [moment](https://momentjs.com/) parsing, validating, manipulating, and formatting dates
- use [typescript moment](http://momentjs.cn/docs/use-it/typescript.html)

## Server Documents

- [nest.js](https://docs.nestjs.com/)

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

## Moniter/Metrics/Tracing

## Online Tools

## Publish npm package to Nexus3

```
npm login –-registry=https://127.0.0.1/repository/npm-hosted/
npm config get registry
npm config set registry https://127.0.0.1/repository/npm-hosted/
npm config set registry https://registry.npmjs.org/
npm publish packages/validators
```

## Docker Build

```
sh docker-build.sh -i bff-server:1.0.1
docker run -e MAIN_ARGS="--env qa" -t -d -p 4001:4000 bff-server:1.0.1 /bin/sh
docker exec -it {container_id} /bin/sh
```
