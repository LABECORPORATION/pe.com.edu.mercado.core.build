package pe.com.edu.siaa.core.ejb.dao.generic.impl;

import java.sql.Connection;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.sql.DataSource;

import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.util.log.Logger;

/**
 * La Class GenericIntegrationDAOImpl.
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
public class GenericAutomaticoDAOImpl<K,T> extends GenericOperacionDAOImpl {

	private Logger log = Logger.getLogger(GenericAutomaticoDAOImpl.class);
	/** El objeto session factory. */
	
	public GenericAutomaticoDAOImpl () {
		
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
	
	/**
	    * saveNative.
	    *
	    * @param entity el entity
	    * @return the t
	    */
		public T saveNative(T entity,EntityManager sessionHibernate) throws Exception {
			try {
				Map<String, Object> parametros = TransferDataObjectUtil.transferObjetoEntityAtributeMap(entity);
				String sql = saveInsert(entity.getClass());
				log.info(" saveNative sql : " + sql);
				log.info(" saveNative parametros : " + parametros);
				Query query = createNativeQuery(sql, parametros,sessionHibernate);
				query.executeUpdate();
			} catch (Exception e) {
				log.error(e);
			}
			return entity;
	   }
    /**
     * Save.
     *
     * @param entity el entity
     * @return the t
     */
	public T save(T entity,EntityManager sessionHibernate) {
		sessionHibernate.persist(entity);
    	return entity;
    }

    /**
     * Update.
     *
     * @param entity el entity
     * @return the t
     */
	public T update(T entity,EntityManager sessionHibernate) {
		entity = sessionHibernate.merge(entity);
		return entity;
    }

    /**
     * Delete.
     *
     * @param entity el entity
     * @return the t
     */
	public T delete(T entity,EntityManager sessionHibernate) {
		sessionHibernate.remove(entity);
    	return entity;
    }

    
	/**
	 * Find.
	 *
	 * @param classs el classs
	 * @param id el id
	 * @return the t
	 */
	public T find(Class<T> classs, K id,EntityManager sessionHibernate) {
    	return sessionHibernate.find(classs, id);
    }

	
	/**
	 * Creates the named query.
	 *
	 * @param arg0 el arg0
	 * @param parametraMap el parametra map
	 * @return the query
	 */
	public Query createNamedQuery(String arg0, Map<String,Object> parametraMap,EntityManager sessionHibernate) {
		Query query = sessionHibernate.createNamedQuery(arg0);
		if (parametraMap != null) {
			for (Map.Entry<String, Object> entry : parametraMap.entrySet()) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
		return query;
	}
	
	
	/**
	 * Creates the query.
	 *
	 * @param arg0 el arg0
	 * @param parametraMap el parametra map
	 * @return the query
	 */
	public Query createQuery(String arg0, Map<String,Object> parametraMap,EntityManager sessionHibernate) {
		Query query = sessionHibernate.createQuery(arg0);
		if (parametraMap != null) {
			for (Map.Entry<String, Object> entry : parametraMap.entrySet()) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
		return query;
	}
	
	/**
	 * Creates the native query.
	 *
	 * @param arg0 el arg0
	 * @param parametraMap el parametra map
	 * @return the query
	 */
	public Query createNativeQuery(String arg0, Map<String,Object> parametraMap,EntityManager sessionHibernate) {
		Query query = sessionHibernate.createNativeQuery(arg0);
		if (parametraMap != null) {
			for (Map.Entry<String, Object> entry : parametraMap.entrySet()) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
		return query;
	}
	
	/**
	 * Creates the native query.
	 *
	 * @param arg0 the arg0
	 * @param parametraMap the parametra map
	 * @param classs the classs
	 * @return the query
	 */
	public Query createNativeQuery(String arg0, Map<String,Object> parametraMap,Class<?> classs,EntityManager sessionHibernate) {
		Query query = sessionHibernate.createNativeQuery(arg0,classs);
		if (parametraMap != null) {
			for (Map.Entry<String, Object> entry : parametraMap.entrySet()) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
		return query;
	}
	public Query createNativeQueryCache(String arg0, Map<String,Object> parametraMap,EntityManager sessionHibernate) {
		Query query = sessionHibernate.createNativeQuery(arg0);
		if (parametraMap != null) {
			for (Map.Entry<String, Object> entry : parametraMap.entrySet()) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
		//query.setHint(QueryHints.HINT_CACHEABLE, Boolean.TRUE);
		return query;
	}
	public Query createNativeQueryInsert(String arg0, Map<Integer,Object> parametraMap,EntityManager sessionHibernate) {
		Query query = sessionHibernate.createNativeQuery(arg0);
		if (parametraMap != null) {
			for (Map.Entry<Integer, Object> entry : parametraMap.entrySet()) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
		return query;
	}
	
	/**
	 * Creates the native query.
	 *
	 * @param arg0 el arg0
	 * @param arg1 el arg1
	 * @param parametraMap el parametra map
	 * @return the query
	 */
	public Query createNativeQuery(String arg0, Class<?> arg1, Map<String,Object> parametraMap,EntityManager sessionHibernate) {
		Query query = sessionHibernate.createNativeQuery(arg0, arg1);
		if (parametraMap != null) {
			for (Map.Entry<String, Object> entry : parametraMap.entrySet()) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
		return query;
	}
}
