package pe.com.builderp.core.facturacion.ejb.dao.venta.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.builderp.core.facturacion.model.dto.venta.PedidoDTO;
import pe.com.builderp.core.facturacion.model.jpa.venta.Pedido;
import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;

/**
 * La Class PedidoDaoLocal.
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
public interface PedidoDaoLocal  extends GenericDAOLocal<String,Pedido> {
	/**
	 * Listar pedido.
	 *
	 * @param pedido el pedidoDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<Pedido> listarPedido(PedidoDTO pedido) throws Exception;
	
	/**
	 * contar lista pedido.
	 *
	 * @param pedido el pedido
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarPedido(PedidoDTO pedido);
	/**
	 * Generar id pedido.
	 *
	 * @return the String
	 * @throws Exception the exception
	 */
	String generarIdPedido() throws Exception;
}