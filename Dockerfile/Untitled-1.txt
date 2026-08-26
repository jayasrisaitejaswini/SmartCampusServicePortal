FROM tomcat:9.0-jdk8-temurin

RUN rm -rf /usr/local/tomcat/webapps/*

COPY src/main/webapp /usr/local/tomcat/webapps/SmartCampusServicePortal

EXPOSE 8080

CMD ["catalina.sh", "run"]