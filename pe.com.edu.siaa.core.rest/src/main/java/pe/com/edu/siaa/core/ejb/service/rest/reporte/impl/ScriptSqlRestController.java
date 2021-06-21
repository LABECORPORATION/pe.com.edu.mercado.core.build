package pe.com.edu.siaa.core.ejb.service.rest.reporte.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.seguridad.jwt.rsa.util.AppHTTPHeaderNames;
import pe.com.edu.siaa.core.ejb.service.reporte.local.ReporteServiceLocal;
import pe.com.edu.siaa.core.ejb.service.rest.impl.GenericServiceRestImpl;
import pe.com.edu.siaa.core.ejb.util.cache.AppAuthenticator;
import pe.com.edu.siaa.core.model.dto.reporte.ScriptSqlDTO;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class ScriptSqlRestController.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 13:43:30 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
@Path("/scriptSqlRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class ScriptSqlRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient ReporteServiceLocal reporteServiceLocal;
	
	@POST
	public ResultadoRestVO<ScriptSqlDTO> crear(ScriptSqlDTO scriptSql) throws Exception {
		return controladorAccion(scriptSql,AccionType.CREAR);
	}
	@PUT
	public ResultadoRestVO<ScriptSqlDTO> modificar(ScriptSqlDTO scriptSql) throws Exception {
		return controladorAccion(scriptSql,AccionType.MODIFICAR);
	}
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<ScriptSqlDTO> eliminar(@PathParam("id") String idScriptSql) throws Exception {
		ScriptSqlDTO scriptSql = new ScriptSqlDTO();
		scriptSql.setIdScriptSql(idScriptSql);		
		return controladorAccion(scriptSql,AccionType.ELIMINAR);
	}
	private ResultadoRestVO<ScriptSqlDTO> controladorAccion(ScriptSqlDTO scriptSql, AccionType accionType){
		ResultadoRestVO<ScriptSqlDTO> resultado = new ResultadoRestVO<ScriptSqlDTO>();
		 try {
    		resultado.setObjetoResultado(reporteServiceLocal.controladorAccionScriptSql(scriptSql,accionType));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<ScriptSqlDTO> finById(@PathParam("id") String idScriptSql) throws Exception {
		ScriptSqlDTO scriptSql = new ScriptSqlDTO();
		scriptSql.setIdScriptSql(idScriptSql);
		return controladorAccion(scriptSql,AccionType.FIND_BY_ID);
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<ScriptSqlDTO> listarScriptSql(@Context UriInfo info){
		ResultadoRestVO<ScriptSqlDTO> resultado = new ResultadoRestVO<ScriptSqlDTO>();
		ScriptSqlDTO scriptSql = transferUriInfo(info);
		 try {
    		resultado.setListaResultado(reporteServiceLocal.listarScriptSql(scriptSql));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/contar")
	public ResultadoRestVO<ScriptSqlDTO> contarListarScriptSql(@Context UriInfo info){
		ResultadoRestVO<ScriptSqlDTO> resultado = new ResultadoRestVO<ScriptSqlDTO>();
		ScriptSqlDTO scriptSql = transferUriInfo(info);
		 try {
    		resultado.setContador(reporteServiceLocal.contarListarScriptSql(scriptSql));
    		if (resultado.isData()) {
    			resultado.setListaResultado(reporteServiceLocal.listarScriptSql(scriptSql));
    		}
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/")
	public ResultadoRestVO<ScriptSqlDTO> inicializarScriptSql(@Context UriInfo info) throws Exception {
	     ScriptSqlDTO scriptSql = transferUriInfo(info);
		 ResultadoRestVO<ScriptSqlDTO> resultado = new ResultadoRestVO<ScriptSqlDTO>();
		 try {
    		resultado.setObjetoResultado(scriptSql);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	
	
	@POST
    @Path("/generarReporteScript")
	public ResultadoRestVO<String> generarReporteScript(@Context HttpHeaders httpHeaders,ScriptSqlDTO scriptSql){
		ResultadoRestVO<String> resultado = new ResultadoRestVO<String>();
		String serviceKey = httpHeaders.getHeaderString( AppHTTPHeaderNames.SERVICE_KEY );
		String authToken = httpHeaders.getHeaderString( AppHTTPHeaderNames.AUTH_TOKEN );
		 try {
			String userName = AppAuthenticator.getInstance().getUserName(authToken);
    		resultado.setObjetoResultado(reporteServiceLocal.generarReporteScript(scriptSql, userName));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private ScriptSqlDTO transferUriInfo(@Context UriInfo info) {
		ScriptSqlDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,ScriptSqlDTO.class);
		return resultado;
	}
}