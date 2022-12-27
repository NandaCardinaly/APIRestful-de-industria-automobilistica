package gft.controllers;

import java.util.List;
import java.util.stream.Collectors;

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

import gft.dto.peca.ConsultaPecaDTO;
import gft.dto.peca.PecaMapper;
import gft.dto.peca.RegistroPecaDTO;
import gft.entities.Peca;
import gft.services.PecaService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/pecas")
public class PecaController {

	private final PecaService pecaService;
	
	@PreAuthorize("hasAnyAuthority('PERFIL_ADMIN', 'PERFIL_USER')")
	@PostMapping
	public ResponseEntity<ConsultaPecaDTO> salvarPeca(@RequestBody RegistroPecaDTO dto) {
		
		Peca peca = pecaService.salvarPeca(PecaMapper.fromDTO(dto));
		return ResponseEntity.ok(PecaMapper.fromEntity(peca));
	}
	
	@PreAuthorize("hasAnyAuthority('PERFIL_ADMIN', 'PERFIL_USER')")
	@GetMapping
	public ResponseEntity<List<ConsultaPecaDTO>> buscarTodasAsPecas() {
		
		return ResponseEntity.ok(pecaService.listarTodasAsPecas().stream().map(PecaMapper::fromEntity).collect(Collectors.toList()));
	}
	
	@PreAuthorize("hasAnyAuthority('PERFIL_ADMIN', 'PERFIL_USER')")
	@GetMapping("{id}")
	public ResponseEntity<ConsultaPecaDTO> buscarPeca(@PathVariable Long id) {

		Peca peca = pecaService.buscarPeca(id);
		return ResponseEntity.ok(PecaMapper.fromEntity(peca));
	}
	
	@PreAuthorize("hasAnyAuthority('PERFIL_ADMIN', 'PERFIL_USER')")
	@PutMapping("{id}")
	public ResponseEntity<ConsultaPecaDTO> alterarPeca(@RequestBody RegistroPecaDTO dto, @PathVariable Long id) {
		
		try {
			Peca peca = pecaService.atualizarPeca(PecaMapper.fromDTO(dto), id);
			return ResponseEntity.ok(PecaMapper.fromEntity(peca));
		}catch(RuntimeException ex) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PreAuthorize("hasAuthority('PERFIL_ADMIN')")
	@DeleteMapping("{id}")
	public ResponseEntity<ConsultaPecaDTO> excluirPeca(@PathVariable Long id) {
		
		try {
			pecaService.excluirPeca(id);;
			return ResponseEntity.ok().build();
		}catch(RuntimeException ex) {
			return ResponseEntity.notFound().build();
		}
	}
}
