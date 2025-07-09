# FROM maven:3.8.7-amazoncorretto-17 as java_build_stage
# WORKDIR /app
# COPY pom.xml /app
# RUN mvn dependency:go-offline
# COPY src /app/src
# RUN mvn clean package -DskipTests

FROM amazoncorretto:17 as image_stage
WORKDIR /app
COPY ./target/java-web-service-0.0.1-SNAPSHOT.jar /app/jws.jar
EXPOSE 8080
USER root
ENV SPRING_PROFILES_ACTIVE=dev
ENTRYPOINT ["java", "-jar", "jws.jar"]
CMD ["--server.port=8080"]