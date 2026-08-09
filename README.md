# Physcolog Backend

A production-oriented backend application developed for a professional psychology platform using **Java and Spring Boot**.

The project follows a layered architecture and demonstrates practical enterprise backend development with REST APIs, Spring Security, JWT authentication, JPA/Hibernate, DTOs, repositories, services, validation, and PostgreSQL.

## Project Preview

### Application Architecture

![Application Architecture](docs/screenshots/architecture.png)

## Authentication

The API is secured using **Spring Security and JWT (JSON Web Token)** authentication.

The authentication flow is based on:

1. User authentication
2. JWT token generation
3. Token-based request authentication
4. Role-based authorization

<<<<<<< HEAD
=======
### Login

```http
POST /auth/login
```

Request body:

```json
{
  "userName": "example",
  "password": "password"
}
```

Successful response:

```json
{
  "token": "JWT_TOKEN"
}
```

Invalid credentials return:

```text
HTTP 401 Unauthorized
```

```json
{
  "error": "Geçersiz kullanıcı adı veya şifre"
}
```

>>>>>>> 0885fd1 (docs: improve backend documentation)
Protected endpoints require a valid JWT token in the request header:

```http
Authorization: Bearer <JWT_TOKEN>
```

## Backend Technologies

- Java
- Spring Boot
- Spring Security
- JWT
- JPA / Hibernate
- PostgreSQL
- Maven
- REST API

## Features

- RESTful API architecture
- JWT-based authentication
- Spring Security
- Role-based authorization
- DTO-based API design
- JPA / Hibernate
- PostgreSQL
- Layered architecture
- Service and repository patterns
- Entity / DTO separation
- Request validation
- Global configuration
- Exception handling
- Helper and utility components
- Multipart file upload support
- Pagination with Spring Data `Pageable`
- Maven-based project structure

## Architecture

The application follows a layered backend architecture:

```text
src/main/java/com/physcolog
│
├── config
├── controllers
├── dto
├── entities
├── enums
├── helper
├── repository
├── security.service
├── services
│
└── PhyscologApplication
```
<<<<<<< HEAD
=======

## API Documentation

The backend exposes RESTful APIs for authentication, content management, clinic information, education, courses, appointments, contact forms, documents, sliders, and videos.

### Base URL

```text
http://localhost:8080
```

### Authentication API

| Method | Endpoint | Description |
|---|---|---|
| POST | `/auth/login` | Authenticate user and generate JWT token |

### About API

| Method | Endpoint | Description |
|---|---|---|
| GET | `/about` | Get the latest About record |
| GET | `/about/{aboutId}` | Get an About record by ID |
| GET | `/about/all` | Get all About records |
| POST | `/about` | Create a new About record |
| PUT | `/about/{aboutId}` | Update an About record |
| DELETE | `/about/{aboutId}` | Delete an About record |

`POST` and `PUT` use `multipart/form-data` with:

```text
namesurname
jobtitle
photo
details
```

### Academic API

| Method | Endpoint | Description |
|---|---|---|
| POST | `/academic` | Create an academic record |
| GET | `/academic/all` | Get all academic records |
| GET | `/academic/{academicId}` | Get an academic record by ID |
| PUT | `/academic/{academicId}` | Update an academic record |
| DELETE | `/academic/{academicId}` | Delete an academic record |

### Clinics API

| Method | Endpoint | Description |
|---|---|---|
| POST | `/clinics` | Create a clinic |
| PUT | `/clinics/{clinicId}` | Update a clinic |
| GET | `/clinics/all` | Get all clinics |
| GET | `/clinics/{clinicId}` | Get a clinic by ID |
| GET | `/clinics/latest-six` | Get the latest six clinics |
| GET | `/clinics/all-clinics` | Get all clinics |
| DELETE | `/clinics/{clinicId}` | Delete a clinic |

### Contact API

| Method | Endpoint | Description |
|---|---|---|
| POST | `/contact` | Create contact information |
| GET | `/contact/all` | Get all contact records |
| GET | `/contact/{contactId}` | Get a contact record by ID |
| GET | `/contact/latest-one` | Get the latest contact record |
| PUT | `/contact/{contactId}` | Update contact information |
| DELETE | `/contact/{contactId}` | Delete a contact record |

### Contact Form API

| Method | Endpoint | Description |
|---|---|---|
| POST | `/contact-form` | Create a contact form |
| GET | `/contact-form/all` | Get all contact forms with pagination |
| GET | `/contact-form/{contactFormId}` | Get a contact form by ID |
| GET | `/contact-form/isApproved` | Get approved contact forms |
| PUT | `/contact-form/isApproved/{contactFormId}` | Approve a contact form |
| PUT | `/contact-form/{contactFormId}` | Update a contact form |
| DELETE | `/contact-form/{contactFormId}` | Delete a contact form |

The contact form endpoints support Spring Data pagination through `Pageable`.

Example:

