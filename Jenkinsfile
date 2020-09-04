pipeline {
	agent any
	
	stages {
	
                stage('Clean-up') {
                        steps {
				dir("Docker/") [
					sh 'docker stop docker_app-java_1'
					sh 'docker stop docker_app-python_1'
					sh 'docker stop docker_web-apache_1'
					sh 'docker stop docker_mysql-db_1'
					sh 'docker rm docker_app-java_1'
                                        sh 'docker rm docker_app-python_1'
                                        sh 'docker rm docker_web-apache_1'
                                        sh 'docker rm docker_mysql-db_1'
					
					sh 'docker rmi docker_app-python'
					sh 'docker rmi docker_app-java'
					sh 'docker rmi python'
					sh 'docker rmi openjdk'
					sh 'docker rmi httpd'
					sh 'docker rmi mysql' 
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

