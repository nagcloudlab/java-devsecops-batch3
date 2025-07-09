

```Dockerfile
ARG BASE_IMAGE=ubuntu:20.04
FROM ${BASE_IMAGE}
WORKDIR /app
RUN apt-get update && apt-get install -y openjdk-11-jdk maven
COPY target/myapp.jar /app/myapp.jar
ADD remote-config.properties /app/remote-config.properties
EXPOSE 8080
USER root
VOLUME /app/logs
HEALTHCHECK --interval=30s --timeout=10s --start-period=5s --retries=3 \
  CMD curl -f http://localhost:8080/actuator/health || exit 1
LABEL maintainer="Your Name <"
ENV JAVA_OPTS="-Xmx512m -Xms256m"
ENTRYPOINT ["java", "-jar", "/app/myapp.jar"]
CMD ["--spring.config.location=classpath:/,file:/app/remote-config.properties"]


```