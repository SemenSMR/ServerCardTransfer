FROM openjdk:17-jdk-alpine

EXPOSE 5500:5500

ADD target/ServicecardTransfer-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
