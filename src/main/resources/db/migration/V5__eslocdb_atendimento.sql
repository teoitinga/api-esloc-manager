-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: eslocdb
-- ------------------------------------------------------
-- Server version	8.0.16

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
  `emitiuart` varchar(10) DEFAULT NULL,
  `emitiudae` varchar(10) DEFAULT NULL,
  `observacoes` varchar(255) DEFAULT NULL,
  `status_tarefa` varchar(20) DEFAULT NULL,
  `tarefa_descricao` varchar(255) DEFAULT NULL,
  `valor_do_servico` decimal(10,2) DEFAULT NULL,
  `valor_do_dae` decimal(10,2) DEFAULT NULL,
  `emissor_cpf` varchar(15) DEFAULT NULL,
  `produtor_cpf` varchar(15) NOT NULL,
  `responsavel_cpf` varchar(15) DEFAULT NULL,
  `fk_service` varchar(50) DEFAULT NULL,
  `data_atendimento` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKq396gkcu6rpk42av549cm7vdl` (`emissor_cpf`),
  KEY `FKrt9fvw5orr2mw8opf26yad5f6` (`produtor_cpf`),
  KEY `FKfuo5cfkopk8ei9nl1e2ai7js` (`responsavel_cpf`),
  KEY `FK5s91w04xlouj3euv2ljj7rrl3` (`fk_service`),
  CONSTRAINT `FK5s91w04xlouj3euv2ljj7rrl3` FOREIGN KEY (`fk_service`) REFERENCES `tipo_servico` (`tipo`),
  CONSTRAINT `FKfuo5cfkopk8ei9nl1e2ai7js` FOREIGN KEY (`responsavel_cpf`) REFERENCES `persona` (`cpf`),
  CONSTRAINT `FKq396gkcu6rpk42av549cm7vdl` FOREIGN KEY (`emissor_cpf`) REFERENCES `persona` (`cpf`),
  CONSTRAINT `FKrt9fvw5orr2mw8opf26yad5f6` FOREIGN KEY (`produtor_cpf`) REFERENCES `persona` (`cpf`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

