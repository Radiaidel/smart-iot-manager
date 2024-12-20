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
                // Utiliser Maven Wrapper pour Windows
                bat 'mvnw clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                // Vérifier que Docker est disponible
                bat 'docker --version'
                // Construire l'image Docker
                bat 'docker build -t app:latest .'
            }
        }

      stage('Push Docker Image') {
          steps {
              withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials-id', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                  echo "DOCKER_USERNAME: ${DOCKER_USERNAME}"
                  echo "DOCKER_PASSWORD: ${DOCKER_PASSWORD}" // This will be masked, so the password will not be shown
                  bat '''
                      echo %DOCKER_PASSWORD% | docker login -u %DOCKER_USERNAME% --password-stdin
                      docker tag app:latest %DOCKER_USERNAME%/app:latest
                      docker push %DOCKER_USERNAME%/app:latest
                  '''
              }
          }
      }


        stage('Deploy') {
            steps {
                // Déploiement avec Docker Compose
                bat 'docker-compose down && docker-compose up -d'
            }
        }
    }

    post {
        always {
            echo 'Nettoyage des ressources Docker...'
            bat 'docker system prune -f || true' // Assurez-vous que cette commande ne fait pas échouer le pipeline
        }
        success {
            echo 'Pipeline exécuté avec succès !'
        }
        failure {
            echo 'Pipeline échoué.'
        }
    }
}
