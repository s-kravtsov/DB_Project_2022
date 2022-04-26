/* Définition des tables */
DROP TABLE Bid;
DROP TABLE Sale;
DROP TABLE Lot;
DROP TABLE Stock;
DROP TABLE Product;
DROP TABLE BaseUser;
DROP TABLE Room;
DROP TABLE Category;

CREATE TABLE Category(
category_code INT PRIMARY KEY,
category_name VARCHAR(50)
);

CREATE TABLE Room (
room_code INT PRIMARY KEY,
category_code INT,
CONSTRAINT fk_category_room FOREIGN KEY (category_code) REFERENCES Category(category_code)
);

CREATE TABLE BaseUser (user_code INTEGER PRIMARY KEY, email VARCHAR2(100), first_name VARCHAR2(50), last_name VARCHAR2(50), address VARCHAR2(150), CONSTRAINT email_check CHECK(email LIKE '%@%.%'));

CREATE TABLE Product (product_code INTEGER PRIMARY KEY, product_name VARCHAR2(50), product_cost FLOAT(2), category_code INTEGER, CONSTRAINT fk_category_product FOREIGN KEY (category_code) REFERENCES Category(category_code));

CREATE TABLE Stock (stock_code INTEGER PRIMARY KEY, user_code INTEGER, product_code INTEGER, quantity INTEGER, CONSTRAINT fk_user_product FOREIGN KEY (user_code) REFERENCES BaseUser(user_code), CONSTRAINT fk_product_stock FOREIGN KEY (product_code) REFERENCES BaseUser(user_code), CONSTRAINT quantity_positive_stock CHECK(quantity > 0));

CREATE TABLE Lot (lot_code INTEGER PRIMARY KEY, stock_code INTEGER, quantity_to_sell INTEGER, CONSTRAINT fk_stock_lot FOREIGN KEY (stock_code) REFERENCES Stock(stock_code), CONSTRAINT quantity_positive_lot CHECK(quantity_to_sell > 0));

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
CONSTRAINT fk_lot_sale FOREIGN KEY (lot_code) REFERENCES Lot(lot_code),
CONSTRAINT fk_room_sale FOREIGN KEY (room_code) REFERENCES Room(room_code)
);

CREATE TABLE Bid (bid_code INTEGER PRIMARY KEY, sale_code INTEGER, user_code INTEGER, accepted INTEGER, amount FLOAT(2), tstamp DATE, CONSTRAINT fk_sale_bid FOREIGN KEY (sale_code) REFERENCES Sale(sale_code), CONSTRAINT fk_user_bid FOREIGN KEY (user_code) REFERENCES BaseUser(user_code), CONSTRAINT accepted_bool_bid CHECK(accepted IN (1, 0)), CONSTRAINT amount_positive_bid CHECK(amount > 0));


INSERT INTO Category (category_code,category_name)
VALUES (1,'Painting');
INSERT INTO Category (category_code,category_name)
VALUES (2,'Car');
INSERT INTO Category (category_code,category_name)
VALUES (3,'Sculpture');
INSERT INTO Category (category_code,category_name)
VALUES (4,'House');
INSERT INTO Category (category_code,category_name)
VALUES (5,'Apartment');
INSERT INTO Category (category_code,category_name)
VALUES (6,'Ground');
INSERT INTO Category (category_code,category_name)
VALUES (7,'Farmland');
INSERT INTO Category (category_code,category_name)
VALUES (8,'Fiat money');
INSERT INTO Category (category_code,category_name)
VALUES (9,'Archeology');
INSERT INTO Category (category_code,category_name)
VALUES (10,'Other');
INSERT INTO Category (category_code,category_name)
VALUES (11,'Wine');
INSERT INTO Category (category_code,category_name)
VALUES (12,'Watch');
INSERT INTO Category (category_code,category_name)
VALUES (13,'Music instrument');
INSERT INTO Category (category_code,category_name)
VALUES (14,'Furniture');
INSERT INTO Category (category_code,category_name)
VALUES (15,'Jewelry');


INSERT INTO Room (room_code,category_code)
VALUES (1,1);
INSERT INTO Room (room_code,category_code)
VALUES (2,2);
INSERT INTO Room (room_code,category_code)
VALUES (3,3);
INSERT INTO Room (room_code,category_code)
VALUES (4,4);
INSERT INTO Room (room_code,category_code)
VALUES (5,5);
INSERT INTO Room (room_code,category_code)
VALUES (6,6);
INSERT INTO Room (room_code,category_code)
VALUES (7,7);
INSERT INTO Room (room_code,category_code)
VALUES (8,8);
INSERT INTO Room (room_code,category_code)
VALUES (9,9);
INSERT INTO Room (room_code,category_code)
VALUES (10,10);
INSERT INTO Room (room_code,category_code)
VALUES (11,11);
INSERT INTO Room (room_code,category_code)
VALUES (12,12);
INSERT INTO Room (room_code,category_code)
VALUES (13,13);
INSERT INTO Room (room_code,category_code)
VALUES (14,14);
INSERT INTO Room (room_code,category_code)
VALUES (15,15);
INSERT INTO Room (room_code,category_code)
VALUES (16,1);
INSERT INTO Room (room_code,category_code)
VALUES (17,2);

