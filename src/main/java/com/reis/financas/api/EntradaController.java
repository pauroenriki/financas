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

import com.reis.financas.domain.Entrada;
import com.reis.financas.domain.dto.EntradaDTO;
import com.reis.financas.domain.dto.SaidaDTO;
import com.reis.financas.domain.dto.TipoEntradaDTO;
import com.reis.financas.domain.dto.UsuarioDTO;
import com.reis.financas.service.EntradaService;
import com.reis.financas.service.TipoEntradaService;
import com.reis.financas.service.UsuarioService;
//@CrossOrigin(origins = "*", maxAge = 3600)
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 7600)
@RestController
@RequestMapping("/financas/api/v1/entrada")
public class EntradaController {

	@Autowired
	private EntradaService service;
	@Autowired
	private TipoEntradaService serviceTipo;
	@Autowired
	private UsuarioService serviceUsu;
	
	@GetMapping()
	public ResponseEntity<List<EntradaDTO>> getAll() {
//		return new ResponseEntity<Iterable<Recibo>>(service.getRecibos(),HttpStatus.OK);
		return ResponseEntity.ok(service.buscarTodos());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity buscarPorID(@PathVariable("id") Long id) throws IllegalArgumentException{
		Optional<EntradaDTO> recibo =  service.getEntradaById(id);
		return recibo.isPresent() ?
			 ResponseEntity.ok(recibo.get()) :
			 ResponseEntity.notFound().build();
	}
	@GetMapping("/tipo/{tipo}")
	public ResponseEntity buscarPorTipo(@PathVariable("tipo") Long tipo){
		List<EntradaDTO> entradas =  service.getEntradaByTipoEntrada(TipoEntradaDTO.decode(serviceTipo.getTipoEntradaById(tipo).get()));
		return entradas.isEmpty() ? ResponseEntity.noContent().build() :
			ResponseEntity.ok(entradas);
	}
	@GetMapping("/usuario/{usuario}")
	public ResponseEntity buscarPorUsuario(@PathVariable("usuario") Long usuario){
		List<EntradaDTO> entradas =  service.getEntradaByUsuario(UsuarioDTO.decode(serviceUsu.getUsuarioById(usuario).get()));
		return entradas.isEmpty() ? ResponseEntity.noContent().build() :
			ResponseEntity.ok(entradas);
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
		List<EntradaDTO> saidas =  service.getEntradaByPeriodo(dataIni, dataFim);
		return saidas.isEmpty() ? ResponseEntity.noContent().build() :
			ResponseEntity.ok(saidas);
	}
	
	@PostMapping
	public ResponseEntity InsereEntrada(@RequestBody Entrada entrada) {
		try {
			EntradaDTO rec = service.insert(entrada);
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
	public ResponseEntity AlteraEntrada(@PathVariable("id") Long id, @RequestBody Entrada entrada) {
		EntradaDTO rec = service.update(entrada, id);
		return rec != null ? ResponseEntity.ok(rec) :
			ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable("id") Long id) {
		return service.delete(id) ? ResponseEntity.ok().build() :
			ResponseEntity.notFound().build();
	}
}
