# Java web application with: File system, MySQL, Oracle DB, Servlet, Authentification, Permission and Encoding filters, Jsp, Bootstrap.

Small online coffee shop, where user can choose and order some coffee. Admin can watch all orders and update order status as delivered. 

## Getting Started

You can choose 1 of 3 DB (file system, mysql, oracle).

### Installing

1. If you want use file system:
  - update in file root.properties 1 row to "method.of.orders.saving=file"
  - update in file root.properties 2 and 3 rows. You can choose any local disk or folder, but you must create it, if not exist. Don't create files, it will be created automatically. 
in my example, the files will be created on disk D.

2. If you want use MySQL DB:
  - update in file root.properties 1 row to "method.of.orders.saving=mysql"
  - update in file database.properties DB properties: port number, DB name, login and password. Be carefull: don't remove "?createDatabaseIfNotExist=true". All tables and DB will be created automatically.

3. If you want use Oracle DB:
  - update in file root.properties 1 row to "method.of.orders.saving=oracle"
  - update in file database.properties DB properties: port number, DB name, login and password.
  - launch scripts like in  file oracle.sql to create tables, and insert any coffees.
  
## Built With

MAVEN
TOMCAT

## Authors

Denis Monich

## Usability

At start page you can choose coffee, after can go to next page, where you can change order and come back to 1 page, after you can confirm order, 
pre-filling the form fields, after you will come to congratulation page. Also you can go to /admin url, where you can log in with admin/admin credential, and you will see all ordes, 
with the ability to change delivery status (buttom "change delivery status availible only for not deliveried orders)

Good Luck! Hope to see you comments and stars! Thanks
