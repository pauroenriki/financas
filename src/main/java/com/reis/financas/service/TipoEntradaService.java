package com.reis.financas.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.reis.financas.domain.TipoEntrada;
import com.reis.financas.domain.dto.TipoEntradaDTO;
import com.reis.financas.repository.TipoEntradaRepository;

@Service
public class TipoEntradaService {

	@Autowired
	private TipoEntradaRepository rep;
	
	public List<TipoEntradaDTO> buscarTodos(){
		return rep.findAll().stream().map(TipoEntradaDTO::create).collect(Collectors.toList());
	}
	
	public Optional<TipoEntradaDTO> getTipoEntradaById(Long id) {
		if(id == null) {
			throw new IllegalArgumentException("ID não pode ser nulo.");
		}
		return rep.findById(id).map(TipoEntradaDTO::create);
	}


	public List<TipoEntradaDTO> getTipoEntradaByDescricao(String descricao) {
		if(descricao == null || "".contentEquals(descricao)) {
			throw new IllegalArgumentException("Descrição não pode ser nulo.");
		}
		return rep.findByDescricao(descricao).stream().map(TipoEntradaDTO::create).collect(Collectors.toList());
	}


	public TipoEntradaDTO insert(TipoEntrada tipoEntrada) {
		if(tipoEntrada == null) {
			throw new IllegalArgumentException("Tipo de Entrada não pode ser nulo.");
		}
		return TipoEntradaDTO.create(rep.save(tipoEntrada));
	}


	public TipoEntradaDTO update(TipoEntrada tipoEntrada, Long id) {
		if(tipoEntrada == null) {
			throw new IllegalArgumentException("Tipo de Entrada não pode ser nulo.");
		}else if(id == null) {
			throw new IllegalArgumentException("ID não pode ser nulo.");
		}
		Assert.notNull(id, "Não foi possível atualizar o registro!");
		
		Optional<TipoEntrada> optional = rep.findById(id);
		if(optional.isPresent()) {
			TipoEntrada db = optional.get();
			db.setDescricao(tipoEntrada.getDescricao());
			rep.save(db);
			
			return TipoEntradaDTO.create(db);
		}
			return null;
		
	}
	public TipoEntrada save(TipoEntrada tipoEntrada) {
		if(tipoEntrada == null) {
			throw new IllegalArgumentException("Tipo de Entrada não pode ser nulo.");
		}
		return rep.save(tipoEntrada);
	}


	public boolean delete(Long id) {
		if(id == null) {
			throw new IllegalArgumentException("ID não pode ser nulo.");
		}
		if(getTipoEntradaById(id).isPresent()) {
			rep.deleteById(id);
			return true;
		}
		return false;
	}
}
