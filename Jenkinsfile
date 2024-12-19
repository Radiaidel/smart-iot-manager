pipeline {
    agent any
    environment {
        SONARQUBE_TOKEN = credentials('sonarqube-token')
        SONARQUBE = 'SonarQube-Server' // Le nom de votre serveur SonarQube configuré dans Jenkins
        DOCKER_IMAGE = 'aidar673/smart-iot-manager'  // Nom de votre image Docker
        DOCKER_REGISTRY = 'docker.io'            // Docker Hub comme registre
        DOCKER_TAG = 'latest'                    // Le tag de l'image
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Radiaidel/smart-iot-manager'
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
        stage('SonarQube Analysis') {
            steps {
                // Analyse SonarQube
                bat """
                    mvn sonar:sonar \
                    -Dsonar.host.url=http://$SONARQUBE \
                    -Dsonar.login=$SONARQUBE_TOKEN
                """
            }
        }
        stage('Build Docker Image') {
            steps {
                // Construction de l'image Docker
                bat """
                    docker build -t $DOCKER_IMAGE:$DOCKER_TAG .
                """
            }
        }
        stage('Push Docker Image') {
            steps {
                script {
                    // Connexion à Docker Hub et push de l'image
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
