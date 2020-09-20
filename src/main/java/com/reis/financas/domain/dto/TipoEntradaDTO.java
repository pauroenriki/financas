package com.reis.financas.domain.dto;

import java.util.Optional;

import org.modelmapper.ModelMapper;

import com.reis.financas.domain.TipoEntrada;

public class TipoEntradaDTO {

	private Long id;
	private String descricao;
	
	public static TipoEntradaDTO create(TipoEntrada tipo) {
		ModelMapper mp = new ModelMapper();
		return mp.map(tipo, TipoEntradaDTO.class);
	}
	
	public static TipoEntrada decode(TipoEntradaDTO dto) {
		ModelMapper mp = new ModelMapper();
		return mp.map(dto, TipoEntrada.class);
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
