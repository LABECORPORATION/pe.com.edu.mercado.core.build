package pe.com.edu.siaa.core.model.estate;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

 /**
 * La Enum EstadoState.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * MAPFRE. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Thu Jul 31 10:21:30 COT 2017
 * @since SIAA-CORE 2.1
 */
public enum EstadoGeneralState {

    /** El ACTIVO. */
 	ACTIVO("A" , "estado.activo"),
	
    /** El INACTIVO. */
 	INACTIVO("I" , "estado.inactivo"),
 	
    /** El INACTIVO. */
 	PENDIENTE("P" , "estado.pendiente"),
	
    /** El INACTIVO. */
 	CANCELADO("C" , "estado.cancelado"),
	
    /** El INACTIVO. */
 	VENDIDO("V" , "estado.vendido"),
	
	/** El ACTIVO. */
 	ABIERTO("A" , "estado.activo"),
	
    /** El INACTIVO. */
 	CERRADO("C" , "estado.inactivo");
	
	/** La Constante LOO_KUP_MAP. */
	private static final Map<String, EstadoGeneralState> LOO_KUP_MAP = new HashMap<String, EstadoGeneralState>();
	
	static {
		for (EstadoGeneralState s : EnumSet.allOf(EstadoGeneralState.class)) {
			LOO_KUP_MAP.put(s.getKey(), s);
		}
	}

	/** El key. */
	private String key;
	
	/** El value. */
	private String value;

	/**
	 * Instancia un nuevo estado state.
	 *
	 * @param key el key
	 * @param value el value
	 */
	private EstadoGeneralState(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	/**
	 * Gets the.
	 *
	 * @param key el key
	 * @return the estado state
	 */
	public static EstadoGeneralState get(String key) {
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
