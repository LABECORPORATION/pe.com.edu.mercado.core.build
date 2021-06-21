package pe.com.builderp.core.facturacion.ejb.dao.compra.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.builderp.core.facturacion.ejb.dao.compra.local.OrdenCompraDaoLocal;
import pe.com.builderp.core.facturacion.model.dto.compra.OrdenCompraDTO;
import pe.com.builderp.core.facturacion.model.jpa.compra.OrdenCompra;
import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericFacturacionDAOImpl;
import pe.com.edu.siaa.core.ejb.util.jms.UUIDUtil;
import pe.com.edu.siaa.core.model.util.StringUtils;

/**
 * La Class OrdenCompraDaoImpl.
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
public class OrdenCompraDaoImpl extends  GenericFacturacionDAOImpl<String, OrdenCompra> implements OrdenCompraDaoLocal  {

    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.compra.local.OrdenCompraDaoLocal#listarOrdenCompra(pe.com.builderp.core.facturacion.model.jpa.compra.OrdenCompra)
     */  
    @Override	 
    public List<OrdenCompra> listarOrdenCompra(OrdenCompraDTO ordenCompra) {
        Query query = generarQueryListaOrdenCompra(ordenCompra, false);
        query.setFirstResult(ordenCompra.getStartRow());
        query.setMaxResults(ordenCompra.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista OrdenCompra.
     *
     * @param OrdenCompraDTO el ordenCompra
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaOrdenCompra(OrdenCompraDTO ordenCompra, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idOrdenCompra) from OrdenCompra o where 1=1 ");
        } else {
            jpaql.append(" select o from OrdenCompra o where 1=1 ");           
        }
		if (!StringUtils.isNullOrEmpty(ordenCompra.getSearch())) {
	          jpaql.append(" and upper(o.idOrdenCompra) like :search ");
	          parametros.put("search", "%" + ordenCompra.getSearch().toUpperCase() + "%");
	    } else {
			if (!StringUtils.isNullOrEmptyNumeric(ordenCompra.getTipoCambio())) {
				jpaql.append(" and o.tipoCambio = :tipoCambio ");
				parametros.put("tipoCambio", ordenCompra.getTipoCambio());
			}
			if (!StringUtils.isNullOrEmpty(ordenCompra.getNroDoc())) {
				jpaql.append(" and upper(o.nroDoc) like :nroDoc ");
				parametros.put("nroDoc", "%" + ordenCompra.getNroDoc().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmptyNumeric(ordenCompra.getProcentajeIgv())) {
				jpaql.append(" and o.procentajeIgv = :procentajeIgv ");
				parametros.put("procentajeIgv", ordenCompra.getProcentajeIgv());
			}
			if (!StringUtils.isNullOrEmptyNumeric(ordenCompra.getIgv())) {
				jpaql.append(" and o.igv = :igv ");
				parametros.put("igv", ordenCompra.getIgv());
			}
			if (!StringUtils.isNullOrEmptyNumeric(ordenCompra.getDescuentoTotal())) {
				jpaql.append(" and o.descuentoTotal = :descuentoTotal ");
				parametros.put("descuentoTotal", ordenCompra.getDescuentoTotal());
			}
			if (!StringUtils.isNullOrEmptyNumeric(ordenCompra.getSubMontoTotal())) {
				jpaql.append(" and o.subMontoTotal = :subMontoTotal ");
				parametros.put("subMontoTotal", ordenCompra.getSubMontoTotal());
			}
			if (!StringUtils.isNullOrEmptyNumeric(ordenCompra.getMontoTotal())) {
				jpaql.append(" and o.montoTotal = :montoTotal ");
				parametros.put("montoTotal", ordenCompra.getMontoTotal());
			}
			if (!StringUtils.isNullOrEmpty(ordenCompra.getFechaOrdenCompra())) {
				jpaql.append(" and o.fechaOrdenCompra = :fechaOrdenCompra ");
				parametros.put("fechaOrdenCompra", ordenCompra.getFechaOrdenCompra());
			}
			if (!StringUtils.isNullOrEmpty(ordenCompra.getFechaCreacion())) {
				jpaql.append(" and o.fechaCreacion = :fechaCreacion ");
				parametros.put("fechaCreacion", ordenCompra.getFechaCreacion());
			}
			if (!StringUtils.isNullOrEmpty(ordenCompra.getUsuarioCreacion())) {
				jpaql.append(" and upper(o.usuarioCreacion) like :usuarioCreacion ");
				parametros.put("usuarioCreacion", "%" + ordenCompra.getUsuarioCreacion().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(ordenCompra.getFechaModificacion())) {
				jpaql.append(" and o.fechaModificacion = :fechaModificacion ");
				parametros.put("fechaModificacion", ordenCompra.getFechaModificacion());
			}
			if (!StringUtils.isNullOrEmpty(ordenCompra.getUsuarioModificacion())) {
				jpaql.append(" and upper(o.usuarioModificacion) like :usuarioModificacion ");
				parametros.put("usuarioModificacion", "%" + ordenCompra.getUsuarioModificacion().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(ordenCompra.getEstado())) {
				jpaql.append(" and upper(o.estado) like :estado ");
				parametros.put("estado", "%" + ordenCompra.getEstado().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(ordenCompra.getIpAcceso())) {
				jpaql.append(" and upper(o.ipAcceso) like :ipAcceso ");
				parametros.put("ipAcceso", "%" + ordenCompra.getIpAcceso().toUpperCase() + "%");
			}
		}
        if (!esContador) {
            //jpaql.append(" ORDER BY 1 ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.compra.local.OrdenCompraDaoLocal#contarListar{entity.getClassName()}(pe.com.builderp.core.facturacion.model.jpa.compra.OrdenCompraDTO)
     */
	@Override
    public int contarListarOrdenCompra(OrdenCompraDTO ordenCompra) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaOrdenCompra(ordenCompra, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.compra.local.OrdenCompraDaoLocal#generarIdOrdenCompra()
     */
	 @Override
    public String generarIdOrdenCompra() {
		 return UUIDUtil.generarElementUUID();
    }
   
}