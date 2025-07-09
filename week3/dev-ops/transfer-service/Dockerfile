ARG base_image=amazoncorretto:17-alpine-jdk
ARG message="Hello, World!"
FROM ${base_image}
WORKDIR /app
RUN echo "${message}" > /message.txt
COPY ./target/transfer-service-0.0.1-SNAPSHOT.jar /app/transfer-service.jar
ENV JAVA_OPTS="-Xmx512m -Xms256m"
STOPSIGNAL SIGTERM
SHELL ["/bin/sh", "-c"]
USER root
HEALTHCHECK --interval=30s --timeout=30s --start-period=5s --retries=3 CMD [ "curl", "-f", "http://localhost:8080/actuator/health" ] || exit 1
ENTRYPOINT ["java", "-jar", "/app/transfer-service.jar" ]
CMD ["--spring.profiles.active=training"]

