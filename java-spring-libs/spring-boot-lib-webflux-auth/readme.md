# spring-boot-lib-webflux-auth

## Introduction

Add this dependency to add auth token authentication support.

## Technology

- Spring Webflux
- WebClient
- Spring Security

## Referencing

**Maven Dependency**

```xml
<dependency>
  <groupId>com.spring.libs</groupId>
  <artifactId>spring-boot-flux-auth</artifactId>
  <version>${spring-boot-flux-auth.version}</version>
</dependency>
```
**YML Configuration**

```yaml
kt:
  security:
    # required
    public-key: "keys/rsa/public_key.pem"
    # optional, default value is false, can be set to true for media service
    disable-authorization: false
    # optional if disable-authorization=true
    program: HFV3
    # optional if disable-authorization=true
    authorization-uri: http://auth2-qa.kt.eflabs.cn
    # optional
    exclude-paths:
      - '/test/api/excluded'
```
Please be noted on different cloud platforms, the key configurations are incompatible.
- On AWS, the public keys are downloaded to EC2 from S3, 
and the ECS task container must mount it to local first;
- On Tencent, the public keys are decoded into string value from sealed-secrets.

## Deployment

This library should be deploy to internal nexus3. 

#### Prerequest

Setup nexus credentials in your local maven settings.

```xml
<servers>
  <server>
    <id>kt-nexus3-release</id>
    <username>ci-user</username>
    <password>/* credential */</password>
  </server>
</servers>
```

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