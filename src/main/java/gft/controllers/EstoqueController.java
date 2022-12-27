package gft.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.javatuples.Pair;
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

import gft.dto.estoque.ConsultaEstoqueDTO;
import gft.dto.estoque.EstoqueMapper;
import gft.dto.estoque.RegistroEstoqueDTO;
import gft.entities.Estoque;
import gft.services.EstoqueService;

@RestController
@RequestMapping("/api/v1/estoque")
public class EstoqueController {

private final EstoqueService estoqueService;
	
	
	public EstoqueController(EstoqueService estoqueService) {
		this.estoqueService = estoqueService;
	}

	@PreAuthorize("hasAnyAuthority('PERFIL_ADMIN', 'PERFIL_USER')")
	@GetMapping
	public ResponseEntity<List<ConsultaEstoqueDTO>> atualizarEstoque() {
		
		return ResponseEntity.ok(estoqueService.listarEstoque().stream().map(EstoqueMapper::fromEntity).collect(Collectors.toList()));
	}
	
	@PreAuthorize("hasAuthority('PERFIL_ADMIN')")
	@PostMapping
	public ResponseEntity<?> salvarEstoque(@RequestBody RegistroEstoqueDTO dto) {
		
		Estoque fromDto = EstoqueMapper.fromDto(dto);
		
		Pair<Estoque, String> estoque = estoqueService.salvarEstoque(fromDto);
		
		if(estoque.getValue0() == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(estoque.getValue1());
		}
		
		return ResponseEntity.ok(EstoqueMapper.fromEntity(estoque.getValue0()));
	} 
	
	@PreAuthorize("hasAnyAuthority('PERFIL_ADMIN', 'PERFIL_USER')")
	@GetMapping("{id}") 
	public ResponseEntity<ConsultaEstoqueDTO> buscarEstoque(@PathVariable Long id) {
	
		
		Estoque estoque = estoqueService.buscarEstoque(id);
			
			return ResponseEntity.ok(EstoqueMapper.fromEntity(estoque));
			
	}
	
	@PreAuthorize("hasAuthority('PERFIL_ADMIN')")
	@PutMapping("{id}")
	public ResponseEntity<?> alterarEstoque(@RequestBody RegistroEstoqueDTO dto, @PathVariable Long id) {
		
		try {
			Pair<Estoque, String> estoque = estoqueService.atualizarEstoque(EstoqueMapper.fromDto(dto), id);
			
			if(estoque.getValue0() == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(estoque.getValue1());
			}
			
			return ResponseEntity.ok(EstoqueMapper.fromEntity(estoque.getValue0()));
			
		} catch (RuntimeException ex) {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@PreAuthorize("hasAuthority('PERFIL_ADMIN')")
	@DeleteMapping("{id}")
	public ResponseEntity<ConsultaEstoqueDTO> excluirEstoque(@PathVariable Long id) {
		
		try {
			estoqueService.excluirEstoque(id);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
}
