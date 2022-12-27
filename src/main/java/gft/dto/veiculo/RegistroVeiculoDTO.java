package gft.dto.veiculo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegistroVeiculoDTO {
	
	private String origem;
	private String modelo;
	private double insumo = 5.000;
	private double valorTotal;

}
