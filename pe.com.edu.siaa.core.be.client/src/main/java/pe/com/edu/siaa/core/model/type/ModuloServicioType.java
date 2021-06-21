package pe.com.edu.siaa.core.model.type;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
* La Enum ModuloServicioType.
* <ul>
* <li>Copyright 2017 ndavilal -
* MAPFRE. Todos los derechos reservados.</li>
* </ul>
*
* @author ndavilal
* @version 1.0, Thu Oct 31 10:21:30 COT 2017
* @since SIAA-CORE 2.1
*/
public enum ModuloServicioType {
	
	/** PTSWEB. */
	PTSWEB(1, "Plataforma Web");
	
	/** La llave. */
	private Integer key;
	
	/** El valor. */
	private String value;
		
	/** La Constante lookup. */
	private static final Map<Integer, ModuloServicioType> lookup = new HashMap<Integer, ModuloServicioType>();

	static {
		for (ModuloServicioType s : EnumSet
				.allOf(ModuloServicioType.class)) {
			lookup.put(s.getKey(), s);
		}
	}

	/**
	 * Instancia un nuevo modulo type.
	 *
	 * @param key el key
	 * @param value el value
	 */
	private ModuloServicioType(Integer key, String value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * Obtiene key.
	 *
	 * @return key
	 */
	public Integer getKey() {
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



	/**
	 * Gets the.
	 *
	 * @param key el key
	 * @return the modulo type
	 */
	public static ModuloServicioType get(Integer key) {
		return lookup.get(key);
	}
}