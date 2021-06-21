package pe.com.edu.siaa.core.ejb.factory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;

import pe.com.edu.siaa.core.ejb.service.util.FechaUtil;
import pe.com.edu.siaa.core.ejb.util.log.Logger;
import pe.com.edu.siaa.core.model.dto.ConfiguracionTramaDetalleDTO;
import pe.com.edu.siaa.core.model.jdbc.vo.ScriptSqlResulJDBCVO;
import pe.com.edu.siaa.core.model.util.ConstanteConfiguracionTramaUtil;
import pe.com.edu.siaa.core.model.util.StringUtils;
import pe.com.edu.siaa.core.model.vo.AtributoEntityVO;
import pe.com.edu.siaa.core.model.vo.ValueDataVO;

/**
 * La Class TransferDataObjectUtil.
 * <ul>
 * <li>Copyright 2017 ndavilal-
 * mapfre. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Tue Apr 29 17:13:19 COT 2017
 * @since mytron v1.0
 */
public class TransferDataObjectUtil  extends TransferDataObjectValidarUtil  implements Serializable {


	/** La Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

		/** El log. */
	private static Logger log = Logger.getLogger(TransferDataObjectUtil.class);

	/**
	 * Instancia un nuevo data export excel.
	 */
	public TransferDataObjectUtil() {
		
	}
	
	public static <T> T  transferObjetoEntityDTOPK(Object ressul,Class<T> entityClassDTO,String... entityClasess) {
		return transferObjetoEntityDTO(ressul, entityClassDTO,true, entityClasess);
	}
	
	public static <T> T  transferObjetoEntityDTO(Object ressul,Class<T> entityClassDTO,String... entityClasess) {
		return transferObjetoEntityDTO(ressul, entityClassDTO,false, entityClasess);
	}
	/**
	 * Transfer objeto entity dto.
	 *
	 * @param <T> el tipo generico
	 * @param ressul el ressul
	 * @param entityClassDTO el entity class dto
	 * @return the t
	 */
	public static <T> T  transferObjetoEntityDTO(Object ressul,Class<T> entityClassDTO,boolean esPK, String... entityClasess) {
		String className = "";
		try {
			if (ressul == null) {
				return null;
			}
			T resultado = entityClassDTO.newInstance();
			className = ressul.getClass().getName();
			String handlerHibernate = obtenerHandlerHibernate(className);
			if (className.contains(handlerHibernate)) {
				int indexOf = className.indexOf(handlerHibernate);
				className =  className.substring(0,indexOf);
				 Hibernate.initialize(ressul);
				 if (ressul instanceof HibernateProxy) {
				    ressul = ((HibernateProxy) ressul).getHibernateLazyInitializer().getImplementation();
				 }
			}
			List<AtributoEntityVO> listaAtributos = AtributosEntityCacheUtil.getInstance().obtenerListaAtributos(className);
			for (AtributoEntityVO objAtr : listaAtributos) {
				if (esPK) {
					if (objAtr.isColumn() && objAtr.isEsPK() ) {
						Field f = resultado.getClass().getDeclaredField(objAtr.getNombreAtributo());
						f.setAccessible(true);
						Field fValue = ressul.getClass().getDeclaredField(objAtr.getNombreAtributo());
						fValue.setAccessible(true);
						Object value = fValue.get(ressul);
						if (value != null) {
							f.set(resultado, value) ;
						}
						break;
					}
				} else {
					if (objAtr.isColumn()) {
						Field f = resultado.getClass().getDeclaredField(objAtr.getNombreAtributo());
						f.setAccessible(true);
						Field fValue = ressul.getClass().getDeclaredField(objAtr.getNombreAtributo());
						fValue.setAccessible(true);
						Object value = fValue.get(ressul);
						if (value != null) {
							f.set(resultado, value) ;
						}
					}	
				}
				
			}	
			if (entityClasess != null && entityClasess.length > 0) {
				for (String clasesPojoTemp : entityClasess) {
					boolean isSubClase = clasesPojoTemp.contains(":");
					String[] entitySubClasess = null;
					if (isSubClase) {
						String[] dataTempClase = clasesPojoTemp.split(":");
						clasesPojoTemp = dataTempClase[0];
						String dataTempArray = dataTempClase[1];
						if (dataTempArray.contains("{")) {
							int indexOf = dataTempArray.indexOf("{");
							int lastIndexOf = dataTempArray.lastIndexOf("}");
							dataTempArray = dataTempArray.substring(indexOf + 1, lastIndexOf);
						}
						
						String[]  entitySubClasessTemp = dataTempArray.split(";",-1);
						if (entitySubClasessTemp != null && entitySubClasessTemp.length > 0) {
							entitySubClasess = new String[entitySubClasessTemp.length ];
							int index = 0;
							for (String dataTemp : entitySubClasessTemp) {
								if (!dataTemp.contains("{") && ! dataTemp.contains("}")) {
									entitySubClasess[index] = dataTemp;
									index++;
								}
							}
						} else {
							entitySubClasess = new String[1];
							String dataTemp = entitySubClasessTemp[0];
							int indexOf = dataTemp.indexOf("{");
							int lastIndexOf = dataTemp.lastIndexOf("}");
							dataTemp = dataTemp.substring(indexOf + 1, lastIndexOf);
							entitySubClasess[0] = dataTemp;
						}
					
					}
					String clasesPojo = clasesPojoTemp;
					boolean esTansferSoloPK = false;
					if (clasesPojoTemp.contains("@PK@")) {
						esTansferSoloPK =  true;
						clasesPojo = clasesPojoTemp.substring(0, clasesPojoTemp.indexOf("@PK@"));
					}
					
					try {
						Field f = resultado.getClass().getDeclaredField(clasesPojo);
						if (f != null) {
							f.setAccessible(true);
							
							Field fValue = ressul.getClass().getDeclaredField(clasesPojo);
							fValue.setAccessible(true);
							Object valueTransfer = fValue.get(ressul);
							if (valueTransfer == null) {
								valueTransfer = fValue.getType().newInstance();
							}
							Object value = null;
							
							if (esTansferSoloPK) {
								value = transferObjetoEntityDTOPK(valueTransfer, f.getType());
							} else {
								if (entitySubClasess != null) {
									value = transferObjetoEntityDTO(valueTransfer, f.getType(), entitySubClasess);
								    //value = transferObjetoEntityDTO(valueTransfer, f.getType(), entityClasess);	
								} else {
									value = transferObjetoEntityDTO(valueTransfer, f.getType());	
								}
							   
							}
							if (value != null) {
								f.set(resultado, value) ;
							}
						}
					} catch (Exception e) {
						continue;
					}
					
				}
			}
			return resultado;
		} catch (Exception e) {
			log.error("Error TransferDataObjectUtil.transferObjetoEntityDTO(Object ressul,Class<T> entityClassDTO) al parsear class name = " + className + ", " + entityClassDTO.getName() + "  " + e.getMessage() );
		}
		return null;
	}
	public static <T> T  transferObjetoHerenciaPojo(Object ressul,Class<T> entityClassVO) {
		String className = "";
		try {
			if (ressul == null) {
				return null;
			}
			T resultado = entityClassVO.newInstance();
			className = ressul.getClass().getName();
			String handlerHibernate = obtenerHandlerHibernate(className);
			if (className.contains(handlerHibernate)) {
				int indexOf = className.indexOf(handlerHibernate);
				className =  className.substring(0,indexOf);
				 Hibernate.initialize(ressul);
				 if (ressul instanceof HibernateProxy) {
				    ressul = ((HibernateProxy) ressul).getHibernateLazyInitializer().getImplementation();
				 }
			}
			List<AtributoEntityVO> listaAtributos = AtributosEntityCacheUtil.getInstance().obtenerListaAtributos(ressul.getClass());
			Map<String,Integer> fieldHerenciaMap = fieldHerenciaMap(ressul);
			Map<String,Integer> fieldHerenciaResultadoMap = fieldHerenciaMap(resultado);
			for (AtributoEntityVO objAtr : listaAtributos) {
				if (!"serialVersionUID".equalsIgnoreCase(objAtr.getNombreAtributo())) {
				try {
					Field f = fieldHerenciaSet(resultado, fieldHerenciaResultadoMap, objAtr);					
					f.setAccessible(true);
					Field fValue = fieldHerenciaSet(ressul, fieldHerenciaMap, objAtr);
					fValue.setAccessible(true);
					Object value = fValue.get(ressul);
					if (value != null) {
						f.set(resultado, value) ;
					}
				} catch (Exception e) {
					continue;
				}
					
				}
			}	
			return resultado;
		} catch (Exception e) {
			log.error("Error TransferDataObjectUtil.transferObjetoVO(Object ressul,Class<T> entityClassDTO) al parsear class name = " + className + ", " + entityClassVO.getName() + "  " + e.getMessage() );
		}
		return null;
	}
	
