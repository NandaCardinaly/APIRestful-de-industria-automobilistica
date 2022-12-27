package gft.dto.cliente;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gft.entities.Cliente;

@Component("ClienteMapper")
public class ClienteMapper {
	

	public static Cliente converteDTOEmCliente(RegistroClienteDTO clienteDTO) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(clienteDTO, Cliente.class);
	}
	
	public static ConsultaClienteDTO converteClienteEmDTO(Cliente cliente) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(cliente, ConsultaClienteDTO.class);
	}

}
