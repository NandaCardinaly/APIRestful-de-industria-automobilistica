package gft.dto.estoque;

import org.modelmapper.ModelMapper;

import gft.entities.Estoque;

public class EstoqueMapper {

	public static Estoque fromDto(RegistroEstoqueDTO dto) {
		
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(dto, Estoque.class);
	}
	
	public static ConsultaEstoqueDTO fromEntity(Estoque estoque) {
		
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(estoque, ConsultaEstoqueDTO.class);
	}
}
