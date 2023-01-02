The other states (State machine) for A given Employee are:
- ADDED
- IN-CHECK
- APPROVED
- ACTIVE


Our backend stack is:
- Java 11 
- Spring Framework 


To Execute the springboot application please run the following commands:

- docker build -f execute.Dockerfile -t springboot
- docker run -d -p 8080:8080 -t spring-boot 

open your browser on the home page of swagger :
http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config





