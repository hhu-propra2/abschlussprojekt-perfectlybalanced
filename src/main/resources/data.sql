INSERT INTO USER_ENTITY(firstname,lastname,username,password,email)
VALUES('Max','Mustermann','MMustermann','MaxMuster223','Max@Mustermann.de');

INSERT INTO USER_ENTITY(firstname,lastname,username,password,email)
VALUES('Jan','Wellem','JanW','JanWellem','Jan@Wellem.de');

INSERT INTO ADMIN_ENTITY(username,password)
VALUES('JanW','JanWellem');

INSERT INTO LENDING_ENTITY(start,end,status,borrower_user_id,product_id)
VALUES('2019-02-12','2019-02-14',conflict,1,1);

INSERT INTO PRODUCT_ENTITY(titel,description,cost,address_id,street,housenumber,postcode,city,surety,owner_user_id)
VALUES('Rasenmäher','Mäht den Rasen',30,1,'Universiätsstraße',1,40225,'Düsseldorf',10,1);