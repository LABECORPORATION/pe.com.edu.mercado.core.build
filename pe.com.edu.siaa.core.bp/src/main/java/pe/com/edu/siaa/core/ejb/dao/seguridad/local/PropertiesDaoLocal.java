package pe.com.edu.siaa.core.ejb.dao.seguridad.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;
import pe.com.edu.siaa.core.model.dto.seguridad.PropertiesDTO;
import pe.com.edu.siaa.core.model.jpa.seguridad.Properties;

/**
 * La Class PropertiesDaoLocal.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 11:25:11 COT 2017
 * @since SIAA-CORE 2.1
 */
@Local
public interface PropertiesDaoLocal  extends GenericDAOLocal<Long,Properties> {
	/**
	 * Listar properties.
	 *
	 * @param properties el propertiesDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<Properties> listarProperties(PropertiesDTO properties) throws Exception;
	
	/**
	 * contar lista properties.
	 *
	 * @param properties el properties
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarProperties(PropertiesDTO properties);
	/**
	 * Generar id properties.
	 *
	 * @return the Long
	 * @throws Exception the exception
	 */
	Long generarIdProperties() throws Exception;
}