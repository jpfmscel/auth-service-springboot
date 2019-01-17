# Auth service

This application is a standalone auth service for prototyping applications.  
It already carries a mongoDB image in the docker-compose.yml file. (Docker must be installed on the running machine).

## To run it

1) Set you hashing secret key in the `application.properties` file (to be changed in the future).

2.1)
#####  If you are using Maven, you can run the application using  
 ` $ sudo docker-compose up & ./mvnw spring-boot:run ` 
 
 
2.2)
##### Or you can build the JAR file with   
` $ ./mvnw clean package` 

Then you can run the JAR file:

` $ sudo docker-compose up & java -jar target/auth-service-0.0.1-SNAPSHOT.jar`



#### Swagger    

Swagger documentation is available at : `http://localhost:8080/swagger-ui.html`