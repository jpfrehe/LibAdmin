FROM maven:3.8.2-openjdk-11 AS MAVEN_BUILD
COPY pom.xml /build/
COPY src/build/src/WORKDIR /build/
RUN mvn-DskipTests=true package

FROM openjdk:11-jdk
ARG JAVA_OPTS
COPY target/isbnvalidator-0.0.1-SNAPSHOT.jar isbnvalidator-0.0.1-SNAPSHOT.jar
ENV JAVA_OPTS=$JAVA_OPTS
EXPOSE 8080
ENTRYPOINT exec java $JAVA_OPTS -jar isbnvalidator-0.0.1-SNAPSHOT.jar