package pe.com.edu.siaa.core.model.type;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * <ul>
 * <li>Copyright 2012 BUILD SOFT S.A.C - BS. Todos los derechos reservados.</li>
 * </ul>
 * 
 * La Enum DragHorarioType.
 *
 * @author ndavilal.
 * @version 1.0 , 18/06/2012
 * @since SIAA 2.0
 */
public enum DragHorarioType {

     /** El PENDIENTE. */
     PENDIENTE("pen","Pendiente"),
 	
	 /** El OCUPADO. */
 	OCUPADO("ocu","Ocupado"),
 	
 	SIN_MOVER("smov","Sin mover");
     
	/** La Constante LOO_KUP_MAP. */
	private static final Map<String, DragHorarioType> LOO_KUP_MAP = new HashMap<String, DragHorarioType>();
	
	static {
		for (DragHorarioType s : EnumSet.allOf(DragHorarioType.class)) {
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
	private DragHorarioType(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	/**
	 * Gets the.
	 *
	 * @param key el key
	 * @return the accion type
	 */
	public static DragHorarioType get(String key) {
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
