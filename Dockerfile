FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/forwordpanel-0.0.1-SNAPSHOT.jar app.jar
ADD forward_db.mv.db forward_db.mv.db
ADD forward_db.trace.db forward_db.trace.db
ADD src/main/resources/application-prod.properties application.properties
ENTRYPOINT ["java","-jar","/app.jar"]
