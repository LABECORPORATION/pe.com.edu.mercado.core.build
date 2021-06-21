package pe.com.edu.siaa.core.ejb.dao.seguridad.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericDAOImpl;
import pe.com.edu.siaa.core.ejb.dao.seguridad.local.PrivilegioMenuDaoLocal;
import pe.com.edu.siaa.core.ejb.util.jms.UUIDUtil;
import pe.com.edu.siaa.core.model.dto.seguridad.PrivilegioMenuDTO;
import pe.com.edu.siaa.core.model.jpa.seguridad.PrivilegioMenu;
import pe.com.edu.siaa.core.model.util.StringUtils;

/**
 * La Class PrivilegioMenuDaoImpl.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 19:53:35 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
public class PrivilegioMenuDaoImpl extends  GenericDAOImpl<String, PrivilegioMenu> implements PrivilegioMenuDaoLocal  {

    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.PrivilegioMenuDaoLocal#listarPrivilegioMenu(pe.com.edu.siaa.core.model.jpa.seguridad.PrivilegioMenu)
     */  
    @Override	 
    public List<PrivilegioMenu> listarPrivilegioMenu(PrivilegioMenuDTO privilegioMenu) {
        Query query = generarQueryListaPrivilegioMenu(privilegioMenu, false);
        query.setFirstResult(privilegioMenu.getStartRow());
        query.setMaxResults(privilegioMenu.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista PrivilegioMenu.
     *
     * @param PrivilegioMenuDTO el privilegioMenu
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaPrivilegioMenu(PrivilegioMenuDTO privilegioMenu, boolean esContador) {
    	if (privilegioMenu.getListaIdMenu().size() == 0) {
    		privilegioMenu.getListaIdMenu().add(-1L);
    	}
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idPrivilegioMenu) from PrivilegioMenu o where 1=1 ");
        } else {
            jpaql.append(" select o from PrivilegioMenu o  left join fetch o.menu left join fetch o.privilegio where 1=1 ");           
        }
        jpaql.append(" and o.menu.idMenu in (:listaIdMenu) ");
        parametros.put("listaIdMenu", privilegioMenu.getListaIdMenu());
		if (!StringUtils.isNullOrEmpty(privilegioMenu.getSearch())) {
	          jpaql.append(" and upper(o.privilegio.nombre) like :search ");
	          parametros.put("search", "%" + privilegioMenu.getSearch().toUpperCase() + "%");
	    } else {
			if (!StringUtils.isNullOrEmpty(privilegioMenu.getEstado())) {
				jpaql.append(" and upper(o.estado) like :estado ");
				parametros.put("estado", "%" + privilegioMenu.getEstado().toUpperCase() + "%");
			}
		}
        if (!esContador) {
            //jpaql.append(" ORDER BY 1 ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.PrivilegioMenuDaoLocal#contarListar{entity.getClassName()}(pe.com.edu.siaa.core.model.jpa.seguridad.PrivilegioMenuDTO)
     */
	@Override
    public int contarListarPrivilegioMenu(PrivilegioMenuDTO privilegioMenu) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaPrivilegioMenu(privilegioMenu, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.PrivilegioMenuDaoLocal#generarIdPrivilegioMenu()
     */
	 @Override
    public String generarIdPrivilegioMenu() {
        return UUIDUtil.generarElementUUID();
    }
   
}