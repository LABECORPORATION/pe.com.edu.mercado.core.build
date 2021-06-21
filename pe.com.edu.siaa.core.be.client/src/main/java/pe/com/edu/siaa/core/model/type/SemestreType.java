package pe.com.edu.siaa.core.model.type;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * <ul>
 * <li>Copyright 2012 BUILD SOFT S.A.C - BS. Todos los derechos reservados.</li>
 * </ul>
 * 
 * La Enum SemestreType.
 *
 * @author ndavilal.
 * @version 1.0 , 16/05/2012
 * @since SIAA 2.0
 */
public enum SemestreType {

	/** La SEMESTR e_ verano. */
	SEMESTRE_VERANO("0","0"),
	
    /** La SEMESTR e_ i. */
    SEMESTRE_I("1","I"),
 	
	/** La SEMESTR e_ ii. */
	SEMESTRE_II("2","II");
 	
	/** La Constante LOO_KUP_MAP. */
	private static final Map<String, SemestreType> LOO_KUP_MAP = new HashMap<String, SemestreType>();
		
	static {
		for (SemestreType s : EnumSet.allOf(SemestreType.class)) {
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
	private SemestreType(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	
	/**
	 * Gets the.
	 *
	 * @param key el key
	 * @return the genero type
	 */
	public static SemestreType get(String key) {
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
