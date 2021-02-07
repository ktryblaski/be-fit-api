ARG OPENJDK_VERSION

# BUILD
FROM openjdk:$OPENJDK_VERSION AS build

RUN mkdir code
WORKDIR code

COPY mvnw                               .
COPY .mvn                               .mvn
COPY pom.xml                            .
COPY be-fit-app/pom.xml                 be-fit-app/
COPY be-fit-domain/pom.xml              be-fit-domain/
COPY be-fit-dto/pom.xml                 be-fit-dto/
COPY be-fit-persistence/pom.xml         be-fit-persistence/
COPY be-fit-service/pom.xml             be-fit-service/
COPY be-fit-webservice/pom.xml          be-fit-webservice/

RUN ./mvnw dependency:go-offline
COPY . .
RUN ./mvnw clean package spring-boot:repackage

# RUN
FROM openjdk:$OPENJDK_VERSION

RUN useradd -m befit
USER befit
WORKDIR /home/befit
RUN mkdir app

COPY --from=build --chown=befit:befit /code/be-fit-app/target/be-fit-app-1.0-SNAPSHOT.jar app/be-fit-api.jar

EXPOSE 9090
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=remote", "./app/be-fit-api.jar"]
