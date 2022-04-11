/* Définition des tables */
CREATE TABLE Category(
category_code INT PRIMARY KEY,
category_name VARCHAR(50)
);

CREATE TABLE Room (
room_code INT PRIMARY KEY,
category_code INT,
FOREIGN KEY (category_code) REFERENCES Category(category_code)
);

CREATE TABLE BaseUser (user_code INTEGER PRIMARY KEY, email VARCHAR2(100), first_name VARCHAR2(50), last_name VARCHAR2(50), address VARCHAR2(150), CONSTRAINT email_check CHECK(email LIKE "%@%.%"));

CREATE TABLE Product (product_code INTEGER PRIMARY KEY, product_name VARCHAR2(50), product_cost FLOAT(2), category_code INTEGER, CONSTRAINT fk_category FOREIGN KEY category_code REFERENCES Category(category_code));

CREATE TABLE Stock (stock_code INTEGER PRIMARY KEY, user_code INTEGER, product_code INTEGER, quantity INTEGER, CONSTRAINT fk_user FOREIGN KEY (user_code) REFERENCES BaseUser(user_code), CONSTRAINT fk_product FOREIGN KEY (product_code) REFERENCES BaseUser(user_code), CONSTRAINT quantity_positive CHECK(quantity > 0));

CREATE TABLE Lot (lot_code INTEGER PRIMARY KEY, stock_code INTEGER, quantity_to_sell INTEGER, CONSTRAINT fk_stock FOREIGN KEY (stock_code) REFERENCES Stock(stock_code), CONSTRAINT quantity_positive CHECK(quantity_to_sell > 0));

CREATE TABLE Sale (
sale_code INTEGER PRIMARY KEY,
lot_code INTEGER,
start_price FLOAT(2),
sale_type VARCHAR(4) CHECK (sale_type IN ('UP', 'DOWN')),
multiple_offer INT CHECK (multiple_offer IN (0, 1)),
limited INT CHECK (limited IN (0, 1)),
start_tstamp DATE,
end_tstamp DATE,
revocable INT CHECK (revocable IN (0 ,1)),
room_code INT,
FOREIGN KEY (lot_code) REFERENCES Lot(lot_code),
FOREIGN KEY (room_code) REFERENCES Room(room_code)
);

CREATE TABLE Bid (bid_code INTEGER PRIMARY KEY, sale_code INTEGER, user_code INTEGER, accepted INTEGER, amount FLOAT(2), tstamp DATE, CONSTRAINT fk_sale FOREIGN KEY (sale_code) REFERENCES Sale(sale_code), CONSTRAINT fk_user FOREIGN KEY (user_code) REFERENCES BaseUser(user_code), CONSTRAINT accepted_bool CHECK(accepted IN (1, 0), CONSTRAINT amount_positive CHECK(amount > 0));
