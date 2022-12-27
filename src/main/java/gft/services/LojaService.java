package gft.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gft.entities.Loja;
import gft.exception.EntityNotFoundException;
import gft.repositories.LojaRepository;

@Service
public class LojaService {

	@Autowired
	private LojaRepository lojaRepository;
	
	
	public List<Loja> listarLoja(){
		
		return lojaRepository.findAll();
		
	}
	
	public Loja salvarLoja(Loja loja) {
		
		return lojaRepository.save(loja);
	}
	
	public Loja buscarLoja(Long id) {
		
		Optional<Loja> optional = lojaRepository.findById(id);
		
		return optional.orElseThrow(() -> new EntityNotFoundException("Loja não encontrada") );
	}

	public Loja atualizarLoja(Loja loja, Long id) {
		
		Loja lojaOriginal = this.buscarLoja(id);
		
		loja.setId(lojaOriginal.getId());
		
		return lojaRepository.save(loja);
		
	}

	public void excluirLoja(Long id) {
		
		Loja lojaOriginal = this.buscarLoja(id);
		
		lojaRepository.delete(lojaOriginal);
	}

}
