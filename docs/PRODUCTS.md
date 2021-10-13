#Example requests

##PRODUCTS

- POST http://localhost:8080/v1/ecommerce/products

    - Method is adding new product record. 
    - Requires JSON. 
    - Returns saved product in JSON.

```
{
    "name" : "First Product",
    "description" : "Super Product",
    "price" : 1000,
    "groupDto" : {
        "id" : 2,
        "name" : "Second Group"
    }
}

{
    "name" : "Second Product",
    "description" : "Better Product",
    "price" : 500,
    "groupDto" : {
        "id" : 1,
        "name" : "New name of a group"
    }
}

{
    "name" : "Third Product",
    "description" : "Bad Product",
    "price" : 500,
    "groupDto" : {
        "id" : 1,
        "name" : "New name of a group"
    }
}
```

- GET http://localhost:8080/v1/ecommerce/products

    - Method is returning all available products in JSON.
    - Doesn't require any parameter.

- GET http://localhost:8080/v1/ecommerce/products/3

    - Method is returning chosen product in JSON. 
    - Require a path variable (id of a product).

- PUT http://localhost:8080/v1/ecommerce/products/4

    - Method is updating chosen product.
    - Requires a path variable(id of a product), and JSON value of the updated product.
    - Returns updated product.

```
{
    "name" : "Second Product",
    "description" : "The Best Product",
    "price" : 100,
    "groupDto" : {
        "id" : 1,
        "name" : "New name of a group"
    }
}
```
- DELETE http://localhost:8080/v1/ecommerce/products/5

    - Method is deleting chosen product.
    - Requires a path variable(id of a product). 
    - Doesn't return anything.