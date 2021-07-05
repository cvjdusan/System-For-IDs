-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: document
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `documentrequest`
--

DROP TABLE IF EXISTS `documentrequest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `documentrequest` (
  `id` bigint(12) NOT NULL,
  `JMBG` varchar(45) NOT NULL,
  `bracnoStanje` varchar(45) NOT NULL,
  `brojPrebivalista` varchar(45) NOT NULL,
  `datumRodjenja` varchar(45) NOT NULL,
  `ime` varchar(45) NOT NULL,
  `imeMajke` varchar(45) NOT NULL,
  `imeOca` varchar(45) NOT NULL,
  `nacionalnost` varchar(45) NOT NULL,
  `opstinaPrebivalista` varchar(45) NOT NULL,
  `pol` varchar(45) NOT NULL,
  `prezime` varchar(45) NOT NULL,
  `prezimeMajke` varchar(45) NOT NULL,
  `prezimeOca` varchar(45) NOT NULL,
  `profesija` varchar(45) NOT NULL,
  `ulicaPrebivalista` varchar(45) NOT NULL,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `documentrequest`
--

LOCK TABLES `documentrequest` WRITE;
/*!40000 ALTER TABLE `documentrequest` DISABLE KEYS */;
INSERT INTO `documentrequest` VALUES (171690000001,'3011997762056','neozenjen/a','MMMM1','1997-11-30','levo','aha','sda','srbenda','Holawola','yolo','desno','fff','fff','programer','zeam','Kreiran'),(171690000002,'3011997762056','neozenjen/a','MMMM1','1997-11-30','Dusan','aha','sda','srbenda','Holawola','yolo','Oho','fff','fff','programer','zeam','Kreiran'),(171690000003,'3011997762056','udovac/Udovica','MMMM1','1997-11-30','Mercedes','aha','sda','srbenda','Holawola','yolo','desno','fff','fff','programer','zeam','Kreiran'),(171690000004,'3011997762056','neozenjen/a','MMMM1','1997-11-30','Audi','aha','sda','srbenda','Holawola','yolo','desno','fff','fff','programer','zeam','Kreiran'),(171690000005,'3011997762056','ozenjen/Udata','MMMM1','1997-11-30','Audi','aha','sda','srbenda','Holawola','yolo','desno','fff','fff','programer','zeam','Kreiran'),(171690000006,'3011997762056','razveden/a','MMMM1','1997-11-30','ferari','aha','sda','srbenda','Holawola','yolo','desno','fff','fff','programer','zeam','Kreiran'),(171690000007,'3011997762056','udovac/Udovica','MMMM1','1997-11-30','LuiGI','aha','sda','srbenda','Holawola','yolo','desno','fff','fff','programer','zeam','cekaNaIzvrsenje'),(171690000008,'3011997762056','ozenjen/Udata','MMMM1','1997-11-30','Wow','aha','sda','srbenda','Holawola','yolo','desno','fff','fff','programer','zeam','cekaNaIzvrsenje'),(171690000009,'3011997762056','ozenjen/Udata','MMMM1','1997-11-30','Wow','aha','sda','srbenda','Holawola','yolo','desno','fff','fff','programer','zeam','urucen'),(171690000010,'3011997762056','ozenjen/Udata','MMMM1','1997-11-30','Wow','aha','sda','srbenda','Holawola','yolo','desno','fff','fff','programer','zeam','urucen'),(171690000011,'3011997762056','ozenjen/Udata','MMMM1','1997-11-30','Wow','aha','sda','srbenda','Holawola','yolo','desno','fff','fff','programer','zeam','urucen'),(171690000012,'3011997762056','ozenjen/Udata','MMMM1','1997-11-30','Wow','aha','sda','srbenda','Holawola','yolo','desno','fff','fff','programer','zeam','urucen'),(171690000013,'3011997762056','ozenjen/Udata','MMMM1','1997-11-30','Wow','aha','sda','srbenda','Holawola','yolo','desno','fff','fff','programer','zeam','urucen'),(171690000014,'3011997762056','ozenjen/Udata','MMMM1','1997-11-30','Wow','aha','sda','srbenda','Holawola','yolo','desno','fff','fff','programer','zeam','urucen'),(171690000015,'3011997762056','ozenjen/Udata','MMMM1','1997-11-30','Wow','aha','sda','srbenda','Holawola','yolo','desno','fff','fff','programer','zeam','urucen'),(171690000016,'3011997762056','ozenjen/Udata','MMMM1','1997-11-30','Wow','aha','sda','srbenda','Holawola','yolo','desno','fff','fff','programer','zeam','urucen'),(171690000017,'3011997762056','udovac/Udovica','MMMM1','1997-11-30','levo','aha','sda','srbenda','Holawola','yolo','desno','fff','fff','programer','zeam','urucen'),(171690000018,'3011997762056','udovac/Udovica','MMMM1','1997-11-30','levo','aha','sda','srbenda','Holawola','yolo','desno','fff','fff','programer','zeam','cekaNaIzvrsenje'),(171690000019,'3011997762056','ozenjen/Udata','MMMM1','1997-11-30','levo','aha','sda','srbenda','Holawola','yolo','desno','fff','fff','programer','zeam','cekaNaIzvrsenje'),(171690000020,'3011997762056','ozenjen/Udata','MMMM1','1997-11-30','levo','aha','sda','srbenda','Holawola','yolo','desno','fff','fff','programer','zeam','cekaNaIzvrsenje'),(171690000021,'3030303030303','udovac/Udovica','MMMM1','1997-11-30','levo','aha','sda','srbenda','Holawola','yolo','desno','fff','fff','programer','zeam','urucen'),(171690000022,'3030303030303','neozenjen/a','MMMM1','1997-11-30','levo','aha','sda','srbenda','Holawola','yolo','desno','fff','fff','programer','zeam','urucen'),(171690000023,'3030303030303','udovac/Udovica','MMMM1','1997-11-30','levo','aha','sda','srbenda','Holawola','yolo','desno','fff','fff','programer','zeam','urucen'),(171690000024,'3030303030303','razveden/a','MMMM1','1997-11-30','levo','aha','sda','srbenda','Holawola','yolo','desno','fff','fff','programer','zeam','urucen'),(171690000025,'3030303030303','ozenjen/Udata','MMMM1','1997-11-30','levo','aha','sda','srbenda','Holawola','yolo','desno','fff','fff','programer','zeam','cekaNaIzvrsenje'),(171690000026,'3030303030303','udovac/Udovica','MMMM1','1997-11-30','levo','aha','sda','srbenda','Holawola','yolo','desno','fff','fff','programer','zeam','cekaNaIzvrsenje'),(171690000027,'3030303030303','neozenjen/a','MMMM1','1997-11-30','levo','aha','sda','srbenda','Holawola','yolo','desno','fff','fff','programer','zeam','uProdukciji'),(171690000028,'3030303030303','udovac/Udovica','MMMM1','1997-11-30','levo','aha','sda','srbenda','Holawola','yolo','desno','fff','fff','programer','zeam','uProdukciji'),(171690000029,'3030303030303','ozenjen/Udata','MMMM1','1997-11-30','levo','aha','sda','srbenda','Holawola','yolo','desno','fff','fff','programer','zeam','uProdukciji'),(171690000030,'3030303030303','neozenjen/a','MMMM1','1997-11-30','K','aha','sda','srbenda','Holawola','yolo','desno','fff','fff','programer','zeam','cekaNaIzvrsenje'),(171690000031,'3030303030303','ozenjen/Udata','MMMM1','1997-11-30','hm','aha','sda','srbenda','Holawola','yolo','desno','fff','fff','programer','zeam','uProdukciji'),(171690000032,'3030303030303','ozenjen/Udata','MMMM1','1997-11-30','levo','aha','sda','srbenda','Holawola','yolo','desno','fff','fff','programer','zeam','uProdukciji'),(171690000033,'3030303030303','ozenjen/Udata','MMMM1','1997-11-30','levo','aha','sda','srbenda','Holawola','yolo','desno','fff','fff','programer','zeam','cekaNaIzvrsenje'),(171690000034,'3030303030303','ozenjen/Udata','MMMM1','1997-11-30','levo','aha','sda','srbenda','Holawola','yolo','desno','fff','fff','programer','zeam','urucen'),(171690000035,'3030303030303','ozenjen/Udata','MMMM1','1997-11-30','levo','aha','sda','srbenda','Holawola','yolo','desno','fff','fff','programer','zeam','urucen'),(171690000036,'3030303030303','razveden/a','MMMM1','1997-11-30','levo','aha','sda','srbenda','Holawola','yolo','desno','sd','fff','programer','zeam','cekaNaIzvrsenje');
/*!40000 ALTER TABLE `documentrequest` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-11 12:52:33
