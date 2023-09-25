<h1 align=center>
  <img src="./github/icon.png">
</h1>

## Link to challenge
[PicPay Challenge Back-end](https://github.com/PicPay/picpay-desafio-backend)
## Config Database

`Change the database information and create the database. `

Run the query to create the database
```
create database picpay;
```
`Access application.properties`
```
spring.datasource.url= jdbc:mysql://localhost:3306/picpay?useSSL=false
spring.datasource.username=root
spring.datasource.password=docker
```
## Running
```bash
  mvn install
```

```bash
  mvn spring-boot:run
```

## Features

- [X] Create a User;
- [X] Create a Transaction;
- [X] List all users;


