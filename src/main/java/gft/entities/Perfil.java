package gft.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import gft.enums.NomePerfil;
import lombok.Data;

@Entity
@Table(name = "tb_perfil")
@Data
public class Perfil implements GrantedAuthority{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private NomePerfil nome;

	@Override
	public String getAuthority() {
		return this.nome.toString();
	}
}
