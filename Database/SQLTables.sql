
-- Database: RolesUtilCommunities
-- ------------------------------------------------------
-- Server version	5.7.29-0ubuntu0.18.04.1


-- ------------------------------------------------------
-- ---------------------Database-------------------------
-- ------------------------------------------------------
USE RolesUtilCommunities;
-- ------------------------------------------------------
-- ---------------------Drop Tables----------------------
-- ------------------------------------------------------
DROP TABLE IF EXISTS RolesUsersCommunities CASCADE;
DROP TABLE IF EXISTS Events CASCADE;
DROP TABLE IF EXISTS Users CASCADE;
DROP TABLE IF EXISTS Roles CASCADE;
DROP TABLE IF EXISTS Communities CASCADE;

-- ------------------------------------------------------
-- --------------------Create Tables---------------------
-- ------------------------------------------------------

CREATE TABLE Users (
	`uID` integer auto_increment, 
	`uName` varchar(50) NOT NULL,
	`firstName` varchar(30) NOT NULL,
	`lastName` varchar(30) NOT NULL,
	`uEmail` varchar(50) NOT NULL,
	`uPassword` varchar(20) NOT NULL,
	PRIMARY KEY (`uID`),
	UNIQUE KEY `uName` (`uName`)
);

CREATE TABLE Communities (
	`cID` integer auto_increment, 
	`cName` varchar(20) NOT NULL,
	`description` varchar(500) NOT NULL,
	PRIMARY KEY (`cID`),
	UNIQUE KEY `cName` (`cName`)
);

CREATE TABLE Roles (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`rName` varchar(20) NOT NULL,
	`authLevel` int(1) NOT NULL,
	`cID` int(11) NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE KEY `rName` (`rName`),
	FOREIGN KEY (cID) REFERENCES Communities(cID) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Events (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`eName` varchar(30) NOT NULL,
	`start` date NOT NULL,
	`end` date NOT NULL,
	`cID` int(11) NOT NULL,
	`cName` varchar(20) NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE KEY `eName` (`eName`),
	FOREIGN KEY (cID) REFERENCES Communities(cID) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE RolesUsersCommunities (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`uID` int(11) NOT NULL,
	`cID` int(11) NOT NULL,
	`rID` int(11) NOT NULL,
	`dStart` date NOT NULL,
	`dEnd` date NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE KEY `ruc` (`uID`,`cID`,`rID`),
	FOREIGN KEY (uID) REFERENCES Users(uID) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (cID) REFERENCES Communities(cID) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (rID) REFERENCES Roles(id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- ------------------------------------------------------
-- -------------------Insert Values----------------------
-- ------------------------------------------------------

-- --------------------Communities-----------------------
LOCK TABLES Communities WRITE;
INSERT INTO Communities VALUES(1, 'ADMIN_COMMUNITY', 'All Admins belong to this community');
INSERT INTO Communities VALUES(2, 'MAINTAINER_COMMUNITY', 'All Maintainers belong to this community');
INSERT INTO Communities VALUES(3, 'ALL_USER_COMMUNITY', 'All Users belong to this community');

-- Test Values
INSERT INTO Communities VALUES (4,'ASC', 'This is meant for all the students that  are participating in the
	course of ASC'),
	(5,'LASIGE', 'Computer Science and Engineering research unit at the Department of Informatics, 
	Faculty of Sciences, University of Lisboa.'),
	(6,'SD', 'This is meant for all the students that  are participating in the
	course of SD'),
	(7,'SO', 'This is meant for all the students that  are participating in the
	course of SO');
UNLOCK TABLES;


-- ----------------------Roles------------------------
-- There is a default role, which all users get when they enter the system. All users
-- are given a role on the ALL_USER_COMMUNITY, which they belong to.
-- Roles go from authLevel 1 to 3, 1 being the authLevel with the least privileges
Lock Tables Roles WRITE;
INSERT INTO Roles Values(1, 'ADMIN_ROLE', 1, 3);
INSERT INTO Roles Values(2, 'MAINTAINER_ROLE', 1, 2);
INSERT INTO Roles Values(3, 'DEFAULT_USER_ROLE', 1, 1);


-- Test Values
INSERT INTO Roles VALUES (4, 'Hero-Admin', 3, 1);
UNLOCK TABLES;

-- ----------------------Users------------------------
LOCK TABLES Users WRITE;
-- Test Values
INSERT INTO Users VALUES (2,'Manuel', 'Manuel', 'Goncalves', 'manuel@gmail.com', 'manuelPassword'),
			 (1,'Pedro', 'Pedro', 'Manuel', 'pedro@outlook.pt', 'pedroPassword');
UNLOCK TABLES;

-- --------------------Events-------------------------
LOCK TABLES Events WRITE;
-- Test Values
INSERT INTO Events VALUES (1,'Reunião','2020-02-24','2020-02-25',1,'ASC'),
			  (2,'Almoço_no_LASIGE','2020-03-14','2020-03-14',2,'LASIGE');
UNLOCK TABLES;

-- --------------RolesUsersCommunities----------------
LOCK TABLES RolesUsersCommunities WRITE;
-- Test Values
INSERT INTO RolesUsersCommunities VALUES (1,1,4,3,'2020-01-04','2021-01-04'),
					 (2,2,5,3,'2020-01-04','2021-01-04');
UNLOCK TABLES;