	//TODO:omar_valuedata
	/**
	 * Transfer objeto entity trama.
	 *
	 * @param <T> el tipo generico
	 * @param ressul el ressul
	 * @param entityClass el entity class
	 * @return the t
	 */
	public static <T> T  transferObjetoEntityTrama(Map<String,ValueDataVO> ressul,Class<T> entityClass) {
		try {
			if (ressul == null) {
				return null;
			}
			T resultado = entityClass.newInstance();
			List<AtributoEntityVO> listaAtributos = AtributosEntityCacheUtil.getInstance().obtenerListaAtributos(entityClass.getName().replace("DTO", "").replace("dto", "jpa"));
			for (AtributoEntityVO objAtr : listaAtributos) {
				if (ressul.containsKey(objAtr.getNombreColumna())) {
					Field f = resultado.getClass().getDeclaredField(objAtr.getNombreAtributo());
					f.setAccessible(true);
					Object value  = null;
					if (objAtr.getClasssAtributoType().isAssignableFrom(Date.class)) {
						value = ((ValueDataVO)ressul.get(objAtr.getNombreColumna())).getData();
					} else {
						value = obtenerValor(ressul.get(objAtr.getNombreColumna()).getData() + "",objAtr,false);
					}
					try {
						if (value != null) {
							f.set(resultado, value) ;
						}
					} catch (Exception e) {
						//log.error("Error TransferDataObjectUtil.transferObjetoEntityTrama(Object ressul,Class<T> entityClass) al parsear " + entityClass.getName() + " campo " + objAtr.getNombreAtributo() + "  " + e.getMessage() );
					}
				}
			}	
			ressul = null;
			return resultado;
		} catch (Exception e) {
			log.error("Error TransferDataObjectUtil.transferObjetoEntityTrama(Object ressul,Class<T> entityClass) al parsear " + entityClass.getName() + "  " + e.getMessage() );
		}
		return null;
	}
	
	/**
	 * Metodo que trasnfiere datos de un mapa a un objeto por el atributo valor de la clase.
	 *
	 * @param <T> el tipo generico
	 * @param listaObjectValueMap el lista object value map
	 * @param entityClass el entity class
	 * @return the t
	 */
	public static <T> T  transferObjetoVOTrama(Map<String,Map<String,Object>> listaObjectValueMap , Class<T> entityClass) {
		try {
			Map<String,Object> ressul = listaObjectValueMap.get(entityClass.getName());
			if (ressul == null) {
				return entityClass.newInstance();
			}
			T resultado = entityClass.newInstance();
			List<AtributoEntityVO> listaAtributos = AtributosEntityCacheUtil.getInstance().obtenerListaAtributos(entityClass);
			for (AtributoEntityVO objAtr : listaAtributos) {
				if (ressul.containsKey(objAtr.getNombreAtributo())) {
					Field f = resultado.getClass().getDeclaredField(objAtr.getNombreAtributo());
					f.setAccessible(true);
					Object value  = null;
					value = obtenerValor(ressul.get(objAtr.getNombreAtributo()) + "",objAtr,true);
					if (value != null) {
						if (ARTIFICIO_CLASS.equals(value.toString())) {
							if (listaObjectValueMap.containsKey(objAtr.getClasssAtributoType().getName())) {
								value = transferObjetoVOTrama(listaObjectValueMap, objAtr.getClasssAtributoType());
								try {
									if (value != null) {
										f.set(resultado, value) ;
									}
								} catch (Exception e) {
									log.error("Error OBJETO TransferDataObjectUtil.transferObjetoEntityTrama(Object ressul,Class<T> entityClass) al parsear " + entityClass.getName() + " campo " + objAtr.getNombreAtributo() + "  " + e.getMessage() );
								}
							}
						}  else {
							try {
								if (value != null) {
									f.set(resultado, value) ;
								}
							} catch (Exception e) {
								log.error("Error TransferDataObjectUtil.transferObjetoEntityTrama(Object ressul,Class<T> entityClass) al parsear " + entityClass.getName() + " campo " + objAtr.getNombreAtributo() + "  " + e.getMessage() );
							}
						}
					}
				}
			}	
			listaObjectValueMap = null;
			return resultado;
		} catch (Exception e) {
			log.error("Error TransferDataObjectUtil.transferObjetoEntityTrama(Object ressul,Class<T> entityClass) al parsear " + entityClass.getName() + "  " + e.getMessage() );
		}
		return null;
	}
	
	/**
	 * Transfer objeto entity dto.
	 *
	 * @param <T> el tipo generico
	 * @param campoMappingExcelMap el campo mapping excel map
	 * @param cellDataList el cell data list
	 * @param entityClassDTO el entity class dto
	 * @return the t
	 */
	public  static <T> List<T>  transferObjetoEntityExcelDTO(Map<String,Integer> campoMappingExcelMap,HSSFWorkbook  workBook,Class<T> entityClassDTO,int hoja,int filaData) {
		List<T> resultado = new ArrayList<T>();
		try {
			if (workBook == null) {
				return null;
			}
			List<AtributoEntityVO> listaAtributos = AtributosEntityCacheUtil.getInstance().obtenerListaAtributos(entityClassDTO);
			HSSFSheet hssfSheet = workBook.getSheetAt(hoja - 1);
			Iterator rowIterator = (Iterator) hssfSheet.rowIterator();
			int contador = 0;
			while (rowIterator.hasNext()) {
				contador++;
				HSSFRow hssfRow = (HSSFRow) rowIterator.next();
				if (contador >= filaData) {
					T resultadoTemp = entityClassDTO.newInstance();
					for (AtributoEntityVO objAtr : listaAtributos) {
						if (campoMappingExcelMap.containsKey(objAtr.getNombreAtributo())) {
							Field f = resultadoTemp.getClass().getDeclaredField(objAtr.getNombreAtributo());
							f.setAccessible(true);
							Object value = obtenerValorXls(hssfRow, campoMappingExcelMap.get(objAtr.getNombreAtributo()), objAtr);
							if (value != null) {
								f.set(resultadoTemp, value) ;
							}
						}
					}	
					resultado.add(resultadoTemp);
				}
			}
			if (workBook != null) {
				workBook.close();
			}
		} catch (Exception e) {
			log.error("Error TransferDataObjectUtil.transferObjetoEntityExelDTO(Object ressul,Class<T> entityClassDTO) al parsear " + entityClassDTO.getName() + "  " + e.getMessage() );
		}
		return resultado;
	}
	
	/**
	 * Transfer objeto entity excel xlsx dto.
	 *
	 * @param <T> el tipo generico
	 * @param campoMappingExcelMap el campo mapping excel map
	 * @param cellDataList el cell data list
	 * @param entityClassDTO el entity class dto
	 * @return the list
	 */
	public  static <T> List<T>  transferObjetoEntityExcelXlsxDTO(Map<String,Integer> campoMappingExcelMap,XSSFWorkbook  workBook,Class<T> entityClassDTO,int hoja,int filaData) {
		List<T> resultado = new ArrayList<T>();
		try {
			if (workBook == null) {
				return null;
			}
			List<AtributoEntityVO> listaAtributos = AtributosEntityCacheUtil.getInstance().obtenerListaAtributos(entityClassDTO);
			XSSFSheet hssfSheet = workBook.getSheetAt(hoja - 1);
			Iterator<XSSFRow> rowIterator = (Iterator) hssfSheet.rowIterator();
			int contador = 0;
			while (rowIterator.hasNext()) {
				contador++;
				XSSFRow hssfRow = rowIterator.next();
				if (contador >= filaData) {
					T resultadoTemp = entityClassDTO.newInstance();
					for (AtributoEntityVO objAtr : listaAtributos) {
						if (campoMappingExcelMap.containsKey(objAtr.getNombreAtributo())) {
							Field f = resultadoTemp.getClass().getDeclaredField(objAtr.getNombreAtributo());
							f.setAccessible(true);
							Object value = obtenerValorXlsx(hssfRow, campoMappingExcelMap.get(objAtr.getNombreAtributo()), objAtr);
							if (value != null) {
								f.set(resultadoTemp, value) ;
							}
						}
					}	
					resultado.add(resultadoTemp);
				}
				
			}	
			if (workBook != null) {
				workBook.close();
			}
		} catch (Exception e) {
			log.error("Error TransferDataObjectUtil.transferObjetoEntityExcelXlsxDTO(Object ressul,Class<T> entityClassDTO) al parsear " + entityClassDTO.getName() + "  " + e.getMessage() );
		}
		return resultado;
	}
	
