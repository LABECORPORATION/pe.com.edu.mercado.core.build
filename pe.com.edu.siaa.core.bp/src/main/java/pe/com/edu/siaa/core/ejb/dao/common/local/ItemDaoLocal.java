package pe.com.edu.siaa.core.ejb.dao.common.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.edu.siaa.core.model.type.ListaItemType;
import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;
import pe.com.edu.siaa.core.model.dto.common.ItemDTO;
import pe.com.edu.siaa.core.model.jpa.common.Item;

/**
 * La Class ItemDaoLocal.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 11:25:04 COT 2017
 * @since SIAA-CORE 2.1
 */
@Local
public interface ItemDaoLocal  extends GenericDAOLocal<Long,Item> {
	
	/**
	 * Listar item.
	 *
	 * @param listaItemType el lista item type
	 * @return the list
	 * @throws Exception the exception
	 */
	List<Item> listarItem() throws Exception;
	
	/**
	 * Listar item.
	 *
	 * @param item el itemDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<Item> listarItem(ItemDTO item) throws Exception;
	
	/**
	 * contar lista item.
	 *
	 * @param item el item
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarItem(ItemDTO item);
	/**
	 * Generar id item.
	 *
	 * @return the Long
	 * @throws Exception the exception
	 */
	Long generarIdItem() throws Exception;
	
	//agregado
	
	Item obteneItemId(ListaItemType itemState) throws Exception;
	
}