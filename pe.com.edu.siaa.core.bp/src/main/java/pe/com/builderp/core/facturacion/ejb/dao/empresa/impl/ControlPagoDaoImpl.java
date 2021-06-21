package pe.com.builderp.core.facturacion.ejb.dao.empresa.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.ibm.icu.text.DecimalFormat;

import pe.com.builderp.core.facturacion.ejb.dao.empresa.local.ControlPagoDaoLocal;
import pe.com.builderp.core.facturacion.model.dto.empresa.ControlPagoDTO;
import pe.com.builderp.core.facturacion.model.jpa.venta.Venta;
import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericFacturacionDAOImpl;
import pe.com.edu.siaa.core.ejb.factory.ConstanteQueryNameUtil;
import pe.com.edu.siaa.core.ejb.factory.SqlMapingUtil;
import pe.com.edu.siaa.core.ejb.jdbc.generic.GenericJDBC;
import pe.com.edu.siaa.core.ejb.service.util.FechaUtil;
import pe.com.edu.siaa.core.ejb.util.log.Logger; 
import pe.com.edu.siaa.core.model.estate.EstadoGeneralState;
import pe.com.edu.siaa.core.model.jdbc.vo.ScriptSqlResulJDBCVO;
import pe.com.edu.siaa.core.model.jpa.empresa.ControlPago;
import pe.com.edu.siaa.core.model.util.StringUtils;
import pe.com.edu.siaa.core.model.vo.PagoFiltroVO;
import pe.com.edu.siaa.core.model.vo.VentaFiltroVO;

