FROM maven:3.6.0-jdk-11-slim AS build
COPY src /app/src
COPY pom.xml /app
RUN mvn -f /app/pom.xml clean package spring-boot:repackage

FROM ubuntu-java-mysql
COPY --from=build /app/target/COMP30860-Web-Development-Group-Project-1.0-SNAPSHOT.jar /app/out/COMP30860-Web-Development-Group-Project-1.0-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/out/COMP30860-Web-Development-Group-Project-1.0-SNAPSHOT.jar"]

#version： --check
#services:
#    server:
#        build: .
#        restoart: always
#        prots:
#         - 8080:8080
#        depends_on:
#            - db
#
#    db:
#        platform: linux/x86_64
#        image: "mysql"  from docker hub