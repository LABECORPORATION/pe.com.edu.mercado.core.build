package pe.com.builderp.core.facturacion.ejb.dao.venta.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.builderp.core.facturacion.model.dto.venta.ProductoDTO;
import pe.com.builderp.core.facturacion.model.dto.venta.ProductoProveedorDTO;
import pe.com.builderp.core.facturacion.model.jpa.venta.Producto;
import pe.com.builderp.core.facturacion.model.jpa.venta.ProductoProveedor;
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
public interface ProductoProveedorDaoLocal  extends GenericDAOLocal<String,ProductoProveedor> {
	/**
	 * Listar producto.
	 *
	 * @param producto el productoDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<ProductoProveedor> listarProductoProveedor(ProductoProveedorDTO productoProveedor) throws Exception;
	
	/**
	 * contar lista ProductoProveedor.
	 *
	 * @param ProductoProveedor el ProductoProveedor
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarProductoProveedor(ProductoProveedorDTO productoProveedor);
	/**
	 * Generar id ProductoProveedor.
	 *
	 * @return the Long
	 * @throws Exception the exception
	 */
	String generarIdProductoProveedor() throws Exception;
	
	
	List<ProductoProveedor> listaProductoProveedor(Long idProducto) throws Exception;
	 
}