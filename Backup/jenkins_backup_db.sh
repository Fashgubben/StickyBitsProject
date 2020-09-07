#!/bin/bash
backup_dir=./Docker/Mysqlmysql-dump/
db_name="ArmadaDataBase"

docker exec docker_mysql-db_1 /usr/bin/mysqldump -u root --password=password fleet > $backup_dir/$db_name.sql
