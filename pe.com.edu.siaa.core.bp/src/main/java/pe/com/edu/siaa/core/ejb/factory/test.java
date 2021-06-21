package pe.com.edu.siaa.core.ejb.factory;

import javax.ws.rs.core.UriInfo;

import com.lowagie.text.SplitCharacter;

import pe.com.edu.siaa.core.model.dto.common.ListaItemsDTO;
import pe.com.edu.siaa.core.model.jpa.common.ListaItems;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListaItems resul = new ListaItems();
		UriInfo info = null;
		ListaItemsDTO newDTO = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info, ListaItemsDTO.class);
		//com/lowagie/text/SplitCharacter
		SplitCharacter aa;
	}

}
