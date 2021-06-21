package pe.com.edu.siaa.core.ejb.dao.seguridad.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericDAOImpl;
import pe.com.edu.siaa.core.ejb.dao.seguridad.local.GrupoUsuarioUsuarioDaoLocal;
import pe.com.edu.siaa.core.ejb.util.jms.UUIDUtil;
import pe.com.edu.siaa.core.model.dto.seguridad.GrupoUsuarioUsuarioDTO;
import pe.com.edu.siaa.core.model.jpa.seguridad.GrupoUsuarioUsuario;
import pe.com.edu.siaa.core.model.util.StringUtils;

/**
 * La Class GrupoUsuarioUsuarioDaoImpl.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 19:53:32 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
public class GrupoUsuarioUsuarioDaoImpl extends  GenericDAOImpl<String, GrupoUsuarioUsuario> implements GrupoUsuarioUsuarioDaoLocal  {

    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.GrupoUsuarioUsuarioDaoLocal#listarGrupoUsuarioUsuario(pe.com.edu.siaa.core.model.jpa.seguridad.GrupoUsuarioUsuario)
     */  
    @Override	 
    public List<GrupoUsuarioUsuario> listarGrupoUsuarioUsuario(GrupoUsuarioUsuarioDTO grupoUsuarioUsuario) {
        Query query = generarQueryListaGrupoUsuarioUsuario(grupoUsuarioUsuario, false);
        query.setFirstResult(grupoUsuarioUsuario.getStartRow());
        query.setMaxResults(grupoUsuarioUsuario.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista GrupoUsuarioUsuario.
     *
     * @param GrupoUsuarioUsuarioDTO el grupoUsuarioUsuario
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaGrupoUsuarioUsuario(GrupoUsuarioUsuarioDTO grupoUsuarioUsuario, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idGrupoUsuarioUsuario) from GrupoUsuarioUsuario o where 1=1 ");
        } else {
            jpaql.append(" select o from GrupoUsuarioUsuario o left join fetch o.grupoUsuario where 1=1 ");           
        }
        
        jpaql.append(" and o.usuario.idUsuario =:idUsuario ");
        parametros.put("idUsuario", grupoUsuarioUsuario.getId() + "");
        if (!StringUtils.isNullOrEmpty(grupoUsuarioUsuario.getEstado())) {
			jpaql.append(" and upper(o.estado) =:estado ");
			parametros.put("estado", grupoUsuarioUsuario.getEstado().toUpperCase() );
		}
		if (!StringUtils.isNullOrEmpty(grupoUsuarioUsuario.getSearch())) {
	          jpaql.append(" and upper(o.idGrupoUsuarioUsuario) like :search ");
	          parametros.put("search", "%" + grupoUsuarioUsuario.getSearch().toUpperCase() + "%");
	    } else {
			
			if (!StringUtils.isNullOrEmpty(grupoUsuarioUsuario.getFechaCreacion())) {
				jpaql.append(" and o.fechaCreacion = :fechaCreacion ");
				parametros.put("fechaCreacion", grupoUsuarioUsuario.getFechaCreacion());
			}
			if (!StringUtils.isNullOrEmpty(grupoUsuarioUsuario.getUsuarioCreacion())) {
				jpaql.append(" and upper(o.usuarioCreacion) like :usuarioCreacion ");
				parametros.put("usuarioCreacion", "%" + grupoUsuarioUsuario.getUsuarioCreacion().toUpperCase() + "%");
			}
		}
        if (!esContador) {
            //jpaql.append(" ORDER BY 1 ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.GrupoUsuarioUsuarioDaoLocal#contarListar{entity.getClassName()}(pe.com.edu.siaa.core.model.jpa.seguridad.GrupoUsuarioUsuarioDTO)
     */
	@Override
    public int contarListarGrupoUsuarioUsuario(GrupoUsuarioUsuarioDTO grupoUsuarioUsuario) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaGrupoUsuarioUsuario(grupoUsuarioUsuario, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.GrupoUsuarioUsuarioDaoLocal#generarIdGrupoUsuarioUsuario()
     */
	 @Override
    public String generarIdGrupoUsuarioUsuario() {
        return UUIDUtil.generarElementUUID();
    }
   
}