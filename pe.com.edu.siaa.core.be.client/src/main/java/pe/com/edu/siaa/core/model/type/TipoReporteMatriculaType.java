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
public enum TipoReporteMatriculaType {

    /** La FICHA_MATRICULA_MASIVA. */
 //	FICHA_MATRICULA_MASIVA("1","Ficha matricula masiva"),
 	
	 /** El REPORTE_ESTADISTICO_MATRICULA_RANGO_SEMESTRE. */
	 REPORTE_ESTADISTICO_MATRICULA_RANGO_SEMESTRE("1","Reporte estadistico matricula rango semestre"),
	 
 	/** EL REPORTE_ESTADISTICO_COMPARATIVO_MATRICULA_RANGO_SEMESTRE. */
	 //TODO:FALTA
	 //REPORTE_ESTADISTICO_COMPARATIVO_MATRICULA_RANGO_SEMESTRE("3","Reporte estadistico comparativo matricula rango semestre"),
	 
	 /** El REPORTE_RESUMEN_MATRICULA_POR_CICLO_Y_SEMESTRE. */
 	REPORTE_RESUMEN_MATRICULA_POR_CICLO_Y_SEMESTRE("2","Reporte resumen matricula por ciclo y semestre"),
 	
 	/** El REPORTE_PLAESTUDIO. */
 	REPORTE_PLAESTUDIO("3","Reporte de Plan de Estudio"),
 	 /** El REPORTE_RESUMEN_ALUMNO_REGULAR. */
 	REPORTE_RESUMEN_ALUMNO_REGULAR("4","Reporte resumen alumno regular"),
 	
 	 /** El REPORTE_RESUMEN_ALUMNO_IREGULAR. */
 	REPORTE_RESUMEN_ALUMNO_IREGULAR("5","Reporte resumen alumno iregular"),
 	
	/** El REPORTE_RESUMEN_ALUMNO_IREGULAR. */
 	REPORTE_ALUMNO_MATRICULA_GENERO("6","Reporte alumno matricula genero");
 	
	/** La Constante LOO_KUP_MAP. */
	private static final Map<String, TipoReporteMatriculaType> LOO_KUP_MAP = new HashMap<String, TipoReporteMatriculaType>();	

	
	static {
		for (TipoReporteMatriculaType s : EnumSet.allOf(TipoReporteMatriculaType.class)) {
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
	private TipoReporteMatriculaType(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	/**
	 * Gets the.
	 *
	 * @param key el key
	 * @return the accion type
	 */
	public static TipoReporteMatriculaType get(String key) {
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
