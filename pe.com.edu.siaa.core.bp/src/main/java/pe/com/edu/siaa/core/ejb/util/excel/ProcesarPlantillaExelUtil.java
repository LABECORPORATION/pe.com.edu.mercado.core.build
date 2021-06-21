package pe.com.edu.siaa.core.ejb.util.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;

import pe.com.edu.siaa.core.ejb.util.log.Logger;
import pe.com.edu.siaa.core.model.util.ConstanteConfigUtil;
import pe.com.edu.siaa.core.model.vo.ExcelHederDataVO;


/**
 * <ul>
 * <li>Copyright 2018 ndavilal. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class ProcesarPlantillaExelUtil.
 *
 * @author ndavilal
 * @version 1.0 , 23/01/2018
 * @since SIAA-CORE 2.1
 */
public class ProcesarPlantillaExelUtil {
	
	/** El log. */
    private static Logger log = Logger.getLogger(ProcesarPlantillaExelUtil.class);

	/** La Constante RUTA_RECURSOS. */
	public static final String RUTA_RECURSOS_BYTE_BUFFER = ConstanteConfigUtil.RUTA_RECURSOS_BYTE_BUFFER;
	
	
	public static String generarExcelXLSX(List<?> listaData, List<ExcelHederDataVO> listaHeaderData, String archivoName, String titulo, String nombreArchivoTemplate, Map<String, Object> propiedadesMap) {
		String resultado = null;
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("listaData", listaData);
		dataMap.put("titulo", titulo);
		dataMap.putAll(propiedadesMap);
		try {
			String rutaTemplate = ConstanteConfigUtil.RUTA_GENERAL_TEMPLANTE + nombreArchivoTemplate;
			File file = new File(rutaTemplate);
			 try(InputStream is = new FileInputStream(file)) {
			        try(OutputStream os = new FileOutputStream(RUTA_RECURSOS_BYTE_BUFFER + archivoName)) {
			            Context context = new Context();
			            List<String> listaHeader = new ArrayList<String>();
			            List<String> listaAtribute = new ArrayList<String>();
			            for (ExcelHederDataVO objHeader : listaHeaderData) {
			            	listaHeader.add(objHeader.getNameHeader());
			            	listaAtribute.add(objHeader.getNameAtribute());
						}
						context.putVar("headers", listaHeader);
			            context.putVar("data", listaData);
			            //SimpleExporter exporter = new SimpleExporter();
			           // exporter.gridExport(listaHeader, listaData,listaAtribute.toString().replaceAll("[", "").replaceAll("]",""), os);
			            JxlsHelper.getInstance().processGridTemplateAtCell(is, os, context, listaAtribute.toString().replaceAll("[", "").replaceAll("]",""), "Sheet2!A1");
			        }
			    }
		} catch (Exception e) {
			log.error(e);
			resultado = e.getMessage();
		}
		return resultado;
	}
	
	public static String proesarPlantillaExcelXLSX(List<?> listaData, String archivoName, String titulo, String nombreArchivoTemplate, Map<String, Object> propiedadesMap) {
		String resultado = null;
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("listaData", listaData);
		dataMap.put("titulo", titulo);
		dataMap.putAll(propiedadesMap);
		try {
			String rutaTemplate = ConstanteConfigUtil.RUTA_GENERAL_TEMPLANTE + nombreArchivoTemplate;
			File file = new File(rutaTemplate);
			 try(InputStream is = new FileInputStream(file)) {
			        try(OutputStream os = new FileOutputStream(RUTA_RECURSOS_BYTE_BUFFER + archivoName)) {
			            Context context = new Context();
			            context.putVar("data", listaData);
			            JxlsHelper.getInstance().processTemplateAtCell(is, os, context, "Result!A1");
			        }
			    }
		} catch (Exception e) {
			log.error(e);
			resultado = e.getMessage();
		}
		return resultado;
	}
}
