# Price Service

This Spring Boot service provides an endpoint to retrieve the price of an article based on a given filter.
It utilizes H2 database for data storage and Swagger for API documentation.

The project is built using hexagonal architecture and an API-first approach.
It is dockerized and can be run as a container.

## Features

- RESTful API to get article prices
- H2 in-memory database
- Swagger UI for API documentation and testing
- Dockerized application

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven
- Docker (optional)

### Running the Application

1. Clone the repository
2. Navigate to the project directory
3. Run `mvn spring-boot:run`

The application will start on `http://localhost:8080`

### Accessing H2 Console

You can access the H2 database console at:

http://localhost:8080/h2console/

No credentials are required to access.

### API Documentation

Swagger UI is available at:

http://localhost:8080/swagger-ui/index.html

OpenAPI documentation can be found at:

http://localhost:8080/v3/api-docs

## Docker Support

### Building Docker Image

To build a Docker image of the application, run:

`
mvn spring-boot:build-image
`

### Running Docker Container

To run the Docker container (in detach mode), use:

`
docker run -p 8080:8080 -d images/price-service
`

This will start the container in detached mode and map port 8080 of the container to port 8080 on the host.

## Usage

Use the Swagger UI or send HTTP requests to the exposed endpoint using the curl command provided in the `postman_request file located in the root of the project.
