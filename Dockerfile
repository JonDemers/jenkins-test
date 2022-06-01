FROM eclipse-temurin:17-jre-alpine

EXPOSE 8080/tcp

COPY target/jenkins-test-*.jar jenkins-test.jar
ENTRYPOINT ["java", "-jar", "jenkins-test.jar"]
