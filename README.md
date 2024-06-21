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


## Description
This project is a REST API designed to create, retrieve, update, and delete to-do list items. It utilizes PostgreSQL as the repository. The primary goal is to showcase skills in creating a REST API with CRUD functionalities while adhering to best practices such as testing, naming conventions, code structure, and readability.

## Approach
The application follows the principles of Spring Boot’s Inversion of Control (IoC) to create loose coupling between components. Mocking and validation are used extensively to ensure the robustness of the code. Custom exceptions and detailed error handling have been implemented to provide clear and informative error messages.

## Technologies
- Spring Boot 3.2
- Java 17
- PostgreSQL
- Docker
- Docker Compose
- JUnit
- Postman

## Endpoints
- Create new to-do list items
- Retrieve existing to-do list items
- Retrieve existing to-do items by ID
- Update existing to-do list items
- Delete existing to-do list items

  ![Swagger documentation](./Swagger-Endpoints.png)

## Good Practices
- **Inversion of Control (IoC)**: Utilizes Spring Boot’s IoC to create loose coupling and enhance modularity.
- **Mocking**: Used in tests to simulate the behavior of complex objects.
- **Validation**: Ensures that only valid data is processed.
- **Custom Exceptions and Error Handling**: Provides clear and detailed error messages for easier debugging and user understanding.
- **Testing Frameworks**: Includes JUnit and Postman for comprehensive testing.
- **Detailed Error Handling**: Follows good API practices by providing detailed error messages and status codes.

## Installation

### Prerequisites
- Ensure you have Docker and Docker Compose installed.

### Steps
1. Clone the repository:
   ```sh
   git clone https://github.com/your-username/your-repo.git
   cd your-repo
