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

import gft.dto.fornecedor.ConsultaFornecedorDTO;
import gft.dto.fornecedor.FornecedorMapper;
import gft.dto.fornecedor.RegistroFornecedorDTO;
import gft.entities.Endereco;
import gft.entities.Fornecedor;
import gft.services.EnderecoService;
import gft.services.FornecedorService;

@RestController
@RequestMapping("api/v1/fornecedores")
public class FornecedorController {

	private final FornecedorService fornecedorService;

	public FornecedorController(FornecedorService fornecedorService) {
		this.fornecedorService = fornecedorService;
	}

	@Autowired
	private EnderecoService enderecoService;

	@PreAuthorize("hasAnyAuthority('PERFIL_ADMIN', 'PERFIL_USER')")
	@GetMapping
	public ResponseEntity<List<ConsultaFornecedorDTO>> buscarTodosOsFornecedores() {

		return ResponseEntity.ok(fornecedorService.listarTodosOsFornecedores().stream()
				.map(FornecedorMapper::fromEntity).collect(Collectors.toList()));

	}

	@PreAuthorize("hasAnyAuthority('PERFIL_ADMIN', 'PERFIL_USER')")
	@PostMapping
	public ResponseEntity<ConsultaFornecedorDTO> salvarFornecedor(@RequestBody RegistroFornecedorDTO dto)
			throws Exception {

		
		Fornecedor fornecedor = FornecedorMapper.fromDTO(dto);

		String retorno = fornecedor.getEndereco().getCep();

		Endereco endereco = enderecoService.getCep(retorno);

		fornecedor.setEndereco(endereco);
		fornecedorService.salvarFornecedor(fornecedor);

		
		return ResponseEntity.ok(FornecedorMapper.fromEntity(fornecedor));

	}

	@PreAuthorize("hasAnyAuthority('PERFIL_ADMIN', 'PERFIL_USER')")
	@GetMapping("{id}")
	public ResponseEntity<ConsultaFornecedorDTO> buscarFornecedor(@PathVariable Long id) {

		Fornecedor fornecedor = fornecedorService.buscarFornecedor(id);

		return ResponseEntity.ok(FornecedorMapper.fromEntity(fornecedor));

	}

	@PreAuthorize("hasAnyAuthority('PERFIL_ADMIN', 'PERFIL_USER')")
	@PutMapping("{id}")
	public ResponseEntity<ConsultaFornecedorDTO> alterarFornecedor(@RequestBody RegistroFornecedorDTO dto,
			@PathVariable Long id) {

		try {

			Fornecedor fornecedor = fornecedorService.atualizarFornecedor(FornecedorMapper.fromDTO(dto), id);

			return ResponseEntity.ok(FornecedorMapper.fromEntity(fornecedor));
		} catch (RuntimeException ex) {
			return ResponseEntity.notFound().build();
		}

	}

	@PreAuthorize("hasAuthority('PERFIL_ADMIN')")
	@DeleteMapping("{id}")
	public ResponseEntity<ConsultaFornecedorDTO> excluirFornecedor(@PathVariable Long id) {

		try {
			fornecedorService.excluirFornecedor(id);

			return ResponseEntity.ok().build();
		} catch (RuntimeException ex) {
			return ResponseEntity.notFound().build();
		}

	}

}
