package com.reis.financas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reis.financas.domain.TipoEntrada;

public interface TipoEntradaRepository extends JpaRepository<TipoEntrada, Long>{

	List<TipoEntrada> findByDescricao(String descricao);

}
