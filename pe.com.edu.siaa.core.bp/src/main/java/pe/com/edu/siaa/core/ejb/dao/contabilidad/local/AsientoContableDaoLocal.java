package pe.com.edu.siaa.core.ejb.dao.contabilidad.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.builderp.core.contabilidad.model.vo.RegistroAsientoFiltroVO;
import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;
import pe.com.edu.siaa.core.model.jpa.contabilidad.AsientoContable;

/**
 * La Class AsientoContableDaoLocal.
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
public interface AsientoContableDaoLocal  extends GenericDAOLocal<String,AsientoContable> {
	
	String generarNumeroAsiento(Integer anho,String idEntidad) throws Exception;
	
	String generarNumeroOperacion(Integer anho,String idEntidad, Long idLibro, Long idSubLibro) throws Exception;
	
	/**
	 * Listar asiento contable.
	 *
	 * @param asientoContable el asiento contableDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<AsientoContable> listarAsientoContable(RegistroAsientoFiltroVO registroVentaFiltro) throws Exception;
	
	/**
	 * contar lista asiento contable.
	 *
	 * @param asientoContable el asiento contable
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarAsientoContable(RegistroAsientoFiltroVO registroVentaFiltro);
	/**
	 * Generar id asientoContable.
	 *
	 * @return the String
	 * @throws Exception the exception
	 */
	String generarIdAsientoContable() throws Exception;
}