CREATE TABLE `persona` (
  `id` bigint(20) DEFAULT NULL,
  `cpf` varchar(12) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `fone` varchar(30) DEFAULT NULL,
  `data_nascimento` date DEFAULT NULL,
  `data_cadastro` datetime(6) DEFAULT NULL,
  `data_atualizacao` datetime(6) DEFAULT NULL,
  `categoria` varchar(30) DEFAULT NULL,
  `permissao` varchar(30) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `municipio` varchar(50) DEFAULT NULL,
  `endereco` varchar(255) DEFAULT NULL,
  `cpf_cadastrante` varchar(12) DEFAULT NULL,
  `sexo` varchar(30) DEFAULT NULL,
  `escolaridade` varchar(50) DEFAULT NULL,
  `conclusao_cadastro` int(2) DEFAULT NULL,
  PRIMARY KEY (`cpf`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;