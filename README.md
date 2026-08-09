# Physcolog Backend

A RESTful backend application for a psychologist management platform, built with **Java and Spring Boot**.

The project provides a structured backend architecture with authentication, authorization, REST APIs, persistence, validation, and PostgreSQL integration.

## Features

- RESTful API architecture
- JWT-based authentication
- Spring Security integration
- Role-based security
- Spring Data JPA
- PostgreSQL database integration
- DTO-based API design
- Bean Validation
- Service and repository layers
- Global configuration and security components
- Actuator support
- Image processing support
- Unit and application test structure

## Tech Stack

- **Java 17**
- **Spring Boot 3.2.5**
- **Spring Web**
- **Spring Security**
- **Spring Data JPA**
- **PostgreSQL**
- **JWT**
- **Bean Validation**
- **Lombok**
- **Maven**
- **Spring Boot Actuator**
- **Thumbnailator**

## Architecture

The application follows a layered backend architecture:

```text
src/main/java/com/physcolog/
│
├── config/
├── controllers/
├── dto/
├── entities/
├── enums/
├── helper/
├── repository/
├── security/
│   └── service/
├── services/
└── PhyscologApplication.java
