package pe.com.edu.siaa.core.ejb.dao.seguridad.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;
import pe.com.edu.siaa.core.model.dto.seguridad.UsuarioDTO;
import pe.com.edu.siaa.core.model.jpa.seguridad.Usuario;

/**
 * La Class UsuarioDaoLocal.
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
public interface UsuarioDaoLocal  extends GenericDAOLocal<String,Usuario> {
	
	Usuario obtenerUsuarioByCodigoExterno(String codigoExterno) throws Exception;
	/**
	 * Validar login.
	 *
	 * @param userName el user name
	 * @param userPassword el user password
	 * @return the persona
	 * @throws Exception the exception
	 */
	Usuario validarLogin(String userName,String userPassword) throws Exception;
	/**
	 * Listar usuario.
	 *
	 * @param usuario el usuarioDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<Usuario> listarUsuario(UsuarioDTO usuario) throws Exception;
	
	/**
	 * contar lista usuario.
	 *
	 * @param usuario el usuario
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarUsuario(UsuarioDTO usuario);
	/**
	 * Generar id usuario.
	 *
	 * @return the String
	 * @throws Exception the exception
	 */
	String generarIdUsuario() throws Exception;
	
	//agregado
	
	Usuario validarLoginPassword(String userName,String email) throws Exception;
	
	Usuario findUsuario(String userName) throws Exception;
	
	Usuario findByUsuarioID(String idUusario) throws Exception;
}