package pe.com.edu.siaa.core.ejb.dao.seguridad.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericDAOImpl;
import pe.com.edu.siaa.core.ejb.dao.seguridad.local.UsuarioEntidadDaoLocal;
import pe.com.edu.siaa.core.ejb.factory.CollectionUtil;
import pe.com.edu.siaa.core.ejb.util.jms.UUIDUtil;
import pe.com.edu.siaa.core.model.dto.seguridad.UsuarioEntidadDTO;
import pe.com.edu.siaa.core.model.jpa.seguridad.UsuarioEntidad;
import pe.com.edu.siaa.core.model.util.StringUtils;
import pe.com.edu.siaa.core.model.vo.EgresadoFiltroVO;

/**
 * La Class UsuarioEntidadDaoImpl.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 19:53:47 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
public class UsuarioEntidadDaoImpl extends  GenericDAOImpl<String, UsuarioEntidad> implements UsuarioEntidadDaoLocal  {

	@Override	 
    public  Map<String,Integer> obtenerCantidadEntidadAsignadaMap(List<String> listaIdUsuario) throws Exception {
		Map<String,Integer>  resultado = new HashMap<String,Integer>();
		if (CollectionUtil.isEmpty(listaIdUsuario)) {
			return resultado;
		}
		Map<String, Object> parametros = new HashMap<String, Object>();
		StringBuilder jpaql = new StringBuilder();
	    jpaql.append(" select o.usuario.idUsuario, count(1)  from UsuarioEntidad o where 1=1 ");
	    jpaql.append(" and o.usuario.idUsuario in (:listaIdUsuario) group by  o.usuario.idUsuario ");
		parametros.put("listaIdUsuario", listaIdUsuario);
	    Query query = createQuery(jpaql.toString(), parametros);
	    List<Object[]> resulTemp = query.getResultList();
	    for (Object[] objects : resulTemp) {
			String key = objects[0].toString()	;
			Integer value = Integer.parseInt(objects[1].toString());
			resultado.put(key, value);
		}
		return resultado;
	}
	
    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.UsuarioEntidadDaoLocal#listarUsuarioEntidad(pe.com.edu.siaa.core.model.jpa.seguridad.UsuarioEntidad)
     */  
    @Override	 
    public List<UsuarioEntidad> listarUsuarioEntidad(UsuarioEntidadDTO usuarioEntidad) {
        Query query = generarQueryListaUsuarioEntidad(usuarioEntidad, false);
        query.setFirstResult(usuarioEntidad.getStartRow());
        query.setMaxResults(usuarioEntidad.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista UsuarioEntidad.
     *
     * @param UsuarioEntidadDTO el usuarioEntidad
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaUsuarioEntidad(UsuarioEntidadDTO usuarioEntidad, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idUsuarioEntidad) from UsuarioEntidad o where 1=1 ");
        } else {
            jpaql.append(" select o from UsuarioEntidad o  left join fetch o.entidad where 1=1 ");           
        }
        jpaql.append(" and o.usuario.idUsuario = :idUsuario ");
        parametros.put("idUsuario", usuarioEntidad.getId() + "");
        
		if (!StringUtils.isNullOrEmpty(usuarioEntidad.getSearch())) {
	          jpaql.append(" and upper(o.entidad.nombre) like :search ");
	          parametros.put("search", "%" + usuarioEntidad.getSearch().toUpperCase() + "%");
	    } else {
			if (!StringUtils.isNullOrEmpty(usuarioEntidad.getUsuario())) {
				if (!StringUtils.isNullOrEmpty(usuarioEntidad.getUsuario().getIdUsuario())) {
					jpaql.append(" and upper(o.usuario.idUsuario) like :idUsuario ");
					parametros.put("idUsuario", "%" + usuarioEntidad.getUsuario().getIdUsuario().toUpperCase() + "%");
				}
			}
			if (!StringUtils.isNullOrEmpty(usuarioEntidad.getEstado())) {
				jpaql.append(" and upper(o.estado) like :estado ");
				parametros.put("estado", "%" + usuarioEntidad.getEstado().toUpperCase() + "%");
			}
		}
        if (!esContador) {
            //jpaql.append(" ORDER BY 1 ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.UsuarioEntidadDaoLocal#contarListar{entity.getClassName()}(pe.com.edu.siaa.core.model.jpa.seguridad.UsuarioEntidadDTO)
     */
	@Override
    public int contarListarUsuarioEntidad(UsuarioEntidadDTO usuarioEntidad) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaUsuarioEntidad(usuarioEntidad, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.UsuarioEntidadDaoLocal#generarIdUsuarioEntidad()
     */
	 @Override
    public String generarIdUsuarioEntidad() {
        return UUIDUtil.generarElementUUID();
    }
   
	 
	 
		@Override
		public UsuarioEntidad listarUsuarioEntidadDelect(EgresadoFiltroVO filtro) throws Exception {
			UsuarioEntidad resultado = null;
			StringBuilder jpaql = new StringBuilder();
			jpaql.append("from UsuarioEntidad o  left join fetch o.entidad left join fetch o.usuario where");
			if(filtro.getIdFiltro() != null) {
				jpaql.append(" o.usuario.codigoExterno = :codigoExterno ");
			}else if(filtro.getIdUsuario() != null) {
				jpaql.append("  o.usuario.idUsuario = :idUsuario ");
			}
			Query query = createQuery(jpaql.toString(),null);
			if(filtro.getIdFiltro() !=null) {
				query.setParameter("codigoExterno", filtro.getIdFiltro());
			}else if(filtro.getIdUsuario() !=null) {
				query.setParameter("idUsuario", filtro.getIdUsuario());
			}
			
			List<UsuarioEntidad> listaAlumno = query.getResultList();
			if (listaAlumno != null && listaAlumno.size() > 0) {
				resultado = listaAlumno.get(0);
			}
			return resultado;	
		}

		
}