package gft.dto.usuario;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gft.entities.Usuario;

@Component
public class UsuarioMapper {

	public static Usuario fromDTO(RegistroUsuarioDTO dto) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(dto, Usuario.class);
	}
	
	public static ConsultaUsuarioDTO fromEntity(Usuario usuario) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(usuario, ConsultaUsuarioDTO.class);
	}
}
