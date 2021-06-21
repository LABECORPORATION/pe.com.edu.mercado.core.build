package pe.com.edu.siaa.core.ejb.factory;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

import pe.com.edu.siaa.core.ejb.service.util.ConfiguracionUtil;
import pe.com.edu.siaa.core.ejb.service.util.FechaUtil;
import pe.com.edu.siaa.core.model.dto.ConfiguracionTramaDetalleDTO;
import pe.com.edu.siaa.core.model.type.RespuestaNaturalType;
import pe.com.edu.siaa.core.model.util.ConstanteConfiguracionTramaUtil;
import pe.com.edu.siaa.core.model.util.ResourceUtil;
import pe.com.edu.siaa.core.model.util.StringUtils;
import pe.com.edu.siaa.core.model.vo.AtributoEntityVO;
import pe.com.edu.siaa.core.model.vo.ValueDataVO;

/**
 * La Class TransferDataObjectValidarUtil.
 * <ul>
 * <li>Copyright 2017 ndavilal-
 * mapfre. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0,27/10/2016
 * @since mytron v1.0
 */
public class TransferDataObjectValidarUtil   implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** La Constante ARTIFICIO_CLASS. */
	public static final String ARTIFICIO_CLASS = "$class";

	/** La Constante CARACTER_EXTRANHO. */
	public static final String CARACTER_EXTRANHO = "ï»¿";
	
	public static void defaultLocaleProcess(){
		Locale.setDefault(Locale.US);
	}
	
	public static <T> Field fieldHerenciaSet(T resultado,Map<String,Integer> fieldHerenciaMap,AtributoEntityVO objAtr) throws Exception {
		Field f = null;
		if (!fieldHerenciaMap.containsKey(objAtr.getNombreAtributo())) {
			f = resultado.getClass().getDeclaredField(objAtr.getNombreAtributo());
		} else {
			int nivelHerencia = fieldHerenciaMap.get(objAtr.getNombreAtributo());
			int nivel = 0;
			Class<?> currentHerencia = resultado.getClass();
			while(currentHerencia.getSuperclass() != null){ 
				nivel++;
				currentHerencia = currentHerencia.getSuperclass();
				if (nivel == nivelHerencia) {
					f = currentHerencia.getDeclaredField(objAtr.getNombreAtributo());
					break;
				}
			}
		}		
		return f;
	}
	public static <T> Map<String,Integer> fieldHerenciaMap(T resultado) {
		Map<String,Integer> fieldHerenciaMap = new HashMap<String, Integer>();
		Class<?> current = resultado.getClass();
		int nivel = 0;
		while(current.getSuperclass() != null){ // we don't want to process Object.class
		    // do something with current's fields
		    current = current.getSuperclass();
		    List<AtributoEntityVO> listaAtributosHerencia = AtributosEntityCacheUtil.getInstance().obtenerListaAtributos(current);
		    nivel++;
		    if (listaAtributosHerencia != null) {
			    for (AtributoEntityVO field : listaAtributosHerencia) {
			    	fieldHerenciaMap.put(field.getNombreAtributo(), nivel);
				}
		    }
		}
		return fieldHerenciaMap;
	}
	public static String obtenerHandlerHibernate(String className) {
		String resultado = "";
		String handlerHibernateKeys = ConfiguracionUtil.getPwrConfUtil("handlerHibernate.impl");
		boolean existeConfiguracion = false;
		if (!StringUtils.isNullOrEmpty(handlerHibernateKeys)) {
			String[] dataKey = handlerHibernateKeys.split(",", -1);
			if (dataKey != null && dataKey.length > 0) {
				for (String handlerHibernate : dataKey) {
					if (className.contains(handlerHibernate)) {
						resultado = handlerHibernate;
						existeConfiguracion =  true;
						break;
					}
				}
			}
		} 
		if (!existeConfiguracion) {
			resultado = "_$$_javassist";
			if (!className.contains("_$$_javassist")) {
				resultado = "_$$_jvst";
			}
		}
		return resultado;
		
	}
	
	public static boolean validarDataExel(HSSFRow hssfRow,Map<String,Object> campoMappingExcelMap) {
		boolean resultado = false;
		for (Map.Entry<String, Object> objAtr : campoMappingExcelMap.entrySet()) {
			int index = Integer.parseInt(objAtr.getValue() + "");
			if (index < 0) {
				continue;
			}
			try {
				HSSFCell hssfCell = (HSSFCell) hssfRow.getCell(index);
				hssfCell.setCellType(Cell.CELL_TYPE_STRING);
				if (hssfCell != null && !hssfCell.toString().trim().equals("")) {
					resultado = true;
					break;
				}
			} catch (Exception e) {
				resultado = false;
			}
		}
		return resultado;
	}
	public static boolean validarDataExel(XSSFRow hssfRow,Map<String,Object> campoMappingExcelMap) {
		boolean resultado = false;
		for (Map.Entry<String, Object> objAtr : campoMappingExcelMap.entrySet()) {
			int index = Integer.parseInt(objAtr.getValue() + "");
			if (index < 0) {
				continue;
			}
			try {
				XSSFCell hssfCell = (XSSFCell) hssfRow.getCell(index);
				hssfCell.setCellType(Cell.CELL_TYPE_STRING);
				if (hssfCell != null && !hssfCell.toString().trim().equals("")) {
					resultado = true;
					break;
				}
			} catch (Exception e) {
				resultado = false;
			}
		}
		return resultado;
	}
	
	public static boolean validarCSV(String[] data,Map<String,Object> campoMappingCVSMap) {
		boolean resultado = false;
		for (Map.Entry<String, Object> objAtr : campoMappingCVSMap.entrySet()) {
	         int index = Integer.parseInt(objAtr.getValue() + "");			
			 try {
				String hssfCell = (String) data[index];
				if (hssfCell.contains(CARACTER_EXTRANHO)) {
					hssfCell = hssfCell.substring(lengthCaracterExtranho());
				}
				if (hssfCell != null && !hssfCell.toString().trim().equals("")) {
					resultado = true;
					break;
				}
			} catch (Exception e) {
				resultado = false;
			}
		}
		return resultado;
	}
	public static int lengthCaracterExtranho() {
		return CARACTER_EXTRANHO.length() + 1;
	}
	protected static StringBuilder generarKeyAgrupador(Map<String,ValueDataVO> resultadoTemp,Map<String,ConfiguracionTramaDetalleDTO> configuracionTramaDetalleMap) {
    	StringBuilder key = new StringBuilder();
		for (Map.Entry<String, ValueDataVO> mapValue : resultadoTemp.entrySet()) {
			// validar
			ConfiguracionTramaDetalleDTO configuracionTramaDetalle = configuracionTramaDetalleMap.get(mapValue.getKey());
			boolean isAgrupador = RespuestaNaturalType.SI.getKey().equals(configuracionTramaDetalle.getFlagCampoAgrupador());
			if (isAgrupador) {
				key.append(mapValue.getValue() + "");
			}
		}
		return key;
    }
    protected static boolean validarTXT(String data,Map<String,Object> campoMappingTXTMap) {
		boolean resultado = false;
		for (Map.Entry<String, Object> objAtr : campoMappingTXTMap.entrySet()) {
			String index = objAtr.getValue() + "";
			try {
				String[] begin = index.split(";");
				int beginIndex = Integer.parseInt(begin[0]);
				if (beginIndex < 0 ) {
					continue;
				}
				int endIndex = Integer.parseInt(begin[1]);
				if (endIndex < 0 ) {
					continue;
				}
				//Inicio aumentado el nuevo requerimiento
				if (data.length() < endIndex) {
					endIndex = data.length();
				}
				//Fin aumentado el nuevo requerimiento
				String hssfCell = (String) data.substring(beginIndex, endIndex);
				if (hssfCell.contains(CARACTER_EXTRANHO)) {
					hssfCell = hssfCell.substring(lengthCaracterExtranho());
				}
				if (hssfCell != null && !hssfCell.toString().trim().equals("")) {
					resultado = true;
					break;
				}
			} catch (Exception e) {
				resultado = false;
			}
		}
		
		return resultado;
	}
    
    protected static boolean validarTXTCoordenada(String data,Map<String,Object> campoMappingTXTMap) {
		boolean resultado = false;
		for (Map.Entry<String, Object> objAtr : campoMappingTXTMap.entrySet()) {
			String index = objAtr.getValue() + "";
			int filaData = 0;
			try {
				String[] begin = index.split(";");
				filaData = Integer.parseInt(begin[0]);
				if (filaData < 0 ) {
					continue;
				}
				int beginIndex = Integer.parseInt(begin[1]);
				if (beginIndex < 0 ) {
					continue;
				}
				int endIndex = Integer.parseInt(begin[2]);
				if (endIndex < 0 ) {
					continue;
				}
				//Inicio aumentado el nuevo requerimiento
				if (data.length() < endIndex) {
					endIndex = data.length();
				}
				//Fin aumentado el nuevo requerimiento
				String hssfCell = (String) data.substring(beginIndex, endIndex);
				if (hssfCell.contains(CARACTER_EXTRANHO)) {
					hssfCell = hssfCell.substring(lengthCaracterExtranho());
				}
				if (hssfCell != null && !hssfCell.toString().trim().equals("")) {
					resultado = true;
					break;
				}
			} catch (Exception e) {
				resultado = false;
			}
		}
		return resultado;
	}
    /**
	 * Obtener valor xls.
	 *
	 * @param hssfRow el hssf row
	 * @param index el index
	 * @param objAt el obj at
	 * @return the object
	 */
	protected static Object obtenerValorXls(HSSFRow hssfRow,int index,AtributoEntityVO objAt) {
		Object resultado = null;
		if (index < 0) {
			return resultado;
		}
		HSSFCell hssfCell = null;
		try {
			hssfCell = (HSSFCell) hssfRow.getCell(index);
			hssfCell.setCellType(Cell.CELL_TYPE_STRING);
		} catch (Exception e) {
			//log.error("no existe index " +  (index + 1) + " " + e.toString());
		}
		try {
			if (hssfCell != null && !hssfCell.toString().trim().equals("")) {
				resultado = obtenerValor(hssfCell.toString(), objAt,false);
			}
		} catch (Exception e) {
			//log.error("no se puede parsear a " + objAt.getClasssAtributo() + "  obtenido de la columna : " + (index + 1)+ " --> " + e.toString());
		}
		return resultado;
	}
	/**
	 * Obtener valor xlsx.
	 *
	 * @param hssfRow el hssf row
	 * @param index el index
	 * @param objAt el obj at
	 * @return the object
	 */
	public static Object obtenerValorXlsx(XSSFRow hssfRow,int index,AtributoEntityVO objAt) {
		Object resultado = null;
		if (index < 0) {
			return resultado;
		}
		XSSFCell hssfCell = null;
		try {
			hssfCell = (XSSFCell) hssfRow.getCell(index);
			hssfCell.setCellType(Cell.CELL_TYPE_STRING);
		} catch (Exception e) {
			//log.error("no existe index " +  (index + 1) + " " + e.toString());
		}
		try {
			if (hssfCell != null && !hssfCell.toString().trim().equals("")) {
				resultado = obtenerValor(hssfCell.toString(), objAt,false);
			}
		} catch (Exception e) {
			//log.error("no se puede parsear a " + objAt.getClasssAtributo() + "  obtenido de la columna : " + (index + 1)+ " --> " + e.toString());
		}
		return resultado;
	}
	/**
	 * Obtener value csv.
	 *
	 * @param hssfRow el hssf row
	 * @param index el index
	 * @param objAt el obj at
	 * @param formatoFecha el formato fecha
	 * @param filaData el fila data
	 * @param parametroMap el parametro map
	 * @return the value data vo
	 */
	protected static ValueDataVO obtenerValueCSV(String[] hssfRow,int index,String objAt,String formatoFecha,int filaData,Map<String,Object> parametroMap) {
		ValueDataVO resultado = new ValueDataVO();
		resultado.setFila("" + (filaData));
		if (index < 0) {
			return resultado;
		}
		String hssfCell = null;
		try {
			hssfCell = (String) hssfRow[index];
			if (hssfCell.contains(CARACTER_EXTRANHO)) {
				hssfCell = hssfCell.substring(lengthCaracterExtranho());
			}
		} catch (Exception e) {
			if (ResourceUtil.esSimulacion(parametroMap.get(ConstanteConfiguracionTramaUtil.ES_SIMULACION))) {
				resultado.setData("${ERROR} al obtener la información, la posición " + (index + 1) +  " no existe en la fila ");
    	    } else {
    			//log.error("no existe index " +  (index + 1) + " " + e.toString());
				resultado.setData("${ERROR}:Posición(" + (index + 1) + ") no existe en la fila : " + (filaData));
    	    }
		}
		try {
			if (hssfCell != null && !hssfCell.toString().trim().equals("")) {
				resultado = obtenerValueParse(hssfCell.toString(), objAt,formatoFecha,filaData,parametroMap);
			}
		} catch (Exception e) {
			if (ResourceUtil.esSimulacion(parametroMap.get(ConstanteConfiguracionTramaUtil.ES_SIMULACION))) {
				resultado.setData("${ERROR} al obtener la información, la posición " + (index + 1) +  " no existe en la fila ");
    	    } else {
				//log.error("no se puede parsear a " + objAt + "  obtenido de la columna : " + (index + 1) + " --> " + e.toString());
				resultado.setData("${ERROR}:Posición(" + (index + 1) + ") no existe  en la fila : " + (filaData));
    	    }
		}
		return resultado;
	}
	/**
	 * Obtener value posicion.
	 *
	 * @param data el data
	 * @param index el index
	 * @param objAt el obj at
	 * @param formatoFecha el formato fecha
	 * @param filaData el fila data
	 * @return the value data vo
	 */
	protected static ValueDataVO obtenerValuePosicion(String data,String index,String objAt,String formatoFecha, int filaData,Map<String, Object> parametroMap) {
		ValueDataVO resultado = new ValueDataVO();
		resultado.setFila((filaData) + "");
		String hssfCell = null;
		String vIindex = "";
		try {
			String[] begin = index.split(";");
			int beginIndex = Integer.parseInt(begin[0]);
			if (beginIndex < 0 ) {
				return resultado;
			}
			vIindex = "" + (beginIndex + 1);
			int endIndex = Integer.parseInt(begin[1]);
			if (endIndex < 0 ) {
				return resultado;
			}
			vIindex = vIindex + ";" + (endIndex + 1);
			//Inicio aumentado el nuevo requerimiento
			if (data.length() < endIndex) {
				endIndex = data.length();
			}
			//Fin aumentado el nuevo requerimiento
			hssfCell = (String) data.substring(beginIndex, endIndex);
			if (hssfCell.contains(CARACTER_EXTRANHO)) {
				hssfCell = hssfCell.substring(lengthCaracterExtranho());
			}
		} catch (Exception e) {
			//log.error("no existe index " +  (vIindex) + " " + e.toString());
			//log.error("no se puede parsear a " + objAt + "  obtenido de la columna : " + (vIindex) + " --> " + e.toString());
			if (ResourceUtil.esSimulacion(parametroMap.get(ConstanteConfiguracionTramaUtil.ES_SIMULACION))) {
				resultado.setData("${ERROR} al obtener la información, la posición " + recortarCadenaValuePosicion(vIindex) +  " no existe en la fila ");
			} else {
				resultado.setData("${ERROR}:Posición(" + (vIindex) + ") no existe en la fila " + (filaData));				
			}
		}
		try {
			if (hssfCell != null && !hssfCell.toString().trim().equals("")) {
				resultado = obtenerValueParse(hssfCell.toString(), objAt,formatoFecha,filaData,parametroMap);
			}
		} catch (Exception e) {
			//log.error("no se puede parsear a " + objAt + "  obtenido de la columna : " + (index + 1) + " --> " + e.toString());
			if (ResourceUtil.esSimulacion(parametroMap.get(ConstanteConfiguracionTramaUtil.ES_SIMULACION))) {
				resultado.setData("${ERROR} al obtener la información, la posición " + recortarCadenaValuePosicion(vIindex) +  " no existe en la fila ");
			} else {
				resultado.setData("${ERROR}:Posición(" + (index + 1) + ") no existe en la fila " + (filaData));				
			}
			
		}
		return resultado;
	}
	
	
	/**
	 * Recortar cadena personalizado.
	 *
	 * @param vIindex el v iindex
	 * @return the string
	 */
	public static StringBuilder recortarCadenaValuePosicion(String vIindex) {
		StringBuilder resultado = new StringBuilder();
		resultado.append(vIindex.substring(0,((vIindex.indexOf(";"))) > 0 ? (vIindex.indexOf(";")) : 0));
		return resultado;
	}
	
	/**
	 * Obtener coordenada valor.
	 *
	 * @param dataList el data list
	 * @param dataValue el data value
	 * @param index el index
	 * @param objAt el obj at
	 * @param formatoFecha el formato fecha
	 * @param isCabecera el is cabecera
	 * @return the value data vo
	 */
	protected static ValueDataVO obtenerCoordenadaValor(List<String> dataList, String dataValue,String index,String objAt,String formatoFecha, boolean isCabecera,Map<String, Object> parametroMap) {
		ValueDataVO resultado = new ValueDataVO();
		String hssfCell = null;
		int filaData = 0;
		String vIindex = "";
		try {
			String[] begin = index.split(";");
			filaData = Integer.parseInt(begin[0]);
			if (filaData < 0 ) {
				return resultado;
			}
			vIindex = "" + (filaData);
			int beginIndex = Integer.parseInt(begin[1]);
			if (beginIndex < 0 ) {
				return resultado;
			}
			vIindex = vIindex + ";" + (beginIndex + 1);
			int endIndex = Integer.parseInt(begin[2]);
			if (endIndex < 0 ) {
				return resultado;
			}
			vIindex = vIindex + ";" + (endIndex + 1);
			if (isCabecera) {
				if (dataList.size() >= filaData) {
					String data = dataList.get(filaData);
					//Inicio aumentado el nuevo requerimiento
					if (data.length() < endIndex) {
						endIndex = data.length();
					}
					//Fin aumentado el nuevo requerimiento
					hssfCell = (String) data.substring(beginIndex, endIndex);
					if (hssfCell.contains(CARACTER_EXTRANHO)) {
						hssfCell = hssfCell.substring(lengthCaracterExtranho());
					}
				}
				
			} else {
				//Inicio aumentado el nuevo requerimiento
				if (dataValue.length() < endIndex) {
					endIndex = dataValue.length();
				}
				//Fin aumentado el nuevo requerimiento
				hssfCell = (String) dataValue.substring(beginIndex, endIndex);
				if (hssfCell.contains(CARACTER_EXTRANHO)) {
					hssfCell = hssfCell.substring(lengthCaracterExtranho());
				}
			}
			resultado.setFila((filaData + 1) + "");   //SE SUMA + 1 PARA PRESENTAR AL USUARIO 
			
		} catch (Exception e) {
			resultado.setFila((filaData + 1) + ""); //SE SUMA + 1 PARA PRESENTAR AL USUARIO 
			//log.error("no existe index " + vIindex + " " + e.toString());
			//log.error("no se puede parsear a " + objAt + "  obtenido de la columna : " + (vIindex) + " --> " + e.toString());
			if (ResourceUtil.esSimulacion(parametroMap.get(ConstanteConfiguracionTramaUtil.ES_SIMULACION))) {
				resultado.setData("${ERROR} al obtener la información, la posición " + recortarCadenaCoordenadaValor(vIindex) +  " no existe en la fila ");
			} else {
				resultado.setData("${ERROR}:Posición(" + (vIindex) + ") no existe en la fila " + (filaData + 1));				
			}
		}
		try {
			if (hssfCell != null && !hssfCell.toString().trim().equals("")) {
				resultado = obtenerValueParse(hssfCell.toString(), objAt,formatoFecha,filaData,parametroMap);
			}
		} catch (Exception e) {
		    //log.error("no se puede parsear a " + objAt + "  obtenido de la columna : " + (index + 1) + " --> " + e.toString());
			if (ResourceUtil.esSimulacion(parametroMap.get(ConstanteConfiguracionTramaUtil.ES_SIMULACION))) {
				resultado.setData("${ERROR} al obtener la información, la posición " + recortarCadenaCoordenadaValor(vIindex) +  " no existe en la fila ");
			} else {
				resultado.setData("${ERROR}:Posición(" + (index + 1) + ") no existe en la fila " + (filaData));				
			}
			
		}
		return resultado;
	}
	
	
	/**
	 * Recortar cadena coordenada valor.
	 *
	 * @param vIindex el v iindex
	 * @return the string builder
	 */
	public static StringBuilder recortarCadenaCoordenadaValor(String vIindex) {
		StringBuilder resultado = new StringBuilder();
		int index1 = vIindex.indexOf(";");
		int index2 = vIindex.indexOf(";", index1 + 1);
		resultado.append(vIindex.substring(index1 + 1 , index2));
		return resultado;
	}
	
	
	/**
	 * Obtener valor xls.
	 *
	 * @param hssfRow el hssf row
	 * @param index el index
	 * @param objAt el obj at
	 * @param formatoFecha el formato fecha
	 * @param filaData el fila data
	 * @return the object
	 */
	protected static ValueDataVO obtenerValorXls(HSSFRow hssfRow,int index,String objAt,String formatoFecha, int filaData,Map<String, Object> parametroMap) {
		ValueDataVO resultado = new ValueDataVO();
		resultado.setFila("" +  (filaData));
		if (index < 0) {
			return resultado;
		}
		HSSFCell hssfCell = null;
		try {
			hssfCell = (HSSFCell) hssfRow.getCell(index);
			if (StringUtils.isNullOrEmpty(formatoFecha)) {
				hssfCell.setCellType(Cell.CELL_TYPE_STRING);
			}
		} catch (Exception e) {
			resultado.setFila((filaData) + "");							
			//log.error("no existe index " +  index + " " + e.toString());
			//log.error("no se puede parsear a " + objAt + "  obtenido de la columna : " + (index + 1) + " --> " + e.toString());
			
			if (ResourceUtil.esSimulacion(parametroMap.get(ConstanteConfiguracionTramaUtil.ES_SIMULACION))) {
				resultado.setData("${ERROR} al obtener la información, la posición " + (index + 1) +  " no existe en la fila ");
			} else {
				resultado.setData("${ERROR}:Posición(" + (index + 1) + ") no existe en la fila " + (filaData));
			}
		}
		try {
			if (hssfCell != null && !hssfCell.toString().trim().equals("")) {
				resultado = obtenerValueParse(hssfCell.toString(), objAt,formatoFecha,filaData,parametroMap);
			}
		} catch (Exception e) {
			//log.error("no se puede parsear a " + objAt + "  obtenido de la columna : " + (index + 1) + " --> " + e.toString());
			if (ResourceUtil.esSimulacion(parametroMap.get(ConstanteConfiguracionTramaUtil.ES_SIMULACION))) {
				resultado.setData("${ERROR} al obtener la información, la posición " + (index + 1) +  " no existe en la fila ");
			} else {
				resultado.setData("${ERROR}:Posición(" + (index + 1) + ") no existe en la fila " + (filaData));				
			}
		}
		return resultado;
	}
	
	/**
	 * Obtener valor xlsx.
	 *
	 * @param hssfRow el hssf row
	 * @param index el index
	 * @param objAt el obj at
	 * @param formatoFecha el formato fecha
	 * @param filaData el fila data
	 * @return the object
	 */
	protected static ValueDataVO obtenerValorXlsx(XSSFRow hssfRow,int index,String objAt,String formatoFecha, int filaData,Map<String,Object> parametroMap) {
		ValueDataVO resultado = new ValueDataVO();
		resultado.setFila("" + (filaData));
		if (index < 0) {
			return resultado;
		}
		XSSFCell hssfCell = null;
		try {
			hssfCell = (XSSFCell) hssfRow.getCell(index);
			if (StringUtils.isNullOrEmpty(formatoFecha)) {
				hssfCell.setCellType(Cell.CELL_TYPE_STRING);
			}
		} catch (Exception e) {
			resultado.setFila((filaData) + "");							
			//log.error("no existe index " +  (index + 1) + " " + e.toString());
			//log.error("no se puede parsear a " + objAt + "  obtenido de la columna : " + (index + 1) + " --> " + e.toString());
			if (ResourceUtil.esSimulacion(parametroMap.get(ConstanteConfiguracionTramaUtil.ES_SIMULACION))) {
				resultado.setData("${ERROR} al obtener la información, la posición " + (index + 1) +  " no existe en la fila ");
			} else {
				resultado.setData("${ERROR}:Posición(" + (index + 1) + ") no existe en la fila " + (filaData));
			}
		}
		try {
			if (hssfCell != null && !hssfCell.toString().trim().equals("")) {
				resultado = obtenerValueParse(hssfCell.toString(), objAt,formatoFecha,filaData,parametroMap);
			}
		} catch (Exception e) {
			//log.error("no se puede parsear a " + objAt + "  obtenido de la columna : " + (index + 1) + " --> " + e.toString());
			if (ResourceUtil.esSimulacion(parametroMap.get(ConstanteConfiguracionTramaUtil.ES_SIMULACION))) {
				resultado.setData("${ERROR} al obtener la información, la posición " + (index + 1) +  " no existe en la fila ");
			} else {
				resultado.setData("${ERROR}:Posición(" + (index + 1) + ") no existe en la fila " + (filaData));
			}
		}
		return resultado;
	}
	
	/**
	 * Obtener value parse.
	 *
	 * @param resultadoValor el resultado valor
	 * @param objAt el obj at
	 * @param formatoFecha el formato fecha
	 * @param filaData el fila data
	 * @return the object
	 * @throws Exception the exception
	 */
	public static ValueDataVO obtenerValueParse(String resultadoValor,String objAt, String formatoFecha, int filaData,Map<String, Object> parametroMap) throws Exception {
		ValueDataVO resultado = new ValueDataVO();
		resultado.setFila("" + (filaData != 0 ? filaData : "") );
		try {
			if (StringUtils.isNullOrEmpty(resultadoValor))  {
				return null;
			}
			resultadoValor = StringUtils.quitarCaracterExtranio(resultadoValor);
			resultadoValor = resultadoValor.trim();
			if (objAt.equals(Boolean.class.getName())) {
				resultado.setData(Boolean.valueOf(resultadoValor));
			} else if (objAt.equals(Integer.class.getName())) {
				resultado.setData(Integer.parseInt(resultadoValor));
			} else if (objAt.equals(Float.class.getName())) {
				resultado.setData(Float.parseFloat(resultadoValor));
			} else if (objAt.equals(Double.class.getName())) {
				resultado.setData(Double.parseDouble(resultadoValor));
			} else if (objAt.equals(Long.class.getName())) {
				resultado.setData(Long.parseLong(resultadoValor));
			} else if (objAt.equals(BigDecimal.class.getName())) {
				//convirtiendo datos numericos fomateados
				resultadoValor = reingenieriaFormateoNumerico(resultadoValor);
				resultado.setData(new  BigDecimal(resultadoValor));
			} else if (objAt.equals(Character.class.getName())) {
				resultado.setData(resultadoValor.charAt(0));
			}  else if (objAt.equals(Date.class.getName())) {
				resultado.setData(FechaUtil.obtenerFechaFormatoPersonalizado(resultadoValor,formatoFecha));
			}  else if (objAt.equals(Collection.class.getName())) {
				resultado =  null;
			}  else  {
				resultado.setData(resultadoValor);
			}
		} catch (Exception e) {
			
			if (ResourceUtil.esSimulacion(parametroMap.get(ConstanteConfiguracionTramaUtil.ES_SIMULACION))) {
				resultado.setData("${ERROR}${type} El tipo de dato del campo no corresponde a lo configurado");//+ resultadoValor + "
			} else {				
				resultado.setData("${ERROR} :Tipo de Conversión no valido ${type} --> " + resultadoValor + " en la fila " + (filaData));
			}
			return resultado;
		}
		return resultado;
	}
	
	/**
	 * Reingenieria formateo numerico.
	 *
	 * @param dataResul el data resul
	 * @return the string
	 */
	public static String reingenieriaFormateoNumerico(String dataResul) {
		try {
			String formateoDecimal = ConfiguracionUtil.getPwrConfUtil(ConfiguracionUtil.CARACTER_FORMATEO_NUMERICO);
			if ( formateoDecimal != null) {
				for (Character chart : formateoDecimal.toCharArray()) {
					dataResul = dataResul.replace(chart + "", "");
				}
			}
		} catch (Exception e) {
			//log.error(e);
		}
		if (dataResul != null) {
			dataResul = dataResul.trim();
		}
		return dataResul;
	}
	
	/**
	 * Obtener valor.
	 *
	 * @param resultadoValor el resultado valor
	 * @param objAt el obj at
	 * @param isVO el is vo
	 * @return the object
	 * @throws Exception the exception
	 */
	public static Object obtenerValor(String resultadoValor,AtributoEntityVO objAt,boolean isVO) throws Exception {
		return obtenerValor(resultadoValor,objAt,isVO, new HashMap<String, String>());
	}
	
	/**
	 * Obtener valor.
	 *
	 * @param resultadoValor el resultado valor
	 * @param objAt el obj at
	 * @param isVO el is vo
	 * @param formatoMap el formato map
	 * @return the object
	 * @throws Exception the exception
	 */
	public static Object obtenerValor(String resultadoValor,AtributoEntityVO objAt,boolean isVO,Map<String,String> formatoMap) throws Exception {
		Object resultado = null;
		if (StringUtils.isNullOrEmpty(resultadoValor))  {
			return null;
		}
		try {
			if ("id".equalsIgnoreCase(objAt.getNombreAtributo())) {//comodin
				resultado =  resultadoValor;
			} else if ("idPadreView".equalsIgnoreCase(objAt.getNombreAtributo())) {//comodin
				resultado =  resultadoValor;
			} else if (objAt.getClasssAtributoType().isAssignableFrom(Boolean.class)) {
				resultado = Boolean.valueOf(resultadoValor);
			} else if (objAt.getClasssAtributoType().isAssignableFrom(int.class)) {
				resultado = Integer.parseInt(resultadoValor);
			} else if (objAt.getClasssAtributoType().isAssignableFrom(Integer.class)) {
				resultado =  Integer.parseInt(resultadoValor);
			} else if (objAt.getClasssAtributoType().isAssignableFrom(Float.class)) {
				resultado =  Float.parseFloat(resultadoValor);
			} else if (objAt.getClasssAtributoType().isAssignableFrom(Double.class)) {
				resultado =  Double.parseDouble(resultadoValor);
			} else if (objAt.getClasssAtributoType().isAssignableFrom(Long.class)) {
				resultado =  Long.parseLong(resultadoValor);
			} else if (objAt.getClasssAtributoType().isAssignableFrom(BigDecimal.class)) {
				resultado =  new  BigDecimal(resultadoValor);
			} else if (objAt.getClasssAtributoType().isAssignableFrom(Character.class)) {
				resultado =  resultadoValor.charAt(0);
			}  else if (objAt.getClasssAtributoType().isAssignableFrom(Date.class)) {
				if (!isVO) {
					if (formatoMap.containsKey(objAt.getNombreAtributo())) {
						resultado =  FechaUtil.obtenerFechaFormatoPersonalizado(resultadoValor, formatoMap.get(objAt.getNombreAtributo()));
					} else {
						resultado =  FechaUtil.obtenerFecha(resultadoValor);
					}
					
				} else {
					if (formatoMap.containsKey(objAt.getNombreAtributo())) {
						resultado =  FechaUtil.obtenerFechaFormatoPersonalizado(resultadoValor, formatoMap.get(objAt.getNombreAtributo()));
					} else {
						resultado = FechaUtil.obtenerFechaFormatoCompleto(resultadoValor);
					}
				}
			}  else if (objAt.getClasssAtributoType().isAssignableFrom(Collection.class)) {
				resultado =  null;
			} else  {
				resultado =  resultadoValor;
			}
		} catch (Exception e) {
			//log.error("Error.obtenerValor-->  " + resultadoValor  + " , " + objAt.getClasssAtributoType().getName());
			resultado =  null;
		}
		
		return resultado;
	}
	
}
