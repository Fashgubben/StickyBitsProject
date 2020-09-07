#!/bin/bash

backup_dir=/home/backup/mysql
backup_latest=$(find $backup_dir -type f -printf '%T+ %p\n' | sort -r | head -n 1 | cut -d' ' -f2)

RESULT=$(docker exec docker_mysql-db_1 /usr/bin/mysqlshow -u root --password=password fleet| grep -v Wildcard | grep -o fleet)
if [ "$RESULT" == "fleet" ]; then
        docker exec docker_mysql-db_1 mysql -u root --password=password -e "drop database fleet"
fi
docker exec docker_mysql-db_1 mysql -u root --password=password -e "create database fleet"
cat $backup_latest | docker exec -i docker_mysql-db_1 /usr/bin/mysql -u root --password=password fleet

