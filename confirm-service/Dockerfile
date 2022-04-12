FROM openjdk:11-ea-jdk-slim
VOLUME /tmp
COPY build/libs/confirm-service-1.0.jar ConfirmService.jar
ENTRYPOINT ["java", "-jar", "ConfirmService.jar"]