#Building the application.
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean install

#Running the application.
FROM eclipse-temurin:21-jre
WORKDIR /app
##The build name is specified in the pom.xml file.
COPY --from=build /app/target/github-actions-demo-0.0.1-SNAPSHOT.jar github-actions-demo.jar
EXPOSE 8080
CMD ["java", "-jar", "coinbase.jar"]
