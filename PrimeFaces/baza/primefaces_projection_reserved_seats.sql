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
-- Table structure for table `projection_reserved_seats`
--

DROP TABLE IF EXISTS `projection_reserved_seats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `projection_reserved_seats` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `projection_id` int(11) NOT NULL,
  `cinema_seats_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `prs_projection_fk_idx` (`projection_id`),
  KEY `prs_cinema_seats_fk_idx` (`cinema_seats_id`),
  KEY `prs_user_fk_idx` (`user_id`),
  CONSTRAINT `prs_cinema_seats_fk` FOREIGN KEY (`cinema_seats_id`) REFERENCES `cinema_seats` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `prs_projection_fk` FOREIGN KEY (`projection_id`) REFERENCES `projection` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projection_reserved_seats`
--

LOCK TABLES `projection_reserved_seats` WRITE;
/*!40000 ALTER TABLE `projection_reserved_seats` DISABLE KEYS */;
INSERT INTO `projection_reserved_seats` VALUES (1,'2015-04-27 16:32:31',12,11,3),(2,'2015-04-27 16:32:31',12,12,5),(3,'2015-04-27 16:32:31',12,15,6),(4,'2015-04-27 17:52:57',12,16,3),(5,'2015-04-27 18:17:26',12,17,7),(6,'2015-04-27 18:20:12',13,6,7),(7,'2015-04-27 18:20:12',13,9,7),(8,'2015-04-27 20:25:30',12,14,7),(9,'2015-04-29 15:32:27',13,8,7);
/*!40000 ALTER TABLE `projection_reserved_seats` ENABLE KEYS */;
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
