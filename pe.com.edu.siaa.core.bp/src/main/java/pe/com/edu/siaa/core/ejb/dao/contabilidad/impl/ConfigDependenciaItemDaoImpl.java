package pe.com.edu.siaa.core.ejb.dao.contabilidad.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.edu.siaa.core.ejb.dao.contabilidad.local.ConfigDependenciaItemDaoLocal;
import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericContabilidadDAOImpl;
import pe.com.edu.siaa.core.model.dto.contabilidad.ConfigDependenciaItemDTO;
import pe.com.edu.siaa.core.model.jpa.contabilidad.ConfigDependenciaItem;
import pe.com.edu.siaa.core.model.util.StringUtils;

/**
 * La Class ConfigDependenciaItemDaoImpl.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Mon Oct 16 10:52:27 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
public class ConfigDependenciaItemDaoImpl extends  GenericContabilidadDAOImpl<String, ConfigDependenciaItem> implements ConfigDependenciaItemDaoLocal  {

    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.contabilidad.local.ConfigDependenciaItemDaoLocal#listarConfigDependenciaItem(pe.com.edu.siaa.core.model.jpa.contabilidad.ConfigDependenciaItem)
     */  
    @Override	 
    public List<ConfigDependenciaItem> listarConfigDependenciaItem(ConfigDependenciaItemDTO configDependenciaItem) {
        Query query = generarQueryListaConfigDependenciaItem(configDependenciaItem, false);
        query.setFirstResult(configDependenciaItem.getStartRow());
        query.setMaxResults(configDependenciaItem.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista ConfigDependenciaItem.
     *
     * @param ConfigDependenciaItemDTO el configDependenciaItem
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaConfigDependenciaItem(ConfigDependenciaItemDTO configDependenciaItem, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idConfigDependenciaItem) from ConfigDependenciaItem o where 1=1 ");
        } else {
            jpaql.append(" select o from ConfigDependenciaItem o left join fetch o.item  left join fetch o.itemHijo where 1=1 ");           
        }
        if (!StringUtils.isNullOrEmpty(configDependenciaItem.getItem())) {
        	if (StringUtils.isNullOrEmptyNumeric(configDependenciaItem.getItem().getIdItem())) {
        		jpaql.append(" and o.item.idItem =:idItem ");
        		parametros.put("idItem", configDependenciaItem.getItem().getIdItem());
        	}
	    }
		if (!StringUtils.isNullOrEmpty(configDependenciaItem.getSearch())) {
	          jpaql.append(" and (upper(o.item.nombre) or upper(o.itemHijo.nombre) ) like :search ");
	          parametros.put("search", "%" + configDependenciaItem.getSearch().toUpperCase() + "%");
	    }
		if (!StringUtils.isNullOrEmpty(configDependenciaItem.getEstado())) {
			jpaql.append(" and o.estado =:estado ");
			parametros.put("estado", configDependenciaItem.getEstado());
		}
        if (!esContador) {
            //jpaql.append(" ORDER BY 1 ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.contabilidad.local.ConfigDependenciaItemDaoLocal#contarListar{entity.getClassName()}(pe.com.edu.siaa.core.model.jpa.contabilidad.ConfigDependenciaItemDTO)
     */
	@Override
    public int contarListarConfigDependenciaItem(ConfigDependenciaItemDTO configDependenciaItem) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaConfigDependenciaItem(configDependenciaItem, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.contabilidad.local.ConfigDependenciaItemDaoLocal#generarIdConfigDependenciaItem()
     */
	 @Override
    public String generarIdConfigDependenciaItem() {
        String resultado = "1";
        Query query = createQuery("select max(o.idConfigDependenciaItem) from ConfigDependenciaItem o", null);
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