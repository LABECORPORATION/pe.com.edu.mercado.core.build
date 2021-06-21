package pe.com.edu.siaa.core.model.type;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * <ul>
 * <li>Copyright 2012 BUILD SOFT S.A.C - BS. Todos los derechos reservados.</li>
 * </ul>
 * 
 * La Enum TipoNotaType.
 *
 * @author ndavilal.
 * @version 1.0 , 04/07/2012
 * @since SIAA 2.0
 */
public enum TipoNotaType {

	//Los datos se encuentrar registrados en item
	/*
	 * 
UPDATE Item SET nombre = 'Primera Unidad' WHERE idItem=517
UPDATE Item SET nombre = 'Segunda Unidad' WHERE idItem=518
UPDATE Item SET nombre = 'Tercera Unidad' WHERE idItem=519
UPDATE Item SET nombre = 'Cuarta Unidad' WHERE idItem=520
	 * */
    /** Las TAREAS_ACADEMICAS. */
 	PRIMERA_UNIDAD(1L,"Primera Unidad"),
 	
 	/** La PRACTICAS_Y_OTROS. */
	 SEGUNDA_UNIDAD(2L,"Segunda Unidad"),
 	
	/** El EXAMEN_PARCIAl. */
 	TERCERA_UNIDAD(3L,"Tercera Unidad"),
 	
 	/** El EXAMEN_FINAL. */
	 CUARTA_UNIDAD(4L,"Cuarta Unidad"),
 	
 	/** El EXAMEN_SUSTITUTORIO. */
//	 EXAMEN_SUSTITUTORIO(5L,"Examen Sustitutorio"),//no se usa
	 
	 /** El EXAMEN_APLAZADO. */
	 EXAMEN_APLAZADO(5L,"Examen Aplazado");
 	
	/** La Constante looKup. */
	private static final Map<Long, TipoNotaType> LOO_KUP_MAP = new HashMap<Long, TipoNotaType>();
	
	static {
		for (TipoNotaType s : EnumSet.allOf(TipoNotaType.class)) {
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
	private TipoNotaType(Long key, String value) {
		this.key = key;
		this.value = value;
	}
	
	/**
	 * Gets the.
	 *
	 * @param key el key
	 * @return the tipo asinatura type
	 */
	public static TipoNotaType get(Long key) {
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
