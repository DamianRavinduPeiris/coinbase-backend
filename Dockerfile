#Building the application.
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean install

#Running the application.
FROM eclipse-temurin:21-jre
WORKDIR /app
##The build name and the version is specified in the pom.xml file.
COPY --from=build /app/target/coinbase-0.0.1.jar coinbase.jar
EXPOSE 8080
CMD ["java", "-jar", "coinbase.jar"]
