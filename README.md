# OneHub Engineering
## Engineering Candidate Test for OneHub

## Overview

This repository contains a **Spring Boot REST API** implemented as part of the  
**OneHub Engineering – Engineering Candidate Test**.

The aim of this exercise is to demonstrate how a simple user story can be translated into a **clear, maintainable, and well-structured RESTful API**, with a strong focus on validation, correctness, and developer experience.

## Approach

I approached this exercise by focusing on clarity, simplicity, and clean separation of concerns.

The API is designed to be easy for frontend engineers to consume, with:
- Clear request/response contracts using DTOs
- Strong validation at the API boundary
- Centralized error handling for consistent responses

The implementation intentionally avoids overengineering and instead focuses on delivering the required functionality in a clean and extensible way within the given time constraints.

## Key Design Decisions

- **Layered architecture**  
  Controllers handle HTTP concerns, services encapsulate business logic, and repositories abstract persistence.

- **DTO-based API contracts**  
  Request and response DTOs are used to avoid exposing persistence entities directly and to allow the API to evolve independently of the data model.

- **Validation using Jakarta Bean Validation**  
  Field-level validation (e.g. required fields, email format, date of birth in the past) is handled declaratively using annotations.

- **Centralized error handling**  
  A global exception handler is used to ensure consistent and meaningful error responses (e.g. `400 Bad Request`, `409 Conflict`).

- **Generated employee number**  
  Employee numbers are generated server-side to guarantee uniqueness and avoid client responsibility.

- **In-memory persistence**  
  An H2 database is used for simplicity and ease of local execution, as data storage strategy was not a key requirement.

## Acceptance Criteria

The system must store the following employee details:

- Employee Number (generated, unique)
- Title
- First Name
- Surname
- Date of Birth
- Gender
- Email (unique)
- Address

## Tech Stack

- **Java 17**
- **Spring Boot 3**
- **Spring Web**
- **Spring Data JPA**
- **Jakarta Validation**
- **H2 In-Memory Database**
- **JUnit 5 & Mockito**
- **Maven**

## API Endpoints

### Create Employee
POST /api/employees

#### Request Body
```json
{
  "title": "Mr",
  "firstname": "John",
  "surname": "Doe",
  "dob": "1990-01-01",
  "gender": "Male",
  "email": "john.doe@company.com",
  "address": "123 High Street"
}
```

#### Response
```json
{
  "id": "1",
  "employeeNo": "EMP-1234",
  "title": "Mr",
  "firstname": "John",
  "surname": "Doe",
  "dob": "1990-01-01",
  "gender": "Male",
  "email": "john.doe@company.com",
  "address": "123 High Street"
}
```
Behaviour
- Generates a unique employee number
- Validates all fields
- Rejects duplicate email addresses
- Returns HTTP 201 Created

### Get Employees
GET /api/employees

#### Response
```json
[
  {
    "employeeNo": "EMP-1234",
    "title": "Mr",
    "firstname": "John",
    "surname": "Doe",
    "dob": "1990-01-01",
    "gender": "Male",
    "email": "john.doe@company.com",
    "address": "123 High Street"
  }
]
```

## Validation & Error Handling

- Validation errors return 400 Bad Request
- Duplicate email returns 409 Conflict

## Running the Application

### Start the application

```bash
./mvnw spring-boot:run
```

The application will start on:
```
http://localhost:8080
```

## Testing

- Service layer tested using Mockito
- Controller behaviour validated using MockMvc
- Tests focus on business rules and API behaviour

Run tests with:
```bash
./mvnw test
```