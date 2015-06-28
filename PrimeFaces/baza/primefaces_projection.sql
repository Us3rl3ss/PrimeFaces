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
-- Table structure for table `projection`
--

DROP TABLE IF EXISTS `projection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `projection` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `theater_id` int(11) NOT NULL,
  `cinema_id` int(11) NOT NULL,
  `movie_id` int(11) NOT NULL,
  `date` date NOT NULL,
  `start_time` varchar(50) NOT NULL,
  `end_time` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `p_cineam_fk_idx` (`cinema_id`),
  KEY `p_movie_fk_idx` (`movie_id`),
  KEY `p_theater_fk_idx` (`theater_id`),
  CONSTRAINT `p_cinema_fk` FOREIGN KEY (`cinema_id`) REFERENCES `cinema` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `p_movie_fk` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `p_theater_fk` FOREIGN KEY (`theater_id`) REFERENCES `theater` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projection`
--

LOCK TABLES `projection` WRITE;
/*!40000 ALTER TABLE `projection` DISABLE KEYS */;
INSERT INTO `projection` VALUES (12,'2015-04-27 10:56:28',18,24,34,'2015-04-02','Thu Jan 01 08:00:00 CET 1970','Thu Jan 01 10:00:00 CET 1970'),(13,'2015-04-27 11:10:33',17,23,40,'2015-04-01','Thu Jan 01 05:00:00 CET 1970','Thu Jan 01 08:00:00 CET 1970'),(14,'2015-04-27 11:11:11',17,23,38,'2015-04-02','Thu Jan 01 06:00:00 CET 1970','Thu Jan 01 09:00:00 CET 1970'),(15,'2015-04-27 11:11:27',17,24,39,'2015-04-09','Thu Jan 01 10:00:00 CET 1970','Thu Jan 01 12:00:00 CET 1970'),(16,'2015-04-27 12:14:50',17,23,40,'2015-04-16','Thu Jan 01 06:08:00 CET 1970','Thu Jan 01 09:00:00 CET 1970');
/*!40000 ALTER TABLE `projection` ENABLE KEYS */;
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
