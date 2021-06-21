package pe.com.builderp.core.facturacion.ejb.dao.venta.local;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import pe.com.builderp.core.facturacion.model.dto.venta.DetalleVentaDTO;
import pe.com.builderp.core.facturacion.model.jpa.compra.DetalleCompra;
import pe.com.builderp.core.facturacion.model.jpa.venta.DetalleVenta;
import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;
import pe.com.edu.siaa.core.model.vo.DetalleVentaFiltroVO;
import pe.com.edu.siaa.core.model.vo.VentaFiltroVO;

/**
 * La Class DetalleVentaDaoLocal.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu Dec 28 12:02:23 COT 2017
 * @since SIAA-CORE 2.1
 */
@Local
public interface DetalleVentaDaoLocal  extends GenericDAOLocal<String,DetalleVenta> {	
	/**
	 * Listar detalle venta.
	 *
	 * @param detalleVenta el detalle ventaDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<DetalleVenta> listarDetalleVenta(DetalleVentaDTO detalleVenta) throws Exception;
	
	/**
	 * contar lista detalle venta.
	 *
	 * @param detalleVenta el detalle venta
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarDetalleVenta(DetalleVentaDTO detalleVenta);
	/**
	 * Generar id detalleVenta.
	 *
	 * @return the String
	 * @throws Exception the exception
	 */
	String generarIdDetalleVenta() throws Exception;
	List<DetalleVenta> listarDetVenta(String idVenta) throws Exception;
	
	List<DetalleVenta> listarDetalleVentaPedidoTemp(List<String> idPedido);
	
	List<DetalleVentaFiltroVO> obtenerProductoVendidos(VentaFiltroVO filter) throws Exception;
	List<DetalleVentaFiltroVO> obtenerDetallVentaMap(List<Long>listadoIdProductos, Date fechaInicio, Date fechaFin) throws Exception;
}