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
  `last_changed_time` datetime NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accomodation_type`
--

LOCK TABLES `accomodation_type` WRITE;
/*!40000 ALTER TABLE `accomodation_type` DISABLE KEYS */;
INSERT INTO `accomodation_type` VALUES (1,'2019-06-26 20:01:56','bazen'),(2,'2019-06-26 20:01:56','klima'),(3,'2019-06-26 20:01:56','pogled'),(4,'2019-06-26 20:01:56','kuhinja'),(5,'2019-06-26 20:01:56','kablovska'),(6,'2019-06-26 20:01:56','nesto'),(7,'2019-06-26 20:01:56','dosta');
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
  `last_changed_time` datetime NOT NULL,
  `street` varchar(255) DEFAULT NULL,
  `street_number` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'Maglic',10,10,'Srbija','2019-06-26 20:01:56','marsala tita',88),(2,'Novi sad',15,15,'Srbija','2019-06-26 20:01:56','bulevar evrope',55),(3,'Novi sad',20,20,'Srbija','2019-06-26 20:01:56','bulevar oslobodjenja',45),(4,'Beograd',30,30,'Srbija','2019-06-26 20:01:56','beogradska ulica',12), (5,'Novi grad',17,17,'Srbija','2019-06-26 20:01:56','Neka ulica',19);
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
  `last_changed_time` datetime NOT NULL,
  `price` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `amount_type`
--

LOCK TABLES `amount_type` WRITE;
/*!40000 ALTER TABLE `amount_type` DISABLE KEYS */;
INSERT INTO `amount_type` VALUES (1,'RSD','2017-01-01 00:00:00',100.00),(2,'RSD','2017-01-01 00:00:00',200.00),(3,'RSD','2017-01-01 00:00:00',300.00),(4,'RSD','2017-01-01 00:00:00',400.00),(5,'RSD','2017-01-01 00:00:00',500.00),(6,'RSD','2017-01-01 00:00:00',600.00),(7,'RSD','2017-01-01 00:00:00',700.00),(8,'RSD','2017-01-01 00:00:00',800.00),(9,'RSD','2017-01-01 00:00:00',900.00);
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
  `last_changed_time` datetime NOT NULL,
  `chats_hotel_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7e3dqo8ld4mww7wes9de4h7ju` (`chats_hotel_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat`
--

LOCK TABLES `chat` WRITE;
/*!40000 ALTER TABLE `chat` DISABLE KEYS */;
INSERT INTO `chat` VALUES (1,'2017-01-01 00:00:00',1),(2,'2017-01-01 00:00:00',2);
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
  `last_changed_time` datetime NOT NULL,
  `amount_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2b22b50sx5ep460wfreubsc1m` (`amount_id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `currency_price`
--

LOCK TABLES `currency_price` WRITE;
/*!40000 ALTER TABLE `currency_price` DISABLE KEYS */;
INSERT INTO `currency_price` VALUES (1,'2017-01-01 00:00:00',1),(2,'2017-01-01 00:00:00',2),(3,'2017-01-01 00:00:00',3),(4,'2017-01-01 00:00:00',4),(5,'2017-01-01 00:00:00',5),(6,'2017-01-01 00:00:00',6),(7,'2017-01-01 00:00:00',7),(8,'2017-01-01 00:00:00',8),(9,'2017-01-01 00:00:00',9);
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
  `last_changed_time` datetime NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `extra_option`
--

