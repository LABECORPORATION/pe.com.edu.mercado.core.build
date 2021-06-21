package pe.com.edu.siaa.core.ejb.factory;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pe.com.edu.siaa.core.model.vo.AtributoEntityVO;
import pe.com.edu.siaa.core.model.vo.EntityVO;

/**
 * La Class EntityMapperJPAQLUtil.
 * <ul>
 * <li>Copyright 2017 ndavilal-
 * mapfre. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 1.0, Tue Apr 29 17:13:19 COT 2017
 * @since SIAA-CORE 2.1
 */
public class EntityMapperJPAQLUtil   implements Serializable {

	/** La Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;


	/**
	 * Instancia un nuevo data export excel.
	 */
	public EntityMapperJPAQLUtil() {
		
	}
		
	/**
	 * Obtener jpaql atributos.
	 *
	 * @param <T> the generic type
	 * @param alias the alias
	 * @param entityClass the entity class
	 * @param isNative el is native
	 * @return the string
	 */
	public static <T> String obtenerJpaqlAtributos(String alias,String entityClass, boolean isNative) {
		return AtributosEntityCacheUtil.getInstance().obtenerAtributos(alias, entityClass,isNative);
	}
	
	/**
	 * Obtener jpaql atributos.
	 *
	 * @param <T> el tipo generico
	 * @param alias el alias
	 * @param listaAtributoEntityVO el lista atributo entity vo
	 * @param isNative el is native
	 * @return the string
	 */
	public static <T> String obtenerJpaqlAtributos(String alias,List<AtributoEntityVO> listaAtributoEntityVO,boolean isNative) {
		return AtributosEntityCacheUtil.getInstance().obtenerAtributos(alias, listaAtributoEntityVO,isNative);
	}
	
	/**
	 * Obtener jpaql atributos.
	 *
	 * @param <T> the generic type
	 * @param entityClass the entity class
	 * @param distinct el distinct
	 * @param isNative el is native
	 * @return the string
	 */
	public static <T> String obtenerSelectJpaqlAtributos(List<EntityVO> entityClass, boolean distinct, boolean isNative) {
		StringBuilder jpaql = new StringBuilder();
		jpaql.append("select ");
		if (distinct) {
			jpaql.append(" distinct ");
		}
		int contador = 0;
		for (EntityVO obj : entityClass) {
			String class1 = obj.getClasss();
			if (contador > 0) {
				jpaql.append(", ");
			}
			jpaql.append(obtenerJpaqlAtributos(obj.getAlias(), class1,isNative));
			contador ++;
		}
		jpaql.append(" ");
		return jpaql.toString(); 
	}
	
	/**
	 * Obtener values insert native atributos.
	 *
	 * @param <T> el tipo generico
	 * @param entityClass el entity class
	 * @param isNative el is native
	 * @return the string
	 */
	public static <T> String generarInsertNative(Class<T> entityClass) {
		StringBuilder jpaql = new StringBuilder();
		List<AtributoEntityVO> listaAtributos = AtributosEntityCacheUtil.getInstance().obtenerListaAtributos(entityClass);
		String tableName = 	"";
		entityClass.getDeclaredAnnotations();
		if (listaAtributos != null && listaAtributos.size() > 0 ) {
			tableName = listaAtributos.get(0).getTableName();
			if (listaAtributos.get(0).getSchema() != null) {
				tableName = listaAtributos.get(0).getSchema() + "." + tableName;
			}
		}
		
		jpaql.append("Insert into "+ tableName +" ( ");
		String class1 = entityClass.getName();
		jpaql.append(obtenerJpaqlAtributos(null, class1,true));
		jpaql.append(" )");
		jpaql.append(" values ( ");
		jpaql.append(AtributosEntityCacheUtil.getInstance().obtenerAtributosValues(listaAtributos));
		jpaql.append(" )");
		return jpaql.toString(); 
	}
	
	/**
	 * Obtener select jpaql atributos.
	 *
	 * @param <T> el tipo generico
	 * @param entityClass el entity class
	 * @return the string
	 */
	public static <T> String obtenerSelectJpaqlAtributos(List<EntityVO> entityClass) {
		return obtenerSelectJpaqlAtributos(entityClass, false, false);
	}
	
