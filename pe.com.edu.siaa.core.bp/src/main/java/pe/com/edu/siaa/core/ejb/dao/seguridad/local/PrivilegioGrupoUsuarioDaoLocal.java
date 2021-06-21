package pe.com.edu.siaa.core.ejb.dao.seguridad.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;
import pe.com.edu.siaa.core.model.dto.seguridad.PrivilegioGrupoUsuarioDTO;
import pe.com.edu.siaa.core.model.jpa.seguridad.PrivilegioGrupoUsuario;

/**
 * La Class PrivilegioGrupoUsuarioDaoLocal.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 11:25:17 COT 2017
 * @since SIAA-CORE 2.1
 */
@Local
public interface PrivilegioGrupoUsuarioDaoLocal  extends GenericDAOLocal<String,PrivilegioGrupoUsuario> {
	/**
	 * Listar privilegio grupo usuario.
	 *
	 * @param privilegioGrupoUsuario el privilegio grupo usuarioDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<PrivilegioGrupoUsuario> listarPrivilegioGrupoUsuario(PrivilegioGrupoUsuarioDTO privilegioGrupoUsuario) throws Exception;
	
	/**
	 * contar lista privilegio grupo usuario.
	 *
	 * @param privilegioGrupoUsuario el privilegio grupo usuario
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarPrivilegioGrupoUsuario(PrivilegioGrupoUsuarioDTO privilegioGrupoUsuario);
	/**
	 * Generar id privilegioGrupoUsuario.
	 *
	 * @return the Long
	 * @throws Exception the exception
	 */
	String generarIdPrivilegioGrupoUsuario() throws Exception;
}