package pe.com.builderp.core.facturacion.ejb.dao.empresa.local;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import pe.com.builderp.core.facturacion.model.dto.empresa.ControlPagoDTO;
import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;
import pe.com.edu.siaa.core.model.jpa.empresa.ControlPago;
import pe.com.edu.siaa.core.model.vo.PagoFiltroVO;
import pe.com.edu.siaa.core.model.vo.VentaFiltroVO; 

/**
 * La Class ControlPagoDaoLocal.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 11:25:13 COT 2017
 * @since SIAA-CORE 2.1
 */
@Local
public interface ControlPagoDaoLocal  extends GenericDAOLocal<String,ControlPago> {
	
	String generarNumeroOperacion(Integer anho) throws Exception;
	
	/**
	 * Generar id controlPago.
	 *
	 * @param controlPago el cuota concepto
	 * @return the long
	 * @throws Exception the exception
	 */
	String generarIdControlPago(String idAnhoSemestre) throws Exception;
	
	/**
	 * Listar control pago.
	 *
	 * @param controlPago el control pagoDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<ControlPago> listarControlPago(ControlPagoDTO controlPago) throws Exception;
	
	/**
	 * contar lista control pago.
	 *
	 * @param controlPago el control pago
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarControlPago(ControlPagoDTO controlPago);
	/**
	 * Generar id controlPago.
	 *
	 * @return the String
	 * @throws Exception the exception
	 */
	String generarIdControlPago() throws Exception;
	
//agregado
	
	ControlPago findControlPagoRepetido(String nroDoc,Long idTipoDoc) throws Exception ;
	
	void updaterControlPago(String idControlPago,BigDecimal cuotaMonto ) throws Exception;
	
	void updaterControlPagoByconvertidor(String idControlPago,BigDecimal cuotaMonto,String estado ) throws Exception;
	
	void updaterControlPagoManual(String idControlPago,BigDecimal cuotaMonto ) throws Exception;
	
	List<ControlPago> listaControlPagoIdalumno(String idControlpago) throws Exception;
	
	List<PagoFiltroVO> obtenerIngresoDetalladoMap( Date fechaInicio, Date fechaFin) throws Exception;
	
	ControlPago findAlumnoByControlPago(String idDetControlPago) throws Exception;
	
	
	List<Object[]> listaControlPagoGrupByFechas() throws Exception;
	
	List<Object[]> listaControlPagoGrupByFechasAnulados() throws Exception;
	
	List<Map<String,Object>> generarArchivosPlanosXML(ControlPagoDTO controlp) throws Exception;
	
	List<ControlPago> listaVentaExtracionF() throws Exception;
	
	void updateVentaExtracion() throws Exception;
	
	void updateVentaExtracionAnulados() throws Exception;
	
	int verificarButtonAnulados() throws Exception;
	
	List<ControlPago> obtenerListaCajaFiltroPago(VentaFiltroVO filtro) throws Exception;
	
	void updateControlPagoCierre(String objeto,String idUsuario,Date fecha) throws Exception;
}