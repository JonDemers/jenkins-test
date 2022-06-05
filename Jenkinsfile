pipeline {
  environment {
    dockerPushRegistry = "http://debian-cloud-test:8083"
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
        script {
          pomVersion = sh(script: 'mvn help:evaluate -Dexpression=project.version -q -DforceStdout', returnStdout: true)
        }
        sh 'printenv'
        sh 'mvn clean install -s ./settings.xml'
      }
    }
    stage('Docker Build') {
      steps{
        sh 'printenv'
        script {
          docker.withRegistry(dockerPushRegistry) {
            dockerImage = docker.build(dockerPushImageName)
          }
        }
      }
    }
    stage('Docker Push') {
      steps{
        sh 'printenv'
        script {
          docker.withRegistry(dockerPushRegistry, dockerPushRegistryCredential) {
            dockerImage.push("${env.GIT_COMMIT}")
            dockerImage.push(pomVersion)
            dockerImage.push('latest')
          }
        }
      }
    }
    stage('Kubernetes Deploy') {
      agent any
      steps {
        sh 'printenv'
        sh 'echo cat k8s.yml _ sed -r "s/{{image_version}}/${env.GIT_COMMIT}/g" _ kubectl apply -f -'
      }
    }
  }
}