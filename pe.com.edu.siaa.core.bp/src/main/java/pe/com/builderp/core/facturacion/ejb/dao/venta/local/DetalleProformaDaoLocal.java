package pe.com.builderp.core.facturacion.ejb.dao.venta.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.builderp.core.facturacion.model.dto.venta.DetalleProformaDTO;
import pe.com.builderp.core.facturacion.model.jpa.venta.DetalleProforma;
import pe.com.builderp.core.facturacion.model.jpa.venta.DetalleVenta;
import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;

/**
 * La Class DetalleProformaDaoLocal.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu Dec 28 12:02:22 COT 2017
 * @since SIAA-CORE 2.1
 */
@Local
public interface DetalleProformaDaoLocal  extends GenericDAOLocal<String,DetalleProforma> {
	/**
	 * Listar detalle proforma.
	 *
	 * @param detalleProforma el detalle proformaDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<DetalleProforma> listarDetalleProforma(DetalleProformaDTO detalleProforma) throws Exception;
	
	/**
	 * contar lista detalle proforma.
	 *
	 * @param detalleProforma el detalle proforma
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarDetalleProforma(DetalleProformaDTO detalleProforma);
	/**
	 * Generar id detalleProforma.
	 *
	 * @return the String
	 * @throws Exception the exception
	 */
	String generarIdDetalleProforma() throws Exception;
	
	List<DetalleProforma> listarDetProforma(String idProforma) throws Exception;
}