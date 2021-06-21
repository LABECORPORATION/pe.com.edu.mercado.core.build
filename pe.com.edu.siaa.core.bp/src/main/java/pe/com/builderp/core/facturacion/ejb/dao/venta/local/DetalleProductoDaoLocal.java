package pe.com.builderp.core.facturacion.ejb.dao.venta.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.builderp.core.facturacion.model.dto.venta.DetalleProductoDTO; 
import pe.com.builderp.core.facturacion.model.jpa.venta.DetalleProducto; 
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
public interface DetalleProductoDaoLocal  extends GenericDAOLocal<String,DetalleProducto> {
	/**
	 * Listar producto.
	 *
	 * @param producto el productoDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<DetalleProducto> listarDetalleProducto(DetalleProductoDTO DetalleProducto) throws Exception;
	
	List<DetalleProducto> listarDetalleProductoBy(Long idProducto) throws Exception;
	
	/**
	 * contar lista DetalleProducto.
	 *
	 * @param DetalleProducto el DetalleProducto
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarDetalleProducto(DetalleProductoDTO DetalleProducto);
	/**
	 * Generar id DetalleProducto.
	 *
	 * @return the Long
	 * @throws Exception the exception
	 */
	String generarIdDetalleProducto() throws Exception;
	
	DetalleProducto findByDetProducto(String codigo) throws Exception;
	
	List<DetalleProducto> listaDetalleProducto(Long idProducto) throws Exception;
	
	DetalleProducto findByDetalleProductoIdProducto(Long idProducto) throws Exception;
	
	List<DetalleProducto> listarDetalleProductoCombo(DetalleProductoDTO detalleProducto) throws Exception;
	
}