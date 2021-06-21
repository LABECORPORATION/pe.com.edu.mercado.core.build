package pe.com.builderp.core.facturacion.ejb.dao.empresa.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.builderp.core.facturacion.ejb.dao.empresa.local.PlanPagosDaoLocal;
import pe.com.builderp.core.facturacion.model.dto.empresa.PlanPagosDTO;
import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericFacturacionDAOImpl;
import pe.com.edu.siaa.core.ejb.service.util.FechaUtil;
import pe.com.edu.siaa.core.ejb.util.jms.UUIDUtil;
import pe.com.edu.siaa.core.model.jpa.empresa.PlanPagos;
import pe.com.edu.siaa.core.model.util.ObjectUtil;
import pe.com.edu.siaa.core.model.util.StringUtils;

/**
 * La Class PlanPagosDaoImpl.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 19:53:42 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
public class PlanPagosDaoImpl extends  GenericFacturacionDAOImpl<String, PlanPagos> implements PlanPagosDaoLocal  {

    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.PlanPagosDaoLocal#listarPlanPagos(pe.com.edu.siaa.core.model.jpa.seguridad.PlanPagos)
     */  
    @Override	 
    public List<PlanPagos> listarPlanPagos(PlanPagosDTO planPagos) {
        Query query = generarQueryListaPlanPagos(planPagos, false);
        query.setFirstResult(planPagos.getStartRow());
        query.setMaxResults(planPagos.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista PlanPagos.
     *
     * @param PlanPagosDTO el planPagos
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaPlanPagos(PlanPagosDTO planPagos, boolean esContador) { 
    	String idAsociado = "";
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idPlanPagos) from PlanPagos o where 1=1 ");
        } else {
            jpaql.append(" select o from PlanPagos o where 1=1 ");           
        } 
        if (planPagos.getAsociado() != null && !StringUtils.isNullOrEmpty(planPagos.getAsociado().getIdAsociado())) {
        	idAsociado = planPagos.getAsociado().getIdAsociado();
            jpaql.append(" and o.asociado.idAsociado =:idAsociado ");
            parametros.put("idAsociado",idAsociado);
        }
        
        if (planPagos.getAnio() != null && !StringUtils.isNullOrEmpty(planPagos.getAnio().getIdAnhio())) { 
            jpaql.append(" and o.anio.idAnhio =:idAnhio ");
            parametros.put("idAnhio",ObjectUtil.objectToLong(planPagos.getAnio().getIdAnhio()));
        } 
        
		if (!StringUtils.isNullOrEmpty(planPagos.getSearch())) {
	          jpaql.append(" and upper(o.idPlanPagos) like :search ");
	          parametros.put("search", "%" + planPagos.getSearch().toUpperCase() + "%");
	    } else {
			if (!StringUtils.isNullOrEmpty(planPagos.getFechaCreacion())) {
				jpaql.append(" and o.fechaCreacion = :fechaCreacion ");
				parametros.put("fechaCreacion", planPagos.getFechaCreacion());
			}
			if (!StringUtils.isNullOrEmpty(planPagos.getUsuarioCreacion())) {
				jpaql.append(" and upper(o.usuarioCreacion) like :usuarioCreacion ");
				parametros.put("usuarioCreacion", "%" + planPagos.getUsuarioCreacion().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(planPagos.getFechaModificacion())) {
				jpaql.append(" and o.fechaModificacion = :fechaModificacion ");
				parametros.put("fechaModificacion", planPagos.getFechaModificacion());
			}
			if (!StringUtils.isNullOrEmpty(planPagos.getUsuarioModificacion())) {
				jpaql.append(" and upper(o.usuarioModificacion) like :usuarioModificacion ");
				parametros.put("usuarioModificacion", "%" + planPagos.getUsuarioModificacion().toUpperCase() + "%");
			}
		}
        if (!esContador) {
            //jpaql.append(" ORDER BY 1 ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.PlanPagosDaoLocal#contarListar{entity.getClassName()}(pe.com.edu.siaa.core.model.jpa.seguridad.PlanPagosDTO)
     */
	@Override
    public int contarListarPlanPagos(PlanPagosDTO planPagos) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaPlanPagos(planPagos, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
	
	//add
	@Override
	public String generarIdPlanPagos(String idCliente) throws Exception {
		String resultado = null;
		if(idCliente != null) {
			 resultado = idCliente;
		} 
		return resultado;
	}
    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.PlanPagosDaoLocal#generarIdPlanPagos()
     */
	 @Override
    public String generarIdPlanPagos() {
        /*String resultado = "1";
        Query query = createQuery("select max(o.idPlanPagos) from PlanPagos o", null);
        List<Object> listLong =  query.getResultList();
        if (listLong != null && listLong.size() > 0 && listLong.get(0) != null)  {
            Long ultimoIdGenerado = Long.valueOf(listLong.get(0).toString());
            if (!StringUtils.isNullOrEmpty(ultimoIdGenerado)) {
                resultado = resultado + ultimoIdGenerado;
            }
        }*/
        return UUIDUtil.generarElementUUID();
    }
   
		//agregado
	 
	@Override
	public List<PlanPagos> listarPlanPagosID(String idAsociado) throws Exception {
		Map<String, Object> parametros = new HashMap<String, Object>();
		boolean ejecutarQuery = false;
		StringBuilder jpaql = new StringBuilder();
		jpaql.append("from PlanPagos o   left join fetch o.asociado  where 1=1 ");
	 
		if (idAsociado != null) {
			ejecutarQuery = true;
			jpaql.append(" and o.asociado.idAsociado = :idAsociado ");
			parametros.put("idAsociado", idAsociado);
	     } 
		//jpaql.append(" order by cl.escuela.idEscuela ");
		if (ejecutarQuery) {		
			Query query = createQuery(jpaql.toString(), parametros);
			return query.getResultList();
		} 
		return null;
	}
	
	
	@Override
	public PlanPagos findByPlanPagos(PlanPagosDTO planPagos) throws Exception {
		PlanPagos resultado = null;
		StringBuilder jpaql = new StringBuilder();
		 Map<String, Object> parametros = new HashMap<String, Object>();
		jpaql.append("from PlanPagos o left join fetch o.asociado left join fetch o.anio");
		jpaql.append(" where 1=1 ");
		  if (planPagos.getAsociado() != null && !StringUtils.isNullOrEmpty(planPagos.getAsociado().getIdAsociado())) { 
	            jpaql.append(" and o.asociado.idAsociado =:idAs ");
	            parametros.put("idAs", planPagos.getAsociado().getIdAsociado());
	        }
	        
	        if (planPagos.getAnio() != null && !StringUtils.isNullOrEmpty(planPagos.getAnio().getIdAnhio())) { 
	            jpaql.append(" and o.anio.idAnhio =:idAnhio ");
	            parametros.put("idAnhio", planPagos.getAnio().getIdAnhio());
	        } 
	        
	        if(!StringUtils.isNullOrEmpty(planPagos.getIdFiltro1())) {
	        	jpaql.append(" and to_char(o.fechaCreacion,'MM') =:mes ");
	        	parametros.put("mes", FechaUtil.obtenerFechaFormatoPersonalizado(FechaUtil.obtenerFecha(), "MM"));
	        }
		Query query = createQuery(jpaql.toString(),parametros); 
         
		List<PlanPagos> listaPlanPagos = query.getResultList();
		if (listaPlanPagos != null && listaPlanPagos.size() > 0) {
			resultado = listaPlanPagos.get(0);
		}
		return resultado;	
	}
	
 
	
/*	 
@Override
public List<PlanPagos> listarPlanPagosID(String idsemestre,String idalumno) throws Exception {
	Map<String, Object> parametros = new HashMap<String, Object>();
	boolean ejecutarQuery = false;
	StringBuilder jpaql = new StringBuilder();
	jpaql.append("from PlanPagos o  left join fetch o.alumno left join fetch o.anhoSemestre where 1=1 ");
	if (idalumno != null) {
			ejecutarQuery = true;
			jpaql.append(" and o.alumno.idAlumno = :idalumno ");
			parametros.put("idalumno", idalumno);
	}
	if (idsemestre != null) {
		ejecutarQuery = true;
		jpaql.append(" and o.anhoSemestre.idAnhoSemestre = :idsemestre ");
		parametros.put("idsemestre", idsemestre);
}
	//jpaql.append(" order by cl.escuela.idEscuela ");
	if (ejecutarQuery) {		
		Query query = createQuery(jpaql.toString(), parametros);
		return query.getResultList();
	} 
	return null;
}*/
//add ojo
	
  
}