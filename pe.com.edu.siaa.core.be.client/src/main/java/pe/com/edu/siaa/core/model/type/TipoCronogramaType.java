package pe.com.edu.siaa.core.model.type;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;


/**
 * <ul>
 * <li>Copyright 2012 BUILD SOFT S.A.C - BS. Todos los derechos reservados.</li>
 * </ul>
 * 
 * La Enum TipoCronogramaType.
 *
 * @author ndavilal.
 * @version 1.0 , 24/09/2012
 * @since SIAA 2.0
 */
public enum TipoCronogramaType {

	//Los datos se encuentrar registrados en item//TODO:ver esto
	
	/** El NO_APLICA. */
	NO_APLICA(1L,"No aplica"),
	
    /** El CICLO_AVANZADO. */
 	CICLO_AVANZADO(2L,"Ciclo Avanzado"),
 	
 	/** El CICLO_INICO. */
 	CICLO_INICO(3L,"Ciclo Inicio");
 	
	/** La Constante looKup. */
	private static final Map<Long, TipoCronogramaType> LOO_KUP_MAP = new HashMap<Long, TipoCronogramaType>();
	
	
	static {
		for (TipoCronogramaType s : EnumSet.allOf(TipoCronogramaType.class)) {
			LOO_KUP_MAP.put(s.getKey(), s);
		}
	}

	/** El key. */
	private Long key;
	
	/** El value. */
	private String value;

	/**
	 * Instancia un nuevo tipo asinatura type.
	 *
	 * @param key el key
	 * @param value el value
	 */
	private TipoCronogramaType(Long key, String value) {
		this.key = key;
		this.value = value;
	}
	
	/**
	 * Gets the.
	 *
	 * @param key el key
	 * @return the tipo asinatura type
	 */
	public static TipoCronogramaType get(Long key) {
		return LOO_KUP_MAP.get(key);
	}

	/**
	 * Obtiene key.
	 *
	 * @return key
	 */
	public Long getKey() {
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
