package pe.com.edu.siaa.core.ejb.util.excel;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.util.log.Logger;

/**
 * <ul>
 * <li>Copyright 2017 ndavilal. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class ExcelUtil.
 *
 * @author ndavilal
 * @version 1.0 , 07/04/2015
 * @since SIAA-CORE 2.1
 */
public class ExcelUtil {

	/** 
	 * Logger para el registro de errores.
	 */
	private static Logger log = Logger.getLogger(ExcelUtil.class);
	
	public  static <T> List<T>  transferObjetoEntityExcelDTO(byte[] datosArchivo,int hoja,int filaData,Map<String,Integer> campoMappingExcelMap,Class<T> entityClassDTO) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityExcelDTO(campoMappingExcelMap, leerExcel(datosArchivo), entityClassDTO, hoja, filaData);
	}
	public  static <T> List<T>  transferObjetoEntityExcelAllDTO(byte[] datosArchivo,int hoja,int filaData,Map<String,Integer> campoMappingExcelMap,Class<T> entityClassDTO) throws Exception {
		try {
			return transferObjetoEntityExcelDTO(datosArchivo, hoja, filaData, campoMappingExcelMap, entityClassDTO);
		} catch (Exception e) {
			return transferObjetoEntityExcelXlsxDTO(datosArchivo, hoja, filaData, campoMappingExcelMap, entityClassDTO);
		}
		
	}
	public  static <T> List<T>  transferObjetoEntityExcelDTO(HSSFWorkbook  hSSFWorkbook,int hoja,int filaData,Map<String,Integer> campoMappingExcelMap,Class<T> entityClassDTO) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityExcelDTO(campoMappingExcelMap, hSSFWorkbook, entityClassDTO, hoja, filaData);
	}
	public  static <T> List<T>  transferObjetoEntityExcelXlsxDTO(byte[] datosArchivo,int hoja,int filaData,Map<String,Integer> campoMappingExcelMap,Class<T> entityClassDTO) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityExcelXlsxDTO(campoMappingExcelMap, leerExcelXLSX(datosArchivo), entityClassDTO,hoja, filaData);
	}
	public  static <T> List<T>  transferObjetoEntityExcelXlsxDTO(XSSFWorkbook  xSSFWorkbook,int hoja,int filaData,Map<String,Integer> campoMappingExcelMap,Class<T> entityClassDTO) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityExcelXlsxDTO(campoMappingExcelMap, xSSFWorkbook, entityClassDTO,hoja, filaData);
	}
	
	
	public static void defaultLocale(){
		Locale ES = new Locale("es", "PE");
		Locale.setDefault(ES);
	}
	
	public static void defaultLocaleProcess(){
		TransferDataObjectUtil.defaultLocaleProcess();
	}
	
	public static  HSSFWorkbook leerExcel(File rutaArchivo)throws Exception {
		defaultLocale();
		FileInputStream fileInputStream = new FileInputStream(rutaArchivo);
		HSSFWorkbook workBook = new HSSFWorkbook(fileInputStream);
		defaultLocale();
		return workBook;
	}
	
	public static  HSSFWorkbook leerExcel(byte[] datosArchivo)throws Exception {
		defaultLocale();
		InputStream fileExcel = new ByteArrayInputStream(datosArchivo);
		/** Create a workbook desde InputStream **/
		HSSFWorkbook workBook = new HSSFWorkbook(fileExcel);
		defaultLocale();
		return workBook;
	}
	public static  XSSFWorkbook leerExcelXLSX(byte[] datosArchivo)throws Exception {
		defaultLocale();
		InputStream fileExcel = new ByteArrayInputStream(datosArchivo);
		/** Create a workbook desde InputStream **/
		XSSFWorkbook workBook = new XSSFWorkbook(fileExcel);
		defaultLocale();
		return workBook;
	}
	
	
	public static  XSSFWorkbook leerExcelXlsx(File rutaArchivo)throws Exception {
		defaultLocale();
		FileInputStream fileInputStream = new FileInputStream(rutaArchivo);
		XSSFWorkbook workBook = new XSSFWorkbook(fileInputStream);
		defaultLocale();
		return workBook;
	}
	public static  SXSSFWorkbook leerExcelsXlsx(File rutaArchivo)throws Exception {
		XSSFWorkbook xSSFWorkbook = leerExcelXlsx(rutaArchivo);
		SXSSFWorkbook workBook = new SXSSFWorkbook(xSSFWorkbook);
		defaultLocale();
		return workBook;
	}
}