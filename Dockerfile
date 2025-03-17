FROM maven:3.8.4-openjdk-17 as build
WORKDIR /build
COPY . .
RUN mvn clean package -DskipTests


FROM openjdk:17
WORKDIR /app
COPY --from=build ./build/target/*.jar ./app.jar
EXPOSE 8081

ENTRYPOINT java -jar app.jar
