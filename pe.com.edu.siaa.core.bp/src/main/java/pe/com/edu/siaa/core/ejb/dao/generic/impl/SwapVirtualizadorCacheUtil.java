package pe.com.edu.siaa.core.ejb.dao.generic.impl;

import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.fill.JRAbstractLRUVirtualizer;
import pe.com.edu.siaa.core.ejb.util.jasper.ArchivoUtilidades;
import pe.com.edu.siaa.core.ejb.util.log.Logger;
import pe.com.edu.siaa.core.model.util.ConstanteConfigUtil;

/**
 * <ul>
 * <li>Copyright 2017 ndavilal. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class SwapVirtualizadorCacheUtil.
 *
 * @author ndavilal
 * @version 1.0 , 28/08/2016
 * @since SIAA-CORE 2.1
 */
public class SwapVirtualizadorCacheUtil {

	/** La log. */
	private static Logger log = Logger.getLogger(SwapVirtualizadorCacheUtil.class);
	
	/** La regla cache util. */
	private static SwapVirtualizadorCacheUtil swapVirtualizador;

	/** El jRAbstractLRUVirtualizerList list. */
	private static Map<String, Map<String,JRAbstractLRUVirtualizer>> jRAbstractLRUVirtualizerMap = new HashMap<String, Map<String,JRAbstractLRUVirtualizer>>();
	
	
	/**
	 * Instanciar.
	 *
	 * @return the configurador cache utl
	 */
	public static SwapVirtualizadorCacheUtil getInstance() {
		if (swapVirtualizador == null) {
			createInstance();
		} 
		return swapVirtualizador;
	}
	
	 /**
     * Creates the instance.
     */
    private static synchronized void createInstance() {
    	if (swapVirtualizador == null) {
    		swapVirtualizador = new SwapVirtualizadorCacheUtil();
		}
    }

    public static synchronized void put(String usuario,String uuid, JRAbstractLRUVirtualizer jRAbstractLRUVirtualizer) {
    	if (!jRAbstractLRUVirtualizerMap.containsKey(usuario)) {
    		Map<String, JRAbstractLRUVirtualizer> value = new HashMap<String, JRAbstractLRUVirtualizer>();
    		value.put(uuid,jRAbstractLRUVirtualizer);
    		jRAbstractLRUVirtualizerMap.put(usuario, value);
    	} else {
    		Map<String,JRAbstractLRUVirtualizer> value = jRAbstractLRUVirtualizerMap.get(usuario);
    		value.put(uuid,jRAbstractLRUVirtualizer);
    		jRAbstractLRUVirtualizerMap.put(usuario, value);
    	}
    }
    public static synchronized void cleanup(String usuario) {
    	try {
    		if (jRAbstractLRUVirtualizerMap.containsKey(usuario)) {
    			Map<String,JRAbstractLRUVirtualizer> value = jRAbstractLRUVirtualizerMap.get(usuario);
    			if (value != null) {
    				for (Map.Entry<String,JRAbstractLRUVirtualizer> jrAbstractLRUVirtualizerMap : value.entrySet()) {
    					eliminarTemp(usuario, jrAbstractLRUVirtualizerMap.getKey(), jrAbstractLRUVirtualizerMap.getValue());
					}
    			}
    		}
    		jRAbstractLRUVirtualizerMap.remove(usuario);
		} catch (Exception e) {
			log.error("Error.cleanUp --> " + e.getMessage());
		}
    }
    private static void eliminarTemp(String usuario,String uuid, JRAbstractLRUVirtualizer jRAbstractLRUVirtualizer) {
    	try {
    		jRAbstractLRUVirtualizer.cleanup();
			String rutaSession = ConstanteConfigUtil.RUTA_SESSION_TEMP + ConstanteConfigUtil.generarRuta(usuario) ;
			ArchivoUtilidades.limpiarArchivoAllDirectory(rutaSession + uuid);
		} catch (Exception e) {
			log.error("Error.cleanUp.tractLRUVirtualizer.cleanup() --> " + e.getMessage());
		}
    }
    public static synchronized void cleanup(String usuario,String uuid) {
    	if (jRAbstractLRUVirtualizerMap.containsKey(usuario)) {
			Map<String,JRAbstractLRUVirtualizer> valueMap = jRAbstractLRUVirtualizerMap.get(usuario);
			if (valueMap != null) {
			    if (valueMap.containsKey(uuid)) {
			    	eliminarTemp(usuario, uuid, valueMap.get(uuid));
			    }
			    valueMap.remove(uuid);
			}
    	}
    }
	public static Map<String, Map<String,JRAbstractLRUVirtualizer>> getjRAbstractLRUVirtualizerMap() {
		return jRAbstractLRUVirtualizerMap;
	}	
}