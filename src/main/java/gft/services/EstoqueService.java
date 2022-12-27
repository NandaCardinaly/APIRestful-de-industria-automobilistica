package gft.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gft.entities.Estoque;
import gft.entities.Peca;
import gft.entities.Veiculo;
import gft.exception.EntityNotFoundException;
import gft.repositories.EstoqueRepository;
import gft.repositories.PecaRepository;
import gft.repositories.VeiculoRepository;

@Service
public class EstoqueService {

	@Autowired
	private PecaRepository pecaRepository;

	@Autowired
	private VeiculoRepository veiculoRepository;

	private final EstoqueRepository estoqueRepository;

	public EstoqueService(EstoqueRepository estoqueRepository) {

		this.estoqueRepository = estoqueRepository;
	}

	public Pair<Estoque, String> salvarEstoque(Estoque estoque) {

		if (estoque.getQtdPecas() < 10 || estoque.getQtdPecas() > 500) {
			return new Pair<Estoque, String>(null,
					"Alerta de estoque para peças. Estoque minimo: 10 e Estoque máximo: 500");
		}

		if (estoque.getQtdVeiculos() < 10 || estoque.getQtdVeiculos() > 500) {
			return new Pair<Estoque, String>(null,
					"Alerta de estoque para veiculos. Estoque minimo: 10 e Estoque máximo: 500");
		}

		BigDecimal valorTotal = calcularValorTotal(estoque.getQtdPecas(), estoque.getQtdVeiculos());
		estoque.setValorTotal(valorTotal);
		
		Estoque retorno = estoqueRepository.save(estoque);
		return new Pair<Estoque, String>(retorno, null);
	}

	public List<Estoque> listarEstoque() {

		return estoqueRepository.findAll();
	}

	public Estoque buscarEstoque(Long id) {

		Optional<Estoque> optional = estoqueRepository.findById(id);

		return optional.orElseThrow(() -> new EntityNotFoundException("Estoque não encontrado"));
	}

	// Pair é uma tupla da biblioteca JavaTuples que lida com 2 elementos (neste
	// caso, um para estoque e outro para mensagem).

	public Pair<Estoque, String> atualizarEstoque(Estoque estoque, Long id) {

		// validar o estoque máximo e minimo:

		if (estoque.getQtdPecas() < 10 || estoque.getQtdPecas() > 500) {
			return new Pair<Estoque, String>(null,
					"Alerta de estoque para peças. Estoque minimo: 10 e Estoque máximo: 500");
		}

		if (estoque.getQtdVeiculos() < 10 || estoque.getQtdVeiculos() > 500) {
			return new Pair<Estoque, String>(null,
					"Alerta de estoque para veiculos. Estoque minimo: 10 e Estoque máximo: 500");
		}

		Optional<Estoque> optional = estoqueRepository.findById(id);

		// verificar se estoque está vazio, e retorna mensagem para Id de estoque não
		// encontrado:

		if (optional.isEmpty()) {
			return new Pair<Estoque, String>(null, "Estoque não encontrado!");
		}

		// atualizar o estoque:

		Estoque original = optional.get();

		original.setQtdPecas(estoque.getQtdPecas());
		original.setQtdVeiculos(estoque.getQtdVeiculos());
		original.setValorTotal(estoque.getValorTotal());

		Estoque retorno = estoqueRepository.save(original);

		return new Pair<Estoque, String>(retorno, null);
	}

	public void excluirEstoque(Long id) {

		Estoque estoqueOriginal = this.buscarEstoque(id);

		estoqueRepository.delete(estoqueOriginal);
	}

	public BigDecimal calcularValorTotal(int qtdPecas, int qtdVeiculos) {

		List<Peca> pecas = pecaRepository.findAll();
		List<Veiculo> veiculos = veiculoRepository.findAll();
		
		// Percorre a lista de veiculos e peças, mapeando para double as propriedades getValor e realiza a somatória dos itens
		
		double valorTotalVeiculo = veiculos.stream().mapToDouble(e -> e.getValorTotal()).sum();
		
		
		double valorTotalPecas = pecas.stream().mapToDouble(e -> e.getValor().doubleValue()).sum();
		
		// calculo do valor total:
		double total = (valorTotalVeiculo * qtdVeiculos) + (valorTotalPecas * qtdPecas);
		
		return new BigDecimal(total);
	}
	
}
