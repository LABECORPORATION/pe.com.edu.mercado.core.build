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
import pe.com.edu.siaa.core.model.dto.common.ItemDTO;
import pe.com.edu.siaa.core.model.dto.common.ListaItemsDTO;
import pe.com.edu.siaa.core.model.util.StringUtils;
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
public class MigrarTablaTablas {

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
			campoMappingExcelDetalleMap.put("descripcion", 0);
			
			Map<String,Integer> campoMappingExcelDetalleDataMap = new HashMap<String, Integer>();
			campoMappingExcelDetalleDataMap.put("codigoExterno", 0);
			campoMappingExcelDetalleDataMap.put("nombre", 1);
			
			//Forma 1
			List<ListaItemsDTO> listaItem = new ArrayList<ListaItemsDTO>();
			int contadorItem = 0;
			for (int i = 1; i < 13; i++) {
				List<ListaItemsDTO> listaItemTemp = transferObjetoEntityExcelXlsxDTO(hSSFWorkbook, i , 1, campoMappingExcelDetalleMap, ListaItemsDTO.class,1);
				for (ListaItemsDTO rubroHFMDTO : listaItemTemp) {
					List<ItemDTO> listaDataItem = transferObjetoEntityExcelXlsxDTO(hSSFWorkbook, i , 4, campoMappingExcelDetalleDataMap, ItemDTO.class,null);
					contadorItem = contadorItem + listaDataItem.size();
					rubroHFMDTO.setListaItemsItemList(listaDataItem);
				}
				listaItem.addAll(listaItemTemp);
				
			}
			long generarCodigo = 41 + 1;
			System.out.println("lista Obtenida del XLSX " + listaItem.size());
			StringBuilder sql = new StringBuilder();
			sql.append("delete from dbo.listaItems where idlistaitems >= " + generarCodigo+ ";\n");
			for (ListaItemsDTO objListaItem : listaItem) {
				objListaItem.setIdListaItems(generarCodigo);
				sql.append("insert into dbo.listaitems (idlistaitems,descripcion,estado) values(" + generarCodigo+ ", '" + objListaItem.getDescripcion() + "','A');\n");
				generarCodigo++;
			}
			crearArchivo("dbo.listaItem", sql);
			System.out.println("fin dbo.listaItem.sql " + new Date());
			sql = null;
			System.out.println("Inici0 item Procesando exel " + new Date());
			generarCodigo = 532 + 1;
			System.out.println("lista Obtenida del XLSX " + contadorItem);
			sql = new StringBuilder();
			sql.append("delete from commo.item where iditem >= " + generarCodigo + ";\n");
			for (ListaItemsDTO objListaItem : listaItem) {
				int codigo = 1;
				sql.append("/*INICIO DATOS DE  " + objListaItem.getDescripcion() +" */\n");
				for (ItemDTO objItem : objListaItem.getListaItemsItemList()) {
					if (StringUtils.isNullOrEmpty(objItem.getNombre())) {
						if (!StringUtils.isNullOrEmpty(objItem.getCodigoExterno())) {
							System.out.println("Eror existe un codigo sin nombre " + objItem.getCodigoExterno());
						}
						continue;
					}
					sql.append("insert into commo.item (iditem,idlistaitems,descripcion,nombre,codigo,codigoexterno,estado) values(" + generarCodigo+ ", " + objListaItem.getIdListaItems() + ",'" + objListaItem.getDescripcion() + "','" + objItem.getNombre().trim() + "'," + codigo + ",'" + objItem.getCodigoExterno() + "','A');\n");
					generarCodigo++;
					codigo++;
				}
				sql.append("/*FIN DATOS DE  " + objListaItem.getDescripcion() +" */\n");
			}
			crearArchivo("commo.item", sql);
			System.out.println("fin commo.item.sql " + new Date());
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
