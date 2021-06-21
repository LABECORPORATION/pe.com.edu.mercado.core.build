package pe.com.edu.siaa.core.ejb.factory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Id;
import javax.persistence.Table;

import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import pe.com.edu.siaa.core.ejb.util.log.Logger;
import pe.com.edu.siaa.core.model.util.StringUtils;
import pe.com.edu.siaa.core.model.vo.AtributoEntityVO;

/**
 * La Class AtributosEntityCacheUtil.
 * <ul>
 * <li>Copyright 2017 ndavilal-
 * mapfre. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Tue Apr 29 19:20:13 COT 2017
 * @since SIAA-CORE 2.1
 */
public class AtributosEntityCacheUtil {

	/** La log. */
	private static Logger log = Logger.getLogger(AtributosEntityCacheUtil.class);
	
	/** La configurador cache utl. */
	private static AtributosEntityCacheUtil atributoEntityCacheUtl = null;
	
	
   /** El resultado valor variable. */
   private static  HashMap<String, List<AtributoEntityVO>> entityAtributoMap = new HashMap<String, List<AtributoEntityVO>>();
   private static  HashMap<String, Integer> entityAtributoCantidaCamposMap = new HashMap<String, Integer>();
   
  	/** El flag cargo listado. */
	private boolean flagCargoListado = false;
	
	/**
	 * Instancia un nuevo administracion cache utl.
	 */
	public AtributosEntityCacheUtil() {
		
	}
	
	/**
	 * Instanciar.
	 *
	 * @return the configurador cache utl
	 */
	public static AtributosEntityCacheUtil getInstance() {
		if (atributoEntityCacheUtl == null) {
			createInstance();
		} else if (!atributoEntityCacheUtl.isFlagCargoListado()) {
			atributoEntityCacheUtl.sincronizarData();
		}
		return atributoEntityCacheUtl;
	}
	 /**
     * Creates the instance.
     */
    private static synchronized void createInstance() {
    	if (atributoEntityCacheUtl == null) {
			atributoEntityCacheUtl = new AtributosEntityCacheUtil();
			atributoEntityCacheUtl.sincronizarData();
		}
    }

	/**
	 * Sincronizar data.
	 */
	public   void sincronizarData() {
		sincronizarAtributos();
	}

	/**
	 * Obtener atributos.
	 *
	 * @param <T> el tipo generico
	 * @param alias el alias
	 * @param entityClass el entity class
	 * @param isNative el is native
	 * @return the string
	 */
	public static String obtenerAtributos(String alias, String entityClass, boolean isNative) {
		List<AtributoEntityVO> listaAtributos = entityAtributoMap.get(entityClass);
		return obtenerAtributos(alias, listaAtributos,isNative);
	}
	
	/**
	 * Obtener atributos.
	 *
	 * @param <T> el tipo generico
	 * @param alias el alias
	 * @param listaAtributos el lista atributos
	 * @return the string
	 */
	public static  String obtenerAtributos(String alias, List<AtributoEntityVO> listaAtributos,boolean isNative ) {
		StringBuilder resultado = new StringBuilder();
		int cantidad = listaAtributos.size();
		int contador = 0;
		for (AtributoEntityVO atributoEntityVO : listaAtributos) {
			if (atributoEntityVO.getNombreColumna() != null || atributoEntityVO.isPKCompuesta()) {
				contador++;
				if (alias != null) {
					resultado.append(alias);
					resultado.append(".");
				}
				if (isNative) {
					if (!atributoEntityVO.isPKCompuesta())  {
						resultado.append(atributoEntityVO.getNombreColumna());
					} else {
						resultado.append(obtenerAtributos(null,atributoEntityVO.getListaAtributoEntityVOPK(),true));
					}
				} else {
					if (!atributoEntityVO.isPKCompuesta())  {
						resultado.append(atributoEntityVO.getNombreAtributo());
					} else {
						resultado.append(obtenerAtributos(null,atributoEntityVO.getListaAtributoEntityVOPK(),true));
					}
				}
				
				if (contador != cantidad) {
					resultado.append(",");
				}
			}
		}
		return resultado.toString();
	}
	
