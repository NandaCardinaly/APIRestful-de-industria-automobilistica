package gft.dto.estoque;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ConsultaEstoqueDTO {

	private Long id;
	private int qtdPecas;
	private int qtdVeiculos;
	private BigDecimal  valorTotal;
	
}
