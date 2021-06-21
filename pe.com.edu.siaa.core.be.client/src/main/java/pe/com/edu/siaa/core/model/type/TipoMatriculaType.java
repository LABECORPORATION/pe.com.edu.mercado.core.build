package pe.com.edu.siaa.core.model.type;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * <ul>
 * <li>Copyright 2012 BUILD SOFT S.A.C - BS. Todos los derechos reservados.</li>
 * </ul>
 * 
 * La Enum TipoMatriculaType.
 *
 * @author ndavilal.
 * @version 1.0 , 19/05/2013
 * @since SIAA 2.0
 */
public enum TipoMatriculaType {

    /** El NORMAL. */
 	NORMAL("N","Normal"),
 	
	 /** El EXTEMPORANEO. */
	 EXTEMPORANEO("E","Extemporaneo");
 	
	/** La Constante LOO_KUP_MAP. */
	private static final Map<String, TipoMatriculaType> LOO_KUP_MAP = new HashMap<String, TipoMatriculaType>();
	

	static {
		for (TipoMatriculaType s : EnumSet.allOf(TipoMatriculaType.class)) {
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
	private TipoMatriculaType(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	/**
	 * Gets the.
	 *
	 * @param key el key
	 * @return the accion type
	 */
	public static TipoMatriculaType get(String key) {
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