	public static  String obtenerAtributosValues(List<AtributoEntityVO> listaAtributos) {
		StringBuilder resultado = new StringBuilder();
		int cantidad = listaAtributos.size();
		int contador = 0;
		for (AtributoEntityVO atributoEntityVO : listaAtributos) {
			if (!StringUtils.isNullOrEmpty(atributoEntityVO.getNombreColumna()) || atributoEntityVO.isPKCompuesta() ) {
				contador++;
				if (atributoEntityVO.isPKCompuesta())  {
					resultado.append(obtenerAtributosValues(atributoEntityVO.getListaAtributoEntityVOPK()));
				} else {
					resultado.append(":");
					resultado.append(atributoEntityVO.getNombreAtributo());
				}
				if (contador != cantidad) {
					resultado.append(",");
				}
			}
		}
		return resultado.toString();
	}
	
	/**
	 * Obtener lista atributos.
	 *
	 * @param <T> the generic type
	 * @param entityClass the entity class
	 * @return the list
	 */
	public static List<AtributoEntityVO> obtenerListaAtributos(String entityClass) {
		return entityAtributoMap.get(entityClass);
	}
	public static  Integer obtenerListaAtributosCantidad(String entityClass) {
		return entityAtributoCantidaCamposMap.get(entityClass);
	}
	/**
	 * Obtener lista atributos.
	 *
	 * @param <T> the generic type
	 * @param entityClass the entity class
	 * @return the list
	 */
	public static <T> List<AtributoEntityVO> obtenerListaAtributos(Class<T> entityClass) {
		if (!entityAtributoMap.containsKey(entityClass.getName())){
			try {
				List<AtributoEntityVO> listaAtrivuto = obtenerAtributosColunm(entityClass,true,0);
				entityAtributoMap.put(entityClass.getName(), listaAtrivuto);
			} catch (Exception e) {
				log.error(e);
			}
			
		} 
		return entityAtributoMap.get(entityClass.getName());
	}
	
	/**
	 * Prints the fields.
	 *
	 * @param <T> el tipo generico
	 * @param dtoTemp el dto temp
	 * @return the list
	 * @throws Exception the exception
	 */
	private static <T> List<AtributoEntityVO>  obtenerAtributosColunm(Class<T> dtoTemp, boolean isClaseNormal, int posicionTemp) throws Exception {
		T dto =  dtoTemp.newInstance();
		/*System.out.println("dtoTemp.getName()==> " + dtoTemp.getName());
		if (dtoTemp.getName().contains("TipoPersona")) {
			System.out.println("si existe " + dtoTemp.getName());
		}*/

		List<AtributoEntityVO> resultado = new ArrayList<AtributoEntityVO>();
//		PropertyDescriptor[] propiedades = BeanUtilsBean.getInstance()
//				.getPropertyUtils().getPropertyDescriptors(dto.getClass());
		String tableName = "";
		String schema = "";
		dtoTemp.getDeclaredAnnotations();
		if (dtoTemp.isAnnotationPresent(Table.class) ) {
			Table table = dtoTemp.getAnnotation(Table.class);
			tableName = table.name();
			if (table.schema() != null) {
				schema = table.schema();
			}
		}
		Field[] propiedades = dto.getClass().getDeclaredFields();
		Class<?> current = dtoTemp.getSuperclass();
		while(current  != null && !current.getName().contains("java.lang.Object") ){ // we don't want to process Object.class
		    // do something with current's fields
			List<AtributoEntityVO> listaAtributosHerencia = AtributosEntityCacheUtil.getInstance().obtenerListaAtributos(current);
			if (listaAtributosHerencia != null) {
			    	resultado.addAll(listaAtributosHerencia);
			}
		    current = current.getSuperclass();		   
		 
		}
		
		int posicion = posicionTemp;
		int cantidadColumna = 0;
		for (Field pd : propiedades) {
			try {
				//System.out.println("pd.getName() --> " + pd.getName());
				// ENUMERADOS NO SE ENCRYPTAN
//				if (pd.getPropertyType().isEnum()) {
//					continue;
//				}
				if (pd.isEnumConstant()) {
					continue;
				}
				// SE OBTIENE EL CAMPO
				Field f = dto.getClass().getDeclaredField(pd.getName());
				// SE VALIDA QUE SEA UNA COLUMNA
				if (f != null && ((f.isAnnotationPresent(Column.class) || f.isAnnotationPresent(EmbeddedId.class)) || isClaseNormal)) {
					//System.out.println("es Column pd.getName() --> " + pd.getName());
						boolean isColum =  f.isAnnotationPresent(Column.class);
						boolean esPK =  f.isAnnotationPresent(Id.class);
						AtributoEntityVO objAtri = new AtributoEntityVO();
						objAtri.setTableName(tableName);
						objAtri.setSchema(schema);
						objAtri.setColumn(isColum);
						objAtri.setEsPK(esPK);
						if (!f.isAnnotationPresent(EmbeddedId.class)) {
							objAtri.setNombreAtributo(pd.getName());
							objAtri.setClasssAtributo(f.getType());
							objAtri.setClasssAtributoType(f.getType());
		
							if (f.isAnnotationPresent(Column.class) ) {
								cantidadColumna++;
								Column column = f.getAnnotation(Column.class);
								objAtri.setNombreColumna(column.name());
								entityAtributoCantidaCamposMap.put(dtoTemp.getName(), cantidadColumna);
								objAtri.setPosicion(posicion);
								posicion ++;
							}
							resultado.add(objAtri);							
							
					} else {
						objAtri.setListaAtributoEntityVOPK(obtenerAtributosColunm(pd.getType(), isClaseNormal,0));
						objAtri.setPKCompuesta(true);
						objAtri.setNombreAtributo(pd.getName());
						objAtri.setClasssAtributo(f.getType());
						objAtri.setClasssAtributoType(f.getType());
						objAtri.setPosicion(posicion);
						resultado.add(objAtri);
						//posicion ++;
					}
					// NO APLICA PARA ID O EMBEDDED
					
				}
			} catch (Exception e) {
				//log.error(e.getMessage(), e);
				continue;
			}
		}
		return resultado;
	}
	
