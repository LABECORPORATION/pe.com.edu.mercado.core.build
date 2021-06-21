package pe.com.edu.siaa.core.ejb.dao.reporte.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericDAOImpl;
import pe.com.edu.siaa.core.ejb.dao.reporte.local.ScriptSqlDaoLocal;
import pe.com.edu.siaa.core.ejb.util.jms.UUIDUtil;
import pe.com.edu.siaa.core.model.dto.reporte.ScriptSqlDTO;
import pe.com.edu.siaa.core.model.jpa.reporte.ScriptSql;
import pe.com.edu.siaa.core.model.util.StringUtils;

/**
 * La Class ScriptSqlDaoImpl.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 19:53:36 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
public class ScriptSqlDaoImpl extends  GenericDAOImpl<String, ScriptSql> implements ScriptSqlDaoLocal  {

    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.ScriptSqlDaoLocal#listarScriptSql(pe.com.edu.siaa.core.model.jpa.seguridad.ScriptSql)
     */  
    @Override	 
    public List<ScriptSql> listarScriptSql(ScriptSqlDTO scriptSql) {
        Query query = generarQueryListaScriptSql(scriptSql, false);
        query.setFirstResult(scriptSql.getStartRow());
        query.setMaxResults(scriptSql.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista ScriptSql.
     *
     * @param ScriptSqlDTO el scriptSql
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaScriptSql(ScriptSqlDTO scriptSql, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idScriptSql) from ScriptSql o where 1=1 ");
        } else {
            jpaql.append(" select o from ScriptSql o where 1=1 ");           
        }
        if (!StringUtils.isNullOrEmpty(scriptSql.getTodos())) {//comodin
        	jpaql.append(" and o.escuelaPertenece =:variable ");
			parametros.put("variable", scriptSql.getTodos());

        }

        if (!StringUtils.isNullOrEmpty(scriptSql.getUpdate())) {//comodin
        	jpaql.append(" and o.tipoSentencia =:variable ");
			parametros.put("variable", scriptSql.getUpdate());

        }
        
        if (!StringUtils.isNullOrEmpty(scriptSql.getInsert())) {//comodin
        	jpaql.append(" and o.tipoSentencia =:variable ");
			parametros.put("variable", scriptSql.getInsert());

        }
        if (!StringUtils.isNullOrEmpty(scriptSql.getSistem())) {//comodin
        	jpaql.append(" and o.escuelaPertenece =:variable ");
			parametros.put("variable", scriptSql.getSistem());

        }
        if (!StringUtils.isNullOrEmpty(scriptSql.getAdmin())) {//comodin
        	jpaql.append(" and o.escuelaPertenece =:variable ");
			parametros.put("variable", scriptSql.getAdmin());

        }
        if (!StringUtils.isNullOrEmpty(scriptSql.getConta())) {//comodin
        	jpaql.append(" and o.escuelaPertenece =:variable ");
			parametros.put("variable", scriptSql.getConta());

        }
        if (!StringUtils.isNullOrEmpty(scriptSql.getDerech())) {//comodin
        	jpaql.append(" and o.escuelaPertenece =:variable ");
			parametros.put("variable", scriptSql.getDerech());

        }
		if (!StringUtils.isNullOrEmpty(scriptSql.getSearch())) {
	          jpaql.append(" and ((upper(o.codigo) like :search) or (upper(o.descripcion) like :search) )");
	          parametros.put("search", "%" + scriptSql.getSearch().toUpperCase() + "%");
	    } else {
			if (!StringUtils.isNullOrEmpty(scriptSql.getCodigo())) {
				jpaql.append(" and upper(o.codigo) like :codigo ");
				parametros.put("codigo", "%" + scriptSql.getCodigo().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(scriptSql.getTipoSentencia())) {
				jpaql.append(" and upper(o.tipoSentencia) like :tipoSentencia ");
				parametros.put("tipoSentencia", "%" + scriptSql.getTipoSentencia().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(scriptSql.getDescripcion())) {
				jpaql.append(" and upper(o.descripcion) like :descripcion ");
				parametros.put("descripcion", "%" + scriptSql.getDescripcion().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(scriptSql.getSql())) {
				jpaql.append(" and upper(o.sql) like :sql ");
				parametros.put("sql", "%" + scriptSql.getSql().toUpperCase() + "%");
			}
		}
        if (!esContador) {
            //jpaql.append(" ORDER BY 1 ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.ScriptSqlDaoLocal#contarListar{entity.getClassName()}(pe.com.edu.siaa.core.model.jpa.seguridad.ScriptSqlDTO)
     */
	@Override
    public int contarListarScriptSql(ScriptSqlDTO scriptSql) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaScriptSql(scriptSql, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.ScriptSqlDaoLocal#generarIdScriptSql()
     */
	 @Override
    public String generarIdScriptSql() {
        String resultado = UUIDUtil.generarElementUUID();
        return resultado;
    }
   
}