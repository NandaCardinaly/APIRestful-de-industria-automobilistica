package gft.dto.veiculo;

import lombok.Data;

@Data
public class ConsultaVeiculoDTO {
	
	private Long id;
	private String origem;
	private String modelo;
	private double insumo = 5.000;
	private double valorTotal;

}
