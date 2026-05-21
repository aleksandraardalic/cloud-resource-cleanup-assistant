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

## API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/health` | Application health check |
| GET | `/api/aws/ec2/stopped` | List stopped EC2 instances |
| GET | `/api/aws/ec2/stopped?region=eu-central-1` | Filter stopped instances by AWS region |
| GET | `/api/aws/ec2/stopped/summary` | Get summary with estimated savings |

---

## Validation & Error Handling

The API includes global exception handling and validation for unsupported AWS regions.

Example error response:

```json
{
  "timestamp": "2026-05-21T15:01:02",
  "status": 400,
  "error": "Invalid AWS region: eu-central-12"
}
```
## Running with Docker

Build and start the application:

```bash
docker compose up --build
```

Swagger UI:

```text
http://localhost:8080/swagger-ui/index.html
```

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
