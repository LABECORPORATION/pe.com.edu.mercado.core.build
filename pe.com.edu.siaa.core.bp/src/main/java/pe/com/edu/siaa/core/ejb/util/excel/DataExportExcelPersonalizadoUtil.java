package pe.com.edu.siaa.core.ejb.util.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanMap;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFName;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import pe.com.edu.siaa.core.ejb.factory.CollectionUtil;
import pe.com.edu.siaa.core.ejb.service.util.FechaUtil;
import pe.com.edu.siaa.core.ejb.util.cache.SessionUtil;
import pe.com.edu.siaa.core.ejb.util.jms.UUIDUtil;
import pe.com.edu.siaa.core.ejb.util.log.Logger;
import pe.com.edu.siaa.core.model.type.TipoReporteGenerarType;
import pe.com.edu.siaa.core.model.util.ConstanteConfigUtil;
import pe.com.edu.siaa.core.model.util.ObjectUtil;
import pe.com.edu.siaa.core.model.util.StringUtils;
import pe.com.edu.siaa.core.model.vo.ExcelComboDataVO;
import pe.com.edu.siaa.core.model.vo.ExcelHederDataVO;
import pe.com.edu.siaa.core.model.vo.ExcelHederTitleVO;
import pe.com.edu.siaa.core.model.vo.FileVO;


/**
 * <ul>
 * <li>Copyright 2017 ndavilal. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class DataExportExcelPersonalizadoUtil.
 *
 * @author ndavilal
 * @version 1.0 , 07/04/2015
 * @since SIAA-CORE 2.1
 */
public class DataExportExcelPersonalizadoUtil   implements Serializable {

	private static final String IS_FORMULA = "${FORMULA}";

	private static final int CANTIDAD_REGISTROS_COMBO = 100;

	/** La Constante DD_MM_YYY_HH_MM_SS. */
	private static final String DD_MM_YYY_HH_MM_SS = "dd/mm/yyy hh:mm:ss";
	
	/** La Constante DD_MM_YYY. */
	private static final String DD_MM_YYY = "dd/mm/yyy";

	/** La Constante serialVersionUID. */
	private static final long serialVersionUID = 6359062834392294265L;

	/** La Constante NOMBRE_LETRA. */
	public static final String NOMBRE_LETRA = "Arial";
	
	/** La Constante ROW_INFO_INDEX. */
	private static final String ROW_INFO_INDEX = "rowInfo.index";
	
	/** La Constante MAXIMO_RANGE_EXCEL. */
	private static final Integer MAXIMO_RANGE_EXCEL = 65535;
	
	private static final Integer MAXIMO_RANGE_EXCEL_XLSX = 1048575;
	
	/** La Constante CANTIDAD_FILAS_USADO_CABECERA. */
	private static final Integer CANTIDAD_FILAS_USADO_CABECERA = 1;
	
		/** El log. */
	private static Logger log = Logger.getLogger(DataExportExcelPersonalizadoUtil.class);

	/** La Constante RUTA_RECURSOS. */
	public static final String RUTA_RECURSOS_BYTE_BUFFER = ConstanteConfigUtil.RUTA_RECURSOS_BYTE_BUFFER;

	/**
	 * Instancia un nuevo data export excel.
	 */
	public DataExportExcelPersonalizadoUtil() {
		
	}
	
	public static  String generarExcelXLSXViewMap( String archivoName) {
		 FileVO objeto = new FileVO();
		 String nombre = archivoName + ".xlsx";
		 objeto.setName(archivoName + ".xlsx");
		 objeto.setDataBig(nombre);
		 objeto.setMime(TipoReporteGenerarType.XLSX.getContentType());
		 //objeto.setLength(byteTemp.length);
		 SessionUtil.pasarParametro(archivoName, objeto);
		 return archivoName;
	}
	

