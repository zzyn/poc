# Spring Boot WebMvc Auth Library

### Latest Version
1.0.0

### Usage

Add dependency in ```pom.xml```

```
<dependency>
    <groupId>com.spring.libs</groupId>
    <artifactId>spring-boot-mvc-auth</artifactId>
    <version>1.0.0</version>
</dependency>
```

##### Use other path pattern(s)

 - [spring-boot-mvc-auth](readme.md) provide a default path pattern ```/api/**```. If you want change it, you can add configuration below in your ```application.yml``` and replace the ```path-patterns``` value with your patterns.

Sample:
```yaml
auth:
  path-patterns: /api/v1/**, /api/v2/**, /api/v3/**
```

##### dependency

 - [spring-boot-mvc-auth](readme.md) depend on project ```spring-boot-jwt```. You can see [spring-boot-jwt](/projects/ETKT/repos/lib-spring-boot-jwt/browse) to get how to use your own RSA keys.

### Features

Tokens reception

 - Use [@Jwt](src/main/java/com/spring/libs/mvc/auth/annotation/Jwt.java) before ```JwtContext``` param to receive [#IdentityToken](src/main/java/com/spring/libs/mvc/auth/model/IdentityToken.java)、[#AccessToken](src/main/java/com/spring/libs/mvc/auth/model/AccessToken.java) and [#RefreshToken](src/main/java/com/spring/libs/mvc/auth/model/RefreshToken.java) in request header.

Sample:
```java
import com.spring.libs.mvc.auth.annotation.Jwt;
import com.spring.libs.mvc.auth.model.JwtContext;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {
    
    @GetMapping
    public void test(@Jwt JwtContext context) {
        // context.getIdentityToken();
        // context.getAccessToken();
        // context.getRefreshToken();
    }
}
```

Tokens requirement

 - Use [@RequiredIdToken](src/main/java/com/spring/libs/mvc/auth/annotation/RequiredIdToken.java)、[@RequiredAccessToken](src/main/java/com/spring/libs/mvc/auth/annotation/RequiredAccessToken.java) or [@RequiredRefreshToken](src/main/java/com/spring/libs/mvc/auth/annotation/RequiredRefreshToken.java) on your method to validate tokens' requirement. They should be used with [@Jwt](src/main/java/com/spring/libs/mvc/auth/annotation/Jwt.java).

Sample:
```java
import com.spring.libs.mvc.auth.annotation.Jwt;
import com.spring.libs.mvc.auth.model.JwtContext;
import com.spring.libs.mvc.auth.annotation.RequiredIdToken;
import com.spring.libs.mvc.auth.annotation.RequiredAccessToken;
import com.spring.libs.mvc.auth.annotation.RequiredRefreshToken;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {
    
    @GetMapping
    @RequiredIdToken
    @RequiredAccessToken
    @RequiredRefreshToken
    public void test(@Jwt JwtContext context) {
        // TODO
    }
}
```

### In progress features

 - Tokens DB validation
   - [@IdTokenDbValidator](src/main/java/com/spring/libs/mvc/auth/annotation/IdTokenDbValidator.java)
   - [@AccessTokenDbValidator](src/main/java/com/spring/libs/mvc/auth/annotation/AccessTokenDbValidator.java)
   - [@RefreshTokenDbValidator](src/main/java/com/spring/libs/mvc/auth/annotation/RefreshTokenDbValidator.java)