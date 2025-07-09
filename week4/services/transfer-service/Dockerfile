# ----------------------------------------------------------------------------------
# STAGE 1: Build the application using Maven and Azul Zulu JDK
# ----------------------------------------------------------------------------------

ARG MAVEN_VERSION=3.8.7
FROM maven:${MAVEN_VERSION}-zulu-17 AS builder

LABEL stage="builder"
LABEL maintainer="you@yourcompany.com"

# Set environment variables
ENV MAVEN_OPTS="-Dmaven.repo.local=/root/.m2/repository"

# Set working directory
WORKDIR /app

# Optimize layer caching: Copy only pom.xml and download dependencies first
COPY pom.xml .

RUN mvn dependency:go-offline -B

# Now copy full source and build
COPY src ./src

RUN mvn clean package -DskipTests -B

# ----------------------------------------------------------------------------------
# STAGE 2: Create minimal runtime image with Azul Zulu OpenJDK
# ----------------------------------------------------------------------------------

FROM azul/zulu-openjdk:17-jre

# Metadata labels for Docker Hub / OCI
LABEL org.opencontainers.image.title="Transfer Service" \
      org.opencontainers.image.description="Secure money transfer microservice" \
      org.opencontainers.image.version="0.0.1-SNAPSHOT" \
      org.opencontainers.image.authors="you@yourcompany.com" \
      org.opencontainers.image.source="https://github.com/your-org/transfer-service" \
      org.opencontainers.image.licenses="Apache-2.0"

# Set work directory
WORKDIR /app

# Copy only the built jar from builder stage
COPY --from=builder /app/target/transfer-service-0.0.1-SNAPSHOT.jar app.jar

# Create unprivileged user and switch to it
RUN adduser --system --group appuser
USER appuser

# Set environment variables
ENV SPRING_PROFILES_ACTIVE=default \
    JAVA_OPTS="-Xms256m -Xmx512m"

# Expose application port
EXPOSE 8080

# Healthcheck for container orchestration platforms
HEALTHCHECK --interval=30s --timeout=3s --start-period=30s CMD curl -f http://localhost:8080/actuator/health || exit 1

# Entry point (exec form for signal forwarding and better control)
ENTRYPOINT ["sh", "-c", "exec java $JAVA_OPTS -jar app.jar"]
