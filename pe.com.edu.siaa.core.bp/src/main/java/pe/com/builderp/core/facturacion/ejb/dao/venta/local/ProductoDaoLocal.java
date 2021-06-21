package pe.com.builderp.core.facturacion.ejb.dao.venta.local;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Local;


import pe.com.builderp.core.facturacion.model.dto.venta.ProductoDTO;
import pe.com.builderp.core.facturacion.model.jpa.venta.Producto;
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
public interface ProductoDaoLocal  extends GenericDAOLocal<Long,Producto> {
	/**
	 * Listar producto.
	 *
	 * @param producto el productoDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<Producto> listarProducto(ProductoDTO producto) throws Exception;
	
	/**
	 * contar lista producto.
	 *
	 * @param producto el producto
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarProducto(ProductoDTO producto);
	/**
	 * Generar id producto.
	 *
	 * @return the Long
	 * @throws Exception the exception
	 */
	Long generarIdProducto() throws Exception;
	
	void updateOferta() throws Exception;
	
	
	void updateProductoStock(Long idProducto,BigDecimal numeroStock ) throws Exception;
}