package pe.com.builderp.core.facturacion.ejb.dao.venta.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.builderp.core.facturacion.ejb.dao.venta.local.DetalleProformaDaoLocal;
import pe.com.builderp.core.facturacion.model.dto.venta.DetalleProformaDTO;
import pe.com.builderp.core.facturacion.model.jpa.venta.DetalleProforma;
import pe.com.builderp.core.facturacion.model.jpa.venta.DetalleVenta;
import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericFacturacionDAOImpl;
import pe.com.edu.siaa.core.ejb.util.jms.UUIDUtil;
import pe.com.edu.siaa.core.model.util.StringUtils;

/**
 * La Class DetalleProformaDaoImpl.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu Dec 28 12:32:57 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
public class DetalleProformaDaoImpl extends  GenericFacturacionDAOImpl<String, DetalleProforma> implements DetalleProformaDaoLocal  {
	
	@Override
	public List<DetalleProforma> listarDetProforma(String idProforma) throws Exception {
		Query query = createQuery("from DetalleProforma detP left join fetch detP.producto left join fetch detP.proforma  where detP.proforma.idProforma = :idProforma",null);
		query.setParameter("idProforma", idProforma);
		return query.getResultList();
	}

    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.DetalleProformaDaoLocal#listarDetalleProforma(pe.com.builderp.core.facturacion.model.jpa.venta.DetalleProforma)
     */  
    @Override	 
    public List<DetalleProforma> listarDetalleProforma(DetalleProformaDTO detalleProforma) {
        Query query = generarQueryListaDetalleProforma(detalleProforma, false);
        query.setFirstResult(detalleProforma.getStartRow());
        query.setMaxResults(detalleProforma.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista DetalleProforma.
     *
     * @param DetalleProformaDTO el detalleProforma
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaDetalleProforma(DetalleProformaDTO detalleProforma, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idDetalleProforma) from DetalleProforma o where 1=1 ");
        } else {       
            jpaql.append(" select o from DetalleProforma o left join fetch o.producto where 1=1 ");  
        }
        jpaql.append(" and o.proforma.idProforma = :idProforma ");
     	parametros.put("idProforma", detalleProforma.getId() + "");
		if (!StringUtils.isNullOrEmpty(detalleProforma.getSearch())) {
	          jpaql.append(" and upper(o.idDetalleProforma) like :search ");
	          parametros.put("search", "%" + detalleProforma.getSearch().toUpperCase() + "%");
	    } else {
			if (!StringUtils.isNullOrEmpty(detalleProforma.getDescripcionProducto())) {
				jpaql.append(" and upper(o.descripcionProducto) like :descripcionProducto ");
				parametros.put("descripcionProducto", "%" + detalleProforma.getDescripcionProducto().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmptyNumeric(detalleProforma.getPrecio())) {
				jpaql.append(" and o.precio = :precio ");
				parametros.put("precio", detalleProforma.getPrecio());
			}
			if (!StringUtils.isNullOrEmptyNumeric(detalleProforma.getCantidad())) {
				jpaql.append(" and o.cantidad = :cantidad ");
				parametros.put("cantidad", detalleProforma.getCantidad());
			}
			if (!StringUtils.isNullOrEmptyNumeric(detalleProforma.getDescuento())) {
				jpaql.append(" and o.descuento = :descuento ");
				parametros.put("descuento", detalleProforma.getDescuento());
			}
			if (!StringUtils.isNullOrEmptyNumeric(detalleProforma.getSubMontoTotal())) {
				jpaql.append(" and o.subMontoTotal = :subMontoTotal ");
				parametros.put("subMontoTotal", detalleProforma.getSubMontoTotal());
			}
			if (!StringUtils.isNullOrEmptyNumeric(detalleProforma.getMontoTotal())) {
				jpaql.append(" and o.montoTotal = :montoTotal ");
				parametros.put("montoTotal", detalleProforma.getMontoTotal());
			}
		}
        if (!esContador) {
            //jpaql.append(" ORDER BY 1 ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.DetalleProformaDaoLocal#contarListar{entity.getClassName()}(pe.com.builderp.core.facturacion.model.jpa.venta.DetalleProformaDTO)
     */
	@Override
    public int contarListarDetalleProforma(DetalleProformaDTO detalleProforma) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaDetalleProforma(detalleProforma, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.DetalleProformaDaoLocal#generarIdDetalleProforma()
     */
	 @Override
    public String generarIdDetalleProforma() {
		 return UUIDUtil.generarElementUUID();
    }
   
}