pipeline {
  environment {
    dockerPushRegistry = "http://docker-registry.k8s.lan:30501"
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
          commitHash = "${env.GIT_COMMIT}"
          pomVersion = sh(script: 'mvn help:evaluate -Dexpression=project.version -q -DforceStdout', returnStdout: true)
        }
        sh 'mvn clean install'
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
            dockerImage.push(commitHash)
            dockerImage.push(pomVersion)
            dockerImage.push('latest')
          }
        }
      }
    }
    stage('Kubernetes Deploy') {
      agent any
      steps {
        sh "cat k8s.yml | sed -r 's/\\{\\{image_version\\}\\}/${commitHash}/g' | kubectl apply -f -"
      }
    }
  }
}