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
-- Table structure for table `user_movie_review`
--

DROP TABLE IF EXISTS `user_movie_review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_movie_review` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` int(11) NOT NULL,
  `movie_id` int(11) NOT NULL,
  `review` varchar(5000) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `mur_movie_fk_idx` (`movie_id`),
  KEY `mur_user_fk_idx` (`user_id`),
  CONSTRAINT `umre_movie_fk` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `umre_user_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_movie_review`
--

LOCK TABLES `user_movie_review` WRITE;
/*!40000 ALTER TABLE `user_movie_review` DISABLE KEYS */;
INSERT INTO `user_movie_review` VALUES (1,'2015-04-28 11:21:41',7,38,'test'),(2,'2015-04-28 14:25:16',7,35,'disabled=\"#{movieMB.reviewDisabled}\"disabled=\"#{movieMB.reviewDisabled}\"disabled=\"#{movieMB.reviewDisabled}\"disabled=\"#{movieMB.reviewDisabled}\"disabled=\"#{movieMB.reviewDisabled}\"disabled=\"#{movieMB.reviewDisabled}\"disabled=\"#{movieMB.reviewDisabled}\"disabled=\"#{movieMB.reviewDisabled}\"disabled=\"#{movieMB.reviewDisabled}\"disabled=\"#{movieMB.reviewDisabled}\"disabled=\"#{movieMB.reviewDisabled}\"disabled=\"#{movieMB.reviewDisabled}\"disabled=\"#{movieMB.reviewDisabled}\"disabled=\"#{movieMB.reviewDisabled}\"disabled=\"#{movieMB.reviewDisabled}\"disabled=\"#{movieMB.reviewDisabled}\"disabled=\"#{movieMB.reviewDisabled}\"disabled=\"#{movieMB.reviewDisabled}\"disabled=\"#{movieMB.reviewDisabled}\"disabled=\"#{movieMB.reviewDisabled}\"disabled=\"#{movieMB.reviewDisabled}\"disabled=\"#{movieMB.reviewDisabled}\"disabled=\"#{movieMB.reviewDisabled}\"disabled=\"#{movieMB.reviewDisabled}\"disabled=\"#{movieMB.reviewDisabled}\"disabled=\"#{movieMB.reviewDisabled}\"disabled=\"#{movieMB.reviewDisabled}\"'),(3,'2015-04-28 15:48:49',7,33,'test'),(4,'2015-04-28 15:50:22',7,32,'test'),(5,'2015-04-28 16:04:02',3,38,'test4aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaatest4aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaatest4aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaatest4aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaatest4aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa'),(6,'2015-04-28 16:04:02',5,38,'test3test4aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaatest4aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaatest4aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaatest4aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa'),(7,'2015-04-28 16:04:02',6,38,'test4aaaaaaaaaaaaaaaaaaaaaaaaaaaaatest4aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaatest4aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaatest4aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaatest4aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaatest4aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaatest4aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaatest4aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaatest4aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaatest4aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa'),(9,'2015-04-28 16:25:23',7,36,'test2'),(10,'2015-04-29 08:52:06',7,34,'test new date'),(11,'2015-04-29 10:02:12',7,42,'testetsafdfasf'),(12,'2015-05-02 14:03:06',7,43,'Prva recenzija');
/*!40000 ALTER TABLE `user_movie_review` ENABLE KEYS */;
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
