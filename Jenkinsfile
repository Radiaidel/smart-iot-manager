pipeline {
    agent any
    environment {
        SONARQUBE_TOKEN = credentials('sonarqube-token') // ID du token stocké dans Jenkins
        SONARQUBE = 'SonarQube-Local'                   // Nom du serveur SonarQube configuré dans Jenkins
        DOCKER_IMAGE = 'aidar673/smart-iot-manager'     // Nom de l'image Docker
        DOCKER_REGISTRY = 'docker.io'                  // Docker Hub comme registre
        DOCKER_TAG = 'latest'                          // Tag de l'image
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'SIM-24', url: 'https://github.com/Radiaidel/smart-iot-manager'
            }
        }
        stage('Build') {
            steps {
                bat 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }



        stage('Build Docker Image') {
    steps {
        bat 'docker build -t %DOCKER_IMAGE%:%DOCKER_TAG% .'
    }
}
        stage('Push Docker Image') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'docker-credentials', passwordVariable: 'DOCKER_PASSWORD', usernameVariable: 'DOCKER_USERNAME')]) {
                        bat """
                            echo $DOCKER_PASSWORD | docker login -u $DOCKER_USERNAME --password-stdin $DOCKER_REGISTRY
                            docker push $DOCKER_REGISTRY/$DOCKER_IMAGE:$DOCKER_TAG
                        """
                    }
                }
            }
        }
    }
    post {
        always {
            echo 'Pipeline completed.'
        }
        success {
            echo 'Pipeline executed successfully!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}
