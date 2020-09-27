package com.reis.financas.domain.dto;

import org.modelmapper.ModelMapper;

import com.reis.financas.domain.FormaPagamento;

public class FormaPagamentoDTO {
	private Long id;
	private String descricao;
	
	public static FormaPagamentoDTO create(FormaPagamento pgto) {
		ModelMapper mp = new ModelMapper();
		return mp.map(pgto, FormaPagamentoDTO.class);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
