FROM eclipse-temurin:17-jre-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} .
ENTRYPOINT ["java", "-jar", "/${JAR_FILE}"]
