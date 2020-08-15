
## Spring Boot Kafka Archetype Application

The Kafka archetype generates a template project for a spring boot kafka application. It provides the following:

* Spring Boot[Done]
* Kafka Consumer/Producer[Done]
* Kafka Streams [Done]
* Spring AOP [Done]
* JVM Remote Debug [Done]
* JVM Adjust and Optimize [Done]
* CI/CD Integration [Done]
* Support Config Inject [Done]
* Support Multi-Env Profile [Done]
* Support Json Serialization (Jackson) [Done]
* MyBatis Plus [Done]
* Unit tests(Mockito) [Done]
* Logging(Log4j2) [Done]
* Docker image build(AWS) [Done]
* Docker image build(QCloud)  [Done]
* Integration K8S Config and Secret  []
* Integration APM System [] 
* Integration Open Trace System []


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
sh ./docker-build.sh -i e1-pd/spring-kafka-demo:latest
```

### Launching Application

#### Local Launch

To run the application locally, it's just a matter of executing the fat jar. From the top-level directory of this project, simply do:

```
java -jar target/spring-kafka-demo-1.0.0-fat.jar --spring.profiles.active=qa
```

#### Docker Launch

```
docker run -e APP_JVM_OPTS='-Xmx256m -Xms128m' -e APP_PROFILE='--spring.profiles.active=qa' -t -d -p 80:80 e1-pd/spring-kafka-demo:latest /bin/bash
```

#### Attach Docker Container(Enter System)

```
docker exec -it CONTAINER_ID sh
```


### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring for Apache Kafka](https://docs.spring.io/spring-boot/docs/{bootVersion}/reference/htmlsingle/#boot-features-kafka)
* [Apache Kafka Streams Support](https://docs.spring.io/spring-kafka/docs/current/reference/html/_reference.html#kafka-streams)
* [Apache Kafka Streams Binding Capabilities of Spring Cloud Stream](https://docs.spring.io/spring-cloud-stream/docs/current/reference/htmlsingle/#_kafka_streams_binding_capabilities_of_spring_cloud_stream)

### Guides
The following guides illustrate how to use some features concretely:

* [Samples for using Apache Kafka Streams with Spring Cloud stream](https://github.com/spring-cloud/spring-cloud-stream-samples/tree/master/kafka-streams-samples)

