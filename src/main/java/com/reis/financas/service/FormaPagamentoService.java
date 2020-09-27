package com.reis.financas.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.reis.financas.domain.FormaPagamento;
import com.reis.financas.domain.dto.FormaPagamentoDTO;
import com.reis.financas.repository.FormaPagamentoRepository;

@Service
public class FormaPagamentoService {
	@Autowired
	private FormaPagamentoRepository rep;
	
	public List<FormaPagamentoDTO> buscarTodos(){
		return rep.findAll().stream().map(FormaPagamentoDTO::create).collect(Collectors.toList());
	}
	
	public Optional<FormaPagamentoDTO> getFormaPagamentoById(Long id) {
		if(id == null) {
			throw new IllegalArgumentException("ID não pode ser nulo.");
		}
		return rep.findById(id).map(FormaPagamentoDTO::create);
	}


	public List<FormaPagamentoDTO> getFormaPagamentoByDescricao(String descricao) {
		return rep.findByDescricao(descricao).stream().map(FormaPagamentoDTO::create).collect(Collectors.toList());
	}


	public FormaPagamentoDTO insert(FormaPagamento formaPagamento) {
		return FormaPagamentoDTO.create(rep.save(formaPagamento));
	}


	public FormaPagamentoDTO update(FormaPagamento formaPagamento, Long id) {
		Assert.notNull(id, "Não foi possível atualizar o registro!");
		
		Optional<FormaPagamento> optional = rep.findById(id);
		if(optional.isPresent()) {
			FormaPagamento db = optional.get();
			db.setDescricao(formaPagamento.getDescricao());
			rep.save(db);
			
			return FormaPagamentoDTO.create(db);
		}
			return null;
		
	}
	public FormaPagamento save(FormaPagamento formaPagamento) {
		return rep.save(formaPagamento);
	}


	public boolean delete(Long id) {
		if(getFormaPagamentoById(id).isPresent()) {
			rep.deleteById(id);
			return true;
		}
		return false;
	}
}
