package pe.com.builderp.core.facturacion.ejb.dao.venta.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.builderp.core.facturacion.model.dto.venta.DetallePedidoDTO;
import pe.com.builderp.core.facturacion.model.jpa.compra.DetalleOrdenCompra;
import pe.com.builderp.core.facturacion.model.jpa.venta.DetallePedido;
import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;

/**
 * La Class DetallePedidoDaoLocal.
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
public interface DetallePedidoDaoLocal  extends GenericDAOLocal<String,DetallePedido> {
	/**
	 * Listar detalle pedido.
	 *
	 * @param detallePedido el detalle pedidoDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<DetallePedido> listarDetallePedido(DetallePedidoDTO detallePedido) throws Exception;
	
	/**
	 * contar lista detalle pedido.
	 *
	 * @param detallePedido el detalle pedido
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarDetallePedido(DetallePedidoDTO detallePedido);
	/**
	 * Generar id detallePedido.
	 *
	 * @return the String
	 * @throws Exception the exception
	 */
	String generarIdDetallePedido() throws Exception;
	
	List<DetallePedido> listarDetallePedido(String idPedido);
}