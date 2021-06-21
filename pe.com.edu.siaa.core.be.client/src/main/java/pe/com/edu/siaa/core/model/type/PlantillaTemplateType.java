package pe.com.edu.siaa.core.model.type;


/**
 * <ul>
 * <li>Copyright 2012 BUILD SOFT S.A.C - BS. Todos los derechos reservados.</li>
 * </ul>
 * 
 * La Enum PlantillaTemplateType.
 *
 * @author ndavilal.
 * @version 1.0 , 27/07/2013
 * @since SIAA 2.0
 */
public enum PlantillaTemplateType {

	/** La RUTA_PLANTILLA. */
	RUTA_PLANTILLA("0","template//"),
	
    /** El RECUPERAR_CLAVE. */
 	RECUPERAR_CLAVE("1","siaa_recuperar_clave.ftl"),
 	
	 /** El CURSO_MATRICULADO. */
	 CURSO_MATRICULADO("2","siaa_curso_matriculado.ftl");
 	
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
	private PlantillaTemplateType(String key, String value) {
		this.key = key;
		this.value = value;
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
