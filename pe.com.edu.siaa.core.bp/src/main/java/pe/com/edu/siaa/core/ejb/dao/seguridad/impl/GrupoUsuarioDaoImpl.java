package pe.com.edu.siaa.core.ejb.dao.seguridad.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericDAOImpl;
import pe.com.edu.siaa.core.ejb.dao.seguridad.local.GrupoUsuarioDaoLocal;
import pe.com.edu.siaa.core.model.dto.seguridad.GrupoUsuarioDTO;
import pe.com.edu.siaa.core.model.jpa.seguridad.GrupoUsuario;
import pe.com.edu.siaa.core.model.util.StringUtils;

/**
 * La Class GrupoUsuarioDaoImpl.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 19:53:34 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
public class GrupoUsuarioDaoImpl extends  GenericDAOImpl<Long, GrupoUsuario> implements GrupoUsuarioDaoLocal  {

    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.GrupoUsuarioDaoLocal#listarGrupoUsuario(pe.com.edu.siaa.core.model.jpa.seguridad.GrupoUsuario)
     */  
    @Override	 
    public List<GrupoUsuario> listarGrupoUsuario(GrupoUsuarioDTO grupoUsuario) {
        Query query = generarQueryListaGrupoUsuario(grupoUsuario, false);
        query.setFirstResult(grupoUsuario.getStartRow());
        query.setMaxResults(grupoUsuario.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista GrupoUsuario.
     *
     * @param GrupoUsuarioDTO el grupoUsuario
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaGrupoUsuario(GrupoUsuarioDTO grupoUsuario, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idGrupoUsuario) from GrupoUsuario o where 1=1 ");
        } else {
            jpaql.append(" select o from GrupoUsuario o where 1=1 ");           
        }
		if (!StringUtils.isNullOrEmpty(grupoUsuario.getSearch())) {
	          jpaql.append(" and upper(o.descripcion) like :search ");
	          parametros.put("search", "%" + grupoUsuario.getSearch().toUpperCase() + "%");
	    } else {
			if (!StringUtils.isNullOrEmpty(grupoUsuario.getDescripcion())) {
				jpaql.append(" and upper(o.descripcion) like :descripcion ");
				parametros.put("descripcion", "%" + grupoUsuario.getDescripcion().toUpperCase() + "%");
			}
		}
        if (!esContador) {
            //jpaql.append(" ORDER BY 1 ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.GrupoUsuarioDaoLocal#contarListar{entity.getClassName()}(pe.com.edu.siaa.core.model.jpa.seguridad.GrupoUsuarioDTO)
     */
	@Override
    public int contarListarGrupoUsuario(GrupoUsuarioDTO grupoUsuario) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaGrupoUsuario(grupoUsuario, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.GrupoUsuarioDaoLocal#generarIdGrupoUsuario()
     */
	 @Override
    public Long generarIdGrupoUsuario() {
        Long resultado = 1L;
        Query query = createQuery("select max(o.idGrupoUsuario) from GrupoUsuario o", null);
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