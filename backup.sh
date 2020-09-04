#!/bin/bash

docker exec docker_mysql-db_1 /usr/bin/mysqldump -u root --password=password fleet > fleetbackup-$(date +\%F).sql
