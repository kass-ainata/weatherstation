Api Documentation
http://localhost:8080/swagger-ui.html

# Weather Station
> Java backend application that provides historic weather-related information
> 
## What is a Palindrome?
> "A palindrome is a word, number, phrase, or other sequence of characters which reads the same backward as forward", like "tattarrattat". The latter palindrome is the longest palindromic word in the Oxford English Dictionary. It is also used in our spring boot banner, visible when the application starts.

## Application Architecture Overview
![alt text](./documentation/Messages-App-Architecture-Overview.jpeg)

## Application Architecture Components
- Web Application Framework: Spring Boot v2.2.5
- Databases: PostgresSQL used as database for the application, Hsql is used for application tests as in-memory database
- Docker-compose: Orchestrates multiple docker containers for the purpose of local development.
    

## Application Design Consideration
- Spring Boot was chosen as it enables developers to build secure and production-ready web applications quickly. Its out-of-the-box configurations lets the developer focus on application design/business logic with less focus on boiler plate code. 
- Dockerized application
- Leveraged Spring Boot actuators like health, info, metrics, in order to provide deep insight of the application. 
- Liquibase: used for deploying and keeping track of database data and schema migrations/changes.
- Swagger UI: SwaggerUI used provide a UI to test the application's APIs
- OpenAPI: OpenAPI v3.0 specification is used to document and define the application's APIs


