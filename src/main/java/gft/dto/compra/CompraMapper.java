package gft.dto.compra;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import gft.entities.Compra;



@Component("CompraMapper")
public class CompraMapper {
	
	public static Compra converteDTOEmCompra(RegistroCompraDTO compraDTO) {
		ModelMapper modelMapper = new ModelMapper();
	//	modelMapper.createTypeMap(compraDTO, Compra.class).
	//	<List>addMapping(src -> src.getItens(), (dest, value) -> dest.setItens(value)); 
		
		return modelMapper.map(compraDTO, Compra.class);
		
		}
	

	public static ConsultaCompraDTO converteCompraEmDTO(Compra compra) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(compra, ConsultaCompraDTO.class);
	}

}
