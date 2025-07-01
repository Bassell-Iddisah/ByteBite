# ByteBites - Restaurant Ordering Platform

## A platform connecting customers with local restaurants*

## Table of Contents
- Overview
- Architecture
- Services
- Getting Started
- Configuration
- Security
- Event-Driven Communication
- API Documentation
- Deployment
- Monitoring & Logging
- Contributing
- License

---

## Overview
ByteBites is a microservices-based platform that connects customers with local restaurants. The system features:
- JWT-based authentication with centralized auth service
- Role-Based Access Control (RBAC)
- Event-driven architecture using Kafka/RabbitMQ
- Scalable, independently deployable services

---

## Architecture

### Tech Stack
- Java 17 with Spring Boot 3.x
- Spring Cloud for service discovery & config
- Spring Security with JWT
- Kafka/RabbitMQ for event streaming
- PostgreSQL for relational data
- MongoDB for document storage (where applicable)
- Docker & Kubernetes for containerization

### Service Discovery & Config
- Spring Cloud Config Server for centralized configuration
- Eureka Server for service discovery

---

## Services

### Core Services
| Service | Description | Port Range |
|---------|------------|------------|
| auth-service | Handles authentication & JWT generation | 8000-8009 |
| restaurant-service | Manages restaurant data & menus | 8010-8019 |
| order-service | Processes customer orders | 8020-8029 |
| notification-service | Handles email/SMS notifications | 8030-8039 |
| gateway-service | API Gateway (Spring Cloud Gateway) | 8040-8049 |

### Supporting Services
| Service | Description | Port Range |
|---------|------------|------------|
| config-server | Centralized configuration | 8888 |
| discovery-server | Eureka service registry | 8761 |
| kafka/zookeeper | Event streaming | 9092/2181 |

---

## Getting Started

### Prerequisites
- Java 17 JDK
- Docker & Docker Compose
- Maven 3.8+
- Kafka/RabbitMQ (or use provided Docker setup)

### Local Development
1. Clone the repository:
   git clone https://github.com/yourorg/bytebites.git
   cd bytebites

2. Start infrastructure services:
   docker-compose -f docker-compose-infra.yml up -d

3. Build and run services:
   mvn clean install
   # Start config server and discovery first
   # Then start other services in any order

---

## Configuration
All configurations are managed via the config server. Environment-specific files are located in:
config-repo/
├── application.yml          # Base config
├── auth-service.yml
├── restaurant-service.yml
├── order-service.yml
└── notification-service.yml

Override local properties in application-local.yml per service.

---

## Security

### RBAC Model
| Role | Permissions |
|------|-------------|
| ROLE_CUSTOMER | Browse restaurants, place orders, view order history |
| ROLE_RESTAURANT_OWNER | Manage restaurant info, update menus, view orders |
| ROLE_ADMIN | Full system access, user management |

### JWT Flow
1. Client authenticates with auth-service
2. Receives JWT with roles/authorities
3. Subsequent requests include JWT in Authorization header
4. Gateway validates token with auth-service

---

## Event-Driven Communication

### Key Events
| Event | Produced By | Consumed By |
|-------|-------------|-------------|
| OrderCreated | order-service | notification-service, restaurant-service |
| OrderStatusUpdated | order-service | notification-service |
| RestaurantMenuUpdated | restaurant-service | (future services) |

### Sample Consumer
@KafkaListener(topics = "order-created")
public void handleOrderCreated(OrderCreatedEvent event) {
// Process event
}

---

## API Documentation
Each service exposes Swagger UI at:
http://localhost:{port}/swagger-ui.html

Postman collection available in /docs directory.

---

## Deployment

### Docker
docker-compose -f docker-compose.yml up -d --build

### Kubernetes
kubectl apply -f k8s/

### CI/CD
GitHub Actions workflows provided for:
- Build & test on PR
- Docker image build on main
- Helm chart deployment to staging/prod

---

## Monitoring & Logging

### Health Checks
All services expose actuator endpoints:
/actuator/health
/actuator/metrics
/actuator/prometheus

### Centralized Logging
- ELK stack (Elasticsearch, Logstash, Kibana)
- Logs shipped via Filebeat

### Monitoring
- Prometheus + Grafana dashboards
- AlertManager for critical issues

---

## Contributing
1. Fork the repository
2. Create your feature branch (git checkout -b feature/foo)
3. Commit your changes (git commit -am 'Add some foo')
4. Push to the branch (git push origin feature/foo)
5. Create a new Pull Request

---

## License
Distributed under the MIT License. See LICENSE for more information.