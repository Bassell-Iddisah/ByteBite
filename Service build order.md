# Service Build Order:

- discovery-server → Eureka setup

- config-server → External config working

- auth-service → Full JWT login/register + token validation

- api-gateway → Routes + JWT filter

- restaurant-service → Secured CRUD + ownership

- order-service → Secure ordering + status

- notification-service → Kafka/RabbitMQ event listener

- event-publishing → Hook order → restaurant + notification