```text
GET /contact-form/all?page=0&size=10
```

### Courses API

| Method | Endpoint | Description |
|---|---|---|
| POST | `/courses` | Create a course |
| GET | `/courses/all` | Get all courses |
| GET | `/courses/{coursesId}` | Get a course by ID |
| PUT | `/courses/{coursesId}` | Update a course |
| DELETE | `/courses/{coursesId}` | Delete a course |

### Documents API

| Method | Endpoint | Description |
|---|---|---|
| POST | `/documents` | Create a document |
| GET | `/documents/all` | Get all documents |
| GET | `/documents/{documentsId}` | Get a document by ID |
| GET | `/documents/latest-one` | Get the latest document |
| PUT | `/documents/{documentsId}` | Update a document |
| DELETE | `/documents/{documentsId}` | Delete a document |

### Education API

| Method | Endpoint | Description |
|---|---|---|
| POST | `/education` | Create an education record |
| GET | `/education/all` | Get all education records |
| GET | `/education/{educationId}` | Get an education record by ID |
| PUT | `/education/{educationId}` | Update an education record |
| DELETE | `/education/{educationId}` | Delete an education record |

### Login Page

| Method | Endpoint | Description |
|---|---|---|
| GET | `/login` | Return the login page |

### Appointment API

| Method | Endpoint | Description |
|---|---|---|
| POST | `/randevu` | Create an appointment form |
| GET | `/randevu/all` | Get all appointment forms |
| GET | `/randevu/{randevuId}` | Get an appointment form by ID |
| PUT | `/randevu/{randevuId}` | Update an appointment form |
| DELETE | `/randevu/{randevuId}` | Delete an appointment form |

### Slider API

| Method | Endpoint | Description |
|---|---|---|
| POST | `/slider` | Create a slider with image upload |
| GET | `/slider` | Get all sliders |
| GET | `/slider/{sliderId}` | Get a slider by ID |
| PUT | `/slider/{sliderId}` | Update a slider with image upload |
| DELETE | `/slider/{sliderId}` | Delete a slider |

`POST` and `PUT` use `multipart/form-data` with:

```text
title
description
photo
```

### Videos API

| Method | Endpoint | Description |
|---|---|---|
| POST | `/videos` | Create a video |
| GET | `/videos` | Get all videos |
| GET | `/videos/{videoId}` | Get a video by ID |
| GET | `/videos/latest-four` | Get the latest four videos |
| GET | `/videos/category/{videoCategory}` | Get videos by category |
| PUT | `/videos/{videoId}` | Update a video |
| DELETE | `/videos/{videoId}` | Delete a video |

## API Summary

The application exposes REST endpoints covering:

- Authentication
- About management
- Academic records
- Clinic management
- Contact information
- Contact forms
- Courses
- Documents
- Education
- Appointment forms
- Slider management
- Video management

The API demonstrates:

- RESTful resource design
- CRUD operations
- JWT authentication
- Spring Security
- Role-based authorization
- DTO-based request handling
- Multipart file uploads
- Request validation
- Pagination with Spring Data `Pageable`
- JPA / Hibernate persistence
- PostgreSQL integration
- Layered service architecture

## Getting Started

### Prerequisites

- Java 17+
- Maven
- PostgreSQL

### Clone the Repository

```bash
git clone https://github.com/blgncan/PhyscologBackend.git
cd PhyscologBackend
```

### Configuration

Configure the PostgreSQL database and application properties according to your local environment.

The application uses Spring Boot configuration for database connectivity and application settings.

### Run the Application

Using Maven:

```bash
./mvnw spring-boot:run
```

Or on Windows:

```bash
mvnw.cmd spring-boot:run
```

The application will start on:

```text
http://localhost:8080
```

## Project Structure

```text
src/
└── main/
    ├── java/
    │   └── com/
    │       └── physcolog/
    │           ├── config/
    │           ├── controllers/
    │           ├── dto/
    │           ├── entities/
    │           ├── enums/
    │           ├── helper/
    │           ├── repository/
    │           ├── security/
    │           ├── services/
    │           └── PhyscologApplication.java
    │
    └── resources/
        └── application.properties
```

## Development Approach

The project is structured around a layered architecture separating responsibilities between controllers, services, repositories, DTOs, entities, configuration, and security components.

This separation improves maintainability, testability, and scalability while keeping the application structure aligned with common enterprise Spring Boot development practices.

## Purpose

This project was developed as a real-world backend application for a professional psychology platform.

It demonstrates practical experience with:

- Enterprise Java development
- Spring Boot application architecture
- REST API development
- Authentication and authorization
- Database-driven applications
- File upload handling
- DTO-based API design
- PostgreSQL persistence
- Layered architecture

## Author

**Bilgin Can**

- GitHub: https://github.com/blgncan
- LinkedIn: https://linkedin.com/in/bilgincann
>>>>>>> 0885fd1 (docs: improve backend documentation)
