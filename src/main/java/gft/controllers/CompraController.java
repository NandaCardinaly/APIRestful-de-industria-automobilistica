package gft.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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

import gft.dto.compra.CompraMapper;
import gft.dto.compra.ConsultaCompraDTO;
import gft.dto.compra.RegistroCompraDTO;
import gft.entities.Compra;
import gft.services.CompraService;


@RestController
@RequestMapping("api/v1/compras")
public class CompraController {
	
	@Autowired
	private CompraService compraService;

	@PreAuthorize("hasAnyAuthority('PERFIL_ADMIN', 'PERFIL_USER')")
	@GetMapping
	public ResponseEntity<List<ConsultaCompraDTO>> buscarTodosAsCompras(){
		
		
		return ResponseEntity.ok(compraService.buscarTodasAsCompras().stream().map(CompraMapper::converteCompraEmDTO).
				collect(Collectors.toList()));	
		
		
		
	}
	
	@PreAuthorize("hasAnyAuthority('PERFIL_ADMIN', 'PERFIL_USER')")
	@GetMapping("{id}") 
	public ResponseEntity<ConsultaCompraDTO> buscarCompra(@PathVariable Long id){

		Compra compra = compraService.buscarCompra(id);
		
		return ResponseEntity.ok(CompraMapper.converteCompraEmDTO(compra));
				
	}
	
	@PreAuthorize("hasAnyAuthority('PERFIL_ADMIN', 'PERFIL_USER')")
	@PostMapping
	public ResponseEntity<ConsultaCompraDTO> salvarCompra(@RequestBody RegistroCompraDTO dto){
		
		Compra compra = compraService.salvarCompra(CompraMapper.converteDTOEmCompra(dto));
		
		return ResponseEntity.ok(CompraMapper.converteCompraEmDTO(compra));
		
		
	
		
	}
	
	@PreAuthorize("hasAnyAuthority('PERFIL_ADMIN', 'PERFIL_USER')")
	@PutMapping("{id}")
	public ResponseEntity<ConsultaCompraDTO> alterarCompra(@RequestBody RegistroCompraDTO dto,
			@PathVariable Long id){
		
		try {
				Compra compra = compraService.atualizarCompra(CompraMapper.converteDTOEmCompra(dto), id);
				return ResponseEntity.ok(CompraMapper.converteCompraEmDTO(compra));
		}catch(RuntimeException ex) {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@PreAuthorize("hasAuthority('PERFIL_ADMIN')")
	@DeleteMapping("{id}")
	public ResponseEntity<ConsultaCompraDTO> excluirCompra(@PathVariable Long id){
		
		try {
			compraService.excluirCompra(id);
			
			return ResponseEntity.ok().build();
		}catch(RuntimeException ex) {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	

}
