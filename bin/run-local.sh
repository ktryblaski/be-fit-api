#!/bin/sh

DIR="$( dirname "$0" )"

mvn -f "$DIR"/../pom.xml clean install && mvn -f "$DIR"/../pom.xml spring-boot:run -pl be-fit-app
