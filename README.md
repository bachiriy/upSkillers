#  UpSkillers - REST API

## Project Overview
This project involves developing a REST API using Spring Boot to manage a professional training platform. The platform will allow for the management of trainees, trainers, training courses, and training sessions.

## Key Features
- **Trainee Management**: Create, update, delete, and view trainee information including name, email, level, training courses, and training classes.
- **Trainer Management**: Create, update, delete, and view trainer information including name, email, specialty, training courses, and training classes.
- **Training Course Management**: Create, update, delete, and view training course information including title, level, prerequisites, minimum and maximum capacity, start and end dates, assigned trainers, enrolled trainees, and status (planned, in progress, completed, canceled).
- **Training Class Management**: Create, update, delete, and view training class information including name and classroom number.

## Technologies and Concepts Used
- Spring Boot
- Spring Data JPA
- REST API design principles
- Java 8 features (Streams, Lambda expressions, Java Time API, Collections API)
- Dependency Injection and Inversion of Control
- Unit and integration testing (JUnit, Mockito)
- Error handling and exception management
- Logging (SLF4J)
- Database management (H2 for development, PostgreSQL for production)
- Build automation with Maven
- Version control with Git
- Project management with JIRA and Scrum methodology

## Getting Started

### Clone the Repository
```
git clone https://github.com/bachiriy/UpSkillers.git
```

### Configure the Application
1. Navigate to the project directory: `cd UpSkillers`
2. Open the `application.properties` file (or `application-dev.properties` and `application-prod.properties`) and configure the database connection settings.

### Run the Application
1. Build the application with Maven: `mvn clean package`
2. Run the application: `mvn spring-boot:run`

The application will be available at `http://localhost:8080`.

## Documentation
- The Swagger documentation for the API is available at `http://localhost:8080/swagger-ui`
- A Postman collection is provided in the project repository for testing the API endpoints.

## Contributing
If you would like to contribute to this project, please follow these steps:
1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Implement your changes and write corresponding tests.
4. Submit a pull request with a detailed description of your changes.

## License
This project is licensed under the [MIT License](LICENSE).
