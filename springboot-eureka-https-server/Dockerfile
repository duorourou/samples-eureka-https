FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
ARG PROFLE
ENV SPRING_PROFILES_ACTIVE=${PROFLE}
COPY ${JAR_FILE} /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]