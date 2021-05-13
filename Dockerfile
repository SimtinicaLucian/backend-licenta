FROM openjdk:latest

COPY target/*.jar /srv/

EXPOSE 8080

ENTRYPOINT ["java","-jar","/srv/conta-0.0.1-SNAPSHOT.jar"]