	/**
	 * Sincronizar obtener listados.
	 *
	 * @param <T> el tipo generico
	 */
	private  <T> void sincronizarAtributos() {
		try {
			List<Object> listEntity = new ArrayList<Object>();			
			listEntity = obtenerClasesReflections("pe.com.edu.siaa.core.model.jpa.empresa","pe.com.edu.siaa.core.model.jpa.seguridad","pe.com.edu.siaa.core.model.jpa.common","pe.com.edu.siaa.core.model.jpa.reporte","pe.com.edu.siaa.core.model.jpa.contabilidad","pe.com.builderp.core.facturacion.model.jpa.venta","pe.com.builderp.core.facturacion.model.jpa.compra");
			entityAtributoMap = new HashMap<String, List<AtributoEntityVO>>();
			for (Object object : listEntity) {
				Class<T> classs = (Class<T>) object;
				List<AtributoEntityVO> listaAtrivuto = obtenerAtributosColunm(classs,false,0);
				entityAtributoMap.put(classs.getName(), listaAtrivuto);
			}
				
			flagCargoListado = true;
			sincronizarAtributosVO();
		} catch (Exception e) {
			log.error(e);
			flagCargoListado = false;
		}
		
	}
	private static List<Object> obtenerClasesReflections(String... paquete) {
		List<Object> resultado = new ArrayList<Object>();
		try {
			List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
			classLoadersList.add(ClasspathHelper.contextClassLoader());
			classLoadersList.add(ClasspathHelper.staticClassLoader());
			for (String classLoaderPaquete : paquete) {
				try {
					 Reflections modules = new Reflections(classLoaderPaquete);
					 Reflections reflections = new Reflections(new ConfigurationBuilder()
				    .setScanners(new SubTypesScanner(false /* don't exclude Object.class */), new ResourcesScanner())
				    .setUrls(modules.getConfiguration().getUrls())
				    .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix(classLoaderPaquete))));
					
					Set<Class<?>> classes = reflections.getSubTypesOf(Object.class);
					
					for (Class<?> class1 : classes) {
						resultado.add(class1);
					}
				} catch (Exception e) {
					log.error("No cargo para el paquete --> " + classLoaderPaquete);
					log.error(e);
				}
				
			}
			
		} catch (Exception e) {
			log.error(e);
		}
		return resultado;
	}
	
	private  <T> void sincronizarAtributosVO() {
		try {
			List<Object> listVo = new ArrayList<Object>();
    		listVo.add(  pe.com.edu.siaa.core.model.vo.ExcelHederDataVO.class);
			for (Object object : listVo) {
				Class<T> classs = (Class<T>) object;
				List<AtributoEntityVO> listaAtrivuto = obtenerAtributosColunm(classs,true,0);
				entityAtributoMap.put(classs.getName(), listaAtrivuto);
			}
			flagCargoListado = true;
		} catch (Exception e) {
			log.error(e);
			flagCargoListado = false;
		}
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
	 * Obtiene entity atributo map.
	 *
	 * @return entity atributo map
	 */
	public static HashMap<String, List<AtributoEntityVO>> getEntityAtributoMap() {
		return entityAtributoMap;
	}

	 
}
