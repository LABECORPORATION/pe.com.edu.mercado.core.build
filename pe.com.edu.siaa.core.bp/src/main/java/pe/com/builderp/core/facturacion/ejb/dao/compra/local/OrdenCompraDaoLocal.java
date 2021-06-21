package pe.com.builderp.core.facturacion.ejb.dao.compra.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.builderp.core.facturacion.model.dto.compra.OrdenCompraDTO;
import pe.com.builderp.core.facturacion.model.jpa.compra.OrdenCompra;
import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;

/**
 * La Class OrdenCompraDaoLocal.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu Dec 28 12:02:20 COT 2017
 * @since SIAA-CORE 2.1
 */
@Local
public interface OrdenCompraDaoLocal  extends GenericDAOLocal<String,OrdenCompra> {
	/**
	 * Listar orden compra.
	 *
	 * @param ordenCompra el orden compraDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<OrdenCompra> listarOrdenCompra(OrdenCompraDTO ordenCompra) throws Exception;
	
	/**
	 * contar lista orden compra.
	 *
	 * @param ordenCompra el orden compra
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarOrdenCompra(OrdenCompraDTO ordenCompra);
	/**
	 * Generar id ordenCompra.
	 *
	 * @return the String
	 * @throws Exception the exception
	 */
	String generarIdOrdenCompra() throws Exception;
}