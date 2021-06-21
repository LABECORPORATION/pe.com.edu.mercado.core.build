package pe.com.edu.siaa.core.ejb.dao.contabilidad.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.edu.siaa.core.ejb.dao.contabilidad.local.PlanContableDaoLocal;
import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericContabilidadDAOImpl;
import pe.com.edu.siaa.core.ejb.util.jms.UUIDUtil;
import pe.com.edu.siaa.core.model.dto.contabilidad.PlanContableDTO;
import pe.com.edu.siaa.core.model.jpa.contabilidad.PlanContable;
import pe.com.edu.siaa.core.model.util.StringUtils;

/**
 * La Class PlanContableDaoImpl.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Fri Sep 08 16:33:19 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
public class PlanContableDaoImpl extends  GenericContabilidadDAOImpl<String, PlanContable> implements PlanContableDaoLocal  {

    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.contabilidad.local.PlanContableDaoLocal#listarPlanContable(pe.com.edu.siaa.core.model.jpa.contabilidad.PlanContable)
     */  
    @Override	 
    public List<PlanContable> listarPlanContable(PlanContableDTO planContable) {
        Query query = generarQueryListaPlanContable(planContable, false);
        query.setFirstResult(planContable.getStartRow());
        query.setMaxResults(planContable.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista PlanContable.
     *
     * @param PlanContableDTO el planContable
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaPlanContable(PlanContableDTO planContable, boolean esContador)  {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idPlanContable) from PlanContable o where 1=1 ");
        } else {
            jpaql.append(" select o from PlanContable o left join fetch o.planContableDepedencia left join fetch o.planContableDebe   left join fetch o.planContableHaber left join fetch o.entidad  where 1=1  ");           
        }
        boolean ejecutoBusqueda = false;
        if (!StringUtils.isNullOrEmptyNumeric(planContable.getIdEmpresaSelect())) {//comodin
        	jpaql.append(" and (o.entidad.idEntidad =:idEntidad  ");
        		
        	jpaql.append("  or o.entidad.idEntidad is null  ");
        	
        	jpaql.append("  ) ");
			parametros.put("idEntidad", planContable.getIdEmpresaSelect());
			//ejecutoBusqueda = true;
        }
        if (!StringUtils.isNullOrEmpty(planContable.getId())) {//comodin
        	jpaql.append(" and o.planContableDepedencia.idPlanContable =:idPlanContableDependencia ");
			parametros.put("idPlanContableDependencia", planContable.getId());
			ejecutoBusqueda = true;
        }
		if (!StringUtils.isNullOrEmpty(planContable.getSearch())) {
	          jpaql.append(" and ((upper(o.nombre) like :search) or (o.codigo = :searchEquals) ) ");
	          parametros.put("search", "%" + planContable.getSearch().toUpperCase() + "%");
	          parametros.put("searchEquals", planContable.getSearch());
	          ejecutoBusqueda = true;
	    } else {
			if (!StringUtils.isNullOrEmpty(planContable.getCodigo())) {
				jpaql.append(" and upper(o.codigo) like :codigo ");
				parametros.put("codigo", "%" + planContable.getCodigo().toUpperCase() + "%");
				ejecutoBusqueda = true;
			}
			if (!StringUtils.isNullOrEmpty(planContable.getNombre())) {
				jpaql.append(" and upper(o.nombre) like :nombre ");
				parametros.put("nombre", "%" + planContable.getNombre().toUpperCase() + "%");
				ejecutoBusqueda = true;
			}
			if (!StringUtils.isNullOrEmpty(planContable.getEstado())) {
				jpaql.append(" and o.estado = :estado ");
				parametros.put("estado", planContable.getEstado());
				ejecutoBusqueda = true;
			}
		}
		if (!ejecutoBusqueda) {
			jpaql.append(" and o.planContableDepedencia.idPlanContable is null ");
		}
        if (!esContador) {
            jpaql.append(" ORDER BY o.codigo ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }


    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.contabilidad.local.PlanContableDaoLocal#contarListar{entity.getClassName()}(pe.com.edu.siaa.core.model.jpa.contabilidad.PlanContableDTO)
     */
	@Override
    public int contarListarPlanContable(PlanContableDTO planContable) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaPlanContable(planContable, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.contabilidad.local.PlanContableDaoLocal#generarIdPlanContable()
     */
	 @Override
    public String generarIdPlanContable() {
        return UUIDUtil.generarElementUUID();
    }
   
}