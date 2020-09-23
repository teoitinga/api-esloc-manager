package com.jp.eslocapi.api.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jp.eslocapi.api.entities.Atendimento;
import com.jp.eslocapi.api.entities.EnumStatus;

public interface AtendimentosRepository extends JpaRepository<Atendimento, Long> {

	@Query(value = "SELECT * FROM atendimento a where a.data_cadastro >= :inicio and a.data_cadastro <= :fim and a.emissor_cpf = :cpf order by a.id DESC", nativeQuery = true)
	Optional<List<Atendimento>> meusLancamentosHoje(@Param("cpf") String cpf, LocalDate inicio, LocalDate fim);

	@Modifying
	@Transactional
	@Query("update Atendimento a set a.statusTarefa = :status where a.id = :id")
	void updateStatus(@Param("id") Long id, @Param("status") EnumStatus status);
	
	@Modifying
	@Transactional
	@Query("update Atendimento a set a.responsavel = :cpf where a.id = :id")
	void updateResponsavel(Long id, String cpf);

	@Query(value = "SELECT * FROM atendimento a where a.emissor_cpf = :cpf and a.status_tarefa = :status order by a.id DESC", nativeQuery = true)
	Optional<List<Atendimento>> meusAtendimentos(String status, String cpf);

	@Query(value = "SELECT a FROM Atendimento a where a.tornarPublico is null order by a.dataCadastro DESC")
	Page<Atendimento> findAllOrderByDataCadastroDesc(Pageable pageable);

	@Query(value = "SELECT a FROM Atendimento a where (a.responsavel = :cpf) and ((a.statusTarefa != 'FINALIZADA') and (a.statusTarefa != 'CANCELADA'))  order by a.dataCadastro DESC")
	Page<Atendimento> findMyAtendimentosAConcluir( @Param("cpf") String cpf, Pageable pageable);


}
