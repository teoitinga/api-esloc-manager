CREATE TABLE `eslocdb`.`declaracao_de_renda` (
  `id` VARCHAR(25) NOT NULL,
  `produto_fk` VARCHAR(10) NOT NULL,
  `reg_renda_fk` VARCHAR(25) NOT NULL,
  `descricao` VARCHAR(25) NOT NULL,
  `rendaAnual` DECIMAL(5,2) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `produto_UNIQUE` (`id` ASC) VISIBLE);