package pe.com.edu.siaa.core.ejb.dao.contabilidad.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.edu.siaa.core.ejb.dao.contabilidad.local.ConfiguracionCuentaDaoLocal;
import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericContabilidadDAOImpl;
import pe.com.edu.siaa.core.ejb.factory.CollectionUtil;
import pe.com.edu.siaa.core.ejb.util.jms.UUIDUtil;
import pe.com.edu.siaa.core.model.dto.contabilidad.ConfiguracionCuentaDTO;
import pe.com.edu.siaa.core.model.estate.EstadoGeneralState;
import pe.com.edu.siaa.core.model.jpa.contabilidad.ConfiguracionCuenta;
import pe.com.edu.siaa.core.model.util.StringUtils;

/**
 * La Class ConfiguracionCuentaDaoImpl.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Mon Oct 16 10:52:28 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
public class ConfiguracionCuentaDaoImpl extends  GenericContabilidadDAOImpl<String, ConfiguracionCuenta> implements ConfiguracionCuentaDaoLocal  {

	@Override	 
	public Map<Long,ConfiguracionCuenta> listarConfiguracionCuentaMap(List<Long> listaIdItem,EstadoGeneralState estadoGeneralState) throws Exception {
		Map<Long,ConfiguracionCuenta> resultado = new HashMap<Long,ConfiguracionCuenta>();
		if (CollectionUtil.isEmpty(listaIdItem)) {
			return resultado;
		}
		Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        jpaql.append(" select o from ConfiguracionCuenta o  left join fetch o.planContable  left join fetch o.itemByLibro  where 1=1 ");           
		
	    jpaql.append(" and o.itemByLibro.idItem in (:listaIdItem) ");
	    parametros.put("listaIdItem", listaIdItem);	    
        if (!StringUtils.isNullOrEmpty(estadoGeneralState)) {
			jpaql.append(" and o.estado =:estado ");
			parametros.put("estado", estadoGeneralState.getKey());
		}
        Query query = createQuery(jpaql.toString(), parametros);
        List<ConfiguracionCuenta> resulTemp = query.getResultList();
        if (!CollectionUtil.isEmpty(resulTemp)) {
        	for (ConfiguracionCuenta configuracionCuenta : resulTemp) {
        		resultado.put(configuracionCuenta.getItemByLibro().getIdItem(), configuracionCuenta);
			}
        }
		return resultado;
	}
    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.contabilidad.local.ConfiguracionCuentaDaoLocal#listarConfiguracionCuenta(pe.com.edu.siaa.core.model.jpa.contabilidad.ConfiguracionCuenta)
     */  
    @Override	 
    public List<ConfiguracionCuenta> listarConfiguracionCuenta(ConfiguracionCuentaDTO configuracionCuenta) {
        Query query = generarQueryListaConfiguracionCuenta(configuracionCuenta, false);
        query.setFirstResult(configuracionCuenta.getStartRow());
        query.setMaxResults(configuracionCuenta.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista ConfiguracionCuenta.
     *
     * @param ConfiguracionCuentaDTO el configuracionCuenta
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaConfiguracionCuenta(ConfiguracionCuentaDTO configuracionCuenta, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idConfiguracionCuenta) from ConfiguracionCuenta o where 1=1 ");
        } else {
            jpaql.append(" select o from ConfiguracionCuenta o  left join fetch o.planContable  left join fetch o.itemByLibro left join fetch o.entidad  where 1=1 ");           
        }
		if (!StringUtils.isNullOrEmpty(configuracionCuenta.getSearch())) {
	          jpaql.append(" and upper(o.idConfiguracionCuenta) like :search ");
	          parametros.put("search", "%" + configuracionCuenta.getSearch().toUpperCase() + "%");
	    } else {
			if (!StringUtils.isNullOrEmpty(configuracionCuenta.getEstado())) {
				jpaql.append(" and upper(o.estado) like :estado ");
				parametros.put("estado", "%" + configuracionCuenta.getEstado().toUpperCase() + "%");
			}
		}
        if (!esContador) {
            //jpaql.append(" ORDER BY 1 ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.contabilidad.local.ConfiguracionCuentaDaoLocal#contarListar{entity.getClassName()}(pe.com.edu.siaa.core.model.jpa.contabilidad.ConfiguracionCuentaDTO)
     */
	@Override
    public int contarListarConfiguracionCuenta(ConfiguracionCuentaDTO configuracionCuenta) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaConfiguracionCuenta(configuracionCuenta, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.contabilidad.local.ConfiguracionCuentaDaoLocal#generarIdConfiguracionCuenta()
     */
	 @Override
    public String generarIdConfiguracionCuenta() {
        return UUIDUtil.generarElementUUID();
    }
   
}