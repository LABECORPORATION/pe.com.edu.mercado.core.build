package pe.com.edu.siaa.core.ejb.service.reporte.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.com.edu.siaa.core.ejb.dao.reporte.local.ParametrosScriptSqlDaoLocal;
import pe.com.edu.siaa.core.ejb.dao.reporte.local.ScriptSqlDaoLocal;
import pe.com.edu.siaa.core.ejb.factory.CollectionUtil;
import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.jdbc.generic.GenericJDBC;
import pe.com.edu.siaa.core.ejb.service.reporte.local.ReporteServiceLocal;
import pe.com.edu.siaa.core.ejb.util.cache.CabeceraCacheUtil;
import pe.com.edu.siaa.core.ejb.util.cache.SessionUtil;
import pe.com.edu.siaa.core.ejb.util.excel.DataExportExcelPersonalizadoUtil;
import pe.com.edu.siaa.core.ejb.util.jms.UUIDUtil;
import pe.com.edu.siaa.core.model.dto.reporte.ParametrosScriptSqlDTO;
import pe.com.edu.siaa.core.model.dto.reporte.ScriptSqlDTO;
import pe.com.edu.siaa.core.model.jdbc.vo.ScriptSqlResulJDBCVO;
import pe.com.edu.siaa.core.model.jpa.reporte.ParametrosScriptSql;
import pe.com.edu.siaa.core.model.jpa.reporte.ScriptSql;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.model.type.TipoReporteGenerarType;
import pe.com.edu.siaa.core.model.vo.FileVO;
import pe.com.edu.siaa.core.ui.paginator.IDataProvider;
import pe.com.edu.siaa.core.ui.paginator.LazyLoadingList;


/**
 * La Class ReporteServiceImpl.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 13:43:23 COT 2017
 * @since SIAA-CORE 2.1
 */
 @Stateless
 @EJB(name = "java:app/ReporteService", beanInterface = ReporteServiceLocal.class)
public class ReporteServiceImpl implements ReporteServiceLocal {
	
	/** El servicio script sql dao impl. */
	@EJB
	private ScriptSqlDaoLocal scriptSqlDaoImpl; 
	
	
	/** El servicio parametros script sql dao impl. */
	@EJB
	private ParametrosScriptSqlDaoLocal parametrosScriptSqlDaoImpl; 
	

	@Override
	public String generarReporteScript(ScriptSqlDTO scriptSql,String userName) throws Exception {
	  String resultado =  UUIDUtil.generarElementUUID();
	    int cantidadPagina = 5000;
		Map<String, Object> propiedadesMap = new HashMap<String, Object>();
		String nombreArchivo = resultado;
		propiedadesMap.put("calcularWitchDemanda", "true");
		CabeceraCacheUtil.getInstance();
		String titulo = "Data";
		List<Map<String,Object>> listaDataMap = new ArrayList<Map<String,Object>>();
		listaDataMap = buscarPaginadoScript(listaDataMap, cantidadPagina, scriptSql);
		listaDataMap.size();
		List<String> cabecerasRed = CabeceraCacheUtil.getInstance().get("HEADER" + scriptSql.getIdScriptSql() );
		DataExportExcelPersonalizadoUtil.generarExcelXLSXMap(cabecerasRed, new HashMap<String,String>(), listaDataMap, nombreArchivo, titulo, propiedadesMap, null);
		generarExcelXLSXViewMap(nombreArchivo);
		
      return resultado;
	}
	public static String generarExcelXLSXViewMap( String archivoName) {
		 FileVO objeto = new FileVO();
		 String nombre = archivoName + ".xlsx";
		 objeto.setName(archivoName + ".xlsx");
		 objeto.setDataBig(nombre);
		 objeto.setMime(TipoReporteGenerarType.XLSX.getContentType());
		 //objeto.setLength(byteTemp.length);
		 SessionUtil.pasarParametro(archivoName, objeto);
		 return archivoName;
	}
	private List<Map<String,Object>> buscarPaginadoScript(List<Map<String,Object>> listaRedSalud, int cantidadPagina, final ScriptSqlDTO scriptSql) {
		IDataProvider<Map<String,Object>> dataProvider = new IDataProvider<Map<String,Object>>() {
			private int total = 0;
			private int cuenta = 0;
			@Override
			public List<Map<String,Object>> getBufferedData(int startRow, int offset) {
				List<Map<String,Object>> lista = new ArrayList<Map<String,Object>>();
				scriptSql.setStartRow(startRow);
				scriptSql.setOffset(offset);
				try {
					ScriptSqlResulJDBCVO resultado = obtenerScriptSql(scriptSql,false);
					lista = resultado.getListaData();
					CabeceraCacheUtil.getInstance().put("HEADER" + scriptSql.getIdScriptSql(), resultado.getListaHeader());
				} catch (Exception e) {
					lista = new ArrayList<Map<String,Object>>();
				}
				return lista;
			}

			@Override
			public int getTotalResultsNumber() {
				if (total == 0 && cuenta == 0) {
					try {
						if (CabeceraCacheUtil.getInstance().get("HEADER" + scriptSql.getIdScriptSql()).size() == 0) {
							scriptSql.setStartRow(0);
							scriptSql.setOffset(1);
							ScriptSqlResulJDBCVO resultado = obtenerScriptSql(scriptSql,false);
							CabeceraCacheUtil.getInstance().put("HEADER" + scriptSql.getIdScriptSql(), resultado.getListaHeader());
						}
						total = Integer.parseInt(obtenerScriptSql(scriptSql,true).getListaData().get(0).get("CONTADOR") + "");
					} catch (Exception e) {
						e.printStackTrace();
					}					
					cuenta++;
				}
				return total;
			}
		};
		listaRedSalud = new LazyLoadingList<Map<String,Object>>(dataProvider, cantidadPagina);
		return listaRedSalud;
	}
	
