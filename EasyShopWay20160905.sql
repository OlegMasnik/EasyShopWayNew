-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: easy_shop_way
-- ------------------------------------------------------
-- Server version	5.7.9-log

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
-- Table structure for table `cupboard`
--

DROP TABLE IF EXISTS `cupboard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cupboard` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `board_amount` int(11) NOT NULL,
  `description_en` varchar(255) DEFAULT NULL,
  `description_uk` varchar(255) DEFAULT NULL,
  `active` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=5461 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cupboard`
--

LOCK TABLES `cupboard` WRITE;
/*!40000 ALTER TABLE `cupboard` DISABLE KEYS */;
INSERT INTO `cupboard` VALUES (1,3,'different','різне',1),(2,3,'different','різне',1),(3,5,'all','все',1);
/*!40000 ALTER TABLE `cupboard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cupboard_placement`
--

DROP TABLE IF EXISTS `cupboard_placement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cupboard_placement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cupboard_id` int(11) NOT NULL,
  `placement_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `cboard_placement_id_idx` (`placement_id`),
  KEY `cboard_id_idx` (`cupboard_id`),
  CONSTRAINT `cboard_cupboard_id` FOREIGN KEY (`cupboard_id`) REFERENCES `cupboard` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `cboard_placement_id` FOREIGN KEY (`placement_id`) REFERENCES `placement` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=8192 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cupboard_placement`
--

LOCK TABLES `cupboard_placement` WRITE;
/*!40000 ALTER TABLE `cupboard_placement` DISABLE KEYS */;
/*!40000 ALTER TABLE `cupboard_placement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enter`
--

DROP TABLE IF EXISTS `enter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `enter` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `placement_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `enter_placement_id_idx` (`placement_id`),
  CONSTRAINT `enter_placement_id` FOREIGN KEY (`placement_id`) REFERENCES `placement` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enter`
--