INSERT INTO BaseUser (user_code,email,first_name,last_name,address)
VALUES (1,'jade@protonmail.ca','Jade','Carney','4192 Ultricies Ave');
INSERT INTO BaseUser (user_code,email,first_name,last_name,address)
VALUES (2,'brynn@outlook.org','Brynn','Torres','965-4808 Purus. Road');
INSERT INTO BaseUser (user_code,email,first_name,last_name,address)
VALUES (3,'gray@hotmail.co.uk','Gray','Potts','Ap #766-7336 Mus. St.');
INSERT INTO BaseUser (user_code,email,first_name,last_name,address)
VALUES (4,'serina@outlook.co.uk','Serina','Duke','3879 Senectus St.');
INSERT INTO BaseUser (user_code,email,first_name,last_name,address)
VALUES (5,'callum@icloud.co.uk','Callum','Day','5786 Consequat Rd.');
INSERT INTO BaseUser (user_code,email,first_name,last_name,address)
VALUES (6,'tobias@outlook.org','Tobias','Beach','262-7421 Nisi. Ave');
INSERT INTO BaseUser (user_code,email,first_name,last_name,address)
VALUES (7,'harper@outlook.co.uk','Harper','Lynn','Ap #560-8696 Neque Av.');
INSERT INTO BaseUser (user_code,email,first_name,last_name,address)
VALUES (8,'fuller@icloud.edu','Fuller','Horne','P.O. Box 879, 3987 Ac Road');
INSERT INTO BaseUser (user_code,email,first_name,last_name,address)
VALUES (9,'harrell@protonmail.net','Amethyst','Harrell','Ap #280-6282 Porttitor Road');
INSERT INTO BaseUser (user_code,email,first_name,last_name,address)
VALUES (10,'brennan@icloud.org','Rashad','Brennan','1015 Diam Street');
INSERT INTO BaseUser (user_code,email,first_name,last_name,address)
VALUES (11,'dacey@icloud.org','Dacey','Vazquez','Ap #816-2687 At, St.');
INSERT INTO BaseUser (user_code,email,first_name,last_name,address)
VALUES (12,'leo@protonmail.co.uk','Leo','Tate','Ap #887-2124 Tristique Rd.');
INSERT INTO BaseUser (user_code,email,first_name,last_name,address)
VALUES (13,'hurst@google.ca','Xandra','Hurst','634-8053 Sem. Street');
INSERT INTO BaseUser (user_code,email,first_name,last_name,address)
VALUES (14,'ann@hotmail.co.uk','Ann','Glover','P.O. Box 908, 6622 Tempor Avenue');
INSERT INTO BaseUser (user_code,email,first_name,last_name,address)
VALUES (15,'stanton@google.com','Mikayla','Stanton','477-522 Nisl Rd.');


INSERT INTO Product (product_code,product_name,product_cost,category_code)
VALUES (1,'Banksy 101','7.01',1);
INSERT INTO Product (product_code,product_name,product_cost,category_code)
VALUES (2,'Alpine 1','67.37',2);
INSERT INTO Product (product_code,product_name,product_cost,category_code)
VALUES (3,'Penseur de Rodin','63.62',3);
INSERT INTO Product (product_code,product_name,product_cost,category_code)
VALUES (4,'Cannes villa','59.81',4);
INSERT INTO Product (product_code,product_name,product_cost,category_code)
VALUES (5,'Paris 4 rooms','67.86',5);
INSERT INTO Product (product_code,product_name,product_cost,category_code)
VALUES (6,'Forest','37.63',6);
INSERT INTO Product (product_code,product_name,product_cost,category_code)
VALUES (7,'25 hectare','63.98',7);
INSERT INTO Product (product_code,product_name,product_cost,category_code)
VALUES (8,'French franc collection','5.23',8);
INSERT INTO Product (product_code,product_name,product_cost,category_code)
VALUES (9,'Parthenon small piece','37.74',9);
INSERT INTO Product (product_code,product_name,product_cost,category_code)
VALUES (10,'Chest of drawers','34.83',10);
INSERT INTO Product (product_code,product_name,product_cost,category_code)
VALUES (11,'Bordeaux wine 2006','61.54',11);
INSERT INTO Product (product_code,product_name,product_cost,category_code)
VALUES (12,'Lip French watch','26.90',12);
INSERT INTO Product (product_code,product_name,product_cost,category_code)
VALUES (13,'Jimi Hendrix Guitar','96.46',13);
INSERT INTO Product (product_code,product_name,product_cost,category_code)
VALUES (14,'Chest of drawers','37.06',14);
INSERT INTO Product (product_code,product_name,product_cost,category_code)
VALUES (15,'Queen ring','36.93',15);


