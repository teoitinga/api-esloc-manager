-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: eslocdb
-- ------------------------------------------------------
-- Server version	8.0.16

--
-- Table structure for table `tipo_servico`
--

DROP TABLE IF EXISTS `tipo_servico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tipo_servico` (
  `tipo` varchar(255) NOT NULL,
  `data_atualizacao` date DEFAULT NULL,
  `data_cadastro` date DEFAULT NULL,
  `descricao_tipo` varchar(255) DEFAULT NULL,
  `id` bigint(20) DEFAULT NULL,
  `tempo_estimado` int(11) DEFAULT NULL,
  `valor_referencia` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`tipo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

