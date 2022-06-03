pipeline {
  environment {
    dockerPushRegistry = "debian-cloud-test:8083/com.opcode.jenkins-test"
    dockerPushRegistryCredential = 'dockerPushRegistryCredential'
  }
  agent none
  stages {
    stage('Maven Build') {
      agent {
        docker {
          image 'maven:3.8.5-eclipse-temurin-17'
        }
      }
      steps {
        sh 'mvn clean install -s ./settings.xml'
      }
    }
    stage('Docker Build and Push') {
      steps{
        script {
          dockerImage = docker.build("${env.dockerPushRegistry}:${env.BUILD_ID}")
          customImage.push()
          customImage.push('latest')
        }
      }
    }
  }
}