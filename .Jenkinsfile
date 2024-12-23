pipeline {
    agent any
    tools {
        maven 'maven'
        jdk 'JDK17'
    }
    environment {
        SONAR_TOKEN = credentials('bcf42071-9cd2-4211-b75e-2b551807522f')
    }
    stages {
        stage('Checkout') {
            steps {
                git credentialsId: 'a34501ce-0263-48d7-9380-5f8892714617',
                    branch: 'main',
                    url: 'https://github.com/agyassir/Jenkins-Workshop.git'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh 'mvn sonar:sonar -Dsonar.login=$SONAR_TOKEN'
                }
            }
        }
    }
}
