package pe.com.builderp.core.facturacion.ejb.dao.venta.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.builderp.core.facturacion.model.dto.venta.ComboDTO; 
import pe.com.builderp.core.facturacion.model.jpa.venta.Combo; 
import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;

/**
 * La Class ProductoDaoLocal.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu Dec 28 12:02:24 COT 2017
 * @since SIAA-CORE 2.1
 */
@Local
public interface ComboDaoLocal  extends GenericDAOLocal<String,Combo> {
	/**
	 * Listar producto.
	 *
	 * @param producto el productoDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<Combo> listarCombo(ComboDTO Combo) throws Exception;
	
	List<Combo> listarComboBy(String idDetalleProducto) throws Exception;
	
	/**
	 * contar lista Combo.
	 *
	 * @param Combo el Combo
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarCombo(ComboDTO Combo);
	/**
	 * Generar id Combo.
	 *
	 * @return the Long
	 * @throws Exception the exception
	 */
	String generarIdCombo() throws Exception;
	
	
	List<Combo> listaCombo(Long idDetalleProducto) throws Exception;
	
	Combo findByComboIdDetalleProducto(Long idDetalleProducto) throws Exception;
}