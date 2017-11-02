/****************************************************************************
* Create the SheridanSports inventory database and all associated tables    *
*****************************************************************************/



CREATE DATABASE SheridanSports;

USE SheridanSports;

CREATE TABLE User(
	UserId INT NOT NULL AUTO_INCREMENT,
	FirstName VARCHAR(50),
	LastName VARCHAR(50),
	Email VARCHAR(50),
	Username VARCHAR(20) NOT NULL,
	Password VARCHAR(50) NOT NULL,
	RoleName VARCHAR(50) NOT NULL,
	
	PRIMARY KEY(UserId)
);

INSERT INTO User 
  (FirstName, LastName, Email, Username, Password, RoleName)
VALUES 
  ('Audrey', 'Chang', 'achang@email.com', 'audreyc', 'shoes', 'Customer'),
  ('Benson', 'Amberbach', 'bamberbach@sheridansports.com', 'bensona', 'coffee', 'Sales'), 
  ('Kelly', 'Reich', 'kreich@sheridansports.com', 'kellyr', 'c0mput3r', 'Manager'),
  ('Efren', 'Delgado', 'edelgado@email.com', 'efrend', 'password', 'Customer'),
  ('Kris', 'Gehry', 'kgehry@sheridansports.com', 'krisg', 'guitar', 'Sales'),
  ('Ralph', 'Swift', 'swift1@email.com', 'swift1', 'taylor', 'Customer'),
  ('William', 'Talevich', 'will.talevich@email.com', 'willt', 'truck5', 'Customer'),
  ('Carly', 'Cyrus', 'carly472@email.com', 'carlyc', 'musical', 'Customer'),
  ('Gerry', 'Geraldson', 'geraldson@email.com', 'geraldson', 'baseball', 'Customer'),
  ('Dominic', 'Candell-Mench', 'domcm@email.com', 'candellmench', 'papaya', 'Customer');
  
CREATE TABLE Card(
	CardId INT NOT NULL AUTO_INCREMENT,
	CardNumber VARCHAR(20) NOT NULL,
	CardType VARCHAR(20) NOT NULL,
	ExpiryMonth VARCHAR(2) NOT NULL,
	ExpiryYear VARCHAR(2) NOT NULL,
	CardHolder VARCHAR(50) NOT NULL,
	UserId INT NOT NULL,
	UNIQUE (CardNumber),
	PRIMARY KEY(CardId),
	FOREIGN KEY(UserId) REFERENCES User(UserId)
);

INSERT INTO Card
	(CardNumber, CardType, ExpiryMonth, ExpiryYear, CardHolder, UserId)
VALUES
	('5555555555554444', 'MC', '12', '16', 'Audrey Chang', 1),
	('4111111111111111', 'VISA', '03', '17', 'Dominic Candell-Mench', 10),
	('371449635398431', 'AMEX', '07', '15', 'Gerry Geraldson', 9),
	('378734493671000', 'AMEX', '10', '14', 'Gemma Delgado', 4),
	('4012888888881881', 'VISA', '11', '15', 'Billy Cyrus', 8),
	('5105105105105100', 'MC', '01', '16', 'Taylor Swift', 6),
	('378282246310005', 'AMEX', '05', '18', 'William Talevich', 7);
  
CREATE TABLE Product(
	ProductId VARCHAR(15) NOT NULL,
	Manufacturer VARCHAR(50),
	Item VARCHAR(50) NOT NULL,
	Description VARCHAR(255),
	Price DECIMAL(7,2) NOT NULL,
	Available BOOLEAN,

	PRIMARY KEY(ProductId)
);

INSERT INTO Product
	(ProductId, Manufacturer, Item, Description, Price, Available)
VALUES
	('LSG27809', 'Louisville Slugger', 'Batting Gloves', 'Black with grey suede leather palm', 24.00, true),
	('NBB32655', 'Nike', 'True Grip Basketball', 'Size 7 - outdoor', 29.99, true),
	('RHJ80176', 'Reebok', 'Crosby SC87 1 Hockey Stick - Junior', 'Multi-lam shaft construction, curve pattern: P87A', 14.99, true),
	('NGW41447', 'Nike', 'Thermal Golf Jacket - Women', '100% polyester, white', 59.98, true),
	('RSM45621', 'Reebok', 'RealFlex Speed Running Shoes', 'Low-cut, black/silver', 119.49, true),
	('NTM71839', 'Nike', 'Premier RF Polo Tennis Top', 'Blue', 89.99, true),
	('ADT87717', 'Adidas', 'Adizero Tennis Tee', '100% polyester double knit, white w/ blue, yellow', 39.10, true),
	('TMM64516', 'TaylorMade', 'GHOST Spider S', 'PURE ROLL technology', 200.00, true),
	('NBB83274', 'Nike', 'Kobe 8 System Basketball Shoes', 'Weighs less than 10 ounces', 179.08, true),
	('UAY20808', 'Under Armour', 'Studio Rave Capri Tights - Women', 'Polyester/Elastane, yellow', 89.98, true);

CREATE TABLE Purchase(
	PurchaseId INT NOT NULL AUTO_INCREMENT, 
	CardId INT NOT NULL,
	Notes VARCHAR(255),
	PurchaseDate TIMESTAMP,
	
	PRIMARY KEY(PurchaseId),
	FOREIGN KEY(CardId) REFERENCES Card(CardId)
);

CREATE TABLE PurchaseItem(
	PurchaseItemId INT NOT NULL AUTO_INCREMENT,
	PurchaseId INT NOT NULL,
	ProductId VARCHAR(15) NOT NULL,
	Quantity INT NOT NULL,
	Price DECIMAL(7,2) NOT NULL,
	
	PRIMARY KEY(PurchaseItemId),
	FOREIGN KEY(PurchaseId) REFERENCES Purchase(PurchaseId),
	FOREIGN KEY(ProductId) REFERENCES Product(ProductId)
);
	
	