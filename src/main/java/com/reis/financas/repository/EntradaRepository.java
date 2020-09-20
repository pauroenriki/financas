package com.reis.financas.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.reis.financas.domain.Entrada;
import com.reis.financas.domain.Saida;
import com.reis.financas.domain.TipoEntrada;
import com.reis.financas.domain.Usuario;

public interface EntradaRepository extends JpaRepository<Entrada, Long>{

	List<Entrada> findByTipoEntrada(TipoEntrada tipo);
	List<Entrada> findByUsuario(Usuario usuario);
	@Query(value = "from Entrada t where data BETWEEN :startDate AND :endDate")
	List<Entrada> getAllBetweenDates(@Param("startDate")Date startDate,@Param("endDate")Date endDate);
}
