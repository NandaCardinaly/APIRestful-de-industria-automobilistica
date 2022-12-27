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

import gft.dto.cliente.ClienteMapper;
import gft.dto.cliente.ConsultaClienteDTO;
import gft.dto.cliente.RegistroClienteDTO;
import gft.entities.Cliente;
import gft.services.ClienteService;



@RestController
@RequestMapping("api/v1/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	
	@PreAuthorize("hasAnyAuthority('PERFIL_ADMIN', 'PERFIL_USER')")
	@GetMapping
	public ResponseEntity<List<ConsultaClienteDTO>> buscarTodosOsClientes(){
		
		return ResponseEntity.ok(clienteService.buscarTodosOsClientes().stream().map(ClienteMapper::converteClienteEmDTO).collect(Collectors.toList()));	
		
	}
	
	@PreAuthorize("hasAnyAuthority('PERFIL_ADMIN', 'PERFIL_USER')")
	@GetMapping("{id}") 
	public ResponseEntity<ConsultaClienteDTO> buscarCliente(@PathVariable Long id){

		Cliente cliente = clienteService.buscarCliente(id);
		
		return ResponseEntity.ok(ClienteMapper.converteClienteEmDTO(cliente));
		
		
	}
	
	@PreAuthorize("hasAnyAuthority('PERFIL_ADMIN', 'PERFIL_USER')")
	@PostMapping
	public ResponseEntity<ConsultaClienteDTO> salvarCliente(@RequestBody RegistroClienteDTO dto){
		
		Cliente cliente = clienteService.salvarCliente(ClienteMapper.converteDTOEmCliente(dto));
		
		return ResponseEntity.ok(ClienteMapper.converteClienteEmDTO(cliente));
		
	}
	
	@PreAuthorize("hasAnyAuthority('PERFIL_ADMIN', 'PERFIL_USER')")
	@PutMapping("{id}")
	public ResponseEntity<ConsultaClienteDTO> alterarCliente(@RequestBody RegistroClienteDTO dto,
			@PathVariable Long id){
		
		try {
				Cliente cliente = clienteService.atualizarCliente(ClienteMapper.converteDTOEmCliente(dto), id);
				return ResponseEntity.ok(ClienteMapper.converteClienteEmDTO(cliente));
		}catch(RuntimeException ex) {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@PreAuthorize("hasAuthority('PERFIL_ADMIN')")
	@DeleteMapping("{id}")
	public ResponseEntity<ConsultaClienteDTO> excluirCliente(@PathVariable Long id){
		
		try {
			clienteService.excluirCliente(id);
			
			return ResponseEntity.ok().build();
		}catch(RuntimeException ex) {
			return ResponseEntity.notFound().build();
		}
		
	}
	


	
	

}
