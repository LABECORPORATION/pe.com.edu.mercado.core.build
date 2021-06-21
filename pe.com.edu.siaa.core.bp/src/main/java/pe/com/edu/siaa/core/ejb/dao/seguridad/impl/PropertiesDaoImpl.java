package pe.com.edu.siaa.core.ejb.dao.seguridad.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericDAOImpl;
import pe.com.edu.siaa.core.ejb.dao.seguridad.local.PropertiesDaoLocal;
import pe.com.edu.siaa.core.model.dto.seguridad.PropertiesDTO;
import pe.com.edu.siaa.core.model.jpa.seguridad.Properties;
import pe.com.edu.siaa.core.model.util.StringUtils;

/**
 * La Class PropertiesDaoImpl.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 19:53:39 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
public class PropertiesDaoImpl extends  GenericDAOImpl<Long, Properties> implements PropertiesDaoLocal  {

    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.PropertiesDaoLocal#listarProperties(pe.com.edu.siaa.core.model.jpa.seguridad.Properties)
     */  
    @Override	 
    public List<Properties> listarProperties(PropertiesDTO properties) {
        Query query = generarQueryListaProperties(properties, false);
        query.setFirstResult(properties.getStartRow());
        query.setMaxResults(properties.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista Properties.
     *
     * @param PropertiesDTO el properties
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaProperties(PropertiesDTO properties, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idProperties) from Properties o where 1=1 ");
        } else {
            jpaql.append(" select o from Properties o where 1=1 ");           
        }
		if (!StringUtils.isNullOrEmpty(properties.getSearch())) {
	          jpaql.append(" and ((upper(o.name) like :search) or (upper(o.descripcion) like :search) ) ");
	          parametros.put("search", "%" + properties.getSearch().toUpperCase() + "%");
	    } else {
			if (!StringUtils.isNullOrEmpty(properties.getName())) {
				jpaql.append(" and upper(o.name) like :name ");
				parametros.put("name", "%" + properties.getName().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(properties.getDescripcion())) {
				jpaql.append(" and upper(o.descripcion) like :descripcion ");
				parametros.put("descripcion", "%" + properties.getDescripcion().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(properties.getValueDefault())) {
				jpaql.append(" and upper(o.valueDefault) like :valueDefault ");
				parametros.put("valueDefault", "%" + properties.getValueDefault().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(properties.getValue())) {
				jpaql.append(" and upper(o.value) like :value ");
				parametros.put("value", "%" + properties.getValue().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(properties.getEstado())) {
				jpaql.append(" and upper(o.estado) like :estado ");
				parametros.put("estado", "%" + properties.getEstado().toUpperCase() + "%");
			}
		}
        if (!esContador) {
        	jpaql.append(" order by o.descripcion, o.value");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.PropertiesDaoLocal#contarListar{entity.getClassName()}(pe.com.edu.siaa.core.model.jpa.seguridad.PropertiesDTO)
     */
	@Override
    public int contarListarProperties(PropertiesDTO properties) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaProperties(properties, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.PropertiesDaoLocal#generarIdProperties()
     */
	 @Override
    public Long generarIdProperties() {
        Long resultado = 1L;
        Query query = createQuery("select max(o.idProperties) from Properties o", null);
        List<Object> listLong =  query.getResultList();
        if (listLong != null && listLong.size() > 0 && listLong.get(0) != null)  {
            Long ultimoIdGenerado = Long.valueOf(listLong.get(0).toString());
            if (!StringUtils.isNullOrEmpty(ultimoIdGenerado)) {
                resultado = resultado + ultimoIdGenerado;
            }
        }
        return resultado;
    }
   
}