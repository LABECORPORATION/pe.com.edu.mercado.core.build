package pe.com.edu.siaa.core.ejb.dao.seguridad.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericDAOImpl;
import pe.com.edu.siaa.core.ejb.dao.seguridad.local.EntidadDaoLocal;
import pe.com.edu.siaa.core.model.dto.seguridad.EntidadDTO;
import pe.com.edu.siaa.core.model.jpa.seguridad.Entidad;
import pe.com.edu.siaa.core.model.util.StringUtils;

/**
 * La Class EntidadDaoImpl.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu Dec 14 00:27:42 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
public class EntidadDaoImpl extends  GenericDAOImpl<String, Entidad> implements EntidadDaoLocal  {
	
    /* (non-Javadoc)
     * @see pe.com.builderp.core.ejb.dao.seguridad.local.EntidadDaoLocal#listarEntidad(pe.com.builderp.core.model.jpa.seguridad.Entidad)
     */  
    @Override	 
    public List<Entidad> listarEntidad(EntidadDTO entidad) {
        Query query = generarQueryListaEntidad(entidad, false);
        query.setFirstResult(entidad.getStartRow());
        query.setMaxResults(entidad.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista Entidad.
     *
     * @param EntidadDTO el entidad
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaEntidad(EntidadDTO entidad, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idEntidad) from Entidad o where 1=1 ");
        } else {
            jpaql.append(" select o from Entidad o left join fetch o.itemByTipoVia  left join fetch o.itemByZona where 1=1 ");           
        }
		if (!StringUtils.isNullOrEmpty(entidad.getSearch())) {
	          jpaql.append(" and upper(o.nombre) like :search ");
	          parametros.put("search", "%" + entidad.getSearch().toUpperCase() + "%");
	    } else {
			if (!StringUtils.isNullOrEmpty(entidad.getNombreZona())) {
				jpaql.append(" and upper(o.nombreZona) like :nombreZona ");
				parametros.put("nombreZona", "%" + entidad.getNombreZona().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(entidad.getNombreTipoVia())) {
				jpaql.append(" and upper(o.nombreTipoVia) like :nombreTipoVia ");
				parametros.put("nombreTipoVia", "%" + entidad.getNombreTipoVia().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(entidad.getCodigo())) {
				jpaql.append(" and upper(o.codigo) like :codigo ");
				parametros.put("codigo", "%" + entidad.getCodigo().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(entidad.getNombre())) {
				jpaql.append(" and upper(o.nombre) like :nombre ");
				parametros.put("nombre", "%" + entidad.getNombre().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(entidad.getDireccion())) {
				jpaql.append(" and upper(o.direccion) like :direccion ");
				parametros.put("direccion", "%" + entidad.getDireccion().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(entidad.getTelefono())) {
				jpaql.append(" and upper(o.telefono) like :telefono ");
				parametros.put("telefono", "%" + entidad.getTelefono().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(entidad.getEmail())) {
				jpaql.append(" and upper(o.email) like :email ");
				parametros.put("email", "%" + entidad.getEmail().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(entidad.getWeb())) {
				jpaql.append(" and upper(o.web) like :web ");
				parametros.put("web", "%" + entidad.getWeb().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(entidad.getFechaCreacion())) {
				jpaql.append(" and o.fechaCreacion = :fechaCreacion ");
				parametros.put("fechaCreacion", entidad.getFechaCreacion());
			}
			if (!StringUtils.isNullOrEmpty(entidad.getUsuarioCreacion())) {
				jpaql.append(" and upper(o.usuarioCreacion) like :usuarioCreacion ");
				parametros.put("usuarioCreacion", "%" + entidad.getUsuarioCreacion().toUpperCase() + "%");
			}
		}
        if (!esContador) {
            //jpaql.append(" ORDER BY 1 ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.builderp.core.ejb.dao.seguridad.local.EntidadDaoLocal#contarListar{entity.getClassName()}(pe.com.builderp.core.model.jpa.seguridad.EntidadDTO)
     */
	@Override
    public int contarListarEntidad(EntidadDTO entidad) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaEntidad(entidad, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.builderp.core.ejb.dao.seguridad.local.EntidadDaoLocal#generarIdEntidad()
     */
	 @Override
    public String generarIdEntidad() {
        String resultado = "1";
        Query query = createQuery("select max(o.idEntidad) from Entidad o", null);
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