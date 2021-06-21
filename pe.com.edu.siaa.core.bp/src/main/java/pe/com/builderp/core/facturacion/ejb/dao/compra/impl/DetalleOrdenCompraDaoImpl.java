package pe.com.builderp.core.facturacion.ejb.dao.compra.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.builderp.core.facturacion.ejb.dao.compra.local.DetalleOrdenCompraDaoLocal;
import pe.com.builderp.core.facturacion.model.dto.compra.DetalleOrdenCompraDTO;
import pe.com.builderp.core.facturacion.model.jpa.compra.DetalleOrdenCompra;
import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericFacturacionDAOImpl;
import pe.com.edu.siaa.core.ejb.util.jms.UUIDUtil;
import pe.com.edu.siaa.core.model.util.StringUtils;

/**
 * La Class DetalleOrdenCompraDaoImpl.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu Dec 28 12:32:55 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
public class DetalleOrdenCompraDaoImpl extends  GenericFacturacionDAOImpl<String, DetalleOrdenCompra> implements DetalleOrdenCompraDaoLocal  {

    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.compra.local.DetalleOrdenCompraDaoLocal#listarDetalleOrdenCompra(pe.com.builderp.core.facturacion.model.jpa.compra.DetalleOrdenCompra)
     */  
    @Override	 
    public List<DetalleOrdenCompra> listarDetalleOrdenCompra(DetalleOrdenCompraDTO detalleOrdenCompra) {
        Query query = generarQueryListaDetalleOrdenCompra(detalleOrdenCompra, false);
        query.setFirstResult(detalleOrdenCompra.getStartRow());
        query.setMaxResults(detalleOrdenCompra.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista DetalleOrdenCompra.
     *
     * @param DetalleOrdenCompraDTO el detalleOrdenCompra
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaDetalleOrdenCompra(DetalleOrdenCompraDTO detalleOrdenCompra, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idDetalleOrdenCompra) from DetalleOrdenCompra o where 1=1 ");
        } else {
            jpaql.append(" select o from DetalleOrdenCompra o where 1=1 ");           
        }
        
        if (!StringUtils.isNullOrEmpty(detalleOrdenCompra.getId())) {
	          jpaql.append(" and o.ordenCompra.idOrdenCompra =:idOrdenCompra ");
	          parametros.put("idOrdenCompra", detalleOrdenCompra.getId() +"");
	    }
        
		if (!StringUtils.isNullOrEmpty(detalleOrdenCompra.getSearch())) {
	          jpaql.append(" and upper(o.idDetalleOrdenCompra) like :search ");
	          parametros.put("search", "%" + detalleOrdenCompra.getSearch().toUpperCase() + "%");
	    } else {
			if (!StringUtils.isNullOrEmpty(detalleOrdenCompra.getDescripcionProducto())) {
				jpaql.append(" and upper(o.descripcionProducto) like :descripcionProducto ");
				parametros.put("descripcionProducto", "%" + detalleOrdenCompra.getDescripcionProducto().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmptyNumeric(detalleOrdenCompra.getPrecio())) {
				jpaql.append(" and o.precio = :precio ");
				parametros.put("precio", detalleOrdenCompra.getPrecio());
			}
			if (!StringUtils.isNullOrEmptyNumeric(detalleOrdenCompra.getCantidad())) {
				jpaql.append(" and o.cantidad = :cantidad ");
				parametros.put("cantidad", detalleOrdenCompra.getCantidad());
			}
			if (!StringUtils.isNullOrEmptyNumeric(detalleOrdenCompra.getDescuento())) {
				jpaql.append(" and o.descuento = :descuento ");
				parametros.put("descuento", detalleOrdenCompra.getDescuento());
			}
			if (!StringUtils.isNullOrEmptyNumeric(detalleOrdenCompra.getSubMontoTotal())) {
				jpaql.append(" and o.subMontoTotal = :subMontoTotal ");
				parametros.put("subMontoTotal", detalleOrdenCompra.getSubMontoTotal());
			}
			if (!StringUtils.isNullOrEmptyNumeric(detalleOrdenCompra.getMontoTotal())) {
				jpaql.append(" and o.montoTotal = :montoTotal ");
				parametros.put("montoTotal", detalleOrdenCompra.getMontoTotal());
			}
		}
        if (!esContador) {
            //jpaql.append(" ORDER BY 1 ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    
    
    @Override	 
	 public List<DetalleOrdenCompra> listarDetalleOrdenCompra(String idOrdenCompra) {
	    	Map<String, Object> parametros = new HashMap<String, Object>();
			boolean ejecutarQuery = false;
			StringBuilder jpaql = new StringBuilder();
			jpaql.append("from DetalleOrdenCompra drn where 1 = 1 ");
			
			if (StringUtils.isNotNullOrBlank(idOrdenCompra)) {
				ejecutarQuery = true;
				jpaql.append(" and drn.ordenCompra.idOrdenCompra =:idOrdenCompra  ");
				parametros.put("idOrdenCompra", idOrdenCompra);
			}
			if (ejecutarQuery) {		
				Query query = createQuery(jpaql.toString(), parametros);
				return query.getResultList();
			} 
			return null;
	 }  
    
    
    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.compra.local.DetalleOrdenCompraDaoLocal#contarListar{entity.getClassName()}(pe.com.builderp.core.facturacion.model.jpa.compra.DetalleOrdenCompraDTO)
     */
	@Override
    public int contarListarDetalleOrdenCompra(DetalleOrdenCompraDTO detalleOrdenCompra) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaDetalleOrdenCompra(detalleOrdenCompra, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.compra.local.DetalleOrdenCompraDaoLocal#generarIdDetalleOrdenCompra()
     */
	 @Override
    public String generarIdDetalleOrdenCompra() {
		 return UUIDUtil.generarElementUUID();
    }
   
}