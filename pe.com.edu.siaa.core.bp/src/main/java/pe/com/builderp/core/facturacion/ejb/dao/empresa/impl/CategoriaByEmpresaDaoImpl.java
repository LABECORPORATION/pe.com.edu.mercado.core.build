package pe.com.builderp.core.facturacion.ejb.dao.empresa.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map; 

import javax.ejb.Stateless;
import javax.persistence.Query; 

import pe.com.builderp.core.facturacion.ejb.dao.empresa.local.CategoriaByEmpresaDaoLocal;
import pe.com.builderp.core.facturacion.model.dto.empresa.CategoriaByEmpresaDTO;
import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericFacturacionDAOImpl;
import pe.com.edu.siaa.core.ejb.util.jms.UUIDUtil;
import pe.com.edu.siaa.core.model.jpa.empresa.CategoriaByEmpresa;
import pe.com.edu.siaa.core.model.util.StringUtils;

/**
 * La Class CategoriaDaoImpl.
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
public class CategoriaByEmpresaDaoImpl extends  GenericFacturacionDAOImpl<String, CategoriaByEmpresa> implements CategoriaByEmpresaDaoLocal  {

    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.CategoriaDaoLocal#listarCategoria(pe.com.builderp.core.facturacion.model.jpa.venta.Categoria)
     */  
    @Override	 
    public List<CategoriaByEmpresa> listarCategoria(CategoriaByEmpresaDTO categoria) {
        Query query = generarQueryListaCategoria(categoria, false);
        query.setFirstResult(categoria.getStartRow());
        query.setMaxResults(categoria.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista Categoria.
     *
     * @param CategoriaByEmpresaDTO el categoria
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaCategoria(CategoriaByEmpresaDTO categoria, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idCategoria) from CategoriaByEmpresa o where 1=1 ");
        } else {
            jpaql.append(" select o from CategoriaByEmpresa o   where 1=1 ");           
        }
		if (!StringUtils.isNullOrEmpty(categoria.getId())) {
	          jpaql.append(" and  o.tipoCat =:tipoCat ");
	          parametros.put("tipoCat",  categoria.getId()  );
	    }
		if (!StringUtils.isNullOrEmpty(categoria.getSearch())) {
	          jpaql.append(" and upper(o.nombre) like :search ");
	          parametros.put("search", "%" + categoria.getSearch().toUpperCase() + "%");
	    } else {
			if (!StringUtils.isNullOrEmpty(categoria.getNombre())) {
				jpaql.append(" and upper(o.nombre) like :nombre ");
				parametros.put("nombre", "%" + categoria.getNombre().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(categoria.getDescripcion())) {
				jpaql.append(" and upper(o.descripcion) like :descripcion ");
				parametros.put("descripcion", "%" + categoria.getDescripcion().toUpperCase() + "%");
			} 
			if (!StringUtils.isNullOrEmpty(categoria.getEstado())) {
				jpaql.append(" and upper(o.estado) like :estado ");
				parametros.put("estado", "%" + categoria.getEstado().toUpperCase() + "%");
			}
		}
        if (!esContador) {
            //jpaql.append(" ORDER BY 1 ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.CategoriaDaoLocal#contarListar{entity.getClassName()}(pe.com.builderp.core.facturacion.model.jpa.venta.CategoriaDTO)
     */
	@Override
    public int contarListarCategoria(CategoriaByEmpresaDTO categoria) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaCategoria(categoria, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.CategoriaDaoLocal#generarIdCategoria()
     */
	 @Override
    public String generarIdCategoria() { 
        return UUIDUtil.generarElementUUID();
    }
	 
	 
	@Override
	public CategoriaByEmpresa obtenerCategoriaByParameter(CategoriaByEmpresaDTO categoriaByEmpresa) throws Exception {
		CategoriaByEmpresa resultado = null;
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", categoriaByEmpresa.getId());
		Query query = createQuery("from CategoriaByEmpresa a where   a.idCategoria =:id",parametros);
	
		List<CategoriaByEmpresa> listaAnhoSemestre = query.getResultList();
		if (listaAnhoSemestre != null && listaAnhoSemestre.size() > 0) {
			resultado = listaAnhoSemestre.get(0);
		} 
		return resultado;
	}
   
}