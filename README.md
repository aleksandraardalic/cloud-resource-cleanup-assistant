# Cloud Resource Cleanup Assistant

Backend service for identifying and managing unused cloud resources.

## Features

- REST API for identifying stopped AWS EC2 instances
- Monthly cloud cost savings calculation
- Summary endpoint with aggregated savings statistics
- Swagger/OpenAPI documentation
- Clean layered Spring Boot architecture
- Mock cloud resource data for local development
- Ready for future AWS SDK integration

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
