/*
 * 
 */
package pe.com.edu.siaa.core.ejb.util.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pe.com.edu.siaa.core.ejb.service.common.local.CommonServiceLocal;
import pe.com.edu.siaa.core.ejb.service.helper.Referencia;
import pe.com.edu.siaa.core.ejb.util.log.Logger;
import pe.com.edu.siaa.core.model.dto.common.ParametroDTO;
import pe.com.edu.siaa.core.model.estate.EstadoGeneralState;



/**
 * La Class ParametroCacheUtil.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 13:43:23 COT 2017
 * @since SIAA-CORE 2.1
 */
public class ParametroCacheUtil {

	/** El service common impl. */
	private CommonServiceLocal commonServiceImpl;
	
	private Logger log = Logger.getLogger(ParametroCacheUtil.class);
	/** La configurador cache utl. */
	private static ParametroCacheUtil parametroCacheUtil = null;
		
  	/** El flag cargo listado. */
	private boolean flagCargoListado = false;
	
	private Map<String,Object> paramtroMap = new HashMap<String, Object>();
	
	/**
	 * Instancia un nuevo administracion cache utl.
	 */
	public ParametroCacheUtil() {
		
	}
	
	/**
	 * Instanciar.
	 *
	 * @return the configurador cache utl
	 */
	public static ParametroCacheUtil getInstance() {
		if (parametroCacheUtil == null) {
			createInstance();
		} else if (!parametroCacheUtil.isFlagCargoListado()) {
			parametroCacheUtil.sincronizarData();
		}
		return parametroCacheUtil;
	}
	 /**
     * Creates the instance.
     */
    private static synchronized void createInstance() {
    	if (parametroCacheUtil == null) {
			parametroCacheUtil = new ParametroCacheUtil();
			parametroCacheUtil.sincronizarData();
		}
    }

	/**
	 * Sincronizar data.
	 *
	 * @return the string
	 */
	public   String sincronizarData() {
		return sincronizarProperties();
	}

	
	/**
	 * Sincronizar properties.
	 *
	 * @return the string
	 */
	private String sincronizarProperties() {
		try {
			commonServiceImpl = Referencia.getReference(CommonServiceLocal.class);
			ParametroDTO parametroFiltro = new ParametroDTO();
			parametroFiltro.setEstado(EstadoGeneralState.ACTIVO.getKey());
			List<ParametroDTO> listaParametro = commonServiceImpl.listarParametro(parametroFiltro );
			for (ParametroDTO parametroDTO : listaParametro) {
				String key = parametroDTO.getEntidad().getIdEntidad() + parametroDTO.getCodigo();
				Object value = parametroDTO.getValor();
				paramtroMap.put(key, value);
			}
			flagCargoListado = true;
		} catch (Exception e) {
			log.error(e);
			flagCargoListado = false;
			return e.toString();
		}
		return null;
	}
	
	
	//get y set
	
	/**
	 * Comprueba si es flag cargo listado.
	 *
	 * @return true, si es flag cargo listado
	 */
	public boolean isFlagCargoListado() {
		return flagCargoListado;
	}

	/**
	 * Establece el flag cargo listado.
	 *
	 * @param flagCargoListado el new flag cargo listado
	 */
	public void setFlagCargoListado(boolean flagCargoListado) {
		this.flagCargoListado = flagCargoListado;
	}

	public Map<String, Object> getParamtroMap() {
		return paramtroMap;
	}

}
