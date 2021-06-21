package pe.com.edu.siaa.core.ejb.dao.seguridad.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericDAOImpl;
import pe.com.edu.siaa.core.ejb.dao.seguridad.local.MenuPersonalizadoDaoLocal;
import pe.com.edu.siaa.core.ejb.util.jms.UUIDUtil;
import pe.com.edu.siaa.core.model.dto.seguridad.MenuPersonalizadoDTO;
import pe.com.edu.siaa.core.model.jpa.seguridad.MenuPersonalizado;
import pe.com.edu.siaa.core.model.util.StringUtils;

/**
 * La Class MenuPersonalizadoDaoImpl.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 19:53:37 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
public class MenuPersonalizadoDaoImpl extends  GenericDAOImpl<String, MenuPersonalizado> implements MenuPersonalizadoDaoLocal  {

    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.MenuPersonalizadoDaoLocal#listarMenuPersonalizado(pe.com.edu.siaa.core.model.jpa.seguridad.MenuPersonalizado)
     */  
    @Override	 
    public List<MenuPersonalizado> listarMenuPersonalizado(MenuPersonalizadoDTO menuPersonalizado) {
        Query query = generarQueryListaMenuPersonalizado(menuPersonalizado, false);
        query.setFirstResult(menuPersonalizado.getStartRow());
        query.setMaxResults(menuPersonalizado.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista MenuPersonalizado.
     *
     * @param MenuPersonalizadoDTO el menuPersonalizado
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaMenuPersonalizado(MenuPersonalizadoDTO menuPersonalizado, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idMenuPersonalizado) from MenuPersonalizado o where 1=1 ");
        } else {
            jpaql.append(" select o from MenuPersonalizado o left join fetch o.menu  where 1=1 ");           
        }
        jpaql.append(" and o.persona.idUsuario =:idUsuario ");
        parametros.put("idUsuario", menuPersonalizado.getId() + "");
        if (!StringUtils.isNullOrEmpty(menuPersonalizado.getEstado())) {
			jpaql.append(" and upper(o.estado) = :estado ");
			parametros.put("estado", menuPersonalizado.getEstado().toUpperCase());
		}
		if (!StringUtils.isNullOrEmpty(menuPersonalizado.getSearch())) {
	          jpaql.append(" and upper(o.idMenuPersonalizado) like :search ");
	          parametros.put("search", "%" + menuPersonalizado.getSearch().toUpperCase() + "%");
	    } else {
			
			if (!StringUtils.isNullOrEmpty(menuPersonalizado.getFechaCreacion())) {
				jpaql.append(" and o.fechaCreacion = :fechaCreacion ");
				parametros.put("fechaCreacion", menuPersonalizado.getFechaCreacion());
			}
			if (!StringUtils.isNullOrEmpty(menuPersonalizado.getUsuarioCreacion())) {
				jpaql.append(" and upper(o.usuarioCreacion) like :usuarioCreacion ");
				parametros.put("usuarioCreacion", "%" + menuPersonalizado.getUsuarioCreacion().toUpperCase() + "%");
			}
		}
        if (!esContador) {
            //jpaql.append(" ORDER BY 1 ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.MenuPersonalizadoDaoLocal#contarListar{entity.getClassName()}(pe.com.edu.siaa.core.model.jpa.seguridad.MenuPersonalizadoDTO)
     */
	@Override
    public int contarListarMenuPersonalizado(MenuPersonalizadoDTO menuPersonalizado) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaMenuPersonalizado(menuPersonalizado, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.MenuPersonalizadoDaoLocal#generarIdMenuPersonalizado()
     */
	 @Override
    public String generarIdMenuPersonalizado() {
        return UUIDUtil.generarElementUUID();
    }
   
}