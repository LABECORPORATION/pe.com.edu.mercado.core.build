package pe.com.builderp.core.facturacion.ejb.dao.venta.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.builderp.core.facturacion.ejb.dao.venta.local.EntregaDaoLocal;
import pe.com.builderp.core.facturacion.model.dto.venta.EntregaDTO;
import pe.com.builderp.core.facturacion.model.jpa.venta.Entrega;
import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericFacturacionDAOImpl;
import pe.com.edu.siaa.core.ejb.util.jms.UUIDUtil;
import pe.com.edu.siaa.core.model.util.StringUtils;

/**
 * La Class EntregaDaoImpl.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu Dec 28 12:32:58 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
public class EntregaDaoImpl extends  GenericFacturacionDAOImpl<String, Entrega> implements EntregaDaoLocal  {

    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.EntregaDaoLocal#listarEntrega(pe.com.builderp.core.facturacion.model.jpa.venta.Entrega)
     */  
    @Override	 
    public List<Entrega> listarEntrega(EntregaDTO entrega) {
        Query query = generarQueryListaEntrega(entrega, false);
        query.setFirstResult(entrega.getStartRow());
        query.setMaxResults(entrega.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista Entrega.
     *
     * @param EntregaDTO el entrega
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaEntrega(EntregaDTO entrega, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idEntrega) from Entrega o where 1=1 ");
        } else {
            jpaql.append(" select o from Entrega o where 1=1 ");           
        }       
		if (!StringUtils.isNullOrEmpty(entrega.getSearch())) {
	          jpaql.append(" and upper(o.estadoEntrega) like :search ");
	          parametros.put("search", "%" + entrega.getSearch().toUpperCase() + "%");
	    } else {
			if (!StringUtils.isNullOrEmpty(entrega.getEstadoEntrega())) {
				jpaql.append(" and upper(o.estadoEntrega) like :estadoEntrega ");
				parametros.put("estadoEntrega", "%" + entrega.getEstadoEntrega().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmptyNumeric(entrega.getObservacion())) {
				jpaql.append(" and o.observacion = :observacion ");
				parametros.put("observacion", entrega.getObservacion());
			}
			if (!StringUtils.isNullOrEmpty(entrega.getFechaEntrega())) {
				jpaql.append(" and upper(o.fechaEntrega) like :fechaEntrega ");
				parametros.put("fechaEntrega", "%" + entrega.getFechaEntrega() + "%");
			}			
		}
        if (!esContador) {
            //jpaql.append(" ORDER BY 1 ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.EntregaDaoLocal#contarListar{entity.getClassName()}(pe.com.builderp.core.facturacion.model.jpa.venta.EntregaDTO)
     */
	@Override
    public int contarListarEntrega(EntregaDTO entrega) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaEntrega(entrega, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.EntregaDaoLocal#generarIdEntrega()
     */
	 @Override
    public String generarIdEntrega() {
		 return UUIDUtil.generarElementUUID();
    }
   
}