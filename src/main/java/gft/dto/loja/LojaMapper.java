package gft.dto.loja;

import org.modelmapper.ModelMapper;

import gft.entities.Loja;

public class LojaMapper {

	public static Loja fromDTO(RegistroLojaDTO dto) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(dto, Loja.class);
	}

	public static ConsultaLojaDTO fromEntity(Loja loja) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(loja, ConsultaLojaDTO.class);
	}

}
