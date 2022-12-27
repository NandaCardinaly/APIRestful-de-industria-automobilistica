package gft.dto.venda;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegistroVendaDTO {

	private BigDecimal valorTotal;
	private int qtdItens;
	private String clienteId;
}
