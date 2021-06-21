package pe.com.edu.siaa.core.ejb.dao.seguridad.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericDAOImpl;
import pe.com.edu.siaa.core.ejb.dao.seguridad.local.ConfiguracionMenuDaoLocal;
import pe.com.edu.siaa.core.ejb.util.jms.UUIDUtil;
import pe.com.edu.siaa.core.model.dto.seguridad.ConfiguracionMenuDTO;
import pe.com.edu.siaa.core.model.jpa.seguridad.ConfiguracionMenu;
import pe.com.edu.siaa.core.model.util.StringUtils;

/**
 * La Class ConfiguracionMenuDaoImpl.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 19:53:44 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
public class ConfiguracionMenuDaoImpl extends  GenericDAOImpl<String, ConfiguracionMenu> implements ConfiguracionMenuDaoLocal  {

	
	@Override
	public List<ConfiguracionMenu> obtenerConfiguracionMenu(Long idMenu) throws Exception {
		Map<String, Object> parametros = new HashMap<String, Object>();
		StringBuilder jpaql = new StringBuilder();
		jpaql.append(" select o from ConfiguracionMenu o left join fetch o.itemByComponente it left join fetch o.properties pro where 1 = 1 ");
		if (idMenu != null) {
			if (StringUtils.isNotNullOrBlank(idMenu)) {
				jpaql.append(" and o.menu.idMenu = :idMenu ");
				parametros.put("idMenu",idMenu);
			}
		}
		jpaql.append(" order by it.codigo, pro.descripcion ");
		Query query = createQuery(jpaql.toString(), parametros);
		return query.getResultList();
		
	}
	
    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.ConfiguracionMenuDaoLocal#listarConfiguracionMenu(pe.com.edu.siaa.core.model.jpa.seguridad.ConfiguracionMenu)
     */  
    @Override	 
    public List<ConfiguracionMenu> listarConfiguracionMenu(ConfiguracionMenuDTO configuracionMenu) {
        Query query = generarQueryListaConfiguracionMenu(configuracionMenu, false);
        query.setFirstResult(configuracionMenu.getStartRow());
        query.setMaxResults(configuracionMenu.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista ConfiguracionMenu.
     *
     * @param ConfiguracionMenuDTO el configuracionMenu
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaConfiguracionMenu(ConfiguracionMenuDTO configuracionMenu, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idConfiguracionMenu) from ConfiguracionMenu o where 1=1 ");
        } else {
            jpaql.append(" select o from ConfiguracionMenu o where 1=1 ");           
        }
		if (!StringUtils.isNullOrEmpty(configuracionMenu.getSearch())) {
	          jpaql.append(" and upper(o.idConfiguracionMenu) like :search ");
	          parametros.put("search", "%" + configuracionMenu.getSearch().toUpperCase() + "%");
	    } else {
			if (!StringUtils.isNullOrEmptyNumeric(configuracionMenu.getRequired())) {
				jpaql.append(" and o.required = :required ");
				parametros.put("required", configuracionMenu.getRequired());
			}
			if (!StringUtils.isNullOrEmptyNumeric(configuracionMenu.getReadonly())) {
				jpaql.append(" and o.readonly = :readonly ");
				parametros.put("readonly", configuracionMenu.getReadonly());
			}
			if (!StringUtils.isNullOrEmptyNumeric(configuracionMenu.getRendered())) {
				jpaql.append(" and o.rendered = :rendered ");
				parametros.put("rendered", configuracionMenu.getRendered());
			}
			if (!StringUtils.isNullOrEmptyNumeric(configuracionMenu.getDisabled())) {
				jpaql.append(" and o.disabled = :disabled ");
				parametros.put("disabled", configuracionMenu.getDisabled());
			}
			if (!StringUtils.isNullOrEmpty(configuracionMenu.getEstado())) {
				jpaql.append(" and upper(o.estado) like :estado ");
				parametros.put("estado", "%" + configuracionMenu.getEstado().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(configuracionMenu.getUsuarioModificacion())) {
				jpaql.append(" and upper(o.usuarioModificacion) like :usuarioModificacion ");
				parametros.put("usuarioModificacion", "%" + configuracionMenu.getUsuarioModificacion().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(configuracionMenu.getFechaModificacion())) {
				jpaql.append(" and o.fechaModificacion = :fechaModificacion ");
				parametros.put("fechaModificacion", configuracionMenu.getFechaModificacion());
			}
		}
        if (!esContador) {
            //jpaql.append(" ORDER BY 1 ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.ConfiguracionMenuDaoLocal#contarListar{entity.getClassName()}(pe.com.edu.siaa.core.model.jpa.seguridad.ConfiguracionMenuDTO)
     */
	@Override
    public int contarListarConfiguracionMenu(ConfiguracionMenuDTO configuracionMenu) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaConfiguracionMenu(configuracionMenu, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.ConfiguracionMenuDaoLocal#generarIdConfiguracionMenu()
     */
	 @Override
    public String generarIdConfiguracionMenu() {
        return UUIDUtil.generarElementUUID();
    }
   
}