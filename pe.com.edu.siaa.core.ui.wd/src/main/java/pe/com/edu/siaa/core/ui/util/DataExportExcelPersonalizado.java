package pe.com.edu.siaa.core.ui.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pe.com.edu.siaa.core.ejb.util.cache.SessionUtil;
import pe.com.edu.siaa.core.ejb.util.excel.DataExportExcelPersonalizadoUtil;
import pe.com.edu.siaa.core.ejb.util.log.Logger;
import pe.com.edu.siaa.core.model.type.TipoReporteGenerarType;
import pe.com.edu.siaa.core.model.vo.ExcelHederDataVO;
import pe.com.edu.siaa.core.model.vo.FileVO;


/**
 * <ul>
 * <li>Copyright 2017 ndavilal. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class DataExportExcelPersonalizado.
 *
 * @author ndavilal
 * @version 1.0 , 08/04/2015
 * @since SIAA-CORE 2.1
 */
public class DataExportExcelPersonalizado   implements Serializable {

	/** La Constante serialVersionUID. */
	private static final long serialVersionUID = 6359062834392294265L;

	/** La log. */
	private static Logger log = Logger.getLogger(DataExportExcelPersonalizado.class);

	/**
	 * Instancia un nuevo data export excel.
	 */
	public DataExportExcelPersonalizado() {
		
	}
		

	/**
	 * Generar excel view.
	 *
	 * @param listaHeaderData el lista header data
	 * @param listaData el lista data
	 * @param archivoName el archivo name
	 * @param titulo el titulo
	 * @param propiedadesMap el propiedades map
	 * @return the string
	 */
	public static String generarExcelXLSXView(List<ExcelHederDataVO> listaHeaderData, List<?> listaData,String archivoName,String titulo,Map<String,Object> propiedadesMap) {
		 DataExportExcelPersonalizadoUtil.generarExcelXLSX(listaHeaderData, listaData, archivoName, titulo, propiedadesMap);
		 FileVO objeto = new FileVO();
		 String nombre = archivoName + ".xlsx";
		 objeto.setName(nombre);
		 objeto.setDataBig(nombre);
		 objeto.setMime(TipoReporteGenerarType.XLSX.getContentType());
		 //objeto.setLength(byteTemp.length);
		 SessionUtil.pasarParametro(archivoName, objeto);
		 return archivoName;
	}
	
	
	
	
	/**
	 * Genera generarExcelViewDetalleReporteProduccion.
	 * 
	 * @param listaHeader
	 * @param listaHeaderOverrideMap
	 * @param listaDataMap
	 * @param archivoName
	 * @param titulo
	 * @param propiedadesMap
	 * @return byte
	 */
	public static String generarExcelXLSXViewMap(List<ExcelHederDataVO> listaHeaderTemp, List<Map<String,Object>> listaDataMap, String archivoName,String titulo,Map<String,Object> propiedadesMap) {
		 Map<String,String> listaHeaderOverrideMap = new HashMap<String,String>();
		 List<String> listaHeader = new ArrayList<String>();
		 for (ExcelHederDataVO dataHeader : listaHeaderTemp) {
			 listaHeaderOverrideMap.put(dataHeader.getNameAtribute(), dataHeader.getNameHeader());
			 listaHeader.add(dataHeader.getNameAtribute());
		}
		 DataExportExcelPersonalizadoUtil.generarExcelXLSXMap(listaHeader, listaHeaderOverrideMap, listaDataMap, archivoName, titulo, propiedadesMap,null);
		 
		 FileVO objeto = new FileVO();
		 String nombre = archivoName + ".xlsx";
		 objeto.setName(archivoName + ".xlsx");
		 objeto.setDataBig(nombre);
		 objeto.setMime(TipoReporteGenerarType.XLSX.getContentType());
		 //objeto.setLength(byteTemp.length);
		 SessionUtil.pasarParametro(archivoName, objeto);
		 return archivoName;
	}
	public static String generarExcelXLSXViewMap(List<String> listaHeader, Map<String,String> listaHeaderOverrideMap,List<Map<String,Object>> listaDataMap, String archivoName,String titulo,Map<String,Object> propiedadesMap) {
		 DataExportExcelPersonalizadoUtil.generarExcelXLSXMap(listaHeader, listaHeaderOverrideMap, listaDataMap, archivoName, titulo, propiedadesMap,null);
		 FileVO objeto = new FileVO();
		 String nombre = archivoName + ".xlsx";
		 objeto.setName(archivoName + ".xlsx");
		 objeto.setDataBig(nombre);
		 objeto.setMime(TipoReporteGenerarType.XLSX.getContentType());
		 //objeto.setLength(byteTemp.length);
		 SessionUtil.pasarParametro(archivoName, objeto);
		 return archivoName;
	}
	public static String generarExcelViewBigMemory(String archivoName) {
		 generarViewBigMemory(archivoName, TipoReporteGenerarType.XLSX);
		 return archivoName;
	}
	public static String generarViewBigMemory(String archivoName,TipoReporteGenerarType tipoReporteGenerarType) {
		 FileVO objeto = new FileVO();
		 objeto.setTipoReporteGenerarType(tipoReporteGenerarType);
		 objeto.setName(archivoName + "." + tipoReporteGenerarType.getKey());
		 objeto.setData(null);
		 objeto.setDataBig(archivoName+ "." + tipoReporteGenerarType.getKey());
		 objeto.setMime(tipoReporteGenerarType.getContentType());
		 SessionUtil.pasarParametro(archivoName, objeto);
		 return archivoName;
	}
	

}
