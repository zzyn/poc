FROM openjdk:11-jdk-slim

WORKDIR /app

COPY target/libs ./libs
COPY target/*.jar ./app.jar

# application
EXPOSE 8080
# java remote debug
EXPOSE 5005
# jacoco agent
EXPOSE 6300

ENTRYPOINT exec java -Dloader.path=libs -Duser.timezone=UTC $APP_JVM_OPTS -jar app.jar $APP_MAIN_ARGS