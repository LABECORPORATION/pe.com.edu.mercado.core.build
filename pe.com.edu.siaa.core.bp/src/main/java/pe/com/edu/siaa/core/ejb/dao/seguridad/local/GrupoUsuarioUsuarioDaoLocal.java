package pe.com.edu.siaa.core.ejb.dao.seguridad.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;
import pe.com.edu.siaa.core.model.dto.seguridad.GrupoUsuarioUsuarioDTO;
import pe.com.edu.siaa.core.model.jpa.seguridad.GrupoUsuarioUsuario;

/**
 * La Class GrupoUsuarioUsuarioDaoLocal.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 11:25:04 COT 2017
 * @since SIAA-CORE 2.1
 */
@Local
public interface GrupoUsuarioUsuarioDaoLocal  extends GenericDAOLocal<String,GrupoUsuarioUsuario> {
	/**
	 * Listar grupo usuario usuario.
	 *
	 * @param grupoUsuarioUsuario el grupo usuario usuarioDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<GrupoUsuarioUsuario> listarGrupoUsuarioUsuario(GrupoUsuarioUsuarioDTO grupoUsuarioUsuario) throws Exception;
	
	/**
	 * contar lista grupo usuario usuario.
	 *
	 * @param grupoUsuarioUsuario el grupo usuario usuario
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarGrupoUsuarioUsuario(GrupoUsuarioUsuarioDTO grupoUsuarioUsuario);
	/**
	 * Generar id grupoUsuarioUsuario.
	 *
	 * @return the Long
	 * @throws Exception the exception
	 */
	String generarIdGrupoUsuarioUsuario() throws Exception;
}