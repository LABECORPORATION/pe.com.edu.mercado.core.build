package pe.com.edu.siaa.core.ejb.dao.contabilidad.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;
import pe.com.edu.siaa.core.model.dto.contabilidad.AsientoContableDetDTO;
import pe.com.edu.siaa.core.model.jpa.contabilidad.AsientoContableDet;

/**
 * La Class AsientoContableDetDaoLocal.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Fri Sep 08 16:22:22 COT 2017
 * @since SIAA-CORE 2.1
 */
@Local
public interface AsientoContableDetDaoLocal  extends GenericDAOLocal<String,AsientoContableDet> {
	
	/**
	 * Listar asiento contable det reporte.
	 *
	 * @param asientoContableDet el asiento contable detDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<AsientoContableDet> listarAsientoContableDetReporte(AsientoContableDetDTO asientoContableDet) throws Exception;
	
	/**
	 * contar lista asiento contable det reporte.
	 *
	 * @param asientoContableDet el asiento contable det
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarAsientoContableDetReporte(AsientoContableDetDTO asientoContableDet);
	
	/**
	 * Listar asiento contable det.
	 *
	 * @param asientoContableDet el asiento contable detDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<AsientoContableDet> listarAsientoContableDet(AsientoContableDetDTO asientoContableDet) throws Exception;
	
	/**
	 * contar lista asiento contable det.
	 *
	 * @param asientoContableDet el asiento contable det
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarAsientoContableDet(AsientoContableDetDTO asientoContableDet);
	/**
	 * Generar id asientoContableDet.
	 *
	 * @return the String
	 * @throws Exception the exception
	 */
	String generarIdAsientoContableDet() throws Exception;
}