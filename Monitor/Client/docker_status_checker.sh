#!/bin/bash
#
#set -o nounset

#
#Checks mysql
#
docker ps -f name=docker_mysql-db_1 | grep Up > /dev/null

if [ $? -ne 0  ]; then
	systemctl stop Mysql_status.service
else
	systemctl start Mysql_status.service
fi

#
#Checks python
#
docker ps -f name=docker_app-python_1 | grep Up > /dev/null

if [ $? -ne 0  ]; then
	systemctl stop Python_status.service
else
	systemctl start Python_status.service
fi

#
#Checks java
#
docker ps -f name=docker_app-java_1 | grep Up > /dev/null

if [ $? -ne 0  ]; then
	systemctl stop Java_status.service
else
	systemctl start Java_status.service
fi

#
#Checks apache-web
#
docker ps -f name=docker_web-apache_1 | grep Up > /dev/null

if [ $? -ne 0  ]; then
	systemctl stop Apache_status.service
else
	systemctl start Apache_status.service
fi

