# Example requests

## ORDERS

- GET http://localhost:8080/v1/ecommerce/orders

    - Request is returning all available orders in JSON. 
    - Doesn't require any parameters.


- GET http://localhost:8080/v1/ecommerce/order/13

    - Request is returning chosen order in JSON. 
    - Requires a path variable(id of an order).


- PUT http://localhost:8080/v1/ecommerce/order/13

    - Request is updating chosen order. 
    - Requires a path variable (id of an order).
    - Returns updated order in JSON.
```
{
  "totalPrice": 1000, 
  "cartDto": {
     "id": 12, 
     "products": [
        {
          "name" : "First Product",
          "description" : "Super Product",
          "price" : 1000,
          "groupId" : 2
        }
     ]
  }
}
```

- DELETE http://localhost:8080/v1/ecommerce/orders/13

    - Request is deleting chosen order. 
    - Requires a path variable (id of an order). 
    - Doesn't return anything.

