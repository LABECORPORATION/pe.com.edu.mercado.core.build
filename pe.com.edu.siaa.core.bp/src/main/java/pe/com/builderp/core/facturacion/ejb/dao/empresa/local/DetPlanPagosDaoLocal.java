package pe.com.builderp.core.facturacion.ejb.dao.empresa.local;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Local;

import pe.com.builderp.core.facturacion.model.dto.empresa.DetPlanPagosDTO;
import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;
import pe.com.edu.siaa.core.model.jpa.empresa.DetPlanPagos;
import pe.com.edu.siaa.core.model.vo.DetallePlanPagosFiltroVO; 

/**
 * La Class DetPlanPagosDaoLocal.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 11:25:03 COT 2017
 * @since SIAA-CORE 2.1
 */
@Local
public interface DetPlanPagosDaoLocal  extends GenericDAOLocal<String,DetPlanPagos> {
	
	/**
	 * Generar id detPlanPagos.
	 *
	 * @param detPlanPagos el cuota concepto
	 * @return the long
	 * @throws Exception the exception
	 */
	String generarIdDetPlanPagos(String idPlanPagos) throws Exception;
	
	/**
	 * Actualizar monto resta.
	 *
	 * @param detPlanPagos el det plan pagos
	 * @throws Exception the exception
	 */
	void actualizarMontoResta(DetPlanPagos detPlanPagos ) throws Exception; 


	List<DetPlanPagos> listarConceptoPagoAsociadoSemestre(String idCliente, boolean flagFaltaMontoResta) throws Exception;
	
	List<DetPlanPagos> listarConceptoPagoAsociadoSemestreAPP(String idAsociado, String idCuotaconcepto) throws Exception;
	/**
	 * Listar det plan pagos.
	 *
	 * @param detPlanPagos el det plan pagosDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<DetPlanPagos> listarDetPlanPagos(DetPlanPagosDTO detPlanPagos) throws Exception;
	
	/**
	 * contar lista det plan pagos.
	 *
	 * @param detPlanPagos el det plan pagos
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarDetPlanPagos(DetPlanPagosDTO detPlanPagos);
	/**
	 * Generar id detPlanPagos.
	 *
	 * @return the String
	 * @throws Exception the exception
	 */
	String generarIdDetPlanPagos() throws Exception;
	
//agregados
	
	List<DetPlanPagos> listarDetPlanPagosID(String idplanpagos,String idcuotaconcepto) throws Exception;
	
	void updaterDetPlanPagos(String iddetPlanpagos,BigDecimal monto ) throws Exception;
	
	
	DetPlanPagos optenerByDetPlanPagos(String idPlanPagos,String idConcepto,String idPuesto) throws Exception ;
	
	List<DetallePlanPagosFiltroVO> listarDetallePlanPagosFiltroVO(DetallePlanPagosFiltroVO filtro) throws Exception;
}