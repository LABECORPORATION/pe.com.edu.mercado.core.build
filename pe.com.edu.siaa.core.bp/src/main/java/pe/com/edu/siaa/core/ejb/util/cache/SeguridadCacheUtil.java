/*
 * 
 */
package pe.com.edu.siaa.core.ejb.util.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pe.com.edu.siaa.core.ejb.service.helper.Referencia;
import pe.com.edu.siaa.core.ejb.service.seguridad.local.SeguridadServiceLocal;
import pe.com.edu.siaa.core.ejb.util.log.Logger;
import pe.com.edu.siaa.core.model.dto.seguridad.PropertiesDTO;



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
public class SeguridadCacheUtil {

	private Logger log = Logger.getLogger(SeguridadCacheUtil.class);
	/** La configurador cache utl. */
	private static SeguridadCacheUtil seguridadCacheUtil = null;
		
  	/** El flag cargo listado. */
	private boolean flagCargoListado = false;
	
	private Map<String,String> propertiesMap = new HashMap<String,String>();
	
	/**
	 * Instancia un nuevo administracion cache utl.
	 */
	public SeguridadCacheUtil() {
		
	}
	
	/**
	 * Instanciar.
	 *
	 * @return the configurador cache utl
	 */
	public static SeguridadCacheUtil getInstance() {
		if (seguridadCacheUtil == null) {
			createInstance();
		} else if (!seguridadCacheUtil.isFlagCargoListado()) {
			seguridadCacheUtil.sincronizarData();
		}
		return seguridadCacheUtil;
	}
	 /**
     * Creates the instance.
     */
    private static synchronized void createInstance() {
    	if (seguridadCacheUtil == null) {
			seguridadCacheUtil = new SeguridadCacheUtil();
			seguridadCacheUtil.sincronizarData();
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
			//aquui cargar
			this.propertiesMap = new HashMap<String,String>();
			SeguridadServiceLocal seguridadServiceLocal = Referencia.getReference(SeguridadServiceLocal.class);
			List<PropertiesDTO> listaTemp = seguridadServiceLocal.listarProperties(new PropertiesDTO());
    		for (PropertiesDTO propertiesDTO : listaTemp) {
    			propertiesMap.put(propertiesDTO.getName(), propertiesDTO.getValue());
			}
			flagCargoListado = true;
		} catch (Exception e) {
			log.error(e);
			flagCargoListado = false;
			return e.toString();
		}
		return null;
	}
	

	public Map<String, String> getPropertiesLabelMap() {
		return propertiesMap;
	}
	
	public static String getProperties(String key) {
		if (SeguridadCacheUtil.getInstance().getPropertiesLabelMap().containsKey(key)) {
			return SeguridadCacheUtil.getInstance().getPropertiesLabelMap().get(key);
		}
		return "!" + key + "!";
	}
	
	//get y set
	
	public Map<String, String> getPropertiesMap() {
		return propertiesMap;
	}

	public void setPropertiesMap(Map<String, String> propertiesMap) {
		this.propertiesMap = propertiesMap;
	}

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


}
