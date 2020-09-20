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

import com.reis.financas.domain.CategoriaSaida;
import com.reis.financas.domain.dto.CategoriaSaidaDTO;
import com.reis.financas.service.CategoriaSaidaService;

//@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/financas/api/v1/categoriaSaida")
public class CategoriaSaidaController {

	@Autowired
	private CategoriaSaidaService service;
	
	@GetMapping()
	public ResponseEntity<List<CategoriaSaidaDTO>> getAll() {
//		return new ResponseEntity<Iterable<Recibo>>(service.getRecibos(),HttpStatus.OK);
		return ResponseEntity.ok(service.buscarTodos());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity buscarPorID(@PathVariable("id") Long id) throws IllegalArgumentException{
		Optional<CategoriaSaidaDTO> recibo =  service.getCategoriaSaidaById(id);
		return recibo.isPresent() ?
			 ResponseEntity.ok(recibo.get()) :
			 ResponseEntity.notFound().build();
	}
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity buscarPorLogin(@PathVariable("descricao") String descricao){
		List<CategoriaSaidaDTO> categoriaSaidas =  service.getCategoriaSaidaByDescricao(descricao);
		return categoriaSaidas.isEmpty() ? ResponseEntity.noContent().build() :
			ResponseEntity.ok(categoriaSaidas);
	}
	
	@PostMapping
	public ResponseEntity InsereCategoriaSaida(@RequestBody CategoriaSaida categoriaSaida) {
		try {
			CategoriaSaidaDTO rec = service.insert(categoriaSaida);
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
	public ResponseEntity AlteraCategoriaSaida(@PathVariable("id") Long id, @RequestBody CategoriaSaida categoriaSaida) {
		CategoriaSaidaDTO rec = service.update(categoriaSaida, id);
		return rec != null ? ResponseEntity.ok(rec) :
			ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable("id") Long id) {
		return service.delete(id) ? ResponseEntity.ok().build() :
			ResponseEntity.notFound().build();
	}
}
