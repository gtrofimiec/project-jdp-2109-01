# Example requests

## ORDERS

- POST http://localhost:8080/v1/ecommerce/orders

    - Method is adding new order record. 
    - Requires JSON. 
    - Returns saved order in JSON.

```
{
    "totalPrice" : 1000,
    "cartDto" : null
}
```

- GET http://localhost:8080/v1/ecommerce/orders

    - Method is returning all available orders in JSON. 
    - Doesn't require any parameters.

- GET http://localhost:8080/v1/ecommerce/order/12

    - Method is returning chosen order in JSON. 
    - Requires a path variable(id of an order).


- PUT http://localhost:8080/v1/ecommerce/order/12

    - Method is updating chosen order. 
    - Requires a path variable (id of an order), and JSON value of the updated order.
    - Returns updated order in JSON.
```
{
    "totalPrice" : 1000,
    "cartDto" : null
}
```
- DELETE http://localhost:8080/v1/ecommerce/orders/12

    - Method is deleting chosen order. 
    - Requires a path variable (id of an order). 
    - Doesn't return anything.

