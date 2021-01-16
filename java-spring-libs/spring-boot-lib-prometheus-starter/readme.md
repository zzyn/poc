# spring-boot-prometheus-starter

## Introduction

Add this dependency to config prometheus endpoint in spring-boot web project for grafana automatically.


## Usage

### Installing

**Maven**

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
<dependency>
  <groupId>com.spring.libs</groupId>
  <artifactId>spring-boot-prometheus-starter</artifactId>
  <version>1.0.0</version>
</dependency>
```

### Quickstart

1. Ensure declare `spring.application.name` and `spring.profiles.active` in your spring-boot web application.

2. Run your spring-boot web application, access http://localhost:8080/actuator/prometheus. 
   You should find a custom tag (application="${application-name} (${active-profile})") is added.
   
   
## Development   
  
### Prerequisites

- Java 8
- Maven 3.6.0 (or above)  
   
### Deployment

This library should be deploy to internal nexus3. 

#### Deploy steps

1. Complete the development work and commit on master branch.

2. Prepare release.

```bash
mvn release:prepare
```

In this step you will be asked to provide:

- The release version (e.g. 1.0.0)
- The git release tag (e.g. v1.0.0)
- The new development version (e.g. 1.0.1-SNAPSHOT)

3. Rollback if any error happen in step 2.

```bash
mvn release:rollback

git tag -d {tagName}
```

4. Perform the release.

```bash
mvn release:perform
```

