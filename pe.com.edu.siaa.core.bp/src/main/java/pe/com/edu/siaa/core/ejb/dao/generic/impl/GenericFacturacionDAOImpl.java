package pe.com.edu.siaa.core.ejb.dao.generic.impl;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.util.log.Logger;
import pe.com.edu.siaa.core.model.util.ConfiguracionEntityManagerUtil;

/**
 * La Class GenericFacturacionDAOImpl.
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
public class GenericFacturacionDAOImpl<K,T> extends GenericOperacionDAOImpl {

	private Logger log = Logger.getLogger(GenericFacturacionDAOImpl.class);

	/** El objeto entity manager. */
	@PersistenceContext(unitName = ConfiguracionEntityManagerUtil.FACTURACION_MODEL)
	//@Inject
	private EntityManager entityManager;

	public GenericFacturacionDAOImpl () {
		
	}
	
	/**
	    * saveNative.
	    *
	    * @param entity el entity
	    * @return the t
	    */
		public T saveNative(T entity) throws Exception {
			try {
				Map<String, Object> parametros = TransferDataObjectUtil.transferObjetoEntityAtributeMap(entity);
				String sql = saveInsert(entity.getClass());
				log.info(" saveNative sql : " + sql);
				log.info(" saveNative parametros : " + parametros);
				Query query = createNativeQuery(sql, parametros);
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
	public T save(T entity) {
    	entityManager.persist(entity);
    	return entity;
    }

    /**
     * Update.
     *
     * @param entity el entity
     * @return the t
     */
	public T update(T entity) {
		entity = entityManager.merge(entity);
		return entity;
    }

    /**
     * Delete.
     *
     * @param entity el entity
     * @return the t
     */
	public T delete(T entity) {
    	entityManager.remove(entity);
    	return entity;
    }

    
	/**
	 * Find.
	 *
	 * @param classs el classs
	 * @param id el id
	 * @return the t
	 */
	public T find(Class<T> classs, K id) {
    	return entityManager.find(classs, id);
    }

	
	/**
	 * Creates the named query.
	 *
	 * @param arg0 el arg0
	 * @param parametraMap el parametra map
	 * @return the query
	 */
	public Query createNamedQuery(String arg0, Map<String,Object> parametraMap) {
		Query query = entityManager.createNamedQuery(arg0);
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
	public Query createQuery(String arg0, Map<String,Object> parametraMap) {
		Query query = entityManager.createQuery(arg0);
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
	public Query createNativeQuery(String arg0, Map<String,Object> parametraMap) {
		Query query = entityManager.createNativeQuery(arg0);
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
	 * @param arg1 el arg1
	 * @param parametraMap el parametra map
	 * @return the query
	 */
	public Query createNativeQuery(String arg0, Class<?> arg1, Map<String,Object> parametraMap) {
		Query query = entityManager.createNativeQuery(arg0, arg1);
		if (parametraMap != null) {
			for (Map.Entry<String, Object> entry : parametraMap.entrySet()) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
		return query;
	}
}
