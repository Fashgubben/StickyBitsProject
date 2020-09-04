pipeline {
	agent any
	
	stages {
	
		stage('Deploy') {
			steps {
				dir("Docker/") {
					sh 'docker-compose up --build -d'
				}	
			}
		}
	}
}

