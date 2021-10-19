# Example requests

## PRODUCTS

- POST http://localhost:8080/v1/ecommerce/products

    - Request is adding new product record. 
    - Requires JSON. 
    - Returns saved product in JSON.

```
{
    "name" : "First Product",
    "description" : "Super Product",
    "price" : 1000,
    "groupId" : 2
}

{
    "name" : "Second Product",
    "description" : "Better Product",
    "price" : 500,
    "groupId" : 1
}

{
    "name" : "Third Product",
    "description" : "Bad Product",
    "price" : 500,
    "groupId" : 1
}
```

- GET http://localhost:8080/v1/ecommerce/products

    - Request is returning all available products in JSON.
    - Doesn't require any parameter.


- GET http://localhost:8080/v1/ecommerce/products/3

    - Request is returning chosen product in JSON. 
    - Require a path variable (id of a product).
  

- PUT http://localhost:8080/v1/ecommerce/products/4

    - Request is updating chosen product.
    - Requires a path variable(id of a product), and JSON value of the updated product.
    - Returns updated product.

```
{
    "name" : "Second Product",
    "description" : "The Best Product",
    "price" : 100,
    "groupId" : 1
}
```
- DELETE http://localhost:8080/v1/ecommerce/products/5

    - Request is deleting chosen product.
    - Requires a path variable(id of a product). 
    - Doesn't return anything.