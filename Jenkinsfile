pipeline {
    agent any

    environment {
        DOCKER_USERNAME = 'aidar673'
        DOCKER_IMAGE = 'aidar673/smart-iot-manager'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'SIM-8-ci-cd-integration', url: 'https://github.com/Radiaidel/smart-iot-manager.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvnw clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh "docker build -t ${DOCKER_IMAGE}:latest ."
            }
        }

        stage('Push Docker Image') {
            steps {
              withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                  sh '''
                  echo $DOCKER_PASSWORD | docker login -u $DOCKER_USERNAME --password-stdin
                  docker push docker.io/library/app:latest
                  '''
              }
            }
        }

        stage('Deploy') {
            steps {
                sh 'docker-compose down || true'
                sh 'docker-compose up -d'
            }
        }
    }

    post {
        always {
            sh 'docker logout || true'
            sh 'docker system prune -f || true'
        }
        success {
            echo 'Pipeline executed successfully!'
        }
        failure {
            echo 'Pipeline failed.'
        }
    }
}