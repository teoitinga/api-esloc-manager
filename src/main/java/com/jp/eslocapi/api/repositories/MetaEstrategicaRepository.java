package com.jp.eslocapi.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jp.eslocapi.api.entities.MetaEstrategica;

public interface MetaEstrategicaRepository extends JpaRepository<MetaEstrategica, Long> {

	Optional<MetaEstrategica> findByCodigo(String codigo);

}
