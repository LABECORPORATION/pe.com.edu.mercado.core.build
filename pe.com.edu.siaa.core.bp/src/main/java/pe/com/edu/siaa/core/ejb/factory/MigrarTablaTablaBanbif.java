package pe.com.edu.siaa.core.ejb.factory;
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

import pe.com.edu.siaa.core.ejb.util.excel.ExcelUtil;
import pe.com.edu.siaa.core.model.dto.ConfiguracionTramaDetalleDTO;
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
public class MigrarTablaTablaBanbif {

	/**
	 * El metodo principal.
	 *
	 * @param args los argumentos
	 */
	public static void main(String[] args) {		
		try {
			//hoja2
			System.out.println("Inici0 listaitem Procesando exel " + new Date());
			File rutaArchivo = new File("F:\\app\\xlsx\\TABLAS.xlsx");
			XSSFWorkbook hSSFWorkbook = ExcelUtil.leerExcelXlsx(rutaArchivo);
			Map<String,Integer> campoMappingExcelDetalleMap = new HashMap<String, Integer>();
			campoMappingExcelDetalleMap.put("descripcionCampo", 0);
			
			Map<String,Integer> campoMappingExcelDetalleDataMap = new HashMap<String, Integer>();
			campoMappingExcelDetalleDataMap.put("nombreCampo", 0);
			campoMappingExcelDetalleDataMap.put("descripcionCampo", 1);
			
			//Forma 1
			List<ConfiguracionTramaDetalleDTO> listaItem = new ArrayList<ConfiguracionTramaDetalleDTO>();
			int contadorItem = 0;
			for (int i = 1; i < 13; i++) {
				List<ConfiguracionTramaDetalleDTO> listaItemTemp = transferObjetoEntityExcelXlsxDTO(hSSFWorkbook, i , 1, campoMappingExcelDetalleMap, ConfiguracionTramaDetalleDTO.class,1);
				for (ConfiguracionTramaDetalleDTO rubroHFMDTO : listaItemTemp) {
					List<ConfiguracionTramaDetalleDTO> listaDataItem = transferObjetoEntityExcelXlsxDTO(hSSFWorkbook, i , 4, campoMappingExcelDetalleDataMap, ConfiguracionTramaDetalleDTO.class,null);
					contadorItem = contadorItem + listaDataItem.size();
					rubroHFMDTO.setCampoAsociadoConfiguracionTramaDetalleList(listaDataItem);
				}
				listaItem.addAll(listaItemTemp);
				
			}
			long generarCodigo = 1;
			System.out.println("lista Obtenida del XLSX " + listaItem.size());
			StringBuilder sql = new StringBuilder();
			sql.append("delete from conta.listaItem;\n");
			for (ConfiguracionTramaDetalleDTO itemDTO : listaItem) {
				itemDTO.setOrden(generarCodigo);
				sql.append("insert into conta.listaitem (idlistaitem,descripcion,estado) values(" + generarCodigo+ ", '" + itemDTO.getDescripcionCampo() + "','A');\n");
				generarCodigo++;
			}
			crearArchivo("conta.listaItem", sql);
			System.out.println("fin conta.listaItem.sql " + new Date());
			sql = null;
			System.out.println("Inici0 item Procesando exel " + new Date());
			generarCodigo = 1;
			System.out.println("lista Obtenida del XLSX " + contadorItem);
			sql = new StringBuilder();
			sql.append("delete from conta.item;\n");
			for (ConfiguracionTramaDetalleDTO itemDTO : listaItem) {
				int codigo = 1;
				sql.append("/*INICIO DATOS DE  " + itemDTO.getDescripcionCampo() +" */\n");
				for (ConfiguracionTramaDetalleDTO data : itemDTO.getCampoAsociadoConfiguracionTramaDetalleList()) {
					sql.append("insert into conta.item (iditem,idlistaitem,descripcion,nombre,codigo,codigoexterno,estado,fechacreacion,usuariocreacion,ipacceso) values(" + generarCodigo+ ", " + itemDTO.getOrden() + ",'" + itemDTO.getDescripcionCampo() + "','" + data.getDescripcionCampo() + "'," + codigo + ",'" + data.getNombreCampo() + "','A',current_date,'admin','localhost');\n");
					generarCodigo++;
					codigo++;
				}
				sql.append("/*FIN DATOS DE  " + itemDTO.getDescripcionCampo() +" */\n");
			}
			crearArchivo("conta.item", sql);
			System.out.println("fin conta.item.sql " + new Date());
			sql = null;
			
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
