package pe.com.edu.siaa.core.ejb.dao.seguridad.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericDAOImpl;
import pe.com.edu.siaa.core.ejb.dao.seguridad.local.TipoUsuarioDaoLocal;
import pe.com.edu.siaa.core.model.dto.seguridad.TipoUsuarioDTO;
import pe.com.edu.siaa.core.model.jpa.seguridad.TipoUsuario;
import pe.com.edu.siaa.core.model.util.StringUtils;

/**
 * La Class TipoUsuarioDaoImpl.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu Dec 14 00:27:45 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
public class TipoUsuarioDaoImpl extends  GenericDAOImpl<Long, TipoUsuario> implements TipoUsuarioDaoLocal  {

    /* (non-Javadoc)
     * @see pe.com.builderp.core.ejb.dao.seguridad.local.TipoUsuarioDaoLocal#listarTipoUsuario(pe.com.builderp.core.model.jpa.seguridad.TipoUsuario)
     */  
    @Override	 
    public List<TipoUsuario> listarTipoUsuario(TipoUsuarioDTO tipoUsuario) {
        Query query = generarQueryListaTipoUsuario(tipoUsuario, false);
        query.setFirstResult(tipoUsuario.getStartRow());
        query.setMaxResults(tipoUsuario.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista TipoUsuario.
     *
     * @param TipoUsuarioDTO el tipoUsuario
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaTipoUsuario(TipoUsuarioDTO tipoUsuario, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idTipoUsuario) from TipoUsuario o where 1=1 ");
        } else {
            jpaql.append(" select o from TipoUsuario o where 1=1 ");           
        }
		if (!StringUtils.isNullOrEmpty(tipoUsuario.getSearch())) {
	          jpaql.append(" and upper(o.idTipoUsuario) like :search ");
	          parametros.put("search", "%" + tipoUsuario.getSearch().toUpperCase() + "%");
	    } else {
			if (!StringUtils.isNullOrEmpty(tipoUsuario.getDescripcion())) {
				jpaql.append(" and upper(o.descripcion) like :descripcion ");
				parametros.put("descripcion", "%" + tipoUsuario.getDescripcion().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(tipoUsuario.getCodigo())) {
				jpaql.append(" and upper(o.codigo) like :codigo ");
				parametros.put("codigo", "%" + tipoUsuario.getCodigo().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(tipoUsuario.getCodigoExterno())) {
				jpaql.append(" and upper(o.codigoExterno) like :codigoExterno ");
				parametros.put("codigoExterno", "%" + tipoUsuario.getCodigoExterno().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(tipoUsuario.getEstado())) {
				jpaql.append(" and upper(o.estado) like :estado ");
				parametros.put("estado", "%" + tipoUsuario.getEstado().toUpperCase() + "%");
			}
		}
        if (!esContador) {
            //jpaql.append(" ORDER BY 1 ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.builderp.core.ejb.dao.seguridad.local.TipoUsuarioDaoLocal#contarListar{entity.getClassName()}(pe.com.builderp.core.model.jpa.seguridad.TipoUsuarioDTO)
     */
	@Override
    public int contarListarTipoUsuario(TipoUsuarioDTO tipoUsuario) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaTipoUsuario(tipoUsuario, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.builderp.core.ejb.dao.seguridad.local.TipoUsuarioDaoLocal#generarIdTipoUsuario()
     */
	 @Override
    public Long generarIdTipoUsuario() {
        Long resultado = 1L;
        Query query = createQuery("select max(o.idTipoUsuario) from TipoUsuario o", null);
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