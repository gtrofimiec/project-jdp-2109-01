# Example requests

## CARTS

- POST http://localhost:8080/v1/ecommerce/carts/6

    - Request is adding new cart record.
    - Requires a path variable (id of a user). 
    - Returns JSON value of the created cart.
  

- PUT http://localhost:8080/v1/ecommerce/carts/12/addProduct/3
- PUT http://localhost:8080/v1/ecommerce/carts/12/addProduct/4

    - Request is updating chosen cart, it adds product.
    - Requires a path variables (id of a cart and id of a product). 
    - Returns JSON value of updated cart.


- PUT http://localhost:8080/v1/ecommerce/carts/12/deleteProduct/4

    - Request is updating chosen cart, it removes product
    - Requires a path variables (id of a cart and id of a product). 
    - Returns JSON value of updated cart.


- POST http://localhost:8080/v1/ecommerce/carts/12/order

    - Request is creating new order based on cart.
    - Requires a path variable(id of a cart). 
    - Returns JSON value of created order.