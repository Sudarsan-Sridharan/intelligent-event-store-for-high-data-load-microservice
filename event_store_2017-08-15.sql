# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: localhost (MySQL 5.7.19)
# Database: event_store
# Generation Time: 2017-08-15 12:37:15 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table captures
# ------------------------------------------------------------

DROP TABLE IF EXISTS `captures`;

CREATE TABLE `captures` (
  `uuid` varchar(255) DEFAULT NULL,
  `cartUuid` int(11) DEFAULT NULL,
  `data` text,
  UNIQUE KEY `identifier` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table cart
# ------------------------------------------------------------

DROP TABLE IF EXISTS `cart`;

CREATE TABLE `cart` (
  `uuid` varchar(255) DEFAULT NULL,
  `customer_uuid` varchar(255) DEFAULT NULL,
  `sessionId` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table clients
# ------------------------------------------------------------

DROP TABLE IF EXISTS `clients`;

CREATE TABLE `clients` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `customerNumber` int(11) DEFAULT NULL,
  `organisation` varchar(255) DEFAULT NULL,
  `title` varchar(11) DEFAULT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `addressStreetName` varchar(255) DEFAULT NULL,
  `addressStreetNumber` varchar(255) DEFAULT NULL,
  `addressZip` varchar(255) DEFAULT NULL,
  `addressCity` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table events
# ------------------------------------------------------------

DROP TABLE IF EXISTS `events`;

CREATE TABLE `events` (
  `uuid` int(64) unsigned NOT NULL AUTO_INCREMENT,
  `customer` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `description` mediumtext,
  `location` varchar(255) DEFAULT NULL,
  `dateTime` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `id_idx` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table line_items
# ------------------------------------------------------------

DROP TABLE IF EXISTS `line_items`;

CREATE TABLE `line_items` (
  `uuid` varchar(255) DEFAULT NULL,
  `cartUuid` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `productUuid` varchar(255) DEFAULT NULL,
  `tax` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `line_items` WRITE;
/*!40000 ALTER TABLE `line_items` DISABLE KEYS */;

INSERT INTO `line_items` (`uuid`, `cartUuid`, `title`, `price`, `amount`, `productUuid`, `tax`)
VALUES
	('22486d88-6030-4eee-84a0-f9d27a86ea1a','13a270e4-e4d8-44b2-ac6e-897f60c8f753','Sample Product',10.6,2,'0f8be861-f5e5-4ba2-acdc-8c9f5a6a2427',19),
	('18eba9ec-4dce-4de6-ab49-2ff861eb51db','13a270e4-e4d8-44b2-ac6e-897f60c8f753','Demo Product',5.9,1,'ca275984-0f8d-43a7-88a8-0bd12f157547',19);

/*!40000 ALTER TABLE `line_items` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table orders
# ------------------------------------------------------------

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `uuid` varchar(255) DEFAULT NULL,
  `cartUuid` int(11) DEFAULT NULL,
  `data` text,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `streetName` varchar(255) DEFAULT NULL,
  `streetNumber` varchar(255) DEFAULT NULL,
  `postalCode` int(5) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `payment` varchar(255) DEFAULT NULL,
  UNIQUE KEY `identifier` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;

INSERT INTO `orders` (`uuid`, `cartUuid`, `data`, `firstName`, `lastName`, `streetName`, `streetNumber`, `postalCode`, `city`, `payment`)
VALUES
	('914f000d-447e-4eff-a36e-ab913a0ad68e',NULL,NULL,'John','Doe','Marienplatz','1',80331,'München','PayPal');

/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table payments
# ------------------------------------------------------------

DROP TABLE IF EXISTS `payments`;

CREATE TABLE `payments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer` varchar(255) DEFAULT NULL,
  `amount` int(11) DEFAULT '1',
  `token` varchar(255) DEFAULT NULL,
  `payer_id` varchar(255) DEFAULT NULL,
  `recurring` tinyint(1) DEFAULT '0',
  `digital` tinyint(1) DEFAULT '0',
  `popup` tinyint(1) DEFAULT '0',
  `completed` tinyint(1) DEFAULT '0',
  `canceled` tinyint(1) DEFAULT '0',
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `identifier` varchar(255) DEFAULT NULL,
  `buyer` mediumtext,
  `payment_type` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `payment_option` varchar(10000) DEFAULT NULL,
  `total_price_after_tax` float DEFAULT NULL,
  `postal_code` int(11) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `line1` varchar(255) DEFAULT NULL,
  `line2` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `country_code` varchar(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `last_update_time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table payments_ingenicopaymentservices
# ------------------------------------------------------------

DROP TABLE IF EXISTS `payments_ingenicopaymentservices`;

CREATE TABLE `payments_ingenicopaymentservices` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table payments_paypal
# ------------------------------------------------------------

DROP TABLE IF EXISTS `payments_paypal`;

CREATE TABLE `payments_paypal` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `token` varchar(255) DEFAULT NULL,
  `paypal_transaction` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table products
# ------------------------------------------------------------

DROP TABLE IF EXISTS `products`;

CREATE TABLE `products` (
  `uuid` varchar(255) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(1024) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `isActive` int(11) DEFAULT NULL,
  `isUpdated` int(11) DEFAULT '1',
  `taxClass` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `identifier` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;

INSERT INTO `products` (`uuid`, `id`, `title`, `name`, `price`, `isActive`, `isUpdated`, `taxClass`)
VALUES
	('0f8be861-f5e5-4ba2-acdc-8c9f5a6a2427',93,'Sample Product',NULL,10.6,1,0,NULL),
	('ca275984-0f8d-43a7-88a8-0bd12f157547',106,'Demo Product',NULL,5.9,1,0,NULL);

/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sale_items
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sale_items`;

CREATE TABLE `sale_items` (
  `uuid` varchar(255) DEFAULT NULL,
  `cartUuid` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `productUuid` varchar(255) DEFAULT NULL,
  `tax` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `sale_items` WRITE;
/*!40000 ALTER TABLE `sale_items` DISABLE KEYS */;

INSERT INTO `sale_items` (`uuid`, `cartUuid`, `title`, `price`, `amount`, `productUuid`, `tax`)
VALUES
	('2d2617b3-83ab-407c-a9c6-cc2b092f87f7','13a270e4-e4d8-44b2-ac6e-897f60c8f753','Sample Product',10.6,2,'0f8be861-f5e5-4ba2-acdc-8c9f5a6a2427',19),
	('415dc267-1230-42be-aa1a-72119adae5a5','13a270e4-e4d8-44b2-ac6e-897f60c8f753','Demo Product',5.9,1,'ca275984-0f8d-43a7-88a8-0bd12f157547',19);

/*!40000 ALTER TABLE `sale_items` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table tax_classes
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tax_classes`;

CREATE TABLE `tax_classes` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `tax_classes` WRITE;
/*!40000 ALTER TABLE `tax_classes` DISABLE KEYS */;

INSERT INTO `tax_classes` (`id`, `title`)
VALUES
	(1,'Food'),
	(2,'Non-Food');

/*!40000 ALTER TABLE `tax_classes` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table topic_items
# ------------------------------------------------------------

DROP TABLE IF EXISTS `topic_items`;

CREATE TABLE `topic_items` (
  `uuid` varchar(255) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(1024) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `imagePath` varchar(255) DEFAULT NULL,
  `credit` float DEFAULT '0',
  `finished` int(11) DEFAULT NULL,
  `identifier` varchar(255) DEFAULT NULL,
  `last_bidder` varchar(255) DEFAULT NULL,
  `isActive` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `identifier` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table users
# ------------------------------------------------------------

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `avatar_url_gravatar_by_email` varchar(255) DEFAULT NULL,
  `encrypted_password` varchar(255) NOT NULL DEFAULT '',
  `remember_created_at` datetime DEFAULT NULL,
  `current_sign_in_at` datetime DEFAULT NULL,
  `last_sign_in_at` datetime DEFAULT NULL,
  `current_sign_in_ip` varchar(255) DEFAULT NULL,
  `last_sign_in_ip` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `role` varchar(11) DEFAULT '2',
  `password` varchar(255) DEFAULT '',
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `user_role` int(11) DEFAULT '2',
  `telephone` varchar(255) DEFAULT NULL,
  `enabled` int(11) DEFAULT NULL,
  `last_token_timestamp` int(11) DEFAULT NULL,
  `isTechnical` int(11) DEFAULT '0',
  `reset_password_token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `index_users_on_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;

INSERT INTO `users` (`id`, `email`, `avatar_url_gravatar_by_email`, `encrypted_password`, `remember_created_at`, `current_sign_in_at`, `last_sign_in_at`, `current_sign_in_ip`, `last_sign_in_ip`, `created_at`, `updated_at`, `name`, `role`, `password`, `first_name`, `last_name`, `username`, `user_role`, `telephone`, `enabled`, `last_token_timestamp`, `isTechnical`, `reset_password_token`)
VALUES
	(1,'mail@next-cloud.de',NULL,'',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','$2a$04$QLN/W3F6M9vq0krrQaa9TulY0l1T9HLol1AzvTxBmKSEL7uytkWd2','Simon','Ebner','admin',1,'',1,1,1,NULL),
	(2,'john.doe@next-cloud.de',NULL,'',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','$2a$06$RRoa8ph72Q9X2G2WxY7/gOhm5qwK62.GrQpEQT3HzVXXVX8v.Ppqq','John','Doe','john.doe',2,'',1,1,0,NULL);

/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
