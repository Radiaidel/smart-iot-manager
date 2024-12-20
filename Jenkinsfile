pipeline {
        agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'SIM-8-ci-cd-integration', url: 'https://github.com/Radiaidel/smart-iot-manager.git'
            }
        }

        stage('Build') {
            steps {
                // Fix: Ensure `mvnw` has executable permissions
                sh 'chmod +x ./mvnw'
                // Use Maven to build the project
                sh './mvnw clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                // Ensure Docker is available
                sh 'docker --version'
                // Build the Docker image
                sh 'docker build -t app:latest .'
            }
        }

//         stage('SonarLint') {
//                     steps {
//                         withSonarQubeEnv('SonarQube') {
//                             sh 'mvn sonar:sonar'
//                         }
//                     }
//         }

        stage('Push Docker Image') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials-id', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                    sh '''
                        echo $DOCKER_PASSWORD | docker login -u $DOCKER_USERNAME --password-stdin || exit 1
                        docker tag app:latest $DOCKER_USERNAME/app:latest
                        docker push $DOCKER_USERNAME/app:latest
                    '''
                }
            }
        }


            stage('Deploy') {
                        steps {
                            sh 'docker-compose down && docker-compose up -d'
                        }
            }

        }

        post {
            always {
                echo 'Cleaning up Docker resources...'
                sh 'docker system prune -f || true' // Ensure it doesn't fail the pipeline
            }
            success {
                        echo 'Pipeline exécuté avec succès !'
            }
            failure {
                        echo 'Pipeline échoué.'
            }
        }
    }