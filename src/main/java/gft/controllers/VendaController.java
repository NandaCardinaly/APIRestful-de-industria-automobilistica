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

import gft.dto.venda.ConsultaVendaDTO;
import gft.dto.venda.NotaFiscalDTO;
import gft.dto.venda.RegistroVendaDTO;
import gft.dto.venda.VendaMapper;
import gft.entities.Loja;
import gft.entities.Venda;
import gft.services.LojaService;
import gft.services.VendaService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/vendas")
public class VendaController {
	
	private final VendaService vendaService;
	
	private final LojaService lojaService;
	
	@PreAuthorize("hasAnyAuthority('PERFIL_ADMIN', 'PERFIL_USER')")
	@PostMapping
	public ResponseEntity<ConsultaVendaDTO> salvarVenda(@RequestBody RegistroVendaDTO dto) {
		
		Venda venda = vendaService.salvarVenda(VendaMapper.fromDTO(dto));
		return ResponseEntity.ok(VendaMapper.fromEntity(venda));
	}
	
	@PreAuthorize("hasAnyAuthority('PERFIL_ADMIN', 'PERFIL_USER')")
	@GetMapping
	public ResponseEntity<List<ConsultaVendaDTO>> buscarTodasAsVendas() {
		
		return ResponseEntity.ok(vendaService.listarTodasAsVendas().stream().map(VendaMapper::fromEntity).collect(Collectors.toList()));
	}
	
	@PreAuthorize("hasAnyAuthority('PERFIL_ADMIN', 'PERFIL_USER')")
	@GetMapping("{id}")
	public ResponseEntity<ConsultaVendaDTO> buscarVenda(@PathVariable Long id) throws Exception {
		
		Venda venda = vendaService.buscarVenda(id);
		return ResponseEntity.ok(VendaMapper.fromEntity(venda));
	}
	
	@PreAuthorize("hasAnyAuthority('PERFIL_ADMIN', 'PERFIL_USER')")
	@PutMapping("{id}")
	public ResponseEntity<ConsultaVendaDTO> alterarVenda(@RequestBody RegistroVendaDTO dto, @PathVariable Long id) throws Exception {
		
		try {
			Venda venda = vendaService.atualizarVenda(VendaMapper.fromDTO(dto), id);
			return ResponseEntity.ok(VendaMapper.fromEntity(venda));
		}catch(RuntimeException ex) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PreAuthorize("hasAuthority('PERFIL_ADMIN')")
	@DeleteMapping("{id}")
	public ResponseEntity<ConsultaVendaDTO> excluirVenda(@PathVariable Long id) {
		
		try {
			vendaService.excluirVenda(id);
			return ResponseEntity.ok().build();
		}catch(RuntimeException ex) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PreAuthorize("hasAnyAuthority('PERFIL_ADMIN', 'PERFIL_USER')")
	@GetMapping("notaFiscal/{id}")
	public ResponseEntity<NotaFiscalDTO> notaFilcal(@PathVariable Long id) throws Exception {
		
		ResponseEntity<ConsultaVendaDTO> venda = buscarVenda(id);
		Loja loja = lojaService.buscarLoja(1L);
		
		NotaFiscalDTO nota = new NotaFiscalDTO();
		nota.setValorTotal(venda.getBody().getValorTotal());
		nota.setNomeLoja(loja.getNome());
		nota.setCnpjLoja(loja.getCnpj());
		nota.setClienteNome(venda.getBody().getClienteNome());
		nota.setClienteCpf(venda.getBody().getClienteCpf());
		
		return ResponseEntity.ok(nota);
	}
}
