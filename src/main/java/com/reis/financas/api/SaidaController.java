package com.reis.financas.api;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.reis.financas.domain.Saida;
import com.reis.financas.domain.dto.CategoriaSaidaDTO;
import com.reis.financas.domain.dto.SaidaDTO;
import com.reis.financas.domain.dto.UsuarioDTO;
import com.reis.financas.service.CategoriaSaidaService;
import com.reis.financas.service.SaidaService;
import com.reis.financas.service.UsuarioService;

//@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/financas/api/v1/saida")
public class SaidaController {
	@Autowired
	private SaidaService service;
	@Autowired
	private CategoriaSaidaService serviceCategoria;
	@Autowired
	private UsuarioService serviceUsu;
	
	@GetMapping()
	public ResponseEntity<List<SaidaDTO>> getAll() {
//		return new ResponseEntity<Iterable<Recibo>>(service.getRecibos(),HttpStatus.OK);
		return ResponseEntity.ok(service.buscarTodos());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity buscarPorID(@PathVariable("id") Long id) throws IllegalArgumentException{
		Optional<SaidaDTO> recibo =  service.getSaidaById(id);
		return recibo.isPresent() ?
			 ResponseEntity.ok(recibo.get()) :
			 ResponseEntity.notFound().build();
	}
	@GetMapping("/categoria/{categoria}")
	public ResponseEntity buscarPorTipo(@PathVariable("categoria") Long categoria){
		List<SaidaDTO> saidas =  service.getSaidaByCategoriaSaida(CategoriaSaidaDTO.decode(serviceCategoria.getCategoriaSaidaById(categoria).get()));
		return saidas.isEmpty() ? ResponseEntity.noContent().build() :
			ResponseEntity.ok(saidas);
	}
	@GetMapping("/usuario/{usuario}")
	public ResponseEntity buscarPorUsuario(@PathVariable("usuario") Long usuario){
		List<SaidaDTO> saidas =  service.getSaidaByUsuario(UsuarioDTO.decode(serviceUsu.getUsuarioById(usuario).get()));
		return saidas.isEmpty() ? ResponseEntity.noContent().build() :
			ResponseEntity.ok(saidas);
	}
	
	@GetMapping("/periodo/{periodo}")
	public ResponseEntity buscarPorPeriodo(@PathVariable("periodo") String periodo){
		String[] dados = periodo.split(":");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dataIni = null;
		Date dataFim = null;
		try {
			dataIni = sdf.parse(dados[0]);
			dataFim = sdf.parse(dados[1]);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<SaidaDTO> saidas =  service.getSaidaByPeriodo(dataIni, dataFim);
		return saidas.isEmpty() ? ResponseEntity.noContent().build() :
			ResponseEntity.ok(saidas);
	}
	
	@PostMapping
	public ResponseEntity InsereSaida(@RequestBody Saida  saida) {
		try {
			SaidaDTO rec = service.insert(saida);
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
	public ResponseEntity AlteraSaida(@PathVariable("id") Long id, @RequestBody Saida saida) {
		SaidaDTO rec = service.update(saida, id);
		return rec != null ? ResponseEntity.ok(rec) :
			ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable("id") Long id) {
		return service.delete(id) ? ResponseEntity.ok().build() :
			ResponseEntity.notFound().build();
	}
}
