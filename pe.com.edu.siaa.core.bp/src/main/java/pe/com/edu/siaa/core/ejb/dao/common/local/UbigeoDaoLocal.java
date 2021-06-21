package pe.com.edu.siaa.core.ejb.dao.common.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;
import pe.com.edu.siaa.core.model.dto.common.UbigeoDTO;
import pe.com.edu.siaa.core.model.jpa.common.Ubigeo;

/**
 * La Class UbigeoDaoLocal.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 11:25:12 COT 2017
 * @since SIAA-CORE 2.1
 */
@Local
public interface UbigeoDaoLocal  extends GenericDAOLocal<String,Ubigeo> {
	/**
	 * Listar ubigeo.
	 *
	 * @param ubigeo el ubigeoDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<Ubigeo> listarUbigeo(UbigeoDTO ubigeo) throws Exception;
	
	/**
	 * contar lista ubigeo.
	 *
	 * @param ubigeo el ubigeo
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarUbigeo(UbigeoDTO ubigeo);
	/**
	 * Generar id ubigeo.
	 *
	 * @return the String
	 * @throws Exception the exception
	 */
	String generarIdUbigeo() throws Exception;
}