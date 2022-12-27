package gft.dto.venda;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gft.entities.Venda;

@Component
public class VendaMapper {
	
	public static Venda fromDTO(RegistroVendaDTO dto) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(dto, Venda.class);
	}
	
	public static ConsultaVendaDTO fromEntity(Venda venda) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(venda, ConsultaVendaDTO.class);
	}
}
