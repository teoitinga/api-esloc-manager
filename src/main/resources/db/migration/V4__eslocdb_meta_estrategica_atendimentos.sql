-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: eslocdb
-- ------------------------------------------------------
-- Server version	8.0.16

--
-- Table structure for table `meta_estrategica_atendimentos`
--
CREATE TABLE `meta_estrategica` (
  `codigo` varchar(10) NOT NULL,
  `indicador` varchar(50) NOT NULL,
  `peso` int(255) NOT NULL,
  PRIMARY KEY (`codigo`)
) ;

