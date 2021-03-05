#!/bin/bash

DIR=$( dirname "$0" )

mvn -f "${DIR}/../../pom.xml" clean install &&
mvn -f "${DIR}/../../pom.xml" spring-boot:run \
    -Dspring-boot.run.profiles=dev \
    -Dspring-boot.run.jvmArguments=-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8000 \
    -pl be-fit-web
