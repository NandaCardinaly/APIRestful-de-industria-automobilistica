package gft.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gft.entities.Cliente;
import gft.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	

	public List<Cliente> buscarTodosOsClientes() {
		return clienteRepository.findAll();
	}

	public Cliente salvarCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public Cliente atualizarCliente(Cliente cliente, Long id) {
		Cliente clienteOriginal = buscarCliente(id);
		cliente.setId(clienteOriginal.getId());
		return clienteRepository.save(cliente);
	}

	public Cliente buscarCliente(Long id) {
		Optional<Cliente> optional = clienteRepository.findById(id);
		return optional.orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado") );
		
	}

	public void excluirCliente(Long id) {
		Cliente clienteOriginal = this.buscarCliente(id);
		clienteRepository.delete(clienteOriginal);
		
	}

}
