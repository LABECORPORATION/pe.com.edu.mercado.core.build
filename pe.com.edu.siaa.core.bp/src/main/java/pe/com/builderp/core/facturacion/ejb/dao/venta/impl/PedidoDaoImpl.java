package pe.com.builderp.core.facturacion.ejb.dao.venta.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.builderp.core.facturacion.ejb.dao.venta.local.PedidoDaoLocal;
import pe.com.builderp.core.facturacion.model.dto.venta.PedidoDTO;
import pe.com.builderp.core.facturacion.model.jpa.venta.Pedido;
import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericFacturacionDAOImpl;
import pe.com.edu.siaa.core.ejb.util.jms.UUIDUtil;
import pe.com.edu.siaa.core.model.util.StringUtils;

/**
 * La Class PedidoDaoImpl.
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
public class PedidoDaoImpl extends  GenericFacturacionDAOImpl<String, Pedido> implements PedidoDaoLocal  {

    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.PedidoDaoLocal#listarPedido(pe.com.builderp.core.facturacion.model.jpa.venta.Pedido)
     */  
    @Override	 
    public List<Pedido> listarPedido(PedidoDTO pedido) {
        Query query = generarQueryListaPedido(pedido, false);
        query.setFirstResult(pedido.getStartRow());
        query.setMaxResults(pedido.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista Pedido.
     *
     * @param PedidoDTO el pedido
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaPedido(PedidoDTO pedido, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idPedido) from Pedido o where 1=1 ");
        } else {
            jpaql.append(" select o from Pedido o where 1=1 ");           
        }
        if(!StringUtils.isNullOrEmpty(pedido.getId())) {
        	jpaql.append(" and o.estado = :idEstado ");
        	parametros.put("idEstado", pedido.getId() + "");
        }
		if (!StringUtils.isNullOrEmpty(pedido.getSearch())) {
	          jpaql.append(" and upper(o.idPedido) like :search ");
	          parametros.put("search", "%" + pedido.getSearch().toUpperCase() + "%");
	    } else {
			if (!StringUtils.isNullOrEmpty(pedido.getCodigoPedido())) {
				jpaql.append(" and upper(o.codigoPedido) like :codigoPedido ");
				parametros.put("codigoPedido", "%" + pedido.getCodigoPedido().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmptyNumeric(pedido.getTipoCambio())) {
				jpaql.append(" and o.tipoCambio = :tipoCambio ");
				parametros.put("tipoCambio", pedido.getTipoCambio());
			}
			if (!StringUtils.isNullOrEmpty(pedido.getNroDoc())) {
				jpaql.append(" and upper(o.nroDoc) like :nroDoc ");
				parametros.put("nroDoc", "%" + pedido.getNroDoc().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmptyNumeric(pedido.getProcentajeIgv())) {
				jpaql.append(" and o.procentajeIgv = :procentajeIgv ");
				parametros.put("procentajeIgv", pedido.getProcentajeIgv());
			}
			if (!StringUtils.isNullOrEmptyNumeric(pedido.getIgv())) {
				jpaql.append(" and o.igv = :igv ");
				parametros.put("igv", pedido.getIgv());
			}
			if (!StringUtils.isNullOrEmptyNumeric(pedido.getDescuentoTotal())) {
				jpaql.append(" and o.descuentoTotal = :descuentoTotal ");
				parametros.put("descuentoTotal", pedido.getDescuentoTotal());
			}
			if (!StringUtils.isNullOrEmptyNumeric(pedido.getSubMontoTotal())) {
				jpaql.append(" and o.subMontoTotal = :subMontoTotal ");
				parametros.put("subMontoTotal", pedido.getSubMontoTotal());
			}
			if (!StringUtils.isNullOrEmptyNumeric(pedido.getMontoTotal())) {
				jpaql.append(" and o.montoTotal = :montoTotal ");
				parametros.put("montoTotal", pedido.getMontoTotal());
			}
			if (!StringUtils.isNullOrEmpty(pedido.getFechaPedido())) {
				jpaql.append(" and o.fechaPedido = :fechaPedido ");
				parametros.put("fechaPedido", pedido.getFechaPedido());
			}
			if (!StringUtils.isNullOrEmpty(pedido.getFechaCreacion())) {
				jpaql.append(" and o.fechaCreacion = :fechaCreacion ");
				parametros.put("fechaCreacion", pedido.getFechaCreacion());
			}
			if (!StringUtils.isNullOrEmpty(pedido.getUsuarioCreacion())) {
				jpaql.append(" and upper(o.usuarioCreacion) like :usuarioCreacion ");
				parametros.put("usuarioCreacion", "%" + pedido.getUsuarioCreacion().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(pedido.getFechaModificacion())) {
				jpaql.append(" and o.fechaModificacion = :fechaModificacion ");
				parametros.put("fechaModificacion", pedido.getFechaModificacion());
			}
			if (!StringUtils.isNullOrEmpty(pedido.getUsuarioModificacion())) {
				jpaql.append(" and upper(o.usuarioModificacion) like :usuarioModificacion ");
				parametros.put("usuarioModificacion", "%" + pedido.getUsuarioModificacion().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(pedido.getEstado())) {
				jpaql.append(" and upper(o.estado) like :estado ");
				parametros.put("estado", "%" + pedido.getEstado().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(pedido.getIpAcceso())) {
				jpaql.append(" and upper(o.ipAcceso) like :ipAcceso ");
				parametros.put("ipAcceso", "%" + pedido.getIpAcceso().toUpperCase() + "%");
			}
		}
        if (!esContador) {
            //jpaql.append(" ORDER BY 1 ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.PedidoDaoLocal#contarListar{entity.getClassName()}(pe.com.builderp.core.facturacion.model.jpa.venta.PedidoDTO)
     */
	@Override
    public int contarListarPedido(PedidoDTO pedido) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaPedido(pedido, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.PedidoDaoLocal#generarIdPedido()
     */
	 @Override
    public String generarIdPedido() {
		 return UUIDUtil.generarElementUUID();
    }
   
}