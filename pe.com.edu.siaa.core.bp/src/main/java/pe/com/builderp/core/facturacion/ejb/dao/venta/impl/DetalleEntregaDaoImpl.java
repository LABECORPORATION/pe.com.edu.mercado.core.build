package pe.com.builderp.core.facturacion.ejb.dao.venta.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.builderp.core.facturacion.ejb.dao.venta.local.DetalleEntregaDaoLocal;
import pe.com.builderp.core.facturacion.model.dto.venta.DetalleEntregaDTO;
import pe.com.builderp.core.facturacion.model.jpa.compra.DetalleOrdenCompra;
import pe.com.builderp.core.facturacion.model.jpa.venta.DetalleEntrega;
import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericFacturacionDAOImpl;
import pe.com.edu.siaa.core.ejb.util.jms.UUIDUtil;
import pe.com.edu.siaa.core.model.util.StringUtils;

/**
 * La Class DetalleEntregaDaoImpl.
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
public class DetalleEntregaDaoImpl extends  GenericFacturacionDAOImpl<String, DetalleEntrega> implements DetalleEntregaDaoLocal  {

    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.DetalleEntregaDaoLocal#listarDetalleEntrega(pe.com.builderp.core.facturacion.model.jpa.venta.DetalleEntrega)
     */  
    @Override	 
    public List<DetalleEntrega> listarDetalleEntrega(DetalleEntregaDTO detalleEntrega) {
        Query query = generarQueryListaDetalleEntrega(detalleEntrega, false);
        query.setFirstResult(detalleEntrega.getStartRow());
        query.setMaxResults(detalleEntrega.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista DetalleEntrega.
     *
     * @param DetalleEntregaDTO el detalleEntrega
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaDetalleEntrega(DetalleEntregaDTO detalleEntrega, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idDetalleEntrega) from DetalleEntrega o where 1=1 ");
        } else {
            jpaql.append(" select o from DetalleEntrega o where 1=1 ");           
        }       
        
	      jpaql.append(" and o.entrega.idEntrega =:idEntrega ");
	      parametros.put("idEntrega", detalleEntrega.getId() +"");    
        
		if (!StringUtils.isNullOrEmpty(detalleEntrega.getSearch())) {
	          jpaql.append(" and upper(o.idDetalleEntrega) like :search ");
	          parametros.put("search", "%" + detalleEntrega.getSearch().toUpperCase() + "%");
	    } 
		
		
        if (!esContador) {
            //jpaql.append(" ORDER BY 1 ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }
    
    
    @Override	 
	 public List<DetalleEntrega> listarDetalleEntrega(String idEntrega) {
	    	Map<String, Object> parametros = new HashMap<String, Object>();
			boolean ejecutarQuery = false;
			StringBuilder jpaql = new StringBuilder();
			jpaql.append("from DetalleEntrega drn where 1 = 1 ");
			
			if (StringUtils.isNotNullOrBlank(idEntrega)) {
				ejecutarQuery = true;
				jpaql.append(" and drn.entrega.idEntrega =:idEntrega  ");
				parametros.put("idEntrega", idEntrega);
			}
			if (ejecutarQuery) {		
				Query query = createQuery(jpaql.toString(), parametros);
				return query.getResultList();
			} 
			return null;
	 }  

    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.DetalleEntregaDaoLocal#contarListar{entity.getClassName()}(pe.com.builderp.core.facturacion.model.jpa.venta.DetalleEntregaDTO)
     */
	@Override
    public int contarListarDetalleEntrega(DetalleEntregaDTO detalleEntrega) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaDetalleEntrega(detalleEntrega, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.DetalleEntregaDaoLocal#generarIdDetalleEntrega()
     */
	 @Override
    public String generarIdDetalleEntrega() {
		 return UUIDUtil.generarElementUUID();
    }
   
}