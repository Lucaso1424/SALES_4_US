DROP DATABASE IF EXISTS SALES_4_US;
CREATE DATABASE SALES_4_US;
USE SALES_4_US;

CREATE TABLE user (
user_id INT NOT NULL auto_increment PRIMARY KEY,
user_name VARCHAR (20) NOT NULL,
user_surname VARCHAR (50) NOT NULL,
user_dni VARCHAR (9) NOT NULL,
email VARCHAR (20) NOT NULL,
user_phone INT NOT NULL,
password VARCHAR (200) NOT NULL,
user_address VARCHAR (60) NOT NULL
);

CREATE TABLE purchase (
id INT NOT NULL auto_increment PRIMARY KEY,
DATE DATETIME NOT NULL,
total_prize DECIMAL NOT NULL,
user_id INT NOT NULL,
FOREIGN KEY (user_id) REFERENCES user(user_id)
);

CREATE TABLE provider (
id INT NOT NULL auto_increment PRIMARY KEY,
NAME VARCHAR (40) NOT NULL,
direction VARCHAR (120) NOT NULL,
phone INT NOT NULL,
website VARCHAR (40) NOT NULL,
email VARCHAR (40) NOT NULL
);

CREATE TABLE product(
id INT NOT NULL auto_increment PRIMARY KEY,
provider_id INT NOT NULL, 
name VARCHAR (20) NOT NULL,
prize DECIMAL NOT NULL,
category VARCHAR (40) NOT NULL,
description VARCHAR (500) NOT NULL
);

CREATE TABLE linia_purchase (
purchase_id INT NOT NULL,
product_id INT NOT NULL,
unit INT NOT NULL,
PRIMARY KEY (purchase_id, product_id),
FOREIGN KEY (purchase_id) REFERENCES purchase (id),
FOREIGN KEY (product_id) REFERENCES product (ID)
);

CREATE TABLE opinion (
id INT NOT NULL auto_increment PRIMARY KEY,
user_id INT NOT NULL,
title VARCHAR (40) NOT NULL,
description VARCHAR (400) NOT NULL,
stars INT,
product_id INT NOT NULL,
FOREIGN KEY (product_id) REFERENCES product (id),
FOREIGN KEY (user_id) REFERENCES user (user_id)
);


CREATE TABLE rol (
  `id_rol` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id_rol`),
  KEY `fkRolUsuari_idx` (`user_id`),
  CONSTRAINT `fkRolUsuari` FOREIGN KEY (`user_id`) REFERENCES user (`user_id`)
);



INSERT INTO user(user_name, password, email) VALUES ("jose", "$2a$10$N2Gyo4ryYoCZNS3im0Q/uO1N2FpXDiTsYaF72e/T1RUmnNwbG08hW", "jose@jose.local");
INSERT INTO user(user_name, password, email) VALUES ("client", "$2a$10$Sul1phyB7QsHOmDafAeZP.fCFi/sAQLEoJyGG3nRdx0IV/uuPLn36", "client@client.local");
Select * FROM user;
INSERT INTO rol(name, user_id) VALUES ('admin', 1);
INSERT INTO rol(name, user_id) VALUES ('client', 2);