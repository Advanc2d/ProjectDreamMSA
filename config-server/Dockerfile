FROM openjdk:11-ea-jdk-slim
VOLUME /tmp
COPY build/libs/config-server-1.0.jar config.jar
ENTRYPOINT ["java", "-jar", "config.jar"]