package gft.entities;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "tb_venda")
@Data
public class Venda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private double valorTotal;
	
	private int qtdItens;
	
	@ManyToOne
	private Estoque estoque;
	
	@ManyToOne
	private Cliente cliente;
}
