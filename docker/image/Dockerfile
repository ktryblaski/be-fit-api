ARG MAVEN_VERSION
ARG OPENJDK_VERSION

# BUILD
FROM maven:$MAVEN_VERSION AS build

RUN mkdir /code
WORKDIR /code
COPY . .

RUN mvn clean package spring-boot:repackage

# RUN
FROM openjdk:$OPENJDK_VERSION

EXPOSE 9090

RUN mkdir /be-fit
WORKDIR /be-fit
COPY --from=build /code/be-fit-app/target/be-fit-app-1.0-SNAPSHOT.jar ./be-fit-app.jar

ARG DB_HOST
ARG DB_PORT
ARG DB_NAME
ARG DB_USERNAME
ARG DB_PASSWORD

ENV SPRING_APPLICATION_JSON="{\"spring\":{\"datasource\":{\"url\":\"jdbc:postgresql://$DB_HOST:$DB_PORT/$DB_NAME\",\"username\":\"$DB_USERNAME\",\"password\":\"$DB_PASSWORD\"}}}"

CMD java -jar -Dspring.profiles.active=remote ./be-fit-app.jar