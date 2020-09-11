CREATE TABLE `eslocdb`.`propriedade_rural` (
  `id` VARCHAR(25) NOT NULL,
  `produtor_fk` VARCHAR(15) NOT NULL,
  `data_atual` datetime(6),
  `data_atendimento` datetime(6),
  `data_atualizacao` datetime(6),
  `emissor_fk` VARCHAR(15),
  `area_total` decimal(10,2),
  `latitude` VARCHAR(15),
  `longitude` VARCHAR(15),
  `municipio` VARCHAR(15),
   PRIMARY KEY (`id`)
  );