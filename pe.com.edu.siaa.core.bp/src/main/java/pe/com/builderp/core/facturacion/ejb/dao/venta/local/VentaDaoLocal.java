package pe.com.builderp.core.facturacion.ejb.dao.venta.local;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import pe.com.builderp.core.contabilidad.model.vo.RegistroAsientoFiltroVO;
import pe.com.builderp.core.facturacion.model.dto.venta.VentaDTO;
import pe.com.builderp.core.facturacion.model.jpa.compra.Compra;
import pe.com.builderp.core.facturacion.model.jpa.venta.Venta;
import pe.com.builderp.core.facturacion.model.vo.venta.RegistroVentaVO;
import pe.com.builderp.core.facturacion.model.vo.venta.TendenciasVO;
import pe.com.builderp.core.facturacion.model.vo.venta.VentaGraficoVO;
import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;
import pe.com.edu.siaa.core.model.vo.VentaFiltroVO;

/**
 * La Class VentaDaoLocal.
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
public interface VentaDaoLocal  extends GenericDAOLocal<String,Venta> {
	
    List<RegistroVentaVO> listarVentaReporte(RegistroAsientoFiltroVO registroVentaFiltroVO) throws Exception;
    
    List<Map<String,Object>> listarVentaReporteTXT(RegistroAsientoFiltroVO registroVentaFiltroVO) throws Exception;
	
	int contarListarVentaReporte(RegistroAsientoFiltroVO registroVentaFiltroVO);
	
	int contarListarVentaReporteTXT(RegistroAsientoFiltroVO registroVentaFiltroVO);
	
	/**
	 * Listar venta.
	 *
	 * @param venta el ventaDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<Venta> listarVenta(VentaDTO venta) throws Exception;
	
	/**
	 * contar lista venta.
	 *
	 * @param venta el venta
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarVenta(VentaDTO venta);
	/**
	 * Generar id venta.
	 *
	 * @return the String
	 * @throws Exception the exception
	 */
	String generarIdVenta() throws Exception;
	
	 void updateVentaEstado(String idVenta ) throws Exception;
	 
	 
	 List<Venta> listarVentaReporte(VentaDTO venta) throws Exception;
	 
	 Venta findVenta(String idVenta) throws Exception;
	 //add
	// List<Object[]> listaVentaGrupByFechas() throws Exception;
	 
	 List<Venta> listaVentaExtracionF() throws Exception;
		
	List<Map<String,Object>> generarArchivosPlanosXML(VentaDTO ventap) throws Exception;
	
	List<Object[]> listaControlPagoGrupByFechas() throws Exception;
	
	void updateVentaExtracion() throws Exception;
	
	void updateVentaCierre(String objeto,String idUsuario,Date fecha) throws Exception;	
	
	List<Venta> obtenerListaCajaFiltro(VentaFiltroVO filtro) throws Exception;
	
	List<Venta> listarVentaPedidoTemp(String idPedido);
	
	List<VentaGraficoVO> obtenerDataGanancias(String idMes,String idAnho) throws Exception;
	
	List<VentaGraficoVO> obtenerVentaAnho() throws Exception;
	Map<String,List<BigDecimal>> obtenerVentaMes(String idAnho) throws Exception;
	
	List<TendenciasVO> obtenerDataTendencias() throws Exception;
	
	 List<VentaFiltroVO> obtenerIngresoDetalladoMap( Date fechaInicio, Date fechaFin) throws Exception;
		
}