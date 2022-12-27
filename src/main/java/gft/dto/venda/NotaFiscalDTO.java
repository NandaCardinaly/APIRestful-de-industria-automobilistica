package gft.dto.venda;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class NotaFiscalDTO {

	private String clienteNome;
	private String clienteCpf;
	private String nomeLoja;
	private String cnpjLoja;
	private BigDecimal valorTotal;
}
