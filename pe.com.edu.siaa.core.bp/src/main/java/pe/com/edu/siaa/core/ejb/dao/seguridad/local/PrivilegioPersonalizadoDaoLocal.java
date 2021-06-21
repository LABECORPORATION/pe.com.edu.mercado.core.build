package pe.com.edu.siaa.core.ejb.dao.seguridad.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;
import pe.com.edu.siaa.core.model.dto.seguridad.PrivilegioPersonalizadoDTO;
import pe.com.edu.siaa.core.model.jpa.seguridad.PrivilegioPersonalizado;

/**
 * La Class PrivilegioPersonalizadoDaoLocal.
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
public interface PrivilegioPersonalizadoDaoLocal  extends GenericDAOLocal<String,PrivilegioPersonalizado> {
	/**
	 * Listar privilegio personalizado.
	 *
	 * @param privilegioPersonalizado el privilegio personalizadoDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<PrivilegioPersonalizado> listarPrivilegioPersonalizado(PrivilegioPersonalizadoDTO privilegioPersonalizado) throws Exception;
	
	/**
	 * contar lista privilegio personalizado.
	 *
	 * @param privilegioPersonalizado el privilegio personalizado
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarPrivilegioPersonalizado(PrivilegioPersonalizadoDTO privilegioPersonalizado);
	/**
	 * Generar id privilegioPersonalizado.
	 *
	 * @return the Long
	 * @throws Exception the exception
	 */
	String generarIdPrivilegioPersonalizado() throws Exception;
}