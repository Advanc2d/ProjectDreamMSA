FROM openjdk:11-ea-jdk-slim
VOLUME /tmp
COPY build/libs/approve-service-1.0.jar ApproveService.jar
ENTRYPOINT ["java", "-jar", "ApproveService.jar"]