package pe.com.builderp.core.facturacion.ejb.dao.empresa.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.builderp.core.facturacion.ejb.dao.empresa.local.DetControlPagoDaoLocal;
import pe.com.builderp.core.facturacion.model.dto.empresa.DetControlPagoDTO;
import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericFacturacionDAOImpl;
import pe.com.edu.siaa.core.model.estate.EstadoGeneralState;
import pe.com.edu.siaa.core.model.jpa.empresa.DetControlPago;
import pe.com.edu.siaa.core.model.util.StringUtils;

/**
 * La Class DetControlPagoDaoImpl.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 19:53:46 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
public class DetControlPagoDaoImpl extends  GenericFacturacionDAOImpl<String, DetControlPago> implements DetControlPagoDaoLocal  {

	
	@Override
	public List<DetControlPago> listarDetControlPago(String idControlPago) throws Exception {
		Query query = createQuery("from DetControlPago detControlPago left join fetch detControlPago.cuotaConcepto cc left join fetch detControlPago.controlPago ct left join fetch ct.anio  where detControlPago.controlPago.idControlPago = :idControlPago",null);
		query.setParameter("idControlPago", idControlPago);
		return query.getResultList();
	}
	
	 @Override
	 public String generarIdDetControlPago(String idControlPago) throws Exception {
			String resultado = idControlPago.trim() + "01";
			Query query = createQuery("select max(dcp.idDetControlPago) from DetControlPago dcp where dcp.controlPago.idControlPago=:idControlPago",null);
			query.setParameter("idControlPago", idControlPago);
			List<String> listString =  query.getResultList();
			if (listString != null && listString.size() > 0) {
				String ultimoIdGenerado = listString.get(0);
				if (StringUtils.isNotNullOrBlank(ultimoIdGenerado)) {
					long ultimoId = Long.valueOf(ultimoIdGenerado.trim());
					ultimoId++;
					resultado = "" + ultimoId;
				}
			}
			return resultado;
	}
    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.DetControlPagoDaoLocal#listarDetControlPago(pe.com.edu.siaa.core.model.jpa.seguridad.DetControlPago)
     */  
    @Override	 
    public List<DetControlPago> listarDetControlPago(DetControlPagoDTO detControlPago) {
        Query query = generarQueryListaDetControlPago(detControlPago, false);
        query.setFirstResult(detControlPago.getStartRow());
        query.setMaxResults(detControlPago.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista DetControlPago.
     *
     * @param DetControlPagoDTO el detControlPago
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaDetControlPago(DetControlPagoDTO detControlPago, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idDetControlPago) from DetControlPago o where 1=1 ");
        } else {
            jpaql.append(" select o from DetControlPago o where 1=1 ");           
        }
        jpaql.append(" and o.controlpago.idControlPago = :idControlPago ");
      	parametros.put("idControlPago", detControlPago.getId() + "");
		if (!StringUtils.isNullOrEmpty(detControlPago.getSearch())) {
	          jpaql.append(" and upper(o.idDetControlPago) like :search ");
	          parametros.put("search", "%" + detControlPago.getSearch().toUpperCase() + "%");
	    } else {
			if (!StringUtils.isNullOrEmpty(detControlPago.getDescripcionConcepto())) {
				jpaql.append(" and upper(o.descripcionConcepto) like :descripcionConcepto ");
				parametros.put("descripcionConcepto", "%" + detControlPago.getDescripcionConcepto().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmptyNumeric(detControlPago.getMonto())) {
				jpaql.append(" and o.monto = :monto ");
				parametros.put("monto", detControlPago.getMonto());
			}
			if (!StringUtils.isNullOrEmptyNumeric(detControlPago.getMora())) {
				jpaql.append(" and o.mora = :mora ");
				parametros.put("mora", detControlPago.getMora());
			}
			if (!StringUtils.isNullOrEmpty(detControlPago.getFechaReversion())) {
				jpaql.append(" and o.fechaReversion = :fechaReversion ");
				parametros.put("fechaReversion", detControlPago.getFechaReversion());
			}
			if (!StringUtils.isNullOrEmpty(detControlPago.getFechaCreacion())) {
				jpaql.append(" and o.fechaCreacion = :fechaCreacion ");
				parametros.put("fechaCreacion", detControlPago.getFechaCreacion());
			}
			if (!StringUtils.isNullOrEmpty(detControlPago.getUsuarioCreacion())) {
				jpaql.append(" and upper(o.usuarioCreacion) like :usuarioCreacion ");
				parametros.put("usuarioCreacion", "%" + detControlPago.getUsuarioCreacion().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(detControlPago.getFechaModificacion())) {
				jpaql.append(" and o.fechaModificacion = :fechaModificacion ");
				parametros.put("fechaModificacion", detControlPago.getFechaModificacion());
			}
			if (!StringUtils.isNullOrEmpty(detControlPago.getUsuarioModificacion())) {
				jpaql.append(" and upper(o.usuarioModificacion) like :usuarioModificacion ");
				parametros.put("usuarioModificacion", "%" + detControlPago.getUsuarioModificacion().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(detControlPago.getEstado())) {
				jpaql.append(" and upper(o.estado) like :estado ");
				parametros.put("estado", "%" + detControlPago.getEstado().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(detControlPago.getIp())) {
				jpaql.append(" and upper(o.ip) like :ip ");
				parametros.put("ip", "%" + detControlPago.getIp().toUpperCase() + "%");
			}
		}
        if (!esContador) {
            //jpaql.append(" ORDER BY 1 ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.DetControlPagoDaoLocal#contarListar{entity.getClassName()}(pe.com.edu.siaa.core.model.jpa.seguridad.DetControlPagoDTO)
     */
	@Override
    public int contarListarDetControlPago(DetControlPagoDTO detControlPago) {
        int resultado = 0;
        try {
            Query query = generarQueryListaDetControlPago(detControlPago, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.DetControlPagoDaoLocal#generarIdDetControlPago()
     */
	 @Override
    public String generarIdDetControlPago() {
        String resultado = "1";
        Query query = createQuery("select max(o.idDetControlPago) from DetControlPago o", null);
        List<Object> listLong =  query.getResultList();
        if (listLong != null && listLong.size() > 0 && listLong.get(0) != null)  {
            Long ultimoIdGenerado = Long.valueOf(listLong.get(0).toString());
            if (!StringUtils.isNullOrEmpty(ultimoIdGenerado)) {
                resultado = resultado + ultimoIdGenerado;
            }
        }
        return resultado;
    }
   
	// agregado	
	 @Override
	 public List<DetControlPago> listarDetControlPagoAlumno(String idAlumno, String idAnhoSemestre) throws Exception {
	 	Map<String, Object> parametros = new HashMap<String, Object>();
	 	boolean ejecutarQuery = false;
	 	StringBuilder jpaql = new StringBuilder();
	 	jpaql.append("from DetControlPago o  left join fetch  o.cuotaConcepto ctaConct  left join fetch  ctaConct.anhoSemestre  left join fetch ctaConct.catalogoCuenta   left join fetch o.controlPago cPago left join fetch cPago.alumno alum ");
	 	jpaql.append("where  (ctaConct.catalogoCuenta.idCatalogoCuenta = '9' OR ctaConct.catalogoCuenta.idCatalogoCuenta = '10' OR  ctaConct.catalogoCuenta.idCatalogoCuenta = '11' OR  ctaConct.catalogoCuenta.idCatalogoCuenta = '12' OR ctaConct.catalogoCuenta.idCatalogoCuenta = '13' OR ctaConct.catalogoCuenta.idCatalogoCuenta = '14' OR  ctaConct.catalogoCuenta.idCatalogoCuenta = '146' ) ");
	 	if (StringUtils.isNotNullOrBlank(idAlumno)) {
	 		ejecutarQuery = true;
	 		jpaql.append(" and cPago.alumno.idAlumno=:idAlumno ");
	 		parametros.put("idAlumno", idAlumno);
	 	}
	 	if (StringUtils.isNotNullOrBlank(idAnhoSemestre)) {
	 		ejecutarQuery = true;
	 		jpaql.append(" and ctaConct.anhoSemestre.idAnhoSemestre=:idAnhoSemestre ");
	 		parametros.put("idAnhoSemestre", idAnhoSemestre);
	 	}
	 	jpaql.append(" order by o.nroCuota ASC ");
	 	if (ejecutarQuery) {
	 		Query query = createQuery(jpaql.toString(), parametros);
	 		return query.getResultList();
	 	}
	 	return null;
	 }
	 
	 //agregado
		@Override
		public List<DetControlPago> listarDetControlPagoDelectid(String idControlpago) throws Exception {
			Map<String, Object> parametros = new HashMap<String, Object>();
			boolean ejecutarQuery = false;
			StringBuilder jpaql = new StringBuilder();
			jpaql.append("from DetControlPago o  left join fetch o.cuotaConcepto left join fetch o.controlPago where 1=1 ");
			if (idControlpago != null) {
					ejecutarQuery = true;
					jpaql.append(" and o.idDetControlPago = :idDetControlPago ");
					parametros.put("idDetControlPago", idControlpago);
			}
			//jpaql.append(" order by cl.escuela.idEscuela ");
			if (ejecutarQuery) {		
				Query query = createQuery(jpaql.toString(), parametros);
				return query.getResultList();
			} 
			return null;
		}
	
		@Override
	    public void deleteDetControlpago(List<String> idetcontrolpago ) throws Exception {
			Map<String, Object> parametros = new HashMap<String, Object>();
			Query query = createQuery("delete from DetControlPago  where idDetControlPago in (:idetcontrolpago)",parametros);
			query.setParameter("idetcontrolpago", idetcontrolpago);
			query.executeUpdate();
		}
		
		
		@Override
		public DetControlPago findDetcontrolpagoBy(String idControlPago) throws Exception {
			DetControlPago resultado = null;
			StringBuilder jpaql = new StringBuilder();
			jpaql.append("from DetControlPago a left join fetch a.controlPago left join fetch a.cuotaConcepto ");
			jpaql.append(" where a.controlPago.idControlPago =:idControlPago");
			Query query = createQuery(jpaql.toString(),null);
			query.setParameter("idControlPago", idControlPago);
			List<DetControlPago> listaAlumno = query.getResultList();
			if (listaAlumno != null && listaAlumno.size() > 0) {
				resultado = listaAlumno.get(0);
			}
			return resultado;	
		}
 
}