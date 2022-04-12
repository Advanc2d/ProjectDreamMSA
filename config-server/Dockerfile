FROM openjdk:11-ea-jdk-slim
VOLUME /tmp
COPY build/libs/config-server-1.0.jar ConfigServer.jar
ENTRYPOINT ["java", "-jar", "ConfigServer.jar"]