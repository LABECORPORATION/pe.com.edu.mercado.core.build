package pe.com.edu.siaa.core.ejb.dao.seguridad.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericDAOImpl;
import pe.com.edu.siaa.core.ejb.dao.seguridad.local.PrivilegioGrupoUsuarioDaoLocal;
import pe.com.edu.siaa.core.ejb.util.jms.UUIDUtil;
import pe.com.edu.siaa.core.model.dto.seguridad.PrivilegioGrupoUsuarioDTO;
import pe.com.edu.siaa.core.model.jpa.seguridad.PrivilegioGrupoUsuario;
import pe.com.edu.siaa.core.model.util.StringUtils;

/**
 * La Class PrivilegioGrupoUsuarioDaoImpl.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 19:53:45 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
public class PrivilegioGrupoUsuarioDaoImpl extends  GenericDAOImpl<String, PrivilegioGrupoUsuario> implements PrivilegioGrupoUsuarioDaoLocal  {

    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.PrivilegioGrupoUsuarioDaoLocal#listarPrivilegioGrupoUsuario(pe.com.edu.siaa.core.model.jpa.seguridad.PrivilegioGrupoUsuario)
     */  
    @Override	 
    public List<PrivilegioGrupoUsuario> listarPrivilegioGrupoUsuario(PrivilegioGrupoUsuarioDTO privilegioGrupoUsuario) {
        Query query = generarQueryListaPrivilegioGrupoUsuario(privilegioGrupoUsuario, false);
        query.setFirstResult(privilegioGrupoUsuario.getStartRow());
        query.setMaxResults(privilegioGrupoUsuario.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista PrivilegioGrupoUsuario.
     *
     * @param PrivilegioGrupoUsuarioDTO el privilegioGrupoUsuario
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaPrivilegioGrupoUsuario(PrivilegioGrupoUsuarioDTO privilegioGrupoUsuario, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idPrivilegioGrupoUsuario) from PrivilegioGrupoUsuario o where 1=1 ");
        } else {
            jpaql.append(" select o from PrivilegioGrupoUsuario o left join fetch o.privilegio  where 1=1 ");           
        }
        jpaql.append(" and o.grupoUsuario.idGrupoUsuario =:idGrupoUsuario ");
        
        if (!StringUtils.isNullOrEmpty(privilegioGrupoUsuario.getEstado())) {
			jpaql.append(" and upper(o.estado) = :estado ");
			parametros.put("estado",  privilegioGrupoUsuario.getEstado().toUpperCase());
		}
        parametros.put("idGrupoUsuario", privilegioGrupoUsuario.getId());
		if (!StringUtils.isNullOrEmpty(privilegioGrupoUsuario.getSearch())) {
	          jpaql.append(" and upper(o.idPrivilegioGrupoUsuario) like :search ");
	          parametros.put("search", "%" + privilegioGrupoUsuario.getSearch().toUpperCase() + "%");
	    }
        if (!esContador) {
            //jpaql.append(" ORDER BY 1 ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.PrivilegioGrupoUsuarioDaoLocal#contarListar{entity.getClassName()}(pe.com.edu.siaa.core.model.jpa.seguridad.PrivilegioGrupoUsuarioDTO)
     */
	@Override
    public int contarListarPrivilegioGrupoUsuario(PrivilegioGrupoUsuarioDTO privilegioGrupoUsuario) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaPrivilegioGrupoUsuario(privilegioGrupoUsuario, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.PrivilegioGrupoUsuarioDaoLocal#generarIdPrivilegioGrupoUsuario()
     */
	 @Override
    public String generarIdPrivilegioGrupoUsuario() {
        return UUIDUtil.generarElementUUID();
    }
   
}