/*
 * 
 */
package pe.com.edu.siaa.core.model.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class CadenaCacheUtil {

	
	/** La Constante pwr_CONF_UTIL_PROPERTIES. */
	private static final String CADENA_UTIL_PROPERTIES = "cadenautil.properties";
	
	/** La configurador cache utl. */
	private static CadenaCacheUtil cadenaCacheUtil = null;
	
	/** El objeto properties. */
	private static Properties propiedadesCaracterExtranio =  new Properties();
	
	/** La caracter extranio list. */
	private static List<String> caracterExtranioList = new ArrayList<String>();
	
  	/** El flag cargo listado. */
	private boolean flagCargoListado = false;
	
	/**
	 * Instancia un nuevo administracion cache utl.
	 */
	public CadenaCacheUtil() {
		
	}
	
	/**
	 * Instanciar.
	 *
	 * @return the configurador cache utl
	 */
	public static CadenaCacheUtil getInstance() {
		if (cadenaCacheUtil == null) {
			createInstance();
		} else if (!cadenaCacheUtil.isFlagCargoListado()) {
			cadenaCacheUtil.sincronizarData();
		}
		return cadenaCacheUtil;
	}
	 /**
     * Creates the instance.
     */
    private static synchronized void createInstance() {
    	if (cadenaCacheUtil == null) {
			cadenaCacheUtil = new CadenaCacheUtil();
			cadenaCacheUtil.sincronizarData();
		}
    }

	
	/**
	 * Sincronizar data.
	 *
	 * @return the string
	 */
	public  String sincronizarData() {
		return sincronizarProperties();
	}

	
	
	/**
	 * Sincronizar properties.
	 *
	 * @return the string
	 */
	private synchronized String sincronizarProperties() {
		try {
			InputStream inpwrConfUtil = obtenerArchivo(ConstanteConfigUtil.RUTA_GENERAL_CONFIG +  CADENA_UTIL_PROPERTIES);
			propiedadesCaracterExtranio = new Properties();
			propiedadesCaracterExtranio.load(inpwrConfUtil);
			inpwrConfUtil.close();
			
			cargarListaCaracterExtranio();
			flagCargoListado = true;
		} catch (Exception e) {
			flagCargoListado = false;
			return e.toString();
		}
		return null;
		
	}
	
	/**
	 * Cargar lista caracter extranio.
	 */
	private static void cargarListaCaracterExtranio() {
		caracterExtranioList = new ArrayList<String>();
		String key = "configuracion.caracter.extranio";
		String listaCadenaCaracterExtranio = propiedadesCaracterExtranio.getProperty(key);
		String [] caracterExtranioArray = listaCadenaCaracterExtranio.split(",");
		for (int i = 0; i < caracterExtranioArray.length; i++) {
			caracterExtranioList.add(caracterExtranioArray[i]);
		}
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
	 * Obtiene pwr conf util.
	 *
	 * @param key el key
	 * @return pwr conf util
	 */
	public static String getPropertiesCaracterExtranio(String key) {
		if (propiedadesCaracterExtranio.containsKey(key)) {
			return propiedadesCaracterExtranio.getProperty(key);
		} else {
			return "!" + key + "!";
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
	 * Obtiene caracter extranio list.
	 *
	 * @return caracter extranio list
	 */
	public static List<String> getCaracterExtranioList() {
		return caracterExtranioList;
	}
}
