package pe.com.builderp.core.facturacion.ejb.dao.venta.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.builderp.core.facturacion.ejb.dao.venta.local.ProductoDaoLocal;
import pe.com.builderp.core.facturacion.model.dto.venta.ProductoDTO;
import pe.com.builderp.core.facturacion.model.jpa.venta.Producto;
import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericFacturacionDAOImpl;
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
public class ProductoDaoImpl extends  GenericFacturacionDAOImpl<Long, Producto> implements ProductoDaoLocal  {

    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.ProductoDaoLocal#listarProducto(pe.com.builderp.core.facturacion.model.jpa.venta.Producto)
     */  
    @Override	 
    public List<Producto> listarProducto(ProductoDTO producto) {
        Query query = generarQueryListaProducto(producto, false);
        query.setFirstResult(producto.getStartRow());
        query.setMaxResults(producto.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista Producto.
     *
     * @param ProductoDTO el producto
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaProducto(ProductoDTO producto, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idProducto) from Producto o where 1=1 ");
        } else {
            jpaql.append(" select o from Producto o left join fetch o.categoria left join fetch o.itemByUnidadMedida left join fetch o.modelo  left join fetch o.itemByColor left join fetch o.planContableVenta left join fetch o.planContableCompra where 1=1 ");           
        }
		if (!StringUtils.isNullOrEmpty(producto.getSearch())) {
	          jpaql.append(" and (upper(o.codigo) like :search or upper(o.nombre) like :search or upper(o.descripcion) like :search) ");
	          parametros.put("search", "%" + producto.getSearch().toUpperCase() + "%");
	    } else {
			if (!StringUtils.isNullOrEmpty(producto.getNombre())) {
				jpaql.append(" and upper(o.nombre) like :nombre ");
				parametros.put("nombre", "%" + producto.getNombre().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(producto.getDescripcion())) {
				jpaql.append(" and upper(o.descripcion) like :descripcion ");
				parametros.put("descripcion", "%" + producto.getDescripcion().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(producto.getCodigo())) {
				jpaql.append(" and upper(o.codigo) like :codigo ");
				parametros.put("codigo", "%" + producto.getCodigo().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(producto.getCodigoEquivalente())) {
				jpaql.append(" and upper(o.codigoEquivalente) like :codigoEquivalente ");
				parametros.put("codigoEquivalente", "%" + producto.getCodigoEquivalente().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(producto.getCodigoExterno())) {
				jpaql.append(" and upper(o.codigoExterno) like :codigoExterno ");
				parametros.put("codigoExterno", "%" + producto.getCodigoExterno().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(producto.getCodigoReferencia())) {
				jpaql.append(" and upper(o.codigoReferencia) like :codigoReferencia ");
				parametros.put("codigoReferencia", "%" + producto.getCodigoReferencia().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmptyNumeric(producto.getPrecio())) {
				jpaql.append(" and o.precio = :precio ");
				parametros.put("precio", producto.getPrecio());
			}
			if (!StringUtils.isNullOrEmptyNumeric(producto.getStock())) {
				jpaql.append(" and o.stock = :stock ");
				parametros.put("stock", producto.getStock());
			}
			if (!StringUtils.isNullOrEmpty(producto.getFechaVencimiento())) {
				jpaql.append(" and o.fechaVencimiento = :fechaVencimiento ");
				parametros.put("fechaVencimiento", producto.getFechaVencimiento());
			}
			if (!StringUtils.isNullOrEmpty(producto.getVersion())) {
				jpaql.append(" and upper(o.version) like :version ");
				parametros.put("version", "%" + producto.getVersion().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(producto.getAnho())) {
				jpaql.append(" and upper(o.anho) like :anho ");
				parametros.put("anho", "%" + producto.getAnho().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(producto.getNroSerie())) {
				jpaql.append(" and upper(o.nroSerie) like :nroSerie ");
				parametros.put("nroSerie", "%" + producto.getNroSerie().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(producto.getTipo())) {
				jpaql.append(" and upper(o.tipo) like :tipo ");
				parametros.put("tipo", "%" + producto.getTipo().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(producto.getEsAfectoIGV())) {
				jpaql.append(" and upper(o.esAfectoIGV) like :esAfectoIGV ");
				parametros.put("esAfectoIGV", "%" + producto.getEsAfectoIGV().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(producto.getEstado())) {
				jpaql.append(" and upper(o.estado) like :estado ");
				parametros.put("estado", "%" + producto.getEstado().toUpperCase() + "%");
			}
		}
        if (!esContador) {
            //jpaql.append(" ORDER BY 1 ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.ProductoDaoLocal#contarListar{entity.getClassName()}(pe.com.builderp.core.facturacion.model.jpa.venta.ProductoDTO)
     */
	@Override
    public int contarListarProducto(ProductoDTO producto) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaProducto(producto, true);
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
    public Long generarIdProducto() {
        Long resultado = 1L;
        Query query = createQuery("select max(o.idProducto) from Producto o", null);
        List<Object> listLong =  query.getResultList();
        if (listLong != null && listLong.size() > 0 && listLong.get(0) != null)  {
            Long ultimoIdGenerado = Long.valueOf(listLong.get(0).toString());
            if (!StringUtils.isNullOrEmpty(ultimoIdGenerado)) {
                resultado = resultado + ultimoIdGenerado;
            }
        }
        return resultado;
    }
   
	 
		@Override
	    public void updateProductoStock(Long idProducto,BigDecimal numeroStock ) throws Exception {
			Map<String, Object> parametros = new HashMap<String, Object>();
			Query query = createQuery("UPDATE  Producto o set o.stock=:numeroStock+o.stock   where o.idProducto =:idProducto",parametros);
			query.setParameter("numeroStock", numeroStock);
			query.setParameter("idProducto", idProducto);
			query.executeUpdate();
		}

		@Override
		public void updateOferta() throws Exception {
			// TODO Auto-generated method stub
			Map<String, Object> parametraMap = new HashMap<String, Object>();
			Query query = createQuery(" update Producto o set o.estadoOferta='CANCELADO' where o.fechaVigenciaOferta < CURRENT_DATE", parametraMap);
			query.executeUpdate();
		}
}