package pe.com.edu.siaa.core.ejb.dao.seguridad.local;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;
import pe.com.edu.siaa.core.model.dto.seguridad.UsuarioEntidadDTO;
import pe.com.edu.siaa.core.model.jpa.seguridad.UsuarioEntidad;
import pe.com.edu.siaa.core.model.vo.EgresadoFiltroVO;

/**
 * La Class UsuarioEntidadDaoLocal.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 11:25:18 COT 2017
 * @since SIAA-CORE 2.1
 */
@Local
public interface UsuarioEntidadDaoLocal  extends GenericDAOLocal<String,UsuarioEntidad> {
	Map<String,Integer> obtenerCantidadEntidadAsignadaMap(List<String> listaIdUsuario) throws Exception;
	/**
	 * Listar usuario entidad.
	 *
	 * @param usuarioEntidad el usuario entidadDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<UsuarioEntidad> listarUsuarioEntidad(UsuarioEntidadDTO usuarioEntidad) throws Exception;
	
	/**
	 * contar lista usuario entidad.
	 *
	 * @param usuarioEntidad el usuario entidad
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarUsuarioEntidad(UsuarioEntidadDTO usuarioEntidad);
	/**
	 * Generar id usuarioEntidad.
	 *
	 * @return the Long
	 * @throws Exception the exception
	 */
	String generarIdUsuarioEntidad() throws Exception;
	
	//agregado
	
	
	UsuarioEntidad listarUsuarioEntidadDelect(EgresadoFiltroVO filtro) throws Exception;
		
}