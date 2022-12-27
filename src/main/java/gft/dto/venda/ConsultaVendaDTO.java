package gft.dto.venda;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ConsultaVendaDTO {

	private Long id;
	private BigDecimal valorTotal;
	private int qtdItens;
	private String clienteNome;
	private String clienteCpf;
}
