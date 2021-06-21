package pe.com.edu.siaa.core.ejb.dao.common.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;
import pe.com.edu.siaa.core.model.dto.common.ListaItemsDTO;
import pe.com.edu.siaa.core.model.jpa.common.ListaItems;

/**
 * La Class ListaItemsDaoLocal.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 11:25:03 COT 2017
 * @since SIAA-CORE 2.1
 */
@Local
public interface ListaItemsDaoLocal  extends GenericDAOLocal<Long,ListaItems> {
	/**
	 * Listar lista items.
	 *
	 * @param listaItems el lista itemsDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<ListaItems> listarListaItems(ListaItemsDTO listaItems) throws Exception;
	
	/**
	 * contar lista lista items.
	 *
	 * @param listaItems el lista items
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarListaItems(ListaItemsDTO listaItems);
	/**
	 * Generar id listaItems.
	 *
	 * @return the Long
	 * @throws Exception the exception
	 */
	Long generarIdListaItems() throws Exception;
}