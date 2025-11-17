# FX Deals Management System

A Spring Boot REST API for managing Foreign Exchange (FX) deals with validation, duplicate prevention, and persistence. Developed for ProgressSoft Corporation technical assignment.

##  Features

- **Create FX Deals** - Accept and validate deal details
- **Duplicate Prevention** - Ensure unique deal IDs
- **Data Validation** - Comprehensive input validation
- **RESTful API** - Clean and structured endpoints
- **Database Persistence** - MySQL integration
- **Error Handling** - Proper exception management
- **Logging** - Comprehensive logging system
- **Unit Testing** - Test coverage for critical components

##  Technologies

- **Java 21** - Programming language
- **Spring Boot 3.5.7** - Application framework
- **MySQL** - Database
- **Maven** - Dependency management
- **JUnit 5 & Mockito** - Testing
- **Lombok** - Code reduction
- **Spring Validation** - Data validation

##  Prerequisites

- Java 21 or higher
- Maven 3.6+
- MySQL 5.5+ (or MySQL 8.0+ recommended)

##  Quick Start

## 1. Database Setup
```sql
CREATE DATABASE fx_deals;
```

## 2. Configure Application
```
Update src/main/resources/application.properties with your MySQL credentials:
propertiesspring.datasource.url=jdbc:mysql://localhost:3306/fx_deals
spring.datasource.username=root
spring.datasource.password=your_password
3. Run the Application
bashmvn spring-boot:run
```
## 4. Access the API
```
Application will start on: http://localhost:8081
📡 API Documentation
Create a Deal
Endpoint: POST /api/deals
Request Example:
json{
    "dealUniqueId": "DEAL-001",
    "fromCurrency": "USD",
    "toCurrency": "EUR",
    "dealTimestamp": "2025-11-16T20:00:00",
    "dealAmount": 1000.50
}
Success Response (200):
json{
    "dealUniqueId": "DEAL-001",
    "fromCurrency": "USD",
    "toCurrency": "EUR",
    "dealTimestamp": "2025-11-16T20:00:00",
    "dealAmount": 1000.50,
    "status": "SUCCESS",
    "message": "Deal created successfully"
}
Error Response (200 with error status):
json{
    "status": "ERROR",
    "message": "Deal already exists"
}
```
## Validation Rules
```
dealUniqueId: Required, 3-50 characters
fromCurrency/toCurrency: Required, exactly 3 uppercase letters
dealTimestamp: Required, cannot be in future
dealAmount: Required, positive number, minimum 0.01
```
## 🧪 Testing

 ```
Run All Tests
bashmvn test
Run Specific Test Class
bashmvn test -Dtest=DealControllerTest
mvn test -Dtest=DealServiceImplTest
```

### Test Coverage

* Service Layer: 80%+ coverage
* Controller Layer: 70%+ coverage
* Validation: Comprehensive test cases

## 📁 Project Structure
```
src/
├── main/
│   ├── java/com/progresssoft/fx_deal_salah/
│   │   ├── controller/          # REST controllers
│   │   ├── service/             # Business logic
│   │   │   ├── contracts/       # Service interfaces
│   │   │   └── implementations/ # Service implementations
│   │   ├── repository/          # Data access layer
│   │   ├── entity/              # JPA entities
│   │   ├── dto/                 # Data transfer objects
│   │   └── exception/           # Custom exceptions
│   └── resources/
│       └── application.properties
└── test/
    └── java/com/progresssoft/fx_deal_salah/
        ├── controller/          # Controller tests
        └── service/             # Service tests
```

## 🔍 Logging

The application uses SLF4J with Logback for comprehensive logging:

* **INFO Level**: Application events, successful operations
* **WARN Level**: Business rule violations (duplicate deals)
* **ERROR Level**: System errors and exceptions

Logs are output to console with timestamps and log levels.

## 🏢 Architecture Context

This application serves as the data ingestion layer for a larger Clustered Data Warehouse system at Bloomberg.

### System Flow:
```
Data Sources → This Application → Clustered Data Warehouse → Analytics
     ↑                ↑                    ↑                     ↑
  FX Deals      Validation &          Storage &           Reports &
              Deduplication          Processing           Dashboards
Role in Larger System:

Data Gateway: Receives and validates incoming FX deal data
Quality Control: Ensures clean, duplicate-free data enters the warehouse
Continuous Ingestion: "No rollback" policy ensures all valid data persists for analysis
Scalability Foundation: Designed to feed into a clustered environment handling high-volume financial data
```
## Docker (Optional)
Docker configuration is available for containerized deployment. Contact for Docker setup instructions.
## Development
Code Style

Follows Spring Boot best practices
Clean architecture with separation of concerns
Proper error handling and validation
Comprehensive logging

Key Design Patterns

Repository Pattern
Service Layer Pattern
DTO Pattern
MVC Pattern
