#!/bin/bash

docker stop $(docker ps -a -q)
docker rm $(docker ps -a -q)
docker rmi docker_app-python
docker rmi docker_app-java
docker rmi python
docker rmi openjdk
docker rmi httpd
docker rmi mysql

