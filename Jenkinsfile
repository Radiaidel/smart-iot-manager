pipeline {
    agent any
    stages {
      stage('Checkout') {
                steps {
                    git branch: 'main', url: 'https://github.com/Radiaidel/smart-iot-manager'
                }
            }
        stage('Build') {
            steps {
                sh './mvnw clean package'
            }
        }
        stage('Test') {
            steps {
                sh './mvnw test'
            }
        }
        stage('Build Docker Image') {
            steps {
                sh 'docker build -t aidar673/smart-iot-manager .'
            }
        }
        stage('Push to DockerHub') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-creds', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    sh 'docker login -u $DOCKER_USER -p $DOCKER_PASS'
                    sh 'docker push your-dockerhub-username/smart-iot-manager'
                }
            }
        }
    }
}
