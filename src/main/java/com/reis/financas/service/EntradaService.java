package com.reis.financas.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.reis.financas.domain.Entrada;
import com.reis.financas.domain.TipoEntrada;
import com.reis.financas.domain.Usuario;
import com.reis.financas.domain.dto.EntradaDTO;
import com.reis.financas.domain.dto.SaidaDTO;
import com.reis.financas.repository.EntradaRepository;

@Service
public class EntradaService {
	@Autowired
	private EntradaRepository rep;
	
	public List<EntradaDTO> buscarTodos(){
		return rep.findAll().stream().map(EntradaDTO::create).collect(Collectors.toList());
	}
	
	public Optional<EntradaDTO> getEntradaById(Long id) {
		if(id == null) {
			throw new IllegalArgumentException("ID não pode ser nulo.");
		}
		return rep.findById(id).map(EntradaDTO::create);
	}


	public List<EntradaDTO> getEntradaByTipoEntrada(TipoEntrada tipo) {
		if(tipo == null) {
			throw new IllegalArgumentException("Tipo não pode ser nulo.");
		}
		return rep.findByTipoEntrada(tipo).stream().map(EntradaDTO::create).collect(Collectors.toList());
	}
	public List<EntradaDTO> getEntradaByUsuario(Usuario usuario) {
		if(usuario == null) {
			throw new IllegalArgumentException("Tipo não pode ser nulo.");
		}
		return rep.findByUsuario(usuario).stream().map(EntradaDTO::create).collect(Collectors.toList());
	}
	
	public List<EntradaDTO> getEntradaByPeriodo(Date dataIni, Date dataFim){
		return rep.getAllBetweenDates(dataIni, dataFim).stream().map(EntradaDTO::create).collect(Collectors.toList());
	}


	public EntradaDTO insert(Entrada entrada) {
		if(entrada == null) {
			throw new IllegalArgumentException("Entrada não pode ser nulo.");
		}
		return EntradaDTO.create(rep.save(entrada));
	}


	public EntradaDTO update(Entrada entrada, Long id) {
		if(entrada == null) {
			throw new IllegalArgumentException("Entrada não pode ser nulo.");
		}else if(id == null) {
			throw new IllegalArgumentException("ID não pode ser nulo.");
		}
		Assert.notNull(id, "Não foi possível atualizar o registro!");
		
		Optional<Entrada> optional = rep.findById(id);
		if(optional.isPresent()) {
			Entrada db = optional.get();
			db.setData(entrada.getData());
			db.setTipoEntrada(entrada.getTipoEntrada());
			db.setUsuario(entrada.getUsuario());
			db.setValor(entrada.getValor());
			rep.save(db);
			
			return EntradaDTO.create(db);
		}
			return null;
		
	}
	public Entrada save(Entrada entrada) {
		if(entrada == null) {
			throw new IllegalArgumentException("Entrada não pode ser nulo.");
		}
		return rep.save(entrada);
	}


	public boolean delete(Long id) {
		if(id == null) {
			throw new IllegalArgumentException("ID não pode ser nulo.");
		}
		if(getEntradaById(id).isPresent()) {
			rep.deleteById(id);
			return true;
		}
		return false;
	}
}
