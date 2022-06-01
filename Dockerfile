FROM eclipse-temurin:17-jre-alpine
COPY target/jenkins-test-*.jar jenkins-test.jar
ENTRYPOINT ["java", "-jar", "jenkins-test.jar"]
