package pe.com.edu.siaa.core.ejb.dao.generic.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import pe.com.edu.siaa.core.ejb.factory.CollectionUtil;
import pe.com.edu.siaa.core.ejb.factory.ConstanteQueryParseEntityUtil;
import pe.com.edu.siaa.core.ejb.factory.EntityMapperJPAQLUtil;
import pe.com.edu.siaa.core.model.util.ObjectUtil;
import pe.com.edu.siaa.core.model.util.StringUtils;

/**
 * La Class GenericOperacionDAOImpl.
 * <ul>
 * <li>Copyright 2017 ndavilal-
 * mapfre. Todos los derechos reservados.</li>
 * </ul>
 * 
 * @param <K> el tipo de clave
 * @param <T> el tipo generico
 * @author ndavilal
 * @version 1.0, Fri Apr 25 17:57:10 COT 2017
 * @since SIAA-CORE 2.1
 */
public class GenericOperacionDAOImpl<K,T> {

	protected String saveInsert(Class<T> entityClass) {
		return EntityMapperJPAQLUtil.generarInsertNative(entityClass);
	}

	public GenericOperacionDAOImpl () {
		
	}
	
	public Map<String,Object> obtenerParametroDiscriminarTilde() {
		Map<String,Object> parametraMap = new HashMap<String, Object>();
		parametraMap.put("discriminaTildeMAC",ConstanteQueryParseEntityUtil.DISCRIMINAR_TILDE_MAYUSCULA_CONVERT);			
		parametraMap.put("discriminaTildeMAT",ConstanteQueryParseEntityUtil.DISCRIMINAR_TILDE_MAYUSCULA_TRASLATE);			
		parametraMap.put("discriminaTildeMIC",ConstanteQueryParseEntityUtil.DISCRIMINAR_TILDE_MINUSCULA_CONVERT);			
		parametraMap.put("discriminaTildeMIT",ConstanteQueryParseEntityUtil.DISCRIMINAR_TILDE_MINUSCULA_TRASLATE);
		return parametraMap;
	}
	public Map<String,Object> obtenerParametroListaIn(String nombreParametro,List<Object> listaParametroTemp) {
		Map<String,Object> parametraMap = new HashMap<String, Object>();
		 int indexDinamic = 0;
		 List<Object> listaParametro = new ArrayList<Object>(listaParametroTemp);
		 if (listaParametro.size() > 0) {
			 if (listaParametro.size() > 1000) { 
			      while (listaParametro.size() > 1000) {
			        List<Object> subList = new ArrayList<Object>(listaParametro.subList(0, 1000)) ;
			        parametraMap.put(nombreParametro + indexDinamic, subList);
			        listaParametro.subList(0, 1000).clear();
			        indexDinamic++;
			      }
			  }
			 parametraMap.put(nombreParametro, listaParametro);
		 }
		 
		return parametraMap;
	}
	
	/**
	 * Obtener parametro sql lista in.
	 *
	 * @param nombreParametro the nombre parametro
	 * @param campo the campo
	 * @param listaKeysTemp the lista parametro temp
	 * @param isIn the is in
	 * @return the string builder
	 */
	public StringBuilder obtenerParametroSqlListaIn(String nombreParametro,String campo,List<Object> listaKeysTemp, boolean isIn) {
		StringBuilder jpaql = new StringBuilder();
		 int indexDinamic = 0;
		 List<Object> listaParametro = new ArrayList<Object>(listaKeysTemp);
		 if (listaParametro.size() > 0) {
			 jpaql.append(" and ( ");
			 if (listaParametro.size() > 1000) { 
			      while (listaParametro.size() > 1000) {
			        jpaql.append(" " + campo + " " + ( isIn ? "in" : " not in " ) + " (:" + nombreParametro + "" + indexDinamic + ") OR ");
			        listaParametro.subList(0, 1000).clear();
			        indexDinamic++;
			      }
			  }
			 jpaql.append("  " + campo + " " + ( isIn ? "in" : " not in " ) + "  (:" + nombreParametro + ") ) ");
		 }
		 return jpaql;
	}
	
	public StringBuilder obtenerParametroSqlListaInJDBC(String campo, List<Object> listaParametroTemp, boolean isIn) {
		StringBuilder jpaql = new StringBuilder();
		String cadena = "";
		List<Object> listaParametro = new ArrayList<Object>(listaParametroTemp);
		if (listaParametro.size() > 0) {
			jpaql.append(" and ( ");
			while (listaParametro.size() > 0) {
				List<Object> subList = new ArrayList<Object>();
				if (listaParametro.size() > 1000) {
					subList = new ArrayList<Object>(listaParametro.subList(0, 1000));
				} else {
					subList = new ArrayList<Object>(listaParametro.subList(0, listaParametro.size()));
				}
				for (Object object : subList) {
					if (!cadena.isEmpty()) {
						cadena = cadena + ", " + object.toString();
					} else {
						cadena = object.toString();
					}
				}
				jpaql.append(" " + campo + " " + (isIn ? "in" : " not in ") + " (" + cadena + ") ");
				cadena = "";
				listaParametro.subList(0, subList.size()).clear();
				if (listaParametroTemp.size() > 1000 && listaParametro.size() > 0) {
					jpaql.append(" OR ");
				}
			}
			jpaql.append(" )");
		}
		return jpaql;
	}
	
	
	public Connection getConexionDS(String nombreJNDI) throws Exception {
		Context ctx;
		ctx = new InitialContext();
		DataSource dataSource = (DataSource) ctx.lookup("java:jboss/datasources/" + nombreJNDI);
		return dataSource.getConnection();
		//return entityManager.unwrap(java.sql.Connection.class);
//		Connection connection = entityManager.unwrap(SessionImpl.class).connection();
//		return connection;

	}
	public Connection getConexionDS() throws Exception {
		Context ctx;
		ctx = new InitialContext();
		DataSource dataSource = (DataSource) ctx.lookup("java:jboss/datasources/pwrIntegrationTron2000DS");
		return dataSource.getConnection();
		//return entityManager.unwrap(java.sql.Connection.class);
//		Connection connection = entityManager.unwrap(SessionImpl.class).connection();
//		return connection;

	}
	public Map<String,String> obtenerResultadoMap(List<Object[]> listaObjetos, int cantidadKey,int posicionValue) {
		Map<String, String> resultado = new HashMap<String, String>();
		if (!CollectionUtil.isEmpty(listaObjetos)) {
			for (Object[] objects : listaObjetos) {
				String key = StringUtils.generarKey(objects, cantidadKey);
				if (!resultado.containsKey(key)) {
					resultado.put(key, ObjectUtil.objectToString(objects[posicionValue]));
				}
			}
		}
		listaObjetos = null;
		return resultado;
	}
}
