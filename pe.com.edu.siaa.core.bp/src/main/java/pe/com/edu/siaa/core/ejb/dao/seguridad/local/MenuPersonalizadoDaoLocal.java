package pe.com.edu.siaa.core.ejb.dao.seguridad.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;
import pe.com.edu.siaa.core.model.dto.seguridad.MenuPersonalizadoDTO;
import pe.com.edu.siaa.core.model.jpa.seguridad.MenuPersonalizado;

/**
 * La Class MenuPersonalizadoDaoLocal.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 11:25:09 COT 2017
 * @since SIAA-CORE 2.1
 */
@Local
public interface MenuPersonalizadoDaoLocal  extends GenericDAOLocal<String,MenuPersonalizado> {
	/**
	 * Listar menu personalizado.
	 *
	 * @param menuPersonalizado el menu personalizadoDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<MenuPersonalizado> listarMenuPersonalizado(MenuPersonalizadoDTO menuPersonalizado) throws Exception;
	
	/**
	 * contar lista menu personalizado.
	 *
	 * @param menuPersonalizado el menu personalizado
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarMenuPersonalizado(MenuPersonalizadoDTO menuPersonalizado);
	/**
	 * Generar id menuPersonalizado.
	 *
	 * @return the Long
	 * @throws Exception the exception
	 */
	String generarIdMenuPersonalizado() throws Exception;
}