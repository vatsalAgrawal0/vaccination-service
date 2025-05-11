# Vaccination Service – School Vaccination Portal

This Spring Boot service handles vaccination drive management and student vaccination records in the School Vaccination Portal system. It supports creation, update, and validation of drives and ensures that students are not vaccinated more than once for the same vaccine.

---

## Features

- Create and update vaccination drives
- Prevent overlapping or duplicate drives
- Enforce date validation (minimum 15 days in advance)
- Mark students as vaccinated in a specific drive
- Prevent duplicate vaccinations for the same student and drive
- JWT-secured endpoints
- Swagger API documentation

---

## Tech Stack

- Java 17
- Spring Boot
- Spring Security (JWT)
- Spring Data JPA + PostgreSQL
- Maven
- Docker
- Kubernetes (Minikube)

---

## API Overview

### Vaccination Drive Endpoints

| Method | Endpoint           | Description                                   |
|--------|--------------------|-----------------------------------------------|
| POST   | `/drives`          | Create a vaccination drive                    |
| PUT    | `/drives/{id}`     | Update an existing drive                      |
| GET    | `/drives`          | List all drives                               |
| GET    | `/drives/upcoming` | List upcoming drives (next 30 days)           |

### Vaccination Record Endpoint

| Method | Endpoint               | Description                                  |
|--------|------------------------|----------------------------------------------|
| POST   | `/students/vaccinate`  | Mark a student as vaccinated in a drive      |

**All endpoints (except login) require JWT authentication.**  
