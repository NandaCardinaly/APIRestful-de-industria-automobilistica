package gft.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gft.dto.loja.ConsultaLojaDTO;
import gft.dto.loja.LojaMapper;
import gft.dto.loja.RegistroLojaDTO;
import gft.entities.Endereco;
import gft.entities.Loja;
import gft.services.EnderecoService;
import gft.services.LojaService;

@RestController
@RequestMapping("/api/v1/loja")
public class LojaController {

	
	private final LojaService lojaService;
	
	@Autowired
	private EnderecoService enderecoService;
	
	
	public LojaController(LojaService lojaService) {
		super();
		this.lojaService = lojaService;
	}

	@PreAuthorize("hasAnyAuthority('PERFIL_ADMIN', 'PERFIL_USER')")
	@GetMapping 
	public ResponseEntity<List<ConsultaLojaDTO>> buscarALoja() {
		return ResponseEntity.ok(lojaService.listarLoja().stream()
				.map(LojaMapper::fromEntity).collect(Collectors.toList()));
		}

	@PreAuthorize("hasAnyAuthority('PERFIL_ADMIN', 'PERFIL_USER')")
	@PostMapping
	public ResponseEntity<ConsultaLojaDTO> salvarLoja(@RequestBody RegistroLojaDTO dto) throws Exception {
		
		Loja loja = LojaMapper.fromDTO(dto);

		String retorno = loja.getEndereco().getCep();

		Endereco endereco = enderecoService.getCep(retorno);

		loja.setEndereco(endereco);
		lojaService.salvarLoja(loja);

		
		return ResponseEntity.ok(LojaMapper.fromEntity(loja));
		
	
	}
	
	@PreAuthorize("hasAnyAuthority('PERFIL_ADMIN', 'PERFIL_USER')")
	@GetMapping("{id}") 
	public ResponseEntity<ConsultaLojaDTO> buscarLoja(@PathVariable Long id) {
	
		
			Loja loja = lojaService.buscarLoja(id);
			
			return ResponseEntity.ok(LojaMapper.fromEntity(loja));
			
	}
	
	@PreAuthorize("hasAnyAuthority('PERFIL_ADMIN', 'PERFIL_USER')")
	@PutMapping("{id}")
	public ResponseEntity<ConsultaLojaDTO> alterarLoja(@RequestBody RegistroLojaDTO dto, @PathVariable Long id) {
		
		try {
			Loja loja = lojaService.atualizarLoja(LojaMapper.fromDTO(dto), id);
			
			return ResponseEntity.ok(LojaMapper.fromEntity(loja));
		} catch (RuntimeException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@PreAuthorize("hasAuthority('PERFIL_ADMIN')")
	@DeleteMapping("{id}")
	public ResponseEntity<ConsultaLojaDTO> excluirLoja(@PathVariable Long id) {
		
		try {
			lojaService.excluirLoja(id);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
}
