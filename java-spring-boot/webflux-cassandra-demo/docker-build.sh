#!/usr/bin/env bash

DOCKER_IMAGE_NAME=""

while getopts i: option
do
    case "$option" in
        i)
            DOCKER_IMAGE_NAME=$OPTARG
            #echo "option:i, value $OPTARG"
            echo "option:i, value $DOCKER_IMAGE_NAME";;
        \?)
            echo "Usage: args [-i]"
            echo "-i means docker images name"
            exit 1;;
    esac
done

docker rmi $DOCKER_IMAGE_NAME -f
docker build -t $DOCKER_IMAGE_NAME \
--add-host=nexus3.devops.svc.cluster.local:10.178.86.135 \
.