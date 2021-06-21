package pe.com.edu.siaa.core.ejb.dao.seguridad.local;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;
import pe.com.edu.siaa.core.model.dto.seguridad.PropertiesDTO;
import pe.com.edu.siaa.core.model.dto.seguridad.PropertiesLenguajeDTO;
import pe.com.edu.siaa.core.model.jpa.seguridad.PropertiesLenguaje;

/**
 * La Class PropertiesLenguajeDaoLocal.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 11:25:07 COT 2017
 * @since SIAA-CORE 2.1
 */
@Local
public interface PropertiesLenguajeDaoLocal  extends GenericDAOLocal<String,PropertiesLenguaje> {
	
	int actualizarPropertiesLenguaje(PropertiesDTO properties);
	/**
	 * Obtener properties lenguaje map.
	 *
	 * @param listaIdProperties el lista id properties
	 * @return the map
	 * @throws Exception the exception
	 */
	Map<String,Map<String,String>> obtenerPropertiesLenguajeMap(List<Long> listaIdProperties) throws Exception;
	
	/**
	 * Obtener properties lenguaje map.
	 *
	 * @return the map
	 */
	Map<String,Map<String,String>> obtenerPropertiesLenguajeAllMap();
	
	/**
	 * Listar properties lenguaje.
	 *
	 * @param propertiesLenguaje el properties lenguajeDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<PropertiesLenguaje> listarPropertiesLenguaje(PropertiesLenguajeDTO propertiesLenguaje) throws Exception;
	
	/**
	 * contar lista properties lenguaje.
	 *
	 * @param propertiesLenguaje el properties lenguaje
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarPropertiesLenguaje(PropertiesLenguajeDTO propertiesLenguaje);
	/**
	 * Generar id propertiesLenguaje.
	 *
	 * @return the Long
	 * @throws Exception the exception
	 */
	String generarIdPropertiesLenguaje() throws Exception;
}