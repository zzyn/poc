# Spring Boot WebMvc Cors lib

### Description

Use Cors to allow the api to access from other domain, also some configs on it  

## Latest version

1.0.0

### Usage

Add dependency in pom.xml

```
<dependency>
    <groupId>com.spring.libs</groupId>
    <artifactId>spring-boot-mvc-cors</artifactId>
    <version>1.0.0</version>
</dependency>
```

Add Configuration in your application.yml to override the default Cors configuartion

```
cors:
  allowed-headers: 'Content-Type, Accept, Authorization, If-Match, If-Modified-Since, If-None-Match, If-Unmodified-Since, Accept-Encoding, Accept-Language, Origin, X-EF-TOKEN, X-EF-ID, X-EF-ACCESS'
  allowed-methods: 'GET, PUT, POST, DELETE, PATCH, OPTIONS'
  allowed-origin: '*'
  max-age: '3600'
  default-api-route: '/api/,/paper/'
```