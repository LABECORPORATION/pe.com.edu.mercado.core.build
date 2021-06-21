package pe.com.edu.siaa.core.model.type;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
* La Enum NombreReporteType.
* <ul>
* <li>Copyright 2017 ndavilal -
* MAPFRE. Todos los derechos reservados.</li>
* </ul>
*
* @author ndavilal
* @version 1.0, Thu Jul 31 10:21:30 COT 2017
* @since SIAA-CORE 2.1
*/
public enum NombreReporteType {

	 /** El JR_REP_FICHA_MATRICULA_INDIVIDUAL. */
	JR_REP_FICHA_MATRICULA_INDIVIDUAL("jrFichaMatriculaIndividual.jasper","FichaMatriculaIndividual","jrFichaMatriculaIndividual",""),
	
	/** El JR_REP_RECORD_NOTA_INDIVIDUAL. */
 	JR_REP_RECORD_NOTA_INDIVIDUAL("jrRecordNotaIndividual.jasper","RecordNotaIndividual","jrRecordNotaIndividual",""),
 	
	/** El JR_REP_REPORTE_NOTA_ORDENADO_BY_SEMESTRE. */
 	JR_REP_REPORTE_NOTA_ORDENADO_BY_SEMESTRE("jrReporteNotaOrdenadoBySemestre.jasper","ReporteNotaOrdenadoBySemestre","jrReporteNotaOrdenadoBySemestre",""),
 	
 	/** El JR_REP_REPORTE_NOTA_ORDENADO_BY_CICLO. */
 	JR_REP_REPORTE_NOTA_ORDENADO_BY_CICLO("jrReporteNotaOrdenadoByCiclo.jasper","ReporteNotaOrdenadoByCiclo","jrReporteNotaOrdenadoByCiclo",""),
 	
 	/** El JR_REP_REPORTE_CETIFICADO_ESTUDIO_BY_RANGO_CICLO. */
 	JR_REP_REPORTE_CETIFICADO_ESTUDIO_BY_RANGO_CICLO("jrRepCertificadoEstudio.jasper","ReporteCertificadoByRangoCiclo","jrReporteCertificadoByRangoCiclo",""),
 	
 	//agregando
 	 	/** El JR_REP_MATRICULA_ESTADISTICO. */
 	 	JR_REP_MATRICULA_ESTADISTICO_RANGO_SEMESTRE("jrRepMatriculaEstadistico.jasper","RepMatriculaEstadistico","jrRepMatriculaEstadistico",""),
 	 	
 	 	/** El JR_REP_RESUMEN_MATRICULA_POR_CICLO_Y_SEMESTRE. */
 		JR_REP_RESUMEN_MATRICULA_POR_CICLO_Y_SEMESTRE("jrResumenMatricula.jasper","ResumenMatriculaPorCicloYSemstre","jrResumenMatricula",""),
 		
 		/** El JR_REP_PLAN_ESTUDIO. */
 		JR_REP_PLANESTUDIO("jrRepPlanEstudio.jasper","ReportePlanEstudio","jrRepPlanEstudio",""),
 		//Alumno estadistica regular iregular
 		/** El JR_REP_ALUMNO_REGULAR. */
 	 	JR_REP_ALUMNO_REGULAR("jrRepAlumRegular.jasper","CuadroEstadisticoMatricula","jrCuadroEstadisticoMatricula",""),	
 	 	
 	 	/** El JR_REP_ALUMNO_REGULAR. */
 	 	JR_REP_ALUMNO_IREGULAR("jrRepAlumIregular.jasper","CuadroEstadisticoMatricula","jrCuadroEstadisticoMatricula",""),
 	 	
 	 	/** El JR_REP_ALUMNO_REGULAR. */
 	 	JR_REP_ALUMNO_MATRICULA_GENERO("jrRepAlumMatGenero.jasper","CuadroEstadisticoMatricula","jrCuadroEstadisticoMatricula",""),

 	
 	/** El JR_REP_REPORTE_ACTA_EVALUACION_FINAL. */
 	JR_REP_REPORTE_ACTA_EVALUACION_FINAL("jrRepActaEvaluacionFinal.jasper","ReporteActaEvaluacionFinal","jrReporteActaEvaluacionFinal",""),
 	
 	//add
	/** El JR_REP_REPORTE_ACTA_EVALUACION_FINAL_FORMATO. */
 	JR_REP_REPORTE_ACTA_EVALUACION_FINAL_FORMATO("jrRepActaEvaluacionFinalFormato.jasper","ReporteActaEvaluacionFinal","jrReporteActaEvaluacionFinalFormato",""),
 	
	//para formato de asistencia
 	/** El JR_REP_REPORTE_FORMATO_UNICO_ASISTENCIA. */
 	JR_REP_REPORTE_FORMATO_UNICO_ASISTENCIA("jrRepFormatoUnicoAsistencia.jasper","ReporteActaEvaluacionFinal","jrReporteFormatoUnicoAsistencia",""),
 	 	
 	//para reporte de matriculados por curso
 	/** EL JR_REP_REPORTE_MATRICULADOS_POR_CURSO. */
 	JR_REP_REPORTE_MATRICULADOS_POR_CURSO("jrRepReproteMatriculadosPorCurso.jasper","ReporteActaEvaluacionFinal","jrRepReproteMatriculadosPorCurso",""),
 	
 	/**JR_REP_REPORTE_DET_MOVIMIENTO. */
 	JR_REP_PAGOS_POR_CURSO("jrReportePagoPorCurso.jasper","ReportePagoPorCurso","jrReportePagoPorCurso",""),
 	
 	/**JR_REP_PAGOS_POR_CICLO. */
 	JR_REP_PAGOS_POR_CICLO("jrReportePagoPorCiclo.jasper","ReportePagoPorCiclo","jrReportePagoPorCiclo",""),
 	
