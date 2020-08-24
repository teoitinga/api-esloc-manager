ALTER TABLE `eslocdb`.`atendimento` 
ADD COLUMN `tornar_publico` TINYINT NULL AFTER `data_atendimento`,
CHANGE COLUMN `observacoes` `recomendacoes` TEXT NULL DEFAULT NULL ;