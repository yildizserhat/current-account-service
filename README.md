
# Current Balance Service

 This service for “current account” of already existing customers.
 
 The API will expose an endpoint which accepts the user information (customerID, initialCredit).

• Once the endpoint is called, a new account will be opened connected to the user whose ID is customerID.

• Also, if initialCredit is not 0, a transaction will be sent to the new account.

• Another Endpoint will output the user information showing Name, Surname, balance, and transactions of the accounts.



## API Reference
Postman Collection provided in the project.

Database diagram also provided in the project


#### Documentation 

```http
  localhost:8080/documentation 
```



## How To Run Application

UI will be reached via this url. Also, REST APIs are provided. With provided Postman Collection, you can reach endpoints.

```http
  localhost:8080/v1/ui/customers
```
## Tech Stack

**Technologies:** Java 17, Spring Boot , Docker, Junit, Mockito,  Integration Test, h2 database.

