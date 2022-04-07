FROM openjdk:11-ea-jdk-slim
VOLUME /tmp
COPY build/libs/menu-service-1.0.jar MenuService.jar
ENTRYPOINT ["java", "-jar", "MenuService.jar"]