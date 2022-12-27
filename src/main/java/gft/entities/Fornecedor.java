package gft.entities;

import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_fornecedor")
@Data
@NoArgsConstructor
public class Fornecedor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private double insumo;
		
	@Embedded
	private Endereco endereco;
	
	@OneToMany(mappedBy = "fornecedor")
	private List<Compra> compra;
	

	public Fornecedor(Long id) {
			this.id = id;
	}
	
	
	
	
	

}