LOCK TABLES `extra_option` WRITE;
/*!40000 ALTER TABLE `extra_option` DISABLE KEYS */;
INSERT INTO `extra_option` VALUES (1,'2017-01-01 00:00:00','ekstra opcija 1'),(2,'2017-01-01 00:00:00','opcija 2'),(3,'2017-01-01 00:00:00','masaza'),(4,'2017-01-01 00:00:00','kupka'),(5,'2017-01-01 00:00:00','topla voda'),(6,'2017-01-01 00:00:00','nema vise');
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
  `last_changed_time` datetime DEFAULT NULL,
  `rating` double NOT NULL,
  `address_id` bigint(20) DEFAULT NULL,
  `users_hotel_id` bigint(20) DEFAULT NULL,
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
INSERT INTO `hotel` VALUES (1,'2017-01-01 00:00:00',3,1,3),(2,'2017-01-01 00:00:00',4,2,4),(3,'2017-01-01 00:00:00',5,3,3),(4,'2017-01-01 00:00:00',2,4,3),(5,'2017-01-01 00:00:00',1,5,3);
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
  `last_changed_time` datetime NOT NULL,
  `extra_option_id` bigint(20) DEFAULT NULL,
  `hotel_extra_option_id` bigint(20) DEFAULT NULL,
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
INSERT INTO `hotel_extra_option` VALUES (1,'2017-01-01 00:00:00',1,1),(2,'2017-01-01 00:00:00',2,1),(3,'2017-01-01 00:00:00',3,1),(4,'2017-01-01 00:00:00',4,1),(5,'2017-01-01 00:00:00',5,1),(6,'2017-01-01 00:00:00',6,1),(7,'2017-01-01 00:00:00',1,2),(8,'2017-01-01 00:00:00',2,2),(9,'2017-01-01 00:00:00',3,2),(10,'2017-01-01 00:00:00',4,3),(11,'2017-01-01 00:00:00',5,3),(12,'2017-01-01 00:00:00',6,3),(13,'2017-01-01 00:00:00',1,4),(14,'2017-01-01 00:00:00',2,4);
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
  `last_changed_time` datetime NOT NULL,
  `hotel_id` bigint(20) DEFAULT NULL,
  `room_image_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnmctq4w6r7lkp880d4utoop2l` (`hotel_id`),
  KEY `FKlrrwmdg533l5lyfe73whqny4l` (`room_image_id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
INSERT INTO `image` VALUES (1,'https://t-ec.bstatic.com/xdata/images/hotel/270x200/49498737.jpg?k=41121636f85d1fc6d72e202c2e2fa4af040194a58eb3252aee438b8dc33ef749&o=','2017-01-01 00:00:00',1,NULL),(2,'http://www.telemont.me/wp-content/uploads/2012/08/splendid.jpg','2017-01-01 00:00:00',2,NULL),(3,'https://t-ec.bstatic.com/xdata/images/hotel/270x200/49498737.jpg?k=41121636f85d1fc6d72e202c2e2fa4af040194a58eb3252aee438b8dc33ef749&o=','2017-01-01 00:00:00',3,NULL),(4,'http://www.telemont.me/wp-content/uploads/2012/08/splendid.jpg','2017-01-01 00:00:00',4,NULL),(5,'https://t-ec.bstatic.com/xdata/images/hotel/270x200/49498737.jpg?k=41121636f85d1fc6d72e202c2e2fa4af040194a58eb3252aee438b8dc33ef749&o=','2017-01-01 00:00:00',5,NULL),(6,'http://www.travelland.rs/content_pictures/resized/sivota-diamond-hotel-5-206.jpg','2017-01-01 00:00:00',NULL,1),(7,'https://t-ec.bstatic.com/xdata/images/hotel/270x200/49498737.jpg?k=41121636f85d1fc6d72e202c2e2fa4af040194a58eb3252aee438b8dc33ef749&o=','2017-01-01 00:00:00',NULL,2),(8,'http://www.travelland.rs/content_pictures/resized/sivota-diamond-hotel-5-206.jpg','2017-01-01 00:00:00',NULL,3),(9,'http://www.telemont.me/wp-content/uploads/2012/08/splendid.jpg','2017-01-01 00:00:00',NULL,4),(10,'https://t-ec.bstatic.com/xdata/images/hotel/270x200/49498737.jpg?k=41121636f85d1fc6d72e202c2e2fa4af040194a58eb3252aee438b8dc33ef749&o=','2017-01-01 00:00:00',NULL,5);
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
  `last_changed_time` datetime NOT NULL,
  `opened` bit(1) NOT NULL,
  `text` varchar(255) DEFAULT NULL,
  `chat_id` bigint(20) DEFAULT NULL,
  `receiver_id` bigint(20) DEFAULT NULL,
  `sender_id` bigint(20) DEFAULT NULL,
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
INSERT INTO `message` VALUES (1,'Pitanja za smestaj','2019-01-02 00:00:00','2019-01-02 00:00:00',_binary '','Da li mogu da dodjem u 3',1,3,2),(2,'Pitanja za smestaj','2019-01-02 00:00:00','2019-01-02 00:00:00',_binary '\0','Mozete',1,2,3),(3,'Pitanja za smestaj','2019-01-02 00:00:00','2019-01-02 00:00:00',_binary '','Hvala',1,3,2);
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
  `last_changed_time` datetime NOT NULL,
  `hotel_price_list_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKf6shxjvqetbrjo53re41p1m6a` (`hotel_price_list_id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `price_list`
--

LOCK TABLES `price_list` WRITE;
/*!40000 ALTER TABLE `price_list` DISABLE KEYS */;
INSERT INTO `price_list` VALUES (1,'2019-06-01 00:00:00','2020-06-01 00:00:00','2017-01-01 00:00:00',1),(2,'2019-06-01 00:00:00','2020-06-01 00:00:00','2017-01-01 00:00:00',1),(3,'2019-06-01 00:00:00','2020-06-01 00:00:00','2017-01-01 00:00:00',2),(4,'2019-06-01 00:00:00','2020-06-01 00:00:00','2017-01-01 00:00:00',2),(5,'2019-06-01 00:00:00','2020-06-01 00:00:00','2017-01-01 00:00:00',3),(6,'2019-06-01 00:00:00','2020-06-01 00:00:00','2017-01-01 00:00:00',3);
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
INSERT INTO `privilege` VALUES (1,'2019-06-26 20:01:55','getAllUsers'),(2,'2019-06-26 20:01:55','getUser'),(3,'2019-06-26 20:01:55','getUserByEmail'),(4,'2019-06-26 20:01:55','addRoleToUser'),(5,'2019-06-26 20:01:55','deleteRoleFromUser'),(6,'2019-06-26 20:01:55','getAllRoles'),(7,'2019-06-26 20:01:55','getRole'),(8,'2019-06-26 20:01:55','createRole'),(9,'2019-06-26 20:01:55','updateRole'),(10,'2019-06-26 20:01:55','deleteRole'),(11,'2019-06-26 20:01:55','addPrivilegeToRole'),(12,'2019-06-26 20:01:55','deletePrivilegeFromRole'),(13,'2019-06-26 20:01:55','getAllPrivileges'),(14,'2019-06-26 20:01:55','getPrivilege'),(15,'2019-06-26 20:01:55','createPrivilege'),(16,'2019-06-26 20:01:55','updatePrivilege'),(17,'2019-06-26 20:01:55','deletePrivilege'),(18,'2019-06-26 20:01:55','getInbox'),(19,'2019-06-26 20:01:55','getChat'),(20,'2019-06-26 20:01:55','sendMessage'),(21,'2019-06-26 20:01:55','markReadChat'),(22,'2019-06-26 20:01:55','getReview'),(23,'2019-06-26 20:01:55','getUserReviews'),(24,'2019-06-26 20:01:55','getUnreviewedReviews'),(25,'2019-06-26 20:01:55','getReviewsForRoom'),(26,'2019-06-26 20:01:55','createReview'),(27,'2019-06-26 20:01:55','updateReview'),(28,'2019-06-26 20:01:55','deleteReview'),(29,'2019-06-26 20:01:55','getChatRating'),(30,'2019-06-26 20:01:55','getRoomType'),(31,'2019-06-26 20:01:55','createAccommodationType'),(32,'2019-06-26 20:01:55','updateAccommodationType'),(33,'2019-06-26 20:01:55','removeAccommodationType'),(34,'2019-06-26 20:01:55','getHotelsAddress'),(35,'2019-06-26 20:01:55','createAddress'),(36,'2019-06-26 20:01:55','updateAddress'),(37,'2019-06-26 20:01:55','removeAddress'),(38,'2019-06-26 20:01:55','getRoomExtraOptionsWithHotelId'),(39,'2019-06-26 20:01:55','getRoomExtraOptionsWithRoomId'),(40,'2019-06-26 20:01:55','getRoomExtraOption'),(41,'2019-06-26 20:01:55','createExtraOption'),(42,'2019-06-26 20:01:55','updateRoom'),(43,'2019-06-26 20:01:55','removeRoom'),(44,'2019-06-26 20:01:55','getAllHotels'),(45,'2019-06-26 20:01:55','getHotel'),(46,'2019-06-26 20:01:55','createHotel'),(47,'2019-06-26 20:01:55','updateHotel'),(48,'2019-06-26 20:01:55','removeHotel'),(49,'2019-06-26 20:01:55','getAllReservations'),(50,'2019-06-26 20:01:55','getAllReservationsForUser'),(51,'2019-06-26 20:01:55','getReservation'),(52,'2019-06-26 20:01:55','getRoomReservations'),(53,'2019-06-26 20:01:55','getHotelReservations'),(54,'2019-06-26 20:01:55','createReservation'),(55,'2019-06-26 20:01:55','updateReservation'),(56,'2019-06-26 20:01:55','removeReservation'),(57,'2019-06-26 20:01:55','cancelReservation'),(58,'2019-06-26 20:01:55','getHotelRooms'),(59,'2019-06-26 20:01:55','getHotelRoom'),(60,'2019-06-26 20:01:55','createRoom'),(61,'2019-06-26 20:01:55','updateRoomRoom'),(62,'2019-06-26 20:01:55','removeRoomRoom'),(63,'2019-06-26 20:01:55','updateRating');
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
  `last_changed_time` datetime NOT NULL,
  `role_name` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'2019-06-26 20:01:56','ROLE_LOGGED'),(2,'2019-06-26 20:01:56','ROLE_ADMIN'),(3,'2019-06-26 20:01:56','ROLE_AGENT');
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
  `currently_price` double NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `last_changed_time` datetime NOT NULL,
  `number_of_beds` int(11) NOT NULL,
  `accomodation_type_id` bigint(20) DEFAULT NULL,
  `rooms_hotel_id` bigint(20) DEFAULT NULL,
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
INSERT INTO `room` VALUES (1,_binary '',5,5,200,'Budite u nasem najlepsem hotelu u evropi','1111-11-11 11:11:11',5,1,1),(2,_binary '',2,2,300,'Prirodan vazduh i opustajuca atomosfera u okolini','1111-11-11 11:11:11',2,2,1),(3,_binary '\0',0,4,400,'Najbolji smestaji za cenu koju trazimo\'','1111-11-11 11:11:11',4,3,2),(4,_binary '\0',0,3,500,'Jednostavno najbolje mesto za zurke','1111-11-11 11:11:11',3,4,4),(5,_binary '',4,4,600,'Savrseno za bracne parove','1111-11-11 11:11:11',4,2,3);
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
  `begin_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `last_changed_time` datetime NOT NULL,
  `price` decimal(19,2) DEFAULT NULL,
  `realised` bit(1) NOT NULL,
  `room_reservation_id` bigint(20) DEFAULT NULL,
  `user_review_id` bigint(20) DEFAULT NULL,
  `users_reservation_id` bigint(20) DEFAULT NULL,
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
INSERT INTO `room_reservation` VALUES (1,'2019-10-10','2019-10-30','2017-11-15 00:00:00',1.00,_binary '',1,1,2),(2,'2019-10-10','2019-11-25','2017-11-15 00:00:00',2.00,_binary '',2,NULL,2),(3,'2019-10-10','2019-11-25','2017-11-15 00:00:00',3.00,_binary '\0',3,NULL,2),(4,'2019-10-10','2019-11-25','2017-11-15 00:00:00',3.00,_binary '\0',3,NULL,2),(5,'2019-10-10','2019-11-25','2017-11-15 00:00:00',4.00,_binary '\0',4,NULL,2),(6,'2019-10-10','2019-11-25','2017-11-15 00:00:00',5.00,_binary '\0',5,NULL,2),(7,'2019-10-10','2019-11-25','2017-11-15 00:00:00',3.00,_binary '\0',3,NULL,2),(8,'2019-10-10','2019-11-25','2017-11-15 00:00:00',3.00,_binary '\0',3,NULL,2);
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
  `last_changed_time` datetime NOT NULL,
  `price_id` bigint(20) DEFAULT NULL,
  `price_list_id` bigint(20) DEFAULT NULL,
  `room_id` bigint(20) DEFAULT NULL,
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
INSERT INTO `unit_price_information` VALUES (1,'2017-01-01 00:00:00',1,1,1),(2,'2017-01-01 00:00:00',2,2,2),(3,'2017-01-01 00:00:00',3,3,2),(4,'2017-01-01 00:00:00',4,4,3),(5,'2017-01-01 00:00:00',5,3,4),(6,'2017-01-01 00:00:00',6,5,5);
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
  `active` bit(1) NOT NULL,
  `email` varchar(60) NOT NULL,
  `last_changed_time` datetime NOT NULL,
  `last_name` varchar(30) NOT NULL,
  `name` varchar(30) NOT NULL,
  `password` varchar(255) NOT NULL,
  `salt` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES 
(1,_binary '','s.bokic@yahoo.com','2019-06-26 20:01:56','bokic','stefan','uzLdXi4KJCMBIh15mFV8a4bbWE1u36Da','Okwtg72MiybJ2npowE0Udg=='),(2,_binary '','andrija@gmail.com','2019-06-26 20:01:56','cvejic','andrija','uzLdXi4KJCMBIh15mFV8a4bbWE1u36Da','Okwtg72MiybJ2npowE0Udg=='),(3,_binary '','katarina@gmail.com','2019-06-26 20:01:56','Grujic','Katarina-Glorija','uzLdXi4KJCMBIh15mFV8a4bbWE1u36Da','Okwtg72MiybJ2npowE0Udg=='),(4,_binary '','marko@gmail.com','2019-06-26 20:01:56','Markovic','Marko','uzLdXi4KJCMBIh15mFV8a4bbWE1u36Da','Okwtg72MiybJ2npowE0Udg==');
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
  `approved` bit(1) NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `last_changed_time` datetime NOT NULL,
  `rating` int(11) NOT NULL,
  `time_stamp` datetime DEFAULT NULL,
  `room_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `room_reservation_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKedv0q78kn4goiywahm7hqehr` (`room_id`),
  KEY `FKha3pme57ltquuxc07spwknkdj` (`room_reservation_id`),
  KEY `FKk4378yigvs29qpwh8ughgs4gk` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_review`
--

LOCK TABLES `user_review` WRITE;
/*!40000 ALTER TABLE `user_review` DISABLE KEYS */;
INSERT INTO `user_review` VALUES (1,_binary '','Super bilo','2018-02-02 00:00:00',5,'2018-02-02 00:00:00',1,2,1),(2,_binary '\0','Moglo je biti bolje na bazenima','2018-02-02 00:00:00',2,'2018-02-02 00:00:00',1,2,2);
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
INSERT INTO `users_roles` VALUES (1,2),(2,1),(3,3),(4,3);
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

-- Dump completed on 2019-06-26 23:10:34
