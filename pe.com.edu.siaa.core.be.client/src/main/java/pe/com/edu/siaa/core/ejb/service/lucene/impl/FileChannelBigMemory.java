package pe.com.edu.siaa.core.ejb.service.lucene.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pe.com.edu.siaa.core.model.util.ConstanteConfigUtil;

/**
 * * Java Program to read and write on RandomAccessFile in Java * using
 * FileChannle and ByteBuffer. * * @author Javin
 * */
public class FileChannelBigMemory<K, V>  {

	//private static List<Long> listaIndex = new ArrayList<Long>();

	public static void main(String args[]) {
		/*for (long i = 1; i < 3; i++) {
			OpcionDTO ipad = new OpcionDTO();
			ipad.setIdOpcion(i);
			System.out.println("Writing into RandomAcessFile : " + ipad);
			//write("prueba","OpcionDTO.store", ipad);
			OpcionDTO fromStore = new OpcionDTO();
			//fromStore = (OpcionDTO)read(listaIndex.get((int) (i-1)),"prueba","OpcionDTO.store");
			System.out.println("Object read from RandomAcessFile : " + fromStore);	
		}*/
		System.out.println("Object read from RandomAcessFile : ");	
	}
	public  static boolean writeObject(String archivoRuta,Map<String,Object> objectMap) {
		boolean resultado = true;
		for (Map.Entry<String, Object> objDataMap : objectMap.entrySet()) {
			try {
				RandomAccessFile store = null;
				if (archivoRuta == null) {
					archivoRuta = "data";
				}
				String ruta = ConstanteConfigUtil.RUTA_RECURSOS_DATA_BUFFER;
				if (archivoRuta.contains("session")) {
					ruta = "";
				}
				File file = new File( ruta + archivoRuta + ConstanteConfigUtil.SEPARADOR_FILE ); 
				if (!file.exists()) {
					file.mkdirs();
				}
				store = new RandomAccessFile(ruta +archivoRuta + ConstanteConfigUtil.SEPARADOR_FILE + objDataMap.getKey() + ".dat" , "rw");
				long newPosition = store.length();
				// creating and initializing ByteBuffer for reading/writing data
				byte[] buffer = generarByteMap(objDataMap.getValue());
				store.seek(newPosition);
				store.write(buffer);
				store.close();
			} catch (Exception e) {
				e.printStackTrace();
				resultado = false;
			}
		}
		return resultado;
	}
	public  static Map<String,Long> writeMap(String archivoRuta,List<String> listaFilename, Map<String,Object> objectMap) {
		Map<String,Long> resultado = new HashMap<String, Long>();
		try {
			// Creating RandomAccessFile for writing
			RandomAccessFile store = null;
			if (archivoRuta == null) {
				archivoRuta = "data";
			}
			String ruta = ConstanteConfigUtil.RUTA_RECURSOS_DATA_BUFFER;
			if (archivoRuta.contains("session")) {
				ruta = "";
			}
			File file = new File( ruta + archivoRuta + ConstanteConfigUtil.SEPARADOR_FILE ); 
			if (!file.exists()) {
				file.mkdirs();
			}
			store = new RandomAccessFile(ruta +archivoRuta + ConstanteConfigUtil.SEPARADOR_FILE + "data.dat" , "rw");
			// getting FileChannel from file
			for (String key : listaFilename) {
				long newPosition = store.length();
				// creating and initializing ByteBuffer for reading/writing data
				byte[] buffer = generarByteMap(objectMap.get(key));
				store.seek(newPosition);
				store.write(buffer);
				resultado.put(key, newPosition);
			}
			store.close(); // closing RandomAccess file
			//listaIndex.add(newPosition);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultado;
	}
	public static byte[]  generarByteMap(Object object) {
		byte[] buffer = null;
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream stream = new ObjectOutputStream(baos);
			stream.writeObject(object);
			stream.close();
			buffer = baos.toByteArray();
			baos = null;
			stream = null;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return buffer;
	}
	public static ByteBuffer generarByteBuffer(Object object, boolean isAllocateDirect) {
		ByteBuffer buffer = null;
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream stream = new ObjectOutputStream(baos);
			stream.writeObject(object);
			stream.close();
			byte b[] = baos.toByteArray();
			stream = null;
			baos = null;
			int size = 4 + b.length; 
			if (!isAllocateDirect) {
				buffer =  ByteBuffer.allocate(size);
			} else {
				buffer =  ByteBuffer.allocateDirect(size);
			}
			buffer.order(ByteOrder.BIG_ENDIAN);
			buffer.putInt(b.length);
			buffer.put(b);
			buffer.flip();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return buffer;
	}
	public static void removeRuta(String archivoRutaTemp) {
		String ruta = ConstanteConfigUtil.RUTA_RECURSOS_DATA_BUFFER + "" + archivoRutaTemp;
		if (archivoRutaTemp.contains("session")) {
			ruta = archivoRutaTemp;
		}
		
		limpiarArchivoAll(ruta);
			
	}
	public static void limpiarArchivoAll(String ruta) {
		try {
			 File archivo = new File(ruta);
		        if (archivo != null) {
		        	String[] ficheros = archivo.list();
		        	if (ficheros != null) {
		        		eliminarArhivo(ruta,ficheros);
		        	}
		        }
		        if (archivo.isDirectory()) {
		        	archivo.delete();
		         }
		} catch (Exception e) {
			e.printStackTrace();
		}
       
    }
	public static void eliminarArhivo(String rutaRelativa, String[] ficheros) {
        try {
            for (String fileName : ficheros) {
            	 File file = new File(rutaRelativa  + ConstanteConfigUtil.SEPARADOR_FILE + fileName);
                  if (file.list() != null && file.list().length > 0) {
                        eliminarArhivo(rutaRelativa  + ConstanteConfigUtil.SEPARADOR_FILE + fileName,file.list());
                    } else {
                        file.delete();
                   }
                if (file.isDirectory()) {
                	file.delete();
                 }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
	
	/* * Method to read data from File using FileChannel and ByteBuffeer */
	public static void remove(Map<Integer,StringBuilder> archivoRutaTemp) {
		try {
			for (Map.Entry<Integer, StringBuilder> objRuta : archivoRutaTemp.entrySet()) {
				 File file = new File(ConstanteConfigUtil.RUTA_RECURSOS_DATA_BUFFER  + objRuta.getValue().toString()  + ConstanteConfigUtil.SEPARADOR_FILE);
				if (file.exists()) {
					file.delete();
				}
				file = null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public static Map<String,Object> readObject(String archivoRuta,String... archivosName) {
		Map<String,Object> resultado = new HashMap<String, Object>();
		try {
			boolean isBuscar = false;
			if (archivosName !=null && archivosName.length > 0) {
				isBuscar = true;
			}
			Map<String,String> archivosMap = new HashMap<String, String>();
			for (String nameArchivo : archivosName) {
				archivosMap.put(nameArchivo.toUpperCase(), "");
			}
			File file = new File(archivoRuta);
			if (file.exists()) {
				for (String archivo : file.list()) {
					if (isBuscar) {
						if (!archivosMap.containsKey(archivo.toUpperCase())) {
							continue;
						}
					}
					RandomAccessFile store = null;
					if (archivoRuta != null) {
						String ruta = ConstanteConfigUtil.RUTA_RECURSOS_DATA_BUFFER;
						if (archivoRuta.contains("session")) {
							ruta = "";
						}
						store = new RandomAccessFile(ruta + archivoRuta  + ConstanteConfigUtil.SEPARADOR_FILE + archivo , "r");
					} else {
						continue;
					}
					// getting file channel
					store.seek(0);
					FileChannel inChannel = store.getChannel();
					inChannel.position(0);
					int size = (int)inChannel.size();
					byte[] buffer = new byte[size]; 
					store.read(buffer);
					Object data = leerByteBufferMap(buffer);
					resultado.put(archivo.substring(0,archivo.length() - 4), data);
					store.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}
	public static Map<String,Object> readMap(Map<String,Long> positionMap,Map<String,StringBuilder> archivoRutaTemp) {
		Map<String,Object> resultado = new HashMap<String, Object>();
		try {
			// Opening RandomAccessFile for reading data
			Map<StringBuilder,List<String>> grupoMap = new HashMap<StringBuilder, List<String>>();
			for (Map.Entry<String, StringBuilder> objRuta : archivoRutaTemp.entrySet()) {
				if (!grupoMap.containsKey(objRuta.getValue())) {
					List<String> value = new ArrayList<String>();
					value.add(objRuta.getKey());
					grupoMap.put(objRuta.getValue(), value );
				} else {
					List<String> value = grupoMap.get(objRuta.getValue());
					value.add(objRuta.getKey());
					grupoMap.put(objRuta.getValue(), value );
				}
			} 
			for (Map.Entry<StringBuilder, List<String>> objData : grupoMap.entrySet()) {
				String archivoRuta = objData.getKey().toString();
				RandomAccessFile store = null;
				if (archivoRuta != null) {
					String ruta = ConstanteConfigUtil.RUTA_RECURSOS_DATA_BUFFER;
					if (archivoRuta.contains("session")) {
						ruta = "";
					}
					
					store = new RandomAccessFile(ruta + archivoRuta  + ConstanteConfigUtil.SEPARADOR_FILE + "data.dat" , "r");
				} else {
					//store = new RandomAccessFile("C:\\svr\\mapfre\\pwr\\data_buffer\\" + filename + ".dat" , "r");
				}
				// getting file channel
				for (String key : objData.getValue()) {
					store.seek(positionMap.get(key));
					FileChannel inChannel = store.getChannel();
					inChannel.position(positionMap.get(key));
					int size = (int)inChannel.size();
					byte[] buffer = new byte[size]; 
					store.read(buffer);
					//object.recover(buffer); //TODO:
					Object data = leerByteBufferMap(buffer);
					resultado.put(key, data);
				}
				store.close();
			}
			grupoMap = null;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultado;
	}
	public static Object leerByteBuffer(ByteBuffer buffer) {
		try {
			if (buffer != null) {
				ByteBuffer bb = buffer;// ByteBuffer.wrap(allWrittenBytesTest);
				bb.order(ByteOrder.BIG_ENDIAN);
				bb.rewind();
				int extractAttributeValueLength = bb.getInt();
				byte[] extractAttributeValue = new byte[extractAttributeValueLength];
				bb.get(extractAttributeValue); // read attributeValue from the remaining
				
				ByteArrayInputStream bais = new ByteArrayInputStream(extractAttributeValue);
				ObjectInputStream oois = new ObjectInputStream(bais);
				Object object = oois.readObject();
				oois.close();
				oois = null;
				bb.clear();
				//bb.reset();
				bb = null;
				extractAttributeValue = null;
				bais = null;
				buffer.clear();
				buffer = null;
				return object;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;				
	}
	public static Object leerByteBufferMap(byte[] buffer) {
		try {
			if (buffer != null) {
				ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
				ObjectInputStream oois = new ObjectInputStream(bais);
				Object object = oois.readObject();
				oois.close();
				oois = null;
				//bb.reset();
				buffer = null;
				bais = null;
				buffer = null;
				return object;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;				
	}

}
