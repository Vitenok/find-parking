CREATE DATABASE  IF NOT EXISTS `parking` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `parking`;
-- MySQL dump 10.13  Distrib 5.6.10, for Win64 (x86_64)
--
-- Host: localhost    Database: parking
-- ------------------------------------------------------
-- Server version	5.6.10

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
-- Table structure for table `parking_current_state`
--

DROP TABLE IF EXISTS `parking_current_state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parking_current_state` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parking_id` int(11) NOT NULL,
  `parking_user_car_number` varchar(45) NOT NULL,
  `parking_user_start_time` datetime NOT NULL,
  `parking_user_end_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_idx` (`parking_id`),
  CONSTRAINT `id` FOREIGN KEY (`parking_id`) REFERENCES `parking_places` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parking_current_state`
--

LOCK TABLES `parking_current_state` WRITE;
/*!40000 ALTER TABLE `parking_current_state` DISABLE KEYS */;
/*!40000 ALTER TABLE `parking_current_state` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parking_historical_state`
--

DROP TABLE IF EXISTS `parking_historical_state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parking_historical_state` (
  `id` int(11) NOT NULL,
  `parking_id` int(11) NOT NULL,
  `parking_user_car_number` varchar(45) NOT NULL,
  `parking_user_start_time` datetime NOT NULL,
  `parking_user_end_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `parking_id_idx` (`parking_id`),
  CONSTRAINT `parking_id` FOREIGN KEY (`parking_id`) REFERENCES `parking_places` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parking_historical_state`
--

LOCK TABLES `parking_historical_state` WRITE;
/*!40000 ALTER TABLE `parking_historical_state` DISABLE KEYS */;
/*!40000 ALTER TABLE `parking_historical_state` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parking_places`
--

DROP TABLE IF EXISTS `parking_places`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parking_places` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parking_address` varchar(255) NOT NULL,
  `parking_capacity` int(11) NOT NULL,
  `parking_available_slots` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parking_places`
--

LOCK TABLES `parking_places` WRITE;
/*!40000 ALTER TABLE `parking_places` DISABLE KEYS */;
INSERT INTO `parking_places` VALUES (1,'Sandakerveien 50',20,'20'),(2,'Akkersgata 20',15,'15'),(3,'Lysaker Torg 11',30,'30'),(4,'Bryggetorget 4',15,'15'),(5,'Meridalsveien 74',20,'20');
/*!40000 ALTER TABLE `parking_places` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-06-02 17:11:54
