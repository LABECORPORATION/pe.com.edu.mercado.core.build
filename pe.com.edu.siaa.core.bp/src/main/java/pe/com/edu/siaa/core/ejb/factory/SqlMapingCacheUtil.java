package pe.com.edu.siaa.core.ejb.factory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import pe.com.edu.siaa.core.ejb.util.log.Logger;
import pe.com.edu.siaa.core.model.util.ConstanteConfigUtil;

/**
 * La Class SqlMapingCacheUtil.
 * <ul>
 * <li>Copyright 2017 ndavilal-
 * mapfre. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Tue Apr 29 19:20:13 COT 2017
 * @since SIAA-CORE 2.1
 */
public class SqlMapingCacheUtil {

	/** La log. */
	private static Logger log = Logger.getLogger(SqlMapingCacheUtil.class);
	
	/** La sql maping cache utl. */
	private static SqlMapingCacheUtil sqlMapingCacheUtl = null;
	
	
   /** El sql sentencia map. */
   private static Map<String, String> sqlSentenciaTronBuilERPMap = new HashMap<String, String>();
   
   /** El sql sentencia map. */
   private static Map<String, String> sqlSentenciaSystemMap = new HashMap<String, String>();
  
  	/** El flag cargo listado. */
	private boolean flagCargoListado = false;
	
	/**
	 * Instancia un nuevo administracion cache utl.
	 */
	public SqlMapingCacheUtil() {
		
	}
	
	/**
	 * Instanciar.
	 *
	 * @return the configurador cache utl
	 */
	public static SqlMapingCacheUtil getInstance() {
		if (sqlMapingCacheUtl == null) {
			createInstance();
		} else if (!sqlMapingCacheUtl.isFlagCargoListado()) {
			sqlMapingCacheUtl.sincronizarData();
		}
		return sqlMapingCacheUtl;
	}
	 /**
     * Creates the instance.
     */
    private static synchronized void createInstance() {
    	if (sqlMapingCacheUtl == null) {
			sqlMapingCacheUtl = new SqlMapingCacheUtil();
			sqlMapingCacheUtl.sincronizarData();
		}
    }

	/**
	 * Sincronizar data.
	 *
	 * @return the string
	 */
	public   String sincronizarData() {
		return sincronizarAtributos();
	}

	
	
	
	/**
	 * Sincronizar obtener listados.
	 *
	 * @param <T> el tipo generico
	 * @return the string
	 */
	private  String sincronizarAtributos() {
		try {
			sqlSentenciaTronBuilERPMap = new HashMap<String, String>(); 
			sqlSentenciaSystemMap = new HashMap<String, String>(); 
			
			sqlSentenciaSystemMap.putAll(obtenerSql("sqlMapSystem"));
			
			sqlSentenciaTronBuilERPMap.putAll(obtenerSql("sqlMapRegistroVenta"));
			sqlSentenciaTronBuilERPMap.putAll(obtenerSql("sqlMapRegistroCompra"));
			sqlSentenciaTronBuilERPMap.putAll(obtenerSql("sqlMapAsientoContable"));
			
			
			flagCargoListado = true;
		} catch (Exception e) {
			log.error(e);
			flagCargoListado = false;
			return e.toString();
		}
		return null;
		
	}
	
	/**
	 * Obtener sql.
	 *
	 * @param nombreArchivoXML el nombre archivo xml
	 * @return the map
	 */
	public static Map<String,String> obtenerSql(String nombreArchivoXML) {
		Map<String,String> resultado = new HashMap<String, String>();
		try {
		    DocumentBuilderFactory fabricaCreadorDocumento = DocumentBuilderFactory.newInstance();
		    DocumentBuilder creadorDocumento = fabricaCreadorDocumento.newDocumentBuilder();
		    Document documento = creadorDocumento.parse(ConstanteConfigUtil.RUTA_GENERAL_SQL + nombreArchivoXML + ".xml");
		    //Obtener el elemento raíz del documento
		    Element raiz = documento.getDocumentElement();
		    //Obtener la lista de nodos que tienen etiqueta "sql"
		    NodeList listaSql = raiz.getElementsByTagName("sql");
		    //Recorrer la lista de sqls
		    for (int i = 0; i < listaSql.getLength(); i++) {   
		        //Obtener de la lista un sql tras otro
		        Node sql = listaSql.item(i);
		        //Obtener la lista de los datos que contiene ese sql
		        NodeList datosSql = sql.getChildNodes();
		        String nombreQuery  = "";
		        //Recorrer la lista de los datos que contiene el sql
		        for (int j = 0; j < datosSql.getLength(); j++) {
		            //Obtener de la lista de datos un dato tras otro
		            Node dato = datosSql.item(j);
		            //Comprobar que el dato se trata de un nodo de tipo Element
		            if (dato.getNodeType() == Node.ELEMENT_NODE) {
		                //Mostrar el nombre del tipo de dato
		                //El valor está contenido en un hijo del nodo Element
		                Node datoContenido = dato.getFirstChild();         
		                //Mostrar el valor contenido en el nodo que debe ser de tipo Text
		                if (datoContenido != null && datoContenido.getNodeType() == Node.TEXT_NODE) {
		                    nombreQuery = datoContenido.getNodeValue();
		                }
		                if (datoContenido != null && datoContenido.getNodeType() == Node.CDATA_SECTION_NODE) {
		                    resultado.put(nombreQuery, datoContenido.getNodeValue().trim());
		                }
		                
		            }
		        }
		        //Se deja un salto de línea de separación entre cada sql
		    }

		} catch (SAXException ex) {
			log.error("Error: " + ConstanteConfigUtil.RUTA_GENERAL_SQL + nombreArchivoXML + ".xml");
			log.error("ERROR: El formato XML del fichero no es correcto\n" + ex.getMessage());
		    log.error(ex);
		} catch (IOException ex) {
			log.error("Error: " + ConstanteConfigUtil.RUTA_GENERAL_SQL + nombreArchivoXML + ".xml");
			log.error("ERROR: Se ha producido un error el leer el fichero\n" + ex.getMessage());
		    log.error(ex);
		} catch (ParserConfigurationException ex) {
			log.error("Error: " + ConstanteConfigUtil.RUTA_GENERAL_SQL + nombreArchivoXML + ".xml");
			log.error("ERROR: No se ha podido crear el generador de documentos XML\n" + ex.getMessage());
		    log.error(ex);
		}
		
		return resultado;
	}
	
	/**
	 * Comprueba si es flag cargo listado.
	 *
	 * @return true, si es flag cargo listado
	 */
	public boolean isFlagCargoListado() {
		return flagCargoListado;
	}

	/**
	 * Establece el flag cargo listado.
	 *
	 * @param flagCargoListado el new flag cargo listado
	 */
	public void setFlagCargoListado(boolean flagCargoListado) {
		this.flagCargoListado = flagCargoListado;
	}

	/**
	 * Obtiene entity sql sentencia build ero map.
	 *
	 * @return entity sql sentencia build ero  map
	 */
	public static Map<String, String> getSqlSentenciaTronBuilERPMap() {
		return sqlSentenciaTronBuilERPMap;
	}

	public static Map<String, String> getSqlSentenciaSystemMap() {
		return sqlSentenciaSystemMap;
	}


}