INSERT INTO Stock (stock_code,user_code,product_code,quantity)
VALUES (1,1,1,8);
INSERT INTO Stock (stock_code,user_code,product_code,quantity)
VALUES (2,2,2,4);
INSERT INTO Stock (stock_code,user_code,product_code,quantity)
VALUES (3,3,3,3);
INSERT INTO Stock (stock_code,user_code,product_code,quantity)
VALUES (4,4,4,2);
INSERT INTO Stock (stock_code,user_code,product_code,quantity)
VALUES (5,5,5,10);
INSERT INTO Stock (stock_code,user_code,product_code,quantity)
VALUES (6,6,6,8);
INSERT INTO Stock (stock_code,user_code,product_code,quantity)
VALUES (7,7,7,4);
INSERT INTO Stock (stock_code,user_code,product_code,quantity)
VALUES (8,8,8,10);
INSERT INTO Stock (stock_code,user_code,product_code,quantity)
VALUES (9,9,9,1);
INSERT INTO Stock (stock_code,user_code,product_code,quantity)
VALUES (10,10,10,5);
INSERT INTO Stock (stock_code,user_code,product_code,quantity)
VALUES (11,11,11,6);
INSERT INTO Stock (stock_code,user_code,product_code,quantity)
VALUES (12,12,12,8);
INSERT INTO Stock (stock_code,user_code,product_code,quantity)
VALUES (13,13,13,6);
INSERT INTO Stock (stock_code,user_code,product_code,quantity)
VALUES (14,14,14,5);
INSERT INTO Stock (stock_code,user_code,product_code,quantity)
VALUES (15,15,15,2);


INSERT INTO Lot (lot_code,stock_code,quantity_to_sell)
VALUES (1,1,1);
INSERT INTO Lot (lot_code,stock_code,quantity_to_sell)
VALUES (2,2,1);
INSERT INTO Lot (lot_code,stock_code,quantity_to_sell)
VALUES (3,3,1);
INSERT INTO Lot (lot_code,stock_code,quantity_to_sell)
VALUES (4,4,1);
INSERT INTO Lot (lot_code,stock_code,quantity_to_sell)
VALUES (5,5,1);
INSERT INTO Lot (lot_code,stock_code,quantity_to_sell)
VALUES (6,6,1);
INSERT INTO Lot (lot_code,stock_code,quantity_to_sell)
VALUES (7,7,1);
INSERT INTO Lot (lot_code,stock_code,quantity_to_sell)
VALUES (8,8,1);
INSERT INTO Lot (lot_code,stock_code,quantity_to_sell)
VALUES (9,9,1);
INSERT INTO Lot (lot_code,stock_code,quantity_to_sell)
VALUES (10,10,1);
INSERT INTO Lot (lot_code,stock_code,quantity_to_sell)
VALUES (11,11,1);
INSERT INTO Lot (lot_code,stock_code,quantity_to_sell)
VALUES (12,12,1);
INSERT INTO Lot (lot_code,stock_code,quantity_to_sell)
VALUES (13,13,1);
INSERT INTO Lot (lot_code,stock_code,quantity_to_sell)
VALUES (14,14,1);
INSERT INTO Lot (lot_code,stock_code,quantity_to_sell)
VALUES (15,15,1);


