#!/bin/bash

docker exec docker_mysql-db_1 mysql -u root --password=password -e "create database 'fleet2'"
cat fleetbackup-2020-09-04.sql | docker exec -i docker_mysql-db_1 /usr/bin/mysql -u root --password=password fleet2
