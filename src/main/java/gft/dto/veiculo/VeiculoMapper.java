package gft.dto.veiculo;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gft.entities.Veiculo;

@Component
public class VeiculoMapper {
	
	public static Veiculo fromDTO(RegistroVeiculoDTO dto) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(dto, Veiculo.class);
	}
	
	public static ConsultaVeiculoDTO fromEntity(Veiculo veiculo) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(veiculo, ConsultaVeiculoDTO.class);
	}
}