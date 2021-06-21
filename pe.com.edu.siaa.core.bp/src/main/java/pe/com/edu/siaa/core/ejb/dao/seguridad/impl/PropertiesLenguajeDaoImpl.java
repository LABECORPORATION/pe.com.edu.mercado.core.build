package pe.com.edu.siaa.core.ejb.dao.seguridad.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericDAOImpl;
import pe.com.edu.siaa.core.ejb.dao.seguridad.local.PropertiesLenguajeDaoLocal;
import pe.com.edu.siaa.core.ejb.factory.CollectionUtil;
import pe.com.edu.siaa.core.ejb.util.jms.UUIDUtil;
import pe.com.edu.siaa.core.model.dto.seguridad.PropertiesDTO;
import pe.com.edu.siaa.core.model.dto.seguridad.PropertiesLenguajeDTO;
import pe.com.edu.siaa.core.model.jpa.common.Item;
import pe.com.edu.siaa.core.model.jpa.seguridad.Properties;
import pe.com.edu.siaa.core.model.jpa.seguridad.PropertiesLenguaje;
import pe.com.edu.siaa.core.model.util.StringUtils;

/**
 * La Class PropertiesLenguajeDaoImpl.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 19:53:36 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
public class PropertiesLenguajeDaoImpl extends  GenericDAOImpl<String, PropertiesLenguaje> implements PropertiesLenguajeDaoLocal  {
	
	private static final long LENGUAJE_SPANISH = 526L;
	
	@Override
	public int actualizarPropertiesLenguaje(PropertiesDTO properties) {
		Integer resultado = 0;
		Map<String, Object> parametros = new HashMap<String, Object>();
		StringBuilder jpaql = new StringBuilder();
		jpaql.append("  select o from PropertiesLenguaje o left join fetch o.properties pro left join fetch o.itemByLenguaje it  where pro.idProperties=:idProperties and  it.idItem=:idItemLenguaje ");
		parametros.put("idProperties", properties.getIdProperties());
		parametros.put("idItemLenguaje", LENGUAJE_SPANISH);
		Query query = createQuery(jpaql.toString(), parametros);
		List<PropertiesLenguaje> resultadoTemp = query.getResultList();
		if (!CollectionUtil.isEmpty(resultadoTemp)) {
			PropertiesLenguaje propertiesLenguaje = resultadoTemp.get(0);
			propertiesLenguaje.setValue(properties.getValue());
			update(propertiesLenguaje);
			resultado = 1;
		} else {
			PropertiesLenguaje propertiesLenguaje = new PropertiesLenguaje();
			propertiesLenguaje.setIdPropertiesLenguaje(generarIdPropertiesLenguaje());
			propertiesLenguaje.setValue(properties.getValue());
			propertiesLenguaje.setProperties(new Properties());
			propertiesLenguaje.getProperties().setIdProperties(properties.getIdProperties());
			propertiesLenguaje.setItemByLenguaje(new Item(LENGUAJE_SPANISH));
			save(propertiesLenguaje);
			resultado = 1;
		}
		return resultado;
	}
	@Override
	public Map<String,Map<String,String>> obtenerPropertiesLenguajeMap(List<Long> listaIdProperties) throws Exception {
		Map<String, Map<String,String>> resultado = new HashMap<String, Map<String,String>>();
		Map<String, Object> parametros = new HashMap<String, Object>();
		if (listaIdProperties == null || listaIdProperties.size() == 0) {
			return resultado;
		}
		StringBuilder jpaql = new StringBuilder();
		//pro.name,it.codigoExterno,pro.value, o.value
		jpaql.append("select o from PropertiesLenguaje o left join fetch o.properties pro  left join fetch o.itemByLenguaje it where 1 = 1 ");
		jpaql.append(obtenerParametroSqlListaIn("listaIdProperties","pro.idProperties",listaIdProperties,true));
		parametros.putAll(obtenerParametroListaIn("listaIdProperties", listaIdProperties)); 
				
		Query query = createQuery(jpaql.toString(), parametros);
		List<PropertiesLenguaje> resul = query.getResultList();
		for (PropertiesLenguaje objData : resul) {
			String key = objData.getProperties().getName();
			String keyIdeoma = objData.getItemByLenguaje().getCodigoExterno();
			if (!resultado.containsKey(keyIdeoma)) {
				Map<String,String> ideomaMap = new HashMap<String, String>();
				ideomaMap.put(key, objData.getProperties().getValue());
				ideomaMap.put(key, objData.getValue());
				resultado.put(keyIdeoma, ideomaMap);
			} else {
				Map<String,String> ideomaMap = resultado.get(key);
				ideomaMap.put(key, objData.getValue());
				resultado.put(keyIdeoma, ideomaMap);
			}
		}
		
		return resultado;
	}
	
	
	@Override
	public Map<String,Map<String,String>> obtenerPropertiesLenguajeAllMap() {
		Map<String, Map<String,String>> resultado = new HashMap<String, Map<String,String>>();
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		StringBuilder jpaql = new StringBuilder();
		//o.properties.name,o.itemByLenguaje.codigoExterno,o.properties.value, o.value 
		jpaql.append("select o from PropertiesLenguaje o left join fetch o.properties  pro  left join fetch o.itemByLenguaje it where 1 = 1 ");
				
		Query query = createQuery(jpaql.toString(), parametros);
		List<PropertiesLenguaje> resul = query.getResultList();
		for (PropertiesLenguaje objData : resul) {
			String key = objData.getProperties().getName();
			String keyIdeoma = objData.getItemByLenguaje().getCodigoExterno();
			if (!resultado.containsKey(keyIdeoma)) {
				Map<String,String> ideomaMap = new HashMap<String, String>();
				ideomaMap.put(key, objData.getValue());
				resultado.put(keyIdeoma, ideomaMap);
			} else {
				Map<String,String> ideomaMap = resultado.get(keyIdeoma);
				ideomaMap.put(key, objData.getValue());
				resultado.put(keyIdeoma, ideomaMap);
			}
		}
		
		return resultado;
	}
	
    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.PropertiesLenguajeDaoLocal#listarPropertiesLenguaje(pe.com.edu.siaa.core.model.jpa.seguridad.PropertiesLenguaje)
     */  
    @Override	 
    public List<PropertiesLenguaje> listarPropertiesLenguaje(PropertiesLenguajeDTO propertiesLenguaje) {
        Query query = generarQueryListaPropertiesLenguaje(propertiesLenguaje, false);
        query.setFirstResult(propertiesLenguaje.getStartRow());
        query.setMaxResults(propertiesLenguaje.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista PropertiesLenguaje.
     *
     * @param PropertiesLenguajeDTO el propertiesLenguaje
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaPropertiesLenguaje(PropertiesLenguajeDTO propertiesLenguaje, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idPropertiesLenguaje) from PropertiesLenguaje o where 1=1 ");
        } else {
            jpaql.append(" select o from PropertiesLenguaje o where 1=1 ");           
        }
		if (!StringUtils.isNullOrEmpty(propertiesLenguaje.getSearch())) {
	          jpaql.append(" and upper(o.idPropertiesLenguaje) like :search ");
	          parametros.put("search", "%" + propertiesLenguaje.getSearch().toUpperCase() + "%");
	    } else {
			if (!StringUtils.isNullOrEmpty(propertiesLenguaje.getValue())) {
				jpaql.append(" and upper(o.value) like :value ");
				parametros.put("value", "%" + propertiesLenguaje.getValue().toUpperCase() + "%");
			}
		}
        if (!esContador) {
            //jpaql.append(" ORDER BY 1 ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.PropertiesLenguajeDaoLocal#contarListar{entity.getClassName()}(pe.com.edu.siaa.core.model.jpa.seguridad.PropertiesLenguajeDTO)
     */
	@Override
    public int contarListarPropertiesLenguaje(PropertiesLenguajeDTO propertiesLenguaje) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaPropertiesLenguaje(propertiesLenguaje, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.PropertiesLenguajeDaoLocal#generarIdPropertiesLenguaje()
     */
	 @Override
    public String generarIdPropertiesLenguaje() {
        return UUIDUtil.generarElementUUID();
    }
   
}