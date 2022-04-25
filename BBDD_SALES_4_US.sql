DROP DATABASE IF EXISTS SALES_4_US;
CREATE DATABASE SALES_4_US;
USE SALES_4_US;


CREATE TABLE user (
id INT NOT NULL auto_increment PRIMARY KEY,
first_name VARCHAR (20) NOT NULL,
last_name VARCHAR (50) NOT NULL,
dni VARCHAR (9) NOT NULL,
email VARCHAR (45) NOT NULL,
phone INT NOT NULL,
password VARCHAR (200) NOT NULL,
address VARCHAR (60) NOT NULL,
rol VARCHAR (10) NOT NULL,
image VARCHAR (64),
reset_password_token VARCHAR (30)
);

CREATE TABLE provider (
id INT NOT NULL auto_increment PRIMARY KEY,
NAME VARCHAR (40) NOT NULL,
direction VARCHAR (120) NOT NULL,
phone INT NOT NULL,
website VARCHAR (40) NOT NULL,
email VARCHAR (40) NOT NULL
);

CREATE TABLE category (
id INT NOT NULL auto_increment PRIMARY KEY,
name VARCHAR(20) NOT NULL,
description VARCHAR(500)
);

CREATE TABLE opinion (
id INT NOT NULL auto_increment PRIMARY KEY,
user_id INT NOT NULL,
title VARCHAR (40) NOT NULL,
description VARCHAR (400) NOT NULL,
stars INT
);

CREATE TABLE product(
id INT auto_increment NOT NULL PRIMARY KEY,
name VARCHAR (20) NOT NULL,
path_image VARCHAR (20) NOT NULL,
prize INTEGER NOT NULL,
description VARCHAR (500) NOT NULL,
image VARCHAR (64) NOT NULL
);

CREATE TABLE product_categories (
product_id INT NOT NULL,
category_id INT NOT NULL,
FOREIGN KEY (product_id) REFERENCES product (id),
FOREIGN KEY (category_id) REFERENCES category (id)
);

CREATE TABLE product_opinions (
product_id INT NOT NULL,
opinion_id INT NOT NULL,
FOREIGN KEY (product_id) REFERENCES product (id),
FOREIGN KEY (opinion_id) REFERENCES opinion (id)
);

CREATE TABLE product_provider (
product_id INT NOT NULL,
provider_id INT NOT NULL,
FOREIGN KEY (product_id) REFERENCES product (id),
FOREIGN KEY (provider_id) REFERENCES provider (id)
);

CREATE TABLE cart_item (
id INT NOT NULL auto_increment PRIMARY KEY,
product_id INT,
user_id INT,
quantity INT,
visible BOOLEAN,
FOREIGN KEY (product_id) REFERENCES product (id),
FOREIGN KEY (user_id) REFERENCES user (id)
);

CREATE TABLE purchase (
id INT NOT NULL auto_increment PRIMARY KEY,
DATE DATETIME,
total_prize DECIMAL NOT NULL
);

CREATE TABLE purchase_cart (
purchase_id INT NOT NULL,
cart_item INT NOT NULL,
FOREIGN KEY (purchase_id) REFERENCES purchase (id),
FOREIGN KEY (cart_item) REFERENCES cart_item (id)
);


INSERT INTO user (first_name, last_name, dni, phone, password, email,address,rol,image) VALUES ("jose", "miranda", "12345678Z", 608262020, "$2a$10$N2Gyo4ryYoCZNS3im0Q/uO1N2FpXDiTsYaF72e/T1RUmnNwbG08hW", "jose@jose.local", "Al carrer", "admin", "default_profile.png");
INSERT INTO user (first_name, last_name, dni, phone, password, email,address,rol,image) VALUES ("lucas", "padilla", "12345678P", 633568742, "$2a$10$CLOcU1vc/5Oq384KjEooLOCSO2o5xMnc2w4uaCIiKgGqP8PNGWRQW", "lucas@lucas.local", "Arriba", "client","default_profile.png");

INSERT INTO category (id, name, description) VALUES (1, "Footwear", "A few description for the category");
INSERT INTO provider (id, name, direction,phone,website,email) VALUES (1, "provider_test", "one direction", 678264536, "www.provider.com", "provider@provider.local");
INSERT INTO category (id, name, description) VALUES (2, "Shirts", "A few description for the category");
INSERT INTO provider (id, name, direction,phone,website,email) VALUES (2, "second_provider", "two direction", 698267535, "www.provider2.com", "provider2@provider2.local");

INSERT INTO product ( name, path_image, prize, description, image) VALUES (
"Nike Aire Force",
"Nike Aire Force",
89.90,
"Nike Aire Force One Black and Yellow for teenagers and adults.",
"nike_air_force.jpg"
);

INSERT INTO product ( name, path_image, prize, description, image) VALUES (
"Nike Air Max 97",
"Nike Air Max 97",
240,
"Nike Air Max 97 for teenagers and adults.",
"nike_air_max_97.png"
);

INSERT INTO product ( name, path_image, prize, description, image) VALUES (
'Nike Air Max 90',
'Nike Air Max 90',
'120',
'Nike Air Max 90 for teenagers and adults.',
'nike_air_max_90.png');

INSERT INTO product ( name, path_image, prize, description, image) VALUES (
'Jordan Retro 4',
'Jordan Retro 4',
'350',
'Jordan Retro 4 for teenagers and adults.',
'71HqxX1ENCL._AC_UY395_.jpg');

INSERT INTO product ( name, path_image, prize, description, image) VALUES (
'Adidas Stan Smith',
'Adidas Stan Smith',
'90',
'Adidas Stan Smith for teenagers and adults.',
'stan_smith.png');

INSERT INTO product ( name, path_image, prize, description, image) VALUES (
'Adidas Magmur Runner',
'Adidas Magmur Runner',
'240',
'Adidas Magmur Runner for teenagers and adults.',
'adidas_magmur_runner.png');

INSERT INTO product ( name, path_image, prize, description, image) VALUES (
'Adidas Samba Og',
'Adidas Samba Og',
'100',
'Adidas Samba Og for teenagers and adults.',
'adidas_samba_og.png');

INSERT INTO product_categories(product_id, category_id) VALUE  (1,1);
INSERT INTO product_categories(product_id, category_id) VALUE  (2,1);
INSERT INTO product_categories(product_id, category_id) VALUE  (3,1);
INSERT INTO product_categories(product_id, category_id) VALUE  (4,1);
INSERT INTO product_categories(product_id, category_id) VALUE  (5,1);
INSERT INTO product_categories(product_id, category_id) VALUE  (6,1);
INSERT INTO product_categories(product_id, category_id) VALUE  (7,1);
INSERT INTO product_provider(product_id, provider_id) VALUES (1,1);
INSERT INTO product_provider(product_id, provider_id) VALUES (2,1);
INSERT INTO product_provider(product_id, provider_id) VALUES (3,1);
INSERT INTO product_provider(product_id, provider_id) VALUES (4,1);
INSERT INTO product_provider(product_id, provider_id) VALUES (5,1);
INSERT INTO product_provider(product_id, provider_id) VALUES (6,1);
INSERT INTO product_provider(product_id, provider_id) VALUES (7,1);
#INSERT INTO cart_item (id,product_id,user_id, quantity ) VALUES (1,1,1,2);

SELECT * FROM user;
SELECT * FROM product;
SELECT * FROM opinion;
SELECT * FROM product_categories;
SELECT * FROM cart_item;
SELECT * FROM purchase;
SELECT * FROM purchase_cart;