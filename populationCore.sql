UPDATE Base_user SET adresse = '178-8582 Varius Av.,317235,North Gyeongsang,Italy' WHERE id = 1
UPDATE Base_user SET adresse = 'Ap #422-3464 Ipsum. Road,37115137,Basilicata,Poland' WHERE id = 2
UPDATE Base_user SET adresse = 'Ap #337-3434 Ante Road,1424,Ulster,Sweden' WHERE id = 3

INSERT INTO Category (id,CATEGORY_NAME, CATEGORY_DESCRIPTION)
VALUES (1,'Ameublement', 'Vente des meubles');
INSERT INTO Category (id,CATEGORY_NAME, CATEGORY_DESCRIPTION)
VALUES (2,'Voitures', 'Vente des voitures');
INSERT INTO Category (id,CATEGORY_NAME, CATEGORY_DESCRIPTION)
VALUES (3,'Multimedia', 'Vente des ordinateurs');

INSERT INTO Room (id,CATEGORY_ID, ROOM_TYPE)
VALUES (1,1,'UP');
INSERT INTO Room (id,CATEGORY_ID, ROOM_TYPE)
VALUES (2,2,'UP');
INSERT INTO Room (id,CATEGORY_ID, ROOM_TYPE)
VALUES (3,3,'DOWN');
