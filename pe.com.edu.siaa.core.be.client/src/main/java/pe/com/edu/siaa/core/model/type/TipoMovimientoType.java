package pe.com.edu.siaa.core.model.type;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
* La Enum TipoMovimientoType.
* <ul>
* <li>Copyright 2017 ndavilal -
* MAPFRE. Todos los derechos reservados.</li>
* </ul>
*
* @author ndavilal
* @version 1.0, Thu Jul 31 10:21:30 COT 2017
* @since SIAA-CORE 2.1
*/
public enum TipoMovimientoType {

	 /** El DEBE. */
	 DEBE("D","Debe"),
	 
 	/** El HABER. */
	 HABER("H","Haber");
 	
	/** La Constante LOO_KUP_MAP. */
	private static final Map<String, TipoMovimientoType> LOO_KUP_MAP = new HashMap<String, TipoMovimientoType>();
	
	static {
		for (TipoMovimientoType s : EnumSet.allOf(TipoMovimientoType.class)) {
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
	private TipoMovimientoType(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	/**
	 * Gets the.
	 *
	 * @param key el key
	 * @return the accion type
	 */
	public static TipoMovimientoType get(String key) {
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