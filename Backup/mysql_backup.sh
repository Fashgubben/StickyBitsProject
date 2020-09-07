#!/bin/bash

backup_dir=/home/backup/mysql
date=$(date +%Y_%m_%d_%T)
db_name="fleet"

docker exec docker_mysql-db_1 /usr/bin/mysqldump -u root --password=password fleet > $backup_dir/$db_name-$date.sql

find $backup_dir -mtime +7 -type f -delete
