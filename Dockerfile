FROM amazoncoretto:17-alpine

COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN ./mvnw dependency:resolve

COPY src src
RUN ./mvnw package
WORKDIR todo
COPY target/*.jar todo.jar
ENTRYPOINT ["java", "-jocker ar", "todo.jar"]