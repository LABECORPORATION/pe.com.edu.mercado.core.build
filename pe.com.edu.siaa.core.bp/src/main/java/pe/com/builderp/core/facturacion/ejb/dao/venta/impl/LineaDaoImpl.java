package pe.com.builderp.core.facturacion.ejb.dao.venta.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.builderp.core.facturacion.ejb.dao.venta.local.LineaDaoLocal;
import pe.com.builderp.core.facturacion.model.dto.venta.LineaDTO;
import pe.com.builderp.core.facturacion.model.jpa.venta.Linea;
import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericFacturacionDAOImpl;
import pe.com.edu.siaa.core.model.util.StringUtils;

/**
 * La Class LineaDaoImpl.
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
public class LineaDaoImpl extends  GenericFacturacionDAOImpl<Long, Linea> implements LineaDaoLocal  {

    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.LineaDaoLocal#listarLinea(pe.com.builderp.core.facturacion.model.jpa.venta.Linea)
     */  
    @Override	 
    public List<Linea> listarLinea(LineaDTO linea) {
        Query query = generarQueryListaLinea(linea, false);
        query.setFirstResult(linea.getStartRow());
        query.setMaxResults(linea.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista Linea.
     *
     * @param LineaDTO el linea
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaLinea(LineaDTO linea, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idLinea) from Linea o where 1=1 ");
        } else {
            jpaql.append(" select o from Linea o where 1=1 ");           
        }
		if (!StringUtils.isNullOrEmpty(linea.getSearch())) {
	          jpaql.append(" and upper(o.nombre) like :search ");
	          parametros.put("search", "%" + linea.getSearch().toUpperCase() + "%");
	    } else {
			if (!StringUtils.isNullOrEmpty(linea.getNombre())) {
				jpaql.append(" and upper(o.nombre) like :nombre ");
				parametros.put("nombre", "%" + linea.getNombre().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(linea.getCodigo())) {
				jpaql.append(" and upper(o.codigo) like :codigo ");
				parametros.put("codigo", "%" + linea.getCodigo().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(linea.getCodigoReferencia())) {
				jpaql.append(" and upper(o.codigoReferencia) like :codigoReferencia ");
				parametros.put("codigoReferencia", "%" + linea.getCodigoReferencia().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(linea.getEstado())) {
				jpaql.append(" and upper(o.estado) like :estado ");
				parametros.put("estado", "%" + linea.getEstado().toUpperCase() + "%");
			}
		}
        if (!esContador) {
            //jpaql.append(" ORDER BY 1 ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.LineaDaoLocal#contarListar{entity.getClassName()}(pe.com.builderp.core.facturacion.model.jpa.venta.LineaDTO)
     */
	@Override
    public int contarListarLinea(LineaDTO linea) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaLinea(linea, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.LineaDaoLocal#generarIdLinea()
     */
	 @Override
    public Long generarIdLinea() {
        Long resultado = 1L;
        Query query = createQuery("select max(o.idLinea) from Linea o", null);
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