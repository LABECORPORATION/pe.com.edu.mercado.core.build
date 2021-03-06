package pe.com.builderp.core.facturacion.ejb.dao.compra.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.builderp.core.facturacion.model.dto.compra.DetalleCompraDTO;
import pe.com.builderp.core.facturacion.model.jpa.compra.DetalleCompra;
import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;

/**
 * La Class DetalleCompraDaoLocal.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu Dec 28 12:02:19 COT 2017
 * @since SIAA-CORE 2.1
 */
@Local
public interface DetalleCompraDaoLocal  extends GenericDAOLocal<String,DetalleCompra> {
	/**
	 * Listar detalle compra.
	 *
	 * @param detalleCompra el detalle compraDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<DetalleCompra> listarDetalleCompra(DetalleCompraDTO detalleCompra) throws Exception;
	
	/**
	 * contar lista detalle compra.
	 *
	 * @param detalleCompra el detalle compra
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarDetalleCompra(DetalleCompraDTO detalleCompra);
	/**
	 * Generar id detalleCompra.
	 *
	 * @return the String
	 * @throws Exception the exception
	 */
	String generarIdDetalleCompra() throws Exception;
	
	 List<DetalleCompra> listarDetalleCompraTemp(List<String>idCompra);
}