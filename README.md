# be-fit API

### Running 

```sh
$ mvn clean install && mvn spring-boot:run -Dspring-boot.run.profiles=dev -pl be-fit-web
```

### Building jar file 

```sh
$ mvn clean package spring-boot:repackage
$ java -jar -Dspring.profiles.active=${PROFILE} be-fit-web-1.0-SNAPSHOT.jar
```