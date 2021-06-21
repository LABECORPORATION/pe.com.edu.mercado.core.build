package pe.com.edu.siaa.core.ejb.util.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.util.log.Logger;
import pe.com.edu.siaa.core.model.dto.ConfiguracionTramaDetalleDTO;
import pe.com.edu.siaa.core.model.util.ConstanteConfiguracionTramaUtil;
import pe.com.edu.siaa.core.model.vo.ValueDataVO;

/**
 * <ul>
 * <li>Copyright 2017 ndavilal. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class CSVUtil.
 *
 * @author ndavilal
 * @version 1.0 , 07/04/2015
 * @since SIAA-CORE 2.1
 */
public class CSVUtil {

	/** 
	 * Logger para el registro de errores.
	 */
	private static Logger log = Logger.getLogger(CSVUtil.class);
	
	
	public static List<Map<String, ValueDataVO>> transferObjetoEntityCSVMapDTO(BufferedReader br, int filaData, Map<String, Object> campoMappingCVSMap, Map<String, String> campoMappingExcelTypeMap, Map<String, String> campoMappingFormatoMap, String csvSplitBy, Integer cantidadData, Map<String, Object> parametroMap,Map<String,ConfiguracionTramaDetalleDTO> configuracionTramaDetalleMap) throws Exception {
		parametroMap.put(ConstanteConfiguracionTramaUtil.FILA_DATA_ORIGINAL, filaData);
		parametroMap.put("filaData", filaData);
		parametroMap.put("cantidadData", cantidadData);
		parametroMap.put("csvSplitBy", csvSplitBy);
		return TransferDataObjectUtil.transferObjetoEntityCSVMapDTO(campoMappingCVSMap, br, campoMappingExcelTypeMap, campoMappingFormatoMap, parametroMap,configuracionTramaDetalleMap);
	}

	public static BufferedReader leerCVS(String pathFile) {
		BufferedReader resultado = null;
		InputStream in = null;
		try {
			File f = new File(pathFile);
			in = new FileInputStream(f);		
		} catch (Exception e) {
			log.error(e);
		} 
		
		try {
			 resultado = new BufferedReader(new InputStreamReader(in , "latin1"));
		} catch (Exception e) {
			log.error(e);
		} 
		
		return resultado;
	}
}