	/**
	 * Transfer objeto entity map dto.
	 *
	 * @param campoMappingExcelMap el campo mapping excel map
	 * @param dataList el data list
	 * @param campoMappingExcelTypeMap el campo mapping excel type map
	 * @param campoMappingFormatoMap el campo mapping formato map
	 * @return the t
	 */
	public  static List<Map<String,ValueDataVO>>  transferObjetoEntityExcelMapDTO(Map<String,Object> campoMappingExcelMap,HSSFWorkbook  workBook,Map<String,String> campoMappingExcelTypeMap,Map<String,String> campoMappingFormatoMap,Map<String, Object> parametroMap,Map<String,ConfiguracionTramaDetalleDTO> configuracionTramaDetalleMap) {
		List<Map<String,ValueDataVO>>  resultado = new ArrayList<Map<String,ValueDataVO>>();
		if (campoMappingFormatoMap == null) {
			campoMappingFormatoMap = new HashMap<String, String>();
		}
		Map<String, String> grupoMap = new HashMap<String, String>();
		try {
			int hoja = (Integer)parametroMap.get("hoja");
			int filaData = (Integer)parametroMap.get("filaData");
			Integer cantidadData = (Integer)parametroMap.get("cantidadData");
			HSSFSheet hssfSheet = workBook.getSheetAt(hoja - 1);
			Iterator rowIterator = (Iterator) hssfSheet.rowIterator();
			int contador = 0;
			int contadorData = 0;
			int filaDataProcesar = 0;
			while (rowIterator.hasNext()) {
				filaDataProcesar++;
				contador++;
				HSSFRow hssfRow = (HSSFRow) rowIterator.next();
				if (contador >= filaData) {
					contadorData ++;
					boolean isValido = validarDataExel(hssfRow, campoMappingExcelMap);
					if (isValido) {
						Map<String,ValueDataVO> resultadoTemp = new HashMap<String, ValueDataVO>();
						for (Map.Entry<String, Object> objAtr : campoMappingExcelMap.entrySet()) {
							ValueDataVO value = obtenerValorXls(hssfRow, Integer.parseInt(objAtr.getValue() + ""), campoMappingExcelTypeMap.get(objAtr.getKey()),campoMappingFormatoMap.get(objAtr.getKey()),filaDataProcesar,parametroMap);
							resultadoTemp.put(objAtr.getKey(), value);		
						}	
						StringBuilder key = generarKeyAgrupador(resultadoTemp, configuracionTramaDetalleMap);
						// para agrupar
						if (StringUtils.isNullOrEmpty(key)) {
							if (!grupoMap.containsKey(key.toString())) {
								resultado.add(resultadoTemp);
								grupoMap.put(key.toString(), null);
							} else {
								resultado.add(resultadoTemp);
								grupoMap.put(key.toString(), null);
							}
						} else {
							if (!grupoMap.containsKey(key.toString())) {
								resultado.add(resultadoTemp);
								grupoMap.put(key.toString(), null);
							} 
						}
					} else {
						break;
					}
					
				}
				if (cantidadData != null) {
					if (contadorData == cantidadData.intValue()) {
						break;
					}
				}
			}	
			if (resultado.size() == 0) {
				filaDataProcesar++;
				HSSFWorkbook workbook = new HSSFWorkbook();
				HSSFSheet sheet = workbook.createSheet("data");
				HSSFRow hssfRow = sheet.createRow(0);
				Map<String,ValueDataVO> resultadoTemp = new HashMap<String, ValueDataVO>();
				for (Map.Entry<String, Object> objAtr : campoMappingExcelMap.entrySet()) {
					ValueDataVO value = obtenerValorXls(hssfRow, Integer.parseInt(objAtr.getValue()  + ""), campoMappingExcelTypeMap.get(objAtr.getKey()),campoMappingFormatoMap.get(objAtr.getKey()),filaDataProcesar,parametroMap);
					resultadoTemp.put(objAtr.getKey(), value);		
				}	
				resultado.add(resultadoTemp);
				workbook.close();
			}
			if (workBook != null) {
				workBook.close();
			}
		} catch (Exception e) {
			log.error("Error TransferDataObjectUtil.transferObjetoEntityExcelMapDTO(Object ressul,Class<T> entityClassDTO) al parsear " + e.getMessage() );
		}
		grupoMap = null;
		return resultado;
	}
	/**
	 * Transfer objeto entity excel xlsx map dto.
	 *
	 * @param campoMappingExcelMap el campo mapping excel map
	 * @param dataList el data list
	 * @param campoMappingExcelTypeMap el campo mapping excel type map
	 * @param campoMappingFormatoMap el campo mapping formato map
	 * @return the list
	 */
	public  static List<Map<String,ValueDataVO>>  transferObjetoEntityExcelXlsxMapDTO(Map<String,Object> campoMappingExcelMap,XSSFWorkbook  workBook,Map<String,String> campoMappingExcelTypeMap,Map<String,String> campoMappingFormatoMap,Map<String, Object> parametroMap,Map<String,ConfiguracionTramaDetalleDTO> configuracionTramaDetalleMap) {
		List<Map<String,ValueDataVO>>  resultado = new ArrayList<Map<String,ValueDataVO>>();
		if (campoMappingFormatoMap == null) {
			campoMappingFormatoMap = new HashMap<String, String>();
		}
		Map<String, String> grupoMap = new HashMap<String, String>();
		try {
			int hoja = (Integer)parametroMap.get("hoja");
			int filaData = (Integer)parametroMap.get("filaData");
			Integer cantidadData = (Integer)parametroMap.get("cantidadData");
			XSSFSheet hssfSheet = workBook.getSheetAt(hoja - 1);
			Iterator<XSSFRow> rowIterator = (Iterator) hssfSheet.rowIterator();
			int contador = 0;
			int contadorData = 0;
			int filaDataProcesar = 0;
			while (rowIterator.hasNext()) {
				filaDataProcesar++;
				contador++;
				XSSFRow hssfRow = rowIterator.next();
				if (contador >= filaData) {
					contadorData ++;
					boolean isValido = validarDataExel(hssfRow, campoMappingExcelMap);
					if (isValido) {
						Map<String,ValueDataVO> resultadoTemp = new HashMap<String, ValueDataVO>();
						for (Map.Entry<String, Object> objAtr : campoMappingExcelMap.entrySet()) {
							ValueDataVO value = obtenerValorXlsx(hssfRow, Integer.parseInt(objAtr.getValue() + ""), campoMappingExcelTypeMap.get(objAtr.getKey()),campoMappingFormatoMap.get(objAtr.getKey()),filaDataProcesar,parametroMap);
							resultadoTemp.put(objAtr.getKey(), value);				
						}
						StringBuilder key = generarKeyAgrupador(resultadoTemp, configuracionTramaDetalleMap);
						// para agrupar
						if (StringUtils.isNullOrEmpty(key)) {
							if (!grupoMap.containsKey(key.toString())) {
								resultado.add(resultadoTemp);
								grupoMap.put(key.toString(), null);
							} else {
								resultado.add(resultadoTemp);
								grupoMap.put(key.toString(), null);
							}
						} else {
							if (!grupoMap.containsKey(key.toString())) {
								resultado.add(resultadoTemp);
								grupoMap.put(key.toString(), null);
							} 
						}
					} else {
						break;
					}
				}
				if (cantidadData != null) {
					if (contadorData == cantidadData.intValue()) {
						break;
					}
				}
			}	
			if (resultado.size() == 0) {
				filaDataProcesar++;
				XSSFWorkbook workbook = new XSSFWorkbook();
				XSSFSheet sheet = (XSSFSheet) workbook.createSheet("data");
				XSSFRow hssfRow = (XSSFRow)sheet.createRow(0);
				Map<String,ValueDataVO> resultadoTemp = new HashMap<String, ValueDataVO>();
				for (Map.Entry<String, Object> objAtr : campoMappingExcelMap.entrySet()) {
					ValueDataVO value = obtenerValorXlsx(hssfRow, Integer.parseInt(objAtr.getValue() + ""), campoMappingExcelTypeMap.get(objAtr.getKey()),campoMappingFormatoMap.get(objAtr.getKey()),filaDataProcesar,parametroMap);
					resultadoTemp.put(objAtr.getKey(), value);				
				}
				resultado.add(resultadoTemp);
				workbook.close();
			}
			if (workBook != null) {
				workBook.close();
			}
		} catch (Exception e) {
			log.error("Error TransferDataObjectUtil.transferObjetoEntityExcelXlsxMapDTO(Object ressul,Class<T> entityClassDTO) al parsear " + e.getMessage() );
		}
		grupoMap = null;
		return resultado;
	}
	/**
	 * Transfer objeto entity csv map dto.
	 *
	 * @param campoMappingCVSMap el campo mapping cvs map
	 * @param dataList el data list
	 * @param campoMappingCSVTypeMap el campo mapping csv type map
	 * @param campoMappingFormatoMap el campo mapping formato map
	 * @param parametroMap el parametro map
	 * @return the list
	 */
	public  static List<Map<String,ValueDataVO>>  transferObjetoEntityCSVMapDTO(Map<String,Object> campoMappingCVSMap,BufferedReader br,Map<String,String> campoMappingCSVTypeMap,Map<String,String> campoMappingFormatoMap,Map<String, Object> parametroMap,Map<String,ConfiguracionTramaDetalleDTO> configuracionTramaDetalleMap) {
		List<Map<String,ValueDataVO>>  resultado = new ArrayList<Map<String,ValueDataVO>>();
		if (campoMappingFormatoMap == null) {
			campoMappingFormatoMap = new HashMap<String, String>();
		}
		Map<String, String> grupoMap = new HashMap<String, String>();
		try {
			String cvsSplitBy = (String)parametroMap.get("cvsSplitBy");
			int filaData = (Integer)parametroMap.get("filaData");
			Integer cantidadData = (Integer)parametroMap.get("cantidadData");
			int contador = 0;
			int contadorData = 0;
			String line = "";
			if (StringUtils.isNullOrEmpty(cvsSplitBy)) {
				cvsSplitBy = ",";
			}
			int filaDataProcesar = Integer.parseInt(parametroMap.get(ConstanteConfiguracionTramaUtil.FILA_DATA_ORIGINAL) + "");  //OBTIENE LA FILA DE LECTURA DEL ARCHIVO CONFIGURADO
			while ((line = br.readLine()) != null) {
				contador++;
				if (contador >= filaData) {
					contadorData++;
					String[] data = line.split(cvsSplitBy, -1);// -1 para leer
					boolean isValido = validarCSV(data, campoMappingCVSMap);
					if (isValido) {
						Map<String,ValueDataVO> resultadoTemp = new HashMap<String, ValueDataVO>();
						for (Map.Entry<String, Object> objAtr : campoMappingCVSMap.entrySet()) {
							ValueDataVO value = obtenerValueCSV(data, Integer.parseInt(objAtr.getValue() + "") , campoMappingCSVTypeMap.get(objAtr.getKey()),campoMappingFormatoMap.get(objAtr.getKey()),filaDataProcesar,parametroMap);
							resultadoTemp.put(objAtr.getKey(), value);				
						}	
						StringBuilder key = generarKeyAgrupador(resultadoTemp, configuracionTramaDetalleMap);
						// para agrupar
						if (StringUtils.isNullOrEmpty(key)) {
							if (!grupoMap.containsKey(key.toString())) {
								resultado.add(resultadoTemp);
								grupoMap.put(key.toString(), null);
							} else {
								resultado.add(resultadoTemp);
								grupoMap.put(key.toString(), null);
							}
						} else {
							if (!grupoMap.containsKey(key.toString())) {
								resultado.add(resultadoTemp);
								grupoMap.put(key.toString(), null);
							} 
						}
					} else {
						break;
					}
				}
				if (cantidadData != null) {
					if (contadorData == cantidadData.intValue()) {
						break;
					}
				}
			}
			if (resultado.size() == 0) {
				String[] data = new String[0];//campoMappingTXTMap.size() obtener maximo index
				Map<String,ValueDataVO> resultadoTemp = new HashMap<String, ValueDataVO>();
				for (Map.Entry<String, Object> objAtr : campoMappingCVSMap.entrySet()) {
					ValueDataVO value = obtenerValueCSV(data, Integer.parseInt(objAtr.getValue() + "") , campoMappingCSVTypeMap.get(objAtr.getKey()),campoMappingFormatoMap.get(objAtr.getKey()),filaDataProcesar,parametroMap);
					resultadoTemp.put(objAtr.getKey(), value);				
				}	
				resultado.add(resultadoTemp);
			}
		} catch (Exception e) {
			log.error("Error TransferDataObjectUtil.transferObjetoEntityCSVMapDTO(Object ressul,Class<T> entityClassDTO) al parsear " + e.getMessage() );
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					log.error(e);
				}
			}
		}
		grupoMap = null;
		return resultado;
	}
	
	/**
	 * Transfer objeto entity txt separador map dto.
	 *
	 * @param campoMappingTXTMap el campo mapping txt map
	 * @param dataList el data list
	 * @param campoMappingTxtTypeMap el campo mapping txt type map
	 * @param campoMappingFormatoMap el campo mapping formato map
	 * @param parametroMap el parametro map
	 * @return the list
	 */
	public  static List<Map<String,ValueDataVO>>  transferObjetoEntityTXTSeparadorMapDTO(Map<String,Object> campoMappingTXTMap,BufferedReader br,Map<String,String> campoMappingTxtTypeMap,Map<String,String> campoMappingFormatoMap,Map<String, Object> parametroMap,Map<String,ConfiguracionTramaDetalleDTO> configuracionTramaDetalleMap) {
		List<Map<String,ValueDataVO>>  resultado = new ArrayList<Map<String,ValueDataVO>>();
		if (campoMappingFormatoMap == null) {
			campoMappingFormatoMap = new HashMap<String, String>();
		}
		Map<String, String> grupoMap = new HashMap<String, String>();
		try {
			String txtSplitBy = (String)parametroMap.get("txtSplitBy");
			int filaData = (Integer) parametroMap.get("filaData");
			Integer cantidadData = (Integer) parametroMap.get("cantidadData");
			int contador = 0;
			int contadorData = 0;
			String line = "";
			if (StringUtils.isNullOrEmpty(txtSplitBy)) { 
				txtSplitBy = "\t";//tabuladores
		   }
			int filaDataProcesar = 0;
			while ((line = br.readLine()) != null) {
				filaDataProcesar++;
				contador++;
				if (contador >= filaData) {
					contadorData ++;
					String[] data = line.split(txtSplitBy,-1);//-1 para leer 
					boolean isValido = validarCSV(data, campoMappingTXTMap);
					if (isValido) {
						Map<String,ValueDataVO> resultadoTemp = new HashMap<String, ValueDataVO>();
						for (Map.Entry<String, Object> objAtr : campoMappingTXTMap.entrySet()) {
							ValueDataVO value = obtenerValueCSV(data, Integer.parseInt(objAtr.getValue() + ""), campoMappingTxtTypeMap.get(objAtr.getKey()),campoMappingFormatoMap.get(objAtr.getKey()),filaDataProcesar,parametroMap);
							resultadoTemp.put(objAtr.getKey(), value);		
						}	
						StringBuilder key = generarKeyAgrupador(resultadoTemp, configuracionTramaDetalleMap);
						// para agrupar
						if (StringUtils.isNullOrEmpty(key)) {
							if (!grupoMap.containsKey(key.toString())) {
								resultado.add(resultadoTemp);
								grupoMap.put(key.toString(), null);
							} else {
								resultado.add(resultadoTemp);
								grupoMap.put(key.toString(), null);
							}
						} else {
							if (!grupoMap.containsKey(key.toString())) {
								resultado.add(resultadoTemp);
								grupoMap.put(key.toString(), null);
							} 
						}
					} else {
						break;
					}
				}
				if (cantidadData != null) {
					if (contadorData == cantidadData.intValue()) {
						break;
					}
				}
			}	
			if (resultado.size() == 0) {
				filaDataProcesar++;
				String[] data = new String[0];//campoMappingTXTMap.size() obtener maximo index
				Map<String,ValueDataVO> resultadoTemp = new HashMap<String, ValueDataVO>();
				for (Map.Entry<String, Object> objAtr : campoMappingTXTMap.entrySet()) {
					ValueDataVO value = obtenerValueCSV(data, Integer.parseInt(objAtr.getValue() + ""), campoMappingTxtTypeMap.get(objAtr.getKey()),campoMappingFormatoMap.get(objAtr.getKey()),filaDataProcesar,parametroMap);
					resultadoTemp.put(objAtr.getKey(), value);				
				}	
				resultado.add(resultadoTemp);
			}
		} catch (Exception e) {
			log.error("Error TransferDataObjectUtil.transferObjetoEntityTXTSeparadorMapDTO(Object ressul,Class<T> entityClassDTO) al parsear " + e.getMessage() );
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					log.error(e);
				}
			}
		}
		grupoMap = null;
		return resultado;
	}
	
	/**
	 * Transfer objeto entity txt map dto.
	 *
	 * @param campoMappingTXTMap el campo mapping txt map
	 * @param dataList el data list
	 * @param campoMappingTXTTypeMap el campo mapping txt type map
	 * @param campoMappingFormatoMap el campo mapping formato map
	 * @return the list
	 */
	public  static List<Map<String,ValueDataVO>>  transferObjetoEntityTXTMapDTO(Map<String,Object> campoMappingTXTMap,BufferedReader br,Map<String,String> campoMappingTXTTypeMap,Map<String,String> campoMappingFormatoMap,Map<String, Object> parametroMap,Map<String,ConfiguracionTramaDetalleDTO> configuracionTramaDetalleMap) {
		List<Map<String,ValueDataVO>>  resultado = new ArrayList<Map<String,ValueDataVO>>();
		if (campoMappingFormatoMap == null) {
			campoMappingFormatoMap = new HashMap<String, String>();
		}
		Map<String, List<Map<String, ValueDataVO>>> grupoMap = new HashMap<String, List<Map<String, ValueDataVO>>>();
		try {
			int filaData = (Integer)parametroMap.get("filaData");
			Integer cantidadData = (Integer)parametroMap.get("cantidadData");
			int contador = 0;
			int contadorData = 0;
			int filaDataProcesar = 0;
			String line = "";
			while ((line = br.readLine()) != null) {
				contador++;
				filaDataProcesar++;
				if (contador >= filaData) {
					contadorData ++;
					String data = line;	
					boolean isValido =  validarTXT(data, campoMappingTXTMap);
					if (isValido) {
						Map<String,ValueDataVO> resultadoTemp = new HashMap<String, ValueDataVO>();
						for (Map.Entry<String, Object> objAtr : campoMappingTXTMap.entrySet()) {
							ValueDataVO value = obtenerValuePosicion(data,objAtr.getValue() + "", campoMappingTXTTypeMap.get(objAtr.getKey()),campoMappingFormatoMap.get(objAtr.getKey()),filaDataProcesar,parametroMap);
							resultadoTemp.put(objAtr.getKey(), value);				
						}	
						StringBuilder key = generarKeyAgrupador(resultadoTemp, configuracionTramaDetalleMap);
						// para agrupar
						if (StringUtils.isNullOrEmpty(key)) {
							if (!grupoMap.containsKey(key.toString())) {
								resultado.add(resultadoTemp);
								grupoMap.put(key.toString(), null);
							} else {
								resultado.add(resultadoTemp);
								grupoMap.put(key.toString(), null);
							}
						} else {
							if (!grupoMap.containsKey(key.toString())) {
								resultado.add(resultadoTemp);
								grupoMap.put(key.toString(), null);
							} 
						}
					} else {
						break;
					}
				}
				if (cantidadData != null) {
					if (contadorData == cantidadData.intValue()) {
						break;
					}
				}
			}	
			if(resultado.size() == 0) {
				filaDataProcesar++;
				String data = "";	
				Map<String,ValueDataVO> resultadoTemp = new HashMap<String, ValueDataVO>();
				for (Map.Entry<String, Object> objAtr : campoMappingTXTMap.entrySet()) {
					ValueDataVO value = obtenerValuePosicion(data,objAtr.getValue() + "", campoMappingTXTTypeMap.get(objAtr.getKey()),campoMappingFormatoMap.get(objAtr.getKey()),filaDataProcesar,parametroMap);
					resultadoTemp.put(objAtr.getKey(), value);				
				}	
				resultado.add(resultadoTemp);
			}
		} catch (Exception e) {
			log.error("Error TransferDataObjectUtil.transferObjetoEntityTXTMapDTO(Object ressul,Class<T> entityClassDTO) al parsear " + e.getMessage() );
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					log.error(e);
				}
			}
		}
		grupoMap = null;
		return resultado;
	}
    
	/**
	 * Transfer objeto entity coordenada txt map dto.
	 *
	 * @param campoMappingTXTMap el campo mapping txt map
	 * @param dataList el data list
	 * @param campoMappingTXTTypeMap el campo mapping txt type map
	 * @param campoMappingFormatoMap el campo mapping formato map
	 * @param isCabecera el is cabecera
	 * @return the list
	 */
	public  static List<Map<String,ValueDataVO>>  transferObjetoEntityCoordenadaTXTMapDTO(Map<String,Object> campoMappingTXTMap,BufferedReader br,Map<String,String> campoMappingTXTTypeMap,Map<String,String> campoMappingFormatoMap, boolean isCabecera,Map<String, Object> parametroMap,Map<String,ConfiguracionTramaDetalleDTO> configuracionTramaDetalleMap) {
		List<Map<String,ValueDataVO>>  resultado = new ArrayList<Map<String,ValueDataVO>>();
		if (campoMappingFormatoMap == null) {
			campoMappingFormatoMap = new HashMap<String, String>();
		}
		Map<String, String> grupoMap = new HashMap<String, String>();
		try {
			int filaData = (Integer)parametroMap.get("filaData");
			Integer cantidadData = (Integer)parametroMap.get("cantidadData");
			String delimitadorData = (String)parametroMap.get("delimitadorData");
			if (isCabecera) {
				int contador = 0;
				int contadorData = 0;
				String line = "";
				List<String> dataList = new ArrayList<String>();
				while ((line = br.readLine()) != null) {
					contador++;
					if (contador >= filaData) {
						contadorData ++;
						String data = line;	
						dataList.add(data);
					}
					if (isCabecera) {
						if (cantidadData != null) {
							if (cantidadData.compareTo(contadorData) == 0) {
								break;
							}
						}
					} 
				}	
				if (dataList.size() == 0) {
					dataList.add("");
				}
				Map<String,ValueDataVO> resultadoTemp = new HashMap<String, ValueDataVO>();
				for (Map.Entry<String, Object> objAtr : campoMappingTXTMap.entrySet()) {
					ValueDataVO value = obtenerCoordenadaValor(dataList,null,campoMappingTXTMap.get(objAtr.getKey()) + "", campoMappingTXTTypeMap.get(objAtr.getKey()),campoMappingFormatoMap.get(objAtr.getKey()),isCabecera,parametroMap);
					resultadoTemp.put(objAtr.getKey(), value);				
				}	
				resultado.add(resultadoTemp);
			} else {
				int contador = 0;
				String line = "";
				while ((line = br.readLine()) != null) {
					contador++;
					if (contador >= filaData) {
						if (!isCabecera) {
							if (line.contains(delimitadorData)) {
								break;
							}
						}
						String data = line;	
						boolean isValido = validarTXTCoordenada(data, campoMappingTXTMap);
						if (isValido) {
							Map<String,ValueDataVO> resultadoTemp = new HashMap<String, ValueDataVO>();
							for (Map.Entry<String, Object> objAtr : campoMappingTXTMap.entrySet()) {
								ValueDataVO value = obtenerCoordenadaValor(null,data,objAtr.getValue() + "", campoMappingTXTTypeMap.get(objAtr.getKey()),campoMappingFormatoMap.get(objAtr.getKey()),isCabecera,parametroMap);
								resultadoTemp.put(objAtr.getKey(), value);				
							}
							StringBuilder key = generarKeyAgrupador(resultadoTemp, configuracionTramaDetalleMap);
							// para agrupar
							if (StringUtils.isNullOrEmpty(key)) {
								if (!grupoMap.containsKey(key.toString())) {
									resultado.add(resultadoTemp);
									grupoMap.put(key.toString(), null);
								} else {
									resultado.add(resultadoTemp);
									grupoMap.put(key.toString(), null);
								}
							} else {
								if (!grupoMap.containsKey(key.toString())) {
									resultado.add(resultadoTemp);
									grupoMap.put(key.toString(), null);
								} 
							}
						} else {
							break;
						}
					}
				}	
				if (resultado.size() == 0) {
					Map<String,ValueDataVO> resultadoTemp = new HashMap<String, ValueDataVO>();
					for (Map.Entry<String, Object> objAtr : campoMappingTXTMap.entrySet()) {
						ValueDataVO value = obtenerCoordenadaValor(null,"",objAtr.getValue() + "", campoMappingTXTTypeMap.get(objAtr.getKey()),campoMappingFormatoMap.get(objAtr.getKey()),isCabecera,parametroMap);
						resultadoTemp.put(objAtr.getKey(), value);				
					}
					resultado.add(resultadoTemp);
				}
			}				
			
		} catch (Exception e) {
			log.error("Error TransferDataObjectUtil.transferObjetoEntityCoordenadaTXTMapDTO(Object ressul,Class<T> entityClassDTO) al parsear " + e.getMessage() );
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					log.error(e);
				}
			}
		}
		grupoMap = null;
		return resultado;
	}
	
	/**
	 * Transfer objeto entity get rest dto.
	 *
	 * @param <T> el tipo generico
	 * @param info el info
	 * @param entityClassDTO el entity class dto
	 * @return the t
	 */
	public  static <T> T  transferObjetoEntityGetRestDTO(@Context UriInfo info,Class<T> entityClassDTO) {
		T resultado = null;
		try {
			if (info == null) {
				//return null;
			}
			//String className = entityClassDTO.getClass().getName().substring(0, entityClassDTO.getClass().getName().length() - 3);
			List<AtributoEntityVO> listaAtributos = AtributosEntityCacheUtil.getInstance().obtenerListaAtributos(entityClassDTO);
			resultado = entityClassDTO.newInstance();
			/*for (AtributoEntityVO objAtr : listaAtributos) {
				Field f = resultado.getClass().getDeclaredField(objAtr.getNombreAtributo());
				f.setAccessible(true);
				Object value =  obtenerValor(info.getQueryParameters().getFirst(objAtr.getNombreAtributo()), objAtr,false);
				if (value != null) {
					f.set(resultado, value) ;
				}
			}*/			
			Map<String,Integer> fieldHerenciaResultadoMap = fieldHerenciaMap(resultado);
			for (AtributoEntityVO objAtr : listaAtributos) {
				if (!"serialVersionUID".equalsIgnoreCase(objAtr.getNombreAtributo())) {
					try {
						Field f = fieldHerenciaSet(resultado, fieldHerenciaResultadoMap, objAtr);					
						f.setAccessible(true);
						//Field fValue = fieldHerenciaSet(ressul, fieldHerenciaMap, objAtr);
						//fValue.setAccessible(true);
						Object value =  obtenerValor(info.getQueryParameters().getFirst(objAtr.getNombreAtributo()), objAtr,false);//fValue.get(ressul);	
						
						if (value != null) {
							f.set(resultado, value) ;
						}
						
					} catch (Exception e) {
						continue;
					}
					
				}
			}
			
		} catch (Exception e) {
			log.error("Error TransferDataObjectUtil.transferObjetoEntityGetRestDTO(Object ressul,Class<T> entityClassDTO) al parsear " + entityClassDTO.getName() + "  " + e.getMessage() );
		}
		return resultado;
	}
	
	public  static Map<String,Object>  transferObjetoEntityGetRestMap(@Context UriInfo info) {
		Map<String,Object>  resultado = new HashMap<String,Object>();
		try {
			if (info == null) {
				//return null;
			}
			for (String key : info.getQueryParameters().keySet()) {
				resultado.put(key.toUpperCase(), info.getQueryParameters().getFirst(key));
			} 
			
		} catch (Exception e) {
			log.error("Error TransferDataObjectUtil.transferObjetoEntityGetRestMap " + e.getMessage() );
		}
		return resultado;
	}

	/**
	 * Transfer objeto entity historial.
	 *
	 * @param <T> el tipo generico
	 * @param ressul el ressul
	 * @param entityClassDTO el entity class dto
	 * @return the t
	 */
	public static <T> T  transferObjetoEntityHistorial(Object ressul,Class<T> entityClassDTO) {
		try {
			if (ressul == null) {
				return null;
			}
			T resultado = entityClassDTO.newInstance();
			List<AtributoEntityVO> listaAtributos = AtributosEntityCacheUtil.getInstance().obtenerListaAtributos(ressul.getClass().getName());
			for (AtributoEntityVO objAtr : listaAtributos) {
				if (objAtr.isColumn()) {
					try {
						Field f = resultado.getClass().getDeclaredField(objAtr.getNombreAtributo());
						f.setAccessible(true);
						Field fValue = ressul.getClass().getDeclaredField(objAtr.getNombreAtributo());
						fValue.setAccessible(true);
						Object value = fValue.get(ressul);
						if (value != null) {
							f.set(resultado, value) ;
						}
					} catch (Exception e) {
						//e.printStackTrace();
					}
				}
			}	
			return resultado;
		} catch (Exception e) {
			log.error("Error TransferDataObjectUtil.transferObjetoEntityHistorial(Object ressul,Class<T> entityClassDTO) al parsear " + entityClassDTO.getName() + "  " + e.getMessage() );
		}
		return null;
	}
	
	/**
	 * Transfer objeto entity dto list.
	 *
	 * @param <T> el tipo generico
	 * @param <E> el tipo de elemento
	 * @param ressulList el ressul list
	 * @param entityClassDTO el entity class dto
	 * @return the list
	 */
	public static <T,E> List<T>  transferObjetoEntityDTOList(List<E> ressulList,Class<T> entityClassDTO, String... entityClasess) {
		List<T> resultado = new ArrayList<T>();
		if (ressulList == null) {
			return resultado;
		}
		for (Object ressul : ressulList) {
			T resultadoTemp = transferObjetoEntityDTO(ressul, entityClassDTO,entityClasess);
			resultado.add(resultadoTemp);
		}
		return resultado;
	}
	
	public static <T> T  transferObjetoEntity(Object ressul,Class<T> entityClassEntity,String... entityClasess) {
	  return transferObjetoEntity(ressul, entityClassEntity, false, entityClasess);	
	}
	public static <T> T  transferObjetoEntityPK(Object ressul,Class<T> entityClassEntity,String... entityClasess) {
		  return transferObjetoEntity(ressul, entityClassEntity, true, entityClasess);	
		}
	/**
	 * Transfer objeto entity.
	 *
	 * @param <T> el tipo generico
	 * @param ressul el ressul
	 * @param entityClassEntity el entity class entity
	 * @return the t
	 */
	public static <T> T  transferObjetoEntity(Object ressul,Class<T> entityClassEntity,boolean esPK,String... entityClasess) {
		try {
			if (ressul == null) {
				return null;
			}
			T resultado = entityClassEntity.newInstance();
			List<AtributoEntityVO> listaAtributos = AtributosEntityCacheUtil.getInstance().obtenerListaAtributos(entityClassEntity.getName());
			for (AtributoEntityVO objAtr : listaAtributos) {
				if (esPK) {
					if (objAtr.isColumn() && objAtr.isEsPK() ) {
						Field f = resultado.getClass().getDeclaredField(objAtr.getNombreAtributo());
						f.setAccessible(true);
						Field fValue = ressul.getClass().getDeclaredField(objAtr.getNombreAtributo());
						fValue.setAccessible(true);
						Object value = fValue.get(ressul);						
						if (StringUtils.isNotNullOrBlank(value)) {
							f.set(resultado, value) ;
						} else {
							resultado = null;
						}
						break;
					}
					
				} else {
					if (objAtr.isColumn()) {
						Field f = resultado.getClass().getDeclaredField(objAtr.getNombreAtributo());
						f.setAccessible(true);
						Field fValue = ressul.getClass().getDeclaredField(objAtr.getNombreAtributo());
						fValue.setAccessible(true);
						Object value = fValue.get(ressul);
						if (value != null) {
							f.set(resultado, value) ;
						}
					}
				}
			}
			if (entityClasess != null && entityClasess.length > 0) {
				for (String clasesPojoTemp : entityClasess) {
					String clasesPojo = clasesPojoTemp;
					boolean esTansferSoloPK = false;
					if (clasesPojoTemp.contains("@PK@")) {
						esTansferSoloPK =  true;
						clasesPojo = clasesPojoTemp.substring(0, clasesPojoTemp.indexOf("@PK@"));
					}
					try {
						Field f = resultado.getClass().getDeclaredField(clasesPojo);
						if (f != null) {
							f.setAccessible(true);
							
							Field fValue = ressul.getClass().getDeclaredField(clasesPojo);
							fValue.setAccessible(true);
							Object valueTransfer = fValue.get(ressul);
							Object value = null;
							if (esTansferSoloPK) {
								value = transferObjetoEntityPK(valueTransfer, f.getType(), entityClasess);
							} else {
							   value = transferObjetoEntity(valueTransfer, f.getType(), entityClasess);	
							}
							f.set(resultado, value) ;							
						}
					} catch (Exception e) {
						continue;
					}
					
				}
			}
			return resultado;
		} catch (Exception e) {
			log.error("Error TransferDataObjectUtil.transferObjetoEntity(Object ressul,Class<T> entityClassEntity) al parsear " + entityClassEntity.getName() + "  " + e.getMessage() );
		}
		return null;
	}
	public static <T> T  transferObjetoEntityVO(Object ressul,Class<T> entityClassEntity) {
		return transferObjetoEntityVO(ressul,entityClassEntity,false);
	}
	
	public static <T> T  transferObjetoEntityVO(Object ressul,Class<T> entityClassEntity,boolean isReplaceDTO) {
		try {
			if (ressul == null) {
				return null;
			}
			T resultado = entityClassEntity.newInstance();
			String className = entityClassEntity.getName();
			if (isReplaceDTO) {
				className = className.replace("DTO", "").replace("dto", "jpa");
			}
			List<AtributoEntityVO> listaAtributos = AtributosEntityCacheUtil.getInstance().obtenerListaAtributos(className);
			for (AtributoEntityVO objAtr : listaAtributos) {
				try {
					if (objAtr.isColumn()) {
						Field f = resultado.getClass().getDeclaredField(objAtr.getNombreAtributo());
						f.setAccessible(true);
						Field fValue = ressul.getClass().getDeclaredField(objAtr.getNombreAtributo());
						fValue.setAccessible(true);
						Object value = fValue.get(ressul);
						if (value != null) {
							f.set(resultado, value) ;
						}
					}
				} catch (Exception e) {
					//log.info("Error TransferDataObjectUtil.transferObjetoEntity(Object ressul,Class<T> entityClassEntity) al parsear " + entityClassEntity.getName() + "  " + e.getMessage() );
				}
			}	
			return resultado;
		} catch (Exception e) {
			log.error("Error TransferDataObjectUtil.transferObjetoEntity(Object ressul,Class<T> entityClassEntity) al parsear " + entityClassEntity.getName() + "  " + e.getMessage() );
		}
		return null;
	}
	/**
	 * Transfer objeto entity atribute map.
	 *
	 * @param <T> el tipo generico
	 * @param ressul el ressul
	 * @return the map
	 */
	public static <T> Map<String,Object>  transferObjetoEntityAtributeMap(Object ressul) {
		try {
			if (ressul == null) {
				return null;
			}
			Map<String,Object>  resultado = new HashMap<String, Object>();
			List<AtributoEntityVO> listaAtributos = AtributosEntityCacheUtil.getInstance().obtenerListaAtributos(ressul.getClass());
			for (AtributoEntityVO objAtr : listaAtributos) {
				if (!StringUtils.isNullOrEmpty(objAtr.getNombreColumna()) || objAtr.isPKCompuesta() ) {
					if (!objAtr.isPKCompuesta()) {
						Field fValue = ressul.getClass().getDeclaredField(objAtr.getNombreAtributo());
						fValue.setAccessible(true);
						Object value = fValue.get(ressul);
						if (value == null) {
							resultado.put(objAtr.getNombreAtributo(), "");
						} else {
							resultado.put(objAtr.getNombreAtributo(), value);
						}
					} else {
						Field fValue = ressul.getClass().getDeclaredField(objAtr.getNombreAtributo());
						fValue.setAccessible(true);
						Object value = fValue.get(ressul);
						if (value == null) {
							value = fValue.getType().newInstance();
						}
						resultado.putAll(transferObjetoEntityAtributeMap(value));
						
					}
			}	}
			return resultado;
		} catch (Exception e) {
			log.error("Error TransferDataObjectUtil.transferObjetoEntityAtributeMap(Object ressul,Class<T> entityClassEntity) al parsear " + ressul.getClass().getName() + "  " + e.getMessage() );
		}
		return null;
	}
	
	/**
	 * Transfer objeto entity campos map.
	 *
	 * @param <T> el tipo generico
	 * @param ressul el ressul
	 * @return the map
	 */
	public static <T> Map<String,Object>  transferObjetoEntityCamposMap(Object ressul) {
		try {
			if (ressul == null) {
				return null;
			}
			Map<String,Object>  resultado = new HashMap<String, Object>();
			List<AtributoEntityVO> listaAtributos = AtributosEntityCacheUtil.getInstance().obtenerListaAtributos(ressul.getClass());
			for (AtributoEntityVO objAtr : listaAtributos) {
				if (!StringUtils.isNullOrEmpty(objAtr.getNombreColumna()) || objAtr.isPKCompuesta() ) {
					if (!objAtr.isPKCompuesta()) {
						Field fValue = ressul.getClass().getDeclaredField(objAtr.getNombreAtributo());
						fValue.setAccessible(true);
						Object value = fValue.get(ressul);
						if (value == null) {
							resultado.put(objAtr.getNombreColumna(), "");
						} else {
							resultado.put(objAtr.getNombreColumna(), value);
						}
					} else {
						Field fValue = ressul.getClass().getDeclaredField(objAtr.getNombreAtributo());
						fValue.setAccessible(true);
						Object value = fValue.get(ressul);
						if (value == null) {
							value = fValue.getType().newInstance();
						}
						resultado.putAll(transferObjetoEntityCamposMap(value));
						
					}
			}	}
			return resultado;
		} catch (Exception e) {
			log.error("Error TransferDataObjectUtil.transferObjetoEntityAtributeMap(Object ressul,Class<T> entityClassEntity) al parsear " + ressul.getClass().getName() + "  " + e.getMessage() );
		}
		return null;
	}
	
	/**
	 * Transfer objeto vo atribute map.
	 *
	 * @param <T> el tipo generico
	 * @param ressul el ressul
	 * @param listaObjeto el lista objeto
	 * @param listaAtributo el lista atributo
	 * @param isExcluir el is excluir
	 * @return the map
	 */
	public static <T> Map<String,Map<String,Object>>  transferObjetoVOAtributeMap(Object ressul, Map<String,List<String>> listaObjeto, List<String> listaAtributo, boolean isExcluir) {
		try {
			if (ressul == null) {
				return null;
			}
			if (listaAtributo == null) {
				listaAtributo = new ArrayList<String>();
			}
			if (isExcluir) {
				if (!listaAtributo.contains("serialVersionUID")) {
					listaAtributo.add("serialVersionUID");
				}
			}
			if (listaObjeto == null) {
				listaObjeto = new HashMap<String, List<String>>();
			}
			Map<String,Map<String,Object>>  resultado = new HashMap<String, Map<String,Object>>();
			Map<String,Object>  resultadoValue = new HashMap<String, Object>();
			List<AtributoEntityVO> listaAtributos = AtributosEntityCacheUtil.getInstance().obtenerListaAtributos(ressul.getClass());
			for (AtributoEntityVO objAtr : listaAtributos) {
				boolean isObtenerAtributo = false;
				if (isExcluir) {
					isObtenerAtributo = !listaAtributo.contains(objAtr.getNombreAtributo());
				} else {
					isObtenerAtributo = listaAtributo.contains(objAtr.getNombreAtributo());
				}
				if (isObtenerAtributo) {
					try {
						Field fValue = ressul.getClass().getDeclaredField(objAtr.getNombreAtributo());
						fValue.setAccessible(true);
						Object value = fValue.get(ressul);
						if (value != null) {
							if (!listaObjeto.containsKey(objAtr.getNombreAtributo())) {
								if (!StringUtils.isNullOrEmpty(value)) {
									if (!objAtr.getClasssAtributoType().isAssignableFrom(Date.class)) {
										resultadoValue.put(objAtr.getNombreAtributo(), value);
									} else {
										resultadoValue.put(objAtr.getNombreAtributo(), FechaUtil.obtenerFechaFormatoCompleto((Date)value) );
									}
								}
							} else {
								resultadoValue.put(objAtr.getNombreAtributo(), ARTIFICIO_CLASS);
								resultado.putAll(transferObjetoVOAtributeMap(value ,null ,listaObjeto.get(objAtr.getNombreAtributo()),false));
							}
						}
					} catch (Exception e) {
						//log.error("error convertir " + objAtr.getNombreAtributo());
					}
				}
			}
			if (resultadoValue.size() > 0) {
				resultado.put(ressul.getClass().getName(), resultadoValue);
			}
			return resultado;
		} catch (Exception e) {
			log.error("Error TransferDataObjectUtil.transferObjetoEntityAtributeMap(Object ressul,Class<T> entityClassEntity) al parsear " + ressul.getClass().getName() + "  " + e.getMessage() );
		}
		return null;
	}
	
	/**
	 * Transfer objeto vo atribute map.
	 *
	 * @param <T> el tipo generico
	 * @param ressul el ressul
	 * @return the map
	 */
	public static <T> Map<String,Object>  transferObjetoVOAtributeMap(Object ressul) {
		try {
			if (ressul == null) {
				return null;
			}
			Map<String,Object>  resultado = new HashMap<String, Object>();
			List<AtributoEntityVO> listaAtributos = AtributosEntityCacheUtil.getInstance().obtenerListaAtributos(ressul.getClass());
			for (AtributoEntityVO objAtr : listaAtributos) {
				try {
					Field fValue = ressul.getClass().getDeclaredField(objAtr.getNombreAtributo());
					fValue.setAccessible(true);
					Object value = fValue.get(ressul);
					if (value != null) {
						if (!StringUtils.isNullOrEmpty(value)) {
							if (!objAtr.getClasssAtributoType().isAssignableFrom(Date.class)) {
								resultado.put(objAtr.getNombreAtributo(), value);
							} else {
								resultado.put(objAtr.getNombreAtributo(), FechaUtil.obtenerFechaFormatoCompleto((Date)value) );
							}
						}
					}
				} catch (Exception e) {
					//log.error("error convertir " + objAtr.getNombreAtributo());
				}
			}
			return resultado;
		} catch (Exception e) {
			log.error("Error TransferDataObjectUtil.transferObjetoEntityAtributeMap(Object ressul,Class<T> entityClassEntity) al parsear " + ressul.getClass().getName() + "  " + e.getMessage() );
		}
		return null;
	}
	
	/**
	 * Transfer objeto entity vo.
	 *
	 * @param <T> el tipo generico
	 * @param scriptSqlResulJDBCVO el script sql resul jdbcvo
	 * @param entityClassEntity el entity class entity
	 * @return the t
	 */
	public static <T> T  transferObjetoEntityVO(ScriptSqlResulJDBCVO scriptSqlResulJDBCVO,Class<T> entityClassEntity) {
		try {
			List<Map<String,Object>> ressul = scriptSqlResulJDBCVO.getListaData();
			if (ressul == null || scriptSqlResulJDBCVO.isTieneError()) {
				return null;
			}
			Map<String,Object> valueMap = scriptSqlResulJDBCVO.getListaData().get(0);
			T resultado = entityClassEntity.newInstance();
			List<AtributoEntityVO> listaAtributos = AtributosEntityCacheUtil.getInstance().obtenerListaAtributos(entityClassEntity);
			for (AtributoEntityVO objAtr : listaAtributos) {
				if (scriptSqlResulJDBCVO.getListaHeader().contains(objAtr.getNombreAtributo().toUpperCase())) {
					Field f = resultado.getClass().getDeclaredField(objAtr.getNombreAtributo());
					f.setAccessible(true);
					Object value = obtenerValor(valueMap.get(objAtr.getNombreAtributo().toUpperCase()) + "", objAtr,false) ;
					if (value != null) {
						f.set(resultado, value) ;
					}
				}
			}	
			return resultado;
		} catch (Exception e) {
			log.error("Error TransferDataObjectUtil.transferObjetoEntityVO(Object ressul,Class<T> entityClassEntity) al parsear " + entityClassEntity.getName() + "  " + e.getMessage() );
		}
		return null;
	}
	
	/**
	 * Transfer objeto entity list vo.
	 *
	 * @param <T> el tipo generico
	 * @param scriptSqlResulJDBCVO el script sql resul jdbcvo
	 * @param entityClassEntity el entity class entity
	 * @return the list
	 */
	public static <T> List<T>  transferObjetoEntityListVO(ScriptSqlResulJDBCVO scriptSqlResulJDBCVO,Class<T> entityClassEntity) {
		return transferObjetoEntityListVO(scriptSqlResulJDBCVO, entityClassEntity, new HashMap<String, String>());
	}
	
	/**
	 * Transfer objeto entity list vo.
	 *
	 * @param <T> el tipo generico
	 * @param scriptSqlResulJDBCVO el script sql resul jdbcvo
	 * @param entityClassEntity el entity class entity
	 * @param formatoFechaMap el formato fecha map
	 * @return the list
	 */
	public static <T> List<T>  transferObjetoEntityListVO(ScriptSqlResulJDBCVO scriptSqlResulJDBCVO,Class<T> entityClassEntity,Map<String,String> formatoFechaMap) {
		try {
			List<Map<String,Object>> ressul = scriptSqlResulJDBCVO.getListaData();
			if (ressul == null || scriptSqlResulJDBCVO.isTieneError()) {
				return null;
			}
			List<T> resultado =  new ArrayList<T>();
			List<AtributoEntityVO> listaAtributos = AtributosEntityCacheUtil.getInstance().obtenerListaAtributos(entityClassEntity);
			for (Map<String,Object> valueMap : scriptSqlResulJDBCVO.getListaData()) {
				T resultadoTemp = entityClassEntity.newInstance();
				for (AtributoEntityVO objAtr : listaAtributos) {
					if (scriptSqlResulJDBCVO.getListaHeader().contains(objAtr.getNombreAtributo().toUpperCase())) {
						Object value = null;
						try {
							Field f = resultadoTemp.getClass().getDeclaredField(objAtr.getNombreAtributo());
							f.setAccessible(true);
							value = obtenerValor(valueMap.get(objAtr.getNombreAtributo().toUpperCase()) + "", objAtr,false,formatoFechaMap) ;
							if (value != null) {
								f.set(resultadoTemp, value) ;
							}
						} catch (Exception e) {
							//log.error("No se pudo transferir en el campo " + objAtr.getNombreAtributo() + " el dato " + value );
						}
						
					}
				}	
				resultado.add(resultadoTemp);
			}
			
			return resultado;
		} catch (Exception e) {
			log.error("Error TransferDataObjectUtil.transferObjetoEntityListVO(Object ressul,Class<T> entityClassEntity) al parsear " + entityClassEntity.getName() + "  " + e.getMessage() );
		}
		return null;
	}
	/**
	 * Transfer objeto entity list.
	 *
	 * @param <T> el tipo generico
	 * @param <E> el tipo de elemento
	 * @param ressulList el ressul list
	 * @param entityClassEntity el entity class entity
	 * @return the list
	 */
	public static <T, E> List<T>  transferObjetoEntityList(List<E> ressulList,Class<T> entityClassEntity,String... entityClasess) {
		List<T> resultado = new ArrayList<T>();
		if (ressulList == null) {
			return resultado;
		}
		for (Object ressul : ressulList) {
			T resultadoTemp = transferObjetoEntity(ressul, entityClassEntity,entityClasess);
			resultado.add(resultadoTemp);
		}
		return resultado;
	}
	
}