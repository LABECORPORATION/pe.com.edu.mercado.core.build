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
public enum TipoPersonaType {

	//Los datos se encuentrar registrados en item
    
	/** El ALUMNO. */
	ALUMNO(1L, "Alumno"),
	
	/** El DOCENTE. */
	DOCENTE(2L, "Docente"),
	
	/** El WEBMASTER. */
	WEBMASTER(3L, "Webmaster"),
	
	/** EL SECRETARIA. */
	SECRETARIA(4L, "Secretaria"),
	
	/** El JEFE_DPTP_ASUNTOS_ACADEMICOS. */
	JEFE_DPTP_ASUNTOS_ACADEMICOS(5L, "Jefe del Dpto. de Asuntos Acad�micos"),
	
	/** La LIMPIEZA. */
	LIMPIEZA(6L, "Limpieza"),
	
	/** El VICE_PRESIDENTE_ACADEMICO. */
	VICE_PRESIDENTE_ACADEMICO(7L, "Vicepresidente Acad�mico"),
	
	/** El ENCARGADO_ACADEMICO. */
	ENCARGADO_ACADEMICO(8L, "Encargado Academico"),
	
	/** El CONSOJERO. */
	CONSOJERO(9L, "Consejero"),
	
	/** El APOYO_ACADEMICO. */
	APOYO_ACADEMICO(10L, "Apoyo Acedemico"),
	
	/** El FAMILIAR. */
	FAMILIAR(11L,"Familiar");
	
 	
	/** La Constante looKup. */
	private static final Map<Long, TipoPersonaType> LOO_KUP_MAP = new HashMap<Long, TipoPersonaType>();
	
	
	static {
		for (TipoPersonaType s : EnumSet.allOf(TipoPersonaType.class)) {
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
	private TipoPersonaType(Long key, String value) {
		this.key = key;
		this.value = value;
	}
	
	/**
	 * Gets the.
	 *
	 * @param key el key
	 * @return the tipo asinatura type
	 */
	public static TipoPersonaType get(Long key) {
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
