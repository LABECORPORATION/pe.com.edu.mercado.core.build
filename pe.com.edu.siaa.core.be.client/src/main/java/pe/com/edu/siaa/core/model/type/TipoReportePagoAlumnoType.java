package pe.com.edu.siaa.core.model.type;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <ul>
 * <li>Copyright 2012 BUILD SOFT S.A.C - BS. Todos los derechos reservados.</li>
 * </ul>
 * 
 * La Enum TipoReporteMatriculaType.
 *
 * @author ndavilal.
 * @version 1.0 , 09/09/2012
 * @since SIAA 2.0
 */
public enum TipoReportePagoAlumnoType {

    /** La FICHA_MATRICULA_MASIVA. */
 //	FICHA_MATRICULA_MASIVA("1","Ficha matricula masiva"),
 	
	 /** El REPORTE_ESTADISTICO_MATRICULA_RANGO_SEMESTRE. */
	 REPORTE_PAGO_MATRICULADOS_POR_CURSOS("1","Reporte Pago Matriculados por Curso"),
	 
 	/** EL REPORTE_ESTADISTICO_COMPARATIVO_MATRICULA_RANGO_SEMESTRE. */
	 //TODO:FALTA
	 //REPORTE_ESTADISTICO_COMPARATIVO_MATRICULA_RANGO_SEMESTRE("3","Reporte estadistico comparativo matricula rango semestre"),
	 
	 /** El REPORTE_RESUMEN_MATRICULA_POR_CICLO_Y_SEMESTRE. */
 	REPORTE_PAGO_MATRICULADOS_POR_CICLO("2","Reporte Pago Matriculados por Ciclo");
 	
 	/** El REPORTE_PLAESTUDIO. */
 	/*REPORTE_PLAESTUDIO("3","Reporte de Plan de Estudio");*/
 	
	/** La Constante LOO_KUP_MAP. */
	private static final Map<String, TipoReportePagoAlumnoType> LOO_KUP_MAP = new HashMap<String, TipoReportePagoAlumnoType>();	

	
	static {
		for (TipoReportePagoAlumnoType s : EnumSet.allOf(TipoReportePagoAlumnoType.class)) {
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
	private TipoReportePagoAlumnoType(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	/**
	 * Gets the.
	 *
	 * @param key el key
	 * @return the accion type
	 */
	public static TipoReportePagoAlumnoType get(String key) {
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
