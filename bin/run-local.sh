#!/bin/sh

DIR="$( dirname "$0" )"

mvn -f "$DIR"/../pom.xml clean install spring-boot:run -pl be-fit-app
