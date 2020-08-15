# build runtime image
FROM openjdk:8-jdk-alpine
WORKDIR /app

ENV APP_JVM_OPTS ""
ENV APP_PROFILE ""

COPY target/*.jar ./app.jar

EXPOSE 80 8080 5005

ENTRYPOINT exec java $APP_JVM_OPTS -jar app.jar $APP_PROFILE