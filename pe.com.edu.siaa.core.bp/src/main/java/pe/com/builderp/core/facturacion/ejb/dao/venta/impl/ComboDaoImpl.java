package pe.com.builderp.core.facturacion.ejb.dao.venta.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.builderp.core.facturacion.ejb.dao.venta.local.ComboDaoLocal;
import pe.com.builderp.core.facturacion.model.dto.venta.ComboDTO;
import pe.com.builderp.core.facturacion.model.jpa.venta.Combo;
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
public class ComboDaoImpl extends  GenericFacturacionDAOImpl<String, Combo> implements ComboDaoLocal  {

    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.ProductoDaoLocal#listarProducto(pe.com.builderp.core.facturacion.model.jpa.venta.Producto)
     */  
    @Override	 
    public List<Combo> listarCombo(ComboDTO Combo) {
        Query query = generarQueryListaCombo(Combo, false);
        query.setFirstResult(Combo.getStartRow());
        query.setMaxResults(Combo.getOffset());
        return query.getResultList();
    }   
    
	@Override
	public List<Combo> listarComboBy(String idDetalleProducto) throws Exception {
		Query query = createQuery("from Combo combo  where   combo.detalleProducto.idDetalleProducto = :idDetalleProducto ",null);
		query.setParameter("idDetalleProducto", idDetalleProducto);
		return query.getResultList();
	}
   
    /**
     * Generar query lista Combo.
     *
     * @param ComboDTO el Combo
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaCombo(ComboDTO Combo, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idCombo) from Combo o where 1=1 ");
        } else {
            jpaql.append(" select o from Combo o left join fetch o.detalleProducto   where 1=1 ");           
        } 
 
	      jpaql.append(" and o.detalleProducto.idDetalleProducto=:idDetalleProducto ");
	      parametros.put("idDetalleProducto", ObjectUtil.objectToLong(Combo.getId()));
	      

  		if (!StringUtils.isNullOrEmpty(Combo.getSearch())) {
	          jpaql.append(" and upper(o.idCombo) like :search ");
	          parametros.put("search", "%" + Combo.getSearch().toUpperCase() + "%");
	    }
        if (!esContador) {
            //jpaql.append(" ORDER BY o.idCombo ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.ComboDaoLocal#contarListar{entity.getClassName()}(pe.com.builderp.core.facturacion.model.jpa.venta.ComboDTO)
     */
	@Override
    public int contarListarCombo(ComboDTO Combo) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaCombo(Combo, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.ComboDaoLocal#generarIdCombo()
     */
	 @Override
    public String generarIdCombo() {
        return UUIDUtil.generarElementUUID();
    }
 
	@Override
	public Combo findByComboIdDetalleProducto(Long idDetalleProducto) throws Exception {
		Combo resultado = null;
		StringBuilder jpaql = new StringBuilder();
		jpaql.append("from Combo o left join fetch o.detalleProducto ");
		jpaql.append(" where o.detalleProducto.idDetalleProducto =:idDetalleProducto  ");
		Query query = createQuery(jpaql.toString(),null);
		query.setParameter("idDetalleProducto", idDetalleProducto);  
		List<Combo> listaProducto = query.getResultList();
		if (listaProducto != null && listaProducto.size() > 0) {
			resultado = listaProducto.get(0);
		}
		return resultado;	
	}
	
	@Override
	public List<Combo> listaCombo(Long idDetalleProducto) throws Exception {
		Map<String, Object> parametros = new HashMap<String, Object>();
		boolean ejecutarQuery = false;
		StringBuilder jpaql = new StringBuilder();
		jpaql.append("from Combo dm left join fetch dm.detalleProducto where 1 = 1 ");
		if (StringUtils.isNotNullOrBlank(idDetalleProducto)) {
			ejecutarQuery = true;
			jpaql.append(" and dm.detalleProducto.idDetalleProducto=:idDetalleProducto ");
			parametros.put("idDetalleProducto", idDetalleProducto);
		}
		//jpaql.append(" order by dm.detCargaLectiva.detPlanEstudio.descripcionCurso");
		if (ejecutarQuery) {		
			Query query = createQuery(jpaql.toString(), parametros);
			return query.getResultList();
		} 
		return null;
	}
}