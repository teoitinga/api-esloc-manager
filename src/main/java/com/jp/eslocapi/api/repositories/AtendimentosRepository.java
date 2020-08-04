package com.jp.eslocapi.api.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jp.eslocapi.api.entities.Atendimento;

public interface AtendimentosRepository extends JpaRepository<Atendimento, Long> {

	@Query(value = "SELECT * FROM atendimento a where a.data_cadastro > :inicio and a.data_cadastro < :fim and a.emissor_cpf = :cpf order by a.id DESC", nativeQuery = true)
	List<Atendimento> meusLancamentosHoje(@Param("cpf") String cpf, LocalDate inicio, LocalDate fim);

}
