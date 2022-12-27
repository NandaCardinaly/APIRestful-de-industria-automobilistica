package gft.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import gft.entities.Veiculo;
import gft.repositories.VeiculoRepository;

@Service
public class VeiculoService {
	
private final VeiculoRepository veiculoRepository;
	
	public VeiculoService(VeiculoRepository veiculoRepository) {
		this.veiculoRepository = veiculoRepository;
	}
	
	public Veiculo salvarVeiculo(Veiculo veiculo) {
		
		return veiculoRepository.save(veiculo);
		
	}
	
	public Page<Veiculo> listarTodosOsVeiculos(Pageable pageable){
		
		return veiculoRepository.findAll(pageable);
		
	}

	public Veiculo buscarVeiculo(Long id) {
		Optional<Veiculo> optional = veiculoRepository.findById(id);
		
		return optional.orElseThrow();
		
	}
	
	public Veiculo atualizarVeiculo(Veiculo veiculo, Long id) {
		
		Veiculo veiculoOriginal = this.buscarVeiculo(id);
		
		veiculo.setId(veiculoOriginal.getId());
		
		return veiculoRepository.save(veiculo);
		
	}

	public void excluirVeiculo(Long id) {
		Veiculo veiculoOriginal = this.buscarVeiculo(id);
		
		veiculoRepository.delete(veiculoOriginal);
		
	}

}