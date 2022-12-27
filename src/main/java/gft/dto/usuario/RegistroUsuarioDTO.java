package gft.dto.usuario;

import lombok.Data;

@Data
public class RegistroUsuarioDTO {
	
	private String username;
	private String senha;
	private String email;
	private Long perfilId;
}
