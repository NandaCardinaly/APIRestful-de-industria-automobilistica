package gft.entities;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_peca")
@Data
@NoArgsConstructor
public class Peca {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private String descricao;
	
	private BigDecimal valor;
	
	private String origem;
	
	//@ManyToOne
//	private Estoque estoque;
	
	@ManyToOne
	private Fornecedor fornecedor;
	
	@ManyToOne
	private Compra compra;

	
	
	
	public Peca(Long id) {
	this.id = id;
}
	
	
	
}
