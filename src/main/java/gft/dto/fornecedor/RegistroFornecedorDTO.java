package gft.dto.fornecedor;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegistroFornecedorDTO {
	
	private String nome;
	private double insumo;
	private String enderecoCep;
	
}