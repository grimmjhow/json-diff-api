# Json Diff API

### Running the application
- Run _**mvn clean install**_ to run all tests and generate jar file
- Run _**java -jar target/waes-0.0.1-SNAPSHOT.jar**_ to execute the application
- This program utilizes in memory database, witch will maintain all saved information while 
  the application still running.

### API endpoints
* GET - **_/v1/diff/{id}_**
    * return a diff report from the two json base64 encoded
    
* POST - **_/v1/{id}/diff/left_** - body (json base64 encoded like **eyJuYW1lIjogImZlbGlwZSBtb250ZWlybyJ9**)
    * save left side of the diff from a json base64 encoded

* POST - **_/v1/{id}/diff/right_** - body (json base64 encoded like **eyJuYW1lIjogImZlbGlwZSBtb250ZWlybyJ9**)
    * save right side of the diff from a json base64 encoded

### Libraries and frameworks
The following guides illustrate how to use some features concretely:

* Spring Boot 2.4.3
* Spring Boot Data
* Spring Boot Test
* H2 InMemory Database
* Jackson
* Mockito
* Junit 5
* Rest Assured
* lombok
* json-patch
* mapstruct