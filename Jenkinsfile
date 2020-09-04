pipeline {
	agent any
	
	stages {
	
                stage('Clean-up') {
                        steps {
				dir("Docker/") [
					sh 'docker ps -f name=docker_app-java_1 -q | xargs --no-run-if-empty docker container stop'
					sh 'docker container ls -a -fname=docker_app-java_1 -q | xargs -r docker container rm'

                                        sh 'docker ps -f name=docker_app-python_1 -q | xargs --no-run-if-empty docker container stop'
                                        sh 'docker container ls -a -fname=docker_app-python_1 -q | xargs -r docker container rm'

                                        sh 'docker ps -f name=docker_mysql-db_1 -q | xargs --no-run-if-empty docker container stop'
                                        sh 'docker container ls -a -fname=docker_mysql-db_1 -q | xargs -r docker container rm'

                                        sh 'docker ps -f name=docker_web-apache_1 -q | xargs --no-run-if-empty docker container stop'
                                        sh 'docker container ls -a -fname=docker_web-apache_1 -q | xargs -r docker container rm'
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

