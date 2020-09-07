pipeline {
	agent any
	
	stages {

		stage('Retrieve Database'){
			steps { 
				dir ('Backup/') {
					sh './jenkins_backup_db.sh'			
				}
			}	
		}
		
		stage('Clean-up') {
                        steps {
                                dir("Docker/") {
                                        sh './clean_up_docker.sh'
                                }
                        }
                }


		stage('Deploy') {
			steps {
				dir("Docker/") {
					sh 'docker-compose up --build -d'
				}		
			}
		}

                stage('Wait for connection') {
                        steps {
                        	sh 'sleep 30'
                        }
                }
		
		stage('Load-backup') {
			steps {
				dir("Docker/") {
					sh 'cat ./Mysql/ArmadaDataBase-bak.sql | docker exec -i docker_mysql-db_1 /usr/bin/mysql -u root --database=fleet --password=password'	
				}
			}
		}
	}
}

