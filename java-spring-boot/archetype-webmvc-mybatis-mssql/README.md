# WebMvc + Mybatis + Microsoft SqlServer Archetype


## Generate a Spring Boot WebMvc Microservice from this Archetype

To create the `demo-svc` sample project, run the below command from the folder you wish to create your project in.

```
mvn archetype:generate \
  -DarchetypeGroupId=com.spring.archetype \
  -DarchetypeArtifactId=webmvc-mybatis-mssql-archetype \
  -DarchetypeVersion=1.0.0 \
  -DgroupId=com.spring.demosvc \
  -DartifactId=demo-svc \
  -Dversion=1.0.0-SNAPSHOT \
  -Dentity=Test \
  -Dentity_camel=test
```
without interactive mode
```
mvn archetype:generate \
  -DarchetypeGroupId=com.spring.archetype \
  -DarchetypeArtifactId=webmvc-mybatis-mssql-archetype \
  -DarchetypeVersion=1.0.0 \
  -DgroupId=com.spring.demosvc \
  -DartifactId=demo-svc \
  -Dversion=1.0.0-SNAPSHOT \
  -Dentity=Test \
  -Dentity_camel=test \
  -DinteractiveMode=false \
  -DarchetypeCatalog=internal
```

1. A prompt will come up asking you to confirm the properties configuration, if you don't want to change anything just hit enter.
2. A folder will be created in your present working directory with that artifactId and a skeleton platform project will be created for you.

Archetype Properties:
* groupId - the maven groupId to use.
* artifactId - the root maven artifactId to use.
* version - the maven version ie. 1.0.0-SNAPSHOT 
* entity - the main business object class in the project.
* entity_camel - the main business object camel casing name.


It's worth noting that you can create a new project from this or use this to add a new module inside an existing project. If you run  the
command below from inside an existing project, a new module with your artifactId will be created inside the project and added to your parent pom.


**NOTE:** If generating on Windows the parent pom.xml will have Unix line endings.  We can't figure out why this is happening (PR welcome).  
For the time being run the following:

```
unix2dos pom.xml
```

## Sample Build Workflow
```
cd  demo-svc
git init
git add .
git commit -m "init"
mvn clean package
```

## Maven Settings

```
<profile>
    <id>edtechkt-cn-repos</id>
    <repositories>
        <repository>
            <id>nexus3-cn-kt-releases</id>
            <url>https://127.0.0.1/repository/maven-releases/</url>
        </repository>
        <repository>
            <id>nexus3-cn-kt-snapshots</id>
            <url>https://127.0.0.1/repository/maven-snapshots/</url>
        </repository>
        <repository>
            <id>central-aliyun</id>
            <url>https://maven.aliyun.com/repository/central</url>
        </repository>
    </repositories>
    <pluginRepositories>
        <pluginRepository>
            <id>nexus3-cn-kt-snapshots</id>
            <url>https://127.0.0.1/repository/maven-snapshots/</url>
            <releases>
                <enabled>false</enabled>
            </releases>
            <snapshots>
                <enabled>true</enabled>
            </snapshots>
        </pluginRepository>
        <pluginRepository>
            <id>nexus3-cn-kt-releases</id>
            <url>https://127.0.0.1/repository/maven-releases/</url>
            <releases>
                <enabled>true</enabled>
            </releases>
            <snapshots>
                <enabled>false</enabled>
            </snapshots>
        </pluginRepository>
    </pluginRepositories>
</profile>
```

```
<activeProfiles>
    <activeProfile>edtechkt-cn-repos</activeProfile>
</activeProfiles>
```

# Archetype Publish

## Building

```
mvn clean package
```

Running the package lifecycle will compile, run unit tests, and create a Fat JAR of the application in the top-level target directory.

## Install to Local Repository

```
mvn install
```

## Publish to Nexus3

https://nexus3-cn.englishtown.com

```
mvn deploy
```

