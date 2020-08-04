CREATE TABLE `persona` (
  `cpf` varchar(255) NOT NULL,
  `coclusao_cadastro` int(11) DEFAULT NULL,
  `data_atualizacao` datetime(6) DEFAULT NULL,
  `data_cadastro` datetime(6) DEFAULT NULL,
  `data_nascimento` date DEFAULT NULL,
  `fone` varchar(255) DEFAULT NULL,
  `id` bigint(20) DEFAULT NULL,
  `nome` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `permissao` varchar(255) DEFAULT NULL,
  `tipo` int(11) DEFAULT NULL,
  `categoria` varchar(255) DEFAULT NULL,
  `cpf_cadastrante` varchar(255) DEFAULT NULL,
  `endereco` varchar(255) DEFAULT NULL,
  `escolaridade` varchar(255) DEFAULT NULL,
  `municipio` varchar(255) DEFAULT NULL,
  `sexo` int(11) DEFAULT NULL,
  PRIMARY KEY (`cpf`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

