package gft.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import gft.entities.Fornecedor;
import gft.repositories.FornecedorRepository;

@Service
public class FornecedorService {
	
private final FornecedorRepository fornecedorRepository;
	
	public FornecedorService(FornecedorRepository fornecedorRepository) {
		this.fornecedorRepository = fornecedorRepository;
	}
	
	public Fornecedor salvarFornecedor(Fornecedor fornecedor) {
		
		return fornecedorRepository.save(fornecedor);
		
	}
	

	public List<Fornecedor> listarTodosOsFornecedores() {
		return fornecedorRepository.findAll();
	}
	


	public Fornecedor buscarFornecedor(Long id) {
		Optional<Fornecedor> optional = fornecedorRepository.findById(id);
		
		return optional.orElseThrow();
		
	}
	
	public Fornecedor atualizarFornecedor(Fornecedor fornecedor, Long id) {
		
		Fornecedor fornecedorOriginal = this.buscarFornecedor(id);
		
		fornecedor.setId(fornecedorOriginal.getId());
		
		return fornecedorRepository.save(fornecedor);
		
	}

	public void excluirFornecedor(Long id) {
		Fornecedor fornecedorOriginal = this.buscarFornecedor(id);
		
		fornecedorRepository.delete(fornecedorOriginal);
		
	}

}