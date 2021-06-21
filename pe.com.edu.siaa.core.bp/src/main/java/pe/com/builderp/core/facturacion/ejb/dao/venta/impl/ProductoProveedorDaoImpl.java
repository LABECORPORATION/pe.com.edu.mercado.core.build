package pe.com.builderp.core.facturacion.ejb.dao.venta.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.builderp.core.facturacion.ejb.dao.venta.local.ProductoDaoLocal;
import pe.com.builderp.core.facturacion.ejb.dao.venta.local.ProductoProveedorDaoLocal;
import pe.com.builderp.core.facturacion.model.dto.venta.ProductoDTO;
import pe.com.builderp.core.facturacion.model.dto.venta.ProductoProveedorDTO;
import pe.com.builderp.core.facturacion.model.jpa.venta.DetalleProducto;
import pe.com.builderp.core.facturacion.model.jpa.venta.Producto;
import pe.com.builderp.core.facturacion.model.jpa.venta.ProductoProveedor;
import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericFacturacionDAOImpl;
import pe.com.edu.siaa.core.ejb.util.jms.UUIDUtil;
import pe.com.edu.siaa.core.model.estate.EstadoGeneralState;
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
public class ProductoProveedorDaoImpl extends  GenericFacturacionDAOImpl<String, ProductoProveedor> implements ProductoProveedorDaoLocal  {

    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.ProductoDaoLocal#listarProducto(pe.com.builderp.core.facturacion.model.jpa.venta.Producto)
     */  
    @Override	 
    public List<ProductoProveedor> listarProductoProveedor(ProductoProveedorDTO productoProveedor) {
        Query query = generarQueryListaProductoProveedor(productoProveedor, false);
        query.setFirstResult(productoProveedor.getStartRow());
        query.setMaxResults(productoProveedor.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista ProductoProveedor.
     *
     * @param ProductoProveedorDTO el ProductoProveedor
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaProductoProveedor(ProductoProveedorDTO productoProveedor, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idProductoProveedor) from ProductoProveedor o where 1=1 ");
        } else {
            jpaql.append(" select o from ProductoProveedor o left join fetch o.producto left join fetch o.proveedor where 1=1 ");           
        } 
		if (!StringUtils.isNullOrEmpty(productoProveedor.getId())) {
	          jpaql.append(" and o.producto.idProducto=:idProducto ");
	          parametros.put("idProducto", ObjectUtil.objectToLong(productoProveedor.getId()));
	    }
		
  		if (!StringUtils.isNullOrEmpty(productoProveedor.getIdPadreView())) {
	          jpaql.append(" and o.proveedor.idProveedor =:idProveedor ");
	          parametros.put("idProveedor", productoProveedor.getIdPadreView());
	    }
    
	 
		if (!StringUtils.isNullOrEmpty(productoProveedor.getSearch())) {
			//jpaql.append(" and (UPPER(o.nombre || ' ' ||o.descripcion ) like :search) ");  
			//importante -- tfs('','','','','');
			//productoProveedor.setSearch(productoProveedor.getSearch().replace(" ", "&"));
	         //jpaql.append(" and fts('simple', o.producto.nombre,o.producto.codigo,:search ) = true ");
	        // parametros.put("search", productoProveedor.getSearch().toUpperCase()+":*");
			jpaql.append(" and upper(o.producto.nombre || o.producto.codigo) like :search ");
	        parametros.put("search", "%" + productoProveedor.getSearch().toUpperCase() + "%");
	    }  
        if (!esContador) {
            jpaql.append(" ORDER BY o.producto.nombre asc ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.ProductoProveedorDaoLocal#contarListar{entity.getClassName()}(pe.com.builderp.core.facturacion.model.jpa.venta.ProductoProveedorDTO)
     */
	@Override
    public int contarListarProductoProveedor(ProductoProveedorDTO productoProveedor) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaProductoProveedor(productoProveedor, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.ProductoDaoLocal#generarIdProducto()
     */
	 @Override
    public String generarIdProductoProveedor() {
        /*Long resultado = 1L;
        Query query = createQuery("select max(o.idProducto) from Producto o", null);
        List<Object> listLong =  query.getResultList();
        if (listLong != null && listLong.size() > 0 && listLong.get(0) != null)  {
            Long ultimoIdGenerado = Long.valueOf(listLong.get(0).toString());
            if (!StringUtils.isNullOrEmpty(ultimoIdGenerado)) {
                resultado = resultado + ultimoIdGenerado;
            }
        }*/
        return UUIDUtil.generarElementUUID();
    }
	

		@Override
		public List<ProductoProveedor> listaProductoProveedor(Long idProducto) throws Exception {
			Map<String, Object> parametros = new HashMap<String, Object>();
			boolean ejecutarQuery = false;
			StringBuilder jpaql = new StringBuilder();
			jpaql.append("from ProductoProveedor dm left join fetch dm.producto where 1 = 1 ");
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