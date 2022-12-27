package gft.entities;





import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Table(name = "tb_compra")
@Entity
public class Compra {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@ManyToOne
	private Fornecedor fornecedor;
			
	private BigDecimal valorTotal;
	

	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "compra_id")
	private List<ItemCompra> itens;

	
	public Compra(Long id) {
		this.id = id;
	}


	


	
	
	
	


}
