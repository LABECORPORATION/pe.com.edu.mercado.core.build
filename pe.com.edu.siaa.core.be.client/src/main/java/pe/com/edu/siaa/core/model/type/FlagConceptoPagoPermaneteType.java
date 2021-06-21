package pe.com.edu.siaa.core.model.type;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;


/**
 * <ul>
 * <li>Copyright 2012 BUILD SOFT S.A.C - BS. Todos los derechos reservados.</li>
 * </ul>
 * 
 * La Enum FlagConceptoPagoPermaneteType.
 *
 * @author ndavilal.
 * @version 1.0 , 12/07/2012
 * @since SIAA 2.0
 */
public enum FlagConceptoPagoPermaneteType {

    /** El SI. */
 	SI("S","Si"),
 	
	 /** El NO. */
	 NO("N","No");
 	
	/** La Constante LOO_KUP_MAP. */
	private static final Map<String, FlagConceptoPagoPermaneteType> LOO_KUP_MAP = new HashMap<String, FlagConceptoPagoPermaneteType>();
	
	
	static {
		for (FlagConceptoPagoPermaneteType s : EnumSet.allOf(FlagConceptoPagoPermaneteType.class)) {
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
	private FlagConceptoPagoPermaneteType(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	/**
	 * Gets the.
	 *
	 * @param key el key
	 * @return the flag modalidad ensenanza type
	 */
	public static FlagConceptoPagoPermaneteType get(String key) {
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
