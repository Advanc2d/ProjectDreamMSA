FROM openjdk:11-ea-jdk-slim
VOLUME /tmp
COPY build/libs/loan-service-1.0.jar LoanService.jar
ENTRYPOINT ["java", "-jar", "LoanService.jar"]