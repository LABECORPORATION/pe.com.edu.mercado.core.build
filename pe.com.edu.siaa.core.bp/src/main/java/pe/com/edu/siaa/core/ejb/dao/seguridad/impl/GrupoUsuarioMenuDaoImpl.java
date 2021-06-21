package pe.com.edu.siaa.core.ejb.dao.seguridad.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericDAOImpl;
import pe.com.edu.siaa.core.ejb.dao.seguridad.local.GrupoUsuarioMenuDaoLocal;
import pe.com.edu.siaa.core.ejb.util.jms.UUIDUtil;
import pe.com.edu.siaa.core.model.dto.seguridad.GrupoUsuarioMenuDTO;
import pe.com.edu.siaa.core.model.jpa.seguridad.GrupoUsuarioMenu;
import pe.com.edu.siaa.core.model.jpa.seguridad.Menu;
import pe.com.edu.siaa.core.model.util.ObjectUtil;
import pe.com.edu.siaa.core.model.util.StringUtils;

/**
 * La Class GrupoUsuarioMenuDaoImpl.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 19:53:50 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
public class GrupoUsuarioMenuDaoImpl extends  GenericDAOImpl<String, GrupoUsuarioMenu> implements GrupoUsuarioMenuDaoLocal  {

    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.GrupoUsuarioMenuDaoLocal#listarGrupoUsuarioMenu(pe.com.edu.siaa.core.model.jpa.seguridad.GrupoUsuarioMenu)
     */  
    @Override	 
    public List<GrupoUsuarioMenu> listarGrupoUsuarioMenu(GrupoUsuarioMenuDTO grupoUsuarioMenu) {
        Query query = generarQueryListaGrupoUsuarioMenu(grupoUsuarioMenu, false);
        query.setFirstResult(grupoUsuarioMenu.getStartRow());
        query.setMaxResults(grupoUsuarioMenu.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista GrupoUsuarioMenu.
     *
     * @param GrupoUsuarioMenuDTO el grupoUsuarioMenu
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaGrupoUsuarioMenu(GrupoUsuarioMenuDTO grupoUsuarioMenu, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idGrupoUsuarioMenu) from GrupoUsuarioMenu o where 1=1 ");
        } else {
            jpaql.append(" select o from GrupoUsuarioMenu o left join fetch o.menu where 1=1 ");           
        }
        jpaql.append(" and o.grupoUsuario.idGrupoUsuario =:idGrupoUsuario ");
        parametros.put("idGrupoUsuario", ObjectUtil.objectToLong(grupoUsuarioMenu.getId()));
        if (!StringUtils.isNullOrEmpty(grupoUsuarioMenu.getEstado())) {
			jpaql.append(" and upper(o.estado) =:estado ");
			parametros.put("estado",grupoUsuarioMenu.getEstado().toUpperCase());
		}
		if (!StringUtils.isNullOrEmpty(grupoUsuarioMenu.getSearch())) {
	          jpaql.append(" and upper(o.idGrupoUsuarioMenu) like :search ");
	          parametros.put("search", "%" + grupoUsuarioMenu.getSearch().toUpperCase() + "%");
	    } else {
			
			if (!StringUtils.isNullOrEmpty(grupoUsuarioMenu.getFechaCreacion())) {
				jpaql.append(" and o.fechaCreacion = :fechaCreacion ");
				parametros.put("fechaCreacion", grupoUsuarioMenu.getFechaCreacion());
			}
			if (!StringUtils.isNullOrEmpty(grupoUsuarioMenu.getUsuarioCreacion())) {
				jpaql.append(" and upper(o.usuarioCreacion) like :usuarioCreacion ");
				parametros.put("usuarioCreacion", "%" + grupoUsuarioMenu.getUsuarioCreacion().toUpperCase() + "%");
			}
		}
        if (!esContador) {
            //jpaql.append(" ORDER BY 1 ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.GrupoUsuarioMenuDaoLocal#contarListar{entity.getClassName()}(pe.com.edu.siaa.core.model.jpa.seguridad.GrupoUsuarioMenuDTO)
     */
	@Override
    public int contarListarGrupoUsuarioMenu(GrupoUsuarioMenuDTO grupoUsuarioMenu) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaGrupoUsuarioMenu(grupoUsuarioMenu, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.GrupoUsuarioMenuDaoLocal#generarIdGrupoUsuarioMenu()
     */
	 @Override
    public String generarIdGrupoUsuarioMenu() {
        return UUIDUtil.generarElementUUID();
    }
	 
	@Override
	public GrupoUsuarioMenu findByGrupoUsuarioMenu(Long idMenu) throws Exception {
		GrupoUsuarioMenu resultado = null;
		StringBuilder jpaql = new StringBuilder();
		jpaql.append("from GrupoUsuarioMenu a left join fetch a.menu ");
		jpaql.append(" where  a.menu.idMenu=:idMenu");
		Query query = createQuery(jpaql.toString(),null); 
		query.setParameter("idMenu", idMenu);
		List<GrupoUsuarioMenu> listaAlumno = query.getResultList();
		if (listaAlumno != null && listaAlumno.size() > 0) {
			resultado = listaAlumno.get(0);
		}
		return resultado;	
	}
   
	 @Override	 
	 public List<GrupoUsuarioMenu> listarGrupoUsuarioMenu(Long idMenu) {
	    	Map<String, Object> parametros = new HashMap<String, Object>();
			boolean ejecutarQuery = false;
			StringBuilder jpaql = new StringBuilder();
			jpaql.append("from GrupoUsuarioMenu drn where 1 = 1 ");
			
			if (StringUtils.isNotNullOrBlank(idMenu)) {
				ejecutarQuery = true;
				jpaql.append(" and drn.menu.idMenu =:idMenu  ");
				parametros.put("idMenu", idMenu);
			}
			if (ejecutarQuery) {		
				Query query = createQuery(jpaql.toString(), parametros);
				return query.getResultList();
			} 
			return null;
	  } 
}