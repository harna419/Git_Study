-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: boarddb
-- ------------------------------------------------------
-- Server version	5.5.37

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
-- Table structure for table `board`
--

DROP TABLE IF EXISTS `board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `board` (
  `num` int(11) NOT NULL AUTO_INCREMENT,
  `writer` varchar(20) NOT NULL,
  `subject` varchar(50) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(20) NOT NULL,
  `wdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `readcount` int(11) DEFAULT '0',
  `ref` int(11) NOT NULL,
  `ref_step` smallint(6) NOT NULL,
  `ref_level` smallint(6) NOT NULL,
  `content` text NOT NULL,
  `ip` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`num`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board`
--

LOCK TABLES `board` WRITE;
/*!40000 ALTER TABLE `board` DISABLE KEYS */;
INSERT INTO `board` VALUES (1,'1','1','1','1','2016-07-04 12:02:47',0,24,0,0,'1','0:0:0:0:0:0:0:1'),(2,'2','2','2','2','2016-07-04 12:02:47',0,23,0,0,'2','0:0:0:0:0:0:0:1'),(3,'3','3','3','3','2016-07-04 12:02:47',0,22,0,0,'3','0:0:0:0:0:0:0:1'),(4,'4','4','4','4','2016-07-04 12:02:47',0,21,0,0,'4','0:0:0:0:0:0:0:1'),(5,'5','5','5','5','2016-07-04 12:02:47',0,20,0,0,'5','0:0:0:0:0:0:0:1'),(6,'6','6','6','6','2016-07-04 12:02:47',0,19,0,0,'6','0:0:0:0:0:0:0:1'),(7,'7','7','7','7','2016-07-04 12:02:47',0,18,0,0,'7','0:0:0:0:0:0:0:1'),(8,'8','8','8','8','2016-07-04 12:02:47',0,17,0,0,'8','0:0:0:0:0:0:0:1'),(9,'9','9','9','9','2016-07-04 12:02:47',0,16,0,0,'9','0:0:0:0:0:0:0:1'),(10,'10','10','10','10','2016-07-04 12:02:47',0,15,0,0,'10','0:0:0:0:0:0:0:1'),(11,'11','11','11','11','2016-07-04 12:02:47',0,14,0,0,'11','0:0:0:0:0:0:0:1'),(12,'12','12','12','12','2016-07-04 12:02:47',0,13,0,0,'12','0:0:0:0:0:0:0:1'),(13,'13','13','13','13','2016-07-04 12:02:47',0,12,0,0,'13','0:0:0:0:0:0:0:1'),(14,'14','14','14','14','2016-07-04 12:02:47',4,11,0,0,'14','0:0:0:0:0:0:0:1'),(16,'15','15','15','15','2016-07-04 12:02:47',13,9,0,0,'15','0:0:0:0:0:0:0:1'),(17,'16','16','16','16','2016-07-04 12:02:47',0,8,0,0,'16','0:0:0:0:0:0:0:1'),(18,'17','17','17','17','2016-07-04 12:02:47',1,7,0,0,'17','0:0:0:0:0:0:0:1'),(19,'18','답글 : 17','18','18','2016-07-04 12:02:47',0,6,0,0,'본문 내용 :\r\n===========================\r\n 17\r\n===========================\r\n18','0:0:0:0:0:0:0:1'),(20,'19','19','19','19','2016-07-04 12:02:47',1,5,0,0,'19','0:0:0:0:0:0:0:1'),(21,'20','답글 : 19','20','20','2016-07-04 12:02:47',0,4,0,0,'본문 내용 :\r\n===========================\r\n 19\r\n===========================\r\n20','0:0:0:0:0:0:0:1'),(22,'21','21','21','21','2016-07-04 12:02:47',1,3,0,0,'21','0:0:0:0:0:0:0:1'),(23,'22','답글 : 21 워크벤치 오케이','22','22','2016-07-04 12:02:47',1,2,0,0,'본문 내용 :\r\n===========================\r\n 21\r\n===========================\r\n22','0:0:0:0:0:0:0:1'),(25,'23','23','23','23','2016-07-04 12:02:47',2,1,0,0,'23','0:0:0:0:0:0:0:1'),(26,'24','답글 : 23','24','24','2016-07-04 12:02:47',0,0,0,0,'본문 내용 :\r\n===========================\r\n 23\r\n===========================\r\n','0:0:0:0:0:0:0:1');
/*!40000 ALTER TABLE `board` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-07-04 21:13:15
