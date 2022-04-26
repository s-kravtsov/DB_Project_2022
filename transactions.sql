/*
La mise en place d’une Salle de Vente et la sélection de produits déjà disponibles à la
vente et permettant le choix du type d’enchères et du prix de départ
*/
SET TRANSACTION READ WRITE;

INSERT INTO Category (id,CATEGORY_NAME, CATEGORY_DESCRIPTION)
VALUES (4,'Immobilier', 'Vente des maisons');

INSERT INTO Room (id,CATEGORY_ID, ROOM_TYPE)
VALUES (4,4,'UP');

SELECT * FROM Sale JOIN Room_Sales ON Sale.id == Room_Sales.sale_id
WHERE Room_Sales.room_id == 4 AND Sale.start_price < 5000

COMMIT;

/*
L’enchère faite par un utilisateur sur un produit mis en vente dans une Salle de Vente
*/

SET TRANSACTION READ WRITE;

INSERT INTO Product (id,BENEFICE_PRICE,PRODUCT_NAME,CATEGORY_ID, STOCK, BASE_USER_ID)
VALUES (15,9000000,'Villa in Monaco',4, 1, 2);

INSERT INTO Lot (id,QUANTITY_TO_SELL,PRODUCT_ID)
VALUES (15,1,15);

INSERT INTO Sale (ID,CLOSED,END_TSTAMP,LIMITED,MULTIPLE_OFFER,REVOCABLE,START_PRICE,START_TSTAMP,LOT_ID, LAST_REFRESH)
VALUES (15,0,NULL,0,1,0,1000000,CURRENT_TIMESTAMP,15, CURRENT_TIMESTAMP);

INSERT INTO Bid (ID,ACCEPTED,AMOUNT,QUANTITY,TSTAMP,BASE_USER_ID,SALE_ID)
VALUES (101,0, 11000000, 1, CURRENT_TIMESTAMP, 3, 15);

COMMIT;

/*
Le processus de fin d’enchère déterminant le (ou les) utilisateur(s) ayant remporté une
vente, en tenant compte du type d’enchère bien sûr.
*/

SET TRANSACTION READ WRITE;

SELECT * FROM Bid WHERE sale_id = 15 AND accepted = 1

COMMIT;
