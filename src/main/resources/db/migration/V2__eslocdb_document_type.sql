CREATE TABLE `document_type` (
  `descricao` varchar(255) NOT NULL,
  `abreviatura` varchar(255) DEFAULT NULL,
  `id` bigint(20) DEFAULT NULL,
  `informacoes` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`descricao`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

