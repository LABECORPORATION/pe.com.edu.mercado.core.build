package pe.com.edu.siaa.core.ejb.dao.common.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;
import pe.com.edu.siaa.core.model.dto.common.ParametroDTO;
import pe.com.edu.siaa.core.model.jpa.common.Parametro;

/**
 * La Class ParametroDaoLocal.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu Dec 14 00:27:42 COT 2017
 * @since SIAA-CORE 2.1
 */
@Local
public interface ParametroDaoLocal  extends GenericDAOLocal<String,Parametro> {
	/**
	 * Listar parametro.
	 *
	 * @param parametro el parametroDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<Parametro> listarParametro(ParametroDTO parametro) throws Exception;
	
	/**
	 * contar lista parametro.
	 *
	 * @param parametro el parametro
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarParametro(ParametroDTO parametro);
	/**
	 * Generar id parametro.
	 *
	 * @return the String
	 * @throws Exception the exception
	 */
	String generarIdParametro() throws Exception;
}