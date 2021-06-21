package pe.com.edu.siaa.core.ejb.dao.contabilidad.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;
import pe.com.edu.siaa.core.model.dto.contabilidad.ConfigDependenciaItemDTO;
import pe.com.edu.siaa.core.model.jpa.contabilidad.ConfigDependenciaItem;

/**
 * La Class ConfigDependenciaItemDaoLocal.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Mon Oct 16 10:52:27 COT 2017
 * @since SIAA-CORE 2.1
 */
@Local
public interface ConfigDependenciaItemDaoLocal  extends GenericDAOLocal<String,ConfigDependenciaItem> {
	/**
	 * Listar config dependencia item.
	 *
	 * @param configDependenciaItem el config dependencia itemDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<ConfigDependenciaItem> listarConfigDependenciaItem(ConfigDependenciaItemDTO configDependenciaItem) throws Exception;
	
	/**
	 * contar lista config dependencia item.
	 *
	 * @param configDependenciaItem el config dependencia item
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarConfigDependenciaItem(ConfigDependenciaItemDTO configDependenciaItem);
	/**
	 * Generar id configDependenciaItem.
	 *
	 * @return the String
	 * @throws Exception the exception
	 */
	String generarIdConfigDependenciaItem() throws Exception;
}