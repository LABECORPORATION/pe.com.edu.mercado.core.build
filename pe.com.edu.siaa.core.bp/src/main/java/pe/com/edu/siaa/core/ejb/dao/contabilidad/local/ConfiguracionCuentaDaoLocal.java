package pe.com.edu.siaa.core.ejb.dao.contabilidad.local;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import pe.com.edu.siaa.core.ejb.dao.generic.local.GenericDAOLocal;
import pe.com.edu.siaa.core.model.dto.contabilidad.ConfiguracionCuentaDTO;
import pe.com.edu.siaa.core.model.estate.EstadoGeneralState;
import pe.com.edu.siaa.core.model.jpa.contabilidad.ConfiguracionCuenta;

/**
 * La Class ConfiguracionCuentaDaoLocal.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Mon Oct 16 10:52:28 COT 2017
 * @since SIAA-CORE 2.1
 */
@Local
public interface ConfiguracionCuentaDaoLocal  extends GenericDAOLocal<String,ConfiguracionCuenta> {
	
	Map<Long,ConfiguracionCuenta> listarConfiguracionCuentaMap(List<Long> listaIdItem,EstadoGeneralState estadoGeneralState) throws Exception;
	
	/**
	 * Listar configuracion cuenta.
	 *
	 * @param configuracionCuenta el configuracion cuentaDTO
	 * @return the list
	 * @throws Exception the exception
	 */
	List<ConfiguracionCuenta> listarConfiguracionCuenta(ConfiguracionCuentaDTO configuracionCuenta) throws Exception;
	
	/**
	 * contar lista configuracion cuenta.
	 *
	 * @param configuracionCuenta el configuracion cuenta
	 * @return the list
	 * @throws Exception the exception
	 */
	int contarListarConfiguracionCuenta(ConfiguracionCuentaDTO configuracionCuenta);
	/**
	 * Generar id configuracionCuenta.
	 *
	 * @return the String
	 * @throws Exception the exception
	 */
	String generarIdConfiguracionCuenta() throws Exception;
}