	public static String generarExcelXLSX(List<ExcelHederDataVO> listaHeaderData, List<?> listaData, String archivoName, String titulo, Map<String, Object> propiedadesMap) {
		String resultado = null;
		int hojaActiva = 0;
		boolean isFormula = propiedadesMap.containsKey("isFormula");
		boolean isBloqueo = propiedadesMap.containsKey("isBloqueo");
		try {
			boolean exluirCabecera = propiedadesMap.containsKey("exluirCabecera");
			List<ExcelHederTitleVO> listaTituloFinal = new ArrayList<ExcelHederTitleVO>();
			if (propiedadesMap.containsKey("listaTituloFinal")) {
				listaTituloFinal = (List<ExcelHederTitleVO>)propiedadesMap.get("listaTituloFinal");
			}
			Map<Integer,Integer> columnWidtMaxMap = new HashMap<Integer, Integer>();
			boolean calcularWitchDemanda = propiedadesMap.containsKey("calcularWitchDemanda");
			// Inicio Agregar coombo
			Map<String, Integer> campoPosicionMap = new HashMap<String, Integer>();
			boolean isCombo = propiedadesMap.containsKey("comboData");
			boolean anexarHojaExistente = propiedadesMap.containsKey("anexarHojaExistente");
			int posicionCellCabecera = 0;
			if (isCombo) {
				for (ExcelHederDataVO cellHeaderVO : listaHeaderData) {
					String nombreColumna = cellHeaderVO.getNameAtribute();
					if (!campoPosicionMap.containsKey(nombreColumna)) {
						campoPosicionMap.put(nombreColumna, posicionCellCabecera);
					}
					posicionCellCabecera++;
				}
			}
			// Fin Agregar coombo
			File archivoXLS = new File(RUTA_RECURSOS_BYTE_BUFFER);
			if (!archivoXLS.isFile()) {
				archivoXLS.mkdirs();
			}
			SXSSFWorkbook workbook = new SXSSFWorkbook(100);
			workbook.setCompressTempFiles(true); // temp files will be gzipped
			if (isCombo) {
				generarComboHojaXLSX(workbook, propiedadesMap);
			}
			int cantidadData = listaData.size();
			int cantidadHojas = 1;
			int contador = 0;
			if (cantidadData > MAXIMO_RANGE_EXCEL) {
				BigDecimal bCantidadData = new BigDecimal(cantidadData);
				BigDecimal maxRange = new BigDecimal(MAXIMO_RANGE_EXCEL);
				BigDecimal bCantidadHojas = bCantidadData.divide(maxRange, 2, BigDecimal.ROUND_UP);
				bCantidadHojas = bCantidadHojas.setScale(0, BigDecimal.ROUND_UP);
				cantidadHojas = bCantidadHojas.intValue();
			}
			DataFormat format = workbook.createDataFormat();
			CellStyle cellDateStyle = generarStyleDate(workbook);
			// indicando un patron de formato
			CellStyle titleStyle = generarStyleTitle(workbook,(short)9);
			// titleStyle.setLocked(false);
			for (int cantidadDataPaginadorHoja = 1; cantidadDataPaginadorHoja <= cantidadHojas; cantidadDataPaginadorHoja++) {
				String tituloFinal = titulo;
				if (propiedadesMap != null && propiedadesMap.containsKey("hojaName")) {
					tituloFinal = propiedadesMap.get("hojaName") + "";
				}
				if (cantidadHojas > 1) {
					tituloFinal = tituloFinal + cantidadDataPaginadorHoja;
				}
				SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet(tituloFinal); //CREA UNA HOJA
				
				for (ExcelHederTitleVO excelHederTitleVO : listaTituloFinal) {
					if(excelHederTitleVO.getWidthde()==10) {
							sheet.setDefaultColumnWidth(3); 

					}
				}
				
				sheet.setRandomAccessWindowSize(100);// keep 100 rows in memory, exceeding rows will be flushed to disk
				if (isBloqueo) {
					sheet.protectSheet(UUIDUtil.generarElementUUID());
				}
				int posicionRow = 0;
				int incrementroRow = 1;
				int maxPosicionRow = 0;
				if (!CollectionUtil.isEmpty(listaTituloFinal)) {
					for (ExcelHederTitleVO excelHederTitleVO : listaTituloFinal) {
						if (!excelHederTitleVO.isEsPiePagina()) {
							//excelHederTitleVO.setPosicionRow(posicionRow);
							int posicionRowVar =  excelHederTitleVO.getPosicionRow();
							int posicionCeldaVar =  excelHederTitleVO.getPosicionCelda();
							if (posicionRowVar > 0) {
								posicionRowVar = posicionRowVar - 1;
							}
							if (posicionCeldaVar > 0) {
								posicionCeldaVar = posicionCeldaVar - 1;
							}
							if (posicionRowVar > maxPosicionRow) {
								maxPosicionRow = posicionRowVar;
							}
							SXSSFRow  filaTitle = (SXSSFRow)sheet.getRow(posicionRowVar);
					    	if (filaTitle == null) {
					    	  	filaTitle = (SXSSFRow)sheet.createRow(posicionRowVar);
					    	}
							String tituloFinalPer = excelHederTitleVO.getNameHeader();
							SXSSFCell  heraderTitleCell = null;
							if (posicionCeldaVar > 0) {
								heraderTitleCell = (SXSSFCell)filaTitle.createCell(posicionCeldaVar);
							} else {
								heraderTitleCell = (SXSSFCell)filaTitle.createCell(0);
							}
							heraderTitleCell.setCellValue(tituloFinalPer);
							
							CellStyle titleStyleVar = generarStyleTitle(workbook,excelHederTitleVO.getFontHeightInPoints());
							heraderTitleCell.setCellStyle(titleStyleVar);
							heraderTitleCell.getCellStyle().setAlignment(HorizontalAlignment.forInt(excelHederTitleVO.getAling()));
							heraderTitleCell.getCellStyle().setFillForegroundColor(excelHederTitleVO.getBg());
	 
							if(excelHederTitleVO.getBg() > 1) {
								heraderTitleCell.getCellStyle().setFillPattern(FillPatternType.SOLID_FOREGROUND);
							    Font font = workbook.createFont();
					            font.setColor(excelHederTitleVO.getColorFontText());
					            font.setBold(true);
					            font.setFontHeightInPoints((short)10);
					            heraderTitleCell.getCellStyle().setFont(font);
							}
							
							
							if (excelHederTitleVO.getVerticalAlignment() > -1) {
								heraderTitleCell.getCellStyle().setVerticalAlignment(VerticalAlignment.forInt(excelHederTitleVO.getVerticalAlignment()));
							}
							heraderTitleCell.getCellStyle().setWrapText(excelHederTitleVO.isWrapText());
					
							excelHederTitleVO.setPosicionRow(posicionRowVar);
							excelHederTitleVO.setPosicionCelda(posicionCeldaVar);
							if (excelHederTitleVO.getRotacion() != 0) {
								heraderTitleCell.getCellStyle().setRotation((short)excelHederTitleVO.getRotacion() );
							}
							//posicionRow = posicionRow + incrementroRow;
							if (excelHederTitleVO.getColumnIndex() > -1 && excelHederTitleVO.getWidth() > -1) {
								columnWidtMaxMap.put(excelHederTitleVO.getColumnIndex(), excelHederTitleVO.getWidth());
							}
						}
					}
					
					if(listaHeaderData.size()>0) {
						posicionRow = maxPosicionRow + posicionRow + incrementroRow;
					}
					
					
					 
				} else {
					if (propiedadesMap != null && propiedadesMap.containsKey("printTitleView")) {
						SXSSFRow filaTitle = (SXSSFRow) sheet.createRow(posicionRow);
						SXSSFCell heraderTitleCell = (SXSSFCell) filaTitle.createCell(0);
						heraderTitleCell.setCellValue(titulo);
						posicionRow = posicionRow + incrementroRow;
					}
				}
				// creando cabecera del datos
				if (propiedadesMap != null && propiedadesMap.containsKey("rowInicio")) {
					posicionRow = Integer.parseInt(propiedadesMap.get("rowInicio") + "") - 1;
				}
				SXSSFRow fila = (SXSSFRow) sheet.createRow(posicionRow);
				sheet.createFreezePane(0, posicionRow);
				posicionCellCabecera = 0;
				int incremetoCellCabecera = 1;
				int columnIndex = 0;
				for (ExcelHederDataVO cellHeaderVO : listaHeaderData) {
					if (!exluirCabecera) {
						String cellHeader = cellHeaderVO.getNameHeader();
						SXSSFCell heraderCell = (SXSSFCell) fila.createCell(posicionCellCabecera);
						heraderCell.setCellValue(cellHeader);
						heraderCell.setCellStyle(titleStyle);
						posicionCellCabecera = posicionCellCabecera + incremetoCellCabecera;
					}
					if (calcularWitchDemanda) {
						int widtMaxActual =  ObjectUtil.objectToString(cellHeaderVO.getNameHeader()).length();
						double porcentaje = 0.20;
						if (!columnWidtMaxMap.containsKey(columnIndex)) {
							widtMaxActual = obtenerWidt(widtMaxActual, porcentaje);
							columnWidtMaxMap.put(columnIndex, widtMaxActual);
						}
					}
					columnIndex++;
				}
				//posicionRow = posicionRow + incrementroRow;
				
				for (ExcelHederTitleVO excelHederTitleVO : listaTituloFinal) {
					if (!excelHederTitleVO.isEsPiePagina()) {
						try {
							//int firstRow, int lastRow, int firstCol, int lastCol
							CellRangeAddress cellRangeAddress = null;
							if (excelHederTitleVO.getCantidadAgrupar() > 0 && excelHederTitleVO.getCantidadAgruparHorizontal() == 0) {
								cellRangeAddress = new CellRangeAddress(excelHederTitleVO.getPosicionRow().intValue(), excelHederTitleVO.getPosicionRow().intValue(), excelHederTitleVO.getPosicionCelda().intValue(),((excelHederTitleVO.getPosicionCelda().intValue()) - 1) + excelHederTitleVO.getCantidadAgrupar().intValue());
								sheet.addMergedRegion(cellRangeAddress);
								generarMergeRegionBorder(cellRangeAddress, sheet);
							}
							if (excelHederTitleVO.getCantidadAgruparHorizontal() > 0 && excelHederTitleVO.getCantidadAgrupar()  == 0) {
								cellRangeAddress = new CellRangeAddress(excelHederTitleVO.getPosicionRow().intValue(), ((excelHederTitleVO.getPosicionRow().intValue() - 1) + excelHederTitleVO.getCantidadAgruparHorizontal().intValue()), excelHederTitleVO.getPosicionCelda().intValue(),excelHederTitleVO.getPosicionCelda().intValue());
								sheet.addMergedRegion(cellRangeAddress);
								generarMergeRegionBorder(cellRangeAddress, sheet);
							}
							if (excelHederTitleVO.getCantidadAgruparHorizontal() > 0 && excelHederTitleVO.getCantidadAgrupar()  > 0) {
								cellRangeAddress = new CellRangeAddress(excelHederTitleVO.getPosicionRow().intValue(), ((excelHederTitleVO.getPosicionRow().intValue() - 1) + excelHederTitleVO.getCantidadAgruparHorizontal().intValue()), excelHederTitleVO.getPosicionCelda().intValue(),((excelHederTitleVO.getPosicionCelda().intValue()) - 1) + excelHederTitleVO.getCantidadAgrupar().intValue());
								sheet.addMergedRegion(cellRangeAddress);
								generarMergeRegionBorder(cellRangeAddress, sheet);
							}
						} catch (Exception e) {
							log.error(e);
						}
					}					
				}
				// llenando la data
				int primeraFila = posicionRow;
				int i = 0;
				int fromIndex = fromIndexXlsx(cantidadDataPaginadorHoja);
				int toIndex = toIndexXlsx(cantidadData, cantidadDataPaginadorHoja);
				for (Object cellData : listaData.subList(fromIndex, toIndex)) {
					SXSSFRow filaDet = (SXSSFRow) sheet.createRow(i + primeraFila);
					posicionCellCabecera = 0;
					incremetoCellCabecera = 1;
					columnIndex = 0;
					for (ExcelHederDataVO cellHeaderVO : listaHeaderData) {
						String nombreColumna = cellHeaderVO.getNameAtribute();
						Object value = null;
						if (!nombreColumna.equals(ROW_INFO_INDEX) && !nombreColumna.contains(IS_FORMULA)) {
							value = atributoValueComplejo(cellData, nombreColumna);
						} else {
							if (nombreColumna.equals(ROW_INFO_INDEX)) {
								value = (contador + 1);
							} 
						}
						
						CellStyle titleStyleVar = generarStyleTitle(workbook);

						if (esFecha(nombreColumna)) {
							Object valueDate = verificarFornatoFecha(nombreColumna, value);
							if (esFechaData(valueDate)) {
								SXSSFCell cellDetalle = (SXSSFCell) filaDet.createCell(posicionCellCabecera);
								cellDetalle.setCellValue((Date) valueDate);
								if (propiedadesMap != null && propiedadesMap.containsKey(nombreColumna + "Format")) {
									cellDateStyle.setDataFormat(format.getFormat(propiedadesMap.get(nombreColumna + "Format") + ""));
									value = FechaUtil.obtenerFechaFormatoPersonalizado((Date)valueDate, propiedadesMap.get(nombreColumna + "Format") + "");
								}
								cellDetalle.setCellStyle(cellDateStyle);
								cellDetalle.getCellStyle().setLocked(false);
								posicionCellCabecera = posicionCellCabecera + incremetoCellCabecera;
							} else {
								SXSSFCell cellDetalle = (SXSSFCell) filaDet.createCell(posicionCellCabecera);
								//
								for (ExcelHederTitleVO excelHederTitleVO : listaTituloFinal) {
									if(excelHederTitleVO.getBg()>1) {
										cellDetalle.setCellStyle(titleStyleVar);
									}else if (excelHederTitleVO.isWrapText()==true) {
										cellDetalle.setCellStyle(titleStyleVar);
									}
								}
								//
								cellDetalle.setCellValue(value == null ? "" : value.toString());		
								cellDetalle.getCellStyle().setLocked(false);
								posicionCellCabecera = posicionCellCabecera + incremetoCellCabecera;
							}
						} else {
							SXSSFCell cellDetalle = (SXSSFCell) filaDet.createCell(posicionCellCabecera);			
							if (nombreColumna.contains(IS_FORMULA)) {		
								cellDetalle.setCellType(CellType.FORMULA);
								cellDetalle.getCellStyle().setLocked(true);
								String[] nombreColumnaCalc  = nombreColumna.split("=>",-1);
								String formula = nombreColumnaCalc[1];
								formula = formula.replace("${N}", "" + (filaDet.getRowNum() + 1));
								cellDetalle.setCellFormula(formula);
								 //evaluator.evaluateInCell(cellDetalle);
							} else {
								if (value != null && value instanceof Number) {
									cellDetalle.setCellValue(Double.parseDouble(value.toString()));							
								} else { 
									cellDetalle.setCellValue(value == null ? "" : value.toString());
								}
								cellDetalle.getCellStyle().setLocked(false);	
								cellDetalle.setCellStyle(titleStyleVar);
						
							}
							posicionCellCabecera = posicionCellCabecera + incremetoCellCabecera;
						}
						if (calcularWitchDemanda) {
							int widtMaxActual =  ObjectUtil.objectToString(cellHeaderVO.getNameHeader()).length();
							double porcentaje = 0.20;
							if (!columnWidtMaxMap.containsKey(columnIndex)) {
								widtMaxActual = obtenerWidt(widtMaxActual, porcentaje);
								columnWidtMaxMap.put(columnIndex, widtMaxActual);
							} 
							int widtMax = columnWidtMaxMap.get(columnIndex);
							widtMaxActual =  ObjectUtil.objectToString(value).length();
							widtMaxActual = obtenerWidt(widtMaxActual, porcentaje);
							if (widtMax < widtMaxActual) {
								columnWidtMaxMap.put(columnIndex, widtMaxActual);
							}
						}
						columnIndex++;
					}					
					
					contador++;
					int posicionCeldaData = 0; 
					for (ExcelHederDataVO cellHeaderVO : listaHeaderData) {
						try {
							if (cellHeaderVO.getCantidadAgrupar()  > 0) {
								CellRangeAddress cellRangeAddress = new CellRangeAddress(i + primeraFila, i + primeraFila, posicionCeldaData,((posicionCeldaData) - 1) + cellHeaderVO.getCantidadAgrupar().intValue());
								sheet.addMergedRegion(cellRangeAddress);
								//generarMergeRegionBorder(cellRangeAddress, sheet);
							}
						} catch (Exception e) {
							log.error(e);
						}
						posicionCeldaData++;
					}
					
					if(listaHeaderData.size()>0) {
						i++;
					}
					
				}			
				//Inicio escribir pie de pagina
				if (!CollectionUtil.isEmpty(listaTituloFinal)) {
					for (ExcelHederTitleVO excelHederTitleVO : listaTituloFinal) {
						if (excelHederTitleVO.isEsPiePagina()) {
							//excelHederTitleVO.setPosicionRow(posicionRow);
							int posicionRowVar =  excelHederTitleVO.getPosicionRow();
							int posicionCeldaVar =  excelHederTitleVO.getPosicionCelda();
							if (posicionRowVar > 0) {
								posicionRowVar = posicionRowVar - 1;
							}
							if (posicionCeldaVar > 0) {
								posicionCeldaVar = posicionCeldaVar - 1;
							}
							if (posicionRowVar > maxPosicionRow) {
								maxPosicionRow = posicionRowVar;
							}
							SXSSFRow  filaTitle = (SXSSFRow)sheet.getRow(posicionRowVar);
					    	if (filaTitle == null) {
					    	  	filaTitle = (SXSSFRow)sheet.createRow(posicionRowVar);
					    	}
							String tituloFinalPer = excelHederTitleVO.getNameHeader();
							SXSSFCell  heraderTitleCell = null;
							if (posicionCeldaVar > 0) {
								heraderTitleCell = (SXSSFCell)filaTitle.createCell(posicionCeldaVar);
							} else {
								heraderTitleCell = (SXSSFCell)filaTitle.createCell(0);
							}
							heraderTitleCell.setCellValue(tituloFinalPer);
							
							CellStyle titleStyleVar = generarStyleTitlePie(workbook,excelHederTitleVO.getFontHeightInPoints());
							heraderTitleCell.setCellStyle(titleStyleVar);
							heraderTitleCell.getCellStyle().setAlignment(HorizontalAlignment.forInt(excelHederTitleVO.getAling()));
							
							if (excelHederTitleVO.getVerticalAlignment() > -1) {
								heraderTitleCell.getCellStyle().setVerticalAlignment(VerticalAlignment.forInt(excelHederTitleVO.getVerticalAlignment()));
							}
							heraderTitleCell.getCellStyle().setWrapText(excelHederTitleVO.isWrapText());
					
							excelHederTitleVO.setPosicionRow(posicionRowVar);
							excelHederTitleVO.setPosicionCelda(posicionCeldaVar);
							if (excelHederTitleVO.getRotacion() != 0) {
								heraderTitleCell.getCellStyle().setRotation((short)excelHederTitleVO.getRotacion() );
							}
							//posicionRow = posicionRow + incrementroRow;
							if (excelHederTitleVO.getColumnIndex() > -1 && excelHederTitleVO.getWidth() > -1) {
							//	columnWidtMaxMap.put(excelHederTitleVO.getColumnIndex(), excelHederTitleVO.getWidth());
							}
						}
					}
					//posicionRow = maxPosicionRow + posicionRow + incrementroRow;
				}
				for (ExcelHederTitleVO excelHederTitleVO : listaTituloFinal) {
					if (excelHederTitleVO.isEsPiePagina()) {
						try {
							//int firstRow, int lastRow, int firstCol, int lastCol
							CellRangeAddress cellRangeAddress = null;
							if (excelHederTitleVO.getCantidadAgrupar() > 0 && excelHederTitleVO.getCantidadAgruparHorizontal() == 0) {
								cellRangeAddress = new CellRangeAddress(excelHederTitleVO.getPosicionRow().intValue(), excelHederTitleVO.getPosicionRow().intValue(), excelHederTitleVO.getPosicionCelda().intValue(),((excelHederTitleVO.getPosicionCelda().intValue()) - 1) + excelHederTitleVO.getCantidadAgrupar().intValue());
								sheet.addMergedRegion(cellRangeAddress);
								generarMergeRegionBorderPie(cellRangeAddress, sheet);
							}
							if (excelHederTitleVO.getCantidadAgruparHorizontal() > 0 && excelHederTitleVO.getCantidadAgrupar()  == 0) {
								cellRangeAddress = new CellRangeAddress(excelHederTitleVO.getPosicionRow().intValue(), ((excelHederTitleVO.getPosicionRow().intValue() - 1) + excelHederTitleVO.getCantidadAgruparHorizontal().intValue()), excelHederTitleVO.getPosicionCelda().intValue(),excelHederTitleVO.getPosicionCelda().intValue());
								sheet.addMergedRegion(cellRangeAddress);
								generarMergeRegionBorderPie(cellRangeAddress, sheet);
							}
							if (excelHederTitleVO.getCantidadAgruparHorizontal() > 0 && excelHederTitleVO.getCantidadAgrupar()  > 0) {
								cellRangeAddress = new CellRangeAddress(excelHederTitleVO.getPosicionRow().intValue(), ((excelHederTitleVO.getPosicionRow().intValue() - 1) + excelHederTitleVO.getCantidadAgruparHorizontal().intValue()), excelHederTitleVO.getPosicionCelda().intValue(),((excelHederTitleVO.getPosicionCelda().intValue()) - 1) + excelHederTitleVO.getCantidadAgrupar().intValue());
								sheet.addMergedRegion(cellRangeAddress);
								generarMergeRegionBorderPie(cellRangeAddress, sheet);
							}
						} catch (Exception e) {
							log.error(e);
						}
					}					
				}
				//Fin escrubir pie de pagina
				// Inicio agregar combo
				if (isCombo) {
					int hoja = 0;
					int cantidadRegistros = 100;
					if (listaData != null && listaData.size() > 0) {
						cantidadRegistros = listaData.size();
					}
					List<ExcelComboDataVO> listaDataCombo = (List<ExcelComboDataVO>) propiedadesMap.get("comboData");
					if (listaDataCombo == null) {
						listaDataCombo = new ArrayList<ExcelComboDataVO>();
					}
					for (ExcelComboDataVO excelComboDataVO : listaDataCombo) {
						String nombreColumna = excelComboDataVO.getNombreCampo();
						XSSFName namedCell = (XSSFName) workbook.createName();
						namedCell.setNameName("hidden" + hoja);
						namedCell.setRefersToFormula("hidden" + hoja + "!$A$1:$A$" + excelComboDataVO.getListaExcelComboData().size());

						DataValidationHelper dvHelper = sheet.getDataValidationHelper();
						DataValidationConstraint dataValidation = dvHelper.createFormulaListConstraint("hidden" + hoja);
						CellRangeAddressList addressList = new CellRangeAddressList(posicionRow, cantidadRegistros, campoPosicionMap.get(nombreColumna), campoPosicionMap.get(nombreColumna));
						DataValidation validation = dvHelper.createValidation(dataValidation,addressList );
						validation.setSuppressDropDownArrow(true);
						validation.setEmptyCellAllowed(true);
						validation.setShowPromptBox(true);
						validation.createErrorBox("Mensaje", "Elemento no válido");
						sheet.addValidationData(validation);
						hoja++;
					}
					propiedadesMap.remove("comboData");// limpiando data
				}
				// fin agregar combo
				int autoSizeColunm = 0;// 2
				int incrementoSize = 1;
				for (int ih = 0; ih < listaHeaderData.size(); ih++) {
					if (calcularWitchDemanda) {
						try {
							int  width = columnWidtMaxMap.get(autoSizeColunm);
							 width *= 256;
					         int maxColumnWidth = 255 * 256; // The maximum column width for an individual cell is 255 characters
					         if (width > maxColumnWidth) {
					             width = maxColumnWidth;
					         }
							sheet.setColumnWidth(autoSizeColunm, width);
						} catch (Exception e) {
							//log.error("ERROR autoSizeColunm -->" + autoSizeColunm);
						}
					} else {
						sheet.autoSizeColumn(autoSizeColunm, true);
					}
					autoSizeColunm = autoSizeColunm + incrementoSize;
				}
			}
			boolean anexarHojaProcesar = false;
			 if (isCombo) {
				 if (anexarHojaExistente) {
					 anexarHojaProcesar = true;
				 } 
				 hojaActiva++;
			 } else {
				 if (anexarHojaExistente) {
					 anexarHojaProcesar = true;
				 }
			 }
			 if (anexarHojaProcesar) {
				 String nombreArchivo = (String) propiedadesMap.get("nombreArchivo");
				 int anexarHojaPosition = (Integer) propiedadesMap.get("anexarHojaPosition");
				 File rutaArchivo = new File(ConstanteConfigUtil.RUTA_GENERAL_TEMPLANTE + nombreArchivo);
				 XSSFWorkbook HSSFWorkbookAnexar =  ExcelUtil.leerExcelXlsx(rutaArchivo );
				 XSSFSheet sheetAnexar = HSSFWorkbookAnexar.getSheetAt(anexarHojaPosition - 1);
				 if (sheetAnexar != null) {
					 SXSSFSheet sheet = workbook.createSheet(sheetAnexar.getSheetName());
					 sheet.setRandomAccessWindowSize(100);// keep 100 rows in memory, exceeding rows will be flushed to disk
					 TransferUtilExcel.copySheetsXLSX(sheet, sheetAnexar);
				 }
			 }
			//workbook.setActiveSheet(hojaActiva - 1);			
			if (isFormula) {
				try {
					XSSFFormulaEvaluator.evaluateAllFormulaCells(workbook);
					//workbook.setForceFormulaRecalculation(true);
				} catch (Exception e) {
					log.error(e);
				}
			}
			FileOutputStream out = new FileOutputStream(RUTA_RECURSOS_BYTE_BUFFER + "" + archivoName + ".xlsx");
			workbook.write(out);
			workbook.dispose();
			out.close();
			cellDateStyle = null;
			workbook = null;
			out = null;
			ExcelUtil.defaultLocaleProcess();
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			resultado = null;
		}
		return resultado;
	}
	
