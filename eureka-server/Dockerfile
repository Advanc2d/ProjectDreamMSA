FROM openjdk:11-ea-jdk-slim
VOLUME /tmp
COPY build/libs/eureka-server-1.0.jar EurekaServer.jar
ENTRYPOINT ["java", "-jar", "EurekaServer.jar"]