	/**
	 * Obtener select native atributos.
	 *
	 * @param <T> el tipo generico
	 * @param entityClass el entity class
	 * @return the string
	 */
	public static <T> String obtenerSelectNativeAtributos(List<EntityVO> entityClass) {
		return obtenerSelectJpaqlAtributos(entityClass, false, true);
	}
	
	/**
	 * Obtener lista atributos.
	 *
	 * @param <T> the generic type
	 * @param entityClass the entity class
	 * @return the list
	 */
	public static <T> List<AtributoEntityVO> obtenerListaAtributos(String entityClass) {
		return AtributosEntityCacheUtil.getInstance().obtenerListaAtributos(entityClass);
	}
	
	/**
	 * Obtener lista atributos.
	 *
	 * @param <T> the generic type
	 * @param entityClass the entity class
	 * @param listaAtributo el lista atributo
	 * @return the list
	 */
	public static <T> List<AtributoEntityVO> obtenerListaAtributos(String entityClass,List<String> listaAtributo) {
		List<AtributoEntityVO> resultado = new ArrayList<AtributoEntityVO>();
		List<AtributoEntityVO> resulTemp =  AtributosEntityCacheUtil.getInstance().obtenerListaAtributos(entityClass);
		int posicion = 0;
		for (AtributoEntityVO atributoEntityVO : resulTemp) {
			for (String atributo : listaAtributo) {
				if (atributo.equals(atributoEntityVO.getClasssAtributo())) {
					atributoEntityVO.setPosicion(posicion);
					posicion++;
					resultado.add(atributoEntityVO);
				}
			}
		}
		return resultado;
	}
	
	
	/**
	 * Paserar objeto entity.
	 *
	 * @param <T> the generic type
	 * @param ressul the ressul
	 * @param entityClass the entity class
	 * @return the t
	 */
	public static <T> T parsearObjetoEntity(Object[]  ressul, Class<T> entityClass)  {
		try {
			List<AtributoEntityVO> listaAtributos = AtributosEntityCacheUtil.getInstance().obtenerListaAtributos(entityClass.getName());
			T resultado  = parsearObjetoEntity(ressul, entityClass, listaAtributos);
			return resultado;
		} catch (Exception e) {
	
		}
		return null;
	}
	public static <T> T parsearObjetoEntityVO(Object[]  ressul, Class<T> entityClass)  {
		try {
			List<AtributoEntityVO> listaAtributos = AtributosEntityCacheUtil.getInstance().obtenerListaAtributos(entityClass);
			T resultado  = parsearObjetoEntityVO(ressul, entityClass, listaAtributos);
			return resultado;
		} catch (Exception e) {
	
		}
		return null;
	}
	
