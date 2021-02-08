#!/bin/bash

DB_NAME="befit"
DB_USER="postgres"
DB_PASSWORD="qwerty123456"

HOST_PORT=15432

VOLUME_NAME="be-fit-api-db-data"
CONTAINER_NAME="be-fit-api-db"

POSTGRES_VERSION="13.1-alpine"

if [[ "$1" = "--reset" ]]; then
  if [[ -n "$(docker container ls -a | grep ${CONTAINER_NAME})" ]]; then
    echo "Removing old container..."
    docker container rm -f ${CONTAINER_NAME}
  fi

  if [[ -n "$(docker volume ls | grep ${VOLUME_NAME})" ]]; then
    echo "Removing old volume..."
    docker volume rm -f ${VOLUME_NAME}
  fi
fi

container=$(docker container ls | grep ${CONTAINER_NAME})
if [[ -z "$container" ]]; then
  echo "Starting a container..."
  docker run \
          --name ${CONTAINER_NAME} \
          -e POSTGRES_DB=${DB_NAME} \
          -e POSTGRES_USER=${DB_USER} \
          -e POSTGRES_PASSWORD=${DB_PASSWORD} \
          -p ${HOST_PORT}:5432 \
          -v ${VOLUME_NAME}:/var/lib/postgresql/data \
          -d --rm  \
          postgres:${POSTGRES_VERSION}
else
  echo "A container already exists."
fi
