package pe.com.edu.siaa.core.ejb.service.local;

import java.util.Map;

import javax.ejb.Local;

import net.sf.jasperreports.engine.JasperPrint;
import pe.com.edu.siaa.core.model.vo.FileVO;
import pe.com.edu.siaa.core.model.vo.ParametroReporteVO;

/**
 * <ul>
 * <li>Copyright 2017 ndavilal. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Interface GenerarReporteServiceLocal.
 *
 * @author ndavilal
 * @version 1.0 , 07/04/2015
 * @since SIAA-CORE 2.1
 */
@Local
public interface GenerarReporteServiceLocal {

	
	String obtenerParametroReporteBigMemory(ParametroReporteVO parametroReporteVO);
	
	/**
	 * Generar reporte.
	 *
	 * @param parametroReporteVO el parametro reporte vo
	 * @return the file vo
	 * @throws Exception the exception
	 */
	FileVO generarReporte(ParametroReporteVO parametroReporteVO) throws Exception;
	
	/**
	 * Generar reporte.
	 *
	 * @param jasperPrint el jasper print
	 * @param formato el formato
	 * @param context el context
	 * @return the file vo
	 */
	FileVO generarReporte(JasperPrint jasperPrint, String formato, String context,String ruta);
	
	/**
	 * Generar reporte array pdf.
	 *
	 * @param parametroReporteVO el parametro reporte vo
	 * @return the byte[]
	 * @throws Exception the exception
	 */
	Map<String,Object> generarReporteArrayPdf(ParametroReporteVO parametroReporteVO) throws Exception;

	
	
	/**
	 * Generar reporte array html.
	 *
	 * @param parametroReporteVO el parametro reporte vo
	 * @return the byte[]
	 * @throws Exception the exception
	 */
	Map<String,Object> generarReporteArrayHtml(ParametroReporteVO parametroReporteVO) throws Exception;

	

	/**
	 * Generar reporte array xls.
	 *
	 * @param parametroReporteVO el parametro reporte vo
	 * @return the byte[]
	 * @throws Exception the exception
	 */
	Map<String,Object> generarReporteArrayXls(ParametroReporteVO parametroReporteVO) throws Exception;

	
	/**
	 * Generar reporte array rtf.
	 *
	 * @param parametroReporteVO el parametro reporte vo
	 * @return the byte[]
	 * @throws Exception the exception
	 */
	Map<String,Object> generarReporteArrayRtf(ParametroReporteVO parametroReporteVO) throws Exception;

	
		
	
	/**
	 * Generar reporte array odt.
	 *
	 * @param parametroReporteVO el parametro reporte vo
	 * @return the byte[]
	 * @throws Exception the exception
	 */
	Map<String,Object> generarReporteArrayOdt(ParametroReporteVO parametroReporteVO) throws Exception;

	
	
	
	/**
	 * Generar reporte array ods.
	 *
	 * @param parametroReporteVO el parametro reporte vo
	 * @return the byte[]
	 * @throws Exception the exception
	 */
	Map<String,Object> generarReporteArrayOds(ParametroReporteVO parametroReporteVO) throws Exception;

	
		
	/**
	 * Generar reporte array docx.
	 *
	 * @param parametroReporteVO el parametro reporte vo
	 * @return the byte[]
	 * @throws Exception the exception
	 */
	Map<String,Object> generarReporteArrayDocx(ParametroReporteVO parametroReporteVO) throws Exception;

	/**
	 * Generar reporte array pptx.
	 *
	 * @param parametroReporteVO el parametro reporte vo
	 * @return the byte[]
	 * @throws Exception the exception
	 */
	Map<String,Object> generarReporteArrayPptx(ParametroReporteVO parametroReporteVO) throws Exception;

		
	/**
	 * Generar reporte array xlsx.
	 *
	 * @param parametroReporteVO el parametro reporte vo
	 * @return the byte[]
	 * @throws Exception the exception
	 */
	Map<String,Object> generarReporteArrayXlsx(ParametroReporteVO parametroReporteVO) throws Exception;

}