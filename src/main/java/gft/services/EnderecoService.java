package gft.services;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import gft.entities.Endereco;

@Transactional
@Service
public class EnderecoService {
	
	public Endereco getCep(String CEP) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		String URLAPI = "https://viacep.com.br/ws/"+CEP+"/json/";
		Endereco respostaEndereco = restTemplate.getForObject(URLAPI, Endereco.class);
		return respostaEndereco;
	}

}
