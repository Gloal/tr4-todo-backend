FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
ARG SKIP_TESTS=true
RUN if [ "$SKIP_TESTS" = "true" ]; then mvn clean package -DskipTests; else mvn clean package; fi


FROM amazoncorretto:17
WORKDIR /app
COPY --from=build app/target/todo-0.0.1-SNAPSHOT.jar /app/todo.jar
EXPOSE 8080

#Need to define environment variables
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://${DB_HOST}:${DB_PORT}/${DB_NAME}
ENV SPRING_DATASOURCE_USERNAME=${DB_USER}
ENV SPRING_DATASOURCE_PASSWORD=${DB_PASSWORD}

ENTRYPOINT ["java", "-jar", "todo.jar"]