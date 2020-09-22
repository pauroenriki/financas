package com.reis.financas.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.reis.financas.domain.CategoriaSaida;
import com.reis.financas.domain.Saida;
import com.reis.financas.domain.Usuario;

public interface SaidaRepository extends JpaRepository<Saida, Long>{
	List<Saida> findByCategoriaSaida(CategoriaSaida categoria);
	List<Saida> findByUsuario(Usuario usuario);
	@Query(value = "from Saida t where t.dataVencimento BETWEEN :startDate AND :endDate order by t.dataVencimento")
	List<Saida> getAllBetweenDates(@Param("startDate")Date startDate,@Param("endDate")Date endDate);
}
