# Use a multi-stage build for efficiency
FROM maven:3.8.4-openjdk-11 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline

# Mettre à jour les certificats CA
RUN apt-get update && apt-get install -y ca-certificates && update-ca-certificates

# Modifier les paramètres de sécurité Java pour désactiver les algorithmes TLS désactivés
RUN sed -i 's/^jdk.tls.disabledAlgorithms=.*$/jdk.tls.disabledAlgorithms=/' /etc/java-11-openjdk/security/java.security

COPY src ./src
RUN mvn clean install -DskipTests

# Second stage: Running the application
FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/target/kaddem-0.0.1-SNAPSHOT.jar /app/kaddem.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/kaddem.jar"]
