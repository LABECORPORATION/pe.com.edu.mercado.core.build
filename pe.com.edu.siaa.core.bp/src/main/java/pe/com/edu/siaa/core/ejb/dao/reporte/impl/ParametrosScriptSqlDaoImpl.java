package pe.com.edu.siaa.core.ejb.dao.reporte.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericDAOImpl;
import pe.com.edu.siaa.core.ejb.dao.reporte.local.ParametrosScriptSqlDaoLocal;
import pe.com.edu.siaa.core.ejb.util.jms.UUIDUtil;
import pe.com.edu.siaa.core.model.dto.reporte.ParametrosScriptSqlDTO;
import pe.com.edu.siaa.core.model.jpa.reporte.ParametrosScriptSql;
import pe.com.edu.siaa.core.model.util.StringUtils;

/**
 * La Class ParametrosScriptSqlDaoImpl.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 19:53:49 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
public class ParametrosScriptSqlDaoImpl extends  GenericDAOImpl<String, ParametrosScriptSql> implements ParametrosScriptSqlDaoLocal  {

    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.ParametrosScriptSqlDaoLocal#listarParametrosScriptSql(pe.com.edu.siaa.core.model.jpa.seguridad.ParametrosScriptSql)
     */  
    @Override	 
    public List<ParametrosScriptSql> listarParametrosScriptSql(ParametrosScriptSqlDTO parametrosScriptSql) {
        Query query = generarQueryListaParametrosScriptSql(parametrosScriptSql, false);
        query.setFirstResult(parametrosScriptSql.getStartRow());
        query.setMaxResults(parametrosScriptSql.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista ParametrosScriptSql.
     *
     * @param ParametrosScriptSqlDTO el parametrosScriptSql
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaParametrosScriptSql(ParametrosScriptSqlDTO parametrosScriptSql, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idParametrosScriptSql) from ParametrosScriptSql o where 1=1 ");
        } else {
            jpaql.append(" select o from ParametrosScriptSql o where 1=1 ");           
        }
		if (!StringUtils.isNullOrEmpty(parametrosScriptSql.getSearch())) {
	          jpaql.append(" and upper(o.idParametrosScriptSql) like :search ");
	          parametros.put("search", "%" + parametrosScriptSql.getSearch().toUpperCase() + "%");
	    } else {
			if (!StringUtils.isNullOrEmpty(parametrosScriptSql.getDescripcion())) {
				jpaql.append(" and upper(o.descripcion) like :descripcion ");
				parametros.put("descripcion", "%" + parametrosScriptSql.getDescripcion().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(parametrosScriptSql.getCodigoParametro())) {
				jpaql.append(" and upper(o.codigoParametro) like :codigoParametro ");
				parametros.put("codigoParametro", "%" + parametrosScriptSql.getCodigoParametro().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(parametrosScriptSql.getValorDefecto())) {
				jpaql.append(" and upper(o.valorDefecto) like :valorDefecto ");
				parametros.put("valorDefecto", "%" + parametrosScriptSql.getValorDefecto().toUpperCase() + "%");
			}
		}
        if (!esContador) {
            //jpaql.append(" ORDER BY 1 ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.ParametrosScriptSqlDaoLocal#contarListar{entity.getClassName()}(pe.com.edu.siaa.core.model.jpa.seguridad.ParametrosScriptSqlDTO)
     */
	@Override
    public int contarListarParametrosScriptSql(ParametrosScriptSqlDTO parametrosScriptSql) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaParametrosScriptSql(parametrosScriptSql, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.ParametrosScriptSqlDaoLocal#generarIdParametrosScriptSql()
     */
	 @Override
    public String generarIdParametrosScriptSql() {
        String resultado = UUIDUtil.generarElementUUID();
        return resultado;
    }
   
}