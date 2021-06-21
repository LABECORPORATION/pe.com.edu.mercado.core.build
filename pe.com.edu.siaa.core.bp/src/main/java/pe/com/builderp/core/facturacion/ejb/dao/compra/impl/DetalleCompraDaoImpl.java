package pe.com.builderp.core.facturacion.ejb.dao.compra.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.builderp.core.facturacion.ejb.dao.compra.local.DetalleCompraDaoLocal;
import pe.com.builderp.core.facturacion.model.dto.compra.DetalleCompraDTO;
import pe.com.builderp.core.facturacion.model.jpa.compra.DetalleCompra;
import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericFacturacionDAOImpl;
import pe.com.edu.siaa.core.ejb.util.jms.UUIDUtil;
import pe.com.edu.siaa.core.model.util.StringUtils;

/**
 * La Class DetalleCompraDaoImpl.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu Dec 28 12:32:54 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
public class DetalleCompraDaoImpl extends  GenericFacturacionDAOImpl<String, DetalleCompra> implements DetalleCompraDaoLocal  {

    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.compra.local.DetalleCompraDaoLocal#listarDetalleCompra(pe.com.builderp.core.facturacion.model.jpa.compra.DetalleCompra)
     */  
    @Override	 
    public List<DetalleCompra> listarDetalleCompra(DetalleCompraDTO detalleCompra) {
        Query query = generarQueryListaDetalleCompra(detalleCompra, false);
        query.setFirstResult(detalleCompra.getStartRow());
        query.setMaxResults(detalleCompra.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista DetalleCompra.
     *
     * @param DetalleCompraDTO el detalleCompra
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaDetalleCompra(DetalleCompraDTO detalleCompra, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idDetalleCompra) from DetalleCompra o where 1=1 ");
        } else {
            jpaql.append(" select o from DetalleCompra o  left join fetch o.producto where 1=1 ");           
        }
        jpaql.append(" and o.compra.idCompra = :idCompra ");
      	parametros.put("idCompra", detalleCompra.getId() + "");
      	
		if (!StringUtils.isNullOrEmpty(detalleCompra.getSearch())) {
	          jpaql.append(" and upper(o.idDetalleCompra) like :search ");
	          parametros.put("search", "%" + detalleCompra.getSearch().toUpperCase() + "%");
	    } else {
			if (!StringUtils.isNullOrEmpty(detalleCompra.getDescripcionProducto())) {
				jpaql.append(" and upper(o.descripcionProducto) like :descripcionProducto ");
				parametros.put("descripcionProducto", "%" + detalleCompra.getDescripcionProducto().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmptyNumeric(detalleCompra.getPrecio())) {
				jpaql.append(" and o.precio = :precio ");
				parametros.put("precio", detalleCompra.getPrecio());
			}
			if (!StringUtils.isNullOrEmptyNumeric(detalleCompra.getCantidad())) {
				jpaql.append(" and o.cantidad = :cantidad ");
				parametros.put("cantidad", detalleCompra.getCantidad());
			}
			if (!StringUtils.isNullOrEmptyNumeric(detalleCompra.getDescuento())) {
				jpaql.append(" and o.descuento = :descuento ");
				parametros.put("descuento", detalleCompra.getDescuento());
			}
			if (!StringUtils.isNullOrEmptyNumeric(detalleCompra.getSubMontoTotal())) {
				jpaql.append(" and o.subMontoTotal = :subMontoTotal ");
				parametros.put("subMontoTotal", detalleCompra.getSubMontoTotal());
			}
			if (!StringUtils.isNullOrEmptyNumeric(detalleCompra.getMontoTotal())) {
				jpaql.append(" and o.montoTotal = :montoTotal ");
				parametros.put("montoTotal", detalleCompra.getMontoTotal());
			}
		}
        if (!esContador) {
            //jpaql.append(" ORDER BY 1 ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.compra.local.DetalleCompraDaoLocal#contarListar{entity.getClassName()}(pe.com.builderp.core.facturacion.model.jpa.compra.DetalleCompraDTO)
     */
	@Override
    public int contarListarDetalleCompra(DetalleCompraDTO detalleCompra) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaDetalleCompra(detalleCompra, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.compra.local.DetalleCompraDaoLocal#generarIdDetalleCompra()
     */
	 @Override
    public String generarIdDetalleCompra() {
		 return UUIDUtil.generarElementUUID();
    }
	 
	 @Override	 
	 public List<DetalleCompra> listarDetalleCompraTemp(List<String> idCompra) {
	    	Map<String, Object> parametros = new HashMap<String, Object>();
			boolean ejecutarQuery = false;
			StringBuilder jpaql = new StringBuilder();
			jpaql.append("from DetalleCompra drn where 1 = 1 ");
			
			if (StringUtils.isNotNullOrBlank(idCompra)) {
				ejecutarQuery = true;
				jpaql.append(" and drn.compra.idCompra in(:idCompra)  ");
				parametros.put("idCompra", idCompra);
			}
			if (ejecutarQuery) {		
				Query query = createQuery(jpaql.toString(), parametros);
				return query.getResultList();
			} 
			return null;
	 } 
   
}