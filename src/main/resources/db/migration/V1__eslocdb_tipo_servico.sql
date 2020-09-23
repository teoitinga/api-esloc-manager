-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema jp-esloc
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `jp-esloc` ;

-- -----------------------------------------------------
-- Schema jp-esloc
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `jp-esloc` DEFAULT CHARACTER SET utf8 ;
USE `jp-esloc` ;

-- -----------------------------------------------------
-- Table `jp-esloc`.`PERSONA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jp-esloc`.`PERSONA` ;

CREATE TABLE IF NOT EXISTS `jp-esloc`.`PERSONA` (
  `cpf` VARCHAR(30) NOT NULL,
  `nome` VARCHAR(145) NULL,
  `contato` VARCHAR(45) NULL,
  `nascimento` DATE NULL,
  `cadastro` DATE NULL,
  `atualizacao` DATE NULL,
  `categoria` VARCHAR(45) NULL,
  `permissao` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `municipio` VARCHAR(45) NULL,
  `sexo` VARCHAR(20) NULL,
  `cadastrante_cpf` VARCHAR(30) NULL,
  `escolaridade` VARCHAR(45) NULL,
  `index_conclusao` INT NULL,
  `conselho_registro` VARCHAR(45) NULL,
  PRIMARY KEY (`cpf`),
  UNIQUE INDEX `cpf_UNIQUE` (`cpf` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jp-esloc`.`ATENDIMENTO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jp-esloc`.`ATENDIMENTO` ;

CREATE TABLE IF NOT EXISTS `jp-esloc`.`ATENDIMENTO` (
  `codigo` VARCHAR(45) NOT NULL,
  `recomendacoes` BLOB NULL,
  `cadastro` DATE NULL,
  `atualizacao` DATE NULL,
  `data_atendimento` DATE NULL,
  `status_atendimento` VARCHAR(45) NULL,
  `publicar` TINYINT NULL,
  `produtor` VARCHAR(30) NOT NULL,
  `responsavel_tecnico` VARCHAR(30) NOT NULL,
  `emissor` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`codigo`),
  UNIQUE INDEX `codigo_UNIQUE` (`codigo` ASC) VISIBLE,
  INDEX `fk_ATENDIMENTO_PERSONA_idx` (`produtor` ASC) VISIBLE,
  INDEX `fk_ATENDIMENTO_PERSONA1_idx` (`responsavel_tecnico` ASC) VISIBLE,
  INDEX `fk_ATENDIMENTO_PERSONA2_idx` (`emissor` ASC) INVISIBLE,
  CONSTRAINT `fk_ATENDIMENTO_PERSONA`
    FOREIGN KEY (`produtor`)
    REFERENCES `jp-esloc`.`PERSONA` (`cpf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ATENDIMENTO_PERSONA1`
    FOREIGN KEY (`responsavel_tecnico`)
    REFERENCES `jp-esloc`.`PERSONA` (`cpf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ATENDIMENTO_PERSONA2`
    FOREIGN KEY (`emissor`)
    REFERENCES `jp-esloc`.`PERSONA` (`cpf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jp-esloc`.`SERVICOS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jp-esloc`.`SERVICOS` ;

CREATE TABLE IF NOT EXISTS `jp-esloc`.`SERVICOS` (
  `codigo` VARCHAR(45) NOT NULL,
  `descricao` VARCHAR(45) NULL,
  `tempo_estimado` INT NULL,
  `valor_estimado` DECIMAL(6,2) NULL,
  `definicao` VARCHAR(150) NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jp-esloc`.`SERVICOS_ATD`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jp-esloc`.`SERVICOS_ATD` ;

CREATE TABLE IF NOT EXISTS `jp-esloc`.`SERVICOS_ATD` (
  `codigo` VARCHAR(45) NOT NULL,
  `valor_total_servico` DECIMAL(6,2) NULL,
  `valor_dae` DECIMAL(6,2) NULL,
  `observacoes` VARCHAR(45) NULL,
  `SERVICOS_codigo` VARCHAR(45) NOT NULL,
  `ATENDIMENTO_codigo` VARCHAR(45) NOT NULL,
  `servico_descricao` VARCHAR(45) NULL,
  PRIMARY KEY (`codigo`, `ATENDIMENTO_codigo`),
  INDEX `fk_SERVICOS_ATD_SERVICOS1_idx` (`SERVICOS_codigo` ASC) VISIBLE,
  INDEX `fk_SERVICOS_ATD_ATENDIMENTO1_idx` (`ATENDIMENTO_codigo` ASC) VISIBLE,
  CONSTRAINT `fk_SERVICOS_ATD_SERVICOS1`
    FOREIGN KEY (`SERVICOS_codigo`)
    REFERENCES `jp-esloc`.`SERVICOS` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_SERVICOS_ATD_ATENDIMENTO1`
    FOREIGN KEY (`ATENDIMENTO_codigo`)
    REFERENCES `jp-esloc`.`ATENDIMENTO` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jp-esloc`.`MILK_PRICE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jp-esloc`.`MILK_PRICE` ;

CREATE TABLE IF NOT EXISTS `jp-esloc`.`MILK_PRICE` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `cadastro` DATE NULL,
  `valor_por_unidade` DECIMAL(6,2) NULL,
  `emissor` VARCHAR(30) NOT NULL,
  `produtor_info` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `fk_MILK_PRICE_PERSONA1_idx` (`emissor` ASC) VISIBLE,
  INDEX `fk_MILK_PRICE_PERSONA2_idx` (`produtor_info` ASC) VISIBLE,
  CONSTRAINT `fk_MILK_PRICE_PERSONA1`
    FOREIGN KEY (`emissor`)
    REFERENCES `jp-esloc`.`PERSONA` (`cpf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_MILK_PRICE_PERSONA2`
    FOREIGN KEY (`produtor_info`)
    REFERENCES `jp-esloc`.`PERSONA` (`cpf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '		';


-- -----------------------------------------------------
-- Table `jp-esloc`.`BOI_PRICE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jp-esloc`.`BOI_PRICE` ;

CREATE TABLE IF NOT EXISTS `jp-esloc`.`BOI_PRICE` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `cadastro` DATE NULL,
  `valor_por_unidade` DECIMAL(6,2) NULL,
  `categoria_animal` VARCHAR(45) NULL,
  `emissor` VARCHAR(30) NOT NULL,
  `produtor_info` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `fk_BOI_PRICE_PERSONA1_idx` (`emissor` ASC) VISIBLE,
  INDEX `fk_BOI_PRICE_PERSONA2_idx` (`produtor_info` ASC) VISIBLE,
  CONSTRAINT `fk_BOI_PRICE_PERSONA1`
    FOREIGN KEY (`emissor`)
    REFERENCES `jp-esloc`.`PERSONA` (`cpf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_BOI_PRICE_PERSONA2`
    FOREIGN KEY (`produtor_info`)
    REFERENCES `jp-esloc`.`PERSONA` (`cpf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jp-esloc`.`BANCOS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jp-esloc`.`BANCOS` ;

CREATE TABLE IF NOT EXISTS `jp-esloc`.`BANCOS` (
  `agencia` VARCHAR(45) NOT NULL,
  `endereco` VARCHAR(45) NULL,
  `municipio` VARCHAR(45) NULL,
  `nome_gerente` VARCHAR(45) NULL,
  `fone_comercial` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `fone_gerente` VARCHAR(45) NULL,
  `agencia_nome` VARCHAR(45) NULL,
  `nome` VARCHAR(45) NULL,
  PRIMARY KEY (`agencia`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jp-esloc`.`PROJETOS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jp-esloc`.`PROJETOS` ;

CREATE TABLE IF NOT EXISTS `jp-esloc`.`PROJETOS` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `cadastro` DATE NULL,
  `atualizado` DATE NULL,
  `valor_orcado` DECIMAL(6,2) NULL,
  `valor_financiado` DECIMAL(6,2) NULL,
  `data_pagamento` DATE NULL,
  `qtd_parcelas` INT NULL,
  `tx_juros` DECIMAL(6,2) NULL,
  `produtor_cpf` VARCHAR(30) NOT NULL,
  `responsavel_tecnico` VARCHAR(30) NOT NULL,
  `emissor` VARCHAR(30) NOT NULL,
  `BANCOS_agencia` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `fk_PROJETOS_PERSONA1_idx` (`produtor_cpf` ASC) VISIBLE,
  INDEX `fk_PROJETOS_PERSONA2_idx` (`responsavel_tecnico` ASC) VISIBLE,
  INDEX `fk_PROJETOS_PERSONA3_idx` (`emissor` ASC) VISIBLE,
  INDEX `fk_PROJETOS_BANCOS1_idx` (`BANCOS_agencia` ASC) VISIBLE,
  CONSTRAINT `fk_PROJETOS_PERSONA1`
    FOREIGN KEY (`produtor_cpf`)
    REFERENCES `jp-esloc`.`PERSONA` (`cpf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PROJETOS_PERSONA2`
    FOREIGN KEY (`responsavel_tecnico`)
    REFERENCES `jp-esloc`.`PERSONA` (`cpf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PROJETOS_PERSONA3`
    FOREIGN KEY (`emissor`)
    REFERENCES `jp-esloc`.`PERSONA` (`cpf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PROJETOS_BANCOS1`
    FOREIGN KEY (`BANCOS_agencia`)
    REFERENCES `jp-esloc`.`BANCOS` (`agencia`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jp-esloc`.`ITENS_FINANCIAVEIS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jp-esloc`.`ITENS_FINANCIAVEIS` ;

CREATE TABLE IF NOT EXISTS `jp-esloc`.`ITENS_FINANCIAVEIS` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(45) NULL,
  `unidade` VARCHAR(45) NULL,
  `texto_prefixo` VARCHAR(45) NULL,
  `texto_sufixo` VARCHAR(45) NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jp-esloc`.`ITENS_FINANCIADOS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jp-esloc`.`ITENS_FINANCIADOS` ;

CREATE TABLE IF NOT EXISTS `jp-esloc`.`ITENS_FINANCIADOS` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `ITENS_FINANCIAVEIS_codigo` INT NOT NULL,
  `descricao` VARCHAR(45) NULL,
  `quantidade` VARCHAR(45) NULL,
  `valor_orcado` DECIMAL(6,2) NULL,
  `valor_financiado` DECIMAL(6,2) NULL,
  `PROJETOS_codigo` INT NOT NULL,
  PRIMARY KEY (`codigo`, `PROJETOS_codigo`),
  INDEX `fk_ITENS_FINANCIADOS_PROJETOS1_idx` (`PROJETOS_codigo` ASC) VISIBLE,
  INDEX `fk_ITENS_FINANCIADOS_ITENS_FINANCIAVEIS1_idx` (`ITENS_FINANCIAVEIS_codigo` ASC) VISIBLE,
  CONSTRAINT `fk_ITENS_FINANCIADOS_PROJETOS1`
    FOREIGN KEY (`PROJETOS_codigo`)
    REFERENCES `jp-esloc`.`PROJETOS` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ITENS_FINANCIADOS_ITENS_FINANCIAVEIS1`
    FOREIGN KEY (`ITENS_FINANCIAVEIS_codigo`)
    REFERENCES `jp-esloc`.`ITENS_FINANCIAVEIS` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jp-esloc`.`PROPRIEDADE_RURAL`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jp-esloc`.`PROPRIEDADE_RURAL` ;

CREATE TABLE IF NOT EXISTS `jp-esloc`.`PROPRIEDADE_RURAL` (
  `ccir` INT NOT NULL,
  `nome` VARCHAR(45) NULL,
  `area_total` DECIMAL(6,2) NULL,
  `recibo_car` VARCHAR(45) NULL,
  `nirf` VARCHAR(45) NULL,
  `latitude` VARCHAR(45) NULL,
  `longitude` VARCHAR(45) NULL,
  `matricula` VARCHAR(45) NULL,
  `condicao_de_posse` VARCHAR(45) NULL,
  `roteiro_de_acesso` MEDIUMTEXT NULL,
  `proprietario_cpf` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`ccir`, `proprietario_cpf`),
  INDEX `fk_PROPRIEDADE_RURAL_PERSONA1_idx` (`proprietario_cpf` ASC) VISIBLE,
  CONSTRAINT `fk_PROPRIEDADE_RURAL_PERSONA1`
    FOREIGN KEY (`proprietario_cpf`)
    REFERENCES `jp-esloc`.`PERSONA` (`cpf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jp-esloc`.`RENDA_FAMILIAR_ANUAL`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jp-esloc`.`RENDA_FAMILIAR_ANUAL` ;

CREATE TABLE IF NOT EXISTS `jp-esloc`.`RENDA_FAMILIAR_ANUAL` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `cadastro` DATE NULL,
  `atualizacao` DATE NULL,
  `RENDA_FAMILIAR_ANUALcol` VARCHAR(45) NULL,
  `emissor` VARCHAR(30) NOT NULL,
  `responsavel_tecnico` VARCHAR(30) NOT NULL,
  `unidade_familiar` INT NOT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `fk_RENDA_FAMILIAR_ANUAL_PERSONA2_idx` (`emissor` ASC) VISIBLE,
  INDEX `fk_RENDA_FAMILIAR_ANUAL_PERSONA3_idx` (`responsavel_tecnico` ASC) VISIBLE,
  INDEX `fk_RENDA_FAMILIAR_ANUAL_PROPRIEDADE_RURAL1_idx` (`unidade_familiar` ASC) VISIBLE,
  CONSTRAINT `fk_RENDA_FAMILIAR_ANUAL_PERSONA2`
    FOREIGN KEY (`emissor`)
    REFERENCES `jp-esloc`.`PERSONA` (`cpf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_RENDA_FAMILIAR_ANUAL_PERSONA3`
    FOREIGN KEY (`responsavel_tecnico`)
    REFERENCES `jp-esloc`.`PERSONA` (`cpf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_RENDA_FAMILIAR_ANUAL_PROPRIEDADE_RURAL1`
    FOREIGN KEY (`unidade_familiar`)
    REFERENCES `jp-esloc`.`PROPRIEDADE_RURAL` (`ccir`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jp-esloc`.`GRUPO_PRODUCAO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jp-esloc`.`GRUPO_PRODUCAO` ;

CREATE TABLE IF NOT EXISTS `jp-esloc`.`GRUPO_PRODUCAO` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(45) NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jp-esloc`.`PRODUCAO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jp-esloc`.`PRODUCAO` ;

CREATE TABLE IF NOT EXISTS `jp-esloc`.`PRODUCAO` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(45) NULL,
  `unidade` VARCHAR(45) NULL,
  `GRUPO_PRODUCAO_codigo` INT NOT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `fk_PRODUCAO_GRUPO_PRODUCAO1_idx` (`GRUPO_PRODUCAO_codigo` ASC) VISIBLE,
  CONSTRAINT `fk_PRODUCAO_GRUPO_PRODUCAO1`
    FOREIGN KEY (`GRUPO_PRODUCAO_codigo`)
    REFERENCES `jp-esloc`.`GRUPO_PRODUCAO` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '	';


-- -----------------------------------------------------
-- Table `jp-esloc`.`PRODUCAO_FAMILIAR`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jp-esloc`.`PRODUCAO_FAMILIAR` ;

CREATE TABLE IF NOT EXISTS `jp-esloc`.`PRODUCAO_FAMILIAR` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `quantidade_por_unidade` DECIMAL(6,2) NULL,
  `valor_por_unidade` DECIMAL(6,2) NULL,
  `renda_estimada` DECIMAL(6,2) NULL,
  `renda_auferida` DECIMAL(6,2) NULL,
  `observacoes` VARCHAR(45) NULL,
  `PRODUCAO_codigo` INT NOT NULL,
  `RENDA_FAMILIAR_ANUAL_codigo` INT NOT NULL,
  PRIMARY KEY (`codigo`, `RENDA_FAMILIAR_ANUAL_codigo`),
  INDEX `fk_PRODUCAO_FAMILIAR_PRODUCAO1_idx` (`PRODUCAO_codigo` ASC) VISIBLE,
  INDEX `fk_PRODUCAO_FAMILIAR_RENDA_FAMILIAR_ANUAL1_idx` (`RENDA_FAMILIAR_ANUAL_codigo` ASC) VISIBLE,
  CONSTRAINT `fk_PRODUCAO_FAMILIAR_PRODUCAO1`
    FOREIGN KEY (`PRODUCAO_codigo`)
    REFERENCES `jp-esloc`.`PRODUCAO` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PRODUCAO_FAMILIAR_RENDA_FAMILIAR_ANUAL1`
    FOREIGN KEY (`RENDA_FAMILIAR_ANUAL_codigo`)
    REFERENCES `jp-esloc`.`RENDA_FAMILIAR_ANUAL` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jp-esloc`.`LINHA_DE_CREDITO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jp-esloc`.`LINHA_DE_CREDITO` ;

CREATE TABLE IF NOT EXISTS `jp-esloc`.`LINHA_DE_CREDITO` (
  `codigo` INT NOT NULL,
  `nome` VARCHAR(45) NULL,
  `taxa_juros_anual` DECIMAL(6,2) NULL,
  `prazo` INT NULL,
  `PROJETOS_codigo` INT NOT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `fk_LINHA_DE_CREDITO_PROJETOS1_idx` (`PROJETOS_codigo` ASC) VISIBLE,
  CONSTRAINT `fk_LINHA_DE_CREDITO_PROJETOS1`
    FOREIGN KEY (`PROJETOS_codigo`)
    REFERENCES `jp-esloc`.`PROJETOS` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
