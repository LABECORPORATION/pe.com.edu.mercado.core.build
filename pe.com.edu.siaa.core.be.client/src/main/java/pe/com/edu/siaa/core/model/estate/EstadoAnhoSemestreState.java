package pe.com.edu.siaa.core.model.estate;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * <ul>
 * <li>Copyright 2012 BUILD SOFT S.A.C - BS. Todos los derechos reservados.</li>
 * </ul>
 * 
 * La Enum EstadoAnhoSemestreState.
 *
 * @author ndavilal.
 * @version 1.0 , 16/05/2012
 * @since SIAA 2.0
 */
public enum EstadoAnhoSemestreState {

    /** El ACTIVO. */
 	ACTIVO("A","Activo"),
 	
	 /** El INACTIVO. */
	INACTIVO("I","Inactivo"),
	 
	/** El PASADO. */
	PASADO("P","Pasado");
 	
	/** La Constante LOO_KUP_MAP. */
	private static final Map<String, EstadoAnhoSemestreState> LOO_KUP_MAP = new HashMap<String, EstadoAnhoSemestreState>();
    
 
    static {
		for (EstadoAnhoSemestreState s : EnumSet.allOf(EstadoAnhoSemestreState.class)) {
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
	private EstadoAnhoSemestreState(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	/**
	 * Gets the.
	 *
	 * @param key el key
	 * @return the estado general type
	 */
	public static EstadoAnhoSemestreState get(String key) {
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
