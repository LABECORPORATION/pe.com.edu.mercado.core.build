package pe.com.edu.siaa.core.model.type;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
* La Enum TipoDetalleCorreoType.
* <ul>
* <li>Copyright 2017 ndavilal -
* MAPFRE. Todos los derechos reservados.</li>
* </ul>
*
* @author ndavilal
* @version 1.0, Wed Jul 30 17:20:50 COT 2017
* @since SIAA-CORE 2.1
*/
public enum AsuntoDetalleCorreoType {

	 /** El ASUNTO_REPORTE. */
	ASUNTO_PROCESO("R","correoProcesoEjecutado.asunto","correoReporteTerminado.ftl"),
	 
    /**  El ASUNTO_PROCESO. */
	 ASUNTO_REPORTE("P","correoReporteEjecutado.asunto","correoProcesoTerminado.ftl"),
	 
	/** La asunto proceso trama. */
	ASUNTO_PROCESO_TRAMA("PT","correoReporteEjecutado.asunto","correoProcesoTramaTerminado.ftl"),
	
	 /** El CURSO_MATRICULADO. */
	 CURSO_MATRICULADO("CM","cursoMatriculado.asunto","siaa_curso_matriculado.ftl");
 	
	/** La Constante LOO_KUP_MAP. */
	private static final Map<String, AsuntoDetalleCorreoType> LOO_KUP_MAP = new HashMap<String, AsuntoDetalleCorreoType>();
	
	static {
		for (AsuntoDetalleCorreoType s : EnumSet.allOf(AsuntoDetalleCorreoType.class)) {
			LOO_KUP_MAP.put(s.getKey(), s);
		}
	}

	/** El key. */
	private String key;
	
	/** El value. */
	private String value;
	
	/** La template. */
	private String template;


	/**
	 * Instancia un nuevo asunto detalle correo type.
	 *
	 * @param key el key
	 * @param value el value
	 * @param template el template
	 */
	private AsuntoDetalleCorreoType(String key, String value,String template) {
		this.key = key;
		this.value = value;
		this.template = template;
	}
	
	/**
	 * Gets the.
	 *
	 * @param key el key
	 * @return the accion type
	 */
	public static AsuntoDetalleCorreoType get(String key) {
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
	 * Obtiene template.
	 *
	 * @return template
	 */
	public String getTemplate() {
		return template;
	}
}
