package gft.dto.loja;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegistroLojaDTO {

	private String nome;
	private String cnpj;
	private String enderecoCep;
	
}
