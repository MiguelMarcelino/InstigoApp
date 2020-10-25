
-- Database: rolesUtilCommunities
-- ------------------------------------------------------
-- Server version	5.7.29-0ubuntu0.18.04.1


-- ------------------------------------------------------
-- ---------------------Database-------------------------
-- ------------------------------------------------------

USE RolesUtilCommunities;

-- ------------------------------------------------------
-- ---------------------Drop Tables----------------------
-- ------------------------------------------------------

DROP TABLE IF EXISTS user_subscribed_communities CASCADE;
DROP TABLE IF EXISTS events CASCADE;
DROP TABLE IF EXISTS communities CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS operations CASCADE;
DROP TABLE IF EXISTS roles CASCADE;
DROP TABLE IF EXISTS feedback CASCADE;

-- ------------------------------------------------------
-- --------------------Create Tables---------------------
-- ------------------------------------------------------

CREATE TABLE users (
	`id` integer AUTO_INCREMENT, 
	`user_name` varchar(50) NOT NULL,
	`first_name` varchar(30) NOT NULL,
	`last_name` varchar(30) NOT NULL,
	`role_id` integer NOT NULL,
	`email` varchar(50) NOT NULL,
	`password` varchar(20) NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE KEY `user_name` (`user_name`)
);

CREATE TABLE communities (
	`id` integer AUTO_INCREMENT, 
	`name` varchar(20) NOT NULL,
	`description` varchar(500) NOT NULL,
	`community_owner_id` integer NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE KEY `name` (`name`),
	FOREIGN KEY (`community_owner_id`) REFERENCES users(`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE roles (
	`id` integer NOT NULL AUTO_INCREMENT,
	`name` varchar(30) NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE KEY `name` (`name`)
);

CREATE TABLE operations (
	`id` integer NOT NULL AUTO_INCREMENT,
	`name` varchar(30) NOT NULL,
	`role_id` integer NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE KEY `op_name_role` (`name`, `role_id`),
	FOREIGN KEY (`role_id`) REFERENCES roles(`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE events (
	`id` integer NOT NULL AUTO_INCREMENT,
	`name` varchar(30) NOT NULL,
	`start` date NOT NULL,
	`end` date NOT NULL,
	`community_id` integer NOT NULL,
	`event_owner_id` integer NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE KEY `name` (`name`),
	FOREIGN KEY (`community_id`) REFERENCES communities(`id`) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (`event_owner_id`) REFERENCES users(`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE user_subscribed_communities (
	`id` integer NOT NULL AUTO_INCREMENT,
	`user_id` integer NOT NULL,
	`community_id` integer NOT NULL,
	`start_date` date NOT NULL,
	`end_date` date NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE KEY `usc` (`user_id`,`community_id`),
	FOREIGN KEY (`community_id`) REFERENCES communities(`id`) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (`user_id`) REFERENCES users(`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE feedback (
	`id` integer NOT NULL AUTO_INCREMENT,
	`user_name` varchar(50) NOT NULL,
	`date_published` date NOT NULL,
	`feedback_content` varchar(4000) NOT NULL,
	PRIMARY KEY (`id`)
);

-- ------------------------------------------------------
-- -------------------Insert Values----------------------
-- ------------------------------------------------------

-- ----------------------roles------------------------
LOCK TABLES roles WRITE;
INSERT INTO roles VALUES (1, 'ADMIN'), (2, 'EDITOR'), (3, 'USER');

UNLOCK TABLES;

-- -------------------operations----------------------
-- ADMIN
INSERT INTO operations VALUES 
	(1, 'READ_COMMUNITY', 1)
	(1, 'CREATE_COMMUNITY', 1), (2, 'DELETE_COMMUNITY', 1), (2, 'DELETE_ALL_COMMUNITY', 1), (2, 'DELETE', 1), (3, 'READ', 1), (4, 'MANAGE_OWN_SUBSCRIPTIONS', 1), 
	(5, 'ADD_USER', 1), (6, 'DELETE_ALL', 1), (7, 'MANAGE_ALL_SUBSCRIPTIONS', 1), (8, 'CREATE_USER', 1);

-- EDITOR
INSERT INTO operations VALUES 
	(9, 'CREATE', 2), (10, 'DELETE', 2), (11, 'READ', 2), (12, 'MANAGE_OWN_SUBSCRIPTIONS', 2);

-- USER
INSERT INTO operations VALUES 
	(13, 'READ', 3), (14, 'MANAGE_OWN_SUBSCRIPTIONS', 3);

-- ----------------------users------------------------
LOCK TABLES users WRITE;
-- Test Values
INSERT INTO users VALUES (1,'Admin', 'Admin', 'User', 1, 'admin@fculapp.com', 'adminPassword'), -- ADMIN --> role_id 1
			 (2,'TestEditor', 'TestEditor', 'Editor', 2, 'tester@fculapp.com', 'testPassword'), -- EDITOR --> role_id 2
			 (3,'Manuel', 'Manuel', 'Goncalves', 3, 'manuel@fculapp.com', 'manuelPassword'), -- USER --> role_id 3
			 (4,'Pedro', 'Pedro', 'Manuel', 3, 'pedro@fculapp.com', 'pedroPassword'); -- USER --> role_id 3
UNLOCK TABLES;

-- --------------------Communities-----------------------
LOCK TABLES communities WRITE;
-- Test Values
INSERT INTO communities VALUES (1,'ASC', 'This is meant for all the students that  are participating in the
	course of ASC', 2),
	(2,'LASIGE', 'Computer Science and Engineering research unit at the Department of Informatics, 
	Faculty of Sciences, University of Lisboa.', 2),
	(3,'SD', 'This is meant for all the students that  are participating in the
	course of SD', 2),
	(4,'SO', 'This is meant for all the students that  are participating in the
	course of SO', 2);
UNLOCK TABLES;

-- --------------------Events-------------------------
LOCK TABLES events WRITE;
-- Test Values
INSERT INTO events VALUES (1,'Reunião','2020-02-24','2020-02-25',3,2),
			  (2,'Almoço no LASIGE','2020-03-14','2020-03-14',4,2);
UNLOCK TABLES;

-- --------------rolesusersCommunities----------------
LOCK TABLES user_subscribed_communities WRITE;
-- Test Values
INSERT INTO user_subscribed_communities VALUES (1,3,3,'2020-01-04','2021-01-04'),
					 (2,4,4,'2020-01-04','2021-01-04');
UNLOCK TABLES;
