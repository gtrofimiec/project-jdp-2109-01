#Example requests

##USERS

- POST http://localhost:8080/v1/ecommerce/users

    - Method is adding new user record. 
    - Requires JSON. 
    - Returns saved user in JSON.
```
{
    "id" : 6,
    "firstname" : "Jane",
    "surname" : "Nowak",
    "accessKey" : null,
    "expirationTime" : null
}

{
    "id" : 8,
    "firstname" : "John",
    "surname" : "Kowalski",
    "accessKey" : null,
    "expirationTime" : null
}
```
- GET http://localhost:8080/v1/ecommerce/users

    - Method is returning all available users in JSON.
    - Doesn't require any parameters.

- GET http://localhost:8080/v1/ecommerce/users/6

    - Method is returning chosen user in JSON.
    - Requires the path variable (id of a user).

- PUT http://localhost:8080/v1/ecommerce/users/6

Method is updating chosen user - requires the path variable(id of a user), and JSON value of the updated user.
```
{
    "id" : 6,
    "firstname" : "Jane",
    "surname" : "Nowak",
    "accessKey" : null,
    "expirationTime" : null
}
```
- DELETE http://localhost:8080/v1/ecommerce/users/6

    - Method is deleting chosen user.
    - Requires the path variable (id of a user). 
    - Doesn't return anything.
