package pe.com.builderp.core.facturacion.ejb.dao.venta.local;

import java.util.List;

import javax.ejb.Local;

import pe.com.builderp.core.facturacion.model.dto.venta.ConfiguracionAtributoDTO;
import pe.com.builderp.core.facturacion.model.jpa.venta.ConfiguracionAtributo;
import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;

/**
 * La Class ConfiguracionAtributoDaoLocal.
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
public interface ConfiguracionAtributoDaoLocal  extends GenericDAOLocal<String,ConfiguracionAtributo> {
	/**
	 * Listar configuracion atributo.
	 *
	 * @param configuracionAtributo el configuracion atributoDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<ConfiguracionAtributo> listarConfiguracionAtributo(ConfiguracionAtributoDTO configuracionAtributo) throws Exception;
	
	/**
	 * contar lista configuracion atributo.
	 *
	 * @param configuracionAtributo el configuracion atributo
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarConfiguracionAtributo(ConfiguracionAtributoDTO configuracionAtributo);
	/**
	 * Generar id configuracionAtributo.
	 *
	 * @return the String
	 * @throws Exception the exception
	 */
	String generarIdConfiguracionAtributo() throws Exception;
}