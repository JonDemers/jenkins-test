pipeline {
  environment {
    dockerPushRegistry = "debian-cloud-test:8083/com.opcode.jenkins-test"
    dockerPushRegistryCredential = 'dockerPushRegistryCredential'
  }
  agent {
    docker {
      image 'maven:3.8.5-eclipse-temurin-17'
    }
  }
  stages {
    stage('Maven Build') {
      steps {
        sh 'mvn clean install -s ./settings.xml'
      }
    }
    stage('Docker Build') {
      steps{
        script {
          dockerImage = docker.build registry + ":$BUILD_NUMBER"
        }
      }
    }
  }
}