INSERT INTO Sale (sale_code,lot_code,start_price,sale_type,multiple_offer,limited,start_tstamp,end_tstamp,revocable,room_code)
VALUES (1,1,'72.96','UP',0,0,TO_DATE('2022-04-05', 'YYYY-MM-DD'),TO_DATE('2022-04-14', 'YYYY-MM-DD'),1,1);
INSERT INTO Sale (sale_code,lot_code,start_price,sale_type,multiple_offer,limited,start_tstamp,end_tstamp,revocable,room_code)
VALUES (2,2,'90.60','DOWN',1,1,TO_DATE('2022-04-08', 'YYYY-MM-DD'),TO_DATE('2022-04-15', 'YYYY-MM-DD'),0,2);
INSERT INTO Sale (sale_code,lot_code,start_price,sale_type,multiple_offer,limited,start_tstamp,end_tstamp,revocable,room_code)
VALUES (3,3,'71.56','UP',0,0,TO_DATE('2022-04-06', 'YYYY-MM-DD'),TO_DATE('2022-04-20', 'YYYY-MM-DD'),1,3);
INSERT INTO Sale (sale_code,lot_code,start_price,sale_type,multiple_offer,limited,start_tstamp,end_tstamp,revocable,room_code)
VALUES (4,4,'2.05','DOWN',1,0,TO_DATE('2022-04-06', 'YYYY-MM-DD'),TO_DATE('2022-04-19', 'YYYY-MM-DD'),0,4);
INSERT INTO Sale (sale_code,lot_code,start_price,sale_type,multiple_offer,limited,start_tstamp,end_tstamp,revocable,room_code)
VALUES (5,5,'96.43','UP',0,1,TO_DATE('2022-04-06', 'YYYY-MM-DD'),TO_DATE('2022-04-16', 'YYYY-MM-DD'),1,5);
INSERT INTO Sale (sale_code,lot_code,start_price,sale_type,multiple_offer,limited,start_tstamp,end_tstamp,revocable,room_code)
VALUES (6,6,'36.15','UP',0,1,TO_DATE('2022-04-07', 'YYYY-MM-DD'),TO_DATE('2022-04-18', 'YYYY-MM-DD'),1,6);
INSERT INTO Sale (sale_code,lot_code,start_price,sale_type,multiple_offer,limited,start_tstamp,end_tstamp,revocable,room_code)
VALUES (7,7,'55.05','UP',0,1,TO_DATE('2022-04-05', 'YYYY-MM-DD'),TO_DATE('2022-04-16', 'YYYY-MM-DD'),0,7);
INSERT INTO Sale (sale_code,lot_code,start_price,sale_type,multiple_offer,limited,start_tstamp,end_tstamp,revocable,room_code)
VALUES (8,8,'87.25','UP',0,1,TO_DATE('2022-04-05', 'YYYY-MM-DD'),TO_DATE('2022-04-17', 'YYYY-MM-DD'),0,8);
INSERT INTO Sale (sale_code,lot_code,start_price,sale_type,multiple_offer,limited,start_tstamp,end_tstamp,revocable,room_code)
VALUES (9,9,'35.35','DOWN',0,1,TO_DATE('2022-04-07', 'YYYY-MM-DD'),TO_DATE('2022-04-19', 'YYYY-MM-DD'),1,9);
INSERT INTO Sale (sale_code,lot_code,start_price,sale_type,multiple_offer,limited,start_tstamp,end_tstamp,revocable,room_code)
VALUES (10,10,'38.31','UP',0,0,TO_DATE('2022-04-06', 'YYYY-MM-DD'),TO_DATE('2022-04-19', 'YYYY-MM-DD'),1,10);
INSERT INTO Sale (sale_code,lot_code,start_price,sale_type,multiple_offer,limited,start_tstamp,end_tstamp,revocable,room_code)
VALUES (11,11,'51.55','UP',0,0,TO_DATE('2022-04-07', 'YYYY-MM-DD'),TO_DATE('2022-04-16', 'YYYY-MM-DD'),1,11);
INSERT INTO Sale (sale_code,lot_code,start_price,sale_type,multiple_offer,limited,start_tstamp,end_tstamp,revocable,room_code)
VALUES (12,12,'51.71','UP',0,0,TO_DATE('2022-04-06', 'YYYY-MM-DD'),TO_DATE('2022-04-18', 'YYYY-MM-DD'),1,12);
INSERT INTO Sale (sale_code,lot_code,start_price,sale_type,multiple_offer,limited,start_tstamp,end_tstamp,revocable,room_code)
VALUES (13,13,'19.05','UP',1,0,TO_DATE('2022-04-04', 'YYYY-MM-DD'),TO_DATE('2022-04-14', 'YYYY-MM-DD'),1,13);
INSERT INTO Sale (sale_code,lot_code,start_price,sale_type,multiple_offer,limited,start_tstamp,end_tstamp,revocable,room_code)
VALUES (14,14,'39.26','UP',1,0,TO_DATE('2022-04-07', 'YYYY-MM-DD'),TO_DATE('2022-04-16', 'YYYY-MM-DD'),0,14);
INSERT INTO Sale (sale_code,lot_code,start_price,sale_type,multiple_offer,limited,start_tstamp,end_tstamp,revocable,room_code)
VALUES (15,15,'67.00','UP',1,0,TO_DATE('2022-04-06', 'YYYY-MM-DD'),TO_DATE('2022-04-14', 'YYYY-MM-DD'),1,15);
