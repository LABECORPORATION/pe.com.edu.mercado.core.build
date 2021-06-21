package pe.com.edu.siaa.core.ejb.dao.seguridad.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;
import pe.com.edu.siaa.core.model.dto.seguridad.GrupoUsuarioDTO;
import pe.com.edu.siaa.core.model.jpa.seguridad.GrupoUsuario;

/**
 * La Class GrupoUsuarioDaoLocal.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 11:25:06 COT 2017
 * @since SIAA-CORE 2.1
 */
@Local
public interface GrupoUsuarioDaoLocal  extends GenericDAOLocal<Long,GrupoUsuario> {
	/**
	 * Listar grupo usuario.
	 *
	 * @param grupoUsuario el grupo usuarioDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<GrupoUsuario> listarGrupoUsuario(GrupoUsuarioDTO grupoUsuario) throws Exception;
	
	/**
	 * contar lista grupo usuario.
	 *
	 * @param grupoUsuario el grupo usuario
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarGrupoUsuario(GrupoUsuarioDTO grupoUsuario);
	/**
	 * Generar id grupoUsuario.
	 *
	 * @return the Long
	 * @throws Exception the exception
	 */
	Long generarIdGrupoUsuario() throws Exception;
}