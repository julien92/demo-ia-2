# Order API

## Description
This is a Spring Boot application that provides REST API endpoints for managing orders. The Order API allows clients to retrieve and create orders through a RESTful interface.

## Features
- Retrieve order by ID
- Get default order (compatibility endpoint for legacy systems)
- Create new orders

## Technical Stack
- Java 21
- Spring Boot 3.2.6
- Maven
- Lombok

## Project Structure
The application follows standard Spring Boot project structure:
- `com.example.orderapi.OrderApiApplication`: Main application class
- `com.example.orderapi.OrderController`: REST controller handling HTTP requests
- `com.example.orderapi.OrderService`: Service layer handling business logic
- `com.example.orderapi.Order`: Model class representing order data

## API Endpoints

### Get Order by ID
```
GET /orders/{id}
```
Returns a specific order by its ID or 404 if not found.

### Get Default Order (Legacy Compatibility)
```
GET /orders
```
Returns a default order. This endpoint is designed for legacy systems that do not provide an order ID.

### Create Order
```
POST /orders
```
Creates a new order with the provided order data in the request body.

## Building and Running

### Prerequisites
- JDK 21
- Maven

### Build
```bash
mvn clean package
```

### Run
```bash
java -jar target/order-api-0.0.1-SNAPSHOT.jar
```
Or using Maven:
```bash
mvn spring-boot:run
```

## Development

### Testing
Run tests with:
```bash
mvn test
```

## License
Propretary - All Rights Reserved

## Contact
For any questions or support, please contact the development team.
