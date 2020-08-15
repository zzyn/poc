# Custom Graphql Datasource
Repository for datasource used by our services. Extended from Apollo Datasource.

## Get Started
Clone repo

## Build
Run

```
yarn build
```

## Deploy
When deploy, make sure use URL for hosted repository.
Do not use URLs for any other type like group or proxy.
Sample deploy commands are as follows,

```
npm login –-registry=http://127.0.0.1/repository/npm-hosted/
npm config get registry
npm config set registry http://127.0.0.1/repository/npm-hosted/
npm publish –-registry=http://127.0.0.1/repository/npm-hosted/
```
