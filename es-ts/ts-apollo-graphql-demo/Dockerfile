FROM ccr.ccs.tencentyun.com/xxxx/node12_sonarqube_scanner:1.0.0 AS build-env

WORKDIR /app

COPY package.json yarn.lock .npmrc ./

RUN yarn --frozen-lockfile

COPY . ./

RUN yarn package

RUN yarn test --coverage --coverageDirectory=../coverage

RUN sonar-scanner

FROM node:12.16.1-alpine

RUN sed -i 's/dl-cdn.alpinelinux.org/mirrors.aliyun.com/g' /etc/apk/repositories

RUN apk update && \
	apk upgrade && \
	apk add --no-cache nginx && \
	apk add curl

RUN mkdir -p /var/log/nginx

RUN npm install pm2 -g

ENV MAIN_ARGS = ""

WORKDIR /app

COPY --from=build-env /app/dist/. ./
COPY --from=build-env /app/ecosystem.config.yaml ./
COPY --from=build-env /app/package.json ./
COPY --from=build-env /app/.npmrc ./
COPY --from=build-env /app/yarn.lock ./

RUN yarn --frozen-lockfile

COPY ./docker-entrypoint.sh ./docker-entrypoint.sh
RUN chmod +x docker-entrypoint.sh

EXPOSE 4000

ENTRYPOINT ["./docker-entrypoint.sh"]

CMD exec pm2-runtime ecosystem.config.yaml $MAIN_ARGS


