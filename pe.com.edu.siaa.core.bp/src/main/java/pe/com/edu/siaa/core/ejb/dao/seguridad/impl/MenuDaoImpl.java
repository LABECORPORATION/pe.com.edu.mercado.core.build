package pe.com.edu.siaa.core.ejb.dao.seguridad.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericDAOImpl;
import pe.com.edu.siaa.core.ejb.dao.seguridad.local.MenuDaoLocal;
import pe.com.edu.siaa.core.ejb.factory.CollectionUtil;
import pe.com.edu.siaa.core.ejb.util.txt.Utilidades;
import pe.com.edu.siaa.core.model.dto.seguridad.MenuDTO;
import pe.com.edu.siaa.core.model.estate.EstadoGeneralState;
import pe.com.edu.siaa.core.model.jpa.seguridad.Menu;
import pe.com.edu.siaa.core.model.util.ObjectUtil;
import pe.com.edu.siaa.core.model.util.StringUtils;

/**
 * La Class MenuDaoImpl.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 19:53:43 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
public class MenuDaoImpl extends  GenericDAOImpl<Long, Menu> implements MenuDaoLocal  {

	
	@Override
	public List<Menu> obtenerMenuUsuario(String idUsuario) throws Exception {
		List<Menu> resultado = new ArrayList<Menu>();
		Map<String, Object> parametros = new HashMap<String, Object>();
		boolean ejecutarQuery = false;
		StringBuilder jpaql = new StringBuilder();
		if (StringUtils.isNotNullOrBlank(idUsuario) ) {
			ejecutarQuery = true;
		}
		jpaql.append( Utilidades.obtenerWITHRecursivo() + " menuAccesos AS ");
		jpaql.append("( ");
		jpaql.append("select m.* from segur.GrupoUsuarioMenu gum , segur.Menu m where gum.idMenu  = m.idMenu and gum.idGrupoUsuario in (select idGrupoUsuario from segur.GrupoUsuarioUsuario where idUsuario =:idUsuario and estado=:estadoActivo) ");
		jpaql.append(" and gum.estado=:estadoActivo ");
		jpaql.append("union ");
		jpaql.append("select m.* from segur.MenuPersonalizado mp,segur.Menu m where mp.idMenu = m.idMenu and idUsuario =:idUsuario and  mp.estado=:estadoActivo and m.estado=:estadoActivo ");
		jpaql.append("union ALL ");
		jpaql.append("select  m.* from  segur.Menu m inner join menuAccesos ma "); 
		jpaql.append("on m.idMenu = ma.idPadreMenu  ");
		jpaql.append(") ");
		//jpaql.append("select distinct * from menuAccesos where idSistema=:idSistema order by  idPadreMenu , nombre ");
		jpaql.append("select distinct a.idMenu from menuAccesos a ");
		if (idUsuario != null) {
				parametros.put("idUsuario", idUsuario);				
		}
		/*if (idSistema != null) {
			parametros.put("idSistema", idSistema);
		}*/
		parametros.put("estadoActivo", EstadoGeneralState.ACTIVO.getKey() + "");
		if (ejecutarQuery) {
			Query query = createNativeQuery(jpaql.toString(), parametros);
			List<BigDecimal> resultadoTemp = query.getResultList();
			if (!CollectionUtil.isEmpty(resultadoTemp)) {
				List<Long> listaIdMenu = new ArrayList<Long>();
				for (BigDecimal idMenu : resultadoTemp) {
					listaIdMenu.add(idMenu.longValue());
				}
				parametros = new HashMap<String, Object>();
				parametros.put("listaIdMenu", listaIdMenu);
				jpaql = new StringBuilder("select o from Menu o left join fetch o.menuPadre left join fetch o.sistema  where o.idMenu in (:listaIdMenu) order by o.sistema.orden, o.menuPadre.idMenu");
				query = createQuery(jpaql.toString(),parametros);
				resultado = query.getResultList();
			}
		}
		
		return resultado;
	}
	
    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.MenuDaoLocal#listarMenu(pe.com.edu.siaa.core.model.jpa.seguridad.Menu)
     */  
    @Override	 
    public List<Menu> listarMenu(MenuDTO menu) {
        Query query = generarQueryListaMenu(menu, false);
        query.setFirstResult(menu.getStartRow());
        query.setMaxResults(menu.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista Menu.
     *
     * @param MenuDTO el menu
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaMenu(MenuDTO menu, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        boolean ejecutoBusqueda = false;
        if (esContador) {
            jpaql.append(" select count(o.idMenu) from Menu o where 1=1 ");
        } else {
            jpaql.append(" select o from Menu o left join fetch o.menuPadre where 1=1 ");           
        }
        
        jpaql.append(" and o.sistema.idSistema =:idSistema ");
        parametros.put("idSistema", menu.getSistema().getIdSistema());

        if (!StringUtils.isNullOrEmptyNumeric(menu.getId())) {//comodin
        	jpaql.append(" and o.menuPadre.idMenu =:idMenuDependencia ");
			parametros.put("idMenuDependencia", ObjectUtil.objectToLong(menu.getId()));
			ejecutoBusqueda = true;
        }
        if (!StringUtils.isNullOrEmpty(menu.getSearch())) {
	          jpaql.append(" and upper(o.nombre) like :search ");
	          parametros.put("search", "%" + menu.getSearch().toUpperCase() + "%");
	    } else {
			if (!StringUtils.isNullOrEmpty(menu.getNombre())) {
				jpaql.append(" and upper(o.nombre) like :nombre ");
				parametros.put("nombre", "%" + menu.getNombre().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(menu.getDescripcion())) {
				jpaql.append(" and upper(o.descripcion) like :descripcion ");
				parametros.put("descripcion", "%" + menu.getDescripcion().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(menu.getUrl())) {
				jpaql.append(" and upper(o.url) like :url ");
				parametros.put("url", "%" + menu.getUrl().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(menu.getParametro())) {
				jpaql.append(" and upper(o.parametro) like :parametro ");
				parametros.put("parametro", "%" + menu.getParametro().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(menu.getIcono())) {
				jpaql.append(" and upper(o.icono) like :icono ");
				parametros.put("icono", "%" + menu.getIcono().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(menu.getTarget())) {
				jpaql.append(" and upper(o.target) like :target ");
				parametros.put("target", "%" + menu.getTarget().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(menu.getFechaCreacion())) {
				jpaql.append(" and o.fechaCreacion = :fechaCreacion ");
				parametros.put("fechaCreacion", menu.getFechaCreacion());
			}
			if (!StringUtils.isNullOrEmpty(menu.getUsuarioCreacion())) {
				jpaql.append(" and upper(o.usuarioCreacion) like :usuarioCreacion ");
				parametros.put("usuarioCreacion", "%" + menu.getUsuarioCreacion().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(menu.getFechaModificacion())) {
				jpaql.append(" and o.fechaModificacion = :fechaModificacion ");
				parametros.put("fechaModificacion", menu.getFechaModificacion());
			}
			if (!StringUtils.isNullOrEmpty(menu.getUsuarioModificacion())) {
				jpaql.append(" and upper(o.usuarioModificacion) like :usuarioModificacion ");
				parametros.put("usuarioModificacion", "%" + menu.getUsuarioModificacion().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(menu.getEstado())) {
				jpaql.append(" and upper(o.estado) like :estado ");
				parametros.put("estado", "%" + menu.getEstado().toUpperCase() + "%");
			}
		}
		if (!ejecutoBusqueda) {
			jpaql.append(" and o.menuPadre.idMenu is null ");
		}
        if (!esContador) {
            //jpaql.append(" ORDER BY 1 ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.MenuDaoLocal#contarListar{entity.getClassName()}(pe.com.edu.siaa.core.model.jpa.seguridad.MenuDTO)
     */
	@Override
    public int contarListarMenu(MenuDTO menu) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaMenu(menu, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.MenuDaoLocal#generarIdMenu()
     */
	 @Override
    public Long generarIdMenu() {
        Long resultado = 1L;
        Query query = createQuery("select max(o.idMenu) from Menu o", null);
        List<Object> listLong =  query.getResultList();
        if (listLong != null && listLong.size() > 0 && listLong.get(0) != null)  {
            Long ultimoIdGenerado = Long.valueOf(listLong.get(0).toString());
            if (!StringUtils.isNullOrEmpty(ultimoIdGenerado)) {
                resultado = resultado + ultimoIdGenerado;
            }
        }
        return resultado;
    }
   
	 
	@Override
	public Menu findByMenu(Long idMenu) throws Exception {
		Menu resultado = null;
		StringBuilder jpaql = new StringBuilder();
		jpaql.append("from Menu a left join fetch a.sistema ");
		jpaql.append(" where  a.idMenu=:idMenu");
		Query query = createQuery(jpaql.toString(),null); 
		query.setParameter("idMenu", idMenu);
		List<Menu> listaAlumno = query.getResultList();
		if (listaAlumno != null && listaAlumno.size() > 0) {
			resultado = listaAlumno.get(0);
		}
		return resultado;	
	}
}