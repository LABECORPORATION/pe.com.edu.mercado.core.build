package pe.com.edu.siaa.core.model.type;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
* La Enum CarpetaType.
* <ul>
* <li>Copyright 2017 ndavilal -
* MAPFRE. Todos los derechos reservados.</li>
* </ul>
*
* @author ndavilal
* @version 1.0, Thu Jul 31 10:21:30 COT 2017
* @since SIAA-CORE 2.1
*/
public enum CarpetaType {

	 /** El MENU. */
	 MENU("menu","alumno"),
	 
 	/** El TEMP. */
	 TEMP("temp","temp"),
	 
	 /** El IMG. */
	 IMG("img","img"),
	 
	 /** El REPORTE_GENERADO. */
	 REPORTE_GENERADO("reporte_generado","reporte_generado");
 	
	/** La Constante LOO_KUP_MAP. */
	private static final Map<String, CarpetaType> LOO_KUP_MAP = new HashMap<String, CarpetaType>();
	
	static {
		for (CarpetaType s : EnumSet.allOf(CarpetaType.class)) {
			LOO_KUP_MAP.put(s.getKey(), s);
		}
	}

	/** El key. */
	private String key;
	
	/** El value. */
	private String value;

	/**
	 * Instancia un nuevo accion type.
	 *
	 * @param key el key
	 * @param value el value
	 */
	private CarpetaType(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	/**
	 * Gets the.
	 *
	 * @param key el key
	 * @return the accion type
	 */
	public static CarpetaType get(String key) {
		return LOO_KUP_MAP.get(key);
	}

	/**
	 * Obtiene key.
	 *
	 * @return key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Obtiene value.
	 *
	 * @return value
	 */
	public String getValue() {
		return value;
	}
	
}