DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS company;
CREATE TABLE employee (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(100),
  age INT,
  gender VARCHAR(10)  ,
  company_id INT
);

 CREATE TABLE company (
   company_id INT AUTO_INCREMENT  PRIMARY KEY,
   name VARCHAR(100)
 );
