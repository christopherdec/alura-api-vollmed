# Vollmed API - Spring Boot course - Alura

## Description

This project was developed following along the Java and Spring Boot Formation course from Alura.
The course's idea is to develop an API for a medical clinic application using Java and Spring Boot.
Vollmed is name of the hypothetical clinic.

## Topics Covered

This project allowed me to review and learn several topics related to Spring Boot project development topics, such as:

### 1. Implementation of business rules using SOLID principles

- Instead of having long validation methods in the service classes, we separate each validation logic in a specific
  class called validator. This improves the code readability, flexibility and testing.

### 2. API documentation with Spring Doc

- Configuration of the API's Swagger/OpenAPI documentation page, including the authentication header and the indication
  of protected routes.

### 3. Automated tests of Spring components

- Unity tests for the controllers, using `MockMvc` to fake requests and mock the calls to services;
- Unity tests for repository methods, and how to configure the framework to use a test database.

### 4. Spring profiles

- Use of different Spring execution profiles, e.g., for production, testing and development.

### 5. Database migration

- Used Flyway migrations to store an evolution history of the database.

### 6. Data validation

- Used Bean Validation for validating data received by the controllers;

### 7. Authenthication and authorization using JWTs

- Generation and validation of JWTs using the Auth0 library;
- Configured Spring Security for JWT usage, by creating a custom filter for intercepting requests, retrieve the token
  the inject the user.

### 8. Error handling

- Added controller advice classes to handle errors and return the appropriate http code and simple error description.

## System overview

The system is very simple. The main entity is the `Appointment`, which relates a `Client` with a `Professional` at a
specified
date.
Each client has basic information fields, such as name, address and phone, and so does each professional. The
implemented business logics are
described in the project's [Trello board](https://trello.com/b/O0lGCsKb/api-voll-med).
