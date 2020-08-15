FROM maven:3.6.0-jdk-8-alpine AS build-env
WORKDIR /app

# copy everything else and build
COPY . ./
RUN mvn clean package -U

# build runtime image
FROM openjdk:8-jdk-alpine
WORKDIR /app

ENV APP_JVM_OPTS ""
ENV APP_PROFILE ""

COPY --from=build-env /app/target/*fat.jar ./app.jar

EXPOSE 80 8080 9000 5005

ENTRYPOINT exec java $APP_JVM_OPTS -jar app.jar $APP_PROFILE
