FROM maven:3.6.0-jdk-11-slim AS build
COPY src /app/src
COPY pom.xml /app
RUN mvn -f /app/pom.xml clean package

FROM openjdk:17-oracle
COPY --from=build /app/target/COMP30860-Web-Development-Group-Project-1.0-SNAPSHOT.jar /app/out/demo.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/out/demo.jar"]