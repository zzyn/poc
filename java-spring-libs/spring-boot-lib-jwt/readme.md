# Spring Boot JSON Web Token Library

### Latest Version
1.0.0

### Usage

Add dependency in ```pom.xml```

```
<dependency>
    <groupId>com.spring.libs</groupId>
    <artifactId>spring-boot-jwt</artifactId>
    <version>1.0.0</version>
</dependency>
```

Encode Decode and Verify token

 - Use [#TokenUtils](src/main/java/com/spring/libs/jwt/utils/TokenUtils.java) to encode、 decode and verify token.

##### Use other RSA keys

 - [spring-boot-jwt](readme.md) provide default RSA keys. You can change configuration(application.yml) to use other keys.

```yaml
jwt:
  private-key: 'keys/rsa/private_key.pem'
  public-key: 'keys/rsa/public_key.pem'
```