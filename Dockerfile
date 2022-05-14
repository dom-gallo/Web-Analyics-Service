#Base docker image
FROM openjdk:17
MAINTAINER dgallo519@protonmail.com
COPY target/analytics-server-dev.jar analytics-server-dev.jar
ENTRYPOINT ["java","-jar","/analytics-server-dev.jar"]