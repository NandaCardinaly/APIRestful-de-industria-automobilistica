package gft.dto.compra;



import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegistroItemCompraDTO {
	
	private Long pecaId;
	
	private Integer quantidade;
	
	private double valor;

}
