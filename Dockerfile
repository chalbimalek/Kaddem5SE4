FROM maven:3.8.4-openjdk-11 AS build
WORKDIR /app

# Install OpenJDK if needed
RUN apt-get update && apt-get install -y openjdk-11-jdk

# Disable disabled algorithms in Java security file
RUN sed -i 's/^jdk.tls.disabledAlgorithms=.*$/jdk.tls.disabledAlgorithms=/' /usr/lib/jvm/java-11-openjdk-amd64/conf/security/java.security

COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean install -DskipTests -Dhttps.protocols=TLSv1.2
