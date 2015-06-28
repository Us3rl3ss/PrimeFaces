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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `password` varchar(30) NOT NULL,
  `email` varchar(45) NOT NULL,
  `date_of_birth` date NOT NULL,
  `role_id` int(11) NOT NULL DEFAULT '2',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  KEY `role_fk_idx` (`role_id`),
  CONSTRAINT `us_role_fk` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','admin','admin','admin','admin','1990-11-11',1,'2015-02-21 23:02:44',NULL),(3,'test','test','test','test','test','1990-11-11',2,'2015-02-21 23:03:33',NULL),(5,'fdas','fdas','fdas','dsfa','fdas','1990-11-11',2,'2015-02-21 23:59:31',NULL),(6,'testasfa','test','test','tesfas','fdas','2015-02-04',2,'2015-02-22 00:04:53',NULL),(7,'root','Matija','Špendi?','root','fdsa','2015-02-09',2,'2015-02-22 00:06:35','2015-04-29 10:30:30'),(15,'roota','fdsaf','asdfdasf','','fdasf','2015-02-02',2,'2015-02-22 00:12:59',NULL),(16,'gdas','test','tsfa','gasdfda','fdasf','2015-02-11',2,'2015-02-22 00:46:43',NULL),(18,'rootgsdf','gdasfa','gsadf','gsdaf','gsda','2015-02-03',2,'2015-02-22 01:11:32',NULL),(19,'fsadfasg','fdsafsda13432','gasdfa','fdasgasd','fdsafas','2015-02-05',2,'2015-02-22 10:37:52',NULL),(22,'hjkhjk','hjkhjhkj','hjkhj','hjkhj','khjkh','2015-03-03',2,'2015-03-01 17:39:38',NULL),(23,'lkjfhgfdsf','jfd','khjg','dsadh','jgfk','2015-03-03',2,'2015-03-30 23:16:35',NULL),(24,'matija.spendic','Matija','Špendi?','yQVYBgQ__6','matija.spendic@gmail.com','2013-11-01',2,'2015-03-30 23:26:03',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
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
