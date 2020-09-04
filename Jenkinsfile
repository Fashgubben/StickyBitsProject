pipeline {
	agent any
	
	stages {
		stage('Deploy') {
			steps {
				dir("Docker/") {
					sh 'sudo docker-compose up --build'
				}	
			}
		}
	}
}

