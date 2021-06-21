package pe.com.edu.siaa.core.model.type;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;



// TODO: Auto-generated Javadoc
/**
 * <ul>
 * <li>Copyright 2012 BUILD SOFT S.A.C - BS. Todos los derechos reservados.</li>
 * </ul>
 * 
 * La Enum ReglaType.
 *
 * @author ndavilal.
 * @version 1.0 , 10/09/2013
 * @since SIAA 2.0
 */
public enum ReglaType {

   
 	/** El CALCULAR_LIMITE_CREDITO. */
	 CALCULAR_LIMITE_CREDITO("politicacalcularlimitecredito","politicacalcularlimitecredito", TipoEjecucionReglaType.DSL),
 	
 	/** El CURSOS_POSIBLES_MATRICULAR. */
	 CURSOS_POSIBLES_MATRICULAR("politicacursosposiblesmatricular","politicacursosposiblesmatricular", TipoEjecucionReglaType.DSL);
 	
 	/** La Constante LOO_KUP_MAP. */
	private static final Map<String, ReglaType> LOO_KUP_MAP = new HashMap<String, ReglaType>();
	
	
	static {
		for (ReglaType s : EnumSet.allOf(ReglaType.class)) {
			LOO_KUP_MAP.put(s.getKey(), s);
		}
	}

	/** El key. */
	private String key;
	
	/** El value. */
	private String value;
	
	/** La tipo ejecucion regla type. */
	private TipoEjecucionReglaType tipoEjecucionReglaType;

	/**
	 * Instancia un nuevo flag modalidad ensenanza type.
	 *
	 * @param key el key
	 * @param value el value
	 * @param tipoEjecucionReglaType el tipo ejecucion regla type
	 */
	private ReglaType(String key, String value,TipoEjecucionReglaType tipoEjecucionReglaType) {
		this.key = key;
		this.value = value;
		this.tipoEjecucionReglaType = tipoEjecucionReglaType;
	}
	
	/**
	 * Gets the.
	 *
	 * @param key el key
	 * @return the flag modalidad ensenanza type
	 */
	public static ReglaType get(String key) {
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
	
	/**
	 * Obtiene tipo ejecucion regla type.
	 *
	 * @return tipo ejecucion regla type
	 */
	public TipoEjecucionReglaType getTipoEjecucionReglaType() {
		return tipoEjecucionReglaType;
	}
	

}
