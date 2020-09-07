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
	}
}

