# Cloud Resource Cleanup Assistant

Backend service for identifying and managing unused cloud resources.

## Features

- REST API for identifying stopped AWS EC2 instances
- Monthly cloud cost savings calculation
- Summary endpoint with aggregated savings statistics
- Swagger/OpenAPI documentation
- Clean layered Spring Boot architecture
- Automated unit and controller testing
- Mock cloud resource data for local development
- Externalized application configuration
- Port/adapter architecture for cloud integrations
- Environment-specific Spring profiles (`dev` / `prod`)
- Structured application logging
- Environment-aware health monitoring endpoint
- Structured health response with profile and version metadata 
- Automated CI pipeline with GitHub Actions
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

## Testing

Run all tests:

```bash
./mvnw test
```

## Running with Docker

Build and start the application:

```bash
docker compose up --build
```

## Continuous Integration

The project uses GitHub Actions for automated build and test execution.

Pipeline includes:

- Maven build validation
- Automated unit and controller tests
- Java 21 environment setup


## Swagger UI:

```text
http://localhost:8080/swagger-ui/index.html
```

## Tech Stack

- Java 21
- Spring Boot
- Maven
- REST API
- Git & GitHub
- JUnit 5
- MockMvc

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
model/       -> DTOs and response models
aws/         -> cloud provider adapters and ports
config/      -> application configuration
exception/   -> global exception handling
```

## Configuration

Application configuration is externalized through `application.properties`.

Example:

```properties
aws.supported-regions=eu-central-1,eu-west-1
```

This allows different environments (development, testing, production)
to use different cloud configurations without changing application code.

Profiles are used to separate development and production configurations.

Example:

```properties
spring.profiles.active=dev
```

## Architecture

The project follows a clean layered architecture with a port/adapter approach
for cloud integrations.

Current implementation uses:

- `Ec2ClientPort` as the abstraction layer
- `MockEc2Client` as the current adapter implementation

This architecture allows easy replacement of mock integrations
with real AWS SDK implementations in the future.

## Future Improvements

- AWS EC2 scanning
- Stopped resource detection
- Cost estimation
- Authentication & authorization
- Real AWS SDK integration
- Multi-cloud support (AWS/Azure/GCP)
- Authentication & authorization
- Persistent database integration
- Scheduled cleanup jobs
- Cloud cost analytics dashboard
