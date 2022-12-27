package gft.dto.fornecedor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gft.entities.Fornecedor;

@Component("FornecedorMapper")
public class FornecedorMapper {
	
	public static Fornecedor fromDTO(RegistroFornecedorDTO dto) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(dto, Fornecedor.class);
	}
	
	public static ConsultaFornecedorDTO fromEntity(Fornecedor fornecedor) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(fornecedor, ConsultaFornecedorDTO.class);
	}
	
	
	
	
}