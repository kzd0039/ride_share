-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: pic_trade
-- ------------------------------------------------------
-- Server version	5.7.21-log

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

CREATE DATABASE `pic_trade`;
USE `pic_trade`;

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categories` (
  `id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'anime'),(2,'photo-nature'),(3,'photo-architecture'),(4,'photo-portrait'),(5,'photo-events'),(6,'photo-sights'),(7,'paintings'),(8,'backgrounds'),(9,'materials');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `picture`
--

DROP TABLE IF EXISTS `picture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `picture` (
  `id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `author_id` int(11) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `thumbnail` varchar(45) DEFAULT NULL,
  `original` varchar(45) DEFAULT NULL,
  `watermarked` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `picture`
--

LOCK TABLES `picture` WRITE;
/*!40000 ALTER TABLE `picture` DISABLE KEYS */;
INSERT INTO `picture` VALUES (1,'China Town',1,6,3.5,'27a87531-1752-4048-a0b1-60d7f2663989','050ec930-5365-4012-a382-d211742d9209','1b69e677-ec43-44a1-b3ad-04b661053f36'),(2,'Secaucus Junction',1,6,3.8,'48c0cc9d-3cfb-4220-909c-8dc3eab7cbfe','518ce98c-0e8c-4593-9b47-3208b50165e6','7f52040c-a969-42fa-8e76-facaab51652c'),(3,'River in New Jersey',1,2,4.2,'aea1703a-6ebe-4f75-85ed-43a3a2df2fbb','60a83d2f-92ed-4625-bac2-92b34877f80d','f9fd0d9d-73e7-48da-adac-7f915e7fe59f'),(4,'Minako',3,1,4.2,'3d8a7fca-c6db-4afc-882b-3d404cc513c9','da3ad4a1-9b37-4fc4-9d60-3ced96757be0','1da05b1c-bab3-433c-b7d7-b88ea32ff580'),(5,'Sheryl Nome',2,1,4.3,'6a576704-bec2-45a2-bc1b-11f2d785679d','f55bb629-733f-4ea4-978e-8cfa18c2eb87','f860a971-2fb7-40b1-95c2-a1e62ef51fe3'),(6,'Mountain',4,2,2.3,'f8331091-da5b-46e0-b6a5-34153132d4b6','69401f15-a28a-4434-9cea-b6410d3d860f','0da2f074-959e-42c7-9965-bc58fe601445'),(7,'plain',4,2,4,'28705835-ba33-414c-a5a5-ec4ed3759dda','fb0deb06-a4e2-4403-9cbe-b6f58af8e5fc','6db18c58-01f1-4757-a8fb-cf6e1187333d'),(8,'Bridge',4,2,4.2,'23fdd477-f774-4454-9b95-85e477c7ccb9','d1b541f1-7936-4c07-904d-29eaf66d63e7','770d9116-6f5a-428f-8ca9-7349d59a37b6'),(9,'test',4,2,3,'aee3b198-5670-414b-880f-75325de35b5d','632293b5-9494-42fe-8fcf-83ee988d0b83','048303c2-b353-4a21-add3-de1ea74dc565');
/*!40000 ALTER TABLE `picture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase`
--

DROP TABLE IF EXISTS `purchase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purchase` (
  `transaction_id` int(11) NOT NULL,
  `buyer_id` int(11) DEFAULT NULL,
  `seller_id` int(11) DEFAULT NULL,
  `picture_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`transaction_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase`
--

LOCK TABLES `purchase` WRITE;
/*!40000 ALTER TABLE `purchase` DISABLE KEYS */;
INSERT INTO `purchase` VALUES (1,3,1,2),(2,3,1,1),(3,1,3,4),(4,3,2,5),(5,1,4,8),(6,4,1,1);
/*!40000 ALTER TABLE `purchase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `username` varchar(45) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  `nickname` varchar(45) DEFAULT NULL,
  `balance` float DEFAULT '0',
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('admin','admin','Sol',418.2,1),('snome','snomepass','Sheryl Nome',51,2),('sailormoon','sailormoon','Sailor Warriors',30.1,3),('cjiang','123','jc',0.7,4);
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

-- Dump completed on 2018-04-26 14:25:46
