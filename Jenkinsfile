pipeline {
    agent { docker { image 'maven:3.8.5-eclipse-temurin-17' } }
    stages {
        stage('build') {
            steps {
                sh 'mvn clean install -s ./settings.xml'
            }
        }
    }
}