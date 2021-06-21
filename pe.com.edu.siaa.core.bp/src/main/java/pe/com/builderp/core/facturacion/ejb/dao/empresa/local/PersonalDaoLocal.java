package pe.com.builderp.core.facturacion.ejb.dao.empresa.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.builderp.core.facturacion.model.dto.empresa.PersonalDTO;
import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;
import pe.com.edu.siaa.core.model.jpa.empresa.Personal;

/**
 * La Class PersonalDaoLocal.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu Dec 28 12:02:21 COT 2017
 * @since SIAA-CORE 2.1
 */
@Local
public interface PersonalDaoLocal  extends GenericDAOLocal<String,Personal> {
	/**
	 * Listar Personal.
	 *
	 * @param Personal el PersonalDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<Personal> listarPersonal(PersonalDTO personal) throws Exception;
	
	/**
	 * contar lista Personal.
	 *
	 * @param Personal el Personal
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarPersonal(PersonalDTO personal);
	/**
	 * Generar id Personal.
	 *
	 * @return the Long
	 * @throws Exception the exception
	 */
	String generarIdPersonal() throws Exception;
	
	Personal findPersonalByNro(String nroDoc) throws Exception;
}