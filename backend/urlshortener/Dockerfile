FROM maven:3.8.6-openjdk-21-slim AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:21-jdk-slim
COPY --from=build /target/urlshortener-0.0.1-SNAPSHOT.jar /urlshortener.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/urlshortener.jar"]



