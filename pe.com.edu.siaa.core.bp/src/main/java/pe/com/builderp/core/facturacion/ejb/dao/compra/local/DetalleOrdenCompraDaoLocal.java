package pe.com.builderp.core.facturacion.ejb.dao.compra.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.builderp.core.facturacion.model.dto.compra.DetalleOrdenCompraDTO;
import pe.com.builderp.core.facturacion.model.jpa.compra.DetalleOrdenCompra;
import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;

/**
 * La Class DetalleOrdenCompraDaoLocal.
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
public interface DetalleOrdenCompraDaoLocal  extends GenericDAOLocal<String,DetalleOrdenCompra> {
	/**
	 * Listar detalle orden compra.
	 *
	 * @param detalleOrdenCompra el detalle orden compraDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<DetalleOrdenCompra> listarDetalleOrdenCompra(DetalleOrdenCompraDTO detalleOrdenCompra) throws Exception;
	
	/**
	 * contar lista detalle orden compra.
	 *
	 * @param detalleOrdenCompra el detalle orden compra
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarDetalleOrdenCompra(DetalleOrdenCompraDTO detalleOrdenCompra);
	/**
	 * Generar id detalleOrdenCompra.
	 *
	 * @return the String
	 * @throws Exception the exception
	 */
	String generarIdDetalleOrdenCompra() throws Exception;
	
	List<DetalleOrdenCompra> listarDetalleOrdenCompra(String idOrdenCompra);
}