## Prerequisites
Before you can run this project, you must install and configure the following dependencies on your machine:
 -  [Docker Compose](https://docs.docker.com/compose/install/)


## Running Message application
Navigate to where the docker-compose.yml file is and run the application using the following command 
```
docker-compose up 
```
This will start three containers: "api" (spring boot service), "db", and "pgadmin". 

### API container
The "api" container will house the spring boot message service. It is available locally at:
```
http://localhost:8080
```

### DB container
The "db" container will house the postgres database which will be used to store the messages. The database will be available locally at:
```
jdbc:postgresql://localhost:5432/messages
```

### PGAdmin container
The "pgadmin" container will house a web-based database client which can be used to connect to the postgres database. It is pre-configured to connect to the "db" container. 

To use the web-based database client, navigate to:
```
http://localhost:8082
```
The credentials to login are:
```
email: "postgres"
password: "password"
```

Once in, click on the "Messages" database. This will prompt you for a password. Enter "password".

## Liquibase 
Is an open-source tool for managing and deploying database changes/migrations. It can be leveraged as a CLI client or an embedded library within the application. The Messages API service implements the latter approach: embedded library. 

Liquibase is executed every time the spring boot application is started. It deploys any changes found within the changelog files under "/db/changelog/.\*" to the database.

Liquibase will only deploy new changes that have not been deployed previously. This is possible because Liquibase identifies each changeset with a unique identifier comprised of: changeset author, changeset id, changelog filename. It then stores the identifier and other meta-data within a "databasechangelog" table under the public schema. This way, Liquibase can track all the changesets that have ever been executed by Liquibase, the order in which they were executed, and the execution result (success/fail). Using this information, Liquibase will avoid executing an already executed changeset. 

Within the Messages API Service, Liquibase is used to create the initial database schema, and load initial data (messages) into the database. It can also be used to make changes (migrate) to the database schema in the future, or initialize the database with a different set of data based on the environment that the application is deployed in (ex: prd vs stg vs dev vs local)

Note: For demo purposes, Liquibase will initialize database with 25 message records.


## Open-API and Swagger UI
API documentation can be found at:
```
http://localhost:8080/api-docs
```

Swagger UI is available at:
```
http://localhost:8080/swagger-ui.html
```

## Health, Info, Metrics Actuators
This application makes use of spring actuators. The base path is:
```
/api/manage/actuator/[actuator_name]
```
### Health
To retrieve the application's health/status locally, navigate to:
```
http://localhost:8080/api/manage/actuator/health
```
This endpoint can be used by kubernates (liveness/readiness probe) to detect application failure

### Info
To retrieve the deployed application information locally, navigate to:
```
http://localhost:8080/api/manage/actuator/info
```
The "info" endpoint will return the build information and git commitId/branch. This will be useful for developers who are working with multiple environments (dev, stg, uat, hotfix...) and wish to know what version of the code is deployed.

### Metrics
To retrieve the current metrics of the application, navigate to:
```
http://localhost:8080/api/manage/actuator/metrics
```
This will return a list of all components that can be queried for metrics. You can further query the "metrics" endpoint by the specifying the component as a path parameter. Ex:
```
http://localhost:8080/api/manage/actuator/metrics/jdbc.connections.active
```

## Requirements Document
The requirement document can be found under 
```
documentation/Cloud-Audition-Project_Nov18.pdf
```

# Error Handling
There are several errors that the API can generate. Some are the result of user input/misuse. Others are a result of failure on the API server. Here is a list of the error responses the API may return.

## Not Found
If the user requests a non-existent resource, the API will return the following message:

Request: GET http://localhost:8080/api/v1/messa

Response:
```
{
    "path": "/api/v1/messa",
    "error": "Not Found",
    "timestamp": "2020-03-18T18:44:52.632061",
    "status": 404
}
```

If the user requests message by non-existent Id

Request: GET http://localhost:8080/api/v1/messages/08bf715e-2063-4b21-abd3-02a546cb44db

Response:
```
{
  "timestamp": "2020-03-22T22:32:19.155646Z",
  "status": "NOT_FOUND",
  "code": "E2",
  "message": "Message not found with id (08bf715e-2063-4b21-abd3-02a546cb44db)"
}

```

## Input Invalidation Error
If the user's input fails validation, the API will return the following message:

Request: POST http://localhost:8080/api/v1/messages
```
{
  "content": ""
}

```

Response:
```
{
    "timestamp": "2020-03-18T22:47:34.121037Z",
    "status": "BAD_REQUEST",
    "code": "E1",
    "message": "Invalid request body",
    "subErrors": [
        {
            "field": "content",
            "message": "Min length is 1 character and max length is 2000",
            "value": ""
        }
    ]
}
```

If the user requests non-existing request parameter

Request: GET http://localhost:8080/api/v1/messages?pageNo=0&pageSize=25&sortBy=xyz

Response:
```
{
  "timestamp": "2020-03-22T19:30:45.593576Z",
  "status": "BAD_REQUEST",
  "code": "E2",
  "message": "No property xyz found for type Message!"
}
``` 

## Malformed Input/JSON Error
If the user POSTs unrecognized fields within the JSON payload, the API will return the following message:

Request: POST http://localhost:8080/api/v1/messages
```
{
  "contentMsg": "message"
}

```

Response:
```
{
  "timestamp": "2020-03-22T22:21:51.848660Z",
  "status": "BAD_REQUEST",
  "code": "E1",
  "message": "Malformed JSON request: Unrecognized field 'contentMsg'"
}
```


If the user POSTs an empty request body, the API will return the following message:

Request: POST http://localhost:8080/api/v1/messages 
```
{}
```
Response:
```
{
    "timestamp": "2020-03-18T23:11:40.092627Z",
    "status": "BAD_REQUEST",
    "code": "E1",
    "message": "Malformed JSON request: Message request must contain field 'content' and cannot be null or empty."
}
```


## Unknown API Failure
If the API service generates an unhandled/unknown error, the API will return the following message:
```
{
    "path": "/api/v1/messages/22e721cf-02a5-4a82-b3f4-aa6b48c98a77",
    "error": "Internal Server Error",
    "timestamp": "2020-03-18T18:48:57.373363",
    "status": 500
}
```

## Technical Debt
- Add more test coverage

## Future Enhancements
- Authentication/Authorization using Spring Security
- Integrate with continuous integration (CI) ex TeamCity
- Deploy to Cloud Provider ex AWS
