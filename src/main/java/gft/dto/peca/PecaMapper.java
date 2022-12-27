package gft.dto.peca;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gft.entities.Peca;

@Component
public class PecaMapper {

	public static Peca fromDTO(RegistroPecaDTO dto) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(dto, Peca.class);
	}
	
	public static ConsultaPecaDTO fromEntity(Peca peca) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(peca, ConsultaPecaDTO.class);
	}
}
