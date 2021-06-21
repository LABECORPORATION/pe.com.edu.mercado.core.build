package pe.com.builderp.core.facturacion.ejb.dao.venta.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.builderp.core.facturacion.ejb.dao.venta.local.ConfiguracionAtributoDaoLocal;
import pe.com.builderp.core.facturacion.model.dto.venta.ConfiguracionAtributoDTO;
import pe.com.builderp.core.facturacion.model.jpa.venta.ConfiguracionAtributo;
import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericFacturacionDAOImpl;
import pe.com.edu.siaa.core.model.util.StringUtils;

/**
 * La Class ConfiguracionAtributoDaoImpl.
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
public class ConfiguracionAtributoDaoImpl extends  GenericFacturacionDAOImpl<String, ConfiguracionAtributo> implements ConfiguracionAtributoDaoLocal  {

    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.ConfiguracionAtributoDaoLocal#listarConfiguracionAtributo(pe.com.builderp.core.facturacion.model.jpa.venta.ConfiguracionAtributo)
     */  
    @Override	 
    public List<ConfiguracionAtributo> listarConfiguracionAtributo(ConfiguracionAtributoDTO configuracionAtributo) {
        Query query = generarQueryListaConfiguracionAtributo(configuracionAtributo, false);
        query.setFirstResult(configuracionAtributo.getStartRow());
        query.setMaxResults(configuracionAtributo.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista ConfiguracionAtributo.
     *
     * @param ConfiguracionAtributoDTO el configuracionAtributo
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaConfiguracionAtributo(ConfiguracionAtributoDTO configuracionAtributo, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idConfiguracionAtributo) from ConfiguracionAtributo o where 1=1 ");
        } else {
            jpaql.append(" select o from ConfiguracionAtributo o where 1=1 ");           
        }
		if (!StringUtils.isNullOrEmpty(configuracionAtributo.getSearch())) {
	          jpaql.append(" and upper(o.idConfiguracionAtributo) like :search ");
	          parametros.put("search", "%" + configuracionAtributo.getSearch().toUpperCase() + "%");
	    } else {
			if (!StringUtils.isNullOrEmpty(configuracionAtributo.getNombreAtributo())) {
				jpaql.append(" and upper(o.nombreAtributo) like :nombreAtributo ");
				parametros.put("nombreAtributo", "%" + configuracionAtributo.getNombreAtributo().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(configuracionAtributo.getTipoDato())) {
				jpaql.append(" and upper(o.tipoDato) like :tipoDato ");
				parametros.put("tipoDato", "%" + configuracionAtributo.getTipoDato().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(configuracionAtributo.getFormato())) {
				jpaql.append(" and upper(o.formato) like :formato ");
				parametros.put("formato", "%" + configuracionAtributo.getFormato().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmptyNumeric(configuracionAtributo.getNivel())) {
				jpaql.append(" and o.nivel = :nivel ");
				parametros.put("nivel", configuracionAtributo.getNivel());
			}
			if (!StringUtils.isNullOrEmptyNumeric(configuracionAtributo.getEsPersistente())) {
				jpaql.append(" and o.esPersistente = :esPersistente ");
				parametros.put("esPersistente", configuracionAtributo.getEsPersistente());
			}
			if (!StringUtils.isNullOrEmptyNumeric(configuracionAtributo.getOrden())) {
				jpaql.append(" and o.orden = :orden ");
				parametros.put("orden", configuracionAtributo.getOrden());
			}
		}
        if (!esContador) {
            //jpaql.append(" ORDER BY 1 ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.ConfiguracionAtributoDaoLocal#contarListar{entity.getClassName()}(pe.com.builderp.core.facturacion.model.jpa.venta.ConfiguracionAtributoDTO)
     */
	@Override
    public int contarListarConfiguracionAtributo(ConfiguracionAtributoDTO configuracionAtributo) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaConfiguracionAtributo(configuracionAtributo, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.ConfiguracionAtributoDaoLocal#generarIdConfiguracionAtributo()
     */
	 @Override
    public String generarIdConfiguracionAtributo() {
        String resultado = "1";
        Query query = createQuery("select max(o.idConfiguracionAtributo) from ConfiguracionAtributo o", null);
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