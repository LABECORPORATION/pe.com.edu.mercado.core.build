package scriptlets;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import pe.com.edu.siaa.core.ejb.factory.AtributosEntityCacheUtil;
import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.util.excel.ExcelUtil;
import pe.com.edu.siaa.core.ejb.util.jms.UUIDUtil;
import pe.com.edu.siaa.core.model.dto.contabilidad.PlanContableDTO;
import pe.com.edu.siaa.core.model.vo.AtributoEntityVO;


/**
 * <ul>
 * <li>Copyright 2017 ndavilal. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class Test.
 *
 * @author ndavilal
 * @version 1.0 , 07/04/2015
 * @since SIAA-CORE 2.1
 */
public class MigrarTablaTablaPlanContable {

	/**
	 * El metodo principal.
	 *
	 * @param args los argumentos
	 */
	public static void main(String[] args) {		
		try {
			//hoja2
			System.out.println("Inici0 PlanContableDTO Procesando exel " + new Date());
			File rutaArchivo = new File("F:\\app\\xlsx\\PCGE.xlsx");
			XSSFWorkbook hSSFWorkbook = ExcelUtil.leerExcelXlsx(rutaArchivo);
			Map<String,Integer> campoMappingExcelDetalleMap = new HashMap<String, Integer>();
			campoMappingExcelDetalleMap.put("codigo", 0);
			campoMappingExcelDetalleMap.put("nombre", 1);
			//Forma 1
			Map<String,String> dependeciaMap = new HashMap<String,String>();
			Map<String,PlanContableDTO> planContableMap = new HashMap<String,PlanContableDTO>();
			
			List<PlanContableDTO> listaItem = transferObjetoEntityExcelXlsxDTO(hSSFWorkbook, 1 , 3, campoMappingExcelDetalleMap, PlanContableDTO.class,null);
			
			System.out.println("lista Obtenida del XLSX " + listaItem.size());
			StringBuilder sql = new StringBuilder();
			sql.append("delete from conta.planContable;\n");
			for (PlanContableDTO objPlan : listaItem) {
				planContableMap.put(objPlan.getCodigo().trim(), objPlan);
				objPlan.setIdPlanContable(UUIDUtil.generarElementUUID());
				//Inicio algoritmo calculo dependecia
				String codigo = objPlan.getCodigo().trim();
				//dependencia padre
				if (codigo.length() == 3) {
					String vCodigo = codigo.substring(0, 2);
					dependeciaMap.put(codigo, vCodigo.trim());
					
				}
				if (codigo.length() == 4) {
					String vCodigo = codigo.substring(0, 3);
					dependeciaMap.put(codigo, vCodigo.trim());
					
				}
				if (codigo.length() == 5) {
					String vCodigo = codigo.substring(0, 4);
					dependeciaMap.put(codigo, vCodigo.trim());					
				}
				if (codigo.length() == 6) {
					String vCodigo = codigo.substring(0, 5);
					dependeciaMap.put(codigo, vCodigo.trim());					
				}
			}
			for (PlanContableDTO itemDTO : listaItem) {
				String idPlanContableDependencia = "null";
				if (dependeciaMap.containsKey(itemDTO.getCodigo().trim())) {
					String key = dependeciaMap.get(itemDTO.getCodigo().trim());
					if (planContableMap.containsKey(key) && planContableMap.get(key) != null) {
						idPlanContableDependencia = "'" + planContableMap.get(key).getIdPlanContable() + "'";
					} else {
						System.out.println("planContable.error " + key +" " + itemDTO.getCodigo() + " --> "+ itemDTO.getNombre());
					}
				}
				sql.append("insert into conta.planContable (idPlanContable,codigo,nombre,idPlanContableDepedencia,anho,estado) values('" + itemDTO.getIdPlanContable()+ "', '" + itemDTO.getCodigo() + "','" + itemDTO.getNombre() + "'," + idPlanContableDependencia + ",2018,'A');\n");
			}
			
			crearArchivo("conta.planContableuu", sql);
			System.out.println("fin conta.planContable.sql " + new Date());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	public  static <T> List<T>  transferObjetoEntityExcelXlsxDTO(XSSFWorkbook  workBook,int hoja,int filaData,Map<String,Integer> campoMappingExcelMap,Class<T> entityClassDTO, Integer cantidaData) {
		List<T> resultado = new ArrayList<T>();
		try {
			if (workBook == null) {
				return null;
			}
			List<AtributoEntityVO> listaAtributos = AtributosEntityCacheUtil.getInstance().obtenerListaAtributos(entityClassDTO);
			XSSFSheet hssfSheet = workBook.getSheetAt(hoja - 1);
			int contador = 0;
			int cantidadNulos = 0;
			int cantidaDataProcesar = 0;
			while (cantidadNulos <= 2) {
				XSSFRow hssfRow = hssfSheet.getRow(contador);
				if (hssfRow == null) {
					cantidadNulos++;
				}
				contador++;
				if (contador >= filaData) {
					cantidaDataProcesar++;
					if (hssfRow != null) {
						T resultadoTemp = entityClassDTO.newInstance();
						for (AtributoEntityVO objAtr : listaAtributos) {
							if (campoMappingExcelMap.containsKey(objAtr.getNombreAtributo())) {
								Field f = resultadoTemp.getClass().getDeclaredField(objAtr.getNombreAtributo());
								f.setAccessible(true);
								Object value = TransferDataObjectUtil.obtenerValorXlsx(hssfRow, campoMappingExcelMap.get(objAtr.getNombreAtributo()), objAtr);
								if (value != null) {
									f.set(resultadoTemp, value) ;
								}
							}
						}	
						resultado.add(resultadoTemp);
					}
					
					if (cantidaData != null) {
						if (cantidaDataProcesar == cantidaData) {
							break;
						}
					}
				}
				
			}	
			if (workBook != null) {
				workBook.close();
			}
		} catch (Exception e) {
			System.err.println("Error TransferDataObjectUtil.transferObjetoEntityExcelXlsxDTO(Object ressul,Class<T> entityClassDTO) al parsear " + entityClassDTO.getName() + "  " + e.getMessage() );
		}
		return resultado;
	}
	
	
	
	 private static void crearArchivo(String nombre,StringBuilder data) {
	        try {
	            String ruta = "f:/app/" + nombre + ".sql";
	            File archivo = new File(ruta);
	            BufferedWriter bw;
	            archivo.delete();
	            if(archivo.exists()) {
	                 archivo.delete();
	            } 
	             bw = new BufferedWriter(new FileWriter(archivo));
	             bw.write(data.toString());
	            bw.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	    }	
	
	
	

}
