package gft.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gft.entities.Compra;
import gft.repositories.CompraRepository;

@Service
public class CompraService {

	@Autowired
	private CompraRepository compraRepository;
	
	//@Autowired
	//private final EstoqueService estoqueService;

	public List<Compra> buscarTodasAsCompras() {
		return compraRepository.findAll();
	}

	

	public Compra salvarCompra(Compra compra) {
		
			return compraRepository.save(compra);
	
			
			/* double valorTotal = 0d;
		List<ItemCompra> item = compra.getItens();

		for (ItemCompra it : item) {
			valorTotal += it.getQuantidade() * it.getValor();
			compra.setValorTotal(valorTotal); 
			compraRepository.save(compra);
		//}

		return compra;*/
	}

	public Compra atualizarCompra(Compra compra, Long id) {
		Compra compraOriginal = buscarCompra(id);
		compra.setId(compraOriginal.getId());
		return compraRepository.save(compra);
	}

	public Compra buscarCompra(Long id) {
		Optional<Compra> optional = compraRepository.findById(id);
		return optional.orElseThrow(() -> new EntityNotFoundException("Compra não encontrada"));

	}

	public void excluirCompra(Long id) {
		Compra compraOriginal = this.buscarCompra(id);
		compraRepository.delete(compraOriginal);

	}

}
