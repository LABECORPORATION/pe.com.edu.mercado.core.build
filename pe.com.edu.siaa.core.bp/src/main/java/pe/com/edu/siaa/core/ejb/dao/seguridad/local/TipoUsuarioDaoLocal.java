package pe.com.edu.siaa.core.ejb.dao.seguridad.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;
import pe.com.edu.siaa.core.model.dto.seguridad.TipoUsuarioDTO;
import pe.com.edu.siaa.core.model.jpa.seguridad.TipoUsuario;

/**
 * La Class TipoUsuarioDaoLocal.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu Dec 14 00:27:45 COT 2017
 * @since SIAA-CORE 2.1
 */
@Local
public interface TipoUsuarioDaoLocal  extends GenericDAOLocal<Long,TipoUsuario> {
	/**
	 * Listar tipo usuario.
	 *
	 * @param tipoUsuario el tipo usuarioDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<TipoUsuario> listarTipoUsuario(TipoUsuarioDTO tipoUsuario) throws Exception;
	
	/**
	 * contar lista tipo usuario.
	 *
	 * @param tipoUsuario el tipo usuario
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarTipoUsuario(TipoUsuarioDTO tipoUsuario);
	/**
	 * Generar id tipoUsuario.
	 *
	 * @return the Long
	 * @throws Exception the exception
	 */
	Long generarIdTipoUsuario() throws Exception;
}