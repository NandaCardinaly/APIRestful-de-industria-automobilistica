package gft.dto.fornecedor;


import gft.entities.Endereco;
import lombok.Data;

@Data
public class ConsultaFornecedorDTO {
	
	private Long id;
	private String nome;
	private Double insumo;
	private Endereco endereco;

	
}