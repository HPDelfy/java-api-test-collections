FROM maven:3.8.5-openjdk-17 AS builder
WORKDIR java-api-test-collections
COPY pom.xml /java-api-test-collections
COPY src /java-api-test-collections/src
ENTRYPOINT mvn verify -P e2e