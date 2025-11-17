# FX Deals Management System

A Spring Boot REST API for managing Foreign Exchange (FX) deals with validation, duplicate prevention, and persistence.

##  Features

- **Create FX Deals** - Accept and validate deal details
- **Duplicate Prevention** - Ensure unique deal IDs
- **Data Validation** - Comprehensive input validation
- **RESTful API** - Clean and structured endpoints
- **Database Persistence** - MySQL integration
- **Error Handling** - Proper exception management
- **Logging** - Comprehensive logging system
- **Unit Testing** - Test coverage for critical components

## 🛠 Technologies

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

### 1. Database Setup
```sql
CREATE DATABASE fx_deals;
