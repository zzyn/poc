#set( $H = '#' )

$H$H Spring Boot WebMvc Archetype Application(MyBatis + Microsoft Sql Server)

The WebMvc archetype generates a template project for a spring boot webflux application. It provides the following:

* Web Framework (Spring Boot WebMvc) [Done]
* Web Server (Tomcat) [Done]
* Actuator [Done]
* Actuator Git Info Trace[Done]
* Actuator Security [Done]
* Spring AOP [Done]
* Private/Public Keys [Done]
* JWT Token [Done]
* JVM Remote Debug [Done]
* JVM Adjust and Optimize [Done]
* CI/CD Integration [Done]
* Support Config Inject [Done]
* Support Home Page [Done]
* Support Static Page [Done]
* Support Swagger Static File [Done]
* Support Swagger-UI [Done]
* Support Swagger-UI for Profile [Done]
* Support Cross-Origin Resource Sharing [Done]
* Support Pre-flighted Request [Done]
* Support Multi-Env Profile [Done]
* Support Json Serialization (Jackson) [Done]
* Sql Server Driver for JDBC [Done]
* DB Connect Pool (Druid) [Done]
* MyBatis Support [Done]
* MyBatis Adjust and Optimize [Done]
* Redis Cache [Done]
* APM Integration(Azure Application Insight) [Done]
* Unit tests(Mockito) [Done]
* Logging(Log4j2)(Cloud Watch) [Done]
* Docker image build [Done]
* Global Error Handler [Done]
* Code Coverage Open Clever [Done]

$H$H$H Project Structure

This project has five default modules:

```
${rootArtifactId}-app
${rootArtifactId}-contract
${rootArtifactId}-core
${rootArtifactId}-coverage
${rootArtifactId}-docker
${rootArtifactId}-domain
${rootArtifactId}-domain-impl
${rootArtifactId}-repository
${rootArtifactId}-repository-impl
```

$H$H$H Build Source

```
mvn clean package
```

$H$H$H Build with Report generation

```
mvn clean package clover:clover
```

Running the package lifecycle will compile, run unit tests, and create a Fat JAR of the application in the top-level target directory.

$H$H$H$H Maven Docker Build

```
mvn clean package docker:build
```

$H$H$H$H Jenkins Docker Build

```
sh ./docker-build.sh -i e1-pd/${rootArtifactId}:latest
```

$H$H$H Launching Application

$H$H$H$H Local Launch

To run the application locally, it's just a matter of executing the fat jar. From the top-level directory of this project, simply do:

```
java -jar target/${rootArtifactId}-1.0.0-SNAPSHOT-fat.jar --spring.profiles.active=dev
```

$H$H$H$H Docker Launch

```
docker run -e APP_JVM_OPTS='-Xmx256m -Xms128m' -e APP_PROFILE='--spring.profiles.active=dev' --net=host -t -d -p 80:80 e1-pd/${rootArtifactId}:latest /bin/bash
```

$H$H$H$H Attach Docker Container(Enter System)

```
docker exec -it CONTAINER_ID sh
```

$H$H$H Public/Private Keys

$H$H$H$H Generate RSA keys:

To generate a private key, you can run the following on your local machine:
```
openssl genrsa -out private_key.pem 2048
```

To generate a public key with the previously generated private key:
```
openssl rsa -pubout -in private_key.pem -out public_key.pem
```
