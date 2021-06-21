package pe.com.edu.siaa.core.ejb.dao.seguridad.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;
import pe.com.edu.siaa.core.model.dto.seguridad.ConfiguracionMenuDTO;
import pe.com.edu.siaa.core.model.jpa.seguridad.ConfiguracionMenu;

/**
 * La Class ConfiguracionMenuDaoLocal.
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
public interface ConfiguracionMenuDaoLocal  extends GenericDAOLocal<String,ConfiguracionMenu> {
	
	/**
	 * Listar configuracion menu.
	 *
	 * @param menu el menu
	 * @return the list
	 * @throws Exception the exception
	 */
	List<ConfiguracionMenu> obtenerConfiguracionMenu(Long idMenu) throws Exception;
	
	/**
	 * Listar configuracion menu.
	 *
	 * @param configuracionMenu el configuracion menuDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<ConfiguracionMenu> listarConfiguracionMenu(ConfiguracionMenuDTO configuracionMenu) throws Exception;
	
	/**
	 * contar lista configuracion menu.
	 *
	 * @param configuracionMenu el configuracion menu
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarConfiguracionMenu(ConfiguracionMenuDTO configuracionMenu);
	/**
	 * Generar id configuracionMenu.
	 *
	 * @return the Long
	 * @throws Exception the exception
	 */
	String generarIdConfiguracionMenu() throws Exception;
}