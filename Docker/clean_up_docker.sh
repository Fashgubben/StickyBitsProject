#!/bin/bash

if [ `docker ps | wc -l` -gt 1 ]; then
	docker stop $(docker ps -a -q)
fi

if [ `docker ps -a | wc -l` -gt 1 ]; then
	docker rm $(docker ps -a -q)
fi

if [ `docker images | wc -l` -gt 1 ]; then
	docker rmi $(docker images -q)
fi

