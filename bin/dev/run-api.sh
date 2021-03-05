#!/bin/bash

DIR=$( dirname "$0" )

mvn -f "${DIR}/../../pom.xml" clean install &&
mvn -f "${DIR}/../../pom.xml" spring-boot:run \
    -Dspring-boot.run.profiles=dev \
    -pl be-fit-web