	private ScriptSqlResulJDBCVO obtenerScriptSql(ScriptSqlDTO scriptSql, boolean isContador) throws Exception {
		StringBuilder sql = new StringBuilder();
		Map<String, Object> parametros = new HashMap<String, Object>();
		if (!isContador) {
			sql.append(scriptSql.getSql());
			sql.append(" limit :offset OFFSET :startRow ");
		} else {
			sql.append("select count(1) as CONTADOR  from ( " + scriptSql.getSql() +") a where 1 = 1");
		}
		if (!CollectionUtil.isEmpty(scriptSql.getScriptSqlParametrosScriptSqlList())) {
			for (ParametrosScriptSqlDTO objParam : scriptSql.getScriptSqlParametrosScriptSqlList()) {
				parametros.put(objParam.getCodigoParametro(), objParam.getValorDefecto());
			}
		}
		//inicio temporal
		String JNDIConexion = null;
		if (scriptSql.getDescripcion() != null) {
			String[] JNDINameArray = scriptSql.getDescripcion().split("-",-1);
			if (JNDINameArray != null && scriptSql.getDescripcion().contains("-")) {
				JNDIConexion = JNDINameArray[0];
			}
		}
		//Fin tempora
		if (!isContador) {
			parametros.put("offset", scriptSql.getOffset());
			parametros.put("startRow", scriptSql.getStartRow());
		}		
		return GenericJDBC.executeQuery(sql, parametros,JNDIConexion);
	}
	@Override
	public ScriptSqlDTO controladorAccionScriptSql(ScriptSqlDTO scriptSql, AccionType accionType) throws Exception {
		ScriptSqlDTO resultado = null;
		ScriptSql resultadoEntity = null;
		switch (accionType) {
			case CREAR:
				scriptSql.setIdScriptSql(this.scriptSqlDaoImpl.generarIdScriptSql());
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(scriptSql, ScriptSql.class);
				this.scriptSqlDaoImpl.save(resultadoEntity);	
				resultado = scriptSql;
				break;				
			case MODIFICAR:
			    resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(scriptSql, ScriptSql.class);
				this.scriptSqlDaoImpl.update(resultadoEntity);
				resultado = scriptSql;	
				break;
				
			case ELIMINAR:
				resultadoEntity = this.scriptSqlDaoImpl.find(ScriptSql.class, scriptSql.getIdScriptSql());
				this.scriptSqlDaoImpl.delete(resultadoEntity);
				resultado = scriptSql;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.scriptSqlDaoImpl.find(ScriptSql.class, scriptSql.getIdScriptSql());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,ScriptSqlDTO.class);
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.scriptSqlDaoImpl.findByNombre(scriptSql),ScriptSqlDTO .class);
				break;*/
				
			default:
				break;
		}
		
		return resultado;
	}
	@Override
	public List<ScriptSqlDTO> listarScriptSql(ScriptSqlDTO scriptSql) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityDTOList(this.scriptSqlDaoImpl.listarScriptSql(scriptSql),ScriptSqlDTO.class);
	}
	@Override
	public int contarListarScriptSql(ScriptSqlDTO scriptSql){
		return  this.scriptSqlDaoImpl.contarListarScriptSql(scriptSql);
	}
	
	@Override
	public ParametrosScriptSqlDTO controladorAccionParametrosScriptSql(ParametrosScriptSqlDTO parametrosScriptSql, AccionType accionType) throws Exception {
		ParametrosScriptSqlDTO resultado = null;
		ParametrosScriptSql resultadoEntity = null;
		switch (accionType) {
			case CREAR:
				parametrosScriptSql.setIdParametrosScriptSql(this.parametrosScriptSqlDaoImpl.generarIdParametrosScriptSql());
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(parametrosScriptSql, ParametrosScriptSql.class,"scriptSql@PK@");
				this.parametrosScriptSqlDaoImpl.save(resultadoEntity);	
				resultado = parametrosScriptSql;
				break;				
			case MODIFICAR:
			    resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(parametrosScriptSql, ParametrosScriptSql.class,"scriptSql@PK@");
				this.parametrosScriptSqlDaoImpl.update(resultadoEntity);
				resultado = parametrosScriptSql;	
				break;
				
			case ELIMINAR:
				resultadoEntity = this.parametrosScriptSqlDaoImpl.find(ParametrosScriptSql.class, parametrosScriptSql.getIdParametrosScriptSql());
				this.parametrosScriptSqlDaoImpl.delete(resultadoEntity);
				resultado = parametrosScriptSql;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.parametrosScriptSqlDaoImpl.find(ParametrosScriptSql.class, parametrosScriptSql.getIdParametrosScriptSql());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,ParametrosScriptSqlDTO.class);
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.parametrosScriptSqlDaoImpl.findByNombre(parametrosScriptSql),ParametrosScriptSqlDTO .class);
				break;*/
				
			default:
				break;
		}
		
		return resultado;
	}
	@Override
	public List<ParametrosScriptSqlDTO> listarParametrosScriptSql(ParametrosScriptSqlDTO parametrosScriptSql) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityDTOList(this.parametrosScriptSqlDaoImpl.listarParametrosScriptSql(parametrosScriptSql),ParametrosScriptSqlDTO.class);
	}
	@Override
	public int contarListarParametrosScriptSql(ParametrosScriptSqlDTO parametrosScriptSql){
		return  this.parametrosScriptSqlDaoImpl.contarListarParametrosScriptSql(parametrosScriptSql);
	}
}