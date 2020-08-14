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
-- Table structure for table `atendimento`
--

DROP TABLE IF EXISTS `atendimento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `atendimento` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_atualizacao` datetime(6) DEFAULT NULL,
  `data_cadastro` datetime(6) DEFAULT NULL,
  `data_conclusao_prevista` date DEFAULT NULL,
  `data_finalizada` datetime(6) DEFAULT NULL,
  `datapgtoart` date DEFAULT NULL,
  `datapgtodae` date DEFAULT NULL,
  `emitiuart` varchar(255) DEFAULT NULL,
  `emitiudae` varchar(255) DEFAULT NULL,
  `observacoes` varchar(255) DEFAULT NULL,
  `status_tarefa` varchar(255) DEFAULT NULL,
  `tarefa_descricao` varchar(255) DEFAULT NULL,
  `valor_do_servico` decimal(19,2) DEFAULT NULL,
  `emissor_cpf` varchar(255) DEFAULT NULL,
  `produtor_cpf` varchar(255) NOT NULL,
  `responsavel_cpf` varchar(255) DEFAULT NULL,
  `fk_service` varchar(255) DEFAULT NULL,
  `data_atendimento` date DEFAULT NULL,
  `valor_do_contrato` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKq396gkcu6rpk42av549cm7vdl` (`emissor_cpf`),
  KEY `FKrt9fvw5orr2mw8opf26yad5f6` (`produtor_cpf`),
  KEY `FKfuo5cfkopk8ei9nl1e2ai7js` (`responsavel_cpf`),
  KEY `FK5s91w04xlouj3euv2ljj7rrl3` (`fk_service`),
  CONSTRAINT `FK5s91w04xlouj3euv2ljj7rrl3` FOREIGN KEY (`fk_service`) REFERENCES `tipo_servico` (`tipo`),
  CONSTRAINT `FKfuo5cfkopk8ei9nl1e2ai7js` FOREIGN KEY (`responsavel_cpf`) REFERENCES `persona` (`cpf`),
  CONSTRAINT `FKq396gkcu6rpk42av549cm7vdl` FOREIGN KEY (`emissor_cpf`) REFERENCES `persona` (`cpf`),
  CONSTRAINT `FKrt9fvw5orr2mw8opf26yad5f6` FOREIGN KEY (`produtor_cpf`) REFERENCES `persona` (`cpf`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `atendimento`
--

LOCK TABLES `atendimento` WRITE;
/*!40000 ALTER TABLE `atendimento` DISABLE KEYS */;
INSERT INTO `atendimento` VALUES (3,NULL,'2020-07-29 09:44:10.157000','2020-07-30',NULL,NULL,NULL,'SIM','SIM','','INICIADA','ANALISE DE SOLO',100.00,'04459471604','11740141644','04459471604','AMIS','2020-07-29',NULL),(4,NULL,'2020-07-29 09:51:45.324000','2020-08-05',NULL,NULL,NULL,'SIM','SIM','','INICIADA','Elaboração projeto Coban',200.00,'04459471604','70479054649','04459471604','CRINBB','2020-07-29',NULL),(5,NULL,'2020-07-29 09:51:45.416000','2020-07-31',NULL,NULL,NULL,'NAO','NAO','','INICIADA','Emissão segunda via',0.00,'04459471604','70479054649','04459471604','DAP','2020-07-29',NULL),(6,NULL,'2020-07-29 09:51:45.631000','2020-08-05',NULL,NULL,NULL,'SIM','SIM','','INICIADA','Elaboração projeto Coban',200.00,'04459471604','81857918649','04459471604','CRINBB','2020-07-29',NULL),(7,NULL,'2020-07-29 09:51:45.725000','2020-07-31',NULL,NULL,NULL,'NAO','NAO','','INICIADA','Emissão segunda via',0.00,'04459471604','81857918649','04459471604','DAP','2020-07-29',NULL),(8,NULL,'2020-07-29 10:00:04.187000','2020-08-05',NULL,NULL,NULL,'SIM','SIM','','INICIADA','Elaboração de projeto',250.00,'04459471604','11740141644','04459471604','CRCSSC','2020-07-29',NULL),(9,NULL,'2020-07-29 10:00:04.280000','2020-08-05',NULL,NULL,NULL,'SIM','SIM','','INICIADA','Elaboração de projeto',250.00,'04459471604','49529609604','04459471604','CRCSSC','2020-07-29',NULL),(10,NULL,'2020-07-29 10:04:39.649000','2020-07-31',NULL,NULL,NULL,'NAO','NAO','','INICIADA','EMISSÃO DE DAP',0.00,'04459471604','49529609604','04459471604','DAP','2020-07-29',NULL),(11,NULL,'2020-07-29 10:04:39.738000','2020-07-31',NULL,NULL,NULL,'NAO','NAO','','INICIADA','EMISSÃO DE DAP',0.00,'04459471604','07730310732','04459471604','DAP','2020-07-29',NULL),(12,NULL,'2020-07-29 10:04:39.821000','2020-07-31',NULL,NULL,NULL,'NAO','NAO','','INICIADA','EMISSÃO DE DAP',0.00,'04459471604','08898961600','04459471604','DAP','2020-07-29',NULL),(13,NULL,'2020-07-29 10:04:39.886000','2020-07-31',NULL,NULL,NULL,'NAO','NAO','','INICIADA','EMISSÃO DE DAP',0.00,'04459471604','00689187637','04459471604','DAP','2020-07-29',NULL),(14,NULL,'2020-07-29 10:04:39.970000','2020-07-31',NULL,NULL,NULL,'NAO','NAO','','INICIADA','EMISSÃO DE DAP',0.00,'04459471604','66889650620','04459471604','DAP','2020-07-29',NULL),(15,NULL,'2020-07-29 14:58:04.604000','2020-07-31',NULL,NULL,NULL,'NAO','NAO','Ver o valor do custeio para retificar','INICIADA','Elaboração de limite de crédito para Banco do Brasil',150.00,'04459471604','03674950669','04459471604','LM','2020-07-29',NULL),(16,NULL,'2020-07-29 15:29:39.181000','2020-07-31',NULL,NULL,NULL,'SIM','SIM','Produtor retornara para assinar projeto e assim lançar no sistema','INICIADA','Retificação CAR',200.00,'04459471604','04721404697','04459471604','CAR','2020-07-29',NULL),(17,NULL,'2020-07-29 15:29:39.280000','2020-08-05',NULL,NULL,NULL,'NAO','NAO','Produtor retornara para assinar projeto e assim lançar no sistema','INICIADA','Elaboração projeto de crédito COBAN',300.00,'04459471604','04721404697','04459471604','CRINBB','2020-07-29',NULL),(18,NULL,'2020-07-29 15:29:39.372000','2020-07-31',NULL,NULL,NULL,'SIM','SIM','Produtor retornara para assinar projeto e assim lançar no sistema','INICIADA','Retificação CAR',200.00,'04459471604','05819901614','04459471604','CAR','2020-07-29',NULL),(19,NULL,'2020-07-29 15:29:39.504000','2020-08-05',NULL,NULL,NULL,'NAO','NAO','Produtor retornara para assinar projeto e assim lançar no sistema','INICIADA','Elaboração projeto de crédito COBAN',300.00,'04459471604','05819901614','04459471604','CRINBB','2020-07-29',NULL),(20,NULL,'2020-07-29 15:29:39.572000','2020-07-31',NULL,NULL,NULL,'SIM','SIM','Produtor retornara para assinar projeto e assim lançar no sistema','INICIADA','Retificação CAR',200.00,'04459471604','18025501604','04459471604','CAR','2020-07-29',NULL),(21,NULL,'2020-07-29 15:29:39.653000','2020-08-05',NULL,NULL,NULL,'NAO','NAO','Produtor retornara para assinar projeto e assim lançar no sistema','INICIADA','Elaboração projeto de crédito COBAN',300.00,'04459471604','18025501604','04459471604','CRINBB','2020-07-29',NULL),(22,NULL,'2020-07-29 15:35:28.511000','2020-07-31',NULL,NULL,NULL,'NAO','NAO','Emitir DAE assim que o Fernando pedir','INICIADA','Limite para BB',150.00,'04459471604','89942361634','04459471604','LM','2020-07-29',NULL),(23,NULL,'2020-07-29 15:56:26.106000','2020-08-05',NULL,NULL,NULL,'NAO','NAO','','INICIADA','TRATOR',800.00,'04459471604','00689187637','04459471604','CRINBB','2020-07-29',NULL),(24,NULL,'2020-07-29 16:23:03.826000','2020-07-31',NULL,NULL,NULL,'NAO','NAO','','INICIADA','Retificação',200.00,'04459471604','68960840653','04459471604','CAR','2020-07-29',NULL),(26,NULL,'2020-08-04 08:37:58.976000','2020-08-06',NULL,NULL,NULL,'NAO','NAO','','INICIADA','Elaboração de limite de crédito para Banco do Brasil',150.00,'04459471604','57388261672','04459471604','LM','2020-08-04',NULL),(27,NULL,'2020-08-04 09:06:26.423000','2020-08-06',NULL,NULL,NULL,'NAO','NAO','','INICIADA','Elaboração de limite de crédito para Banco do Brasil',150.00,'04459471604','80318002604','04459471604','LM','2020-08-04',NULL),(28,NULL,'2020-08-04 11:12:03.628000','2020-07-31',NULL,NULL,NULL,'NAO','NAO','','INICIADA','Elaboração de limite de crédito para Sicoob',150.00,'04459471604','11740141644','04459471604','CAD','2020-07-29',NULL),(29,NULL,'2020-08-04 11:12:03.642000','2020-08-05',NULL,NULL,NULL,'NAO','NAO','','INICIADA','Sicoob - Elaboração de projeto de crédito rural invstimento',250.00,'04459471604','11740141644','04459471604','CRINSC','2020-07-29',NULL),(30,NULL,'2020-08-04 11:28:02.466000','2020-07-15',NULL,NULL,NULL,'NAO','NAO','','INICIADA','Banco do Brasil - Pronaf - Elaboração de projeto de crédito rural custeio',150.00,'04459471604','10272371696','04459471604','CRCBBAF','2020-07-08',NULL),(31,NULL,'2020-08-04 11:28:02.472000','2020-07-15',NULL,NULL,NULL,'NAO','NAO','','INICIADA','Banco do Brasil - Pronaf - Elaboração de projeto de crédito rural investimento',150.00,'04459471604','10272371696','04459471604','CRIBBAF','2020-07-08',NULL),(32,NULL,'2020-08-04 11:28:02.477000','2020-07-09',NULL,NULL,NULL,'NAO','NAO','','INICIADA','Consulta irregularidade em acesso a DAP',0.00,'04459471604','10272371696','04459471604','DAPCS','2020-07-08',NULL),(33,NULL,'2020-08-04 11:28:02.480000','2020-07-10',NULL,NULL,NULL,'NAO','NAO','','INICIADA','Emissão de Declaração de aptidão ao pronaf',0.00,'04459471604','10272371696','04459471604','DAP','2020-07-08',NULL),(34,NULL,'2020-08-04 11:28:02.482000','2020-07-15',NULL,NULL,NULL,'NAO','NAO','','INICIADA','Banco do Brasil - Pronaf - Elaboração de projeto de crédito rural custeio',150.00,'04459471604','72563397634','04459471604','CRCBBAF','2020-07-08',NULL),(35,NULL,'2020-08-04 11:28:02.485000','2020-07-15',NULL,NULL,NULL,'NAO','NAO','','INICIADA','Banco do Brasil - Pronaf - Elaboração de projeto de crédito rural investimento',150.00,'04459471604','72563397634','04459471604','CRIBBAF','2020-07-08',NULL),(36,NULL,'2020-08-04 11:28:02.489000','2020-07-09',NULL,NULL,NULL,'NAO','NAO','','INICIADA','Consulta irregularidade em acesso a DAP',0.00,'04459471604','72563397634','04459471604','DAPCS','2020-07-08',NULL),(37,NULL,'2020-08-04 11:28:02.492000','2020-07-10',NULL,NULL,NULL,'NAO','NAO','','INICIADA','Emissão de Declaração de aptidão ao pronaf',0.00,'04459471604','72563397634','04459471604','DAP','2020-07-08',NULL),(38,NULL,'2020-08-04 11:28:02.495000','2020-07-15',NULL,NULL,NULL,'NAO','NAO','','INICIADA','Banco do Brasil - Pronaf - Elaboração de projeto de crédito rural custeio',150.00,'04459471604','55637086672','04459471604','CRCBBAF','2020-07-08',NULL),(39,NULL,'2020-08-04 11:28:02.498000','2020-07-15',NULL,NULL,NULL,'NAO','NAO','','INICIADA','Banco do Brasil - Pronaf - Elaboração de projeto de crédito rural investimento',150.00,'04459471604','55637086672','04459471604','CRIBBAF','2020-07-08',NULL),(40,NULL,'2020-08-04 11:28:02.501000','2020-07-09',NULL,NULL,NULL,'NAO','NAO','','INICIADA','Consulta irregularidade em acesso a DAP',0.00,'04459471604','55637086672','04459471604','DAPCS','2020-07-08',NULL),(41,NULL,'2020-08-04 11:28:02.505000','2020-07-10',NULL,NULL,NULL,'NAO','NAO','','INICIADA','Emissão de Declaração de aptidão ao pronaf',0.00,'04459471604','55637086672','04459471604','DAP','2020-07-08',NULL),(42,NULL,'2020-08-05 08:57:55.904000','2020-07-17',NULL,NULL,NULL,'NAO','NAO','','INICIADA','DAP - Emissão de Declaração de aptidão ao pronaf',0.00,NULL,'07730310732',NULL,'DAP','2020-07-15',NULL),(43,NULL,'2020-08-05 08:57:55.978000','2020-07-17',NULL,NULL,NULL,'NAO','NAO','','INICIADA','DAP - Emissão de Declaração de aptidão ao pronaf',0.00,NULL,'08898961600',NULL,'DAP','2020-07-15',NULL),(44,NULL,'2020-08-05 08:59:16.040000','2020-08-01',NULL,NULL,NULL,'NAO','NAO','','INICIADA','DAP - Emissão de Declaração de aptidão ao pronaf',0.00,NULL,'08412252659',NULL,'DAP','2020-07-30',NULL),(45,NULL,'2020-08-05 09:00:21.340000','2020-08-01',NULL,NULL,NULL,'NAO','NAO','','INICIADA','DAP - Emissão de Declaração de aptidão ao pronaf',0.00,NULL,'55775071620',NULL,'DAP','2020-07-30',NULL),(46,NULL,'2020-08-05 09:00:21.347000','2020-08-01',NULL,NULL,NULL,'NAO','NAO','','INICIADA','',0.00,NULL,'55775071620',NULL,'DAP','2020-07-30',NULL),(47,NULL,'2020-08-05 09:00:21.353000','2020-08-01',NULL,NULL,NULL,'NAO','NAO','','INICIADA','DAP - Emissão de Declaração de aptidão ao pronaf',0.00,NULL,'03069742661',NULL,'DAP','2020-07-30',NULL),(48,NULL,'2020-08-05 09:00:21.359000','2020-08-01',NULL,NULL,NULL,'NAO','NAO','','INICIADA','',0.00,NULL,'03069742661',NULL,'DAP','2020-07-30',NULL),(49,NULL,'2020-08-05 09:30:45.074000','2020-08-12',NULL,NULL,NULL,'NAO','NAO','','INICIADA','Banco do Brasil - Pronaf - Elaboração de projeto de crédito rural investimento',20.00,NULL,'94361258600',NULL,'CRIBBAF','2020-08-05',NULL),(50,NULL,'2020-08-05 09:36:45.770000','2020-08-06',NULL,NULL,NULL,'NAO','NAO','','INICIADA','Cadastro de uso insignificante/ emissão 2 via',0.00,NULL,'03249194689',NULL,'AGCAD','2020-08-05',NULL),(51,NULL,'2020-08-05 09:36:45.778000','2020-08-06',NULL,NULL,NULL,'NAO','NAO','','INICIADA','Cadastro de uso insignificante/ emissão 2 via',0.00,NULL,'08620572644',NULL,'AGCAD','2020-08-05',NULL),(52,NULL,'2020-08-05 15:53:00.250000','2020-08-07',NULL,NULL,NULL,'NAO','NAO','','INICIADA','Emissão de Declaração de aptidão ao pronaf',0.00,NULL,'24360961634',NULL,'DAP','2020-08-05',NULL),(53,NULL,'2020-08-05 15:53:00.289000','2020-08-07',NULL,NULL,NULL,'NAO','NAO','','INICIADA','Emissão de Declaração de aptidão ao pronaf',0.00,NULL,'18027717604',NULL,'DAP','2020-08-05',NULL),(54,NULL,'2020-08-06 17:02:57.406000','2020-08-07',NULL,NULL,NULL,'NAO','NAO','Enviado para atendimento em Sobrália','INICIADA','DAP - Consulta irregularidade em acesso a DAP',0.00,NULL,'11773394630',NULL,'DAPCS','2020-08-06',NULL),(55,NULL,'2020-08-10 17:15:22.480000','2020-08-12',NULL,NULL,NULL,'NAO','NAO','','INICIADA','Gleba - elaboração de Geo referenciamento',50.00,NULL,'79131646891',NULL,'GLB','2020-08-10',NULL),(56,NULL,'2020-08-11 15:12:28.347000','2020-08-13',NULL,NULL,NULL,'NAO','NAO','','INICIADA','DAP - Emissão de Declaração de aptidão ao pronaf',0.00,NULL,'01443134643',NULL,'DAP','2020-08-11',NULL),(57,NULL,'2020-08-11 15:12:28.414000','2020-08-13',NULL,NULL,NULL,'NAO','NAO','','INICIADA','DAP - Emissão de Declaração de aptidão ao pronaf',0.00,NULL,'05722708607',NULL,'DAP','2020-08-11',NULL),(58,NULL,'2020-08-11 16:17:52.217000','2020-08-12',NULL,NULL,NULL,'NAO','NAO','','INICIADA','DAP - Consulta irregularidade em acesso a DAP/ municipio Engenheiro',0.00,NULL,'99191261600',NULL,'DAPCS','2020-08-11',NULL),(59,NULL,'2020-08-11 16:17:52.237000','2020-08-12',NULL,NULL,NULL,'NAO','NAO','','INICIADA','DAP - Consulta irregularidade em acesso a DAP/ municipio Engenheiro',0.00,NULL,'04664277601',NULL,'DAPCS','2020-08-11',NULL),(60,NULL,'2020-08-12 09:30:31.648000','2020-08-19',NULL,NULL,NULL,'NAO','NAO','','INICIADA','Banco do Brasil - Pronaf - Elaboração de projeto de crédito rural investimento',20000.00,NULL,'30878454691',NULL,'CRIBBAF','2020-08-12',NULL),(61,NULL,'2020-08-13 09:13:11.406000','2020-08-14',NULL,NULL,NULL,'NAO','NAO','','INICIADA','Interpretação de análise de solo',0.00,NULL,'45717010630',NULL,'AMIS','2020-08-13',NULL),(62,NULL,'2020-08-13 16:30:30.002000','2020-08-14',NULL,NULL,NULL,'NAO','NAO','Ficou de trazer os documentos que estão faltando.','INICIADA','DAP - Consulta irregularidade em acesso a DAP',0.00,NULL,'89942370625',NULL,'DAPCS','2020-08-13',NULL),(63,NULL,'2020-08-13 16:30:30.030000','2020-08-15',NULL,NULL,NULL,'NAO','NAO','Ficou de trazer os documentos que estão faltando.','INICIADA','DAP - Emissão de Declaração de aptidão ao pronaf',0.00,NULL,'89942370625',NULL,'DAP','2020-08-13',NULL),(64,NULL,'2020-08-13 16:30:30.034000','2020-08-14',NULL,NULL,NULL,'NAO','NAO','Ficou de trazer os documentos que estão faltando.','INICIADA','ATER - Bovinocultura',0.00,NULL,'89942370625',NULL,'ATERBOV','2020-08-13',NULL),(65,NULL,'2020-08-13 16:30:30.037000','2020-08-14',NULL,NULL,NULL,'NAO','NAO','Ficou de trazer os documentos que estão faltando.','INICIADA','DAP - Consulta irregularidade em acesso a DAP',0.00,NULL,'07877993609',NULL,'DAPCS','2020-08-13',NULL),(66,NULL,'2020-08-13 16:30:30.043000','2020-08-15',NULL,NULL,NULL,'NAO','NAO','Ficou de trazer os documentos que estão faltando.','INICIADA','DAP - Emissão de Declaração de aptidão ao pronaf',0.00,NULL,'07877993609',NULL,'DAP','2020-08-13',NULL),(67,NULL,'2020-08-13 16:30:30.047000','2020-08-14',NULL,NULL,NULL,'NAO','NAO','Ficou de trazer os documentos que estão faltando.','INICIADA','ATER - Bovinocultura',0.00,NULL,'07877993609',NULL,'ATERBOV','2020-08-13',NULL);
/*!40000 ALTER TABLE `atendimento` ENABLE KEYS */;
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
