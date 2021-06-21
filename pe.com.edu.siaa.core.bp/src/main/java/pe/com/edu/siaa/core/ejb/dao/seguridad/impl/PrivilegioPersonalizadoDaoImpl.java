package pe.com.edu.siaa.core.ejb.dao.seguridad.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericDAOImpl;
import pe.com.edu.siaa.core.ejb.dao.seguridad.local.PrivilegioPersonalizadoDaoLocal;
import pe.com.edu.siaa.core.ejb.util.jms.UUIDUtil;
import pe.com.edu.siaa.core.model.dto.seguridad.PrivilegioPersonalizadoDTO;
import pe.com.edu.siaa.core.model.jpa.seguridad.PrivilegioPersonalizado;
import pe.com.edu.siaa.core.model.util.StringUtils;

/**
 * La Class PrivilegioPersonalizadoDaoImpl.
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
public class PrivilegioPersonalizadoDaoImpl extends  GenericDAOImpl<String, PrivilegioPersonalizado> implements PrivilegioPersonalizadoDaoLocal  {

    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.PrivilegioPersonalizadoDaoLocal#listarPrivilegioPersonalizado(pe.com.edu.siaa.core.model.jpa.seguridad.PrivilegioPersonalizado)
     */  
    @Override	 
    public List<PrivilegioPersonalizado> listarPrivilegioPersonalizado(PrivilegioPersonalizadoDTO privilegioPersonalizado) {
        Query query = generarQueryListaPrivilegioPersonalizado(privilegioPersonalizado, false);
        query.setFirstResult(privilegioPersonalizado.getStartRow());
        query.setMaxResults(privilegioPersonalizado.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista PrivilegioPersonalizado.
     *
     * @param PrivilegioPersonalizadoDTO el privilegioPersonalizado
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaPrivilegioPersonalizado(PrivilegioPersonalizadoDTO privilegioPersonalizado, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idPrivilegioPersonalizado) from PrivilegioPersonalizado o where 1=1 ");
        } else {
            jpaql.append(" select o from PrivilegioPersonalizado o left join fetch o.privilegio where 1=1 ");           
        }
        jpaql.append(" and o.usuario.idUsuario =:idUsuario ");
        parametros.put("idUsuario", privilegioPersonalizado.getId() + "");
        
        if (!StringUtils.isNullOrEmpty(privilegioPersonalizado.getEstado())) {
			jpaql.append(" and upper(o.estado) =:estado ");
			parametros.put("estado", privilegioPersonalizado.getEstado().toUpperCase() );
		}
        
		if (!StringUtils.isNullOrEmpty(privilegioPersonalizado.getSearch())) {
	          jpaql.append(" and upper(o.idPrivilegioPersonalizado) like :search ");
	          parametros.put("search", "%" + privilegioPersonalizado.getSearch().toUpperCase() + "%");
	    } 
        if (!esContador) {
            //jpaql.append(" ORDER BY 1 ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.PrivilegioPersonalizadoDaoLocal#contarListar{entity.getClassName()}(pe.com.edu.siaa.core.model.jpa.seguridad.PrivilegioPersonalizadoDTO)
     */
	@Override
    public int contarListarPrivilegioPersonalizado(PrivilegioPersonalizadoDTO privilegioPersonalizado) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaPrivilegioPersonalizado(privilegioPersonalizado, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.PrivilegioPersonalizadoDaoLocal#generarIdPrivilegioPersonalizado()
     */
	 @Override
    public String generarIdPrivilegioPersonalizado() {
        return UUIDUtil.generarElementUUID();
    }
   
}