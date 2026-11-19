# Student Management System – Spring Boot CRUD API
Building a RESTful CRUD API with Spring Boot, Spring Data JPA and 
MySQL

## Technologies Used
- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- MySQL
- Maven
- Lombok
- Postman
- AI chatbot(chatGPT)

 ## AI-Assisted Learning
ChatGPT was used as an interactive learning assistant to explain concepts, guide implementation, clarify errors, and support troubleshooting. The laboratory conversation is included separately as part of the submission below.
[View chatGPT conversation]
(https://chatgpt.com/share/6afe9eee-2a28-83ea-8214-0cf31b45d448)


## Project Architecture
The application follows a layered architecture:

- **Model:** Defines the Student entity and its database mapping.
- **Repository:** Uses JpaRepository to perform database operations and it gives us the interface of what jpa does.
- **Service:** Handles application logic and coordinates database operations.
- **Controller:** Exposes REST API endpoints and handles HTTP requests.

## Database Configuration
- Database: `student_db`
- Database management system: MySQL
- Configuration file: `application.properties`

Database credentials are configured separately and are not included in the repository.

## Implemented CRUD Operations

| HTTP Method | Endpoint | Description |
|---|---|---|
| POST | `/api/students` | Create a student |
| GET | `/api/students` | Retrieve all students |
| GET | `/api/students/{id}` | Retrieve a student by ID |
| PUT | `/api/students/{id}` | Update a student |
| DELETE | `/api/students/{id}` | Delete a student |

### Student Attributes
- ID (Auto-generated)
- First Name
- Last Name
- Email
- Department

## Additional Feature
Added validation using email so that email is required and valididated.


## Troubleshooting
Common issues encountered during development may include:
- Database connection and authentication errors due to password mismatch.
- Errors are investigated using application logs and resolved by checking the relevant configuration and implementation.

## Learning Outcomes
Through this laboratory, I practiced:
- Understanding RESTful APIs and HTTP methods.
- Configuring a Spring Boot Maven project.
- Connecting Spring Boot to MySQL.
- Understanding JPA entities and Spring Data repositories.
- Implementing layered architecture.
- Using dependency injection.
- Testing REST endpoints using Postman.
- Managing project source code using Git and GitHub.

