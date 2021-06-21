package pe.com.edu.siaa.core.ejb.dao.seguridad.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;
import pe.com.edu.siaa.core.model.dto.seguridad.PrivilegioMenuDTO;
import pe.com.edu.siaa.core.model.jpa.seguridad.PrivilegioMenu;

/**
 * La Class PrivilegioMenuDaoLocal.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 11:25:07 COT 2017
 * @since SIAA-CORE 2.1
 */
@Local
public interface PrivilegioMenuDaoLocal  extends GenericDAOLocal<String,PrivilegioMenu> {
	/**
	 * Listar privilegio menu.
	 *
	 * @param privilegioMenu el privilegio menuDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<PrivilegioMenu> listarPrivilegioMenu(PrivilegioMenuDTO privilegioMenu) throws Exception;
	
	/**
	 * contar lista privilegio menu.
	 *
	 * @param privilegioMenu el privilegio menu
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarPrivilegioMenu(PrivilegioMenuDTO privilegioMenu);
	/**
	 * Generar id privilegioMenu.
	 *
	 * @return the Long
	 * @throws Exception the exception
	 */
	String generarIdPrivilegioMenu() throws Exception;
}