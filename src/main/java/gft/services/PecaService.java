package gft.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import gft.entities.Peca;
import gft.exception.EntityNotFoundException;
import gft.repositories.PecaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PecaService {

	private final PecaRepository pecaRepository;
	
	public Peca salvarPeca(Peca peca) {
		return pecaRepository.save(peca);
	}
	
	public List<Peca> listarTodasAsPecas(){
		return pecaRepository.findAll();
	}
	
	public Peca buscarPeca(Long id) {
		Optional<Peca> optional = pecaRepository.findById(id);
		
		return optional.orElseThrow(() -> new EntityNotFoundException("Peça não encontrada"));
	}
	
	public void excluirPeca(Long id) {
		pecaRepository.deleteById(id);
	}
	
	public Peca atualizarPeca(Peca peca, Long id) {
		
		Peca buscarPeca = buscarPeca(id);
		peca.setId(buscarPeca.getId());
		
		return pecaRepository.save(peca);
	}
}
