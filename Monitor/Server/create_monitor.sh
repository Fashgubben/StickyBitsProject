#!/bin/bash
# Info: A script to set up Monitor environment.
# Usage: Use scp to transfer this script to a newly created monitor instance.
# Run: ./create_monitor.sh


# Download all necessities
sudo apt-get update
sudo apt-get upgrade -y
sudo apt install apache2 -y
sudo apt install iputils-ping -y
sudo apt install git -y
sudo apt install vim -y
sudo apt install cron -y
sudo apt install tree -y

# Add users
sudo adduser monitor1
sudo adduser monitor2

# Gives monitor sudo permission
sudo usermod -aG sudo monitor1
sudo usermod -aG sudo monitor2


# Makes sure right password is inserted
transaction=false
	while [ $transaction == "false" ]; do
		echo "--- Log in as monitor1 ---"
		su - monitor1
		if [ $? -eq 0 ]; then
			transaction=true
		fi
	done

# Clone repository
git clone https://github.com/Fashgubben/StickyBitsProject.git

# Create services
local_file="/home/monitor1/StickyBitsProject/Monitor/Server/Daemons/sticky_monitor.service"
service_path="/etc/systemd/system/"
cp $local_file $service_path
sudo systemctl daemon-reload
sudo systemctl start sticky_monitor.service

# Create cronjob
sudo echo "* * * * * /home/monitor1/StickyBitsProject/Monitor/Server/move_html.sh" >> /var/spool/cron/crontabs/root 

# Generay ssh-key
ssh-keygen -t rsa
