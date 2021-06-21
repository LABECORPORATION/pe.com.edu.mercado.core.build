package pe.com.edu.siaa.core.ejb.dao.seguridad.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;
import pe.com.edu.siaa.core.model.dto.seguridad.GrupoUsuarioMenuDTO;
import pe.com.edu.siaa.core.model.jpa.seguridad.GrupoUsuarioMenu;

/**
 * La Class GrupoUsuarioMenuDaoLocal.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 11:25:21 COT 2017
 * @since SIAA-CORE 2.1
 */
@Local
public interface GrupoUsuarioMenuDaoLocal  extends GenericDAOLocal<String,GrupoUsuarioMenu> {
	/**
	 * Listar grupo usuario menu.
	 *
	 * @param grupoUsuarioMenu el grupo usuario menuDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<GrupoUsuarioMenu> listarGrupoUsuarioMenu(GrupoUsuarioMenuDTO grupoUsuarioMenu) throws Exception;
	
	/**
	 * contar lista grupo usuario menu.
	 *
	 * @param grupoUsuarioMenu el grupo usuario menu
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarGrupoUsuarioMenu(GrupoUsuarioMenuDTO grupoUsuarioMenu);
	/**
	 * Generar id grupoUsuarioMenu.
	 *
	 * @return the Long
	 * @throws Exception the exception
	 */
	String generarIdGrupoUsuarioMenu() throws Exception;
	
	GrupoUsuarioMenu findByGrupoUsuarioMenu(Long idMenu) throws Exception;
	
	List<GrupoUsuarioMenu> listarGrupoUsuarioMenu(Long idMenu);
}