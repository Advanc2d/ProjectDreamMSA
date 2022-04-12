FROM openjdk:11-ea-jdk-slim
VOLUME /tmp
COPY build/libs/main-service-1.0.jar MainService.jar
ENTRYPOINT ["java", "-jar", "MainService.jar"]