package pe.com.edu.siaa.core.model.type;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * <ul>
 * <li>Copyright 2012 BUILD SOFT S.A.C - BS. Todos los derechos reservados.</li>
 * </ul>
 * 
 * La Enum TipoAsinaturaType.
 *
 * @author ndavilal.
 * @version 1.0 , 18/03/2012
 * @since SIAA 2.0
 */
public enum TipoAsinaturaType {

    /** El OBLIGATORIO. */
 	OBLIGATORIO("O","Obligatorio"),
 	
	 /** El ELECTIVO. */
	 ELECTIVO("E","Electivo");
 	
	/** La Constante looKup. */
	private static final Map<String, TipoAsinaturaType> LOO_KUP_MAP = new HashMap<String, TipoAsinaturaType>();
	
	
	static {
		for (TipoAsinaturaType s : EnumSet.allOf(TipoAsinaturaType.class)) {
			LOO_KUP_MAP.put(s.getKey(), s);
		}
	}

	/** El key. */
	private String key;
	
	/** El value. */
	private String value;

	/**
	 * Instancia un nuevo tipo asinatura type.
	 *
	 * @param key el key
	 * @param value el value
	 */
	private TipoAsinaturaType(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	/**
	 * Gets the.
	 *
	 * @param key el key
	 * @return the tipo asinatura type
	 */
	public static TipoAsinaturaType get(String key) {
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
	 * Obtiene nombre.
	 *
	 * @return nombre
	 */
	public String getValue() {
		return value;
	}
	
}
