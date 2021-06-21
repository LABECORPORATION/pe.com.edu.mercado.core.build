package pe.com.edu.siaa.core.model.type;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;


/**
 * <ul>
 * <li>Copyright 2012 BUILD SOFT S.A.C - BS. Todos los derechos reservados.</li>
 * </ul>
 * 
 * La Enum TabReporteNotaType.
 *
 * @author ndavilal.
 * @version 1.0 , 07/10/2012
 * @since SIAA 2.0
 */
public enum TabReporteNotaType {

    /** El ACTA_EVALUACION_FINAL. */
 	ACTA_EVALUACION_FINAL("tabActaEvaluacionFinal","Acta evaluacion final"),
 	
	/** El REPORTE_NOTA_ASOCIADO_ALUMNO. */
	REPORTE_NOTA_ASOCIADO_ALUMNO("tabReporteNotaAsociadoAlumno","Reporte nota asociado al alumno"),
	 
 	/** EL MULTIPLES_REPORTE_NOTA. */
 	MULTIPLES_REPORTE_NOTA("tabMultiplesReporteNota","Multiples reportes de nota");
 	
	/** La Constante LOO_KUP_MAP. */
	private static final Map<String, TabReporteNotaType> LOO_KUP_MAP = new HashMap<String, TabReporteNotaType>();
	
	static {
		for (TabReporteNotaType s : EnumSet.allOf(TabReporteNotaType.class)) {
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
	private TabReporteNotaType(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	/**
	 * Gets the.
	 *
	 * @param key el key
	 * @return the accion type
	 */
	public static TabReporteNotaType get(String key) {
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
