package pe.com.builderp.core.facturacion.ejb.dao.venta.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.builderp.core.facturacion.ejb.dao.venta.local.DetalleVentaDaoLocal;
import pe.com.builderp.core.facturacion.model.dto.venta.DetalleVentaDTO;
import pe.com.builderp.core.facturacion.model.jpa.compra.DetalleCompra;
import pe.com.builderp.core.facturacion.model.jpa.venta.DetalleVenta;
import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericFacturacionDAOImpl;
import pe.com.edu.siaa.core.ejb.service.util.FechaUtil;
import pe.com.edu.siaa.core.ejb.util.jms.UUIDUtil;
import pe.com.edu.siaa.core.ejb.util.log.Logger;
import pe.com.edu.siaa.core.model.util.StringUtils;
import pe.com.edu.siaa.core.model.vo.DetalleVentaFiltroVO;
import pe.com.edu.siaa.core.model.vo.VentaFiltroVO;

/**
 * La Class DetalleVentaDaoImpl.
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
public class DetalleVentaDaoImpl extends  GenericFacturacionDAOImpl<String, DetalleVenta> implements DetalleVentaDaoLocal  {
	private Logger log = Logger.getLogger(DetalleVentaDaoImpl.class);
	@Override
	public List<DetalleVenta> listarDetVenta(String idVenta) throws Exception {
		Query query = createQuery("from DetalleVenta detV left join fetch detV.detalleProducto left join fetch detV.venta  where detV.venta.idVenta = :idVenta",null);
		query.setParameter("idVenta", idVenta);
		return query.getResultList();
	}
    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.DetalleVentaDaoLocal#listarDetalleVenta(pe.com.builderp.core.facturacion.model.jpa.venta.DetalleVenta)
     */  
    @Override	 
    public List<DetalleVenta> listarDetalleVenta(DetalleVentaDTO detalleVenta) {
        Query query = generarQueryListaDetalleVenta(detalleVenta, false);
        query.setFirstResult(detalleVenta.getStartRow());
        query.setMaxResults(detalleVenta.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista DetalleVenta.
     *
     * @param DetalleVentaDTO el detalleVenta
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaDetalleVenta(DetalleVentaDTO detalleVenta, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idDetalleVenta) from DetalleVenta o where 1=1 ");
        } else {
            jpaql.append(" select o from DetalleVenta o left join fetch o.detalleProducto where 1=1 ");           
        }
        jpaql.append(" and o.venta.idVenta = :idVenta ");
      	parametros.put("idVenta", detalleVenta.getId() + "");
		if (!StringUtils.isNullOrEmpty(detalleVenta.getSearch())) {
	          jpaql.append(" and upper(o.idDetalleVenta) like :search ");
	          parametros.put("search", "%" + detalleVenta.getSearch().toUpperCase() + "%");
	    } else {
			if (!StringUtils.isNullOrEmpty(detalleVenta.getDescripcionProducto())) {
				jpaql.append(" and upper(o.descripcionProducto) like :descripcionProducto ");
				parametros.put("descripcionProducto", "%" + detalleVenta.getDescripcionProducto().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmptyNumeric(detalleVenta.getPrecio())) {
				jpaql.append(" and o.precio = :precio ");
				parametros.put("precio", detalleVenta.getPrecio());
			}
			if (!StringUtils.isNullOrEmptyNumeric(detalleVenta.getCantidad())) {
				jpaql.append(" and o.cantidad = :cantidad ");
				parametros.put("cantidad", detalleVenta.getCantidad());
			}
			if (!StringUtils.isNullOrEmptyNumeric(detalleVenta.getDescuento())) {
				jpaql.append(" and o.descuento = :descuento ");
				parametros.put("descuento", detalleVenta.getDescuento());
			}
			if (!StringUtils.isNullOrEmptyNumeric(detalleVenta.getSubMontoTotal())) {
				jpaql.append(" and o.subMontoTotal = :subMontoTotal ");
				parametros.put("subMontoTotal", detalleVenta.getSubMontoTotal());
			}
			if (!StringUtils.isNullOrEmptyNumeric(detalleVenta.getMontoTotal())) {
				jpaql.append(" and o.montoTotal = :montoTotal ");
				parametros.put("montoTotal", detalleVenta.getMontoTotal());
			}
		}
        if (!esContador) {
            //jpaql.append(" ORDER BY 1 ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.DetalleVentaDaoLocal#contarListar{entity.getClassName()}(pe.com.builderp.core.facturacion.model.jpa.venta.DetalleVentaDTO)
     */
	@Override
    public int contarListarDetalleVenta(DetalleVentaDTO detalleVenta) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaDetalleVenta(detalleVenta, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.DetalleVentaDaoLocal#generarIdDetalleVenta()
     */
	 @Override
    public String generarIdDetalleVenta() {
        return UUIDUtil.generarElementUUID();
    }
	 
	 @Override	 
	 public List<DetalleVenta> listarDetalleVentaPedidoTemp(List<String> idPedido) {
	    	Map<String, Object> parametros = new HashMap<String, Object>();
			boolean ejecutarQuery = false;
			StringBuilder jpaql = new StringBuilder();
			jpaql.append("from DetalleVenta drn where 1 = 1 ");
			
			if (StringUtils.isNotNullOrBlank(idPedido)) {
				ejecutarQuery = true;
				jpaql.append(" and drn.pedido.idPedido in(:idPedido)  ");
				parametros.put("idPedido", idPedido);
			}
			if (ejecutarQuery) {		
				Query query = createQuery(jpaql.toString(), parametros);
				return query.getResultList();
			} 
			return null;
	 }
	 
	 
		@Override
		   public List<DetalleVentaFiltroVO> obtenerDetallVentaMap(List<Long>listadoIdProductos, Date fechaInicio, Date fechaFin) throws Exception{
			Map<String, Object> parametros = new HashMap<String, Object>();
		    List<DetalleVentaFiltroVO>  resultado = new  ArrayList<DetalleVentaFiltroVO>();
			StringBuilder jpaql = generarQueryIngresoDetallado();
			parametros.put("listadoIdProductos",  listadoIdProductos );
			parametros.put("fechaInicio",  fechaInicio );
			parametros.put("fechaFin",FechaUtil.sumarDias(fechaFin, 1) );
			Query query = createNativeQuery(jpaql.toString(), parametros); 
			List<Object[]>   listaObject =  query.getResultList();
			resultado = parsearDatosIgresoDetalladoDTO(listaObject);		 
			return resultado;
		}
		//agregado	
		private StringBuilder generarQueryIngresoDetallado()  {		
			StringBuilder jpaql = new StringBuilder();		
			jpaql.append(" SELECT  to_char(Venta.fechaventa,'dd/MM/yyyy') as FechaVenta, (case when docSunat.idITem = 686 then '01' when docSunat.idITem = 688 then '03' end) as TipoComprobante,Venta.serie as Serie,Venta.nrodoc as NroBoleta, ");
			jpaql.append(" (case when Cliente.idtipodocumentoidentidad = 20 then '1' when Cliente.idtipodocumentoidentidad = 21 then '4' when Cliente.idtipodocumentoidentidad = 22 then '7' when Cliente.idtipodocumentoidentidad = 911 then '6' end )  as TipoDoc, "); 	
			jpaql.append(" Cliente.nrodoc as documenCliente,Cliente.nombre as cliente  ,detVenta.montototal  as Efectivo,"); 
			jpaql.append(" detVenta.descripcionproducto,unidadM.nombre,detVenta.cantidad,detVenta.precio "); 
			jpaql.append(" from factu.DetalleVenta detVenta LEFT JOIN  factu.Venta Venta on detVenta.idVenta = Venta.idVenta LEFT JOIN ");
			jpaql.append(" factu.Cliente Cliente on Venta.idCliente = Cliente.idCliente LEFT JOIN ");
			jpaql.append(" factu.detalleproducto on detVenta.iddetalleproducto = detalleproducto.iddetalleproducto LEFT JOIN "); 
			jpaql.append(" commo.item unidadM on detalleproducto.idunidadmedida = unidadM.iditem LEFT JOIN "); 	
			jpaql.append(" commo.item docSunat on  Venta.idtipodocsunat = docSunat.iditem  ");
			 
			jpaql.append("where  Venta.fechaventa  BETWEEN :fechaInicio  AND :fechaFin and (docSunat.idITem = 686 or docSunat.idITem = 688) and detalleproducto.idproducto in (:listadoIdProductos) ");	
			
			jpaql.append(" order by Venta.fechaventa asc ");		
			return jpaql;
		}
		
		
		   private List<DetalleVentaFiltroVO> parsearDatosIgresoDetalladoDTO(List<Object[]>   listaObject) {
			 	  List<DetalleVentaFiltroVO> resultado = new  ArrayList<DetalleVentaFiltroVO>();
			 	  try {
			 		  if (listaObject != null) {
			 				for (Object[] res : listaObject) {
			 					DetalleVentaFiltroVO detallePagoDTO  = new DetalleVentaFiltroVO();	 	
			 					if(res[0] != null) {
				 					detallePagoDTO.setFechaVenta((String)res[0]);
			 					}
			 					if(res[1] != null) {
				 					detallePagoDTO.setHoraEmision((String)res[1]);
			 					}
			 					if(res[2] != null) {
				 					detallePagoDTO.setSerie((String)res[2]);
			 					}
			 					if(res[3] != null) {
				 					detallePagoDTO.setNroComprobante((String)res[3]);	
			 					}
			 					if(res[4] != null) {
				 					detallePagoDTO.setTipoDoc((String)res[4]);
			 					}
			 					if(res[5] != null) {
				 					detallePagoDTO.setNroDoc((String)res[5]);
			 					}
			 					if(res[6] != null) {
				 					detallePagoDTO.setClienteNombre((String)res[6]);
			 					}
			 					if(res[7] != null) {
			 						/*double doble = Double.parseDouble(res[7].toString());
			 						BigDecimal formatNumber = new BigDecimal(doble);
			 						DecimalFormat formatea = new DecimalFormat("###,###.00");
			 						formatNumber = formatNumber.setScale(2, RoundingMode.DOWN);
			 	 					detallePagoDTO.setMonto(formatea.format(formatNumber));	*/	 
			 	 					detallePagoDTO.setMontoTotal((BigDecimal)res[7]);
			 					}
			 					if(res[8] != null) {
				 					detallePagoDTO.setDescipcionProducto((String)res[8]);
			 					}
			 					if(res[9] != null) {
				 					detallePagoDTO.setUnidadMedida((String)res[9]);
			 					}
			 					if(res[10] != null) {
				 					detallePagoDTO.setCantidad((BigDecimal)res[10]);
			 					}
			 					if(res[11] != null) {
				 					detallePagoDTO.setPrecioUnitario((BigDecimal)res[11]);
			 					}
			 					resultado.add(detallePagoDTO);
			 				}
			 			}
			 	  } catch (Exception e) {
			 			log.error(e);
			 		}
			 	  return resultado;
			}	
		
	 @Override
	   public List<DetalleVentaFiltroVO> obtenerProductoVendidos(VentaFiltroVO filter)  throws Exception{
		Map<String, Object> parametros = new HashMap<String, Object>();
	    List<DetalleVentaFiltroVO>  resultado = new  ArrayList<DetalleVentaFiltroVO>();
		StringBuilder jpaql = new StringBuilder();	
		jpaql.append(" select producto.nombre,umedida.nombre as unidadM, sum(detalleventa.cantidad) as cantidad,detalleventa.precio,sum(detalleventa.montototal) as montoTotal ");
		jpaql.append(" from factu.detalleventa"); 	
		jpaql.append(" inner join factu.venta On detalleventa.idventa=venta.idventa ");  
		jpaql.append(" inner join factu.detalleproducto On detalleventa.iddetalleproducto=detalleproducto.iddetalleproducto ");
		jpaql.append(" inner join commo.item umedida ON detalleproducto.idunidadmedida = umedida.iditem ");
		jpaql.append(" inner join factu.producto On detalleproducto.idproducto=producto.idproducto "); 
		jpaql.append(" where  venta.fechaventa BETWEEN :fechaInicio and :fechaFin "); 	
		jpaql.append(" group by producto.nombre,producto.idproducto,umedida.nombre ,detalleventa.precio ");

		jpaql.append(" order by producto.nombre asc");
		
		parametros.put("fechaInicio",  filter.getFechaInicio() );
		parametros.put("fechaFin",FechaUtil.sumarDias(filter.getFechaFin(), 1) );
		Query query = createNativeQuery(jpaql.toString(), parametros); 
		List<Object[]>   listaObject =  query.getResultList();
		for (Object[] res : listaObject) {
				DetalleVentaFiltroVO detalleFiltro  = new DetalleVentaFiltroVO();	 	
				if(res[0] != null) {
					detalleFiltro.setDescipcionProducto((String)res[0]);
				}
				if(res[1] != null) {
					detalleFiltro.setUnidadMedida((String)res[1]);
				}
				if(res[2] != null) {
					detalleFiltro.setCantidad((BigDecimal)res[2]);
				}
				if(res[3] != null) {
					detalleFiltro.setPrecioUnitario((BigDecimal)res[3]);
				}
				if(res[4] != null) {
					detalleFiltro.setMontoTotal((BigDecimal)res[4]);
				}
				resultado.add(detalleFiltro);
		}
		
		//resultado = parsearDatosIgresoDetalladoDTO(listaObject);		 
		return resultado;
	}	
   
}