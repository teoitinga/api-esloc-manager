CREATE TABLE `eslocdb`.`produto` (
  `id` VARCHAR(25) NOT NULL,
  `produto_dsc` VARCHAR(45) NOT NULL,
  `unidade` VARCHAR(15) NOT NULL,
  `peso_por_quilo` DECIMAL(5,2) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `produto_UNIQUE` (`produto_dsc` ASC) VISIBLE
  );
  
INSERT INTO `eslocdb`.`produto` (`id`, `produto_dsc`, `unidade`, `peso_por_quilo`) VALUES 
('CFA', 'Café Arábica', '@', 1),
('CFC', 'Café conilon', '@', 1),
('FBNG', 'Banana', 'Kg', 1),
('BLLT1', 'Leite', 'lt', 1),
('BCBZ1', 'Bezerros', '@', 1),
('BCGR1', 'Garrotes', '@', 1),
('BVCD', 'Vacas', 'Cab', 1),
('BLQJC', 'Queijo', 'kg', 1),
('BLRQ', 'Requeijão', 'kg', 1),
('ARP', 'Rapadura', 'Kg', 1),
('AMC', 'Melaço de cana', 'lt', 1),
('HHRTG', 'Hortaliças', 'kg', 1),
('FMRC', 'Maracujá', 'Kg', 1),
('HQB', 'Quiabo', 'Kg', 1),
('HJL', 'Jiló', 'Kg', 1),
('HPP', 'Pepino', 'Kg', 1),
('HPM', 'Pimentão', 'Kg', 1),
('CPCN', 'Cana-de-açucar', 'ton', 1),
('ADC', 'Doces', 'Kg', 1),
('HMLV', 'Milho verde', 'Kg', 1),
('BMLS', 'Milho silgagem', 'Kg', 1),
('CPRMLG', 'Milho grão', 'Kg', 1);