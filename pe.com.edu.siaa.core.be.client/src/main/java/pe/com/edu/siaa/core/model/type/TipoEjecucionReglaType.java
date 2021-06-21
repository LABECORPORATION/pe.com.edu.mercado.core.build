package pe.com.edu.siaa.core.model.type;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * <ul>
 * <li>Copyright 2012 BUILD SOFT S.A.C - BS. Todos los derechos reservados.</li>
 * </ul>
 * 
 * La Enum TipoEjecucionReglaType.
 *
 * @author ndavilal.
 * @version 1.0 , 27/08/2013
 * @since SIAA 2.0
 */
public enum TipoEjecucionReglaType {

    /** El DRL. */
 	DRL("DRL","DRL"),
 	
	 /** El DSL. */
	 DSL("DSL","DSL"),
	 
	 /** El XLS. */
	 XLS("XLS","XLS");
 	
	/** La Constante LOO_KUP_MAP. */
	private static final Map<String, TipoEjecucionReglaType> LOO_KUP_MAP = new HashMap<String, TipoEjecucionReglaType>();
	
	
	static {
		for (TipoEjecucionReglaType s : EnumSet.allOf(TipoEjecucionReglaType.class)) {
			LOO_KUP_MAP.put(s.getKey(), s);
		}
	}

	/** El key. */
	private String key;
	
	/** El value. */
	private String value;

	/**
	 * Instancia un nuevo flag modalidad ensenanza type.
	 *
	 * @param key el key
	 * @param value el value
	 */
	private TipoEjecucionReglaType(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	/**
	 * Gets the.
	 *
	 * @param key el key
	 * @return the flag modalidad ensenanza type
	 */
	public static TipoEjecucionReglaType get(String key) {
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
