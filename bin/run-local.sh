#!/bin/sh

DIR="$( dirname "$0" )"

mvn -f "$DIR"/../pom.xml clean spring-boot:run -pl be-fit-app
