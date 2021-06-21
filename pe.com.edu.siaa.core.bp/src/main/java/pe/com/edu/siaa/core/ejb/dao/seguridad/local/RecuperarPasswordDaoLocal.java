package pe.com.edu.siaa.core.ejb.dao.seguridad.local;


import java.util.List;

import javax.ejb.Local;

import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;
import pe.com.edu.siaa.core.model.dto.seguridad.RecuperarPasswordDTO;
import pe.com.edu.siaa.core.model.jpa.seguridad.RecuperarPassword;

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
public interface RecuperarPasswordDaoLocal  extends GenericDAOLocal<String,RecuperarPassword> {
	
	
	String generarIdRecuperarPassword() throws Exception;
	/**
	 * Listar det plan estudio.
	 *
	 * @param detPlanEstudio el det plan estudioDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	RecuperarPassword listarRecuperarPassword(String idrecuperarPassword) throws Exception;
	
	void deleteRecuperar(String idUsuario )throws Exception;
	
	RecuperarPassword findUsuario(String idUsuario) throws Exception;
}