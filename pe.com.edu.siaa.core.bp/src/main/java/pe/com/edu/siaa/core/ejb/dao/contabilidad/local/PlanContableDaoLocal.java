package pe.com.edu.siaa.core.ejb.dao.contabilidad.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;
import pe.com.edu.siaa.core.model.dto.contabilidad.PlanContableDTO;
import pe.com.edu.siaa.core.model.jpa.contabilidad.PlanContable;

/**
 * La Class PlanContableDaoLocal.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Fri Sep 08 16:22:23 COT 2017
 * @since SIAA-CORE 2.1
 */
@Local
public interface PlanContableDaoLocal  extends GenericDAOLocal<String,PlanContable> {
	/**
	 * Listar plan contable.
	 *
	 * @param planContable el plan contableDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<PlanContable> listarPlanContable(PlanContableDTO planContable) throws Exception;
	
	/**
	 * contar lista plan contable.
	 *
	 * @param planContable el plan contable
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarPlanContable(PlanContableDTO planContable);
	/**
	 * Generar id planContable.
	 *
	 * @return the Long
	 * @throws Exception the exception
	 */
	String generarIdPlanContable() throws Exception;
}