package com.reis.financas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reis.financas.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

	List<Usuario> findByLogin(String login);

}
