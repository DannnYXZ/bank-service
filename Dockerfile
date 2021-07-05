FROM openjdk:11

COPY $CI_PROJECT_DIR'/build/libs/bank-0.0.1.jar' bank-service.jar

ENTRYPOINT ["java", "-jar", "bank-service.jar"]