/**
 * La Class ControlPagoDaoImpl.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 19:53:43 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
public class ControlPagoDaoImpl extends  GenericFacturacionDAOImpl<String, ControlPago> implements ControlPagoDaoLocal  {

	private Logger log = Logger.getLogger(ControlPagoDaoImpl.class);
	
	@Override
	public String generarNumeroOperacion(Integer anho) throws Exception {
		Map<String, Object> parametros = new HashMap<String, Object>();
		String resultado = "0000000001";
		parametros.put("anho", anho);
		Query query = createQuery("select max(cc.nroCorrelativoOperacion) from ControlPago cc where YEAR(cc.fechaCreacion) =:anho",parametros);
		query.setParameter("anho", anho);
		List<String> listLong =  query.getResultList();
		if (listLong != null && listLong.size() > 0) {
			String ultimoIdGenerado = listLong.get(0);
			if (StringUtils.isNotNullOrBlank(ultimoIdGenerado)) {
				long ultimoId = Long.parseLong(ultimoIdGenerado.trim());
				ultimoId++;
				resultado = "" + ultimoId;
			}
		}
		resultado = StringUtils.completeLeft(resultado, '0', 10);
		return resultado;
	}
	//ID GENERADO:200510000001
	@Override
	public String generarIdControlPago(String idAnhoSemestre) throws Exception {
		Map<String, Object> parametros = new HashMap<String, Object>();
			String resultado = idAnhoSemestre + "0000001"; 
			Query query = createQuery("select max(cc.idControlPago) from ControlPago cc where cc.anio.idAnhio=:idAnhoSemestre",parametros);
			query.setParameter("idAnhoSemestre", Long.parseLong(idAnhoSemestre));
			List<String> listLong =  query.getResultList();
			if (listLong != null && listLong.size() > 0) {
				String ultimoIdGenerado = listLong.get(0);
				if (StringUtils.isNotNullOrBlank(ultimoIdGenerado)) {
					long ultimoId = Long.parseLong(ultimoIdGenerado.trim());
					ultimoId++;
					resultado = "" + ultimoId;
				}
			}
			return resultado;
		}
		
    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.ControlPagoDaoLocal#listarControlPago(pe.com.edu.siaa.core.model.jpa.seguridad.ControlPago)
     */  
    @Override	 
    public List<ControlPago> listarControlPago(ControlPagoDTO controlPago) {
        Query query = generarQueryListaControlPago(controlPago, false);
        query.setFirstResult(controlPago.getStartRow());
        query.setMaxResults(controlPago.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista ControlPago.
     *
     * @param ControlPagoDTO el controlPago
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaControlPago(ControlPagoDTO controlPago, boolean esContador) {
    	String idAlumno = "";
    	String idCliente = "";
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idControlPago) from ControlPago o where 1=1 ");
        } else {
            jpaql.append(" select o from ControlPago o left join fetch o.asociado left join fetch o.tipoDocSunat");
            jpaql.append(" left join fetch o.itemByTipoMoneda  ");
            jpaql.append(" where 1=1 ");         
        } 
    
        if(!StringUtils.isNullOrEmpty(controlPago.getFechaInicio())) { 
            jpaql.append(" and o.fechaPago  BETWEEN :fechaInicio  AND :fechaFin  AND o.alumno.idAlumno !='' ");
          	parametros.put("fechaInicio", controlPago.getFechaInicio());        	
          	parametros.put("fechaFin", controlPago.getFechaFin());      	
        }
        
        if(!StringUtils.isNullOrEmpty(controlPago.getEstadoConvertido())) {
            jpaql.append(" and o.estadoConvertido =:estadoConvertido ");   	
          	parametros.put("estadoConvertido", controlPago.getEstadoConvertido());        	
        }
        if(!StringUtils.isNullOrEmpty(controlPago.getSerie())) {
        	 jpaql.append(" and (o.serie = 'B001' or o.serie = 'B002' or o.serie = 'F001' or o.serie = 'F002' ) and o.anulado !='S' ");      	
        }
         
        
        if(!StringUtils.isNullOrEmpty(controlPago.getMontoMinimo())) {
            jpaql.append(" and o.montoTotal >=:montoTotal ");   	
          	parametros.put("montoTotal", controlPago.getMontoMinimo());        	
        }
        
       if (!StringUtils.isNullOrEmpty(controlPago.getId())) {
            jpaql.append(" and o.asociado.idAsociado =:idFiltro1 ");
            parametros.put("idFiltro1",controlPago.getId());
        }
        
        if (!StringUtils.isNullOrEmpty(controlPago.getIdPadreView() )) {
            jpaql.append(" and o.cliente.idCliente =:idFiltro2 ");
            parametros.put("idFiltro2",controlPago.getIdPadreView());
        }
 
		if (!StringUtils.isNullOrEmpty(controlPago.getSearch())) {
	          jpaql.append(" and upper(o.nroDoc) like :search or o.idControlPago in (select detc.controlPago.idControlPago from DetControlPago detc where detc.controlPago.idControlPago in( select cot from ControlPago cot where cot.alumno.idAlumno = :idAlumno) and  upper(detc.descripcionConcepto) like :search )  ");
	          parametros.put("idAlumno", controlPago.getId());
	          parametros.put("search", "%" + controlPago.getSearch().toUpperCase() + "%");
	    }else {
	    	if (!StringUtils.isNullOrEmpty(controlPago.getIdControlPago())) {
				jpaql.append(" and o.idControlPago = :idControlPago ");
				parametros.put("idControlPago", controlPago.getIdControlPago());
			}
			if (!StringUtils.isNullOrEmptyNumeric(controlPago.getTipoCambio())) {
				jpaql.append(" and o.tipoCambio = :tipoCambio ");
				parametros.put("tipoCambio", controlPago.getTipoCambio());
			}
			if (!StringUtils.isNullOrEmpty(controlPago.getNroDoc())) {
				jpaql.append(" and upper(o.nroDoc) like :nroDoc ");
				parametros.put("nroDoc", "%" + controlPago.getNroDoc().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmptyNumeric(controlPago.getIgv())) {
				jpaql.append(" and o.igv = :igv ");
				parametros.put("igv", controlPago.getIgv());
			}
			if (!StringUtils.isNullOrEmptyNumeric(controlPago.getSubMontoTotal())) {
				jpaql.append(" and o.subMontoTotal = :subMontoTotal ");
				parametros.put("subMontoTotal", controlPago.getSubMontoTotal());
			}
			if (!StringUtils.isNullOrEmptyNumeric(controlPago.getMontoTotal())) {
				jpaql.append(" and o.montoTotal = :montoTotal ");
				parametros.put("montoTotal", controlPago.getMontoTotal());
			}
			if (!StringUtils.isNullOrEmpty(controlPago.getFechaPago())) {
				jpaql.append(" and o.fechaPago = :fechaPago ");
				parametros.put("fechaPago", controlPago.getFechaPago());
			}
			if (!StringUtils.isNullOrEmpty(controlPago.getFechaCreacion())) {
				jpaql.append(" and o.fechaCreacion = :fechaCreacion ");
				parametros.put("fechaCreacion", controlPago.getFechaCreacion());
			}
			if (!StringUtils.isNullOrEmpty(controlPago.getUsuarioCreacion())) {
				jpaql.append(" and upper(o.usuarioCreacion) like :usuarioCreacion ");
				parametros.put("usuarioCreacion", "%" + controlPago.getUsuarioCreacion().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(controlPago.getFechaModificacion())) {
				jpaql.append(" and o.fechaModificacion = :fechaModificacion ");
				parametros.put("fechaModificacion", controlPago.getFechaModificacion());
			}
			if (!StringUtils.isNullOrEmpty(controlPago.getUsuarioModificacion())) {
				jpaql.append(" and upper(o.usuarioModificacion) like :usuarioModificacion ");
				parametros.put("usuarioModificacion", "%" + controlPago.getUsuarioModificacion().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(controlPago.getEstado())) {
				jpaql.append(" and upper(o.estado) like :estado ");
				parametros.put("estado", "%" + controlPago.getEstado().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(controlPago.getIp())) {
				jpaql.append(" and upper(o.ip) like :ip ");
				parametros.put("ip", "%" + controlPago.getIp().toUpperCase() + "%");
			}
		}
        if (!esContador) {
        	//jpaql.append(" ORDER BY o.fechaPago desc NULLS LAST ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.ControlPagoDaoLocal#contarListar{entity.getClassName()}(pe.com.edu.siaa.core.model.jpa.seguridad.ControlPagoDTO)
     */
	@Override
    public int contarListarControlPago(ControlPagoDTO controlPago) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaControlPago(controlPago, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.dao.seguridad.local.ControlPagoDaoLocal#generarIdControlPago()
     */
	 @Override
    public String generarIdControlPago() {
        String resultado = "1";
        Query query = createQuery("select max(o.idControlPago) from ControlPago o", null);
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
		public ControlPago findControlPagoRepetido(String nroDoc,Long idTipoDoc) throws Exception {
			ControlPago resultado = null;
			StringBuilder jpaql = new StringBuilder();
			jpaql.append("from  ControlPago a where a.nroDoc =:nroDoc and  a.tipoDocSunat.idItem=:idTipoDoc");
			Query query = createQuery(jpaql.toString(),null);
			query.setParameter("nroDoc", nroDoc);
			query.setParameter("idTipoDoc", idTipoDoc);
			List<ControlPago> listaControlPago = query.getResultList();
			if (listaControlPago != null && listaControlPago.size() > 0) {
				resultado = listaControlPago.get(0);
			}
			return resultado;	
		}
		
		 //agregado
		 
		@Override
	    public void updaterControlPago(String idControlPago,BigDecimal cuotaMonto ) throws Exception {
			Map<String, Object> parametros = new HashMap<String, Object>();
			Query query = createQuery("UPDATE  ControlPago o set o.subMontoTotal=:cuotaMonto,o.montoTotal=:cuotaMonto where o.idControlPago =:idControlPago",parametros);
			query.setParameter("idControlPago", idControlPago);
			query.setParameter("cuotaMonto", cuotaMonto);
			query.executeUpdate();
	    }
		
		
		@Override
	    public void updaterControlPagoByconvertidor(String idControlPago,BigDecimal cuotaMonto,String estado ) throws Exception {
			Map<String, Object> parametros = new HashMap<String, Object>();
			Query query = createQuery("UPDATE  ControlPago o set o.subMontoTotal=:cuotaMonto,o.montoTotal=:cuotaMonto,estadoConvertido=:estado  where o.idControlPago =:idControlPago",parametros);
			query.setParameter("idControlPago", idControlPago);
			query.setParameter("cuotaMonto", cuotaMonto);
			query.setParameter("estado", estado);
			query.executeUpdate();
	    }
		
		
		
	@Override
	    public void updaterControlPagoManual(String idControlPago,BigDecimal cuotaMonto ) throws Exception {
			Map<String, Object> parametros = new HashMap<String, Object>();
			Query query = createQuery("UPDATE  ControlPago o set o.subMontoTotal=subMontoTotal-(:cuotaMonto) ,o.montoTotal=montoTotal-(:cuotaMonto) where o.idControlPago in(:idControlPago)",parametros);
			query.setParameter("idControlPago", idControlPago);
			query.setParameter("cuotaMonto", cuotaMonto);
			query.executeUpdate();
		}
		
		
		
		
		
		 //agregado
			@Override
		public List<ControlPago> listaControlPagoIdalumno(String idControlpago) throws Exception {
			Map<String, Object> parametros = new HashMap<String, Object>();
			boolean ejecutarQuery = false;
			StringBuilder jpaql = new StringBuilder();
			jpaql.append("from ControlPago o  left join fetch o.anhoSemestre left join fetch o.tipoDocSunat left join fetch o.itemByTipoMoneda left join fetch o.cliente  where 1=1  ");
			if (idControlpago != null) {
					ejecutarQuery = true;
					jpaql.append(" and o.idControlPago = :idControlPago ");
					parametros.put("idControlPago", idControlpago);
			}
			//jpaql.append(" order by cl.escuela.idEscuela ");
			if (ejecutarQuery) {		
				Query query = createQuery(jpaql.toString(), parametros);
				return query.getResultList();
			} 
			return null;
		}
			
			//add ojo
			
			/**
			    * Parsear datos registro nota dto.
			    *
			    * @param listaObject el lista object
			    * @return the list
			    */
		   private List<PagoFiltroVO> parsearDatosIgresoDetalladoDTO(List<Object[]>   listaObject) {
		 	  List<PagoFiltroVO> resultado = new  ArrayList<PagoFiltroVO>();
		 	  try {
		 		  if (listaObject != null) {
		 				for (Object[] res : listaObject) {
		 					PagoFiltroVO detallePagoDTO  = new PagoFiltroVO();	 	
		 					if(res[0] != null) {
			 					detallePagoDTO.setFechaPago((String)res[0]);
		 					}
		 					if(res[1] != null) {
			 					detallePagoDTO.setTipoComprobante((String)res[1]);
		 					}
		 					if(res[2] != null) {
			 					detallePagoDTO.setSerie((String)res[2]);
		 					}
		 					if(res[3] != null) {
			 					detallePagoDTO.setNroComprobante((String)res[3]);	
		 					}
		 					if(res[4] != null) {
			 					detallePagoDTO.setClienteNombre((String)res[4]);
		 					}
		 					if(res[5] != null) {
			 					detallePagoDTO.setTipoDoc((String)res[5]);
		 					}
		 					if(res[6] != null) {
			 					detallePagoDTO.setNroDoc((String)res[6]);
		 					}
		 					if(StringUtils.isNotNullOrBlank(res[7])) {
			 					detallePagoDTO.setMoneda((String)res[7]);
		 					}
		 					if(StringUtils.isNotNullOrBlank(res[8])) {
			 					detallePagoDTO.setCondicion((String)res[8]);
		 					}
		 					if(res[9] != null) {
			 					detallePagoDTO.setNroCuenta((String)res[9]);
		 					}
		 					if(res[10] != null) {
			 					detallePagoDTO.setCuentaContableTotal((Integer)res[10]);
		 					}
		 					if(res[11] != null) {
			 					detallePagoDTO.setMedioPago((String)res[11]);
		 					}
		 					if(res[12] != null) {
			 					detallePagoDTO.setConcepto((String)res[12]);
		 					}
		 					if(res[13] != null) {
		 						double doble = Double.parseDouble(res[13].toString());
		 						BigDecimal formatNumber = new BigDecimal(doble);
		 						DecimalFormat formatea = new DecimalFormat("###,###.00");
		 						formatNumber = formatNumber.setScale(2, RoundingMode.DOWN);
		 	 					detallePagoDTO.setEfectivo(formatea.format(formatNumber));		 					
		 					}
		 					
		 					resultado.add(detallePagoDTO);
		 				}
		 			}
		 	  } catch (Exception e) {
		 			log.error(e);
		 		}
		 	   
		 	  return resultado;
		   }	
		   
		@Override
		   public List<PagoFiltroVO> obtenerIngresoDetalladoMap( Date fechaInicio, Date fechaFin) throws Exception{
			Map<String, Object> parametros = new HashMap<String, Object>();
		    List<PagoFiltroVO>  resultado = new  ArrayList<PagoFiltroVO>();
			StringBuilder jpaql = generarQueryIngresoDetallado();
			parametros.put("fechaInicio", fechaInicio);
			parametros.put("fechaFin", fechaFin);
			Query query = createNativeQuery(jpaql.toString(), parametros);
			List<Object[]>   listaObject =  query.getResultList(); 
			List<PagoFiltroVO> resultadoRegistroNotaDTO = parsearDatosIgresoDetalladoDTO(listaObject);		 
			for (PagoFiltroVO registroNotaDTO : resultadoRegistroNotaDTO) {	
				resultado.add(registroNotaDTO);
				}
			
			return resultadoRegistroNotaDTO;
		}	
		
			
			//agregado	
		private StringBuilder generarQueryIngresoDetallado()  {		
			StringBuilder jpaql = new StringBuilder();		
			jpaql.append(" SELECT  to_char(ControlPago.fechaPago,'dd/MM/yyyy') as FechaPago, case when docSunat.idITem = 686 then '01' when docSunat.idITem = 688 then '03' end as TipoComprobante,ControlPago.serie as Serie,ControlPago.nrodoc as NroBoleta,case when  Cliente.nombre is null then  (Persona.apellidoPaterno || ' ' || Persona.apellidoMaterno || ' ' || Persona.nombre) else Cliente.nombre end as Cliente, ");
			jpaql.append("case when  Cliente.idtipodocumentoidentidad is null  then (case when Persona.idtipodocidentidad = 20 then '1' when Persona.idtipodocidentidad = 21 then '4' when Persona.idtipodocidentidad = 22 then '7' when Persona.idtipodocidentidad = 911 then '6'end ) else (case when Cliente.idtipodocumentoidentidad = 20 then '1' when Cliente.idtipodocumentoidentidad = 21 then '4' when Cliente.idtipodocumentoidentidad = 22 then '7' when Cliente.idtipodocumentoidentidad = 911 then '6'end ) end as TipoDoc, "); 	
			jpaql.append("case when  Cliente.nroDoc is null then Persona.nrodoc else Cliente.nroDoc end as nroDoc, Cast('S' as varchar) as Moneda,Cast('CON' as varchar) as Condicion, CatalogoCuenta.nroCuenta as NroCuenta, 1212 as CuentaContableTotal, case when ControlPago.deposito = 'S' then '001' when ControlPago.deposito = 'N' then '009' when ControlPago.deposito is null then '009' end as MedioPago, DetcontrolPago.descripcionConcepto as Concepto,case  when ControlPago.descuento = 0.00 then DetControlPago.monto when ControlPago.descuento > 0.00 then CAST((DetControlPago.monto-(DetControlPago.monto*ControlPago.descuento/100))AS DECIMAL(6,2)) end as Efectivo"); 		
			jpaql.append(" from dbo.ControlPago ControlPago LEFT JOIN ");
			jpaql.append("dbo.Alumno Alumno on ControlPago.idAlumno = Alumno.idAlumno LEFT JOIN ");
			jpaql.append("dbo.Postulante Postulante on  Alumno.idPostulante = Postulante.idPostulante LEFT JOIN ");
			jpaql.append("dbo.Persona Persona on Postulante.idPersona = Persona.idPersona LEFT JOIN ");
			
			jpaql.append("factu.Cliente Cliente on ControlPago.idCliente = Cliente.idCliente INNER JOIN ");
			jpaql.append("commo.item docSunat on  ControlPago.idtipodocsunat = docSunat.iditem LEFT JOIN ");
			
			jpaql.append("dbo.DetcontrolPago DetcontrolPago ON  ControlPago.idControlPago = DetcontrolPago.idControlPago LEFT JOIN ");
			jpaql.append("dbo.CuotaConcepto on DetcontrolPago.idCuotaConcepto = CuotaConcepto.idCuotaConcepto LEFT JOIN ");
			jpaql.append("dbo.CatalogoCuenta on CuotaConcepto.idCatalogoCuenta = CatalogoCuenta.idCatalogoCuenta ");
			jpaql.append("where ControlPago.fechaPago  BETWEEN :fechaInicio  AND :fechaFin and (ControlPago.serie = 'B001' or ControlPago.serie = 'B002' or ControlPago.serie = 'F001' or ControlPago.serie = 'F002' ) ");	
			
			jpaql.append(" order by ControlPago.fechaPago asc ");		
			return jpaql;
		}
	
		@Override
		public ControlPago findAlumnoByControlPago(String idDetControlPago) throws Exception {
			ControlPago resultado = null;
			StringBuilder jpaql = new StringBuilder();
			jpaql.append("from ControlPago o left join fetch o.anhoSemestre left join fetch o.tipoDocSunat ");
			jpaql.append(" left join fetch o.alumno ");
			jpaql.append(" left join fetch o.cliente");
			jpaql.append(" where o.idControlPago =:idDetControlPago");
			Query query = createQuery(jpaql.toString(),null);
			query.setParameter("idDetControlPago", idDetControlPago); 
			List<ControlPago> listaAlumno = query.getResultList();
			if (listaAlumno != null && listaAlumno.size() > 0) {
				resultado = listaAlumno.get(0);
			}
			return resultado;	
		}
		 
		
		@Override
		public List<Object[]> listaControlPagoGrupByFechas() throws Exception {
			Map<String, Object> parametros = new HashMap<String, Object>();
			StringBuilder jpaql = new StringBuilder();
			jpaql.append("select to_date(to_char(o.fechaPago,'yyyy-MM-dd'),'yyyy-MM-dd') , count(o.fechaPago), to_char(o.fechaPago,'yyyymmdd') ,o.tipoDocSunat.idItem   from ControlPago o ");
			jpaql.append(" where o.envioSunat = '' and o.tipoDocSunat.idItem='688' and to_char(o.fechaPago,'dd/MM/yyyy')!=:fecha  and (o.serie = 'B001' or o.serie = 'B002' ) ");
			jpaql.append(" group by to_date(to_char(o.fechaPago,'yyyy-MM-dd'),'yyyy-MM-dd'),to_char(o.fechaPago,'yyyymmdd'),o.tipoDocSunat.idItem  ");
			parametros.put("fecha",FechaUtil.obtenerFechaFormatoPersonalizado(FechaUtil.obtenerFecha(), "dd/MM/yyyy"));  
			Query query = createQuery(jpaql.toString(), parametros);
			List<Object[]> detPlanEstudioResulCredito = query.getResultList();
			return detPlanEstudioResulCredito;
		}
		
		@Override
		public List<Object[]> listaControlPagoGrupByFechasAnulados() throws Exception {
			Map<String, Object> parametros = new HashMap<String, Object>();
			StringBuilder jpaql = new StringBuilder();
			jpaql.append("select to_date(to_char(o.fechaPago,'yyyy-MM-dd'),'yyyy-MM-dd') , count(o.fechaPago), to_char(o.fechaPago,'yyyymmdd') ,o.tipoDocSunat.idItem   from ControlPago o ");
			jpaql.append(" where o.envioSunat = 'X' and o.anulado='S' and o.tipoDocSunat.idItem='688' and to_char(o.fechaPago,'dd/MM/yyyy')!=:fecha  and (o.serie = 'B001' or o.serie = 'B002' ) ");
			jpaql.append(" group by to_date(to_char(o.fechaPago,'yyyy-MM-dd'),'yyyy-MM-dd'),to_char(o.fechaPago,'yyyymmdd'),o.tipoDocSunat.idItem  ");
			parametros.put("fecha",FechaUtil.obtenerFechaFormatoPersonalizado(FechaUtil.obtenerFecha(), "dd/MM/yyyy"));  
			Query query = createQuery(jpaql.toString(), parametros);
			List<Object[]> detPlanEstudioResulCredito = query.getResultList();
			return detPlanEstudioResulCredito;
		}
		
		@Override
		public int verificarButtonAnulados() throws Exception {
			int resultado = 0;
			Map<String, Object> parametros = new HashMap<String, Object>();
			StringBuilder jpaql = new StringBuilder();
			jpaql.append("select  count(o.idControlPago) from ControlPago o ");
			jpaql.append(" where o.anulado = 'S' and to_char(o.fechaPago,'dd/MM/yyyy')!=:fecha "); 
			parametros.put("fecha",FechaUtil.obtenerFechaFormatoPersonalizado(FechaUtil.obtenerFecha(), "dd/MM/yyyy"));  
			Query query = createQuery(jpaql.toString(), parametros);
			resultado = ((Long) query.getSingleResult()).intValue(); 
			return resultado;
		}
	   
		
		
		
		@Override	 
		 public List<Map<String,Object>> generarArchivosPlanosXML(ControlPagoDTO controlp) throws Exception {
			ScriptSqlResulJDBCVO resultadoTemp = obtenerScriptSqlTXT(controlp);
			return resultadoTemp.getListaData();
		}
		
		private ScriptSqlResulJDBCVO obtenerScriptSqlTXT(ControlPagoDTO controlp) throws Exception {
			StringBuilder sql = new StringBuilder();
			Long comparacionSerieB = 688L;
			Long comparacionSerieF = 686L;
			String det ="DET";
			String trd ="TRD";
			String ley ="LEY";
			String tri ="TRI";
			Map<String, Object> parametros = new HashMap<String, Object>();
			StringBuilder filtroDinamic = new StringBuilder(); 
			if(controlp.isEsAnulado()) {
				if(controlp.getTipoDocSunat().getIdItem().equals(comparacionSerieB)) { 
					parametros.put("fecha",FechaUtil.obtenerFechaFormatoPersonalizado(controlp.getFechaPago(), "yyyy-MM-dd")); 
					sql.append(SqlMapingUtil.obtenerSqlSentenciaBuildERP(ConstanteQueryNameUtil.REPORTE_TXT_GENERAR_DRI_ANULADO));  
					sql = new StringBuilder(sql.toString().replace("${filtroDinamic}", filtroDinamic));
				}
			}else {
				/*if(controlp.getTipoDocSunat().getIdItem().equals(comparacionSerieB)) { 
					if(StringUtils.isNullOrEmpty(controlp.getId())) {
						parametros.put("fecha",FechaUtil.obtenerFechaFormatoPersonalizado(controlp.getFechaPago(), "yyyy-MM-dd")); 
						sql.append(SqlMapingUtil.obtenerSqlSentenciaBuildERP(ConstanteQueryNameUtil.REPORTE_TXT_GENERAR_DRI));  
						sql = new StringBuilder(sql.toString().replace("${filtroDinamic}", filtroDinamic));
						}else if(controlp.getId().equals(trd)) {
							parametros.put("fecha",FechaUtil.obtenerFechaFormatoPersonalizado(controlp.getFechaPago(), "yyyy-MM-dd")); 
							sql.append(SqlMapingUtil.obtenerSqlSentenciaBuildERP(ConstanteQueryNameUtil.REPORTE_TXT_GENERAR_TRD));  
							sql = new StringBuilder(sql.toString().replace("${filtroDinamic}", filtroDinamic));
						}
				}else*/
				if(!StringUtils.isNullOrEmpty(controlp.getTipo())) { 
					if(controlp.getTipoDocSunat().getIdItem().equals(comparacionSerieB)) { 
						parametros.put("idVentaB", controlp.getIdControlPago()); 
						sql.append(SqlMapingUtil.obtenerSqlSentenciaBuildERP(ConstanteQueryNameUtil.REPORTE_TXT_GENERAR_B));  
						sql = new StringBuilder(sql.toString().replace("${filtroDinamic}", filtroDinamic));	 
					}else if(controlp.getTipoDocSunat().getIdItem().equals(comparacionSerieF)) { 
						parametros.put("idVentaF", controlp.getIdControlPago()); 
						sql.append(SqlMapingUtil.obtenerSqlSentenciaBuildERP(ConstanteQueryNameUtil.REPORTE_TXT_GENERAR_F));  
						sql = new StringBuilder(sql.toString().replace("${filtroDinamic}", filtroDinamic));	 
					}

				}else if(controlp.getDet().equals(det)) {
					parametros.put("idVentaDet", controlp.getIdControlPago()); 
					sql.append(SqlMapingUtil.obtenerSqlSentenciaBuildERP(ConstanteQueryNameUtil.REPORTE_TXT_GENERAR_DET));  
					sql = new StringBuilder(sql.toString().replace("${filtroDinamic}", filtroDinamic));
				}else if(controlp.getTri().equals(tri)) {
					parametros.put("idVentaTri", controlp.getIdControlPago()); 
					sql.append(SqlMapingUtil.obtenerSqlSentenciaBuildERP(ConstanteQueryNameUtil.REPORTE_TXT_GENERAR_TRI));  
					sql = new StringBuilder(sql.toString().replace("${filtroDinamic}", filtroDinamic));
				}else if(controlp.getLey().equals(ley)) {
					parametros.put("idVentaLey", controlp.getIdControlPago()); 
					parametros.put("montoLetra", controlp.getMontoletra());
					sql.append(SqlMapingUtil.obtenerSqlSentenciaBuildERP(ConstanteQueryNameUtil.REPORTE_TXT_GENERAR_LEY));  
					sql = new StringBuilder(sql.toString().replace("${filtroDinamic}", filtroDinamic));
				} 	
			}
			return GenericJDBC.executeQuery(sql, parametros);
		}
		
		@Override
		public List<ControlPago> listaVentaExtracionF() throws Exception {
			Map<String, Object> parametros = new HashMap<String, Object>();
			boolean ejecutarQuery = true;
			StringBuilder jpaql = new StringBuilder();
			jpaql.append("from ControlPago o  left join fetch o.tipoDocSunat ");
			jpaql.append("left join fetch o.itemByTipoMoneda left join fetch o.cliente  where o.envioSunat ='' and (o.tipoDocSunat.idItem='686' or o.tipoDocSunat.idItem='688') and to_char(o.fechaPago,'dd/MM/yyyy')!=:fecha   and (o.serie = 'F001' or o.serie = 'F002' or o.serie = 'B001' or o.serie = 'B002' )");
			parametros.put("fecha",FechaUtil.obtenerFechaFormatoPersonalizado(FechaUtil.obtenerFecha(), "dd/MM/yyyy")); 
			//jpaql.append(" order by cl.escuela.idEscuela ");
			if (ejecutarQuery) {		
				Query query = createQuery(jpaql.toString(), parametros);
				return query.getResultList();
			} 
			return null;
		}
		
		@Override
		public void updateVentaExtracion() throws Exception { 
			StringBuilder jpaql = new StringBuilder();
	        jpaql.append(" UPDATE dbo.ControlPago  SET envioSunat='X'  where envioSunat ='' ");
	        jpaql.append(" and to_char(fechaPago,'dd/MM/yyyy') !=:fechaAc and idtipodocsunat is not null ");
			 Query query = createNativeQuery(jpaql.toString(),null);
			 query.setParameter("fechaAc", FechaUtil.obtenerFechaFormatoPersonalizado(FechaUtil.obtenerFecha(), "dd/MM/yyyy"));
			 query.executeUpdate();	
		}
		
		@Override
		public void updateVentaExtracionAnulados() throws Exception { 
			StringBuilder jpaql = new StringBuilder();
	        jpaql.append(" UPDATE dbo.ControlPago  SET anulado='SE'  where anulado ='S' ");
	        jpaql.append(" and to_char(fechaPago,'dd/MM/yyyy') !=:fechaAc and idtipodocsunat is not null ");
			 Query query = createNativeQuery(jpaql.toString(),null);
			 query.setParameter("fechaAc", FechaUtil.obtenerFechaFormatoPersonalizado(FechaUtil.obtenerFecha(), "dd/MM/yyyy"));
			 query.executeUpdate();	
		}
		
		
	@Override
	public List<ControlPago> obtenerListaCajaFiltroPago(VentaFiltroVO filtro) throws Exception {
		Map<String, Object> parametros = new HashMap<String, Object>();
		boolean ejecutarQuery = true;
		StringBuilder jpaql = new StringBuilder(); 
        jpaql.append(" from ControlPago o  left join fetch o.tipoDocSunat ");
        jpaql.append(" left join fetch o.itemByTipoMoneda left join fetch o.anio  "); 
        jpaql.append(" left join fetch o.asociado  where 1=1 ");
         
			if (StringUtils.isNotNullOrBlank(filtro.getIdUsuario())) {
				jpaql.append("  and to_char(o.fechaPago,'dd/MM/yyyy')=:idfechaVenta ");
				parametros.put("idfechaVenta", FechaUtil.obtenerFechaFormatoPersonalizado(FechaUtil.obtenerFecha(), "dd/MM/yyyy") );
				
				jpaql.append("  and o.usuarioCreacion=:idUsuario and o.estadoPago is null");
				parametros.put("idUsuario", filtro.getIdUsuario());
				ejecutarQuery = true;	
			}

     
			if (!StringUtils.isNullOrEmpty(filtro.getSearch())) {
		          jpaql.append(" and (upper(o.idVenta) like :search or upper(o.nroDoc) like :search or upper(o.asociado.nombre) like :search or upper(o.asociado.nrodoc) like :search) ");
		          parametros.put("search", "%" + filtro.getSearch().toUpperCase() + "%");
				ejecutarQuery = true;	
			}
		 
		jpaql.append(" order by o.fechaCreacion desc");
		if (ejecutarQuery) {		
			Query query = createQuery(jpaql.toString(), parametros);
			return query.getResultList();
		} 
		return null;
	}
	
	
	
	@Override
	public void updateControlPagoCierre(String objeto,String idUsuario,Date fecha) throws Exception {
		 Map<String, Object> parametros = new HashMap<String, Object>(); 
		 parametros.put("objeto",objeto);
		 parametros.put("idUsuario", idUsuario);
		 parametros.put("fecha", FechaUtil.obtenerFechaFormatoPersonalizado(fecha, "dd/MM/yyyy"));
		 Query query = createNativeQuery("UPDATE empresa.ControlPago  SET estadoPago=:objeto  where estadoPago is null and usuariocreacion=:idUsuario and to_char(fechaPago,'dd/MM/yyyy')=:fecha ",parametros);
		 query.executeUpdate();	
	}
	
}