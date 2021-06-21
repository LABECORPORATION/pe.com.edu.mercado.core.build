package pe.com.builderp.core.facturacion.ejb.dao.empresa.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.builderp.core.facturacion.model.dto.empresa.PlanPagosDTO;
import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;
import pe.com.edu.siaa.core.model.jpa.empresa.PlanPagos; 

/**
 * La Class PlanPagosDaoLocal.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 11:25:12 COT 2017
 * @since SIAA-CORE 2.1
 */
@Local
public interface PlanPagosDaoLocal  extends GenericDAOLocal<String,PlanPagos> {
	/**
	 * Listar plan pagos.
	 *
	 * @param planPagos el plan pagosDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<PlanPagos> listarPlanPagos(PlanPagosDTO planPagos) throws Exception;
	
	/**
	 * contar lista plan pagos.
	 *
	 * @param planPagos el plan pagos
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarPlanPagos(PlanPagosDTO planPagos);
	
	String generarIdPlanPagos(String idAsociado) throws Exception;
	/**
	 * Generar id planPagos.
	 *
	 * @return the String
	 * @throws Exception the exception
	 */
	String generarIdPlanPagos() throws Exception;
	
	List<PlanPagos> listarPlanPagosID(String idAsociado) throws Exception; 
	
	PlanPagos findByPlanPagos(PlanPagosDTO planPagos) throws Exception;
}