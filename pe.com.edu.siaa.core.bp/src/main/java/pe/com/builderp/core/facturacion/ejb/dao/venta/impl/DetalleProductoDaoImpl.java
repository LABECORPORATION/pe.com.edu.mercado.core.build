package pe.com.builderp.core.facturacion.ejb.dao.venta.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.builderp.core.facturacion.ejb.dao.venta.local.DetalleProductoDaoLocal;
import pe.com.builderp.core.facturacion.model.dto.venta.DetalleProductoDTO;
import pe.com.builderp.core.facturacion.model.jpa.venta.DetalleProducto;
import pe.com.builderp.core.facturacion.model.jpa.venta.Producto;
import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericFacturacionDAOImpl;
import pe.com.edu.siaa.core.ejb.util.jms.UUIDUtil;
import pe.com.edu.siaa.core.model.util.ObjectUtil;
import pe.com.edu.siaa.core.model.util.StringUtils;

/**
 * La Class ProductoDaoImpl.
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
public class DetalleProductoDaoImpl extends  GenericFacturacionDAOImpl<String, DetalleProducto> implements DetalleProductoDaoLocal  {

    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.ProductoDaoLocal#listarProducto(pe.com.builderp.core.facturacion.model.jpa.venta.Producto)
     */  
    @Override	 
    public List<DetalleProducto> listarDetalleProducto(DetalleProductoDTO detalleProducto) {
        Query query = generarQueryListaDetalleProducto(detalleProducto, false);
        query.setFirstResult(detalleProducto.getStartRow());
        query.setMaxResults(detalleProducto.getOffset());
        return query.getResultList();
    }   
    
	@Override
	public List<DetalleProducto> listarDetalleProductoBy(Long idProducto) throws Exception {
		Query query = createQuery("from DetalleProducto detProducto  where detProducto.estado='A' AND  detProducto.producto.idProducto = :idProducto order by detProducto.precioVenta asc ",null);
		query.setParameter("idProducto", idProducto);
		return query.getResultList();
	}
   
    /**
     * Generar query lista DetalleProducto.
     *
     * @param DetalleProductoDTO el DetalleProducto
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaDetalleProducto(DetalleProductoDTO detalleProducto, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idDetalleProducto) from DetalleProducto o where 1=1 ");
        } else {
            jpaql.append(" select o from DetalleProducto o left join fetch o.producto left join fetch o.itemByUnidadMedida   where 1=1 ");           
        } 
 
	      jpaql.append(" and o.producto.idProducto=:idProducto ");
	      parametros.put("idProducto", ObjectUtil.objectToLong(detalleProducto.getId()));	      
	      if (StringUtils.isNullOrEmpty(detalleProducto.getIdPadreView())) {
	    	  jpaql.append(" and o.combo=null");
	      }
	      
  		if (!StringUtils.isNullOrEmpty(detalleProducto.getSearch())) {
	          jpaql.append(" and upper(o.idDetalleProducto) like :search ");
	          parametros.put("search", "%" + detalleProducto.getSearch().toUpperCase() + "%");
	    }
        if (!esContador) {
            //jpaql.append(" ORDER BY o.idDetalleProducto ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.DetalleProductoDaoLocal#contarListar{entity.getClassName()}(pe.com.builderp.core.facturacion.model.jpa.venta.DetalleProductoDTO)
     */
	@Override
    public int contarListarDetalleProducto(DetalleProductoDTO DetalleProducto) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaDetalleProducto(DetalleProducto, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.DetalleProductoDaoLocal#generarIdDetalleProducto()
     */
	 @Override
    public String generarIdDetalleProducto() {
        return UUIDUtil.generarElementUUID();
    }
	 
	@Override
	public DetalleProducto findByDetProducto(String codigo) throws Exception {
		DetalleProducto resultado = null;
		StringBuilder jpaql = new StringBuilder();
		jpaql.append("from DetalleProducto o left join fetch o.producto left join fetch o.itemByUnidadMedida ");
		jpaql.append(" where o.codigoBarra =:codigo and o.estado='A'");
		Query query = createQuery(jpaql.toString(),null);
		query.setParameter("codigo", codigo); 
		List<DetalleProducto> listaProducto = query.getResultList();
		if (listaProducto != null && listaProducto.size() > 0) {
			resultado = listaProducto.get(0);
		}
		return resultado;	
	}
	
	@Override
	public List<DetalleProducto> listarDetalleProductoCombo(DetalleProductoDTO detalleProducto) throws Exception {
		Map<String, Object> parametros = new HashMap<String, Object>();
		boolean ejecutarQuery = false;
		StringBuilder jpaql = new StringBuilder();
		jpaql.append("from DetalleProducto dm left join fetch dm.producto where 1 = 1 ");
		if (StringUtils.isNotNullOrBlank(detalleProducto.getTipo())) {
			ejecutarQuery = true;
			jpaql.append(" and dm.producto.idProducto=:idProducto ");
		     parametros.put("idProducto", ObjectUtil.objectToLong(detalleProducto.getId()));
		     
			 jpaql.append(" and dm.combo=:combo ");
		     parametros.put("combo", ObjectUtil.objectToString(detalleProducto.getTipo()));
		}
		
		
		if (ejecutarQuery) {		
			Query query = createQuery(jpaql.toString(), parametros);
			return query.getResultList();
		} 
		return null;
	}
		
	
	@Override
	public DetalleProducto findByDetalleProductoIdProducto(Long idProducto) throws Exception {
		DetalleProducto resultado = null;
		StringBuilder jpaql = new StringBuilder();
		jpaql.append("from DetalleProducto o left join fetch o.producto left join fetch o.itemByUnidadMedida ");
		jpaql.append(" where o.producto.idProducto =:idProducto order by o.idDetalleProducto asc  ");
		Query query = createQuery(jpaql.toString(),null);
		query.setParameter("idProducto", idProducto);  
		List<DetalleProducto> listaProducto = query.getResultList();
		if (listaProducto != null && listaProducto.size() > 0) {
			resultado = listaProducto.get(0);
		}
		return resultado;	
	}
	
	@Override
	public List<DetalleProducto> listaDetalleProducto(Long idProducto) throws Exception {
		Map<String, Object> parametros = new HashMap<String, Object>();
		boolean ejecutarQuery = false;
		StringBuilder jpaql = new StringBuilder();
		jpaql.append("from DetalleProducto dm left join fetch dm.producto where 1 = 1 ");
		if (StringUtils.isNotNullOrBlank(idProducto)) {
			ejecutarQuery = true;
			jpaql.append(" and dm.producto.idProducto=:idProducto ");
			parametros.put("idProducto", idProducto);
		}
		//jpaql.append(" order by dm.detCargaLectiva.detPlanEstudio.descripcionCurso");
		if (ejecutarQuery) {		
			Query query = createQuery(jpaql.toString(), parametros);
			return query.getResultList();
		} 
		return null;
	}
}