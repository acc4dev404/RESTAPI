FROM maven:3.9.6-eclipse-temurin-21-alpine AS builder
WORKDIR /app
COPY src/ src
COPY pom.xml ./
COPY jolokia-agent-jvm-2.1.0-javaagent.jar ./
RUN mvn package

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/RESTAPI-0.0.1-SNAPSHOT.jar .
COPY --from=builder /app/jolokia-agent-jvm-2.1.0-javaagent.jar .
EXPOSE 8000
EXPOSE 8778
CMD ["java", "-javaagent:jolokia-agent-jvm-2.1.0-javaagent.jar=port=8778,host=0.0.0.0", "-jar", "RESTAPI-0.0.1-SNAPSHOT.jar"]