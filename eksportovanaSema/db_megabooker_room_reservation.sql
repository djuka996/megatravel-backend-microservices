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
  PRIMARY KEY (`id`),
  KEY `FK6dq3w3lmmmg7ju4pnf427quu7` (`room_reservation_id`),
  KEY `FKtm6wlkvl6xm9xph27afb1r1ku` (`user_review_id`),
  KEY `FK8cby3h2f54kex06d3octwnau1` (`users_reservation_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_reservation`
--

LOCK TABLES `room_reservation` WRITE;
/*!40000 ALTER TABLE `room_reservation` DISABLE KEYS */;
INSERT INTO `room_reservation` VALUES (1,'2017-11-15 00:00:00','2017-11-25 00:00:00',500.00,_binary '',1,NULL,NULL),(2,'2017-10-15 00:00:00','2017-11-25 00:00:00',200.00,_binary '',1,NULL,NULL),(3,'2019-10-10 00:00:00','2019-10-20 00:00:00',1500.00,_binary '\0',2,NULL,NULL),(4,'2019-10-10 00:00:00','2019-10-30 00:00:00',2500.00,_binary '\0',3,NULL,NULL),(5,'2019-11-15 00:00:00','2019-11-25 00:00:00',3500.00,_binary '\0',4,NULL,NULL);
/*!40000 ALTER TABLE `room_reservation` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-02 20:46:55
