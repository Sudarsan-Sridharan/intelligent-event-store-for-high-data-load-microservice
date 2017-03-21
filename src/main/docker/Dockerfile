FROM tomcat:7-jre7
MAINTAINER xyz

RUN ["rm", "-fr", "/usr/local/tomcat/webapps/ROOT"]
COPY intelligent-event-store-for-high-data-load-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

CMD ["catalina.sh", "run"]