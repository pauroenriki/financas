package com.reis.financas.domain.dto;

import java.util.Date;

import org.modelmapper.ModelMapper;

import com.reis.financas.domain.CategoriaSaida;
import com.reis.financas.domain.Saida;
import com.reis.financas.domain.StatusEnum;
import com.reis.financas.domain.Usuario;

public class SaidaDTO {

	private Long id;
	private Date data;
	private String descricao;
	private Double valor;
	private boolean vr;
	private Date dataVencimento;
	private StatusEnum status;
	private CategoriaSaida categoriaSaida;
	private Usuario usuario;
	
	public static SaidaDTO create(Saida saida) {
		ModelMapper mp = new ModelMapper();
		return mp.map(saida, SaidaDTO.class);
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

	
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public CategoriaSaida getCategoriaSaida() {
		return categoriaSaida;
	}

	public void setCategoriaSaida(CategoriaSaida categoriaSaida) {
		this.categoriaSaida = categoriaSaida;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Double getValor() {
		return valor;
	}
	
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	
	public boolean isVr() {
		return vr;
	}

	public void setVr(boolean vr) {
		this.vr = vr;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}
	
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}
	
}
