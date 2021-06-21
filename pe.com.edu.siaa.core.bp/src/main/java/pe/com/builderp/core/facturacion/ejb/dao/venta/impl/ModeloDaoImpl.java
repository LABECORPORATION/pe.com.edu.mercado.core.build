package pe.com.builderp.core.facturacion.ejb.dao.venta.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.builderp.core.facturacion.ejb.dao.venta.local.ModeloDaoLocal;
import pe.com.builderp.core.facturacion.model.dto.venta.ModeloDTO;
import pe.com.builderp.core.facturacion.model.jpa.venta.Modelo;
import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericFacturacionDAOImpl;
import pe.com.edu.siaa.core.model.util.StringUtils;

/**
 * La Class ModeloDaoImpl.
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
public class ModeloDaoImpl extends  GenericFacturacionDAOImpl<Long, Modelo> implements ModeloDaoLocal  {

    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.ModeloDaoLocal#listarModelo(pe.com.builderp.core.facturacion.model.jpa.venta.Modelo)
     */  
    @Override	 
    public List<Modelo> listarModelo(ModeloDTO modelo) {
        Query query = generarQueryListaModelo(modelo, false);
        query.setFirstResult(modelo.getStartRow());
        query.setMaxResults(modelo.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista Modelo.
     *
     * @param ModeloDTO el modelo
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaModelo(ModeloDTO modelo, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idModelo) from Modelo o where 1=1 ");
        } else {
            jpaql.append(" select o from Modelo o left join fetch o.marca left join fetch o.subModelo where 1=1 ");           
        }
		if (!StringUtils.isNullOrEmpty(modelo.getSearch())) {
	          jpaql.append(" and upper(o.nombre) like :search ");
	          parametros.put("search", "%" + modelo.getSearch().toUpperCase() + "%");
	    } else {
			if (!StringUtils.isNullOrEmpty(modelo.getNombre())) {
				jpaql.append(" and upper(o.nombre) like :nombre ");
				parametros.put("nombre", "%" + modelo.getNombre().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(modelo.getCodigo())) {
				jpaql.append(" and upper(o.codigo) like :codigo ");
				parametros.put("codigo", "%" + modelo.getCodigo().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(modelo.getCodigoReferencia())) {
				jpaql.append(" and upper(o.codigoReferencia) like :codigoReferencia ");
				parametros.put("codigoReferencia", "%" + modelo.getCodigoReferencia().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(modelo.getEstado())) {
				jpaql.append(" and upper(o.estado) like :estado ");
				parametros.put("estado", "%" + modelo.getEstado().toUpperCase() + "%");
			}
		}
        if (!esContador) {
            //jpaql.append(" ORDER BY 1 ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.ModeloDaoLocal#contarListar{entity.getClassName()}(pe.com.builderp.core.facturacion.model.jpa.venta.ModeloDTO)
     */
	@Override
    public int contarListarModelo(ModeloDTO modelo) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaModelo(modelo, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.ModeloDaoLocal#generarIdModelo()
     */
	 @Override
    public Long generarIdModelo() {
        Long resultado = 1L;
        Query query = createQuery("select max(o.idModelo) from Modelo o", null);
        List<Object> listLong =  query.getResultList();
        if (listLong != null && listLong.size() > 0 && listLong.get(0) != null)  {
            Long ultimoIdGenerado = Long.valueOf(listLong.get(0).toString());
            if (!StringUtils.isNullOrEmpty(ultimoIdGenerado)) {
                resultado = resultado + ultimoIdGenerado;
            }
        }
        return resultado;
    }
   
}