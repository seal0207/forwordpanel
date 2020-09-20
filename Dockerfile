FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/forwordpanel-0.0.1-SNAPSHOT.jar app.jar
ADD docker/forward_db.mv.db forward_db.mv.db
ADD docker/forward_db.trace.db forward_db.trace.db
ADD docker/application-prod.properties application.properties
ENTRYPOINT ["java","-jar","/app.jar"]
