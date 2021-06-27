FROM openjdk:11

COPY 'build/libs/bank-0.0.1.jar' bank-service.jar

ENTRYPOINT ["java", "-jar", "bank-service.jar"]
