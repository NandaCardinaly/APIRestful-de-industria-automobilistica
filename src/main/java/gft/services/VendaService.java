package gft.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import gft.entities.Venda;
import gft.exception.EntityNotFoundException;
import gft.repositories.VendaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VendaService {

	private final VendaRepository vendaRepository;
	
	public Venda salvarVenda(Venda venda) {
		return vendaRepository.save(venda);
	}
	
	public List<Venda> listarTodasAsVendas() {
		return vendaRepository.findAll();
	}
	
	public Venda buscarVenda(Long id) throws Exception {
		Optional<Venda> optional = vendaRepository.findById(id);
		
		return optional.orElseThrow(() -> new EntityNotFoundException("Venda não encontrada"));
	}
	
	public void excluirVenda(Long id) {
		vendaRepository.deleteById(id);
	}
	
	public Venda atualizarVenda(Venda venda, Long id) throws Exception {

		Venda buscarVenda = buscarVenda(id);
		venda.setId(buscarVenda.getId());

		return vendaRepository.save(venda);

	}
}
