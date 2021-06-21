package pe.com.builderp.core.facturacion.ejb.dao.venta.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.builderp.core.facturacion.ejb.dao.venta.local.ConfiguracionAtributoValueDaoLocal;
import pe.com.builderp.core.facturacion.model.dto.venta.ConfiguracionAtributoValueDTO;
import pe.com.builderp.core.facturacion.model.jpa.venta.ConfiguracionAtributoValue;
import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericFacturacionDAOImpl;
import pe.com.edu.siaa.core.model.util.StringUtils;

/**
 * La Class ConfiguracionAtributoValueDaoImpl.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu Dec 28 12:32:56 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
public class ConfiguracionAtributoValueDaoImpl extends  GenericFacturacionDAOImpl<String, ConfiguracionAtributoValue> implements ConfiguracionAtributoValueDaoLocal  {

    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.ConfiguracionAtributoValueDaoLocal#listarConfiguracionAtributoValue(pe.com.builderp.core.facturacion.model.jpa.venta.ConfiguracionAtributoValue)
     */  
    @Override	 
    public List<ConfiguracionAtributoValue> listarConfiguracionAtributoValue(ConfiguracionAtributoValueDTO configuracionAtributoValue) {
        Query query = generarQueryListaConfiguracionAtributoValue(configuracionAtributoValue, false);
        query.setFirstResult(configuracionAtributoValue.getStartRow());
        query.setMaxResults(configuracionAtributoValue.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista ConfiguracionAtributoValue.
     *
     * @param ConfiguracionAtributoValueDTO el configuracionAtributoValue
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaConfiguracionAtributoValue(ConfiguracionAtributoValueDTO configuracionAtributoValue, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idConfiguracionAtributoValue) from ConfiguracionAtributoValue o where 1=1 ");
        } else {
            jpaql.append(" select o from ConfiguracionAtributoValue o where 1=1 ");           
        }
		if (!StringUtils.isNullOrEmpty(configuracionAtributoValue.getSearch())) {
	          jpaql.append(" and upper(o.idConfiguracionAtributoValue) like :search ");
	          parametros.put("search", "%" + configuracionAtributoValue.getSearch().toUpperCase() + "%");
	    } else {
			if (!StringUtils.isNullOrEmpty(configuracionAtributoValue.getIdTuplaEntidad())) {
				jpaql.append(" and upper(o.idTuplaEntidad) like :idTuplaEntidad ");
				parametros.put("idTuplaEntidad", "%" + configuracionAtributoValue.getIdTuplaEntidad().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(configuracionAtributoValue.getValue())) {
				jpaql.append(" and upper(o.value) like :value ");
				parametros.put("value", "%" + configuracionAtributoValue.getValue().toUpperCase() + "%");
			}
		}
        if (!esContador) {
            //jpaql.append(" ORDER BY 1 ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.ConfiguracionAtributoValueDaoLocal#contarListar{entity.getClassName()}(pe.com.builderp.core.facturacion.model.jpa.venta.ConfiguracionAtributoValueDTO)
     */
	@Override
    public int contarListarConfiguracionAtributoValue(ConfiguracionAtributoValueDTO configuracionAtributoValue) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaConfiguracionAtributoValue(configuracionAtributoValue, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.ConfiguracionAtributoValueDaoLocal#generarIdConfiguracionAtributoValue()
     */
	 @Override
    public String generarIdConfiguracionAtributoValue() {
        String resultado = "1";
        Query query = createQuery("select max(o.idConfiguracionAtributoValue) from ConfiguracionAtributoValue o", null);
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