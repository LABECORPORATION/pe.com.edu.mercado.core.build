package pe.com.edu.siaa.core.ejb.dao.seguridad.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;
import pe.com.edu.siaa.core.model.dto.seguridad.PrivilegioDTO;
import pe.com.edu.siaa.core.model.jpa.seguridad.Privilegio;

/**
 * La Class PrivilegioDaoLocal.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 11:25:11 COT 2017
 * @since SIAA-CORE 2.1
 */
@Local
public interface PrivilegioDaoLocal  extends GenericDAOLocal<Long,Privilegio> {
	
	/**
	 * Obtener privilegio by usuario.
	 *
	 * @param usuario el usuario
	 * @return the list
	 * @throws Exception the exception
	 */
	List<Privilegio> obtenerPrivilegioByUsuario(String idUsuario) throws Exception;
	/**
	 * Listar privilegio.
	 *
	 * @param privilegio el privilegioDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<Privilegio> listarPrivilegio(PrivilegioDTO privilegio) throws Exception;
	
	/**
	 * contar lista privilegio.
	 *
	 * @param privilegio el privilegio
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarPrivilegio(PrivilegioDTO privilegio);
	/**
	 * Generar id privilegio.
	 *
	 * @return the Long
	 * @throws Exception the exception
	 */
	Long generarIdPrivilegio() throws Exception;
}