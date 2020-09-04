pipeline {
	agent any
	
	stages {
		stage('Clean-up'){
			steps {
				sh './docker-cleanup'
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

