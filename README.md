> # Weather Station
Java (11) application that provides historic weather-related information

# Weather Station Demo
Java application providing:
- REST APIs
- 2 pages that display the weather station information
- the data can be filtered based on a start/end date

## Application Architecture Components
- Web Application Framework: Spring Boot
- Spring MVC
- Database: HSQLDB (Hyper SQL Database) a relational database management system - In this demo it is being used in-memory


## Application Design Consideration
- Spring Boot was chosen as it enables developers to build secure and production-ready web applications quickly. Its out-of-the-box configurations lets the developer focus on application design/business logic with less focus on boiler plate code.
- Leveraged Spring Boot actuators like health, info, metrics, in order to provide deep insight of the application.
- Swagger UI: SwaggerUI used to provide a UI to test the application's APIs

## Running the application
To see the code and run the application:
- clone the code from: https://github.com/kass-ainata/weatherstation
- git checkout master
- Compile and tests: ./mvnw clean install
- Run the app:  java -jar target/bankcanada-0.0.1-SNAPSHOT.war
- Run the app:  ./mvnw spring-boot:run
- See the app in the browser: http://localhost:8080/

Swagger UI is available at:
```
http://localhost:8080/swagger-ui.html
```

## Requirements Document
The requirement document can be found under
```
/weatherstation/docs/Java Developer Take Home Quiz.pdf
```

## Technical Debt
- Add more test coverage
- Add pagination mechanism
- Load the file into the DB line by line

## Future Enhancements
- Authentication/Authorization using Spring Security
- integrate more production Database and store data on disk
- Dockerize the app/db
- Implement caching
- Integrate with continuous integration (CI) ex TeamCity
- Deploy to Cloud Provider (aws, azure, google cloud...)

## Technology / Framework
- Java 11
- Spring Boot 2.3.9
- Spring, Spring MVC 5.2.13
- Thymeleaf	3.0.
- Bootstrap 4.3.1
- JUnit 5.6.3
- Mockito 3.3.3
- Slf4j 1.1.1.0
- Maven 3.6.3
- Lombok 1.18.18
- Json Matcher - hamcrest 1.3
