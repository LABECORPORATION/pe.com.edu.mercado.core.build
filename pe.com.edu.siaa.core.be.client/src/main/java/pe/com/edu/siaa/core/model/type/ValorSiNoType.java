package pe.com.edu.siaa.core.model.type;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * <ul>
 * <li>Copyright 2012 BUILD SOFT S.A.C - BS. Todos los derechos reservados.</li>
 * </ul>
 * 
 * La Enum SumaCreditoType.
 *
 * @author ndavilal.
 * @version 1.0 , 18/03/2012
 * @since SIAA 2.0
 */
public enum ValorSiNoType {

    /** El SI. */
 	SI("S","SI"),
 	
	 /** El NO. */
	 NO("N","NO");
 	
	/** La Constante LOO_KUP_MAP. */
	private static final Map<String, ValorSiNoType> LOO_KUP_MAP = new HashMap<String, ValorSiNoType>();
	
	static {
		for (ValorSiNoType s : EnumSet.allOf(ValorSiNoType.class)) {
			LOO_KUP_MAP.put(s.getKey(), s);
		}
	}

	/** El key. */
	private String key;
	
	/** EL value. */
	private String value;

	/**
	 * Instancia un nuevo estado general type.
	 *
	 * @param key el key
	 * @param value el value
	 */
	private ValorSiNoType(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	/**
	 * Gets the.
	 *
	 * @param key el key
	 * @return the estado general type
	 */
	public static ValorSiNoType get(String key) {
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
