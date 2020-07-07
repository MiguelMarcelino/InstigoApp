
-- Database: RolesUtilCommunities
-- ------------------------------------------------------
-- Server version	5.7.29-0ubuntu0.18.04.1

-- ------------------------------------------------------
-- ---------------------Drop Tables----------------------
-- ------------------------------------------------------
DROP TABLE IF EXISTS Events CASCADE;
DROP TABLE IF EXISTS RolesUsersCommunities CASCADE;
DROP TABLE IF EXISTS Users CASCADE;
DROP TABLE IF EXISTS Communities CASCADE;
DROP TABLE IF EXISTS Roles CASCADE;

-- ------------------------------------------------------
-- --------------------Create Tables---------------------
-- ------------------------------------------------------

CREATE TABLE Users (
	uID integer PRIMARY KEY auto_increment, 
	uName varchar(20) not null
);

CREATE TABLE Communities (
	cID integer PRIMARY KEY auto_increment, 
	cName varchar(20) UNIQUE not null
);

-- ---------TODO---------
CREATE TABLE Roles (
	rID integer PRIMARY KEY auto_increment, 
	rName varchar(20) UNIQUE not null,
	
	aLevel integer not null
);
-- -----------------------

CREATE TABLE Events (
	id integer PRIMARY KEY auto_increment, 
	eName varchar(50) UNIQUE not null, 
	cID integer, 
	cName varchar(20),
	dStart DATE not null, 
	dEnd DATE not null, 
	CONSTRAINT fk_cID FOREIGN KEY (cID) REFERENCES Communities(cID) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE RolesUsersCommunities (
	id integer PRIMARY KEY auto_increment, 
	uID integer, 
	cID2 integer, 
	rID integer, 
	dStart DATE not null, 
	dEnd DATE not null,
	CONSTRAINT fk_uID FOREIGN KEY (uID) REFERENCES Users(uID) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT fk_cID2 FOREIGN KEY (cID2) REFERENCES Communities(cID) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT fk_rID FOREIGN KEY (rID) REFERENCES Roles(rID) ON DELETE CASCADE ON UPDATE CASCADE
);

-- ------------------------------------------------------
-- -------------------Insert Values----------------------
-- ------------------------------------------------------

LOCK TABLES Users WRITE;
INSERT INTO Users VALUES (2,'Manuel'),(1,'Pedro');
UNLOCK TABLES;

LOCK TABLES Communities WRITE;
INSERT INTO Communities VALUES (1,'ASC'),(2,'LASIGE'),(4,'SD'),(3,'SO');
UNLOCK TABLES;

LOCK TABLES Events WRITE;
INSERT INTO Events VALUES (1,'Reunião',1,'ASC', '2020-02-24','2020-02-25'),(2,'Almoço_no_LASIGE',2,'LASIGE','2020-03-14','2020-03-14');
UNLOCK TABLES;

LOCK TABLES Roles WRITE;
INSERT INTO Roles VALUES (1, 'Hero', 5);
UNLOCK TABLES;

LOCK TABLES RolesUsersCommunities WRITE;
INSERT INTO RolesUsersCommunities VALUES (1,1,1,1,'2020-01-04','2021-01-04'),(2,2,2,1,'2020-01-04','2021-01-04');
UNLOCK TABLES;

