#!/bin/bash


file=/var/www/html/index.html
localfile=/home/monitor1/StickyBitsProject/Monitor/Server/index.html

sudo /bin/cp -rf $localfile $file
