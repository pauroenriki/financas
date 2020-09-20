package com.reis.financas.domain.dto;

import org.modelmapper.ModelMapper;

import com.reis.financas.domain.TipoEntrada;
import com.reis.financas.domain.Usuario;

public class UsuarioDTO {
	private Long id;
	private String nome;
	private String login;
	private String senha;
	private String email;
	
	public static UsuarioDTO create(Usuario usuario) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(usuario, UsuarioDTO.class);
	}
	
	public static Usuario decode(UsuarioDTO usuarioDTO) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(usuarioDTO, Usuario.class);
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
