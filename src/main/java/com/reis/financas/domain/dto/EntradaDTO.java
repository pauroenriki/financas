package com.reis.financas.domain.dto;

import java.util.Date;

import org.modelmapper.ModelMapper;

import com.reis.financas.domain.Entrada;
import com.reis.financas.domain.TipoEntrada;
import com.reis.financas.domain.Usuario;

public class EntradaDTO {

	private Long id;
	private Date data;
	private Double valor;
	private Usuario usuario;
	private TipoEntrada tipoEntrada;
	
	public static EntradaDTO create(Entrada entrada) {
		ModelMapper mp = new ModelMapper();
		return mp.map(entrada, EntradaDTO.class);
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public TipoEntrada getTipoEntrada() {
		return tipoEntrada;
	}

	public void setTipoEntrada(TipoEntrada tipoEntrada) {
		this.tipoEntrada = tipoEntrada;
	}


	
	
}
