-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: caveatemptor
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categories` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `parent_id` bigint(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `parent_id_idx` (`parent_id`),
  CONSTRAINT `fk_categories_categories_parent_id` FOREIGN KEY (`parent_id`) REFERENCES `categories` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=188 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'root',NULL,NULL),(2,'Desktop PC','descriere desktop pc',1),(3,'PC components','descriere pc components',1),(4,'Laptops','descriere laptops',1),(5,'Laptop accessories','descriere laptop accessories',1),(7,'CPUs','descriere CPUs',3),(8,'Storage','descriere storage',3),(9,'RAM','descriere RAM',3),(21,'INTEL','descriere INTEL',7),(167,'AMD','descriere AMD',7),(168,'RYZEN','Ryzen is an AMD brand for microprocessors. The brand was introduced in 2017 with products implementing their Zen microarchitecture.',167),(186,'Stamps','description for stamps',1),(187,'Stamps child','description for stamps child',186);
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `items`
--

DROP TABLE IF EXISTS `items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `items` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `user_id` bigint(11) NOT NULL,
  `category_id` bigint(11) NOT NULL,
  `bid_id` bigint(11) DEFAULT NULL,
  `initial_price` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_items_categories_category_id_idx` (`category_id`),
  KEY `fk_items_user_user_id_idx` (`user_id`),
  CONSTRAINT `fk_items_categories_category_id` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_items_user_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items`
--

LOCK TABLES `items` WRITE;
/*!40000 ALTER TABLE `items` DISABLE KEYS */;
INSERT INTO `items` VALUES (1,'i7 2400',23,7,NULL,NULL),(2,'i7 6700',23,7,NULL,NULL),(3,'i7 6700k',23,7,NULL,NULL),(4,'Civil war stamps',23,186,NULL,NULL),(5,'procesor i3',23,21,NULL,NULL),(6,'test stamp',23,186,NULL,NULL),(7,'test',23,186,NULL,NULL),(8,'i5',23,21,NULL,NULL),(9,'i5',23,21,NULL,NULL),(10,'i5',23,21,NULL,NULL),(11,'stamp test',23,186,NULL,NULL),(12,'test',23,2,NULL,NULL),(13,'stamp final',23,186,NULL,NULL),(14,'Lenovo IdeaCentre',23,2,NULL,NULL),(15,'for the lulz',23,187,NULL,NULL),(16,'shaorma',24,187,NULL,NULL),(17,'best cpu 2k17 buy 4 free',23,168,NULL,NULL),(18,'i7 6700k nou noutz',23,21,NULL,NULL),(19,'gfdgfdgfd',23,168,NULL,NULL),(20,'test',23,2,NULL,4323.34);
/*!40000 ALTER TABLE `items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registrations`
--

DROP TABLE IF EXISTS `registrations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `registrations` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(11) NOT NULL,
  `authorization_key` varchar(100) NOT NULL,
  `authorization_key_expiration` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_users_registration_user_id_idx` (`user_id`),
  CONSTRAINT `fk_users_registration_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registrations`
--

LOCK TABLES `registrations` WRITE;
/*!40000 ALTER TABLE `registrations` DISABLE KEYS */;
INSERT INTO `registrations` VALUES (3,22,'kscbn972l0pareqvdu6l',1493818331256);
/*!40000 ALTER TABLE `registrations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `email_address` varchar(100) NOT NULL,
  `account_name` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `role` varchar(100) NOT NULL DEFAULT 'default_user',
  `is_activated` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `email_address_UNIQUE` (`email_address`),
  UNIQUE KEY `account_name_UNIQUE` (`account_name`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (3,'testian','testnaru','testnaru@test.com','testiantestnaru4','124wrtgwe43','REGULAR_USER',0),(4,'sdad','dffd','afdsf','fdasfas','fdasfdasfdsa','REGULAR_USER',0),(5,'adriann','butnaruuu','adrian@gmail.com','adrianbutnaru444','unhackable2014','REGULAR_USER',0),(18,'fdafdadfdas','fdafdafdaf','dafdafda','fdafafda','fdafdsaf','REGULAR_USER',0),(22,'neactivat','neactivescu','neactivat@mail.com','neactivat','parola','REGULAR_USER',0),(23,'adrian','butnaru','adrian.butnaru4@gmail.com','adrianbutnaru4','parola123','REGULAR_USER',1),(24,'adrian','butnaru','mail@mail.com','adrianbutnaru3','parola123','REGULAR_USER',1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-08 10:22:26
