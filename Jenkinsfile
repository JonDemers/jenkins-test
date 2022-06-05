pipeline {
  environment {
    dockerPushRegistry = "http://debian-cloud-test:8083"
    dockerPushRegistryCredential = 'dockerPushRegistryCredential'
    dockerPushImageName = "com.opcode.jenkins-test"
  }
  agent any
  stages {
    stage('Maven Build') {
      agent {
        docker {
          image 'maven:3.8.5-eclipse-temurin-17'
        }
      }
      steps {
        script {
          pomVersion = sh(script: 'mvn help:evaluate -Dexpression=project.version -q -DforceStdout', returnStdout: true)
        }
        sh 'mvn clean install -s ./settings.xml'
      }
    }
    stage('Docker Build') {
      steps{
        script {
          docker.withRegistry(dockerPushRegistry) {
            dockerImage = docker.build(dockerPushImageName)
          }
        }
      }
    }
    stage('Docker Push') {
      steps{
        script {
          docker.withRegistry(dockerPushRegistry, dockerPushRegistryCredential) {
            dockerImage.push(pomVersion)
            dockerImage.push('latest')
          }
        }
      }
    }
    stage('Kubernetes Deploy') {
      steps {
        sh 'kubectl apply -f k8s.yml'
      }
    }
  }
}