-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: eslocdb
-- ------------------------------------------------------
-- Server version	8.0.16

--
-- Table structure for table `document_type`
--

DROP TABLE IF EXISTS `document_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `document_type` (
  `descricao` varchar(255) NOT NULL,
  `abreviatura` varchar(255) DEFAULT NULL,
  `id` bigint(20) DEFAULT NULL,
  `informacoes` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`descricao`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
