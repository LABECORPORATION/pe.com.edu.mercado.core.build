package pe.com.edu.siaa.core.model.type;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;


/**
 * <ul>
 * <li>Copyright 2012 BUILD SOFT S.A.C - BS. Todos los derechos reservados.</li>
 * </ul>
 * 
 * La Enum TipoReporteNotaAsociandoAlumnoType.
 *
 * @author ndavilal.
 * @version 1.0 , 07/10/2012
 * @since SIAA 2.0
 */
public enum TipoReporteNotaAsociandoAlumnoType {

    /** El REPORTE_NOTA_ORDENADO_BY_SEMESTRE. */
 	REPORTE_NOTA_ORDENADO_BY_SEMESTRE("1","Reporte de nota ordenado por semestre"),	 
 	
	 /** El REPORTE_NOTA_ORDENADO_BY_CICLO. */
 	REPORTE_NOTA_ORDENADO_BY_CICLO("2","Reporte de nota ordenado por ciclo"),
 	
 	/** El RECORD_NOTA_BY_SEMESTRE. */
	RECORD_NOTA_BY_SEMESTRE("3","R�cord de nota"),
 	
 	/** El REPORTE_CERTIFICADO_ESTUDIO_BY_RANGO_CICLO. */
	 REPORTE_CERTIFICADO_ESTUDIO_BY_RANGO_CICLO("4","Reporte de certificado de estudio");
 	
	/** La Constante LOO_KUP_MAP. */
	private static final Map<String, TipoReporteNotaAsociandoAlumnoType> LOO_KUP_MAP = new HashMap<String, TipoReporteNotaAsociandoAlumnoType>();
	
	
	static {
		for (TipoReporteNotaAsociandoAlumnoType s : EnumSet.allOf(TipoReporteNotaAsociandoAlumnoType.class)) {
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
	private TipoReporteNotaAsociandoAlumnoType(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	/**
	 * Gets the.
	 *
	 * @param key el key
	 * @return the accion type
	 */
	public static TipoReporteNotaAsociandoAlumnoType get(String key) {
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
