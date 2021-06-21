package pe.com.builderp.core.facturacion.ejb.dao.venta.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;
import pe.com.edu.siaa.core.ejb.util.log.Logger;


import pe.com.builderp.core.contabilidad.model.vo.RegistroAsientoFiltroVO;
import pe.com.builderp.core.facturacion.ejb.dao.venta.local.VentaDaoLocal;
import pe.com.builderp.core.facturacion.model.dto.venta.VentaDTO;
import pe.com.builderp.core.facturacion.model.jpa.compra.Compra;
import pe.com.builderp.core.facturacion.model.jpa.venta.DetalleVenta;
import pe.com.builderp.core.facturacion.model.jpa.venta.Venta;
import pe.com.builderp.core.facturacion.model.vo.venta.RegistroVentaVO;
import pe.com.builderp.core.facturacion.model.vo.venta.TendenciasVO;
import pe.com.builderp.core.facturacion.model.vo.venta.VentaGraficoVO;
import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericFacturacionDAOImpl;
import pe.com.edu.siaa.core.ejb.factory.ConstanteQueryNameUtil;
import pe.com.edu.siaa.core.ejb.factory.SqlMapingUtil;
import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.jdbc.generic.GenericJDBC;
import pe.com.edu.siaa.core.ejb.service.util.FechaUtil;
import pe.com.edu.siaa.core.ejb.util.jms.UUIDUtil;
import pe.com.edu.siaa.core.model.estate.EstadoGeneralState;
import pe.com.edu.siaa.core.model.jdbc.vo.ScriptSqlResulJDBCVO;
import pe.com.edu.siaa.core.model.util.ObjectUtil;
import pe.com.edu.siaa.core.model.util.StringUtils;
import pe.com.edu.siaa.core.model.vo.VentaFiltroVO;

