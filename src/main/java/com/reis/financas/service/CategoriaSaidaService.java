package com.reis.financas.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.reis.financas.domain.CategoriaSaida;
import com.reis.financas.domain.dto.CategoriaSaidaDTO;
import com.reis.financas.repository.CategoriaSaidaRepository;

@Service
public class CategoriaSaidaService {

	@Autowired
	private CategoriaSaidaRepository rep;
	
	public List<CategoriaSaidaDTO> buscarTodos(){
		return rep.findAll().stream().map(CategoriaSaidaDTO::create).collect(Collectors.toList());
	}
	
	public Optional<CategoriaSaidaDTO> getCategoriaSaidaById(Long id) {
		if(id == null) {
			throw new IllegalArgumentException("ID não pode ser nulo.");
		}
		return rep.findById(id).map(CategoriaSaidaDTO::create);
	}


	public List<CategoriaSaidaDTO> getCategoriaSaidaByDescricao(String descricao) {
		if(descricao == null || "".contentEquals(descricao)) {
			throw new IllegalArgumentException("Descrição não pode ser nulo.");
		}
		return rep.findByDescricao(descricao).stream().map(CategoriaSaidaDTO::create).collect(Collectors.toList());
	}


	public CategoriaSaidaDTO insert(CategoriaSaida categoriaSaida) {
		if(categoriaSaida == null) {
			throw new IllegalArgumentException("Categoria não pode ser nulo.");
		}
		return CategoriaSaidaDTO.create(rep.save(categoriaSaida));
	}


	public CategoriaSaidaDTO update(CategoriaSaida categoriaSaida, Long id) {
		if(categoriaSaida == null) {
			throw new IllegalArgumentException("Categoria não pode ser nulo.");
		}else if(id == null) {
			throw new IllegalArgumentException("ID não pode ser nulo.");
		}
		Assert.notNull(id, "Não foi possível atualizar o registro!");
		
		Optional<CategoriaSaida> optional = rep.findById(id);
		if(optional.isPresent()) {
			CategoriaSaida db = optional.get();
			db.setDescricao(categoriaSaida.getDescricao());
			rep.save(db);
			
			return CategoriaSaidaDTO.create(db);
		}
			return null;
		
	}
	public CategoriaSaida save(CategoriaSaida categoriaSaida) {
		if(categoriaSaida == null) {
			throw new IllegalArgumentException("Categoria não pode ser nulo.");
		}
		return rep.save(categoriaSaida);
	}


	public boolean delete(Long id) {
		if(id == null) {
			throw new IllegalArgumentException("ID não pode ser nulo.");
		}
		if(getCategoriaSaidaById(id).isPresent()) {
			rep.deleteById(id);
			return true;
		}
		return false;
	}
}
