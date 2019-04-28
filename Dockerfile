<<<<<<< HEAD
FROM openjdk:8-jdk-slim
EXPOSE 8080
EXPOSE 8081

# Copy the YAML configuration file to /opt/conf
COPY src/main/resources/application.yml /opt/conf/application.yml
ENV CONFIG_LOCATION /opt/conf/application.yml

# Copy the fat jar file to /opt. This assumes that there are up to four jar files:
#   original-app-1.0.0.jar
#   app-1.0.0.jar
#   app-1.0.0-sources.jar
#   app-1.0.0-javadoc.jar
#
COPY target/[^original]*[^javadoc][^sources].jar /opt/app.jar

WORKDIR /opt

# Start the Dropwizard application
CMD ["sh", "-c", "java -jar /opt/app.jar server ${CONFIG_LOCATION}"]
=======
FROM openjdk:8-jre-alpine

ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    JHIPSTER_SLEEP=0 \
    JAVA_OPTS=""

# Add a jhipster user to run our application so that it doesn't need to run as root
RUN adduser -D -s /bin/sh jhipster
WORKDIR /home/jhipster

ADD entrypoint.sh entrypoint.sh
RUN chmod 755 entrypoint.sh && chown jhipster:jhipster entrypoint.sh
USER jhipster

ENTRYPOINT ["./entrypoint.sh"]

EXPOSE 8080 5701/udp

#ADD *.war app.war
ADD ./target/masternodes-online-supplement-api*.war app.war

>>>>>>> 0284cc9f6f3785d76bb0b3ca255ac2b96b28461d
