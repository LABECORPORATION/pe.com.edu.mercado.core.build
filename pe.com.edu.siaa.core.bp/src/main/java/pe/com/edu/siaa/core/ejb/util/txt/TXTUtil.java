package pe.com.edu.siaa.core.ejb.util.txt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.util.cache.SessionUtil;
import pe.com.edu.siaa.core.ejb.util.log.Logger;
import pe.com.edu.siaa.core.model.dto.ConfiguracionTramaDetalleDTO;
import pe.com.edu.siaa.core.model.type.TipoReporteGenerarType;
import pe.com.edu.siaa.core.model.util.ConstanteConfigUtil;
import pe.com.edu.siaa.core.model.util.StringUtils;
import pe.com.edu.siaa.core.model.vo.FileVO;
import pe.com.edu.siaa.core.model.vo.ValueDataVO;

/**
 * <ul>
 * <li>Copyright 2017 ndavilal. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class TXTUtil.
 *
 * @author ndavilal
 * @version 1.0 , 07/04/2015
 * @since SIAA-CORE 2.1
 */
public class TXTUtil {

	/** 
	 * Logger para el registro de errores.
	 */
	private static Logger log = Logger.getLogger(TXTUtil.class);
	
	
	public  static List<Map<String,ValueDataVO>> transferObjetoEntityTXTMapDTO(BufferedReader br,int filaData,Map<String,Object> campoMappingExcelMap,Map<String,String> campoMappingExcelTypeMap,Map<String,String> campoMappingFormatoMap, Integer cantidadData,Map<String, Object> parametroMap,Map<String,ConfiguracionTramaDetalleDTO> configuracionTramaDetalleMap) throws Exception {
		parametroMap.put("filaData", filaData);
		parametroMap.put("cantidadData", cantidadData);
		return TransferDataObjectUtil.transferObjetoEntityTXTMapDTO(campoMappingExcelMap, br,campoMappingExcelTypeMap,campoMappingFormatoMap,parametroMap,configuracionTramaDetalleMap);
	}
	
	
	public static BufferedReader leerTXT(String pathFile) {
		BufferedReader resultado = null;
		try {
			 resultado =  new BufferedReader(new InputStreamReader(new FileInputStream(pathFile)));
		} catch (Exception e) {
			log.error(e);
		} 
		
		return resultado;
	}
	
	public static void escribirArchivo(List<Map<String,Object>> listaData,String userName,String nombreArchivo,String extension,String separador) {
		FileWriter fichero = null;
	    PrintWriter pw = null;
	    try {
	    	String rutaArchivo = ConstanteConfigUtil.RUTA_SESSION_TEMP + ConstanteConfigUtil.generarRuta(userName,"archivo");
	    	File file = new File(rutaArchivo);
	    	if (!file.exists()) {
	    		file.mkdirs();
	    	}
	    	rutaArchivo = rutaArchivo + nombreArchivo + "." + extension;
	        fichero = new FileWriter(rutaArchivo,true);
	        pw = new PrintWriter(fichero);
	        for (Map<String, Object> map : listaData) {
	           	 Collection<Object> listaDataMap = map.values();
	             StringBuilder data = new StringBuilder();
	             for (Object dataMap : listaDataMap) {
	            	  data.append(dataMap);
	            	  if (StringUtils.isNotNullOrBlank(separador)) {
	            		  data.append(separador);
	            	  }	            		  
	             }
	             pw.println(data);
			}	               

	     } catch (Exception e) {
	        	log.error(e);
	     } finally {
	       try {
	           // Nuevamente aprovechamos el finally para 
	           // asegurarnos que se cierra el fichero.
	           if (null != fichero)
	              fichero.close();
	           } catch (Exception e2) {
	              e2.printStackTrace();
	           }
	        }	
	}
	
	public static  String generarTXTViewMap( String userName, String key, String archivoName, TipoReporteGenerarType tipoReporteGenerarType) {
		 FileVO objeto = new FileVO();
		 objeto.setName(archivoName + "." + tipoReporteGenerarType.getKey());
		 objeto.setMime(tipoReporteGenerarType.getContentType());
		 objeto.setUserName(userName);
		 objeto.setExtension(tipoReporteGenerarType.getKey());
		 //objeto.setLength(byteTemp.length);
		 SessionUtil.pasarParametro(key, objeto);
		 return archivoName;
	}
	
	public static void escribirArchivoXML(List<Map<String,Object>> listaData,String userName,String nombreArchivo,String extension,String separador) {
		FileWriter fichero = null;
	    PrintWriter pw = null;
        String rutaArchivo2 = ConstanteConfigUtil.RUTA_DATOS_EXTRACION + ConstanteConfigUtil.generarRuta(userName,"DATA")+ nombreArchivo + "." + extension;
        File  objFile = new File(rutaArchivo2);
    	if (objFile.exists()) { 
    		System.out.println("ExisteArc");
    		objFile.delete();
    	} 
	    try {
	    	String rutaArchivo = ConstanteConfigUtil.RUTA_DATOS_EXTRACION + ConstanteConfigUtil.generarRuta(userName,"DATA");
	    	File file = new File(rutaArchivo);
	    	
	    	if (!file.exists()) {
	    		file.mkdirs();
	    	}	    	
	    	rutaArchivo = rutaArchivo + nombreArchivo + "." + extension;
	        fichero = new FileWriter(rutaArchivo,true);
	        pw = new PrintWriter(fichero);
	        
	        for (Map<String, Object> map : listaData) { 
	           	 Collection<Object> listaDataMap = map.values();
	             StringBuilder data = new StringBuilder();
	             for (Object dataMap : listaDataMap) { 
	            	  data.append(dataMap);
	            	  if (StringUtils.isNotNullOrBlank(separador)) {
	            		  data.append(separador);
	            	  }	            		  
	             }
	             pw.println(data);
			}              

	     } catch (Exception e) {
	        	log.error(e);
	     } finally {
	       try {
 
	           if (null != fichero)
	              fichero.close();
	           } catch (Exception e2) {
	              e2.printStackTrace();
	           }
	        }	
	}
	
}