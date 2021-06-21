package pe.com.edu.siaa.core.ejb.dao.seguridad.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericDAOImpl;
import pe.com.edu.siaa.core.ejb.dao.seguridad.local.PrivilegioDaoLocal;
import pe.com.edu.siaa.core.model.dto.seguridad.PrivilegioDTO;
import pe.com.edu.siaa.core.model.estate.EstadoGeneralState;
import pe.com.edu.siaa.core.model.jpa.seguridad.Privilegio;
import pe.com.edu.siaa.core.model.util.StringUtils;

/**
 * La Class PrivilegioDaoImpl.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 19:53:39 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
public class PrivilegioDaoImpl extends  GenericDAOImpl<Long, Privilegio> implements PrivilegioDaoLocal  {

	@Override
	public List<Privilegio> obtenerPrivilegioByUsuario(String idUsuario) throws Exception {
		List<Privilegio> resultado = new ArrayList<Privilegio>();
		Map<String, Object> parametros = new HashMap<String, Object>();
		boolean ejecutarQuery = false;
		StringBuilder jpaql = new StringBuilder();
		if (idUsuario != null) {
			ejecutarQuery = true;
		}
		jpaql.append("select p.* from segur.PrivilegioGrupoUsuario gup , segur.Privilegio p where gup.estado=:estadoActivo and gup.idPrivilegio  = p.idPrivilegio and gup.idGrupoUsuario in (select idGrupoUsuario from segur.GrupoUsuarioUsuario gu where gu.estado=:estadoActivo and gu.idUsuario =:idUsuario) ");
		jpaql.append("union ");
		jpaql.append("select p.* from segur.PrivilegioPersonalizado pp,segur.Privilegio p where pp.estado=:estadoActivo and pp.idPrivilegio = p.idPrivilegio and pp.idUsuario =:idUsuario ");
		jpaql.append(" order by nombre ");
		parametros.put("estadoActivo", EstadoGeneralState.ACTIVO.getKey());
		if (idUsuario != null) {
			parametros.put("idUsuario", idUsuario);				
		}
		if (ejecutarQuery) {
			Query query = createNativeQuery(jpaql.toString(),Privilegio.class, parametros);
			resultado = (List<Privilegio>)query.getResultList();
		}
		
		return resultado;
	}
	
	
    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.PrivilegioDaoLocal#listarPrivilegio(pe.com.edu.siaa.core.model.jpa.seguridad.Privilegio)
     */  
    @Override	 
    public List<Privilegio> listarPrivilegio(PrivilegioDTO privilegio) {
        Query query = generarQueryListaPrivilegio(privilegio, false);
        query.setFirstResult(privilegio.getStartRow());
        query.setMaxResults(privilegio.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista Privilegio.
     *
     * @param PrivilegioDTO el privilegio
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaPrivilegio(PrivilegioDTO privilegio, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idPrivilegio) from Privilegio o where 1=1 ");
        } else {
            jpaql.append(" select o from Privilegio o where 1=1 ");           
        }
		if (!StringUtils.isNullOrEmpty(privilegio.getSearch())) {
	          jpaql.append(" and upper(o.idPrivilegio) like :search ");
	          parametros.put("search", "%" + privilegio.getSearch().toUpperCase() + "%");
	    } else {
			if (!StringUtils.isNullOrEmpty(privilegio.getAcronimo())) {
				jpaql.append(" and upper(o.acronimo) like :acronimo ");
				parametros.put("acronimo", "%" + privilegio.getAcronimo().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(privilegio.getNombre())) {
				jpaql.append(" and upper(o.nombre) like :nombre ");
				parametros.put("nombre", "%" + privilegio.getNombre().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(privilegio.getDescripcion())) {
				jpaql.append(" and upper(o.descripcion) like :descripcion ");
				parametros.put("descripcion", "%" + privilegio.getDescripcion().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(privilegio.getAccion())) {
				jpaql.append(" and upper(o.accion) like :accion ");
				parametros.put("accion", "%" + privilegio.getAccion().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(privilegio.getEstado())) {
				jpaql.append(" and upper(o.estado) like :estado ");
				parametros.put("estado", "%" + privilegio.getEstado().toUpperCase() + "%");
			}
		}
        if (!esContador) {
            //jpaql.append(" ORDER BY 1 ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.PrivilegioDaoLocal#contarListar{entity.getClassName()}(pe.com.edu.siaa.core.model.jpa.seguridad.PrivilegioDTO)
     */
	@Override
    public int contarListarPrivilegio(PrivilegioDTO privilegio) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaPrivilegio(privilegio, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.PrivilegioDaoLocal#generarIdPrivilegio()
     */
	 @Override
    public Long generarIdPrivilegio() {
        Long resultado = 1L;
        Query query = createQuery("select max(o.idPrivilegio) from Privilegio o", null);
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