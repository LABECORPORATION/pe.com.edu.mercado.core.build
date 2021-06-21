package scriptlets;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import pe.com.edu.siaa.core.model.dto.seguridad.PropertiesDTO;


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
public class MigrarTablaProperties {

	/**
	 * El metodo principal.
	 *
	 * @param args los argumentos
	 */
	public static void main(String[] args) {		
		try {
			System.out.println("Inici0 Properties Procesando  " + new Date());
			/** El objeto properties. */
			//hoja2
			//Forma 1
			List<PropertiesDTO> listaProperties = new ArrayList<PropertiesDTO>();
			
				String inProperties = "F:/app/properties_conta.properties";
				try {
					LinkedProperties propiedades = new LinkedProperties();
					propiedades.load(inProperties);
					Iterator<Entry<String, String>> itr = propiedades.iterator(false);
					while (itr.hasNext()) {
						Entry<String, String> objDataMap = itr.next();
						if (!(objDataMap.getKey().charAt(0) == '#')) {
							String key = objDataMap.getKey().toString();
							key = key.replace(".", ";");
							String[] keys = key.split(";");
							String descripcion = "";
							if (keys.length > 0) {
								if (keys[1].equals("frm")) {
									descripcion = "" + keys[0] + " Fomulario";
								} else if (keys[1].equals("grilla")) {
									descripcion = "" + keys[0] + " Grilla";
								}
							}
							PropertiesDTO obj = new PropertiesDTO(0L, objDataMap.getKey() + "", descripcion, objDataMap.getValue() + "", objDataMap.getValue() + "", "A");
							listaProperties.add(obj);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			long generarCodigo = 3322 + 1;
			System.out.println("lista Properties del properties " + listaProperties.size());
			StringBuilder sql = new StringBuilder();
			sql.append(" delete from segur.propertieslenguaje where idProperties >= " + generarCodigo+ ";\n");
			sql.append(" delete from segur.Properties where idProperties >= " + generarCodigo+ ";\n");
			for (PropertiesDTO objData : listaProperties) {
				sql.append("insert into segur.Properties (idProperties, name, descripcion, valueDefault, value, estado) values(" + generarCodigo+ ", '" + objData.getName() + "','" + objData.getDescripcion() + "','" + objData.getValueDefault() + "','" + objData.getValue() + "','A');\n");
				generarCodigo++;
			}
			crearArchivo("segur.Properties.final_convenio_escalafon", sql);
			System.out.println("fin dbo.Properties.final.sql " + new Date());
			sql = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	 public static InputStream obtenerArchivo(String pathFile) {
			InputStream is = null;
			try {
				File f = new File(pathFile);
				is = new FileInputStream(f);		
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
			return is;
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
