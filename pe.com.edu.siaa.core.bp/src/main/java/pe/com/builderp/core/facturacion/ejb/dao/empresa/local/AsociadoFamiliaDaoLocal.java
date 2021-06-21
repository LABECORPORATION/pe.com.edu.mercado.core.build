package pe.com.builderp.core.facturacion.ejb.dao.empresa.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.builderp.core.facturacion.model.dto.empresa.AsociadoFamiliaDTO;
import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;
import pe.com.edu.siaa.core.model.jpa.empresa.AsociadoFamilia;

/**
 * La Class AsociadoFamiliaDaoLocal.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu Dec 28 12:02:21 COT 2017
 * @since SIAA-CORE 2.1
 */
@Local
public interface AsociadoFamiliaDaoLocal  extends GenericDAOLocal<String,AsociadoFamilia> {
	/**
	 * Listar AsociadoFamilia.
	 *
	 * @param AsociadoFamilia el AsociadoFamiliaDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<AsociadoFamilia> listarAsociadoFamilia(AsociadoFamiliaDTO AsociadoFamilia) throws Exception;
	
	/**
	 * contar lista AsociadoFamilia.
	 *
	 * @param AsociadoFamilia el AsociadoFamilia
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarAsociadoFamilia(AsociadoFamiliaDTO AsociadoFamilia);
	/**
	 * Generar id AsociadoFamilia.
	 *
	 * @return the Long
	 * @throws Exception the exception
	 */
	String generarIdAsociadoFamilia() throws Exception;
}