package gft.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

import gft.dto.veiculo.ConsultaVeiculoDTO;
import gft.dto.veiculo.RegistroVeiculoDTO;
import gft.dto.veiculo.VeiculoMapper;
import gft.entities.Veiculo;
import gft.services.VeiculoService;

@RestController
@RequestMapping("api/v1/veiculo")
public class VeiculoController {
	
private final VeiculoService veiculoService;
	
	public VeiculoController(VeiculoService veiculoService) {
		this.veiculoService = veiculoService;
	}

	@PreAuthorize("hasAnyAuthority('PERFIL_ADMIN', 'PERFIL_USER')")
	@GetMapping
	public ResponseEntity<Page<ConsultaVeiculoDTO>> buscarTodosOsVeiculos(@PageableDefault Pageable pageable){
		
		return ResponseEntity.ok(veiculoService.listarTodosOsVeiculos(pageable).map(VeiculoMapper::fromEntity));		
			
		
	}
	
	@PreAuthorize("hasAnyAuthority('PERFIL_ADMIN', 'PERFIL_USER')")
	@PostMapping
	public ResponseEntity<ConsultaVeiculoDTO> salvarVeiculo(@RequestBody RegistroVeiculoDTO dto){
		
		Veiculo veiculo = veiculoService.salvarVeiculo(VeiculoMapper.fromDTO(dto));
		
		return ResponseEntity.ok(VeiculoMapper.fromEntity(veiculo));
		
	}
	
	@PreAuthorize("hasAnyAuthority('PERFIL_ADMIN', 'PERFIL_USER')")
	@GetMapping("{id}") 
	public ResponseEntity<ConsultaVeiculoDTO> buscarVeiculo(@PathVariable Long id){

		Veiculo veiculo = veiculoService.buscarVeiculo(id);
		
		return ResponseEntity.ok(VeiculoMapper.fromEntity(veiculo));
		
		
	}
	
	@PreAuthorize("hasAnyAuthority('PERFIL_ADMIN', 'PERFIL_USER')")
	@PutMapping("{id}")
	public ResponseEntity<ConsultaVeiculoDTO> alterarVeiculo(@RequestBody RegistroVeiculoDTO dto,
			@PathVariable Long id){
		
		try {
		
			Veiculo veiculo = veiculoService.atualizarVeiculo(VeiculoMapper.fromDTO(dto), id);
			
			return ResponseEntity.ok(VeiculoMapper.fromEntity(veiculo));
		}catch(RuntimeException ex) {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@PreAuthorize("hasAuthority('PERFIL_ADMIN')")
	@DeleteMapping("{id}")
	public ResponseEntity<ConsultaVeiculoDTO> excluirVeiculo(@PathVariable Long id){
		
		try {
			veiculoService.excluirVeiculo(id);
			
			return ResponseEntity.ok().build();
		}catch(RuntimeException ex) {
			return ResponseEntity.notFound().build();
		}
		
	}

}
