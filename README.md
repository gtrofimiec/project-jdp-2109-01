# E-commerce

Back-end mechanism for an e-commerce application, written in JAVA. This is a group project, prepared during Java Developer Bootcamp in [Kodilla](http://kodilla.com)

## Tools and libraries:

- Java 11
- IntelliJ IDEA
- Gradle version 4.10.2
- Spring Boot 2.1.18.RELEASE
- Lombok version 1.18.20
- JUnit4
- Springfox Swagger2 version 2.9.2
- Databases:
	- MySQL
	- H2 - for tests

## Main assumptions:

- For business purposes we use soft delete in the app, to avoid permanent deletion of records.

- Our e-commerce app has 5 groups of endpoints (documented in Swagger - run app and open in a browser [API documentation](http://localhost:8080/swagger-ui.html)):

|Group of Endpoints|Description                      |Http Methods                                   |Example request             |
|------------------|---------------------------------|-----------------------------------------------|----------------------------|
|Groups            |Groups of Products               |GET(all, by group ID), POST, PUT               |[Groups](docs/GROUPS.md)    |
|Products          |Products available in online shop|GET(all, by product ID), POST, PUT, DELETE     |[Products](docs/PRODUCTS.md)|
|Users             |Clients                          |GET(all, by user ID), POST, PUT, DELETE        |[Users](docs/USERS.md)      |
|Carts             |Cart with products added by user |POST(cart, order), PUT(add, and delete product)|[Carts](docs/CARTS.md)      |
|Orders            |Order based on cart              |GET(all, by order ID), POST, PUT, DELETE       |[Orders](docs/ORDERS.md)    |


## How to run application

1. Clone or download from repository, and build a project.
2. Prepare database in accordance with application.properties file located in resources package (main package).
3. To see how application works, you can run it (Ecommerce Application.class) and use prepared request(look at the table above) in the Postman.

## Ideas for improvement

1. We wanted to add user authentication and authorisation(limit access to certain endpoints). In order to achieve that, we were considering using Spring Security.
2. There is possibility to provide users access to history of their orders - thanks to used soft delete.
