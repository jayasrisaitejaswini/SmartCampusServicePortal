FROM tomcat:9.0.121-jdk21-temurin

RUN rm -rf /usr/local/tomcat/webapps/*

COPY build/SmartCampusServicePortal.war /usr/local/tomcat/webapps/ROOT.war

CMD ["catalina.sh", "run"]