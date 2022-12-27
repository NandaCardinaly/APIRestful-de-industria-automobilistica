package gft.dto.peca;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ConsultaPecaDTO {

	private Long id;
	private String nome;
	private String descricao;
	private BigDecimal valor;
	private String origem;
}
