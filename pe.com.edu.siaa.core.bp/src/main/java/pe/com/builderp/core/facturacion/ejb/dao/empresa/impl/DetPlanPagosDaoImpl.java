package pe.com.builderp.core.facturacion.ejb.dao.empresa.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.builderp.core.facturacion.ejb.dao.empresa.local.DetPlanPagosDaoLocal;
import pe.com.builderp.core.facturacion.model.dto.empresa.DetPlanPagosDTO;
import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericFacturacionDAOImpl; 
import pe.com.edu.siaa.core.ejb.factory.CollectionUtil;
import pe.com.edu.siaa.core.ejb.service.util.FechaUtil;
import pe.com.edu.siaa.core.model.jpa.empresa.DetPlanPagos;
import pe.com.edu.siaa.core.model.util.StringUtils;
import pe.com.edu.siaa.core.model.vo.DetallePlanPagosFiltroVO;

/**
 * La Class DetPlanPagosDaoImpl.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 19:53:31 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
public class DetPlanPagosDaoImpl extends  GenericFacturacionDAOImpl<String, DetPlanPagos> implements DetPlanPagosDaoLocal  {

	@Override
	public String generarIdDetPlanPagos(String idPlanPagos) throws Exception {
		/*String resultado = idPlanPagos + "001";
		Query query = createQuery("select max(dp.idDetPlanPagos) from DetPlanPagos dp where  dp.planPagos.idPlanPagos=:idPlanPago ", null);
		query.setParameter("idPlanPago",  idPlanPagos);
		List<String> listLong =  query.getResultList();
		if (listLong != null && listLong.size() > 0) {
			String ultimoCodigoGenerado = listLong.get(0);
			if (StringUtils.isNotNullOrBlank(ultimoCodigoGenerado)) {
				long ultimoCodigo = Long.parseLong(ultimoCodigoGenerado.trim());
				ultimoCodigo++;
				resultado = "" + ultimoCodigo;
			}
		}
		return resultado;*/
		
		
		 Map<String, Object> parametros = new HashMap<String, Object>();
			String resultado = idPlanPagos.trim() + "01";
			Query query = createQuery("select max(dp.idDetPlanPagos) from DetPlanPagos dp where  dp.planPagos.idPlanPagos=:idPlanPago",parametros);
			query.setParameter("idPlanPago", idPlanPagos);
			List<String> listCodigo =  query.getResultList();
			if (listCodigo != null && listCodigo.size() > 0) {
				String ultimoIdGenerado = listCodigo.get(0);
				if (StringUtils.isNotNullOrBlank(ultimoIdGenerado)) {
					ultimoIdGenerado = ultimoIdGenerado.trim();
					int len = ultimoIdGenerado.length();
					String ultimoIdGeneradoIzquierdo = ultimoIdGenerado.substring(0, len - 2);
					Long ultimoId = Long.parseLong(ultimoIdGenerado.substring(len - 2, len));
					ultimoId++;
					String ultimoIdGeneradoDerecho = StringUtils.completeLeft(ultimoId, '0', 2);
					resultado = ultimoIdGeneradoIzquierdo + ultimoIdGeneradoDerecho;
				}
			}
			return resultado;
		
		
		
		
		
	}
	
	
	/* (non-Javadoc)
	 * @see pe.edu.upp.siaa.pago.dao.DetPlanPagosDaoLocal#actualizarMontoResta(pe.edu.upp.siaa.entity.DetPlanPagos)
	 */
	@Override
	public void actualizarMontoResta(DetPlanPagos detPlanPagos ) {
		Query query = createQuery("update DetPlanPagos set montoResta=:montoResta where idDetPlanPagos=:idDetPlanPagos",null);
		query.setParameter("montoResta", detPlanPagos.getMontoResta());
		query.setParameter("idDetPlanPagos", detPlanPagos.getIdDetPlanPagos());
		query.executeUpdate();
	}
	
 
	@Override
	public List<DetPlanPagos> listarConceptoPagoAsociadoSemestre(String idCliente, boolean flagFaltaMontoResta) throws Exception {
		List<DetPlanPagos> resultado = new ArrayList<DetPlanPagos>();
		Map<String, Object> parametros = new HashMap<String, Object>();
		StringBuilder jpaql = new StringBuilder();
		
		jpaql.append("from DetPlanPagos detPlanPagos left join fetch detPlanPagos.cuotaConcepto ");		
		jpaql.append("  left join fetch detPlanPagos.planPagos  "); 
		

		if (StringUtils.isNotNullOrBlank(idCliente)) {
			jpaql.append(" where detPlanPagos.planPagos.asociado.idAsociado=:idCliente ");
			parametros.put("idCliente", idCliente);
		} 
		if (flagFaltaMontoResta) {
			jpaql.append(" and (montoResta is null or montoResta > 0) ");
		}
		jpaql.append(" order by  detPlanPagos.planPagos.idPlanPagos");
		Query query = this.createQuery(jpaql.toString(),parametros);
		resultado = query.getResultList();
		return resultado;
	}
	
	
	@Override
	public List<DetPlanPagos> listarConceptoPagoAsociadoSemestreAPP(String idAsociado, String idCuotaconcepto) throws Exception {
		List<DetPlanPagos> resultado = new ArrayList<DetPlanPagos>();
		Map<String, Object> parametros = new HashMap<String, Object>();
		StringBuilder jpaql = new StringBuilder();
		
		jpaql.append("from DetPlanPagos detPlanPagos left join fetch detPlanPagos.cuotaConcepto ");		
		jpaql.append("  left join fetch detPlanPagos.planPagos  "); 
		

		if (StringUtils.isNotNullOrBlank(idAsociado)) {
			jpaql.append(" where detPlanPagos.planPagos.asociado.idAsociado=:idAsociado ");
			parametros.put("idAsociado", idAsociado);
		} 
		
		if (StringUtils.isNotNullOrBlank(idCuotaconcepto)) {
			jpaql.append(" and detPlanPagos.cuotaConcepto.idCuotaConcepto=:idCuotaconcepto ");
			jpaql.append(" and (montoResta is null or montoResta > 0) ");
			parametros.put("idCuotaconcepto", idCuotaconcepto);
		} 
 
		jpaql.append(" order by   detPlanPagos.fechaVencimiento desc");
		Query query = this.createQuery(jpaql.toString(),parametros);
		resultado = query.getResultList();
		return resultado;
	}
	
	
	
    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.DetPlanPagosDaoLocal#listarDetPlanPagos(pe.com.edu.siaa.core.model.jpa.seguridad.DetPlanPagos)
     */  
    @Override	 
    public List<DetPlanPagos> listarDetPlanPagos(DetPlanPagosDTO detPlanPagos) {
        Query query = generarQueryListaDetPlanPagos(detPlanPagos, false);
        query.setFirstResult(detPlanPagos.getStartRow());
        query.setMaxResults(detPlanPagos.getOffset());
        return query.getResultList();
    }   

    /**
     * Generar query lista DetPlanPagos.
     *
     * @param DetPlanPagosDTO el detPlanPagos
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaDetPlanPagos(DetPlanPagosDTO detPlanPagos, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idDetPlanPagos) from DetPlanPagos o where 1=1 ");
        } else {
            jpaql.append(" select o from DetPlanPagos o  left join fetch o.planPagos left join fetch o.cuotaConcepto    where 1=1 ");           
        }
        jpaql.append(" and o.planPagos.idPlanPagos =:idPlanPagos ");
        parametros.put("idPlanPagos", detPlanPagos.getId());
        
		if (!StringUtils.isNullOrEmpty(detPlanPagos.getSearch())) {
	          jpaql.append(" and upper(o.cuotaConcepto.cuenta) like :search ");
	          parametros.put("search", "%" + detPlanPagos.getSearch().toUpperCase() + "%");
	    } else {
			if (!StringUtils.isNullOrEmptyNumeric(detPlanPagos.getCuota())) {
				jpaql.append(" and o.cuota = :cuota ");
				parametros.put("cuota", detPlanPagos.getCuota());
			}
			if (!StringUtils.isNullOrEmptyNumeric(detPlanPagos.getMontoResta())) {
				jpaql.append(" and o.montoResta = :montoResta ");
				parametros.put("montoResta", detPlanPagos.getMontoResta());
			}
			if (!StringUtils.isNullOrEmpty(detPlanPagos.getFechaVencimiento())) {
				jpaql.append(" and o.fechaVencimiento = :fechaVencimiento ");
				parametros.put("fechaVencimiento", detPlanPagos.getFechaVencimiento());
			}
			if (!StringUtils.isNullOrEmpty(detPlanPagos.getFlagFraccionado())) {
				jpaql.append(" and upper(o.flagFraccionado) like :flagFraccionado ");
				parametros.put("flagFraccionado", "%" + detPlanPagos.getFlagFraccionado().toUpperCase() + "%");
			}
		}
        if (!esContador) {
            //jpaql.append(" ORDER BY 1 ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.DetPlanPagosDaoLocal#contarListar{entity.getClassName()}(pe.com.edu.siaa.core.model.jpa.seguridad.DetPlanPagosDTO)
     */
	@Override
    public int contarListarDetPlanPagos(DetPlanPagosDTO detPlanPagos) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaDetPlanPagos(detPlanPagos, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.DetPlanPagosDaoLocal#generarIdDetPlanPagos()
     */
	 @Override
    public String generarIdDetPlanPagos() {
        String resultado = "1";
        Query query = createQuery("select max(o.idDetPlanPagos) from DetPlanPagos o", null);
        List<Object> listLong =  query.getResultList();
        if (listLong != null && listLong.size() > 0 && listLong.get(0) != null)  {
            Long ultimoIdGenerado = Long.valueOf(listLong.get(0).toString());
            if (!StringUtils.isNullOrEmpty(ultimoIdGenerado)) {
                resultado = resultado + ultimoIdGenerado;
            }
        }
        return resultado;
    }
   
//agregado
		@Override
		public List<DetPlanPagos> listarDetPlanPagosID(String idplanpagos,String idcuotaconcepto) throws Exception {
			Map<String, Object> parametros = new HashMap<String, Object>();
			boolean ejecutarQuery = false;
			StringBuilder jpaql = new StringBuilder();
			jpaql.append("from DetPlanPagos o  left join fetch o.cuotaConcepto left join fetch o.planPagos where 1=1 ");
			if (idcuotaconcepto != null) {
					ejecutarQuery = true;
					jpaql.append(" and o.cuotaConcepto.idCuotaConcepto = :idCuotaConcepto ");
					parametros.put("idCuotaConcepto", idcuotaconcepto);
			}
			if (idplanpagos != null) {
				ejecutarQuery = true;
				jpaql.append(" and o.planPagos.idPlanPagos = :idplanpagos ");
				parametros.put("idplanpagos", idplanpagos);
		}
			//jpaql.append(" order by cl.escuela.idEscuela ");
			if (ejecutarQuery) {		
				Query query = createQuery(jpaql.toString(), parametros);
				return query.getResultList();
			} 
			return null;
		}
		
		@Override
	    public void updaterDetPlanPagos(String iddetPlanpagos,BigDecimal monto ) throws Exception {
			Map<String, Object> parametros = new HashMap<String, Object>();
			Query query = createQuery("UPDATE  DetPlanPagos o set o.montoResta=o.montoResta+(:monto) where o.idDetPlanPagos =:iddetPlanpagos",parametros);
			query.setParameter("iddetPlanpagos", iddetPlanpagos);
			query.setParameter("monto", monto);
			query.executeUpdate();
		}
		
		
		
		@Override	 
		public DetPlanPagos optenerByDetPlanPagos(String idPlanPagos,String idConcepto,String idPuesto) throws Exception {
			DetPlanPagos resultado = new DetPlanPagos();
			Map<String, Object> parametros = new HashMap<String, Object>();
	        StringBuilder jpaql = new StringBuilder();
			jpaql.append("from DetPlanPagos detPlanPagos left join fetch detPlanPagos.cuotaConcepto ");		 
			jpaql.append(" left join fetch detPlanPagos.planPagos ");
	        
			jpaql.append(" where detPlanPagos.planPagos.idPlanPagos =:idPlanPagos ");
			parametros.put("idPlanPagos", idPlanPagos);
			jpaql.append(" and detPlanPagos.cuotaConcepto.idCuotaConcepto = :idCuotaConcepto ");
			parametros.put("idCuotaConcepto", idConcepto); 
			jpaql.append(" and detPlanPagos.idPuesto = :idPuesto ");
			parametros.put("idPuesto", idPuesto); 
        	jpaql.append(" and to_char(detPlanPagos.fechaVencimiento,'dd/MM/yyyy') =:fecha ");
        	parametros.put("fecha", FechaUtil.obtenerFechaFormatoPersonalizado(FechaUtil.obtenerFecha(), "dd/MM/yyyy"));
	        
	        Query query = createQuery(jpaql.toString(), parametros);
	        List<DetPlanPagos> listaTemp = query.getResultList();
	        if (!CollectionUtil.isEmpty(listaTemp)) {
	        	resultado = listaTemp.get(0);
	        }
			return resultado;
		}
		
		

	@Override
	public List<DetallePlanPagosFiltroVO> listarDetallePlanPagosFiltroVO(DetallePlanPagosFiltroVO filtro) throws Exception { 
		List<DetallePlanPagosFiltroVO> resultado = new ArrayList<DetallePlanPagosFiltroVO>();
		StringBuilder jpaql = new StringBuilder();
        Map<String, Object> parametros = new HashMap<String, Object>();
		jpaql.append("select  o.planPagos.asociado.nombre,o.planPagos.asociado.apellidoPaterno,o.planPagos.asociado.apellidoMaterno, ");
		jpaql.append(" o.planPagos.asociado.idAsociado,o.cuotaConcepto.idCuotaConcepto from DetPlanPagos o ");
		jpaql.append(" where o.cuotaConcepto.idCuotaConcepto = '1' and (o.montoResta > 0.00 or o.montoResta is null) ");
		if (!StringUtils.isNullOrEmpty(filtro.getPuesto())) {
			  jpaql.append(" and (TRANSLATE(UPPER(o.planPagos.asociado.nombre || ' ' || o.planPagos.asociado.apellidoPaterno || ' ' || o.planPagos.asociado.apellidoMaterno ), :discriminaTildeMAC, :discriminaTildeMAT) like UPPER(translate(:search, :discriminaTildeMIC, :discriminaTildeMIT))  or (upper(o.planPagos.asociado.nrodoc) like :search) )");
			  parametros.putAll(obtenerParametroDiscriminarTilde());	
		      parametros.put("search", "%" + filtro.getPuesto().toUpperCase() + "%");
	    }
		jpaql.append(" group by o.planPagos.asociado.idAsociado,o.planPagos.asociado.nombre,o.planPagos.asociado.apellidoPaterno,o.planPagos.asociado.apellidoMaterno ,o.cuotaConcepto.idCuotaConcepto");  
		Query query = createQuery(jpaql.toString(),parametros);
		@SuppressWarnings("unchecked")
		List<Object[]> lisObject = query.getResultList();
		for (Object[] objects : lisObject) {
			DetallePlanPagosFiltroVO detPlanPagosTemp = new  DetallePlanPagosFiltroVO(); 
			detPlanPagosTemp.setIdAsociado((String)objects[3]);
			detPlanPagosTemp.setNombre((String)objects[0]);
			detPlanPagosTemp.setApellidoPaterno((String)objects[1]);
			detPlanPagosTemp.setApellidoMaterno((String)objects[2]);
			detPlanPagosTemp.setIdCuotaConcepto((String)objects[4]);
			resultado.add(detPlanPagosTemp);
		}
		return resultado;
	}
		
}