package gft.dto.compra;




import java.util.List;

import gft.entities.ItemCompra;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegistroCompraDTO {
	

	
	private Long fornecedorId;
	private List<ItemCompra> itens;
	
	

}
