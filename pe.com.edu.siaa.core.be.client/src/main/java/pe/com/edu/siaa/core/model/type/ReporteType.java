/*
 * 
 */
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
 * La Enum ReporteType.
 *
 * @author ndavilal.
 * @version 1.0 , 30/09/2012
 * @since SIAA 2.0
 */
public enum ReporteType {

	//Reportes de matricula
    /** El JR_REP_MATRICULA_COMPARACIOM_SEMESTRE. */
 	JR_REP_MATRICULA_COMPARACIOM_SEMESTRE("jrRepMatriculaComparacionSemestre.jasper","MatriculaComparacionSemestre"),
 	
 	/** El JR_REP_FICHA_MATRICULA_MASIVO. */
	 JR_REP_FICHA_MATRICULA_MASIVO("jrFichaMatricula.jasper","FichaMatriculaMasivo"),
	 
	 /** El JR_REP_FICHA_MATRICULA_INDIVIDUAL. */
 	JR_REP_FICHA_MATRICULA_INDIVIDUAL("jrFichaMatriculaIndividual.jasper","FichaMatriculaIndividual"),
	 
	 /** El JR_REP_MATRICULA_ESTADISTICO. */
 	JR_REP_MATRICULA_ESTADISTICO_RANGO_SEMESTRE("jrRepMatriculaEstadistico.jasper","MatriculaEstadisticoRangoSemestre"),
 	
 	/** El JR_REP_RESUMEN_MATRICULA_POR_CICLO_Y_SEMESTRE. */
	 JR_REP_RESUMEN_MATRICULA_POR_CICLO_Y_SEMESTRE("jrResumenMatricula.jasper","ResumenMatriculaPorCicloYSemstre"),
	 
	 //Reporte de nota
	 /** El JR_REP_RECORD_NOTA_MASIVO*. */
	 JR_REP_RECORD_NOTA_MASIVO("jrRecordNota.jasper","RecordNotaMasivo"),
	 
	 /** El JR_REP_RECORD_NOTA_INDIVIDUAL. */
 	JR_REP_RECORD_NOTA_INDIVIDUAL("jrRecordNotaIndividual.jasper","RecordNotaIndividual"),
 	
 	/** El JR_REP_ACTA_EVALUACION_FINAL. */
	 JR_REP_ACTA_EVALUACION_FINAL("jrRepActaEvaluacionFinal.jasper","ActaEvaluacionFinal"),
	 	 
	//para formato asistencia
	/** El JR_REP_REPORTE_FORMATO_UNICO_ASISTENCIA. */
	    JR_REP_REPORTE_FORMATO_UNICO_ASISTENCIA("jrRepFormatoUnicoAsistencia.jasper","ReporteActaEvaluacionFinal"),
		 	
   //para reporte de matriculados por curso
		/** EL JR_REP_REPORTE_MATRICULADOS_POR_CURSO. */
		JR_REP_REPORTE_MATRICULADOS_POR_CURSO("jrRepReproteMatriculadosPorCurso.jasper","ReporteActaEvaluacionFinal"),
	 
	 /** El JR_REP_CERTIFICADO_ESTUDIO. */
 	JR_REP_CERTIFICADO_ESTUDIO("jrRepCertificadoEstudio.jasper","CertificadoEstudio"),
 	
 	/** El JR_REP_RESUMEN_NOTA_POR_ESCUELA_CURSO. */
	 JR_REP_RESUMEN_NOTA_POR_ESCUELA_CURSO("jrResumenPorEscuelaCurso.jasper","RepResumenPorEscuelaCurso"),
 	
 	//Reporte de Admision
 	/** El JR_REP_POSTULANTE_POR_SEMESTRE. */
	 JR_REP_POSTULANTE_POR_SEMESTRE("jrPostulantePorSemestre.jasper","RepPostulantePorSemestre"),
	 
	//Reporte de Pago
	 /** El JR_REP_ESTADO_CUENTA_ALUMNO. */
	JR_REP_ESTADO_CUENTA_ALUMNO("jrReporteEstadoCuentaIndividual.jasper","RepEstadoCuentaAlumno"),
	
	/** El JR_REP_PAGOS_REALIZADOS_ALUMNO. */
	JR_REP_PAGOS_REALIZADOS_ALUMNO("jrReportePagoIndividual.jasper","RepPagosRealizadosAlumno"),
	
	/** El JR_REP_BOLETA_PAGOS_REALIZADOS_ALUMNO. */
	JR_REP_BOLETA_PAGOS_REALIZADOS_ALUMNO("jrReporteBoletaPagoIndividual.jasper","RepBoletaPagosRealizadosAlumno");
 	
	/** La Constante LOO_KUP_MAP. */
	private static final Map<String, ReporteType> LOO_KUP_MAP = new HashMap<String, ReporteType>();
	
	static {
		for (ReporteType s : EnumSet.allOf(ReporteType.class)) {
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
	private ReporteType(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	/**
	 * Gets the.
	 *
	 * @param key el key
	 * @return the accion type
	 */
	public static ReporteType get(String key) {
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
