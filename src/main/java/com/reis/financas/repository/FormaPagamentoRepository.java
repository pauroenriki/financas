package com.reis.financas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reis.financas.domain.FormaPagamento;
import com.reis.financas.domain.dto.FormaPagamentoDTO;

public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long>{

	List<FormaPagamento> findByDescricao(String descricao);

}