 	public static void generarExcelXLSXMap(List<String> listaHeader,Map<String,String> listaHeaderOverrideMap, List<Map<String, Object>> listaDataMap, String archivoName, String titulo, Map<String, Object> propiedadesMap, SXSSFWorkbook workbook) {
		try {
			Map<Integer,Integer> columnWidtMaxMap = new HashMap<Integer, Integer>();
			boolean calcularWitchDemanda = propiedadesMap.containsKey("calcularWitchDemanda");
			// Inicio Agregar coombo
			Map<String, Integer> campoPosicionMap = new HashMap<String, Integer>();
			boolean isCombo = propiedadesMap.containsKey("comboData");
			int posicionCellCabecera = 0;
			if (isCombo) {
				for (String cellHeaderVO : listaHeader) {
					String nombreColumna = cellHeaderVO;
					if (!campoPosicionMap.containsKey(nombreColumna)) {
						campoPosicionMap.put(nombreColumna, posicionCellCabecera);
					}
					posicionCellCabecera++;
				}
			}
			// Fin Agregar coombo
			boolean isWorkbookNull = false;
			if (workbook == null) {
				isWorkbookNull = true;
				workbook = new SXSSFWorkbook(100);
			}
			if (propiedadesMap.containsKey("writeExcel") || isWorkbookNull) {
					File archivoXLS = new File(RUTA_RECURSOS_BYTE_BUFFER);
					if (!archivoXLS.isFile()) {
						archivoXLS.mkdirs();
					}
			}
			workbook.setCompressTempFiles(true); // temp files will be gzipped

			int cantidadData = listaDataMap.size();
			int cantidadHojas = 1;
			if (cantidadData > MAXIMO_RANGE_EXCEL_XLSX) {
				BigDecimal bCantidadData = new BigDecimal(cantidadData);
				BigDecimal maxRange = new BigDecimal(MAXIMO_RANGE_EXCEL_XLSX);
				BigDecimal bCantidadHojas = bCantidadData.divide(maxRange, 2, BigDecimal.ROUND_UP);
				bCantidadHojas = bCantidadHojas.setScale(0, BigDecimal.ROUND_UP);
				cantidadHojas = bCantidadHojas.intValue();
			}
			CellStyle cellDateStyle = generarStyleDate(workbook);
			CellStyle titleStyle = generarStyleTitle(workbook,(short) 9);
			log.info("generarExcelObjectMapBigMemory.cantidadHojas --> " + cantidadHojas);
			for (int cantidadDataPaginadorHoja = 1; cantidadDataPaginadorHoja <= cantidadHojas; cantidadDataPaginadorHoja++) {
				String tituloFinal = titulo;
				if (propiedadesMap.containsKey("hojaName")) {
					tituloFinal = propiedadesMap.get("hojaName") + "";
				}
				if (cantidadDataPaginadorHoja > 1) {
					tituloFinal = cantidadDataPaginadorHoja + tituloFinal;
				}
				SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet(tituloFinal);
				int posicionRow = 0; //aca coloco el numero de row donde me he quedado
				int incrementroRow = 1;
				
				if (propiedadesMap.containsKey("printTitleView")) {
					SXSSFRow filaTitle = (SXSSFRow) sheet.createRow(posicionRow);
					SXSSFCell heraderTitleCell = (SXSSFCell) filaTitle.createCell(0);
					heraderTitleCell.setCellValue(titulo);
					heraderTitleCell.setCellStyle(titleStyle);
					if (listaHeader.size() > 1) {
						sheet.addMergedRegion(new CellRangeAddress(posicionRow, posicionRow, 0, listaHeader.size() - 1));
					}
					posicionRow = posicionRow + incrementroRow;
				}
				
				// creando cabecera del datos
				if (propiedadesMap.containsKey("rowInicio")) {
					posicionRow = Integer.parseInt(propiedadesMap.get("rowInicio") + "") - 1;
				}
				SXSSFRow fila = (SXSSFRow) sheet.createRow(posicionRow);
				sheet.createFreezePane(0, posicionRow + 1);
				posicionCellCabecera = 0;
				int incremetoCellCabecera = 1; 
				int columnIndex = 0;
				for (String cellHeaderTemp : listaHeader) {
					String cellHeader = listaHeaderOverrideMap.containsKey(cellHeaderTemp) ?  listaHeaderOverrideMap.get(cellHeaderTemp) : cellHeaderTemp;
					SXSSFCell heraderCell = (SXSSFCell) fila.createCell(posicionCellCabecera);
					heraderCell.setCellValue(cellHeader);
					heraderCell.setCellStyle(titleStyle);
					posicionCellCabecera = posicionCellCabecera + incremetoCellCabecera;
					
					if (calcularWitchDemanda) {
						int widtMaxActual =  ObjectUtil.objectToString(cellHeader).length();
						double porcentaje = 0.20;
						if (!columnWidtMaxMap.containsKey(columnIndex)) {
							widtMaxActual = obtenerWidt(widtMaxActual, porcentaje);
							columnWidtMaxMap.put(columnIndex, widtMaxActual);
						}
					}
					columnIndex++;
					
				}
				posicionRow = posicionRow + incrementroRow;
				int primeraFila = posicionRow;
				int i = 0;
				int fromIndex = fromIndexXlsx(cantidadDataPaginadorHoja);
				int toIndex = toIndexXlsx(cantidadData, cantidadDataPaginadorHoja);
				for (Map<String, Object> dataMap : listaDataMap.subList(fromIndex, toIndex)) {
					SXSSFRow filaDet = (SXSSFRow) sheet.createRow(i + primeraFila);
					posicionCellCabecera = 0;
					incremetoCellCabecera = 1;
					columnIndex = 0;
					for (String headerKey : listaHeader) {
						Object value = null;
						if (propiedadesMap.containsKey(headerKey)) {
							value = dataMap.get(headerKey);
							if (StringUtils.isNullOrEmpty(value)) {
								value = 0;
							}
						} else {
							value = dataMap.get(headerKey);
						}
						if (esFechaData(value)) {
							SXSSFCell cellDetalle = (SXSSFCell) filaDet.createCell(posicionCellCabecera);
							cellDetalle.setCellValue((Date) value);
							cellDetalle.setCellStyle(cellDateStyle);
							posicionCellCabecera = posicionCellCabecera + incremetoCellCabecera;
						} else {
							if (propiedadesMap != null && propiedadesMap.containsKey(headerKey + "Numeric")) {
								SXSSFCell cellDetalle = (SXSSFCell) filaDet.createCell(posicionCellCabecera);
								if (!StringUtils.isNullOrEmptyNumeric(value)) {
									cellDetalle.setCellValue(Double.valueOf(value.toString()));
								} else {
									cellDetalle.setCellValue(value == null ? "" : value.toString());
								}
								posicionCellCabecera = posicionCellCabecera + incremetoCellCabecera;
							} else {
								SXSSFCell cellDetalle = (SXSSFCell) filaDet.createCell(posicionCellCabecera);
								if (esNumericoData(value)) {
									cellDetalle.setCellValue(Double.valueOf(value.toString()));
								} else {
									cellDetalle.setCellValue(value == null ? "" : value.toString());
								}
								posicionCellCabecera = posicionCellCabecera + incremetoCellCabecera;
							}
						}
						if (calcularWitchDemanda) {
							int widtMaxActual = ObjectUtil.objectToString(headerKey).length();
							double porcentaje = 0.20;
							if (!columnWidtMaxMap.containsKey(columnIndex)) {
								widtMaxActual = obtenerWidt(widtMaxActual, porcentaje);
								columnWidtMaxMap.put(columnIndex, widtMaxActual);
							}
							int widtMax = columnWidtMaxMap.get(columnIndex);
							widtMaxActual = ObjectUtil.objectToString(value).length();
							widtMaxActual = obtenerWidt(widtMaxActual, porcentaje);
							if (widtMax < widtMaxActual) {
								columnWidtMaxMap.put(columnIndex, widtMaxActual);
							}
						}
						columnIndex++;
					}
					i++;
					dataMap = null;
				} 
				// Inicio agregar combo
				if (isCombo) {
					int hoja = 0;
					int cantidadRegistros = CANTIDAD_REGISTROS_COMBO;
					if (listaDataMap != null && listaDataMap.size() > 0) {
						cantidadRegistros = listaDataMap.size();
					}
					List<ExcelComboDataVO> listaDataCombo = (List<ExcelComboDataVO>) propiedadesMap.get("comboData");
					if (listaDataCombo == null) {
						listaDataCombo = new ArrayList<ExcelComboDataVO>();
					}
					for (ExcelComboDataVO excelComboDataVO : listaDataCombo) {
						String nombreColumna = excelComboDataVO.getNombreCampo();
						XSSFName namedCell = (XSSFName) workbook.createName();
						namedCell.setNameName("hidden" + hoja);
						namedCell.setRefersToFormula("hidden" + hoja + "!$A$1:$A$" + excelComboDataVO.getListaExcelComboData().size());

						DataValidationHelper dvHelper = sheet.getDataValidationHelper();
						DataValidationConstraint dataValidation = dvHelper.createFormulaListConstraint("hidden" + hoja);
						CellRangeAddressList addressList = new CellRangeAddressList(posicionRow, cantidadRegistros, campoPosicionMap.get(nombreColumna), campoPosicionMap.get(nombreColumna));
						DataValidation validation = dvHelper.createValidation(dataValidation,addressList );

						validation.setSuppressDropDownArrow(true);
						validation.setEmptyCellAllowed(true);
						validation.setShowPromptBox(true);
						validation.createErrorBox("Mensaje", "Elemento no válido");

						sheet.addValidationData(validation);
						hoja++;
					}
					propiedadesMap.remove("comboData");// limpiando data
				}
				// fin agregar combo
				int autoSizeColunm = 0;// 2
				int incrementoSize = 1;
				for (int ih = 0; ih < listaHeader.size(); ih++) {
					if (calcularWitchDemanda) {
						try {
							int width = columnWidtMaxMap.get(autoSizeColunm);
							width *= 256;
							int maxColumnWidth = 255 * 256; // The maximum column width for an individual cell is 255 characters
							if (width > maxColumnWidth) {
								width = maxColumnWidth;
							}
							sheet.setColumnWidth(autoSizeColunm, width);
						} catch (Exception e) {
							// log.error("ERROR autoSizeColunm -->" +
							// autoSizeColunm);
						}
					} else {
						sheet.autoSizeColumn(autoSizeColunm, true);
					}
					autoSizeColunm = autoSizeColunm + incrementoSize;
				}
			}
			listaDataMap = null;
			if (propiedadesMap.containsKey("writeExcel") || isWorkbookNull) {
				FileOutputStream out = new FileOutputStream(RUTA_RECURSOS_BYTE_BUFFER + "" + archivoName + ".xlsx");
				workbook.write(out);
				workbook.dispose();
				out.close();
				cellDateStyle = null;
				workbook = null;
				out = null;
				ExcelUtil.defaultLocaleProcess();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		}
	}
	
	
	private static CellStyle generarStyleDate(SXSSFWorkbook workbook) {
		DataFormat format = workbook.createDataFormat();
		CellStyle cellDateStyle = workbook.createCellStyle();
		cellDateStyle = generarStyleDate(cellDateStyle, format);
		return cellDateStyle;
	}
	
	private static CellStyle generarStyleDate(CellStyle cellDateStyle,DataFormat format) {
		cellDateStyle.setDataFormat(format.getFormat(DD_MM_YYY_HH_MM_SS));
		return cellDateStyle;
	}
	
	private static CellStyle generarStyleTitle(SXSSFWorkbook workbook, short fontHeightInPoints) {
		Font titleFont = generarTitleFont(workbook,fontHeightInPoints);
		CellStyle titleStyle = workbook.createCellStyle();
		titleStyle = generarStyleTitle(titleStyle,titleFont);
		return titleStyle;
	}
	private static CellStyle generarStyleTitlePie(SXSSFWorkbook workbook, short fontHeightInPoints) {
		Font titleFont = generarTitleFontPie(workbook,fontHeightInPoints);
		CellStyle titleStyle = workbook.createCellStyle();
		titleStyle = generarStyleTitlePie(titleStyle,titleFont);
		return titleStyle;
	}
	private static CellStyle generarStyleTitle(CellStyle titleStyle,Font titleFont) {
		titleStyle.setFont(titleFont);
		titleStyle.setBorderTop(BorderStyle.THIN);
		titleStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
		titleStyle.setBorderRight(BorderStyle.THIN);
		titleStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		titleStyle.setBorderBottom(BorderStyle.THIN);
		titleStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		titleStyle.setBorderLeft(BorderStyle.THIN);
		titleStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		return titleStyle;
	}
	
	//aggregado
	private static CellStyle generarStyleTitle(SXSSFWorkbook workbook) {
		CellStyle titleStyle = workbook.createCellStyle();
		titleStyle = generarStyleTitle(titleStyle);
		return titleStyle;
	}
	
	//aggregado
	private static CellStyle generarStyleTitle(CellStyle titleStyle) {
		titleStyle.setBorderTop(BorderStyle.THIN);
		titleStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
		titleStyle.setBorderRight(BorderStyle.THIN);
		titleStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		titleStyle.setBorderBottom(BorderStyle.THIN);
		titleStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		titleStyle.setBorderLeft(BorderStyle.THIN);
		titleStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		//titleStyle.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
		//titleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		return titleStyle;
	}
	
	private static CellStyle generarStyleTitlePie(CellStyle titleStyle,Font titleFont) {
		titleStyle.setFont(titleFont);
		/*titleStyle.setBorderTop(BorderStyle.THIN);
		titleStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
		titleStyle.setBorderRight(BorderStyle.THIN);
		titleStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		titleStyle.setBorderBottom(BorderStyle.THIN);
		titleStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		titleStyle.setBorderLeft(BorderStyle.THIN);
		titleStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());*/
		return titleStyle;
	}
	
	private static Font generarTitleFont(Font titleFont,short fontHeightInPoints) {
		titleFont.setFontName(NOMBRE_LETRA);
		titleFont.setFontHeightInPoints((short) fontHeightInPoints);
		titleFont.setBold(true);
		titleFont.setColor(HSSFColorPredefined.BLACK.getIndex());
		return titleFont;
	}
	private static Font generarTitleFontPie(Font titleFont,short fontHeightInPoints) {
		titleFont.setFontName(NOMBRE_LETRA);
		titleFont.setFontHeightInPoints((short) fontHeightInPoints);
		titleFont.setBold(true);
		//titleFont.setColor(HSSFColorPredefined.BLACK.getIndex());
		return titleFont;
	}
	private static Font generarTitleFont(SXSSFWorkbook workbook, short fontHeightInPoints) {
		Font titleFont = workbook.createFont();
		titleFont = generarTitleFont(titleFont,fontHeightInPoints);
		return titleFont;
	}
	private static Font generarTitleFontPie(SXSSFWorkbook workbook, short fontHeightInPoints) {
		Font titleFont = workbook.createFont();
		titleFont = generarTitleFontPie(titleFont,fontHeightInPoints);
		return titleFont;
	}
	
	private static SXSSFWorkbook generarComboHojaXLSX(SXSSFWorkbook workbook , Map<String,Object> propiedadesMap) {
		List<ExcelComboDataVO> listaData = (List<ExcelComboDataVO>)propiedadesMap.get("comboData");
		int hoja = 0;
		for (ExcelComboDataVO excelComboDataVO : listaData) {
			 SXSSFSheet hidden = (SXSSFSheet) workbook.createSheet("hidden" + hoja);
			 hidden.setRandomAccessWindowSize(100);// keep 100 rows in memory, exceeding rows will be flushed to disk
			int i  = 0;
			for (String dataCombo : excelComboDataVO.getListaExcelComboData()) {
				Row row = hidden.createRow(i);
				Cell cell = row.createCell(0);
			    cell.setCellValue(dataCombo);
			    i++;
			 }
			for (int ih = 0;ih < listaData.size(); ih++) {
				hidden.autoSizeColumn(ih,true);
			}
			workbook.setSheetHidden(hoja, true);
			 hoja++;
		}
		return workbook;
	} 
	
	private static void generarMergeRegionBorder(CellRangeAddress range, Sheet sheet) {
		RegionUtil.setBorderBottom(BorderStyle.THIN, range, sheet);
		RegionUtil.setBorderTop(BorderStyle.THIN, range, sheet);
		RegionUtil.setBorderLeft(BorderStyle.THIN, range, sheet);
		RegionUtil.setBorderRight(BorderStyle.THIN, range, sheet);
		RegionUtil.setBorderRight(BorderStyle.THIN, range, sheet);
	}
	
	private static void generarMergeRegionBorderPie(CellRangeAddress range, Sheet sheet) {
		//RegionUtil.setBorderBottom(BorderStyle.THIN, range, sheet);
		//RegionUtil.setBorderTop(BorderStyle.THIN, range, sheet);
		//RegionUtil.setBorderLeft(BorderStyle.THIN, range, sheet);
		//RegionUtil.setBorderRight(BorderStyle.THIN, range, sheet);
		//RegionUtil.setBorderRight(BorderStyle.THIN, range, sheet);
	}
		
		

    private static int fromIndexXlsx(Integer dataPaginator) {
  	   int pagina = 0; 
         if (dataPaginator == null) {
             pagina = 1;
         } else {
             pagina = dataPaginator;                
         }    
  	   int fromIndex = ((pagina - 1) * (MAXIMO_RANGE_EXCEL_XLSX - CANTIDAD_FILAS_USADO_CABECERA));//
  	   return fromIndex;
     }
    
    /**
     * To index.
     *
     * @param cantidadTotalData el cantidad total data
     * @param dataPaginator el data paginator
     * @return the int
     */
    private static int toIndexXlsx(int cantidadTotalData,Integer dataPaginator) {
  	   int pagina = 0; 
         if (dataPaginator == null) {
             pagina = 1;
         } else {
             pagina = dataPaginator;                
         }  
  	   int toIndex = ((pagina - 1) * (MAXIMO_RANGE_EXCEL_XLSX - CANTIDAD_FILAS_USADO_CABECERA)) + (MAXIMO_RANGE_EXCEL_XLSX - CANTIDAD_FILAS_USADO_CABECERA);
         if (toIndex > cantidadTotalData) {
             toIndex = cantidadTotalData;
         }
         return toIndex;
     }
	/**
	 * Es fecha.
	 *
	 * @param columnaName el columna name
	 * @return true, en caso de exito
	 */
	private static boolean esFecha(String columnaName) {
		boolean resultado = false;
		if (columnaName.toUpperCase().contains("fecha".toUpperCase())) {
			resultado = true;
		}
		return resultado;
	}
	
	/**
	 * Es fecha data.
	 *
	 * @param valueDate el value date
	 * @return true, en caso de exito
	 */
	private static boolean esFechaData(Object valueDate) {
		boolean resultado = false;
		if (valueDate != null && (valueDate.getClass().isAssignableFrom(Date.class)
				|| valueDate.getClass().isAssignableFrom(java.sql.Timestamp.class) )) {
			resultado = true;
		}
		return resultado;
	}
	/**
	 * Verificar fornato fecha.
	 *
	 * @param columnaName el columna name
	 * @param value el value
	 * @return the object
	 */
	private static Object verificarFornatoFecha(String columnaName,Object value) {
		Object resultado = value;
		if (esFecha(columnaName)) {
			try {
				Date date = FechaUtil.obtenerFechaFormatoCompleto(value.toString());
				resultado = date;
			} catch (Exception e) {
				 resultado = value;
			}
		} else {
			resultado = value;
		}
		return resultado;
	}
	/**
	 * Atributo value complejo.
	 *
	 * @param object el object
	 * @param nombreColumna el nombre columna
	 * @return the object
	 */
	private  static Object atributoValueComplejo(Object object, String nombreColumna) {
		Object resultado = null;
		String nombreColumnaReplace = nombreColumna.replace(".", ":");
	 	String[] objeto = nombreColumnaReplace.split(":");
	 	int cantidadPropiedad = objeto.length;
		if (cantidadPropiedad == 1) {				
			resultado = getValue(object, nombreColumna);
		}
		if (cantidadPropiedad > 1) {
				String propertyName = objeto[cantidadPropiedad - 1];
				Object object2 = object;
				for (String string : objeto) {
					if (!string.equals(propertyName)) {
						object2 = getValue(object2, string);
					}
				}
				resultado = atributoValueComplejo(object2, propertyName);
		}
		
		return resultado;
	}
	
	/**
	 * Obtiene value.
	 *
	 * @param object el object
	 * @param nombreColumna el nombre columna
	 * @return value
	 */
	public static Object getValue(Object object,String nombreColumna) {
		Object resultado = null;
		try {
			BeanMap beanMap = new BeanMap(object);
			resultado = beanMap.get(nombreColumna);
		} catch (Exception e) {
			resultado = null;
		}
		
		return resultado;
	}
	/**
	 * Obtener nombre.
	 *
	 * @param nombreArchivo el nombre archivo
	 * @return the string
	 */
	public static String obtenerNombrePath(String nombreArchivo) {
		SimpleDateFormat	sdf = new SimpleDateFormat("_yyyyMMdd_HHmmss_SSS");
		StringBuilder		sbNombreArchivo = new StringBuilder();
		//sbNombreArchivo.append(ConstantesUtil.DIRECTORIO_UPLOADS);
		String				extension = obtenerExtension( nombreArchivo );
		
		sbNombreArchivo.append( obtenerNombre( nombreArchivo ) );
		sbNombreArchivo.append( sdf.format(new Date()) );
		if (null != extension && extension.length() > 0 ) {
			sbNombreArchivo.append( "." + extension );
		}
		
		return 	sbNombreArchivo.toString();
	}
	  
  	/**
  	 * Obtener nombre.
  	 *
  	 * @param fileName el file name
  	 * @return the string
  	 */
  	public static  String	obtenerNombre(String fileName) {
	    	int 		pos = fileName.lastIndexOf(".");
			String 		nombre = "";
			if (pos >= 0) {
				nombre = fileName.substring(0, pos);
			} else {
				nombre = fileName;
			}
	    	return nombre;
	    }
	  
	  /**
  	 * Obtener extension.
  	 *
  	 * @param fileName el file name
  	 * @return the string
  	 */
  	public static  String obtenerExtension(String fileName) {
	    	int 	pos = fileName.lastIndexOf(".");
			String 	extension = "";
			if (pos >= 0) {
				extension = fileName.substring(pos + 1);
			}
	    	return extension;
	}


	
	/**
	 * Obtener widt.
	 *
	 * @param widtMaxActual el widt max actual
	 * @param porcentaje el porcentaje
	 * @return the integer
	 */
	private static Integer obtenerWidt(int widtMaxActual, double porcentaje) {
		return (new BigDecimal(widtMaxActual + (widtMaxActual * porcentaje)).setScale(0, RoundingMode.HALF_UP).intValue());
	}

	private static boolean esNumericoData(Object value) {
		boolean	resultado =  false;
		if (value == null ) {
			return resultado;
		}
		if (value instanceof Number ) {
		 resultado =  true;
		} 
		 return resultado;
	}
	
	
}
