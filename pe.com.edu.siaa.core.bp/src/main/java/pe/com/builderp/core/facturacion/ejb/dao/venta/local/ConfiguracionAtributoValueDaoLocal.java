package pe.com.builderp.core.facturacion.ejb.dao.venta.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.builderp.core.facturacion.model.dto.venta.ConfiguracionAtributoValueDTO;
import pe.com.builderp.core.facturacion.model.jpa.venta.ConfiguracionAtributoValue;
import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;

/**
 * La Class ConfiguracionAtributoValueDaoLocal.
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
public interface ConfiguracionAtributoValueDaoLocal  extends GenericDAOLocal<String,ConfiguracionAtributoValue> {
	/**
	 * Listar configuracion atributo value.
	 *
	 * @param configuracionAtributoValue el configuracion atributo valueDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<ConfiguracionAtributoValue> listarConfiguracionAtributoValue(ConfiguracionAtributoValueDTO configuracionAtributoValue) throws Exception;
	
	/**
	 * contar lista configuracion atributo value.
	 *
	 * @param configuracionAtributoValue el configuracion atributo value
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarConfiguracionAtributoValue(ConfiguracionAtributoValueDTO configuracionAtributoValue);
	/**
	 * Generar id configuracionAtributoValue.
	 *
	 * @return the String
	 * @throws Exception the exception
	 */
	String generarIdConfiguracionAtributoValue() throws Exception;
}