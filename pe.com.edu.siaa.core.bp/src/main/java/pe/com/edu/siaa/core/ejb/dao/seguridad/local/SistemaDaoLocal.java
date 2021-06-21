package pe.com.edu.siaa.core.ejb.dao.seguridad.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;
import pe.com.edu.siaa.core.model.dto.seguridad.SistemaDTO;
import pe.com.edu.siaa.core.model.jpa.seguridad.Sistema;

/**
 * La Class SistemaDaoLocal.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 11:25:15 COT 2017
 * @since SIAA-CORE 2.1
 */
@Local
public interface SistemaDaoLocal  extends GenericDAOLocal<Long,Sistema> {
	/**
	 * Listar sistema.
	 *
	 * @param sistema el sistemaDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<Sistema> listarSistema(SistemaDTO sistema) throws Exception;
	
	/**
	 * contar lista sistema.
	 *
	 * @param sistema el sistema
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarSistema(SistemaDTO sistema);
	/**
	 * Generar id sistema.
	 *
	 * @return the Long
	 * @throws Exception the exception
	 */
	Long generarIdSistema() throws Exception;
}