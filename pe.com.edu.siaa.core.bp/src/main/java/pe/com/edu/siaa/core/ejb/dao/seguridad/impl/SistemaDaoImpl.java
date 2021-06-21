package pe.com.edu.siaa.core.ejb.dao.seguridad.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericDAOImpl;
import pe.com.edu.siaa.core.ejb.dao.seguridad.local.SistemaDaoLocal;
import pe.com.edu.siaa.core.model.dto.seguridad.SistemaDTO;
import pe.com.edu.siaa.core.model.jpa.seguridad.Sistema;
import pe.com.edu.siaa.core.model.util.StringUtils;

/**
 * La Class SistemaDaoImpl.
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
public class SistemaDaoImpl extends  GenericDAOImpl<Long, Sistema> implements SistemaDaoLocal  {

    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.SistemaDaoLocal#listarSistema(pe.com.edu.siaa.core.model.jpa.seguridad.Sistema)
     */  
    @Override	 
    public List<Sistema> listarSistema(SistemaDTO sistema) {
        Query query = generarQueryListaSistema(sistema, false);
        query.setFirstResult(sistema.getStartRow());
        query.setMaxResults(sistema.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista Sistema.
     *
     * @param SistemaDTO el sistema
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaSistema(SistemaDTO sistema, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idSistema) from Sistema o where 1=1 ");
        } else {
            jpaql.append(" select o from Sistema o where 1=1 ");           
        }
		if (!StringUtils.isNullOrEmpty(sistema.getSearch())) {
	          jpaql.append(" and upper(o.nombre) like :search ");
	          parametros.put("search", "%" + sistema.getSearch().toUpperCase() + "%");
	    } else {
			if (!StringUtils.isNullOrEmpty(sistema.getNombre())) {
				jpaql.append(" and upper(o.nombre) like :nombre ");
				parametros.put("nombre", "%" + sistema.getNombre().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(sistema.getDescripcion())) {
				jpaql.append(" and upper(o.descripcion) like :descripcion ");
				parametros.put("descripcion", "%" + sistema.getDescripcion().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(sistema.getAbreviatura())) {
				jpaql.append(" and upper(o.abreviatura) like :abreviatura ");
				parametros.put("abreviatura", "%" + sistema.getAbreviatura().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(sistema.getFecha())) {
				jpaql.append(" and o.fecha = :fecha ");
				parametros.put("fecha", sistema.getFecha());
			}
			if (!StringUtils.isNullOrEmpty(sistema.getVersion())) {
				jpaql.append(" and upper(o.version) like :version ");
				parametros.put("version", "%" + sistema.getVersion().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(sistema.getEstado())) {
				jpaql.append(" and upper(o.estado) like :estado ");
				parametros.put("estado", "%" + sistema.getEstado().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(sistema.getIcono())) {
				jpaql.append(" and upper(o.icono) like :icono ");
				parametros.put("icono", "%" + sistema.getIcono().toUpperCase() + "%");
			}
		}
        if (!esContador) {
            jpaql.append(" ORDER BY o.orden asc ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.SistemaDaoLocal#contarListar{entity.getClassName()}(pe.com.edu.siaa.core.model.jpa.seguridad.SistemaDTO)
     */
	@Override
    public int contarListarSistema(SistemaDTO sistema) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaSistema(sistema, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.SistemaDaoLocal#generarIdSistema()
     */
	 @Override
    public Long generarIdSistema() {
        Long resultado = 1L;
        Query query = createQuery("select max(o.idSistema) from Sistema o", null);
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