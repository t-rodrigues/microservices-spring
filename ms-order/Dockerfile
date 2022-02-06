# build

FROM maven:3.6.3-openjdk-17-slim as build
COPY src /app/src
COPY pom.xml /app
RUN mvn -f /app/pom.xml clean package -DskipTests

# run ms-order

FROM openjdk:17-jdk-slim
COPY --from=build /app/target/*.jar /app/ms-order.jar
EXPOSE 8080
CMD [ "java", "-jar", "/app/ms-order.jar" ]
