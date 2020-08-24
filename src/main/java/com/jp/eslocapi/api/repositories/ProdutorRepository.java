package com.jp.eslocapi.api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jp.eslocapi.api.entities.Persona;

public interface ProdutorRepository extends JpaRepository<Persona, Long> {

	boolean existsByCpf(String cpf);

	Optional<Persona> findByCpf(String cpf);
	@Query("select p from Persona p where (p.nome like %:nome%) and ((p.permissao = 'TECNICO')  or (p.permissao = 'CEDIDO'))")
	List<Persona> findByNomeContainingOderByNomeDesc(@Param("nome") String nome);

}
