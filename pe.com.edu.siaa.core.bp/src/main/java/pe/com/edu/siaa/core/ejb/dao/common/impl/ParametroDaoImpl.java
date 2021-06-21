package pe.com.edu.siaa.core.ejb.dao.common.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.edu.siaa.core.ejb.dao.common.local.ParametroDaoLocal;
import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericDAOImpl;
import pe.com.edu.siaa.core.model.dto.common.ParametroDTO;
import pe.com.edu.siaa.core.model.jpa.common.Parametro;
import pe.com.edu.siaa.core.model.util.StringUtils;

/**
 * La Class ParametroDaoImpl.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu Dec 14 00:27:42 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
public class ParametroDaoImpl extends  GenericDAOImpl<String, Parametro> implements ParametroDaoLocal  {

    /* (non-Javadoc)
     * @see pe.com.builderp.core.ejb.dao.common.local.ParametroDaoLocal#listarParametro(pe.com.builderp.core.model.jpa.common.Parametro)
     */  
    @Override	 
    public List<Parametro> listarParametro(ParametroDTO parametro) {
        Query query = generarQueryListaParametro(parametro, false);
        query.setFirstResult(parametro.getStartRow());
        query.setMaxResults(parametro.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista Parametro.
     *
     * @param ParametroDTO el parametro
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaParametro(ParametroDTO parametro, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idParametro) from Parametro o where 1=1 ");
        } else {
            jpaql.append(" select o from Parametro o left join fetch o.entidad where 1=1 ");           
        }
        
        if (!StringUtils.isNullOrEmpty(parametro.getIdEntidadSelect())) {
	        jpaql.append(" and o.entidad.idEntidad =:idEntidad ");
	        parametros.put("idEntidad", parametro.getIdEntidadSelect());
        }
        
		if (!StringUtils.isNullOrEmpty(parametro.getSearch())) {
	          jpaql.append(" and upper(o.descripcion) like :search ");
	          parametros.put("search", "%" + parametro.getSearch().toUpperCase() + "%");
	    } else {
			if (!StringUtils.isNullOrEmpty(parametro.getDescripcion())) {
				jpaql.append(" and upper(o.descripcion) like :descripcion ");
				parametros.put("descripcion", "%" + parametro.getDescripcion().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(parametro.getCodigo())) {
				jpaql.append(" and upper(o.codigo) like :codigo ");
				parametros.put("codigo", "%" + parametro.getCodigo().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(parametro.getValor())) {
				jpaql.append(" and upper(o.valor) like :valor ");
				parametros.put("valor", "%" + parametro.getValor().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(parametro.getEstado())) {
				jpaql.append(" and o.estado =:estado ");
				parametros.put("estado", parametro.getEstado());
			}
		}
        if (!esContador) {
            //jpaql.append(" ORDER BY 1 ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.builderp.core.ejb.dao.common.local.ParametroDaoLocal#contarListar{entity.getClassName()}(pe.com.builderp.core.model.jpa.common.ParametroDTO)
     */
	@Override
    public int contarListarParametro(ParametroDTO parametro) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaParametro(parametro, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.builderp.core.ejb.dao.common.local.ParametroDaoLocal#generarIdParametro()
     */
	 @Override
    public String generarIdParametro() {
        String resultado = "1";
        Query query = createQuery("select max(o.idParametro) from Parametro o", null);
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