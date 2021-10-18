# Example requests

## GROUPS

- POST http://localhost:8080/v1/ecommerce/groups

    - Request is adding new group record. 
    - Requires JSON. 
    - Returns saved group in JSON.
```
{
    "name" : "First Group"
}

{
    "name" : "Second Group"
}
```
- GET http://localhost:8080/v1/ecommerce/groups

    - Request is returning all available groups in JSON.
    - Doesn't require any parameters.


- GET http://localhost:8080/v1/ecommerce/groups/1

    - Request is returning chosen group in JSON.
    - Requires the path variable(id of a group).


- PUT http://localhost:8080/v1/ecommerce/groups/1

    - Request is updating chosen group 
    - Requires the path variable(id of a group), and JSON value of the updated group. 
    - Returns updated group in JSON.
```
{
    "name" : "New name of a group"
}
```