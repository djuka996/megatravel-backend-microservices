-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: db_megabooker
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `accomodation_type`
--

DROP TABLE IF EXISTS `accomodation_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `accomodation_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `last_changed_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accomodation_type`
--

LOCK TABLES `accomodation_type` WRITE;
/*!40000 ALTER TABLE `accomodation_type` DISABLE KEYS */;
INSERT INTO `accomodation_type` VALUES (1,'bazen','1111-11-11 11:11:11'),(2,'klima','1111-11-11 11:11:11'),(3,'jukebox','1111-11-11 11:11:11'),(4,'pogled','1111-11-11 11:11:11'),(5,'zabava','1111-11-11 11:11:11'),(6,'nesto','1111-11-11 11:11:11');
/*!40000 ALTER TABLE `accomodation_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `coordinatex` double NOT NULL,
  `coordinatey` double NOT NULL,
  `country` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `street_number` int(11) NOT NULL,
  `last_changed_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'Maglic',10,10,'21473','marsala tita',56,'1111-11-11 11:11:11'),(2,'Novi sad',15,15,'21200','bulevar evrope',20,'1111-11-11 11:11:11'),(3,'Novi sad',20,20,'21478','bulevar oslobodjenja',15,'1111-11-11 11:11:11'),(4,'Beograd',30,30,'21000','beogradksa ulica',66,'1111-11-11 11:11:11');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `amount_type`
--

DROP TABLE IF EXISTS `amount_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `amount_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `currency` varchar(255) DEFAULT NULL,
  `price` decimal(19,2) DEFAULT NULL,
  `last_changed_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `amount_type`
--

LOCK TABLES `amount_type` WRITE;
/*!40000 ALTER TABLE `amount_type` DISABLE KEYS */;
INSERT INTO `amount_type` VALUES (1,'RSD',200.00,'2017-01-01 00:00:00'),(2,'RSD',500.00,'2017-01-01 00:00:00'),(3,'RSD',700.00,'2017-01-01 00:00:00'),(4,'RSD',1000.00,'2017-01-01 00:00:00'),(5,'RSD',300.00,'2017-01-01 00:00:00'),(6,'RSD',750.00,'2017-01-01 00:00:00'),(7,'RSD',600.00,'2017-01-01 00:00:00'),(8,'RSD',450.00,'2017-01-01 00:00:00');
/*!40000 ALTER TABLE `amount_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat`
--

DROP TABLE IF EXISTS `chat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `chat` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `chats_hotel_id` bigint(20) DEFAULT NULL,
  `last_changed_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7e3dqo8ld4mww7wes9de4h7ju` (`chats_hotel_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat`
--

LOCK TABLES `chat` WRITE;
/*!40000 ALTER TABLE `chat` DISABLE KEYS */;
INSERT INTO `chat` VALUES (1,1,'2019-01-01 00:00:00'),(2,2,'2019-01-01 00:00:00');
/*!40000 ALTER TABLE `chat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `currency_price`
--

DROP TABLE IF EXISTS `currency_price`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `currency_price` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount_id` bigint(20) DEFAULT NULL,
  `last_changed_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2b22b50sx5ep460wfreubsc1m` (`amount_id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `currency_price`
--

LOCK TABLES `currency_price` WRITE;
/*!40000 ALTER TABLE `currency_price` DISABLE KEYS */;
INSERT INTO `currency_price` VALUES (1,1,'2017-01-01 00:00:00'),(2,2,'2017-01-01 00:00:00'),(3,3,'2017-01-01 00:00:00'),(4,4,'2017-01-01 00:00:00'),(5,5,'2017-01-01 00:00:00'),(6,6,'2017-01-01 00:00:00'),(7,7,'2017-01-01 00:00:00'),(8,8,'2017-01-01 00:00:00'),(9,9,'2017-01-01 00:00:00');
/*!40000 ALTER TABLE `currency_price` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `extra_option`
--

DROP TABLE IF EXISTS `extra_option`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `extra_option` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `last_changed_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `extra_option`
--

LOCK TABLES `extra_option` WRITE;
/*!40000 ALTER TABLE `extra_option` DISABLE KEYS */;
INSERT INTO `extra_option` VALUES (1,'ekstraOpcija1','1111-11-11 11:11:11'),(2,'opcija2','1111-11-11 11:11:11'),(3,'masaza','1111-11-11 11:11:11'),(4,'kupka','1111-11-11 11:11:11'),(5,'topla voda','1111-11-11 11:11:11'),(6,'dosta','1111-11-11 11:11:11');
/*!40000 ALTER TABLE `extra_option` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotel`
--

DROP TABLE IF EXISTS `hotel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `hotel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rating` double NOT NULL,
  `address_id` bigint(20) DEFAULT NULL,
  `users_hotel_id` bigint(20) DEFAULT NULL,
  `last_changed_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK48m0ei7s6biikxbrku04slp0s` (`address_id`),
  KEY `FK30v86qtqnqyi2hk3d3w1ejycp` (`users_hotel_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotel`
--

LOCK TABLES `hotel` WRITE;
/*!40000 ALTER TABLE `hotel` DISABLE KEYS */;
INSERT INTO `hotel` VALUES (1,3,1,3,'2017-01-01 00:00:00'),(2,5,2,4,'2017-01-01 00:00:00'),(3,5,3,NULL,'2017-01-01 00:00:00'),(4,4,4,NULL,'2017-01-01 00:00:00'),(5,2,4,NULL,'2017-01-01 00:00:00');
/*!40000 ALTER TABLE `hotel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotel_extra_option`
--

DROP TABLE IF EXISTS `hotel_extra_option`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `hotel_extra_option` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `extra_option_id` bigint(20) DEFAULT NULL,
  `hotel_extra_option_id` bigint(20) DEFAULT NULL,
  `last_changed_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKi6r1ve186qtv9bx7jnl2j7ulu` (`extra_option_id`),
  KEY `FKqmpf66lsf48egq3dj053hddl1` (`hotel_extra_option_id`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotel_extra_option`
--

LOCK TABLES `hotel_extra_option` WRITE;
/*!40000 ALTER TABLE `hotel_extra_option` DISABLE KEYS */;
INSERT INTO `hotel_extra_option` VALUES (1,1,1,'1111-11-11 11:11:11'),(2,2,1,'1111-11-11 11:11:11'),(3,3,1,'1111-11-11 11:11:11'),(4,4,1,'1111-11-11 11:11:11'),(5,1,2,'1111-11-11 11:11:11'),(6,2,2,'1111-11-11 11:11:11'),(7,3,2,'1111-11-11 11:11:11'),(8,1,3,'1111-11-11 11:11:11'),(9,2,3,'1111-11-11 11:11:11'),(10,3,3,'1111-11-11 11:11:11'),(11,5,3,'1111-11-11 11:11:11'),(12,6,3,'1111-11-11 11:11:11'),(13,5,4,'1111-11-11 11:11:11'),(14,2,4,'1111-11-11 11:11:11');
/*!40000 ALTER TABLE `hotel_extra_option` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `image` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `file_path` varchar(255) DEFAULT NULL,
  `hotel_id` bigint(20) DEFAULT NULL,
  `room_image_id` bigint(20) DEFAULT NULL,
  `last_changed_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnmctq4w6r7lkp880d4utoop2l` (`hotel_id`),
  KEY `FKlrrwmdg533l5lyfe73whqny4l` (`room_image_id`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
INSERT INTO `image` VALUES (8,'https://t-ec.bstatic.com/xdata/images/hotel/270x200/49498737.jpg?k=41121636f85d1fc6d72e202c2e2fa4af040194a58eb3252aee438b8dc33ef749&o=',3,NULL,'1111-11-11 11:11:11'),(6,'http://www.travelland.rs/content_pictures/resized/sivota-diamond-hotel-5-206.jpg',1,NULL,'1111-11-11 11:11:11'),(7,'http://www.telemont.me/wp-content/uploads/2012/08/splendid.jpg',2,NULL,'1111-11-11 11:11:11'),(9,'https://t-ec.bstatic.com/xdata/images/hotel/270x200/49498737.jpg?k=41121636f85d1fc6d72e202c2e2fa4af040194a58eb3252aee438b8dc33ef749&o=',5,NULL,'1111-11-11 11:11:11'),(10,'http://www.telemont.me/wp-content/uploads/2012/08/splendid.jpg',4,NULL,'1111-11-11 11:11:11'),(11,'http://www.telemont.me/wp-content/uploads/2012/08/splendid.jpg',NULL,1,'1111-11-11 11:11:11'),(12,'https://t-ec.bstatic.com/xdata/images/hotel/270x200/49498737.jpg?k=41121636f85d1fc6d72e202c2e2fa4af040194a58eb3252aee438b8dc33ef749&o=',NULL,2,'1111-11-11 11:11:11'),(13,'http://www.travelland.rs/content_pictures/resized/sivota-diamond-hotel-5-206.jpg',NULL,3,'1111-11-11 11:11:11'),(14,'https://t-ec.bstatic.com/xdata/images/hotel/270x200/49498737.jpg?k=41121636f85d1fc6d72e202c2e2fa4af040194a58eb3252aee438b8dc33ef749&o=',NULL,4,'1111-11-11 11:11:11'),(15,'http://www.travelland.rs/content_pictures/resized/sivota-diamond-hotel-5-206.jpg',NULL,5,'1111-11-11 11:11:11');
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `caption` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `opened` bit(1) NOT NULL,
  `text` varchar(255) DEFAULT NULL,
  `chat_id` bigint(20) DEFAULT NULL,
  `receiver_id` bigint(20) DEFAULT NULL,
  `sender_id` bigint(20) DEFAULT NULL,
  `last_changed_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmejd0ykokrbuekwwgd5a5xt8a` (`chat_id`),
  KEY `FK86f0kc2mt26ifwupnivu6v8oa` (`receiver_id`),
  KEY `FKcnj2qaf5yc36v2f90jw2ipl9b` (`sender_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (1,'Pitanja za smestaj','2019-01-01 00:00:00',_binary '','Da li mogu da dodjem u 3',1,3,2,'2019-01-01 00:00:00'),(2,'Pitanja za smestaj','2019-01-02 00:00:00',_binary '','Mozete',1,2,3,'2019-01-01 00:00:00'),(3,'Pitanja za smestaj','2019-01-03 00:00:00',_binary '','Hvala',1,3,2,'2019-01-01 00:00:00');
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `price_list`
--

DROP TABLE IF EXISTS `price_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `price_list` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `begin_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `hotel_price_list_id` bigint(20) DEFAULT NULL,
  `last_changed_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKf6shxjvqetbrjo53re41p1m6a` (`hotel_price_list_id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `price_list`
--

LOCK TABLES `price_list` WRITE;
/*!40000 ALTER TABLE `price_list` DISABLE KEYS */;
INSERT INTO `price_list` VALUES (1,'2019-06-01 00:00:00','2020-06-01 00:00:00',1,'2017-01-01 00:00:00'),(2,'2020-06-01 00:00:00','2021-06-01 00:00:00',1,'2017-01-01 00:00:00'),(3,'2019-06-01 00:00:00','2020-06-01 00:00:00',2,'2017-01-01 00:00:00'),(4,'2020-06-01 00:00:00','2021-06-01 00:00:00',2,'2017-01-01 00:00:00'),(5,'2019-06-01 00:00:00','2020-06-01 00:00:00',3,'2017-01-01 00:00:00'),(6,'2020-06-01 00:00:00','2021-06-01 00:00:00',3,'2017-01-01 00:00:00');
/*!40000 ALTER TABLE `price_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `privilege`
--

DROP TABLE IF EXISTS `privilege`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `privilege` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `last_changed_time` datetime NOT NULL,
  `name` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=64 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `privilege`
--

LOCK TABLES `privilege` WRITE;
/*!40000 ALTER TABLE `privilege` DISABLE KEYS */;
INSERT INTO `privilege` VALUES (1,'2019-06-25 21:48:20','getAllUsers'),(2,'2019-06-25 21:48:20','getUser'),(3,'2019-06-25 21:48:20','getUserByEmail'),(4,'2019-06-25 21:48:20','addRoleToUser'),(5,'2019-06-25 21:48:20','deleteRoleFromUser'),(6,'2019-06-25 21:48:20','getAllRoles'),(7,'2019-06-25 21:48:20','getRole'),(8,'2019-06-25 21:48:20','createRole'),(9,'2019-06-25 21:48:20','updateRole'),(10,'2019-06-25 21:48:20','deleteRole'),(11,'2019-06-25 21:48:20','addPrivilegeToRole'),(12,'2019-06-25 21:48:20','deletePrivilegeFromRole'),(13,'2019-06-25 21:48:20','getAllPrivileges'),(14,'2019-06-25 21:48:20','getPrivilege'),(15,'2019-06-25 21:48:20','createPrivilege'),(16,'2019-06-25 21:48:20','updatePrivilege'),(17,'2019-06-25 21:48:20','deletePrivilege'),(18,'2019-06-25 21:48:20','getInbox'),(19,'2019-06-25 21:48:20','getChat'),(20,'2019-06-25 21:48:20','sendMessage'),(21,'2019-06-25 21:48:20','markReadChat'),(22,'2019-06-25 21:48:20','getReview'),(23,'2019-06-25 21:48:20','getUserReviews'),(24,'2019-06-25 21:48:20','getUnreviewedReviews'),(25,'2019-06-25 21:48:20','getReviewsForRoom'),(26,'2019-06-25 21:48:20','createReview'),(27,'2019-06-25 21:48:20','updateReview'),(28,'2019-06-25 21:48:20','deleteReview'),(29,'2019-06-25 21:48:20','getChatRating'),(30,'2019-06-25 21:48:20','getRoomType'),(31,'2019-06-25 21:48:20','createAccommodationType'),(32,'2019-06-25 21:48:20','updateAccommodationType'),(33,'2019-06-25 21:48:20','removeAccommodationType'),(34,'2019-06-25 21:48:20','getHotelsAddress'),(35,'2019-06-25 21:48:20','createAddress'),(36,'2019-06-25 21:48:20','updateAddress'),(37,'2019-06-25 21:48:20','removeAddress'),(38,'2019-06-25 21:48:20','getRoomExtraOptionsWithHotelId'),(39,'2019-06-25 21:48:20','getRoomExtraOptionsWithRoomId'),(40,'2019-06-25 21:48:20','getRoomExtraOption'),(41,'2019-06-25 21:48:20','createExtraOption'),(42,'2019-06-25 21:48:20','updateRoom'),(43,'2019-06-25 21:48:20','removeRoom'),(44,'2019-06-25 21:48:20','getAllHotels'),(45,'2019-06-25 21:48:20','getHotel'),(46,'2019-06-25 21:48:20','createHotel'),(47,'2019-06-25 21:48:20','updateHotel'),(48,'2019-06-25 21:48:20','removeHotel'),(49,'2019-06-25 21:48:20','getAllReservations'),(50,'2019-06-25 21:48:20','getAllReservationsForUser'),(51,'2019-06-25 21:48:20','getReservation'),(52,'2019-06-25 21:48:20','getRoomReservations'),(53,'2019-06-25 21:48:20','getHotelReservations'),(54,'2019-06-25 21:48:20','createReservation'),(55,'2019-06-25 21:48:20','updateReservation'),(56,'2019-06-25 21:48:20','removeReservation'),(57,'2019-06-25 21:48:20','cancelReservation'),(58,'2019-06-25 21:48:20','getHotelRooms'),(59,'2019-06-25 21:48:20','getHotelRoom'),(60,'2019-06-25 21:48:20','createRoom'),(61,'2019-06-25 21:48:20','updateRoomRoom'),(62,'2019-06-25 21:48:20','removeRoomRoom'),(63,'2019-06-25 21:48:20','updateRating');
/*!40000 ALTER TABLE `privilege` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(30) NOT NULL,
  `last_changed_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_USER','0000-00-00 00:00:00'),(2,'ROLE_ADMIN','0000-00-00 00:00:00');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles_privileges`
--

DROP TABLE IF EXISTS `roles_privileges`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `roles_privileges` (
  `role_id` bigint(20) NOT NULL,
  `privilege_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`,`privilege_id`),
  KEY `FK5yjwxw2gvfyu76j3rgqwo685u` (`privilege_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles_privileges`
--

LOCK TABLES `roles_privileges` WRITE;
/*!40000 ALTER TABLE `roles_privileges` DISABLE KEYS */;
INSERT INTO `roles_privileges` VALUES (1,2),(1,3),(1,18),(1,19),(1,20),(1,21),(1,22),(1,23),(1,25),(1,26),(1,27),(1,29),(1,30),(1,34),(1,38),(1,39),(1,40),(1,44),(1,45),(1,50),(1,51),(1,52),(1,54),(1,57),(1,58),(1,59),(1,63),(2,1),(2,2),(2,3),(2,4),(2,5),(2,6),(2,7),(2,8),(2,9),(2,10),(2,11),(2,12),(2,13),(2,14),(2,15),(2,16),(2,17),(2,18),(2,19),(2,20),(2,21),(2,22),(2,23),(2,24),(2,25),(2,26),(2,27),(2,28),(2,29),(2,30),(2,31),(2,32),(2,33),(2,34),(2,35),(2,36),(2,37),(2,38),(2,39),(2,40),(2,41),(2,42),(2,43),(2,44),(2,45),(2,46),(2,47),(2,48),(2,49),(2,50),(2,51),(2,52),(2,53),(2,54),(2,55),(2,56),(2,58),(2,59),(2,60),(2,61),(2,62),(2,63),(3,2),(3,3),(3,18),(3,19),(3,20),(3,21),(3,22),(3,23),(3,24),(3,25),(3,28),(3,29),(3,30),(3,31),(3,32),(3,33),(3,34),(3,35),(3,36),(3,37),(3,38),(3,39),(3,40),(3,41),(3,42),(3,43),(3,44),(3,45),(3,49),(3,51),(3,52),(3,53),(3,54),(3,55),(3,56),(3,58),(3,59),(3,60),(3,61),(3,62),(3,63);
/*!40000 ALTER TABLE `roles_privileges` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `room` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cancellation_allowed` bit(1) NOT NULL,
  `cancellation_days` int(11) NOT NULL,
  `capacity` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `number_of_beds` int(11) NOT NULL,
  `accomodation_type_id` bigint(20) DEFAULT NULL,
  `rooms_hotel_id` bigint(20) DEFAULT NULL,
  `currently_price` double NOT NULL,
  `last_changed_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK80l4i82ipedefawxe84fh5i08` (`accomodation_type_id`),
  KEY `FKtnc2btlt1xb5qlql64o86r7sf` (`rooms_hotel_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (1,_binary '',5,5,'Budite u nasem najlepsem hotelu u evropi',5,1,1,200,'1111-11-11 11:11:11'),(2,_binary '',5,5,'Prirodan vazduh i opustajuca atomosfera u okolini',5,2,1,600,'1111-11-11 11:11:11'),(3,_binary '',2,3,'Najbolji smestaji za cenu koju trazimo',5,3,2,700,'1111-11-11 11:11:11'),(4,_binary '',3,4,'Jednostavno najbolje mesto za zurke',4,4,4,800,'1111-11-11 11:11:11'),(5,_binary '\0',0,6,'Savrseno za bracne parove',4,2,3,750,'1111-11-11 11:11:11');
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_reservation`
--

DROP TABLE IF EXISTS `room_reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `room_reservation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `begin_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `price` decimal(19,2) DEFAULT NULL,
  `realised` bit(1) NOT NULL,
  `room_reservation_id` bigint(20) DEFAULT NULL,
  `user_review_id` bigint(20) DEFAULT NULL,
  `users_reservation_id` bigint(20) DEFAULT NULL,
  `last_changed_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6dq3w3lmmmg7ju4pnf427quu7` (`room_reservation_id`),
  KEY `FKtm6wlkvl6xm9xph27afb1r1ku` (`user_review_id`),
  KEY `FK8cby3h2f54kex06d3octwnau1` (`users_reservation_id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_reservation`
--

LOCK TABLES `room_reservation` WRITE;
/*!40000 ALTER TABLE `room_reservation` DISABLE KEYS */;
INSERT INTO `room_reservation` VALUES (1,'2017-11-15 00:00:00','2017-11-25 00:00:00',500.00,_binary '',1,1,2,'1111-11-11 11:11:11'),(2,'2017-10-15 00:00:00','2017-11-25 00:00:00',200.00,_binary '',2,NULL,2,'1111-11-11 11:11:11'),(3,'2019-10-10 00:00:00','2019-10-20 00:00:00',1500.00,_binary '\0',3,NULL,2,'1111-11-11 11:11:11'),(4,'2019-10-10 00:00:00','2019-10-30 00:00:00',2500.00,_binary '\0',3,NULL,NULL,'1111-11-11 11:11:11'),(5,'2019-11-15 00:00:00','2019-11-25 00:00:00',3500.00,_binary '\0',4,NULL,NULL,'1111-11-11 11:11:11'),(6,'2019-06-26 00:00:00','2019-06-28 00:00:00',0.00,_binary '\0',5,NULL,2,'2019-06-26 16:34:43'),(7,'2019-06-27 00:00:00','2019-06-28 00:00:00',0.00,_binary '\0',3,NULL,2,'2019-06-26 17:57:06'),(8,'2019-07-11 00:00:00','2019-09-15 00:00:00',0.00,_binary '\0',3,NULL,6,'2019-06-26 18:04:41');
/*!40000 ALTER TABLE `room_reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unit_price_information`
--

DROP TABLE IF EXISTS `unit_price_information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `unit_price_information` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `price_id` bigint(20) DEFAULT NULL,
  `price_list_id` bigint(20) DEFAULT NULL,
  `room_id` bigint(20) DEFAULT NULL,
  `last_changed_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqr6h7nrylorh4i95g3p1jr7hm` (`price_id`),
  KEY `FKa7u9qafsf1atjg7w9oo31p50q` (`price_list_id`),
  KEY `FK9i62l0ix6fe4hf7f9i5xasbam` (`room_id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unit_price_information`
--

LOCK TABLES `unit_price_information` WRITE;
/*!40000 ALTER TABLE `unit_price_information` DISABLE KEYS */;
INSERT INTO `unit_price_information` VALUES (1,1,1,1,'2017-01-01 00:00:00'),(2,2,2,1,'2017-01-01 00:00:00'),(3,3,3,2,'2017-01-01 00:00:00'),(4,4,4,3,'2017-01-01 00:00:00'),(5,5,3,4,'2017-01-01 00:00:00'),(6,6,5,5,'2017-01-01 00:00:00');
/*!40000 ALTER TABLE `unit_price_information` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(60) NOT NULL,
  `failed_attempts` int(11) NOT NULL DEFAULT '0',
  `failed_date_attempt` datetime DEFAULT NULL,
  `last_name` varchar(30) NOT NULL,
  `name` varchar(30) NOT NULL,
  `password` varchar(255) NOT NULL,
  `salt` varchar(255) NOT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `active` bit(1) NOT NULL,
  `last_changed_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKn82ha3ccdebhokx3a8fgdqeyy` (`role_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'s.bokic@yahoo.com',0,NULL,'bokic','stefan','AjLAcUiNLnUfi0H4yXbrE9/PqCQAerWP','oSo0UHjkPRZL4qE0WfJVEQ==',2,_binary '\0','0000-00-00 00:00:00'),(2,'andrija@gmail.com',0,NULL,'cvejic','andrija','Qa5mzjfzpusizh3JURiBDjbsNjOIleYq','66x2x3KZNB0MF6YRc5XIYw==',2,_binary '\0','0000-00-00 00:00:00');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_review`
--

DROP TABLE IF EXISTS `user_review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_review` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `comment` varchar(255) DEFAULT NULL,
  `rating` int(11) NOT NULL,
  `time_stamp` datetime DEFAULT NULL,
  `room_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `approved` bit(1) NOT NULL,
  `last_changed_time` datetime NOT NULL,
  `room_reservation_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKedv0q78kn4goiywahm7hqehr` (`room_id`),
  KEY `FKk4378yigvs29qpwh8ughgs4gk` (`user_id`),
  KEY `FKha3pme57ltquuxc07spwknkdj` (`room_reservation_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_review`
--

LOCK TABLES `user_review` WRITE;
/*!40000 ALTER TABLE `user_review` DISABLE KEYS */;
INSERT INTO `user_review` VALUES (1,'Super bilo',5,'2017-01-01 00:00:00',1,2,_binary '','2017-01-01 00:00:00',NULL),(2,'Moglo je biti bolje na bazenima',5,'2018-02-02 00:00:00',1,2,_binary '\0','2018-01-01 00:00:00',NULL);
/*!40000 ALTER TABLE `user_review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `users_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKt4v0rrweyk393bdgt107vdx0x` (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_roles`
--

LOCK TABLES `users_roles` WRITE;
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
INSERT INTO `users_roles` VALUES (1,2),(2,1),(3,3),(4,3),(5,1),(6,1);
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-26 21:02:43
