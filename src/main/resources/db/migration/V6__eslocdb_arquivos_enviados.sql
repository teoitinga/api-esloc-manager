-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: eslocdb
-- ------------------------------------------------------
-- Server version	8.0.16

--
-- Table structure for table `arquivos_enviados`
--
CREATE TABLE `eslocdb`.`arquivos_enviados` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `desccricao` VARCHAR(45) NULL COMMENT 'Breve descrição sobre o documento',
  `data_envio` DATETIME NULL COMMENT 'data que o documento foi enviado',
  `emissor` VARCHAR(15) NULL COMMENT 'CPF do responsável pelo envio do documento',
  `atendimento_id` VARCHAR(45) NULL COMMENT 'Identificador do Atendimento a que se refere este documento',
  `tipo_documento` VARCHAR(45) NULL COMMENT 'Abreviatura do tipo de documento enviado.',
  PRIMARY KEY (`id`))
COMMENT = 'Gerencia o envio de arquivos através da API';

