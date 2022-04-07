FROM openjdk:11-ea-jdk-slim
VOLUME /tmp
COPY build/libs/manage-service-1.0.jar ManageService.jar
ENTRYPOINT ["java", "-jar", "ManageService.jar"]
#ENTRYPOINT ["java", "-Dspring.profiles.active=dev", "-jar", "ManageService.jar"]

