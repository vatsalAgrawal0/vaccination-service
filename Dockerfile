FROM eclipse-temurin:23-jre-alpine
EXPOSE 8085

WORKDIR /app
COPY target/vaccination-service-*.jar ./vaccination-service.jar

ENTRYPOINT ["java", "-jar", "vaccination-service.jar"]
