FROM openjdk:17
EXPOSE 8080
ARG JAR_FILE=target/current-account-service-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} current-account-service
ENTRYPOINT ["java","-jar","/current-account-service.jar"]
