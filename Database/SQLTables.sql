
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
DROP TABLE IF EXISTS Communities CASCADE;
DROP TABLE IF EXISTS Users CASCADE;
DROP TABLE IF EXISTS Roles CASCADE;
DROP TABLE IF EXISTS Feedback CASCADE;

-- ------------------------------------------------------
-- --------------------Create Tables---------------------
-- ------------------------------------------------------

CREATE TABLE Users (
	`uID` integer auto_increment, 
	`uName` varchar(50) NOT NULL,
	`firstName` varchar(30) NOT NULL,
	`lastName` varchar(30) NOT NULL,
	`role` varchar(30) NOT NULL,
	`uEmail` varchar(50) NOT NULL,
	`uPassword` varchar(20) NOT NULL,
	PRIMARY KEY (`uID`),
	UNIQUE KEY `uName` (`uName`)
);

CREATE TABLE Communities (
	`cID` integer auto_increment, 
	`cName` varchar(20) NOT NULL,
	`description` varchar(500) NOT NULL,
	`ownerUserName` varchar(50) NOT NULL,
	PRIMARY KEY (`cID`),
	UNIQUE KEY `cName` (`cName`),
	FOREIGN KEY (ownerUserName) REFERENCES Users(uName) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Roles (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`rName` varchar(30) NOT NULL,
	`authLevel` int(1) NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE KEY `rName` (`rName`)
);

CREATE TABLE Events (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`eName` varchar(30) NOT NULL,
	`start` date NOT NULL,
	`end` date NOT NULL,
	`cID` int(11) NOT NULL,
	`cName` varchar(20) NOT NULL,
	`ownerUserName` varchar(50) NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE KEY `eName` (`eName`),
	FOREIGN KEY (cID) REFERENCES Communities(cID) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (ownerUserName) REFERENCES Users(uName) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE RolesUsersCommunities (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`uID` int(11) NOT NULL,
	`cID` int(11) NOT NULL,
	`roleName` varchar(30) NOT NULL,
	`dStart` date NOT NULL,
	`dEnd` date NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE KEY `ruc` (`uID`,`cID`),
	FOREIGN KEY (uID) REFERENCES Users(uID) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (cID) REFERENCES Communities(cID) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Feedback (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`username` varchar(50) NOT NULL,
	`datePublished` date NOT NULL,
	`feedback` varchar(4000) NOT NULL,
	PRIMARY KEY (`id`)
);

-- ------------------------------------------------------
-- -------------------Insert Values----------------------
-- ------------------------------------------------------

-- ----------------------Users------------------------
LOCK TABLES Users WRITE;
-- Test Values
INSERT INTO Users VALUES (1,'Admin', 'Admin', 'User', 'ADMIN', 'admin@fculapp.com', 'adminPassword'),
			 (2,'TestEditor', 'TestEditor', 'Man', 'EDITOR', 'tester@fculapp.com', 'testPassword'),
			 (3,'Manuel', 'Manuel', 'Goncalves', 'USER', 'manuel@fculapp.com', 'manuelPassword'),
			 (4,'Pedro', 'Pedro', 'Manuel', 'USER', 'pedro@fculapp.com', 'pedroPassword');
UNLOCK TABLES;

-- --------------------Communities-----------------------
LOCK TABLES Communities WRITE;
-- Test Values
INSERT INTO Communities VALUES (1,'ASC', 'This is meant for all the students that  are participating in the
	course of ASC', 'TestEditor'),
	(2,'LASIGE', 'Computer Science and Engineering research unit at the Department of Informatics, 
	Faculty of Sciences, University of Lisboa.', 'TestEditor'),
	(3,'SD', 'This is meant for all the students that  are participating in the
	course of SD', 'TestEditor'),
	(4,'SO', 'This is meant for all the students that  are participating in the
	course of SO', 'TestEditor');
UNLOCK TABLES;


-- ----------------------Roles------------------------
-- There is a default role, which all users get when they enter the system. All users
-- are given a role on the ALL_USER_COMMUNITY, which they belong to.
-- Roles go from authLevel 1 to 3, 1 being the authLevel with the least privileges
Lock Tables Roles WRITE;
INSERT INTO Roles Values(1, 'COMMUNITY_ADMIN_ROLE', 3);
INSERT INTO Roles Values(2, 'COMMUNITY_EDITOR_ROLE', 2);
INSERT INTO Roles Values(3, 'COMMUNITY_USER_ROLE', 1);

-- Test Values
UNLOCK TABLES;

-- --------------------Events-------------------------
LOCK TABLES Events WRITE;
-- Test Values
INSERT INTO Events VALUES (1,'Reunião','2020-02-24','2020-02-25',3,'SD','TestEditor'),
			  (2,'Almoço_no_LASIGE','2020-03-14','2020-03-14',4,'LASIGE','TestEditor');
UNLOCK TABLES;

-- --------------RolesUsersCommunities----------------
LOCK TABLES RolesUsersCommunities WRITE;
-- Test Values
INSERT INTO RolesUsersCommunities VALUES (1,3,3,'COMMUNITY_USER_ROLE','2020-01-04','2021-01-04'),
					 (2,4,4,'COMMUNITY_USER_ROLE','2020-01-04','2021-01-04');
UNLOCK TABLES;

