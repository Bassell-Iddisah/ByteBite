# ✅ 📋 ByteBites Microservices Checklist
## 1#️⃣ Infrastructure Flow

---

## Setup shared backbone services used by all microservices


 - #### Phase 1: Discovery Server (discovery-server)
Initialize Spring Boot project (Eureka Server, Spring Web)

Add @EnableEurekaServer

Setup application.yml with port and instance config

Test by launching and visiting Eureka dashboard

 - #### Phase 2: Config Server (config-server)
Initialize Spring Boot project (Config Server, Git config)

Create local Git repo for config files

Setup application.yml to point to Git config

Add sample config (e.g for auth-service) in Git repo

Test config server loads remote configs

- #### Phase 3: API Gateway (api-gateway)
Initialize Spring Boot project (Gateway, Eureka Client)

Setup route to test service (e.g. /auth/**)

Add JWT validation filter (placeholder)

Test request routing through gateway

## Authentication Flow (auth-service)
Handles login, registration, JWT creation, and user role logic

- #### Phase 1: Basic User Management
Initialize auth-service with Spring Security, JPA, Web, Config Client, Eureka Client

Create User entity, DTOs, repository

Add BCrypt password encoding and user registration logic

Test registration with Postman

- #### Phase 2: JWT Login & Token Issuance
Add JWT utility class (generate + validate tokens)

Setup login controller (authenticate & return token)

Add roles to JWT claims

Test login and inspect returned JWT in Postman

- #### Phase 3: Role Setup & Admin Seeding
Define roles: ROLE_CUSTOMER, ROLE_RESTAURANT_OWNER, ROLE_ADMIN

Preload an admin user with ROLE_ADMIN

Test RBAC using Postman

## Restaurant Service Flow (restaurant-service)
Manages restaurants and menus

- #### Phase 1: Basic CRUD
Create Restaurant and MenuItem entities

Implement repository, service, controller

Test unauthenticated access to GET endpoints

- #### Phase 2: Role-Based Security
Add @PreAuthorize checks for POST/PUT/DELETE

Extract user ID from JWT for ownership checks

Test CRUD for ROLE_RESTAURANT_OWNER only

- #### Phase 3: Connect to Gateway + Config
Consume config from config-server

Register with Eureka

Route via API Gateway

## Order Service Flow (order-service)
Handles food order creation and management

- #### Phase 1: Order Creation
Create Order entity with status enum

Add repository, service, controller

Use JWT to associate order with customer

- #### Phase 2: Secure Access
ROLE_CUSTOMER can create/view own orders

ROLE_RESTAURANT_OWNER can view orders for their restaurant

Add access checks

- #### Phase 3: Event Publishing
Publish OrderPlacedEvent to Kafka/RabbitMQ

Test event emitted after placing order

## Notification Flow (notification-service)
Listens to OrderPlacedEvent and sends a notification

- #### Phase 1: Event Listening
Setup Kafka or RabbitMQ dependency + config

Create listener method with @KafkaListener

Log simulated "notification sent" messages

- #### Phase 2: Integration
Connect to message broker

Test live event consumption

## Integration & Security Flow
Secure, unify and validate end-to-end functionality

- #### Phase 1: JWT Filter in Gateway
Create filter that validates JWT

Add user info (ID, roles) to headers

Test rejected/accepted requests

- #### Phase 2: Config Consistency
Ensure all services pull from config-server

Use placeholders for common props (DB, port)

- #### Phase 3: Final Wiring
Register all services in Eureka

Route all services via Gateway

Validate token flow: login → access protected endpoint

## Documentation & Deliverables
Prepare for final presentation & submission

- #### Phase 1: Swagger Docs
Add springdoc-openapi to all services

Configure @OpenAPIDefinition annotations

Test via /swagger-ui.html

- #### Phase 2: Diagrams
Create architecture diagram (services + Kafka + Gateway)

Create login & order flow sequence diagram (JWT + RBAC)

- #### Phase 3: README + Demo
Document setup instructions

Include JWT + Postman test steps

Record 10–15 min demo (Eureka, flow, RBAC, event)