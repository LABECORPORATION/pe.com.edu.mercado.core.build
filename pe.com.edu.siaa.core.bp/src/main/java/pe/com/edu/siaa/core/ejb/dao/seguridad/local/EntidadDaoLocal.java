package pe.com.edu.siaa.core.ejb.dao.seguridad.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;
import pe.com.edu.siaa.core.model.dto.seguridad.EntidadDTO;
import pe.com.edu.siaa.core.model.jpa.seguridad.Entidad;

/**
 * La Class EntidadDaoLocal.
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
public interface EntidadDaoLocal  extends GenericDAOLocal<String,Entidad> {
	/**
	 * Listar entidad.
	 *
	 * @param entidad el entidadDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<Entidad> listarEntidad(EntidadDTO entidad) throws Exception;
	
	/**
	 * contar lista entidad.
	 *
	 * @param entidad el entidad
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarEntidad(EntidadDTO entidad);
	/**
	 * Generar id entidad.
	 *
	 * @return the String
	 * @throws Exception the exception
	 */
	String generarIdEntidad() throws Exception;
}