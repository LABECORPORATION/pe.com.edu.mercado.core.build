package pe.com.edu.siaa.core.ejb.service.common.local;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import pe.com.edu.siaa.core.model.dto.common.ItemDTO;
import pe.com.edu.siaa.core.model.dto.common.ListaItemsDTO;
import pe.com.edu.siaa.core.model.dto.common.ListaValoresDTO;
import pe.com.edu.siaa.core.model.dto.common.ParametroDTO;
import pe.com.edu.siaa.core.model.dto.common.UbigeoDTO;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.model.type.ListaItemType;
import pe.com.edu.siaa.core.model.vo.FileVO;
import pe.com.edu.siaa.core.model.vo.SelectItemVO;

/**
 * La Class SeguridadServiceLocal.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 13:43:23 COT 2017
 * @since SIAA-CORE 2.1
 */
@Local
public interface CommonServiceLocal {
	

	void subirImagen(FileVO fileVO)  throws Exception;
	
	String obtenerImagenEncodeBase64(FileVO fileVO)  throws Exception;
	/**
	 * Controlador accion parametro.
	 *
	 * @param parametro el parametro
	 * @param accionType el accion type
	 * @return the parametro
	 * @throws Exception the exception
	 */
	ParametroDTO controladorAccionParametro(ParametroDTO parametro,AccionType accionType) throws Exception; 
	
	/**
	 * Listar parametro.
	 *
	 * @param parametro el parametro
	 * @return the list
	 * @throws Exception the exception
	 */
	List<ParametroDTO> listarParametro(ParametroDTO parametro) throws Exception;
	
	/**
	 * contar lista parametro.
	 *
	 * @param parametro el parametro
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarParametro(ParametroDTO parametro);
	
	/**
     * Listar ubigeo select item.
     *
     * @param listaUbigeo el lista ubigeo
     * @return the list
     * @throws Exception the exception
     */
    List<SelectItemVO> listarUbigeoSelectItem(List<UbigeoDTO> listaUbigeo) throws Exception;
    
	/**
	 * Convertir lista item select item map.
	 *
	 * @param listaItem el lista item
	 * @return the map
	 * @throws Exception the exception
	 */
	Map<Long,List<SelectItemVO>> convertirListaItemSelectItemMap(List<ItemDTO> listaItem) throws Exception;
	
	/**
	 * Listar item.
	 *
	 * @param listaItemType el lista item type
	 * @return the list
	 * @throws Exception the exception
	 */
	List<ItemDTO> listarItem() throws Exception;
	
	
	/**
	 * Controlador accion lista items.
	 *
	 * @param listaItems el lista items
	 * @param accionType el accion type
	 * @return the lista items
	 * @throws Exception the exception
	 */
	ListaItemsDTO controladorAccionListaItems(ListaItemsDTO listaItems,AccionType accionType) throws Exception; 
	
	/**
	 * Listar lista items.
	 *
	 * @param listaItems el lista items
	 * @return the list
	 * @throws Exception the exception
	 */
	List<ListaItemsDTO> listarListaItems(ListaItemsDTO listaItems) throws Exception;
	
	/**
	 * contar lista lista items.
	 *
	 * @param listaItems el lista items
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarListaItems(ListaItemsDTO listaItems);
	
	/**
	 * Controlador accion item.
	 *
	 * @param item el item
	 * @param accionType el accion type
	 * @return the item
	 * @throws Exception the exception
	 */
	ItemDTO controladorAccionItem(ItemDTO item,AccionType accionType) throws Exception; 
	
	/**
	 * Listar item.
	 *
	 * @param item el item
	 * @return the list
	 * @throws Exception the exception
	 */
	List<ItemDTO> listarItem(ItemDTO item) throws Exception;
	
	/**
	 * contar lista item.
	 *
	 * @param item el item
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarItem(ItemDTO item);
	

	/**
	 * Controlador accion ubigeo.
	 *
	 * @param ubigeo el ubigeo
	 * @param accionType el accion type
	 * @return the ubigeo
	 * @throws Exception the exception
	 */
	UbigeoDTO controladorAccionUbigeo(UbigeoDTO ubigeo,AccionType accionType) throws Exception; 
	
	/**
	 * Listar ubigeo.
	 *
	 * @param ubigeo el ubigeo
	 * @return the list
	 * @throws Exception the exception
	 */
	List<UbigeoDTO> listarUbigeo(UbigeoDTO ubigeo) throws Exception;
	
	/**
	 * contar lista ubigeo.
	 *
	 * @param ubigeo el ubigeo
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarUbigeo(UbigeoDTO ubigeo);
	
	/**
	 * Controlador accion lista valores.
	 *
	 * @param listaValores el lista valores
	 * @param accionType el accion type
	 * @return the lista valores
	 * @throws Exception the exception
	 */
	ListaValoresDTO controladorAccionListaValores(ListaValoresDTO listaValores,AccionType accionType) throws Exception; 
	
	/**
	 * Listar lista valores.
	 *
	 * @param listaValores el lista valores
	 * @return the list
	 * @throws Exception the exception
	 */
	List<ListaValoresDTO> listarListaValores(ListaValoresDTO listaValores) throws Exception;
	
	/**
	 * contar lista lista valores.
	 *
	 * @param listaValores el lista valores
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarListaValores(ListaValoresDTO listaValores);
	
	//agregado
	ItemDTO obteneItemId(ListaItemType itemState) throws Exception;
	
}