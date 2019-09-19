[![CircleCI](https://circleci.com/gh/murphy1/inventory-management-spring.svg?style=svg)](https://circleci.com/gh/murphy1/inventory-management-spring)
# inventory-management-spring
Inventory Management Project re-imagined with Spring

 - Manage your Inventory efficiently and dynamically. 
 
 
![Home](https://github.com/murphy1/inventory-management-spring/blob/master/src/main/resources/static/Homepage.jpg)


- Easy CRUD operations for your products!


![Products](https://github.com/murphy1/inventory-management-spring/blob/master/src/main/resources/static/Furniture.jpg)


- Check your availables funds, while Finance users can make updates directly with ease.


![Wallet](https://github.com/murphy1/inventory-management-spring/blob/master/src/main/resources/static/Wallet.jpg)


- Admins can easily update users. Adding new roles, enabling and disabling accounts. 


![Users](https://github.com/murphy1/inventory-management-spring/blob/master/src/main/resources/static/Users_readme.jpg)

#

## Prerequisites

```
- Database: H2, MySQL or both
```

## How to Run

```
1) Update the properties file with your local sql instance data

- For MySql

spring:
  datasource:
    url: jdbc:mysql://localhost:3306/{your-db}
    username: {username}
    password: {password}
  jpa:
    hibernate:
      ddl-auto: create-drop
    database-platform: org.hibernate.dialect.MySQL5Dialect
    database: mysql
    show-sql: true
    
IMPORTANT: Hibernate will create the schema. Change above 'ddl-auto' to 'Update' when schema has been generated

2) Add ADMIN privileges for the first user

In the 'UserServiceImpl' file line 62:
Change this to 'user.setRoles("ADMIN");'

This user can then add admin privileges to other users who register.
```

## Further Information

- Feel free to reach me directly for further assistance. Email: smurphy54321@gmail.com
