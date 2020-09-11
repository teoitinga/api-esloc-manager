CREATE TABLE `eslocdb`.`registro_dec_renda` (
  `id` VARCHAR(25) NOT NULL,
  `produtor_fk` VARCHAR(15) NOT NULL,
  `data_atual` datetime(6),
  `data_atendimento` datetime(6),
  `data_atualizacao` datetime(6),
  `emissor_fk` VARCHAR(15),
  PRIMARY KEY (`id`)
  );