## Feign SDK for Java  

### Building

`mvn clean package`

Running the package lifecycle will compile, run unit tests, and create a Fat JAR of the application in the top-level target directory.


### Publish to Nexus3

https://nexus3.xxxx.com

`mvn deploy`


### Usage

Refer to the [official documentation](https://maven.apache.org/plugins/maven-deploy-plugin/usage.html) for more information.

After the client library is installed/deployed, you can use it in your Maven project by adding the following to your *pom.xml*:

```xml
<dependency>
    <groupId>io.swagger</groupId>
    <artifactId>api-client-feign</artifactId>
    <version>1.0.0</version>
    <scope>compile</scope>
</dependency>

```

### Recommendation

It's recommended to create an instance of `ApiClient` per thread in a multithreaded environment to avoid any potential issues.