LOCK TABLES `enter` WRITE;
/*!40000 ALTER TABLE `enter` DISABLE KEYS */;
/*!40000 ALTER TABLE `enter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `map`
--

DROP TABLE IF EXISTS `map`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `map` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `weight` int(11) NOT NULL,
  `height` int(11) NOT NULL,
  `active` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=5461 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `map`
--

LOCK TABLES `map` WRITE;
/*!40000 ALTER TABLE `map` DISABLE KEYS */;
INSERT INTO `map` VALUES (1,50,100,1),(2,100,100,1),(3,100,100,0);
/*!40000 ALTER TABLE `map` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paydesk`
--

DROP TABLE IF EXISTS `paydesk`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paydesk` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `placement_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `paydesk_placement_id_idx` (`placement_id`),
  CONSTRAINT `paydesk_placement_id` FOREIGN KEY (`placement_id`) REFERENCES `placement` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paydesk`
--

LOCK TABLES `paydesk` WRITE;
/*!40000 ALTER TABLE `paydesk` DISABLE KEYS */;
/*!40000 ALTER TABLE `paydesk` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `placement`
--

DROP TABLE IF EXISTS `placement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `placement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `map_id` int(11) NOT NULL,
  `value` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `map_id_idx` (`map_id`),
  CONSTRAINT `map_id` FOREIGN KEY (`map_id`) REFERENCES `map` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `placement`
--

LOCK TABLES `placement` WRITE;
/*!40000 ALTER TABLE `placement` DISABLE KEYS */;
/*!40000 ALTER TABLE `placement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_type_id` int(11) NOT NULL,
  `name_uk` varchar(50) NOT NULL,
  `name_en` varchar(50) NOT NULL,
  `active` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_product_product_type_id` (`product_type_id`),
  CONSTRAINT `FK_product_product_type_id` FOREIGN KEY (`product_type_id`) REFERENCES `product_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=5461 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,1,'лобстер','lobster',1),(2,2,'червоне вино','red wine',1),(3,3,'хліб','bread',1);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_list`
--

DROP TABLE IF EXISTS `product_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `date` date NOT NULL,
  `time` time NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_product_list_commodity_id` (`product_id`),
  KEY `FK_product_list_user_id` (`user_id`),
  CONSTRAINT `FK_product_list_commodity_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FK_product_list_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=8192 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_list`
--

LOCK TABLES `product_list` WRITE;
/*!40000 ALTER TABLE `product_list` DISABLE KEYS */;
INSERT INTO `product_list` VALUES (2,1,1,'2015-10-11','23:36:00'),(3,2,2,'2016-02-11','12:43:00');
/*!40000 ALTER TABLE `product_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_placement`
--

DROP TABLE IF EXISTS `product_placement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_placement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL,
  `cupboard_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_product_placement_cupboard_id` (`cupboard_id`),
  KEY `FK_product_placement_product_id` (`product_id`),
  CONSTRAINT `FK_product_placement_cupboard_id` FOREIGN KEY (`cupboard_id`) REFERENCES `cupboard` (`id`),
  CONSTRAINT `FK_product_placement_product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=8192 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_placement`
--

LOCK TABLES `product_placement` WRITE;
/*!40000 ALTER TABLE `product_placement` DISABLE KEYS */;
INSERT INTO `product_placement` VALUES (3,1,1),(4,2,1);
/*!40000 ALTER TABLE `product_placement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_type`
--

DROP TABLE IF EXISTS `product_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name_en` varchar(50) NOT NULL,
  `name_uk` varchar(50) NOT NULL,
  `img` varchar(255) DEFAULT NULL,
  `active` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=4096 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_type`
--

LOCK TABLES `product_type` WRITE;
/*!40000 ALTER TABLE `product_type` DISABLE KEYS */;
INSERT INTO `product_type` VALUES (1,'seafood','морепродукти',NULL,1),(2,'alcohol','алкоголь',NULL,1),(3,'bakery','випічка',NULL,1),(4,'dishes','їжа',NULL,0);
/*!40000 ALTER TABLE `product_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `email` varchar(32) NOT NULL,
  `password` varchar(32) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `active` tinyint(1) NOT NULL,
  `role` enum('admin','user') NOT NULL,
  `language` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=1638 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Michael','Armstrong','marmstrong0@woothemes.com','1LGFuJEZlQ','2007-08-19',0,'user','en'),(2,'Gregory','Reed','greed1@altervista.org','EDEVYwzz','1996-08-07',1,'user','en'),(3,'Jacqueline','Ramirez','jramirez2@sohu.com','BlGTlu','1988-03-01',0,'admin','en'),(4,'Marie','Perkins','mperkins3@sfgate.com','whXFsg3m','1998-09-14',0,'admin','en'),(5,'Jacqueline','Ray','jray4@google.fr','cEvjdVxt5R1y','1991-06-01',1,'admin','en'),(6,'Randy','Matthews','rmatthews5@sun.com','ruHBvMOE4h','2002-04-30',1,'admin','en'),(8,'Adam','Wells','awells7@oaic.gov.au','znJXml5','2004-02-15',0,'user','en'),(9,'Margaret','Tucker','mtucker8@ow.ly','TDTj4C7TUl','1987-03-11',1,'user','en'),(10,'Amy','Hall','ahall9@taobao.com','ioR0tCczMv','1989-09-22',0,'user','en'),(11,'Olya','narushynska','email@com.ua','password','1992-11-11',1,'user','en');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wall`
--

DROP TABLE IF EXISTS `wall`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wall` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `placement_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `wall_placement_id_idx` (`placement_id`),
  CONSTRAINT `wall_placement_id` FOREIGN KEY (`placement_id`) REFERENCES `placement` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wall`
--

LOCK TABLES `wall` WRITE;
/*!40000 ALTER TABLE `wall` DISABLE KEYS */;
/*!40000 ALTER TABLE `wall` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'easy_shop_way'
--

--
-- Dumping routines for database 'easy_shop_way'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-09-05  0:56:01
