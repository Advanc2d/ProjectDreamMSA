FROM openjdk:11-ea-jdk-slim
VOLUME /tmp
COPY build/libs/error-service-1.0.jar ErrorService.jar
ENTRYPOINT ["java", "-jar", "ErrorService.jar"]