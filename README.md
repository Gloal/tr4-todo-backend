# tr4-todo-backend

# Todo App with Spring Boot REST API

## Overview

This project implements a Todo list application with full CRUD functionality using Spring Boot and PostgreSQL. The application focuses on demonstrating API best practices such as Separation of Concerns, mocking for unit-testing, input validation, and clear,detailed error handling in the API responses.

The primary goal of this project was to build-on knowledge and practice concepts such as Test-Driven Development (TDD). Future enhancements would include implementing security measures, pagination for large datasets, and expanding test coverage for more robust validation.

## Technologies 

- **Spring Boot**
- **Java 17**
- **PostgreSQL**
- **Docker**

## EndPoints

- **Create**: Add new todo items.
- **Read**: Retrieve todo items individually or all at once.
- **Update**: Modify existing todo items.
- **Delete**: Remove todo items based on their unique identifiers.
  
![Swagger documentation(./Swagger-Endpoints.png)

## Installation

### Prerequisites

- Docker installed and running.
- Java Development Kit (JDK) version 17 or higher.
- Maven for dependency management.

### Steps to Run

#### Run with Maven

1. Clone the repository:

   ```bash
   git clone <repository-url>
   cd <project-directory>
   ```
   
2. Set Environment variables:
   ```env
   DB_HOST: ${{ secrets.DB_HOST }}
   DB_USER: ${{ secrets.DB_USER }}
   DB_PASSWORD: ${{ secrets.DB_PASSWORD }}
   DB_PORT: ${{ secrets.DB_PORT }}
   DB_NAME: ${{ secrets.DB_NAME }}

3. ```bash
   $ mvn clean install
   $ mvn spring-boot:run
   ```
   
#### Run with Docker

3. Build using Docker
    Docker image: `docker pull gloal/todo`
    
    `$ docker run -d --hostname=my-container --env DB_NAME=postgres --env DB_PORT=5432 --env DB_PASSWORD=postgres --env DB_USER=postgres --env SPRING_DATASOURC
    E_URL=jdbc:postgresql://db:5432/postgres --env SPRING_DATASOURCE_USERNAME=postgres --env SPRING_DATASOURCE_PASSWORD=postgres --network=bridge   --name=my-container --restart=no gloal/todo:latest
  `
