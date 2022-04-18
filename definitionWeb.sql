INSERT INTO Category VALUES(1, 'Ameublement');
INSERT INTO Category VALUES(2, 'Véhicules');
INSERT INTO Category VALUES(3, 'Multimédia');

INSERT INTO Product VALUES(1, 350, 'Chaise Ikea Markus' 1);
INSERT INTO Product VALUES(2, 500, 'Table Ikea Hemnes' 1);
INSERT INTO Product VALUES(3, 35900, 'BMW X5' 2);
INSERT INTO Product VALUES(4, 49000, 'Tesla Model 3' 2);
INSERT INTO Product VALUES(5, 990, 'iPhone X' 3);
INSERT INTO Product VALUES(6, 1590, 'Lenovo ThinkPad 2020' 3);

INSERT INTO Stock VALUES(1, 4, 1, 1);
INSERT INTO Stock VALUES(2, 2, 2, 2);
INSERT INTO Stock VALUES(3, 1, 3, 3);
INSERT INTO Stock VALUES(4, 1, 3, 4);
INSERT INTO Stock VALUES(5, 4, 2, 5);
INSERT INTO Stock VALUES(6, 2, 3, 6);

INSERT INTO Room VALUES(1, 1);
INSERT INTO Room VALUES(2, 2);
INSERT INTO Room VALUES(3, 3);

INSERT INTO Lot VALUES(1, 2, 1);
INSERT INTO Lot VALUES(2, 1, 3);

INSERT INTO Sale VALUES(1, TO_TIMESTAMP('2022-04-29 15:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0, 1, 1, 'UP', 700, TO_TIMESTAMP(CURRENT_TIMESTAMP), 1);
INSERT INTO Sale VALUES(2, TO_TIMESTAMP('2022-04-27 11:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0, 1, 1, 'UP', 40000, TO_TIMESTAMP(CURRENT_TIMESTAMP), 2);

INSERT INTO Room_Sales VALUES(1, 1);
INSERT INTO Room_Sales VALUES(2, 2);