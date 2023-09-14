FROM maven:3.8.1-openjdk-17-slim as DEPS

WORKDIR /opt/app
COPY pom.xml .

COPY api-shared/pom.xml api-shared/pom.xml
COPY server/pom.xml server/pom.xml
COPY client/pom.xml client/pom.xml

RUN mvn -e -C org.apache.maven.plugins:maven-dependency-plugin:3.1.2:go-offline -DexcludeArtifactIds=com.zerocopy.test:api-shared

COPY api-shared/src api-shared/src
WORKDIR /opt/app/api-shared
RUN mvn clean install -DskipTests=true

##################################################################

FROM maven:3.8.1-openjdk-17-slim as BUILDER

WORKDIR /opt/app
COPY --from=deps /root/.m2 /root/.m2
COPY --from=deps /opt/app/ /opt/app
COPY server/src server/src
COPY client/src client/src

RUN mvn -e -C -pl -api-shared clean package org.springframework.boot:spring-boot-maven-plugin:3.1.3:repackage -DskipTests=true

##################################################################

FROM openjdk:17-slim
ARG APP_EXECUTABLE_PATH

WORKDIR /opt/app
COPY --from=builder /opt/app/${APP_EXECUTABLE_PATH} ./app.jar
EXPOSE 8080
CMD [ "java", "-jar", "-Dspring.profiles.active=docker", "/opt/app/app.jar" ]
