# spring-boot-libs-webflux-exception

## Introduction

Custom error handler for Webflux and Servlet projects.
This code will auto configure the error handlers conditionally on
the web application type.

## Quickstart

1. Add the depencency to your project.

```xml
<dependency>
  <groupId>com.spring.libs</groupId>
  <artifactId>spring-boot-flux-exception</artifactId>
  <version>1.0.0</version>
</dependency>
```

2. Enable custom error handler.

```java
import EnableErrorHandler;

@Configuration
@EnableErrorHandler
public class ErrorHandlerConfiguration {
}
```

3. Add specific error response config to the `application.yml`.

```yml
server:
    error:
        # optional, set this to always to always include the stack trace in the error response.
        include-stacktrace: always
```

### Distribution

1. Prepare release.

```bash
mvn release:prepare
```

In this step you will be asked to provide:

- The release version (e.g. 1.0.0)
- The git release tag (e.g. v1.0.0)
- The new development version (e.g. 1.0.1-SNAPSHOT)

2. Rollback if any error happen in step 2.

You need to delete the tag in your local repo as `mvn release:rollback` would not delete it.

```bash
mvn release:rollback

git tag -d {tagName}
```

3. Perform the release.

```bash
mvn release:perform
```