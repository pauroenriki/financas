package com.reis.financas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reis.financas.domain.CategoriaSaida;

public interface CategoriaSaidaRepository extends JpaRepository<CategoriaSaida, Long>{

	List<CategoriaSaida> findByDescricao(String descricao);

}
