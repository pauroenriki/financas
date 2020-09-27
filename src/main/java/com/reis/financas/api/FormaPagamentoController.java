package com.reis.financas.api;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.reis.financas.domain.FormaPagamento;
import com.reis.financas.domain.dto.FormaPagamentoDTO;
import com.reis.financas.service.FormaPagamentoService;

//@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/financas/api/v1/formaPagamento")
public class FormaPagamentoController {


	@Autowired
	private FormaPagamentoService service;
	
	@GetMapping()
	public ResponseEntity<List<FormaPagamentoDTO>> getAll() {
//		return new ResponseEntity<Iterable<Recibo>>(service.getRecibos(),HttpStatus.OK);
		return ResponseEntity.ok(service.buscarTodos());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity buscarPorID(@PathVariable("id") Long id) throws IllegalArgumentException{
		Optional<FormaPagamentoDTO> recibo =  service.getFormaPagamentoById(id);
		return recibo.isPresent() ?
			 ResponseEntity.ok(recibo.get()) :
			 ResponseEntity.notFound().build();
	}
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity buscarPorLogin(@PathVariable("descricao") String descricao){
		List<FormaPagamentoDTO> formaPagamentos =  service.getFormaPagamentoByDescricao(descricao);
		return formaPagamentos.isEmpty() ? ResponseEntity.noContent().build() :
			ResponseEntity.ok(formaPagamentos);
	}
	
	@PostMapping
	public ResponseEntity InsereFormaPagamento(@RequestBody FormaPagamento formaPagamento) {
		try {
			FormaPagamentoDTO rec = service.insert(formaPagamento);
			URI location = getUri(rec.getId());
			return ResponseEntity.created(location).build();
		}catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	private URI getUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(id).toUri();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity AlteraFormaPagamento(@PathVariable("id") Long id, @RequestBody FormaPagamento formaPagamento) {
		FormaPagamentoDTO rec = service.update(formaPagamento, id);
		return rec != null ? ResponseEntity.ok(rec) :
			ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable("id") Long id) {
		return service.delete(id) ? ResponseEntity.ok().build() :
			ResponseEntity.notFound().build();
	}
}
