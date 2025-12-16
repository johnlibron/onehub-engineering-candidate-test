# OneHub Engineering
## Engineering Candidate Test for OneHub

## Overview

This repository contains a **Spring Boot REST API** implemented as part of the  
**OneHub Engineering – Engineering Candidate Test**.

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