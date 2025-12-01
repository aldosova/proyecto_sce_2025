CREATE TABLE customer (
  id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1 ,INCREMENT BY 1) PRIMARY KEY,
  name VARCHAR(45), email VARCHAR(45), phone VARCHAR(45), address VARCHAR(45), city_region VARCHAR(2), cc_number VARCHAR(19)
);
INSERT INTO customer (name,email,phone,address,city_region,cc_number) VALUES ('Juan Perez','juan@mail.com','555','Calle 1','MX','1234');

CREATE TABLE category (
  id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1 ,INCREMENT BY 1) PRIMARY KEY, name VARCHAR(45)
);
INSERT INTO category (name) VALUES ('Electronica');

CREATE TABLE product (
  id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1 ,INCREMENT BY 1) PRIMARY KEY,
  name VARCHAR(45), price DECIMAL(10,2), description VARCHAR(50), last_update TIMESTAMP, category_id INT,
  existencia INT DEFAULT 1000
);
INSERT INTO product (name,price,category_id,existencia) VALUES ('Laptop',15000.00,1,1000);
INSERT INTO product (name,price,category_id,existencia) VALUES ('Mouse',200.00,1,1000);
INSERT INTO product (name,price,category_id,existencia) VALUES ('Teclado',500.00,1,1000);

CREATE TABLE customer_order (
  id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1 ,INCREMENT BY 1) PRIMARY KEY,
  amount DECIMAL(10,2), date_created TIMESTAMP, confirmation_number INT, customer_id INT,
  STATUS VARCHAR(20) DEFAULT 'CREADO'
);

CREATE TABLE ordered_product (
  customer_order_id INT NOT NULL, product_id INT NOT NULL, quantity SMALLINT,
  PRIMARY KEY (customer_order_id, product_id)
);

CREATE SEQUENCE NUM_CONF START WITH 100 INCREMENT BY 1;

CREATE SEQUENCE NUM_CONF
    START WITH 100
    INCREMENT BY 1;

ALTER TABLE CUSTOMER_ORDER ADD COLUMN STATUS VARCHAR(20) DEFAULT 'CREADO';

INSERT INTO PRODUCT (id, name, price, category_id, existencia) 
    VALUES (1, 'Laptop Reparada', 15000.00, 1, 1000);
INSERT INTO PRODUCT (id, name, price, category_id, existencia) 
    VALUES (2, 'Mouse Reparado', 200.00, 1, 1000);
INSERT INTO PRODUCT (id, name, price, category_id, existencia) 
    VALUES (3, 'Teclado Reparado', 500.00, 1, 1000);