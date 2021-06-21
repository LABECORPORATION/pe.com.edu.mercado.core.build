package pe.com.builderp.core.facturacion.ejb.dao.venta.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.builderp.core.facturacion.ejb.dao.venta.local.DetallePedidoDaoLocal;
import pe.com.builderp.core.facturacion.model.dto.venta.DetallePedidoDTO;
import pe.com.builderp.core.facturacion.model.jpa.compra.DetalleOrdenCompra;
import pe.com.builderp.core.facturacion.model.jpa.venta.DetallePedido;
import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericFacturacionDAOImpl;
import pe.com.edu.siaa.core.ejb.util.jms.UUIDUtil;
import pe.com.edu.siaa.core.model.util.StringUtils;

/**
 * La Class DetallePedidoDaoImpl.
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
public class DetallePedidoDaoImpl extends  GenericFacturacionDAOImpl<String, DetallePedido> implements DetallePedidoDaoLocal  {

    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.DetallePedidoDaoLocal#listarDetallePedido(pe.com.builderp.core.facturacion.model.jpa.venta.DetallePedido)
     */  
    @Override	 
    public List<DetallePedido> listarDetallePedido(DetallePedidoDTO detallePedido) {
        Query query = generarQueryListaDetallePedido(detallePedido, false);
        query.setFirstResult(detallePedido.getStartRow());
        query.setMaxResults(detallePedido.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista DetallePedido.
     *
     * @param DetallePedidoDTO el detallePedido
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaDetallePedido(DetallePedidoDTO detallePedido, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idDetallePedido) from DetallePedido o where 1=1 ");
        } else {
            jpaql.append(" select o from DetallePedido o where 1=1 ");           
        }       
        
	      jpaql.append(" and o.pedido.idPedido =:idPedido ");
	      parametros.put("idPedido", detallePedido.getId() +"");    
        
		if (!StringUtils.isNullOrEmpty(detallePedido.getSearch())) {
	          jpaql.append(" and upper(o.idDetallePedido) like :search ");
	          parametros.put("search", "%" + detallePedido.getSearch().toUpperCase() + "%");
	    } else {
			if (!StringUtils.isNullOrEmpty(detallePedido.getDescripcionProducto())) {
				jpaql.append(" and upper(o.descripcionProducto) like :descripcionProducto ");
				parametros.put("descripcionProducto", "%" + detallePedido.getDescripcionProducto().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmptyNumeric(detallePedido.getPrecio())) {
				jpaql.append(" and o.precio = :precio ");
				parametros.put("precio", detallePedido.getPrecio());
			}
			if (!StringUtils.isNullOrEmptyNumeric(detallePedido.getCantidad())) {
				jpaql.append(" and o.cantidad = :cantidad ");
				parametros.put("cantidad", detallePedido.getCantidad());
			}
			if (!StringUtils.isNullOrEmptyNumeric(detallePedido.getDescuento())) {
				jpaql.append(" and o.descuento = :descuento ");
				parametros.put("descuento", detallePedido.getDescuento());
			}
			if (!StringUtils.isNullOrEmptyNumeric(detallePedido.getSubMontoTotal())) {
				jpaql.append(" and o.subMontoTotal = :subMontoTotal ");
				parametros.put("subMontoTotal", detallePedido.getSubMontoTotal());
			}
			if (!StringUtils.isNullOrEmptyNumeric(detallePedido.getMontoTotal())) {
				jpaql.append(" and o.montoTotal = :montoTotal ");
				parametros.put("montoTotal", detallePedido.getMontoTotal());
			}
		}
        if (!esContador) {
            //jpaql.append(" ORDER BY 1 ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }
    
    
    @Override	 
	 public List<DetallePedido> listarDetallePedido(String idPedido) {
	    	Map<String, Object> parametros = new HashMap<String, Object>();
			boolean ejecutarQuery = false;
			StringBuilder jpaql = new StringBuilder();
			jpaql.append("from DetallePedido drn where 1 = 1 ");
			
			if (StringUtils.isNotNullOrBlank(idPedido)) {
				ejecutarQuery = true;
				jpaql.append(" and drn.pedido.idPedido =:idPedido  ");
				parametros.put("idPedido", idPedido);
			}
			if (ejecutarQuery) {		
				Query query = createQuery(jpaql.toString(), parametros);
				return query.getResultList();
			} 
			return null;
	 }  

    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.DetallePedidoDaoLocal#contarListar{entity.getClassName()}(pe.com.builderp.core.facturacion.model.jpa.venta.DetallePedidoDTO)
     */
	@Override
    public int contarListarDetallePedido(DetallePedidoDTO detallePedido) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaDetallePedido(detallePedido, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.DetallePedidoDaoLocal#generarIdDetallePedido()
     */
	 @Override
    public String generarIdDetallePedido() {
		 return UUIDUtil.generarElementUUID();
    }
   
}