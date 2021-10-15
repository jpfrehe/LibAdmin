FROM maven:3.8.2-openjdk-11 AS MAVEN_BUILD
COPY pom.xml /build/
COPY src /build/src/
WORKDIR /build/
RUN mvn -DskipTests=true package

FROM openjdk:11-jdk
ARG JAVA_OPTS
COPY --from=MAVEN_BUILD ./build/target/*.jar /app/libadmin.jar
ENV JAVA_OPTS=$JAVA_OPTS
EXPOSE 8080
ENTRYPOINT exec java $JAVA_OPTS -jar /app/libadmin.jar