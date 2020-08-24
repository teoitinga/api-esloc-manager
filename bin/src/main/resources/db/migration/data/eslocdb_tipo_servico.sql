-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: eslocdb
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_servico`
--

LOCK TABLES `tipo_servico` WRITE;
/*!40000 ALTER TABLE `tipo_servico` DISABLE KEYS */;
INSERT INTO `tipo_servico` VALUES ('AGCAD',NULL,'2020-07-29','Cadastro de uso insignificante',NULL,1,0.00),('AMIS',NULL,'2020-07-29','Interpretação de análise de solo',NULL,1,0.00),('AMSL',NULL,'2020-07-29','Envio de amostra de solo',NULL,10,25.00),('ATERBOV',NULL,NULL,'ATER - Bovinocultura',NULL,1,0.00),('ATERMA',NULL,NULL,'ATER - Meio ambiente',NULL,1,0.00),('CAD',NULL,'2020-07-29','Elaboração de limite de crédito para Sicoob',NULL,2,150.00),('CAR',NULL,'2020-07-29','Cadastro ambiental Rural - Emissão',NULL,2,200.00),('CAR2V',NULL,'2020-07-30','Cadastro ambiental Rural - 2a via',NULL,1,0.00),('CARRET',NULL,'2020-07-30','Cadastro ambiental Rural - Retificação',NULL,2,200.00),('CRCBBAF',NULL,'2020-07-30','Banco do Brasil - Pronaf - Custeio - Elaboração de projeto de crédito rural custeio',NULL,7,150.00),('CRCBBMP',NULL,'2020-07-30','Banco do Brasil - Pronamp - Elaboração de projeto de crédito rural custeio',NULL,7,150.00),('CRCSBB',NULL,'2020-07-29','Banco do Brasil - Elaboração de projeto de crédito rural custeio',NULL,7,150.00),('CRCSSC',NULL,'2020-07-29','Sicoob - Elaboração de projeto de crédito rural custeio',NULL,7,150.00),('CRIBBAF',NULL,'2020-07-30','Banco do Brasil - Pronaf - Elaboração de projeto de crédito rural investimento',NULL,7,150.00),('CRICBBMP',NULL,'2020-07-30','Banco do Brasil - Pronmp - Elaboração de projeto de crédito rural investimento',NULL,7,150.00),('CRINBB',NULL,'2020-07-29','Banco do Brasil - Elaboração de projeto de crédito rural investimento',NULL,7,150.00),('CRINSC',NULL,'2020-07-29','Sicoob - Elaboração de projeto de crédito rural invstimento',NULL,7,150.00),('CRMABB',NULL,NULL,'Credito Rural Moderagro - Banco do Brasil',NULL,7,150.00),('DAP',NULL,'2020-07-29','DAP - Emissão de Declaração de aptidão ao pronaf',NULL,2,0.00),('DAPCS',NULL,'2020-07-30','DAP - Consulta irregularidade em acesso a DAP',NULL,1,0.00),('DAPDS',NULL,'2020-07-30','DAP - Solicitação de desbloqueio de DAP',NULL,1,0.00),('GLB',NULL,'2020-07-29','Gleba - elaboração de Geo referenciamento',NULL,2,50.00),('LM',NULL,'2020-07-29','Elaboração de limite de crédito para Banco do Brasil',NULL,2,150.00),('PAA',NULL,'2020-07-29','PAA - Elaboração de projeto',NULL,7,0.00),('PAAATER',NULL,'2020-08-04','PAA - Orientações',NULL,1,0.00),('PNAE',NULL,'2020-07-29','PNAE - Elaboração de projeto',NULL,7,0.00),('PNAEATER',NULL,'2020-08-04','PNAE - Orientações',NULL,1,0.00);
/*!40000 ALTER TABLE `tipo_servico` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-08-14 17:10:26
