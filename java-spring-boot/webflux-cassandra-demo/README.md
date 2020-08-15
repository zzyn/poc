
## Spring Boot WebFlux Cassandra Application

The WebFlux archetype generates a template project for a spring boot webflux application. It provides the following:

* Reactive REST Framework (Spring Boot WebFlux) [Done]
* Async Server (Netty) [Done]
* Actuator [Done]
* Actuator Git Info Trace[Done]
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
* Spring Data Reactive Cassandra  [Done]
* Spring Data Reactive Redis [Done]
* Unit tests(Mockito) [Done]
* Logging(Log4j2) [Done]
* Global Error Handler [Done]
* Docker image build(AWS) [Done]
* Docker image build(QCloud)  []
* Integration K8S Config and Secret  []
* Integration Java AMP System [] 
* Integration Invoke Trace System []


### Build Source

```
mvn clean package
```

Running the package lifecycle will compile, run unit tests, and create a Fat JAR of the application in the top-level target directory.

#### Maven Docker Build

```
mvn clean package docker:build
```

#### Jenkins Docker Build

```
sh ./docker-build.sh -i e1-pd/webflux-cassandra-demo:latest
```

### Launching Application

#### Local Launch

To run the application locally, it's just a matter of executing the fat jar. From the top-level directory of this project, simply do:

```
java -jar target/webflux-cassandra-demo-1.0.0-fat.jar --spring.profiles.active=uat
```

#### Docker Launch

```
docker run -e APP_JVM_OPTS='-Xmx256m -Xms128m' -e APP_PROFILE='--spring.profiles.active=qa' -t -d -p 80:80 e1-pd/webflux-cassandra-demo:latest /bin/bash
```

#### Attach Docker Container(Enter System)

```
docker exec -it CONTAINER_ID sh
```

### Public/Private Keys

#### Generate RSA keys:

To generate a private key, you can run the following on your local machine:
```
openssl genrsa -out private_key.pem 2048
```

To generate a public key with the previously generated private key:
```
openssl rsa -pubout -in private_key.pem -out public_key.pem
```
