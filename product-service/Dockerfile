FROM openjdk:11-ea-jdk-slim
VOLUME /tmp
COPY build/libs/product-service-1.0.jar ProductService.jar
ENTRYPOINT ["java", "-jar", "ProductService.jar"]