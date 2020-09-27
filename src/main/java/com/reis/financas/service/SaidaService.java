package com.reis.financas.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.reis.financas.domain.CategoriaSaida;
import com.reis.financas.domain.Saida;
import com.reis.financas.domain.Usuario;
import com.reis.financas.domain.dto.SaidaDTO;
import com.reis.financas.repository.SaidaRepository;

@Service
public class SaidaService {

	@Autowired
	private SaidaRepository rep;
	
	public List<SaidaDTO> buscarTodos(){
		return rep.findAll().stream().map(SaidaDTO::create).collect(Collectors.toList());
	}
	
	public Optional<SaidaDTO> getSaidaById(Long id) {
		if(id == null) {
			throw new IllegalArgumentException("ID não pode ser nulo.");
		}
		return rep.findById(id).map(SaidaDTO::create);
	}


	public List<SaidaDTO> getSaidaByCategoriaSaida(CategoriaSaida categoria) {
		if(categoria == null) {
			throw new IllegalArgumentException("Categoria não pode ser nulo.");
		}
		return rep.findByCategoriaSaida(categoria).stream().map(SaidaDTO::create).collect(Collectors.toList());
	}
	
	public List<SaidaDTO> getSaidaByUsuario(Usuario usuario) {
		if(usuario == null) {
			throw new IllegalArgumentException("Categoria não pode ser nulo.");
		}
		return rep.findByUsuario(usuario).stream().map(SaidaDTO::create).collect(Collectors.toList());
	}
	
	public List<SaidaDTO> getSaidaByPeriodo(Date dataIni, Date dataFim){
		return rep.getAllBetweenDates(dataIni, dataFim).stream().map(SaidaDTO::create).collect(Collectors.toList());
	}


	public SaidaDTO insert(Saida saida) {
		if(saida == null) {
			throw new IllegalArgumentException("Saida não pode ser nulo.");
		}
		return SaidaDTO.create(rep.save(saida));
	}


	public SaidaDTO update(Saida saida, Long id) {
		if(saida == null) {
			throw new IllegalArgumentException("Saida não pode ser nulo.");
		}else if(id == null) {
			throw new IllegalArgumentException("ID não pode ser nulo.");
		}
		Assert.notNull(id, "Não foi possível atualizar o registro!");
		
		Optional<Saida> optional = rep.findById(id);
		if(optional.isPresent()) {
			Saida db = optional.get();
			db.setData(saida.getData());
			db.setCategoriaSaida(saida.getCategoriaSaida());
			db.setUsuario(saida.getUsuario());
			db.setValor(saida.getValor());
			db.setStatus(saida.getStatus());
			db.setDescricao(saida.getDescricao());
			db.setVr(saida.isVr());
			db.setDataVencimento(saida.getDataVencimento());
			db.setFormaPagamento(saida.getFormaPagamento());
			rep.save(db);
			
			return SaidaDTO.create(db);
		}
			return null;
		
	}
	public Saida save(Saida saida) {
		if(saida == null) {
			throw new IllegalArgumentException("Saida não pode ser nulo.");
		}
		return rep.save(saida);
	}


	public boolean delete(Long id) {
		if(id == null) {
			throw new IllegalArgumentException("ID não pode ser nulo.");
		}
		if(getSaidaById(id).isPresent()) {
			rep.deleteById(id);
			return true;
		}
		return false;
	}
}
