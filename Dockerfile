FROM eclipse-temurin:17-jdk-alpine

ADD target/ClinicApp-Backend-0.0.1-SNAPSHOT.jar ClinicApp-Backend-0.0.1-SNAPSHOT.jar

ENTRYPOINT [ "java", "-jar", "/ClinicApp-Backend-0.0.1-SNAPSHOT.jar" ]