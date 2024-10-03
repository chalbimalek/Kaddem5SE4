# Utiliser une image OpenJDK Runtime comme image parent
FROM openjdk:17-jdk-alpine
COPY target/kaddem-0.0.1-SNAPSHOT.jar .
# Exposer le port sur lequel l'application va écouter
EXPOSE 9040
# Commande pour exécuter l'application Spring Boot
ENTRYPOINT ["java", "-jar", "kaddem-0.0.1-SNAPSHOT.jar"]