 	/** El JR_REP_RECORD_NOTA_MASIVO. */
 	JR_REP_RECORD_NOTA_MASIVO("jrRecordNotaMasivo.jasper","RecordNotaMasivo","jrRecordNotaMasivo",""),
 	
 	/** El JR_REP_RESUMEN_NOTA_BY_SEMESTRE. */
 	JR_REP_RESUMEN_NOTA_BY_SEMESTRE("jrResumenPorEscuelaCurso.jasper","ResumenPorEscuelaCurso","jrResumenPorEscuelaCurso",""),
 	
 	/** El JR_REP_BOLETA_PAGOS_REALIZADOS_ALUMNO. */
 	JR_REP_BOLETA_PAGOS_REALIZADOS_ALUMNO("jrReportePagoIndividualMercado.jasper","ReportePagoIndividual","jrReportePagoIndividualMercado",""),
 	//add
 	/**JR_REP_REPORTE_DET_MOVIMIENTO. */
 	JR_REP_INGRESO_DETALLADO("jrRepIngresoDetallado.jasper","RepDetalleVentas","jrRepDetalleVenta",""),
 	
 	/**JR_REP_VENTAS_RESUMEN. */
 	JR_REP_VENTAS_RESUMEN("jrRepIngresoResumen.jasper","RepResumenVenta","jrRepVetasResumen",""),
 	
 	//add
 	/** El JR_REP_BOLETA_VENTA. */
 	JR_REP_BOLETA_VENTA("jrReporteVentaIndividual.jasper","ReporteVentaIndividual","jrReporteVentaIndividual",""),
 	
 	JR_REP_BOLETA_VENTA_A4("jrReporteVentaIndividualA4.jasper","ReporteVentaIndividualA4","jrReporteVentaIndividualA4",""),
 	
 	JR_REP_VISTA_PREVIA("jrReporteVistaPrevia.jasper","ReporteVistaPrevia","jrReporteVistaPrevia",""),
 	
 	/** El JR_REP_PDF_PROFORMA. */
 	JR_REP_PDF_PROFORMA("jrReportePdfProforma.jasper","ReportePdfProforma","jrReportePdfProforma",""),
 	
 	/** El JR_REP_PDF_GUIA_REMISION. */
 	JR_REP_PDF_GUIA_REMISION("jrReporteGuia.jasper","ReporteGuiaA4","jrReporteGuiaA4",""),
 	
 	
 	/** El JR_REP_REPORTE_PRODUCTO_CODIGO_BARRA. */
 	JR_REP_REPORTE_PRODUCTO_CODIGO_BARRA("jrReporteCodigoBarraProducto.jasper","ReporteCodigoBarraProducto","jrReporteCodigoBarraProducto",""),
 	
 	//constancia de registro GyT
 	/** El JR_REP_REPORTE_ACTA_EVALUACION_FINAL. */
 	JR_REP_REPORTE_GRADO_TITULO("jrRepGradoTitulo.jasper","ReporteGradoTitulo","jrReporteGradoTitulo",""),
 	
 	//constancia de registro GyT
 	/** El JR_REP_REPORTE_ACTA_EVALUACION_FINAL. */
 	JR_REP_REPORTE_EGRESADO_FORMULARIO("jrReporteEgresadoFormulario.jasper","ReporteFormularioEgresado","jrReporteEgresadoFormulario",""),
 	
 	JR_REP_REPORTE_EGRESADO("jrReporteEgresado.jasper","ReporteEgresado","jrReporteEgresado",""),
 	
 	JR_REP_CIERRE_CAJA("jrRepCierreCaja.jasper","RepCierreCaja","jrRepCierreCaja",""),
 	
	JR_REP_CIERRE_CAJA_REPORTE("jrRepCierreCajaReporteMercado.jasper","RepCierreCajaReporte","jrRepCierreCajaReporteMercado",""),
	
	/** El JR_REP_ALUMNO_REGULAR. */
 	JR_REP_ORDEN_COMPRA("jrReporteOrdenCompra.jasper","ReporteOrdenCompra","jrReporteOrdenCompra",""),
 	
	 /** El NULO. */
	 NULO("","","","")
	 ;
 	
	 
	/** La Constante LOO_KUP_MAP. */
	private static final Map<String, NombreReporteType> LOO_KUP_MAP = new HashMap<String, NombreReporteType>();
	
	static {
		for (NombreReporteType s : EnumSet.allOf(NombreReporteType.class)) {
			LOO_KUP_MAP.put(s.getKey(), s);
		}
	}

	/** El key. */
	private String key;
	
	/** El value. */
	private String value;
	
	private String carperta;
	
	private String codigoReporte;

	
	/**
	 * Instancia un nuevo nombre reporte type.
	 *
	 * @param key el key
	 * @param value el value
	 * @param carperta el carperta
	 * @param codigoReporte el codigo reporte
	 */
	private NombreReporteType(String key, String value,String carperta,String codigoReporte) {
		this.key = key;
		this.value = value;
		this.carperta = carperta;
		this.codigoReporte = codigoReporte;
	}
	
	/**
	 * Gets the.
	 *
	 * @param key el key
	 * @return the accion type
	 */
	public static NombreReporteType get(String key) {
		if (LOO_KUP_MAP.containsKey(key)) {
			return LOO_KUP_MAP.get(key);
		} 
		return NULO;
		
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
	 * Obtiene carpeta.
	 *
	 * @return carpeta
	 */
	public String getCarpeta() {
		return carperta;
	}

	/**
	 * Obtiene codigo reporte.
	 *
	 * @return codigo reporte
	 */
	public String getCodigoReporte() {
		return codigoReporte;
	}	
	
}