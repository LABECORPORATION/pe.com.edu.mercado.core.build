package pe.com.edu.siaa.core.ejb.dao.contabilidad.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.edu.siaa.core.ejb.dao.contabilidad.local.AsientoContableDetDaoLocal;
import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericContabilidadDAOImpl;
import pe.com.edu.siaa.core.ejb.util.jms.UUIDUtil;
import pe.com.edu.siaa.core.model.dto.contabilidad.AsientoContableDetDTO;
import pe.com.edu.siaa.core.model.jpa.contabilidad.AsientoContableDet;
import pe.com.edu.siaa.core.model.util.StringUtils;

/**
 * La Class AsientoContableDetDaoImpl.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Fri Sep 08 19:44:57 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
public class AsientoContableDetDaoImpl extends  GenericContabilidadDAOImpl<String, AsientoContableDet> implements AsientoContableDetDaoLocal  {

    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.contabilidad.local.AsientoContableDetDaoLocal#listarAsientoContableDet(pe.com.edu.siaa.core.model.jpa.contabilidad.AsientoContableDet)
     */  
    @Override	 
    public List<AsientoContableDet> listarAsientoContableDet(AsientoContableDetDTO asientoContableDet) {
        Query query = generarQueryListaAsientoContableDet(asientoContableDet, false);
        query.setFirstResult(asientoContableDet.getStartRow());
        query.setMaxResults(asientoContableDet.getOffset());
        return query.getResultList();
    }   
    
    @Override	 
    public List<AsientoContableDet> listarAsientoContableDetReporte(AsientoContableDetDTO asientoContableDet) {
        Query query = generarQueryListaAsientoContableDetReporte(asientoContableDet, false);
        query.setFirstResult(asientoContableDet.getStartRow());
        query.setMaxResults(asientoContableDet.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista AsientoContableDet reporte.
     *
     * @param AsientoContableDetDTO el asientoContableDet
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaAsientoContableDetReporte(AsientoContableDetDTO asientoContableDet, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idAsientoContableDet) from AsientoContableDet o where 1=1 ");
        } else {
            jpaql.append(" select o from AsientoContableDet o left join fetch o.planContable  ");
            jpaql.append(" left join fetch o.asientoContable ac  left join fetch ac.itemByLibro   ");
            jpaql.append(" left join fetch ac.itemBySubLibro where 1=1 ");
        }
		if (!StringUtils.isNullOrEmpty(asientoContableDet.getSearch())) {
	          jpaql.append(" and upper(o.idAsientoContableDet) like :search ");
	          parametros.put("search", "%" + asientoContableDet.getSearch().toUpperCase() + "%");
	    } else {
			if (!StringUtils.isNullOrEmptyNumeric(asientoContableDet.getNroCuenta())) {
				jpaql.append(" and o.nroCuenta = :nroCuenta ");
				parametros.put("nroCuenta", asientoContableDet.getNroCuenta());
			}
			if (!StringUtils.isNullOrEmpty(asientoContableDet.getDescripcionCuenta())) {
				jpaql.append(" and upper(o.descripcionCuenta) like :descripcionCuenta ");
				parametros.put("descripcionCuenta", "%" + asientoContableDet.getDescripcionCuenta().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmptyNumeric(asientoContableDet.getMonto())) {
				jpaql.append(" and o.monto = :monto ");
				parametros.put("monto", asientoContableDet.getMonto());
			}
			if (!StringUtils.isNullOrEmpty(asientoContableDet.getTipo())) {
				jpaql.append(" and upper(o.tipo) like :tipo ");
				parametros.put("tipo", "%" + asientoContableDet.getTipo().toUpperCase() + "%");
			}
		}
        if (!esContador) {
            //jpaql.append(" ORDER BY 1 ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }
    
    /**
     * Generar query lista AsientoContableDet.
     *
     * @param AsientoContableDetDTO el asientoContableDet
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaAsientoContableDet(AsientoContableDetDTO asientoContableDet, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idAsientoContableDet) from AsientoContableDet o where 1=1 ");
        } else {
            jpaql.append(" select o from AsientoContableDet o left join fetch o.planContable where 1=1 ");
        }
        jpaql.append(" and o.asientoContable.idAsientoContable = :idAsientoContable ");
		parametros.put("idAsientoContable", asientoContableDet.getId() + "");
		if (!StringUtils.isNullOrEmpty(asientoContableDet.getSearch())) {
	          jpaql.append(" and upper(o.idAsientoContableDet) like :search ");
	          parametros.put("search", "%" + asientoContableDet.getSearch().toUpperCase() + "%");
	    } else {
			if (!StringUtils.isNullOrEmptyNumeric(asientoContableDet.getNroCuenta())) {
				jpaql.append(" and o.nroCuenta = :nroCuenta ");
				parametros.put("nroCuenta", asientoContableDet.getNroCuenta());
			}
			if (!StringUtils.isNullOrEmpty(asientoContableDet.getDescripcionCuenta())) {
				jpaql.append(" and upper(o.descripcionCuenta) like :descripcionCuenta ");
				parametros.put("descripcionCuenta", "%" + asientoContableDet.getDescripcionCuenta().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmptyNumeric(asientoContableDet.getMonto())) {
				jpaql.append(" and o.monto = :monto ");
				parametros.put("monto", asientoContableDet.getMonto());
			}
			if (!StringUtils.isNullOrEmpty(asientoContableDet.getTipo())) {
				jpaql.append(" and upper(o.tipo) like :tipo ");
				parametros.put("tipo", "%" + asientoContableDet.getTipo().toUpperCase() + "%");
			}
		}
        if (!esContador) {
            //jpaql.append(" ORDER BY 1 ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.contabilidad.local.AsientoContableDetDaoLocal#contarListar{entity.getClassName()}(pe.com.edu.siaa.core.model.jpa.contabilidad.AsientoContableDetDTO)
     */
	@Override
    public int contarListarAsientoContableDet(AsientoContableDetDTO asientoContableDet) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaAsientoContableDet(asientoContableDet, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
	
	@Override
    public int contarListarAsientoContableDetReporte(AsientoContableDetDTO asientoContableDet) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaAsientoContableDetReporte(asientoContableDet, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.contabilidad.local.AsientoContableDetDaoLocal#generarIdAsientoContableDet()
     */
	@Override
    public String generarIdAsientoContableDet() throws Exception {
        return UUIDUtil.generarElementUUID();
    }
   
}