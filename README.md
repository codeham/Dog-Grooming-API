# Dog RESTFUL API

  A simple extensible dog API
  
  Not much to it really... feed the API, fetch the output :smiley:

  **Project Layers** 
  **(Spring Web / Hibernate / Spring Data JPA / H2)**
  
  * REST controller that exposes CRUD operations
  * Request input validation
  * HTTP status response handling
  * Exception handling
  * Embedded H2 Database
  * Hibernate JPA
  * API testing (Mockito)
  * Unit testing (Junit)
  
  **Must have Installed to Run**
  - Java 11
  - Gradle


## Installation ##
```
git clone https://github.com/codeham/Dog-REST-API.git
```

## API Testing ##
Sample POST Request (.../api/v1/dogs)

**Request Body**
```
    {
        "name": "Billy",
        "breed": "German Shephered",
        "age": 3,
        "weight": 90.0,
        "color": "black",
        "coatLength": "short",
        "houseTrained": true,
        "vaccinationsReady": true
    }
```
Sample GET Request (.../api/v1/dogs)

**Response Body**
```
[
    {
        "id": 1,
        "name": "Billy",
        "breed": "German Shephered",
        "age": 3,
        "weight": 90.0,
        "color": "black",
        "coatLength": "short",
        "houseTrained": true,
        "vaccinationsReady": true
    }
]
```
