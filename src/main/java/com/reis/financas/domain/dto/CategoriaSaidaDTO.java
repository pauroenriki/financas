package com.reis.financas.domain.dto;

import org.modelmapper.ModelMapper;

import com.reis.financas.domain.CategoriaSaida;

public class CategoriaSaidaDTO {

	private Long id;
	private String descricao;
	
	public static CategoriaSaidaDTO create(CategoriaSaida categoria) {
		ModelMapper mp = new ModelMapper();
		return mp.map(categoria, CategoriaSaidaDTO.class);
	}
	
	public static CategoriaSaida decode(CategoriaSaidaDTO categoriaSaidaDTO) {
		ModelMapper mp = new ModelMapper();
		return mp.map(categoriaSaidaDTO, CategoriaSaida.class);
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
	
	@Override
	public String toString() {
		return this.id + " - " + this.descricao;
	}
}
