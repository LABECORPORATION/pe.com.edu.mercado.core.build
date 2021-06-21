package pe.com.builderp.core.facturacion.ejb.dao.venta.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.builderp.core.facturacion.model.dto.venta.DetalleEntregaDTO;

import pe.com.builderp.core.facturacion.model.jpa.venta.DetalleEntrega;
import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;

/**
 * La Class DetalleEntregaDaoLocal.
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
public interface DetalleEntregaDaoLocal  extends GenericDAOLocal<String,DetalleEntrega> {
	/**
	 * Listar detalle pedido.
	 *
	 * @param detalleEntrega el detalle pedidoDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<DetalleEntrega> listarDetalleEntrega(DetalleEntregaDTO detalleEntrega) throws Exception;
	
	/**
	 * contar lista detalle pedido.
	 *
	 * @param detalleEntrega el detalle pedido
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarDetalleEntrega(DetalleEntregaDTO detalleEntrega);
	/**
	 * Generar id detalleEntrega.
	 *
	 * @return the String
	 * @throws Exception the exception
	 */
	String generarIdDetalleEntrega() throws Exception;
	
	List<DetalleEntrega> listarDetalleEntrega(String idEntrega);
}