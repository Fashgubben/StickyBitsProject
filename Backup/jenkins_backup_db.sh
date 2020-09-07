#!/bin/bash
pwd

backup_dir=../Docker/Mysql
db_name="ArmadaDataBase-bak"

# Takes backup and place in right directory 
docker exec docker_mysql-db_1 /usr/bin/mysqldump -u root --password=password fleet > $backup_dir/$db_name.sql