/**
 * La Class VentaDaoImpl.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu Dec 28 12:32:59 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
public class VentaDaoImpl extends  GenericFacturacionDAOImpl<String, Venta> implements VentaDaoLocal  {

	private Logger log = Logger.getLogger(VentaDaoImpl.class);
	
	@Override	 
	 public List<Map<String,Object>> listarVentaReporteTXT(RegistroAsientoFiltroVO registroVentaFiltroVO) throws Exception {
		Map<String,String> formatoFechaMap = new HashMap<String,String>();
		formatoFechaMap.put("fechaEmision", FechaUtil.DATE_DMY);
		formatoFechaMap.put("fechaVencimientoOPago", FechaUtil.DATE_DMY);
		formatoFechaMap.put("fehaRefComprobanteDocModifica", FechaUtil.DATE_DMY);
		ScriptSqlResulJDBCVO resultadoTemp = obtenerScriptSqlTXT(registroVentaFiltroVO,false);
		return resultadoTemp.getListaData();
	}
		 
	@Override	 
	public  int contarListarVentaReporteTXT(RegistroAsientoFiltroVO registroVentaFiltroVO) {
		int resultado = 0;
		try {
			resultado = Integer.parseInt(obtenerScriptSqlTXT(registroVentaFiltroVO,true).getListaData().get(0).get("CONTADOR") + "");
		} catch (Exception e) {
			resultado = 0;
		}
		return resultado;	 
	}
	
	@Override
	   public List<VentaFiltroVO> obtenerIngresoDetalladoMap( Date fechaInicio, Date fechaFin) throws Exception{
		Map<String, Object> parametros = new HashMap<String, Object>();
	    List<VentaFiltroVO>  resultado = new  ArrayList<VentaFiltroVO>();
		StringBuilder jpaql = generarQueryIngresoDetallado();
		parametros.put("fechaInicio",  fechaInicio );
		parametros.put("fechaFin",FechaUtil.sumarDias(fechaFin, 1) );
		Query query = createNativeQuery(jpaql.toString(), parametros); 
		List<Object[]>   listaObject =  query.getResultList();
		resultado = parsearDatosIgresoDetalladoDTO(listaObject);		 
		return resultado;
	}
	
	//agregado	
		private StringBuilder generarQueryIngresoDetallado()  {		
			StringBuilder jpaql = new StringBuilder();		
			jpaql.append(" SELECT  to_char(Venta.fechaventa,'dd/MM/yyyy') as FechaVenta, (case when docSunat.idITem = 686 then '01' when docSunat.idITem = 688 then '03' end) as TipoComprobante,Venta.serie as Serie,Venta.nrodoc as NroBoleta, ");
			jpaql.append(" (case when Cliente.idtipodocumentoidentidad = 20 then '1' when Cliente.idtipodocumentoidentidad = 21 then '4' when Cliente.idtipodocumentoidentidad = 22 then '7' when Cliente.idtipodocumentoidentidad = 911 then '6' end )  as TipoDoc, "); 	
			jpaql.append(" Cliente.nrodoc as documenCliente,Cliente.nombre as cliente  ,Venta.montototal  as Efectivo "); 		
			jpaql.append(" from factu.Venta Venta LEFT JOIN ");
			jpaql.append(" factu.Cliente Cliente on Venta.idCliente = Cliente.idCliente LEFT JOIN ");
			jpaql.append(" commo.item docSunat on  Venta.idtipodocsunat = docSunat.iditem  ");
			 
			jpaql.append("where  Venta.fechaventa  BETWEEN :fechaInicio  AND :fechaFin and (docSunat.idITem = 686 or docSunat.idITem = 688) ");	
			
			jpaql.append(" order by Venta.fechaventa asc ");		
			return jpaql;
		}
	
		private List<VentaFiltroVO> parsearDatosIgresoDetalladoDTO(List<Object[]>   listaObject) {
		 	  List<VentaFiltroVO> resultado = new  ArrayList<VentaFiltroVO>();
		 	  try {
		 		  if (listaObject != null) {
		 				for (Object[] res : listaObject) {
		 					VentaFiltroVO detallePagoDTO  = new VentaFiltroVO();	 	
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
			 					detallePagoDTO.setTipoDoc((String)res[4]);
		 					}
		 					if(res[5] != null) {
			 					detallePagoDTO.setNroDoc((String)res[5]);
		 					}
		 					if(res[6] != null) {
			 					detallePagoDTO.setClienteNombre((String)res[6]);
		 					}
		 					if(res[7] != null) {
		 						/*double doble = Double.parseDouble(res[7].toString());
		 						BigDecimal formatNumber = new BigDecimal(doble);
		 						DecimalFormat formatea = new DecimalFormat("###,###.00");
		 						formatNumber = formatNumber.setScale(2, RoundingMode.DOWN);
		 	 					detallePagoDTO.setMonto(formatea.format(formatNumber));	*/	 
		 	 					detallePagoDTO.setMontoTotal((BigDecimal)res[7]);
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
	 public List<RegistroVentaVO> listarVentaReporte(RegistroAsientoFiltroVO registroVentaFiltroVO) throws Exception {
		List<RegistroVentaVO> resultado = new ArrayList<RegistroVentaVO>();
		Map<String,String> formatoFechaMap = new HashMap<String,String>();
		formatoFechaMap.put("fechaEmision", FechaUtil.DATE_DMY);
		formatoFechaMap.put("fechaVencimientoOPago", FechaUtil.DATE_DMY);
		formatoFechaMap.put("fehaRefComprobanteDocModifica", FechaUtil.DATE_DMY);
		ScriptSqlResulJDBCVO resultadoTemp = obtenerScriptSql(registroVentaFiltroVO,false);
		resultado = TransferDataObjectUtil.transferObjetoEntityListVO(resultadoTemp, RegistroVentaVO.class, formatoFechaMap);
		return resultado;
	}
		 
	@Override	 
	public  int contarListarVentaReporte(RegistroAsientoFiltroVO registroVentaFiltroVO) {
		int resultado = 0;
		try {
			resultado = Integer.parseInt(obtenerScriptSql(registroVentaFiltroVO,true).getListaData().get(0).get("CONTADOR") + "");
		} catch (Exception e) {
			resultado = 0;
		}
		return resultado;	 
	}

	private ScriptSqlResulJDBCVO obtenerScriptSqlTXT(RegistroAsientoFiltroVO registroVentaFiltroVO, boolean isContador) throws Exception {
		StringBuilder sql = new StringBuilder();
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("fechaInicio", registroVentaFiltroVO.getFechaAsientoDesde());
		parametros.put("fechaFin", registroVentaFiltroVO.getFechaAsientoHasta());
		
		parametros.put("ejercicio", registroVentaFiltroVO.getEjercicio());
		parametros.put("periodo", registroVentaFiltroVO.getPeriodo());
		
		parametros.put("idEntidad", registroVentaFiltroVO.getIdEntidad());
		//parametros.putAll(parametroFiltroMap);
		StringBuilder filtroDinamic = new StringBuilder();
		if (!isContador) {
			parametros.put("offset", registroVentaFiltroVO.getOffset());
			parametros.put("startRow", registroVentaFiltroVO.getStartRow());
			sql.append(SqlMapingUtil.obtenerSqlSentenciaBuildERP(ConstanteQueryNameUtil.REPORTE_TXT_OBTENER_REGISTRO_VENTA)); 
		 } else {
			sql.append(SqlMapingUtil.obtenerSqlSentenciaBuildERP(ConstanteQueryNameUtil.REPORTE_TXT_CONTAR_OBTENER_REGISTRO_VENTA));
		}
		sql = new StringBuilder(sql.toString().replace("${filtroDinamic}", filtroDinamic));
		return GenericJDBC.executeQuery(sql, parametros);
	}
	
	private ScriptSqlResulJDBCVO obtenerScriptSql(RegistroAsientoFiltroVO registroVentaFiltroVO, boolean isContador) throws Exception {
		StringBuilder sql = new StringBuilder();
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("idEntidad", registroVentaFiltroVO.getIdEntidad());
		parametros.put("idEnteUniversitaria", ObjectUtil.objectToLong(registroVentaFiltroVO.getCodigoEntidadUniversitaria()));
		
		//parametros.putAll(parametroFiltroMap);
		StringBuilder filtroDinamic = new StringBuilder();
		StringBuilder filtroDinamicUnion = new StringBuilder();
		if (!isContador) {
			parametros.put("offset", registroVentaFiltroVO.getOffset());
			parametros.put("startRow", registroVentaFiltroVO.getStartRow());
			sql.append(SqlMapingUtil.obtenerSqlSentenciaBuildERP(ConstanteQueryNameUtil.REPORTE_XLSX_OBTENER_REGISTRO_VENTA)); 
		 } else {
			sql.append(SqlMapingUtil.obtenerSqlSentenciaBuildERP(ConstanteQueryNameUtil.REPORTE_XLSX_CONTAR_OBTENER_REGISTRO_VENTA));
		}
		if (StringUtils.isNotNullOrBlank(registroVentaFiltroVO.getFechaAsientoDesde())) {
			filtroDinamic.append("  and to_date(to_char( fechaPago,'dd/mm/yyyy'),'dd/mm/yyyy') >= to_date(:fechaInicio,'dd/mm/yyyy') ");
			filtroDinamicUnion.append("  and to_date(to_char( fechaVenta,'dd/mm/yyyy'),'dd/mm/yyyy') >= to_date(:fechaVentaInicio,'dd/mm/yyyy') ");
			parametros.put("fechaInicio", FechaUtil.obtenerFechaFormatoSimple(registroVentaFiltroVO.getFechaAsientoDesde()));
			parametros.put("fechaVentaInicio", FechaUtil.obtenerFechaFormatoSimple(registroVentaFiltroVO.getFechaAsientoDesde()));
		}
        if (StringUtils.isNotNullOrBlank(registroVentaFiltroVO.getFechaAsientoHasta())) {
        	filtroDinamic.append("  and to_date(to_char( fechaPago,'dd/mm/yyyy'),'dd/mm/yyyy') <= to_date(:fechaFin,'dd/mm/yyyy') ");
        	filtroDinamicUnion.append("  and to_date(to_char( fechaVenta,'dd/mm/yyyy'),'dd/mm/yyyy') <= to_date(:fechaVentaFin,'dd/mm/yyyy') ");
			parametros.put("fechaFin", FechaUtil.obtenerFechaFormatoSimple(registroVentaFiltroVO.getFechaAsientoHasta()));
			parametros.put("fechaVentaFin", FechaUtil.obtenerFechaFormatoSimple(registroVentaFiltroVO.getFechaAsientoHasta()));
		}
        if (StringUtils.isNotNullOrBlank(registroVentaFiltroVO.getEjercicio())) {
			filtroDinamic.append("  and to_char( fechaPago,'yyyy') =:ejercicio ");
			filtroDinamicUnion.append(" and to_char( fechaVenta,'yyyy') =:ejercicioVenta ");
			parametros.put("ejercicio", registroVentaFiltroVO.getEjercicio());
			parametros.put("ejercicioVenta", registroVentaFiltroVO.getEjercicio());
		}
        if (StringUtils.isNotNullOrBlank(registroVentaFiltroVO.getPeriodo())) {
			filtroDinamic.append(" and to_char( fechaPago,'mm') =:periodo  ");
			filtroDinamicUnion.append(" and to_char( fechaVenta,'mm') =:periodoVenta ");
			parametros.put("periodo", registroVentaFiltroVO.getPeriodo());
			parametros.put("periodoVenta", registroVentaFiltroVO.getPeriodo());
		}
        filtroDinamicUnion.append(" and cp.idEntidad =:idEntidad "); 
        filtroDinamic.append(" and per.idEntidadUniversitaria =:idEnteUniversitaria "); 
        
        if (StringUtils.isNotNullOrBlank(registroVentaFiltroVO.getIdEntidad())) {
			filtroDinamic.append("");
		}
		sql = new StringBuilder(sql.toString().replace("${filtroDinamic}", filtroDinamic));
		sql = new StringBuilder(sql.toString().replace("${filtroDinamicUnion}", filtroDinamicUnion));
		return GenericJDBC.executeQuery(sql, parametros);
	}
	
    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.VentaDaoLocal#listarVenta(pe.com.builderp.core.facturacion.model.jpa.venta.Venta)
     */  
    @Override	 
    public List<Venta> listarVenta(VentaDTO venta) {
        Query query = generarQueryListaVenta(venta, false);
        query.setFirstResult(venta.getStartRow());
        query.setMaxResults(venta.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista Venta.
     *
     * @param VentaDTO el venta
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaVenta(VentaDTO venta, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idVenta) from Venta o where 1=1 ");
        } else {
            jpaql.append(" select o from Venta o  left join fetch o.tipoDocSunat ");
            jpaql.append(" left join fetch o.itemByTipoMoneda left join fetch o.pedido  "); 
            jpaql.append(" left join fetch o.cliente  where 1=1 ");
        }
      
        if (!StringUtils.isNullOrEmpty(venta.getIdVenta())) {
			jpaql.append(" and o.idVenta =:idVenta ");
			parametros.put("idVenta",venta.getIdVenta());
		} 
        if (!StringUtils.isNullOrEmpty(venta.getId())) {
          jpaql.append(" and o.estado = :estado ");
           parametros.put("estado", venta.getId());
        }
        

		if (!StringUtils.isNullOrEmpty(venta.getSearch())) {
	          jpaql.append(" and upper(o.cliente.nombre || o.cliente.nroDoc || o.nroDoc) like :search ");
	          parametros.put("search", "%" + venta.getSearch().toUpperCase() + "%");
	    } else {
			if (!StringUtils.isNullOrEmptyNumeric(venta.getTipoCambio())) {
				jpaql.append(" and o.tipoCambio = :tipoCambio ");
				parametros.put("tipoCambio", venta.getTipoCambio());
			}
			if (!StringUtils.isNullOrEmpty(venta.getNroDoc())) {
				jpaql.append(" and upper(o.nroDoc) like :nroDoc ");
				parametros.put("nroDoc", "%" + venta.getNroDoc().toUpperCase() + "%");
			}

			if (!StringUtils.isNullOrEmptyNumeric(venta.getIgv())) {
				jpaql.append(" and o.igv = :igv ");
				parametros.put("igv", venta.getIgv());
			}
			if (!StringUtils.isNullOrEmptyNumeric(venta.getDescuentoTotal())) {
				jpaql.append(" and o.descuentoTotal = :descuentoTotal ");
				parametros.put("descuentoTotal", venta.getDescuentoTotal());
			}
			if (!StringUtils.isNullOrEmptyNumeric(venta.getSubMontoTotal())) {
				jpaql.append(" and o.subMontoTotal = :subMontoTotal ");
				parametros.put("subMontoTotal", venta.getSubMontoTotal());
			}
			if (!StringUtils.isNullOrEmptyNumeric(venta.getMontoTotal())) {
				jpaql.append(" and o.montoTotal = :montoTotal ");
				parametros.put("montoTotal", venta.getMontoTotal());
			}
			if (!StringUtils.isNullOrEmpty(venta.getFechaVenta())) {
				jpaql.append(" and o.fechaVenta = :fechaVenta ");
				parametros.put("fechaVenta", venta.getFechaVenta());
			}
			if (!StringUtils.isNullOrEmptyNumeric(venta.getNroCorrelativoOperacion())) {
				jpaql.append(" and o.nroCorrelativoOperacion = :nroCorrelativoOperacion ");
				parametros.put("nroCorrelativoOperacion", venta.getNroCorrelativoOperacion());
			}
			if (!StringUtils.isNullOrEmpty(venta.getFechaCreacion())) {
				jpaql.append(" and o.fechaCreacion = :fechaCreacion ");
				parametros.put("fechaCreacion", venta.getFechaCreacion());
			}
			if (!StringUtils.isNullOrEmpty(venta.getUsuarioCreacion())) {
				jpaql.append(" and upper(o.usuarioCreacion) like :usuarioCreacion ");
				parametros.put("usuarioCreacion", "%" + venta.getUsuarioCreacion().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(venta.getFechaModificacion())) {
				jpaql.append(" and o.fechaModificacion = :fechaModificacion ");
				parametros.put("fechaModificacion", venta.getFechaModificacion());
			}
			if (!StringUtils.isNullOrEmpty(venta.getUsuarioModificacion())) {
				jpaql.append(" and upper(o.usuarioModificacion) like :usuarioModificacion ");
				parametros.put("usuarioModificacion", "%" + venta.getUsuarioModificacion().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(venta.getEstado())) {
				jpaql.append(" and upper(o.estado) like :estado ");
				parametros.put("estado", "%" + venta.getEstado().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(venta.getIpAcceso())) {
				jpaql.append(" and upper(o.ipAcceso) like :ipAcceso ");
				parametros.put("ipAcceso", "%" + venta.getIpAcceso().toUpperCase() + "%");
			}
		}
        if (!esContador) {
            jpaql.append(" ORDER BY  o.fechaCreacion desc ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    
    
    @Override
	public List<VentaGraficoVO> obtenerDataGanancias(String idMes, String idAnho) throws Exception {
		Map<String, Object> parametros = new HashMap<String, Object>();
		List<VentaGraficoVO> resultado = new ArrayList<VentaGraficoVO>();
		boolean ejecutarQuery = false;
		StringBuilder jpaql = new StringBuilder();
		jpaql.append("select distinct to_char(fechaventa,'yyyy') as anho ");		
		if (idAnho != null) {
			jpaql.append("select distinct to_char(fechaventa,'mm') as mes  ");
		}		
		jpaql.append(" from factu.venta  where 1 = 1 ");	
		if (idAnho != null) {
			 jpaql.append(" and extract(year from fechaventa ) = :idAnho");	
		}
		//jpaql.append(" order by drn.detMatricula.matricula.alumno.postulante.persona.apellidoPaterno,drn.detMatricula.matricula.alumno.postulante.persona.apellidoMaterno,drn.detMatricula.matricula.alumno.postulante.persona.nombre ");
		if (ejecutarQuery) {		
			Query query = createQuery(jpaql.toString(), parametros);
			List<Object[]> lisObject =  query.getResultList();
			for (Object[] objects : lisObject) {
				VentaGraficoVO ventaGraf = new  VentaGraficoVO();				
				ventaGraf.setAnho(objects[0].toString());
				resultado.add(ventaGraf);
			}
		} 
		return resultado;
	}
    
	@Override
	public List<VentaGraficoVO> obtenerVentaAnho() throws Exception {
		List<VentaGraficoVO> resultado = new ArrayList<VentaGraficoVO>();
		Map<String, Object> parametros = new HashMap<String, Object>();
	    StringBuilder jpaql = generarQueryObtenerDataVenta();	
		Query query = createNativeQuery(jpaql.toString(), parametros);
		List<Object[]>   listaObject =  query.getResultList();
		for (Object[] objects : listaObject) {			
			VentaGraficoVO ventaGraf = new  VentaGraficoVO();				
			ventaGraf.setAnho(objects[0].toString());
			resultado.add(ventaGraf);
		}
		//resultado = parsearDatosItem(listaObject);
		return resultado;
	}
		
	@Override
	public List<TendenciasVO> obtenerDataTendencias() throws Exception {
		List<TendenciasVO> resultado = new ArrayList<TendenciasVO>();
		Map<String, Object> parametros = new HashMap<String, Object>();
		StringBuilder jpaql = new StringBuilder();
		jpaql.append("select count(*) as num, 'Productos' as nombre,'#5cb85c' as color , 'category' as icon  from factu.producto   ");
		jpaql.append(" union ");
		jpaql.append(" select count(*) as num, 'Clientes' as nombre,'#f4511e' as color, 'assignment_ind' as icon from factu.cliente   ");
		jpaql.append(" union ");
		jpaql.append(" select count(*) as num, 'Proveedores' as nombre,'#5bc0de' as color,'local_shipping' as icon from factu.proveedor   ");
		jpaql.append(" order by nombre ");	  
		Query query = createNativeQuery(jpaql.toString(), parametros);		
		List<Object[]>   listaObject =  query.getResultList();			
		for (Object[] objects : listaObject) {	
			TendenciasVO value = new TendenciasVO();
			value.setCount(objects[0].toString());
			value.setNombre(objects[1].toString());
			value.setColor(objects[2].toString());
			value.setIcon(objects[3].toString());
			resultado.add(value) ;
	    }			
		return resultado;
	}
	
	private StringBuilder generarQueryObtenerDataVenta() {
		StringBuilder jpaql = new StringBuilder();
		jpaql.append("select distinct to_char(fechaventa,'yyyy') as anho , 'año' as descripcion");
		jpaql.append(" from factu.venta  where 1 = 1 ");	
		jpaql.append(" order by anho desc");
		return jpaql;
	}
	
	
	@Override
	public Map<String,List<BigDecimal>> obtenerVentaMes(String idAnho) throws Exception {
		Map<String,List<BigDecimal>> resultado = new HashMap<String,List<BigDecimal>>();
		Map<String, Object> parametros = new HashMap<String, Object>();
		StringBuilder jpaql = new StringBuilder();
		jpaql.append("select  to_char(fechaventa,'mm') as mes, sum(montototal)  ");
		jpaql.append(" from factu.venta  where 1 = 1 ");
		jpaql.append(" and to_char(fechaventa,'yyyy') = :idAnho");
		jpaql.append(" GROUP BY to_char(fechaventa,'mm') ");	
		jpaql.append(" order by mes asc");		
	    parametros.put("idAnho", idAnho);
		Query query = createNativeQuery(jpaql.toString(), parametros);		
		List<Object[]>   listaObject =  query.getResultList();		
		ArrayList<BigDecimal> value = new ArrayList<BigDecimal>();	
	    String [] mes = {"01","02","03","04","05","06","07","08","09","10","11","12"};
	    Map<String, BigDecimal> parametrosMes = new HashMap<String, BigDecimal>();
		for (Object[] objects : listaObject) {	
			parametrosMes.put(objects[0].toString(), (BigDecimal)objects[1]);
	    }
		for(String mesTemp : mes) { 
			if(parametrosMes.containsKey(mesTemp)) {
				value.add(parametrosMes.get(mesTemp)); 
			}else {
				value.add(new BigDecimal("0.0"));
			}
			//System.out.println(objects[0].toString() + " :: " + objects[1]);
		}		
		resultado.put(idAnho, value);
		return resultado;
	}
    
    
    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.VentaDaoLocal#contarListar{entity.getClassName()}(pe.com.builderp.core.facturacion.model.jpa.venta.VentaDTO)
     */
	@Override
    public int contarListarVenta(VentaDTO venta) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaVenta(venta, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.venta.local.VentaDaoLocal#generarIdVenta()
     */
	 @Override
    public String generarIdVenta() {
        return UUIDUtil.generarElementUUID();
    }
	 
	 
		@Override
	    public void updateVentaEstado(String idVenta ) throws Exception {
			Map<String, Object> parametros = new HashMap<String, Object>();
			Query query = createQuery("UPDATE  Venta o set o.estado='A' , o.anulado='S'  where o.idVenta =:idVenta",parametros);
			query.setParameter("idVenta", idVenta);
			query.executeUpdate();
		}
   
	@Override
	public List<Venta> listarVentaReporte(VentaDTO venta) throws Exception {
		Query query = createQuery("from Venta o  left join fetch o.tipoDocSunat left join fetch o.itemByTipoMoneda left join fetch o.pedido left join fetch o.cliente where o.cliente.idCliente = :idCliente and o.nroDoc =:nroDoc",null);
		query.setParameter("nroDoc", venta.getNroDoc());
		query.setParameter("idCliente", venta.getId());
		return query.getResultList();
	}
	
	
	@Override
	public Venta findVenta(String idVenta) throws Exception {
		Venta resultado = null;
		StringBuilder jpaql = new StringBuilder();
		jpaql.append("from Venta o  left join fetch o.tipoDocSunat");
        jpaql.append(" left join fetch o.itemByTipoMoneda left join fetch o.pedido  "); 
        jpaql.append(" left join fetch o.cliente  where o.idVenta=:idVenta "); 
		Query query = createQuery(jpaql.toString(),null);
		query.setParameter("idVenta", idVenta); 
		List<Venta> listaAlumno = query.getResultList();
		if (listaAlumno != null && listaAlumno.size() > 0) {
			resultado = listaAlumno.get(0);
		}
		return resultado;	
	}

	//add fechaVenta
 
	 
	@Override
	public List<Object[]> listaControlPagoGrupByFechas() throws Exception {
		Map<String, Object> parametros = new HashMap<String, Object>();
		StringBuilder jpaql = new StringBuilder();
		jpaql.append("select to_date(to_char(o.fechaVenta,'yyyy-MM-dd'),'yyyy-MM-dd') , count(o.fechaVenta), to_char(o.fechaVenta,'yyyymmdd') ,o.tipoDocSunat.idItem   from Venta o ");
		jpaql.append(" where o.envioSunat !='X' and o.anulado is null and o.tipoDocSunat.idItem='688' and to_char(o.fechaVenta,'dd/MM/yyyy')!=:fecha ");
		jpaql.append(" group by to_date(to_char(o.fechaVenta,'yyyy-MM-dd'),'yyyy-MM-dd'),to_char(o.fechaVenta,'yyyymmdd'),o.tipoDocSunat.idItem  ");
		parametros.put("fecha",FechaUtil.obtenerFechaFormatoPersonalizado(FechaUtil.obtenerFecha(), "dd/MM/yyyy")); 
		//jpaql.append(" order by cl.escuela.idEscuela ");
		Query query = createQuery(jpaql.toString(), parametros);
		List<Object[]> detPlanEstudioResulCredito = query.getResultList();
		return detPlanEstudioResulCredito;
	}
	
	
	@Override	 
	 public List<Map<String,Object>> generarArchivosPlanosXML(VentaDTO controlp) throws Exception {
		ScriptSqlResulJDBCVO resultadoTemp = obtenerScriptSqlTXT(controlp);
		return resultadoTemp.getListaData();
	}
	
	private ScriptSqlResulJDBCVO obtenerScriptSqlTXT(VentaDTO controlp) throws Exception {
		StringBuilder sql = new StringBuilder();
		Long comparacionSerieB = 688L;
		Long comparacionSerieF = 686L;
		String det ="DET";
		String trd ="TRD";
		Map<String, Object> parametros = new HashMap<String, Object>();
		StringBuilder filtroDinamic = new StringBuilder(); 
		if(controlp.getTipoDocSunat().getIdItem().equals(comparacionSerieB)) { 
			if(StringUtils.isNullOrEmpty(controlp.getId())) {
			parametros.put("fecha",FechaUtil.obtenerFechaFormatoPersonalizado(controlp.getFechaVenta(), "yyyy-MM-dd")); 
			sql.append(SqlMapingUtil.obtenerSqlSentenciaBuildERP(ConstanteQueryNameUtil.REPORTE_TXT_GENERAR_DRI));  
			sql = new StringBuilder(sql.toString().replace("${filtroDinamic}", filtroDinamic));
			}else if(controlp.getId().equals(trd)) {
				parametros.put("fecha",FechaUtil.obtenerFechaFormatoPersonalizado(controlp.getFechaVenta(), "yyyy-MM-dd")); 
				sql.append(SqlMapingUtil.obtenerSqlSentenciaBuildERP(ConstanteQueryNameUtil.REPORTE_TXT_GENERAR_TRD));  
				sql = new StringBuilder(sql.toString().replace("${filtroDinamic}", filtroDinamic));
			}
		}else if(StringUtils.isNullOrEmpty(controlp.getId())) {
			if(controlp.getTipoDocSunat().getIdItem().equals(comparacionSerieF)) {
				parametros.put("idVenta", controlp.getIdVenta()); 
				sql.append(SqlMapingUtil.obtenerSqlSentenciaBuildERP(ConstanteQueryNameUtil.REPORTE_TXT_GENERAR_F));  
				sql = new StringBuilder(sql.toString().replace("${filtroDinamic}", filtroDinamic));	
			}

		}else if(controlp.getId().equals(det)) {
			parametros.put("idVenta2", controlp.getIdVenta()); 
			sql.append(SqlMapingUtil.obtenerSqlSentenciaBuildERP(ConstanteQueryNameUtil.REPORTE_TXT_GENERAR_DET));  
			sql = new StringBuilder(sql.toString().replace("${filtroDinamic}", filtroDinamic));
		}else {
			parametros.put("idVenta", controlp.getIdVenta()); 
			sql.append(SqlMapingUtil.obtenerSqlSentenciaBuildERP(ConstanteQueryNameUtil.REPORTE_TXT_GENERAR_TRI));  
			sql = new StringBuilder(sql.toString().replace("${filtroDinamic}", filtroDinamic));
		}
		
		return GenericJDBC.executeQuery(sql, parametros);
	}

	@Override
	public List<Venta> listaVentaExtracionF() throws Exception {
		Map<String, Object> parametros = new HashMap<String, Object>();
		boolean ejecutarQuery = true;
		StringBuilder jpaql = new StringBuilder();
		jpaql.append("from Venta o  left join fetch o.tipoDocSunat ");
		jpaql.append("left join fetch o.itemByTipoMoneda left join fetch o.cliente  where o.envioSunat !='X' and o.anulado is null  and o.tipoDocSunat.idItem='686' and to_char(o.fechaVenta,'dd/MM/yyyy')!=:fecha  ");
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
	     jpaql.append(" UPDATE factu.venta  SET envioSunat='X'  where envioSunat !='X' ");
        jpaql.append(" and to_char(fechaVenta,'dd/MM/yyyy') !=:fechaAc and idtipodocsunat is not null ");
		 Query query = createNativeQuery(jpaql.toString(),null);
		 query.setParameter("fechaAc", FechaUtil.obtenerFechaFormatoPersonalizado(FechaUtil.obtenerFecha(), "dd/MM/yyyy"));
		 query.executeUpdate();	
	}
	
	@Override
	public void updateVentaCierre(String objeto,String idUsuario,Date fecha) throws Exception {
		 Map<String, Object> parametros = new HashMap<String, Object>(); 
		 parametros.put("objeto",objeto);
		 parametros.put("idUsuario", idUsuario);
		 parametros.put("fecha", FechaUtil.obtenerFechaFormatoPersonalizado(fecha, "dd/MM/yyyy"));
		 Query query = createNativeQuery("UPDATE factu.venta  SET estadoventa=:objeto  where estadoventa='V' and usuariocreacion=:idUsuario and to_char(fechaVenta,'dd/MM/yyyy')=:fecha ",parametros);
		 query.executeUpdate();	
	}
	
	@Override
	public List<Venta> obtenerListaCajaFiltro(VentaFiltroVO filtro) throws Exception {
		Map<String, Object> parametros = new HashMap<String, Object>();
		boolean ejecutarQuery = true;
		StringBuilder jpaql = new StringBuilder(); 
        jpaql.append(" from Venta o  left join fetch o.tipoDocSunat ");
        jpaql.append(" left join fetch o.itemByTipoMoneda left join fetch o.pedido  "); 
        jpaql.append(" left join fetch o.cliente  where 1=1 ");
         
			if (StringUtils.isNotNullOrBlank(filtro.getIdUsuario())) {
				jpaql.append("  and to_char(o.fechaVenta,'dd/MM/yyyy')=:idfechaVenta ");
				parametros.put("idfechaVenta", FechaUtil.obtenerFechaFormatoPersonalizado(FechaUtil.obtenerFecha(), "dd/MM/yyyy") );
				
				jpaql.append("  and o.usuarioCreacion=:idUsuario and o.estadoVenta='V'");
				parametros.put("idUsuario", filtro.getIdUsuario());
				ejecutarQuery = true;	
			}

     
			if (!StringUtils.isNullOrEmpty(filtro.getSearch())) {
		          jpaql.append(" and (upper(o.idVenta) like :search or upper(o.nroDoc) like :search or upper(o.cliente.nombre) like :search or upper(o.cliente.nroDoc) like :search) ");
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
	 public List<Venta> listarVentaPedidoTemp(String idPedido) {
	    	Map<String, Object> parametros = new HashMap<String, Object>();
			boolean ejecutarQuery = false;
			StringBuilder jpaql = new StringBuilder();
			jpaql.append("from Venta drn where 1 = 1 ");
			
			if (StringUtils.isNotNullOrBlank(idPedido)) {
				ejecutarQuery = true;
				jpaql.append(" and drn.pedido.idPedido =:idPedido  ");
				parametros.put("idPedido", idPedido);
			}
			if (ejecutarQuery) {		
				Query query = createQuery(jpaql.toString(), parametros);
				return query.getResultList();
			} 
			return null;
	 }
	
}