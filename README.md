# Actor Management System

A simple RESTful web application developed using Spring Boot and MySQL to manage actors and their associated movies.

## Project Overview

The project demonstrates how a Java backend application communicates with a relational database through REST APIs.

It follows a layered architecture, separating responsibilities into different components:

- **Entity:** Represents the Actor data model and maps Java objects to database tables.
- **Repository:** Uses Spring Data JPA to perform database operations without manually writing SQL queries for basic CRUD operations.
- **Service:** Handles application logic and coordinates data operations.
- **Controller:** Exposes REST endpoints and handles HTTP requests and responses.

Hibernate acts as the ORM (Object-Relational Mapping) framework, translating Java entity operations into SQL and managing database table creation and updates.

## Technologies
- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate ORM
- MySQL
- Maven

## Features
- Create actor records with movie information.
- Retrieve all actors or an individual actor.
- Update existing actor information.
- Delete actor records.

## Database Configuration

The application uses a MySQL database named `actordb`.

Configure the following environment variables:

```text
DB_USERNAME=your_mysql_username
DB_PASSWORD=your_mysql_password
```

## Running the Application

1. Clone the repository.
2. Configure your MySQL credentials.
3. Open the project in IntelliJ IDEA.
4. Run `ActorManagementApplication.java`.