	public static <T> List<T> parsearObjetoEntityList(List<Object[]>  ressul, Class<T> entityClass)  {
		List<T> resultado = new ArrayList<T>();
		try {
			for (Object[] Object : ressul) {
				resultado.add(parsearObjetoEntity(Object, entityClass));
			}
		} catch (Exception e) {
	     e.printStackTrace();
		}
		return resultado;
	}
	public static <T> List<T> parsearObjetoEntityVOList(List<Object[]>  ressul, Class<T> entityClass)  {
		List<T> resultado = new ArrayList<T>();
		try {
			for (Object[] Object : ressul) {
				resultado.add(parsearObjetoEntityVO(Object, entityClass));
			}
		} catch (Exception e) {
	     e.printStackTrace();
		}
		return resultado;
	}
	/**
	 * Parsear objeto entity.
	 *
	 * @param <T> el tipo generico
	 * @param ressul el ressul
	 * @param entityClass el entity class
	 * @param listaAtributos el lista atributos
	 * @return the t
	 */
	public static <T> T  parsearObjetoEntity(Object[]  ressul,Class<T> entityClass,List<AtributoEntityVO> listaAtributos) throws Exception {
		T resultado = entityClass.newInstance();
		try {
			for (AtributoEntityVO objAtr : listaAtributos) {
				if (objAtr.isColumn()) {
					Field f = resultado.getClass().getDeclaredField(objAtr.getNombreAtributo());
					f.setAccessible(true);
					Object value = TransferDataObjectUtil.obtenerValor( "" + (Object)ressul[objAtr.getPosicion()], objAtr, false) ;
					if (value != null) {
							f.set(resultado, value) ;
					}
				}
			}	
		} catch (Exception e) {
			
		}
		return resultado;
	}
	public static <T> T  parsearObjetoEntityVO(Object[]  ressul,Class<T> entityClass,List<AtributoEntityVO> listaAtributos) throws Exception {
		T resultado = entityClass.newInstance();
		try {
			for (AtributoEntityVO objAtr : listaAtributos) {
				try {
					Field f = resultado.getClass().getDeclaredField(objAtr.getNombreAtributo());
					f.setAccessible(true);
					Object value = TransferDataObjectUtil.obtenerValor( "" + (Object)ressul[objAtr.getPosicion()], objAtr, false) ;
					if (value != null) {
							f.set(resultado, value) ;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
			}	
		} catch (Exception e) {
			
		}
		return resultado;
	}
	
	/**
	 * Parsear objeto entity.
	 *
	 * @param <T> el tipo generico
	 * @param ressul el ressul
	 * @param entityClass el entity class
	 * @param listaAtributos el lista atributos
	 * @param ordenSiguente el orden siguente
	 * @return the t
	 */
	public static <T> T  parsearObjetoEntity(Object[]  ressul,Class<T> entityClass,List<AtributoEntityVO> listaAtributos,int ordenSiguente) throws Exception {
		T resultado = entityClass.newInstance();
		try {
			for (AtributoEntityVO objAtr : listaAtributos) {
				if (objAtr.isColumn()) {
					Field f = resultado.getClass().getDeclaredField(objAtr.getNombreAtributo());
					f.setAccessible(true);
					Object value = TransferDataObjectUtil.obtenerValor( "" + (Object)ressul[objAtr.getPosicion()  + ordenSiguente], objAtr, false) ;
					if (value != null) {
							f.set(resultado, value) ;
					}
				}
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}
	
	/**
	 * Parsear objeto entity.
	 *
	 * @param <T> el tipo generico
	 * @param ressul el ressul
	 * @param entityClass el entity class
	 * @param ordenSiguente el orden siguente
	 * @return the t
	 */
	public static <T> T parsearObjetoEntity(Object[]  ressul, Class<T> entityClass, int ordenSiguente)  {
		try {			
			List<AtributoEntityVO> listaAtributos = AtributosEntityCacheUtil.getInstance().obtenerListaAtributos(entityClass.getName());
			T resultado = parsearObjetoEntity(ressul, entityClass, listaAtributos, ordenSiguente);
			return resultado;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Obtener cantidad atributos.
	 *
	 * @param <T> el tipo generico
	 * @param entityClass el entity class
	 * @return the int
	 */
	public static  int obtenerCantidadAtributos(String entityClass)  {
		try {
			return AtributosEntityCacheUtil.getInstance().obtenerListaAtributosCantidad(entityClass);
		} catch (Exception e) {
			
		}
		return 0;
	}
	
	/**
	 * Obtene parametro objeto entity.
	 *
	 * @param <T> el tipo generico
	 * @param entityClass el entity class
	 * @param entityClassName el entity class name
	 * @return the map
	 */
	public static <T> Map<String,Object>  obteneParametroObjetoEntity(T entityClass,String entityClassName) {
		Map<String,Object> resultado = new HashMap<String, Object>();
		try {
			List<AtributoEntityVO> listaAtributos = obtenerListaAtributos(entityClassName);
			for (AtributoEntityVO objAtr : listaAtributos) {
				if (objAtr.isColumn()) {
					Field fValue = entityClass.getClass().getDeclaredField(objAtr.getNombreAtributo());
					fValue.setAccessible(true);
					Object value = fValue.get(entityClass);
					resultado.put(objAtr.getNombreAtributo() , value);
				}
			}	
			return resultado;
		} catch (Exception e) {
			
			
		}
		return null;
	}

}
