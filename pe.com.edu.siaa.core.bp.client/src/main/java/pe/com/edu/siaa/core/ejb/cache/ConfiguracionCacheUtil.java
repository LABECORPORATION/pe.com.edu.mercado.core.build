/*
 * 
 */
package pe.com.edu.siaa.core.ejb.cache;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import pe.com.edu.siaa.core.model.util.ConstanteConfigUtil;


public class ConfiguracionCacheUtil {

	/** La Constante JNDI_PROPERTIES. */
	private static final String MAIL_SERVER_PROPERTIES = "mailserver.properties";
	
	/** La Constante pwr_CONF_UTIL_PROPERTIES. */
	private static final String SIAA_CONF_UTIL_PROPERTIES = "siaaconfutil.properties";
	
	/** La configurador cache utl. */
	private static ConfiguracionCacheUtil configuracionCacheUtil = null;
	
	/** El objeto properties. */
	private static Properties propiedadesMailServer =  new Properties();
	
	/** El objeto properties. */
	private static Properties propiedadespwrConfUtil =  new Properties();
	
	private static Map<String,String> dataIdJuegoLogMap = new HashMap<String, String>();
	
  	/** El flag cargo listado. */
	private boolean flagCargoListado = false;
	
	/**
	 * Instancia un nuevo administracion cache utl.
	 */
	public ConfiguracionCacheUtil() {
		
	}
	
	/**
	 * Instanciar.
	 *
	 * @return the configurador cache utl
	 */
	public static ConfiguracionCacheUtil getInstance() {
		if (configuracionCacheUtil == null) {
			createInstance();
		} else if (!configuracionCacheUtil.isFlagCargoListado()) {
			configuracionCacheUtil.sincronizarData();
		}
		return configuracionCacheUtil;
	}
	 /**
     * Creates the instance.
     */
    private static synchronized void createInstance() {
    	if (configuracionCacheUtil == null) {
			configuracionCacheUtil = new ConfiguracionCacheUtil();
			configuracionCacheUtil.sincronizarData();
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
	private synchronized String sincronizarProperties() {
		try {
			
			InputStream inMailServer = obtenerArchivo(ConstanteConfigUtil.RUTA_GENERAL_CONFIG+  MAIL_SERVER_PROPERTIES);
			propiedadesMailServer = new Properties();
			propiedadesMailServer.load(inMailServer);
			inMailServer.close();
			
			InputStream inpwrConfUtil = obtenerArchivo(ConstanteConfigUtil.RUTA_GENERAL_CONFIG +  SIAA_CONF_UTIL_PROPERTIES);
			propiedadespwrConfUtil = new Properties();
			propiedadespwrConfUtil.load(inpwrConfUtil);
			inpwrConfUtil.close();
			
			generarConfLogTramaJuegoUtil();
			flagCargoListado = true;
		} catch (Exception e) {
			flagCargoListado = false;
			return e.toString();
		}
		return null;
		
	}
	
   
    /**
     * Obtener archivo.
     *
     * @param pathFile el path file
     * @return the input stream
     */
    public static InputStream obtenerArchivo(String pathFile) {
		InputStream is = null;
		try {
			File f = new File(pathFile);
			is = new FileInputStream(f);		
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return is;
	}
	

	/**
	 * Gets the mail server.
	 *
	 * @param key the key
	 * @return the mail server
	 */
	public static String getMailServer(String key)  {
		if (propiedadesMailServer.containsKey(key)) {
			return propiedadesMailServer.getProperty(key);
		} else {
			return "!" + key + "!";
		}
	}

	
	/**
	 * Gets the mail server.
	 *
	 * @param key the key
	 * @return the mail server
	 */
	public static String getPwrConfUtil(String key) {
		if (propiedadespwrConfUtil.containsKey(key)) {
			return propiedadespwrConfUtil.getProperty(key);
		} else {
			return "!" + key + "!";
		}
	}
	
	private static void generarConfLogTramaJuegoUtil() {
		String key = "imprimir.log.configurador.trama.registrar.data";
		dataIdJuegoLogMap = new HashMap<String, String>();
		if (propiedadespwrConfUtil.containsKey(key)) {
			String[] dataIdJuego =  propiedadespwrConfUtil.getProperty(key).split(",",-1);
			if (dataIdJuego != null) {
				for (String keyData : dataIdJuego) {
					dataIdJuegoLogMap.put(keyData, keyData);
				}
			}
		}
	}
	
	public static boolean isGenerarLogTramaJuego(Object key) {
		return dataIdJuegoLogMap.containsKey(key.toString());
	}
	/**
	 * Gets the pwr conf util int.
	 *
	 * @param key the key
	 * @return the pwr conf util int
	 */
	public static int getPwrConfUtilInt(String key) {
		if (propiedadespwrConfUtil.containsKey(key)) {
			try {
				return Integer.parseInt(propiedadespwrConfUtil.getProperty(key));
			} catch (Exception e) {
				return 0;
			}
			
		} else {
			return 0;
		}

	}
	public static boolean isElementoTrue(String key) {
		if (propiedadespwrConfUtil.containsKey(key)) {
			try {
				return propiedadespwrConfUtil.getProperty(key).equalsIgnoreCase("true");
			} catch (Exception e) {
				return false;
			}
			
		} else {
			return false;
		}

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

	/**
	 * Gets the data id juego log map.
	 *
	 * @return the data id juego log map
	 */
	public static Map<String, String> getDataIdJuegoLogMap() {
		return dataIdJuegoLogMap;
	}


	 
}
