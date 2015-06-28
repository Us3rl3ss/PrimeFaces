CREATE DATABASE  IF NOT EXISTS `primefaces` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `primefaces`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: primefaces
-- ------------------------------------------------------
-- Server version	5.6.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cinema_seats`
--

DROP TABLE IF EXISTS `cinema_seats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cinema_seats` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `cinema_id` int(11) NOT NULL,
  `seats_row` varchar(10) NOT NULL,
  `seats_number` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `cs_cinema_fk_idx` (`cinema_id`),
  CONSTRAINT `cs_cinema_fk` FOREIGN KEY (`cinema_id`) REFERENCES `cinema` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cinema_seats`
--

LOCK TABLES `cinema_seats` WRITE;
/*!40000 ALTER TABLE `cinema_seats` DISABLE KEYS */;
INSERT INTO `cinema_seats` VALUES (5,'2015-04-27 12:29:13',23,'A',1),(6,'2015-04-27 12:29:13',23,'A',2),(7,'2015-04-27 12:29:13',23,'A',3),(8,'2015-04-27 12:29:13',23,'A',4),(9,'2015-04-27 12:29:13',23,'A',5),(10,'2015-04-27 12:29:13',23,'A',6),(11,'2015-04-27 12:32:18',24,'B',1),(12,'2015-04-27 12:32:18',24,'B',2),(13,'2015-04-27 12:32:18',24,'B',3),(14,'2015-04-27 12:32:19',24,'B',4),(15,'2015-04-27 12:32:19',24,'B',5),(16,'2015-04-27 12:32:19',24,'B',6),(17,'2015-04-27 12:32:19',24,'B',7);
/*!40000 ALTER TABLE `cinema_seats` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-05-02 17:11:58
