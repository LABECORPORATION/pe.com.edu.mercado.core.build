package pe.com.builderp.core.facturacion.ejb.dao.compra.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pe.com.builderp.core.facturacion.ejb.dao.compra.local.CompraDaoLocal;
import pe.com.builderp.core.facturacion.model.dto.compra.CompraDTO;
import pe.com.builderp.core.facturacion.model.jpa.compra.Compra;
import pe.com.builderp.core.facturacion.model.vo.compra.RegistroCompraVO;
import pe.com.edu.siaa.core.ejb.dao.generic.impl.GenericFacturacionDAOImpl;
import pe.com.edu.siaa.core.ejb.factory.ConstanteQueryNameUtil;
import pe.com.edu.siaa.core.ejb.factory.SqlMapingUtil;
import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.jdbc.generic.GenericJDBC;
import pe.com.edu.siaa.core.ejb.service.util.FechaUtil;
import pe.com.edu.siaa.core.ejb.util.jms.UUIDUtil;
import pe.com.edu.siaa.core.model.jdbc.vo.ScriptSqlResulJDBCVO;
import pe.com.edu.siaa.core.model.util.StringUtils;

/**
 * La Class CompraDaoImpl.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu Dec 28 12:32:53 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
public class CompraDaoImpl extends  GenericFacturacionDAOImpl<String, Compra> implements CompraDaoLocal  {
	
	 @Override	 
	 public List<RegistroCompraVO> listarCompraReporte(Map<String,Object> parametroFiltroMap) throws Exception {
		List<RegistroCompraVO> resultado = new ArrayList<RegistroCompraVO>();
		Map<String,String> formatoFechaMap = new HashMap<String,String>();
		formatoFechaMap.put("fechaEmision", FechaUtil.DATE_DMY);
		formatoFechaMap.put("fechaVencimientoOPago", FechaUtil.DATE_DMY);
		formatoFechaMap.put("fehaRefComprobanteDocModifica", FechaUtil.DATE_DMY);
		ScriptSqlResulJDBCVO resultadoTemp = obtenerScriptSql(parametroFiltroMap,false);
		resultado = TransferDataObjectUtil.transferObjetoEntityListVO(resultadoTemp, RegistroCompraVO.class, formatoFechaMap);
		return resultado;
			
	}
		 
	private ScriptSqlResulJDBCVO obtenerScriptSql(Map<String,Object> parametroFiltroMap, boolean isContador) throws Exception {
		StringBuilder sql = new StringBuilder();
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.putAll(parametroFiltroMap);
		StringBuilder filtroDinamic = new StringBuilder();
		if (!isContador) {
			sql.append(SqlMapingUtil.obtenerSqlSentenciaBuildERP(ConstanteQueryNameUtil.REPORTE_XLSX_OBTENER_REGISTRO_COMPRA)); 
		 } else {
			sql.append(SqlMapingUtil.obtenerSqlSentenciaBuildERP(ConstanteQueryNameUtil.REPORTE_XLSX_CONTAR_OBTENER_REGISTRO_COMPRA));
		}
		sql = new StringBuilder(sql.toString().replace("${filtroDinamic}", filtroDinamic));
		return GenericJDBC.executeQuery(sql, parametros);
	}
		 
	@Override	 
	public  int contarListarCompraReporte(Map<String,Object> parametroFiltroMap) {
		int resultado = 0;
		try {
			resultado = Integer.parseInt(obtenerScriptSql(parametroFiltroMap,true).getListaData().get(0).get("CONTADOR") + "");
		} catch (Exception e) {
			resultado = 0;
		}
		return resultado;	 
	}

    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.compra.local.CompraDaoLocal#listarCompra(pe.com.builderp.core.facturacion.model.jpa.compra.Compra)
     */  
    @Override	 
    public List<Compra> listarCompra(CompraDTO compra) {
        Query query = generarQueryListaCompra(compra, false);
        query.setFirstResult(compra.getStartRow());
        query.setMaxResults(compra.getOffset());
        return query.getResultList();
    }   
   
    /**
     * Generar query lista Compra.
     *
     * @param CompraDTO el compra
     * @param esContador el es contador
     * @return the query
     */
    private Query generarQueryListaCompra(CompraDTO compra, boolean esContador) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        StringBuilder jpaql = new StringBuilder();
        if (esContador) {
            jpaql.append(" select count(o.idCompra) from Compra o where 1=1 ");
        } else {
        	 jpaql.append(" select o from Compra o  left join fetch o.tipoDocSunat ");
             jpaql.append(" left join fetch o.itemByTipoMoneda left join fetch o.ordenCompra  "); 
             jpaql.append(" left join fetch o.proveedor  where 1=1 ");      
        }
		if (!StringUtils.isNullOrEmpty(compra.getSearch())) {
	          jpaql.append(" and upper(o.idCompra) like :search ");
	          parametros.put("search", "%" + compra.getSearch().toUpperCase() + "%");
	    } else {
			if (!StringUtils.isNullOrEmptyNumeric(compra.getTipoCambio())) {
				jpaql.append(" and o.tipoCambio = :tipoCambio ");
				parametros.put("tipoCambio", compra.getTipoCambio());
			}
			if (!StringUtils.isNullOrEmpty(compra.getNroDoc())) {
				jpaql.append(" and upper(o.nroDoc) like :nroDoc ");
				parametros.put("nroDoc", "%" + compra.getNroDoc().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmptyNumeric(compra.getProcentajeIgv())) {
				jpaql.append(" and o.procentajeIgv = :procentajeIgv ");
				parametros.put("procentajeIgv", compra.getProcentajeIgv());
			}
			if (!StringUtils.isNullOrEmptyNumeric(compra.getIgv())) {
				jpaql.append(" and o.igv = :igv ");
				parametros.put("igv", compra.getIgv());
			}
			if (!StringUtils.isNullOrEmptyNumeric(compra.getDescuentoTotal())) {
				jpaql.append(" and o.descuentoTotal = :descuentoTotal ");
				parametros.put("descuentoTotal", compra.getDescuentoTotal());
			}
			if (!StringUtils.isNullOrEmptyNumeric(compra.getSubMontoTotal())) {
				jpaql.append(" and o.subMontoTotal = :subMontoTotal ");
				parametros.put("subMontoTotal", compra.getSubMontoTotal());
			}
			if (!StringUtils.isNullOrEmptyNumeric(compra.getMontoTotal())) {
				jpaql.append(" and o.montoTotal = :montoTotal ");
				parametros.put("montoTotal", compra.getMontoTotal());
			}
			if (!StringUtils.isNullOrEmpty(compra.getFechaCompra())) {
				jpaql.append(" and o.fechaCompra = :fechaCompra ");
				parametros.put("fechaCompra", compra.getFechaCompra());
			}
			if (!StringUtils.isNullOrEmptyNumeric(compra.getNroCorrelativoOperacion())) {
				jpaql.append(" and o.nroCorrelativoOperacion = :nroCorrelativoOperacion ");
				parametros.put("nroCorrelativoOperacion", compra.getNroCorrelativoOperacion());
			}
			if (!StringUtils.isNullOrEmpty(compra.getFechaCreacion())) {
				jpaql.append(" and o.fechaCreacion = :fechaCreacion ");
				parametros.put("fechaCreacion", compra.getFechaCreacion());
			}
			if (!StringUtils.isNullOrEmpty(compra.getUsuarioCreacion())) {
				jpaql.append(" and upper(o.usuarioCreacion) like :usuarioCreacion ");
				parametros.put("usuarioCreacion", "%" + compra.getUsuarioCreacion().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(compra.getFechaModificacion())) {
				jpaql.append(" and o.fechaModificacion = :fechaModificacion ");
				parametros.put("fechaModificacion", compra.getFechaModificacion());
			}
			if (!StringUtils.isNullOrEmpty(compra.getUsuarioModificacion())) {
				jpaql.append(" and upper(o.usuarioModificacion) like :usuarioModificacion ");
				parametros.put("usuarioModificacion", "%" + compra.getUsuarioModificacion().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(compra.getEstado())) {
				jpaql.append(" and upper(o.estado) like :estado ");
				parametros.put("estado", "%" + compra.getEstado().toUpperCase() + "%");
			}
			if (!StringUtils.isNullOrEmpty(compra.getIpAcceso())) {
				jpaql.append(" and upper(o.ipAcceso) like :ipAcceso ");
				parametros.put("ipAcceso", "%" + compra.getIpAcceso().toUpperCase() + "%");
			}
		}
        if (!esContador) {
            //jpaql.append(" ORDER BY 1 ");
        }
        Query query = createQuery(jpaql.toString(), parametros);
        return query;
    }

    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.compra.local.CompraDaoLocal#contarListar{entity.getClassName()}(pe.com.builderp.core.facturacion.model.jpa.compra.CompraDTO)
     */
	@Override
    public int contarListarCompra(CompraDTO compra) {
        int resultado = 0;
        try {
            //StringBuilder jpaql = new StringBuilder();
            Query query = generarQueryListaCompra(compra, true);
            resultado = ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            resultado = 0;
        }
        return resultado;
    }
    /* (non-Javadoc)
     * @see pe.com.builderp.core.facturacion.ejb.dao.compra.local.CompraDaoLocal#generarIdCompra()
     */
	 @Override
    public String generarIdCompra() {
		 return UUIDUtil.generarElementUUID();
    }
	 
	 @Override	 
	 public List<Compra> listarCompraTemp(String idOrdenCompra) {
	    	Map<String, Object> parametros = new HashMap<String, Object>();
			boolean ejecutarQuery = false;
			StringBuilder jpaql = new StringBuilder();
			jpaql.append("from Compra drn where 1 = 1 ");
			
			if (StringUtils.isNotNullOrBlank(idOrdenCompra)) {
				ejecutarQuery = true;
				jpaql.append(" and drn.ordenCompra.idOrdenCompra =:idOrdenCompra  ");
				parametros.put("idOrdenCompra", idOrdenCompra);
			}
			if (ejecutarQuery) {		
				Query query = createQuery(jpaql.toString(), parametros);
				return query.getResultList();
			} 
			return null;
	 }
   
}