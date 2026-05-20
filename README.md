# Cloud Resource Cleanup Assistant

Backend service for identifying and managing unused cloud resources.

## Features

- Health monitoring endpoint
- REST API built with Spring Boot
- Clean layered architecture
- Ready for AWS/Azure integrations
- Docker-ready structure

## Tech Stack

- Java 21
- Spring Boot
- Maven
- REST API
- Git & GitHub

## Current Endpoints

### Health Check

```http
GET /api/health
```

Example response:

```json
{
  "status": "UP",
  "service": "cloud-resource-cleanup-assistant"
}
```

## Project Structure

```text
controller/  -> REST endpoints
service/     -> business logic
model/       -> DTOs and models
aws/         -> AWS integrations
config/      -> configuration classes
exception/   -> error handling
```

## Future Improvements

- AWS EC2 scanning
- Stopped resource detection
- Cost estimation
- Docker deployment
- Swagger/OpenAPI documentation
- Authentication & authorization
