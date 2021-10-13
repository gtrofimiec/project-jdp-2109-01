#Example requests

##GROUPS

- POST http://localhost:8080/v1/ecommerce/groups

    - Method is adding new group record. 
    - Requires JSON. 
    - Returns saved group in JSON.
```
{
    "id" : 1,
    "name" : "First Group"
}

{
    "id" : 2,
    "name" : "Second Group"
}
```
- GET http://localhost:8080/v1/ecommerce/groups

    - Method is returning all available groups in JSON.
    - Doesn't require any parameters.

- GET http://localhost:8080/v1/ecommerce/groups/1

    - Method is returning chosen group in JSON.
    - Requires the path variable(id of a group).

- PUT http://localhost:8080/v1/ecommerce/groups/1

    - Method is updating chosen group 
    - Requires the path variable(id of a group), and JSON value of the updated group. 
    - Returns updated group in JSON.
```
{
    "id" : 1,
    "name" : "New name of a group"
}
```