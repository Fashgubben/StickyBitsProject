#!/bin/bash
# Info: Updates service status web-page
# Usage: ./check_service.sh

set -o nounset


file="/var/www/html/index.html"
localfile="index.html"
tempfile='index.html.temp'

# Work-around to get right premissions to the file 
sudo /bin/cp -rf $file $localfile
touch $tempfile
sudo cat $localfile > $tempfile
sudo rm $localfile
mv $tempfile $localfile

# Apache variables
apache_row_status=57
apache_row_time=58
apache_already_running=false
apache_up_since=" "
apache_down_since=" "
apache_current_time=$(sed "${apache_row_time}q;d" $localfile | awk '{print $2}')

# MySQL variables
mysql_row_status=62
mysql_row_time=63
mysql_already_running=false
mysql_up_since=" "
mysql_down_since=" "
mysql_current_time=$(sed "${mysql_row_time}q;d" $localfile | awk '{print $2}')


update_html() {
	
	row_number=$1
	old_line=$2
	new_line=$3
	

	sed -i "${row_number}s/"$old_line"/"$new_line"/" $localfile
	sleep 1
}


check_apache() {

        if [ `systemctl is-active apache2` == "active" ] && [ $apache_already_running == 'false' ]; then
       		apache_online
        elif [ `systemctl is-active apache2` != "active" ] && [ $apache_already_running == 'true' ]; then
                apache_offline
	fi      
}

apache_online() {

	# Get new time stamp to the replacement line 
        apache_up_since="$(date +%Y-%m-%d_%H-%M-%S)"    
        
        # Update HTML
	update_html $apache_row_status "OFFLINE" "ONLINE"
	update_html $apache_row_status "rdot" "gdot"
	update_html $apache_row_time $apache_current_time $apache_up_since

        apache_current_time=$apache_up_since
        apache_already_running=true
}


apache_offline() {

	# Get new time stamp to the replacement line
        apache_down_since="$(date +%Y-%m-%d_%H-%M-%S)"

        # Update HTML
	update_html $apache_row_status "ONLINE" "OFFLINE" 
	update_html $apache_row_status "gdot" "rdot"
	update_html $apache_row_time $apache_current_time $apache_down_since 
	
        apache_current_time=$apache_down_since
        apache_already_running=false
}


check_mysql() {

if [ `systemctl is-active mysql` == "active" ] && [ $mysql_already_running == 'false' ]; then
        mysql_online
elif [ `systemctl is-active mysql` != "active" ] && [ $mysql_already_running == 'true' ]; then
        mysql_offline
fi
}


mysql_online() {

        # Get new time stamp to the replacement line 
        mysql_up_since="$(date +%Y-%m-%d_%H-%M-%S)"
       	
	# Update HTML-file 
	update_html $mysql_row_status "OFFLINE" "ONLINE"
        update_html $mysql_row_status "rdot" "gdot"
	update_html $mysql_row_time $mysql_current_time $mysql_up_since
        mysql_current_time=$mysql_up_since
        mysql_already_running=true
}


mysql_offline() {

	# Get new time stamp to the replacement line
        mysql_down_since="$(date +%Y-%m-%d_%H-%M-%S)"

        # Update HTML
        update_html $mysql_row_status "ONLINE" "OFFLINE"
        update_html $mysql_row_status "gdot" "rdot"
        update_html $mysql_row_time $mysql_current_time $mysql_down_since

        mysql_current_time=$mysql_down_since
        mysql_already_running=false
}



while true; do
	check_apache
	check_mysql

	# Overwrites the file in production with the new version 
	sudo /bin/cp -rf $localfile $file
	sleep 10
done
