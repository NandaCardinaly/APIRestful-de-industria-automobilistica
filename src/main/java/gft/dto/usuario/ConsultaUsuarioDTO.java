package gft.dto.usuario;

import lombok.Data;

@Data
public class ConsultaUsuarioDTO {

	private Long id;
	private String username;
	private String email;
	private Long perfilId;
}
