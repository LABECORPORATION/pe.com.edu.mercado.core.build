package pe.com.edu.siaa.core.ejb.dao.seguridad.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;
import pe.com.edu.siaa.core.model.dto.seguridad.MenuDTO;
import pe.com.edu.siaa.core.model.jpa.seguridad.Menu;

/**
 * La Class MenuDaoLocal.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 11:25:13 COT 2017
 * @since SIAA-CORE 2.1
 */
@Local
public interface MenuDaoLocal  extends GenericDAOLocal<Long,Menu> {
	
	
	/**
	 * Obtener menu usuario.
	 *
	 * @param usuario el usuario
	 * @param idSistema el id sistema
	 * @return the list
	 * @throws Exception the exception
	 */
	List<Menu> obtenerMenuUsuario(String idUsuario) throws Exception;
	
	/**
	 * Listar menu.
	 *
	 * @param menu el menuDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<Menu> listarMenu(MenuDTO menu) throws Exception;
	
	/**
	 * contar lista menu.
	 *
	 * @param menu el menu
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarMenu(MenuDTO menu);
	/**
	 * Generar id menu.
	 *
	 * @return the Long
	 * @throws Exception the exception
	 */
	Long generarIdMenu() throws Exception;
	
	Menu findByMenu(Long idMenu) throws Exception;
}