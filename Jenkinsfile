pipeline {
	agent any
	
	stages {
	
                stage('Clean-up') {
                        steps {
                                sh 'docker stop $(docker ps)'
                                sh 'docker rm $(docker ps -a -q)'
                                sh 'docker rmi $(docker images -a -q)'
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

