package gft.dto.loja;



import gft.entities.Endereco;
import lombok.Data;

@Data
public class ConsultaLojaDTO {

	private Long id;
	private String nome;
	private String cnpj;
	private Endereco endereco;
	
}
