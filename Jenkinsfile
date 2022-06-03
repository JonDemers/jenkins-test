pipeline {
  environment {
    dockerPushRegistry = "debian-cloud-test:8083"
    dockerPushRegistryCredential = 'dockerPushRegistryCredential'
    dockerPushImageName = "com.opcode.jenkins-test"
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
          docker.withRegistry(dockerPushRegistry, dockerPushRegistryCredential) {
            dockerImage = docker.build("${env.dockerPushImageName}:${env.BUILD_ID}")
            dockerImage.push()
            dockerImage.push('latest')
          }
        }
      }
    }
  }
}