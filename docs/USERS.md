# Example requests

## USERS

- POST http://localhost:8080/v1/ecommerce/users

    - Request is adding new user record. 
    - Requires JSON. 
    - Returns saved user in JSON.
```
{
  "firstname": "Marilyn ",
  "surname": "Monroe",
  "accessKey": "96#QI",
  "expirationTime": "2021-10-13T15:17:43"
}
```
```
{
  "firstname": "Antonio",
  "surname": "Banderas",
  "accessKey": ")?9K%",
  "expirationTime": "2021-10-13T15:17:56"
}
```
```
{
  "firstname": "John",
  "surname": "Smith",
  "accessKey": "@pa>C",
  "expirationTime": "2021-10-13T15:18:06"
}
```

- GET http://localhost:8080/v1/ecommerce/users

    - Request is returning all available users in JSON.
    - Doesn't require any parameters.


- GET http://localhost:8080/v1/ecommerce/users/6

    - Request is returning chosen user in JSON.
    - Requires the path variable (id of a user).


- PUT http://localhost:8080/v1/ecommerce/users/6
  
  - Request returns modified User's credentials (only 'firstname" and 'surname' ) and newly generated Key (new access key and new expiration time). User Id is not changed.

```
{
  "firstname": "Jack ",
  "surname": "Wild",
  "accessKey": "J#-'P",
  "expirationTime": "2021-10-13T17:08:16.115"
}
```
- DELETE http://localhost:8080/v1/ecommerce/users/10

    - Request is deleting chosen user.
    - Requires the path variable (id of a user). 
    - Doesn't return anything.
