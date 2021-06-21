package pe.com.edu.siaa.core.ui.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import pe.com.edu.siaa.core.ejb.util.cache.SessionUtil;
import pe.com.edu.siaa.core.ejb.util.excel.DataExportExcelPersonalizadoUtil;
import pe.com.edu.siaa.core.ejb.util.log.Logger;
import pe.com.edu.siaa.core.model.type.TipoReporteGenerarType;
import pe.com.edu.siaa.core.model.util.ConstanteConfigUtil;
import pe.com.edu.siaa.core.model.vo.FileVO;
import pe.com.edu.siaa.core.model.vo.ScriptSqlResulVO;

/**
 * <ul>
 * <li>Copyright 2017 ndavilal. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class DataExportZip.
 *
 * @author ndavilal
 * @version 1.0 , 08/04/2015
 * @since SIAA-CORE 2.1
 */
public class DataExportZip implements Serializable {

	
	/** La Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** La Constante RUTA_RECURSOS. */
	public static final String RUTA_RECURSOS_BYTE_BUFFER = ConstanteConfigUtil.RUTA_RECURSOS_BYTE_BUFFER;

	
	/** La log. */
	private static Logger log = Logger.getLogger(DataExportExcelPersonalizado.class);
	
	/**
	 * Generar archivos excel zipeado object view.
	 *
	 * @param listaData el lista data
	 * @param archivoTitulo el archivo titulo
	 * @param propiedadesMap el propiedades map
	 * @param nombreZip el nombre zip
	 * @return the string
	 */
	public static String generarArchivosExcelZipeadoObjectView(List<ScriptSqlResulVO>listaData,Map<String,String> archivoTitulo,Map<String,Object> propiedadesMap, String nombreZip) {
		int contador = 0;
		List<FileVO> listaObjetoVo = new ArrayList<FileVO>();
		Map<String,File> listaFileMap = new HashMap<String, File>();
		for (ScriptSqlResulVO d : listaData) {
			 contador ++;	
			 String nombre = archivoTitulo.get(contador + "_archivoName") + ".xlsx";
			 DataExportExcelPersonalizadoUtil.generarExcelXLSXMap(d.getListaHeader(), new HashMap<String,String>(), d.getListaData(), archivoTitulo.get(contador + "_archivoName"), archivoTitulo.get(contador + "_titulo"), propiedadesMap,null);
			 listaFileMap.put(nombre, new File(RUTA_RECURSOS_BYTE_BUFFER + nombre));
			 FileVO objeto = new FileVO();
			 objeto.setName(nombre);
			// objeto.setData(byteTemp);
			 objeto.setMime(TipoReporteGenerarType.XLS.getContentType());
			 //objeto.setLength(byteTemp.length);
			 listaObjetoVo.add(objeto);
		}
		nombreZip = generarZip( listaObjetoVo,listaFileMap, nombreZip);
		return nombreZip;
	}
	public static String generarZip(List<FileVO> listaObjetoVo,Map<String,File> listaFileMap,String nombreZip) {
		 generarZipObject(listaObjetoVo, listaFileMap, nombreZip);
		 generarFileVO(nombreZip);		 
		return nombreZip;
	}
	private static void  generarFileVO(String nombreZip) {
		FileVO objetoZip = new FileVO();
		 objetoZip.setTipoReporteGenerarType(TipoReporteGenerarType.ZIP);
		 objetoZip.setName(nombreZip + ".zip");
		 objetoZip.setData(null);
		 objetoZip.setDataBig(nombreZip + ".zip");
		 objetoZip.setMime(TipoReporteGenerarType.ZIP.getContentType());
		 SessionUtil.pasarParametro(nombreZip, objetoZip);	
	}
	/**
	 * Generar zip object.
	 *
	 * @param listaVo el lista vo
	 * @param archivoName el archivo name
	 * @param propiedadesMap el propiedades map
	 * @param archivosExcelAgregar el archivos excel agregar
	 * @return the byte[]
	 */
	public static void generarZipObject(List<FileVO> listaVo,Map<String,File> listaFileMap,String archivoName) {
		try {
			File archivoZip = new File(RUTA_RECURSOS_BYTE_BUFFER) ;
			if (!archivoZip.isFile()) {
				archivoZip.mkdirs();
			}
			FileOutputStream fos = new FileOutputStream(RUTA_RECURSOS_BYTE_BUFFER + "" + archivoName + ".zip");
			ZipOutputStream zos = new ZipOutputStream(fos);
			// This sets the compression level to STORED, ie, uncompressed
			zos.setLevel(ZipOutputStream.STORED);
			for (FileVO fileVO : listaVo)  {
				addToZipFile(fileVO, listaFileMap.get(fileVO.getName()), zos);
			}
			zos.close();
			fos.close();
		    listaVo = null;
		} catch (Exception ex) {
			 log.error(ex.getMessage(), ex);
		}
	}
	public static boolean descomprimirZipObject(String ruta,String rutaDestino) {
		boolean resultado = true;
		ZipInputStream zis = null;
		try {
			File fileDestino = new File(rutaDestino);
			if (!fileDestino.exists()) {
				fileDestino.mkdirs();
			}
			zis = new ZipInputStream(new FileInputStream(ruta));
			ZipEntry entrada;
			while (null != (entrada=zis.getNextEntry()) ){
			   FileOutputStream fos = new FileOutputStream(rutaDestino + ConstanteConfigUtil.SEPARADOR_FILE +  entrada.getName());
			   int leido;
			   byte [] buffer = new byte[2048];
			   while (0 < ( leido = zis.read(buffer))) {
			      fos.write(buffer,0,leido);
			   }
			   fos.close();
			   zis.closeEntry();
			}
		} catch (Exception ex) {
			 log.error(ex.getMessage(), ex);
			 resultado = false;
		} finally {
	         try {
	             if ( zis != null) {
	            	 zis.close();
	             }
	       } catch(Exception ee) {
	    	   log.error(ee.getMessage(), ee);
	    	   resultado = false;
	       }
		}
		return resultado;
	}
	public static void addToZipFile(FileVO fileVO,File file, ZipOutputStream zos) throws FileNotFoundException, IOException {
		//log.info("Writing '" + fileVO.getName() + "' to zip file");
		FileInputStream fis = new FileInputStream(file);
		ZipEntry zipEntry = new ZipEntry(fileVO.getName());
		zos.putNextEntry(zipEntry);
		byte[] bytes = new byte[2048];
		int length;
		while ((length = fis.read(bytes)) >= 0) {
			zos.write(bytes, 0, length);
		}
		zos.closeEntry();
		fis.close();
	}

}
