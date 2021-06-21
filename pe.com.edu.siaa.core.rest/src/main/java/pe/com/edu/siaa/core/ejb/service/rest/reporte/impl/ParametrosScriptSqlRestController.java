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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.service.reporte.local.ReporteServiceLocal;
import pe.com.edu.siaa.core.ejb.service.rest.impl.GenericServiceRestImpl;
import pe.com.edu.siaa.core.model.dto.reporte.ParametrosScriptSqlDTO;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class ParametrosScriptSqlRestController.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 13:43:42 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
@Path("/parametrosScriptSqlRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class ParametrosScriptSqlRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient ReporteServiceLocal reporteServiceLocal;
	
	@POST
	public ResultadoRestVO<ParametrosScriptSqlDTO> crear(ParametrosScriptSqlDTO parametrosScriptSql) throws Exception {
		return controladorAccion(parametrosScriptSql,AccionType.CREAR);
	}
	@PUT
	public ResultadoRestVO<ParametrosScriptSqlDTO> modificar(ParametrosScriptSqlDTO parametrosScriptSql) throws Exception {
		return controladorAccion(parametrosScriptSql,AccionType.MODIFICAR);
	}
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<ParametrosScriptSqlDTO> eliminar(@PathParam("id") String idParametrosScriptSql) throws Exception {
		ParametrosScriptSqlDTO parametrosScriptSql = new ParametrosScriptSqlDTO();
		parametrosScriptSql.setIdParametrosScriptSql(idParametrosScriptSql);		
		return controladorAccion(parametrosScriptSql,AccionType.ELIMINAR);
	}
	private ResultadoRestVO<ParametrosScriptSqlDTO> controladorAccion(ParametrosScriptSqlDTO parametrosScriptSql, AccionType accionType){
		ResultadoRestVO<ParametrosScriptSqlDTO> resultado = new ResultadoRestVO<ParametrosScriptSqlDTO>();
		 try {
    		resultado.setObjetoResultado(reporteServiceLocal.controladorAccionParametrosScriptSql(parametrosScriptSql,accionType));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<ParametrosScriptSqlDTO> finById(@PathParam("id") String idParametrosScriptSql) throws Exception {
		ParametrosScriptSqlDTO parametrosScriptSql = new ParametrosScriptSqlDTO();
		parametrosScriptSql.setIdParametrosScriptSql(idParametrosScriptSql);
		return controladorAccion(parametrosScriptSql,AccionType.FIND_BY_ID);
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<ParametrosScriptSqlDTO> listarParametrosScriptSql(@Context UriInfo info){
		ResultadoRestVO<ParametrosScriptSqlDTO> resultado = new ResultadoRestVO<ParametrosScriptSqlDTO>();
		ParametrosScriptSqlDTO parametrosScriptSql = transferUriInfo(info);
		 try {
    		resultado.setListaResultado(reporteServiceLocal.listarParametrosScriptSql(parametrosScriptSql));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/contar")
	public ResultadoRestVO<Integer> contarListarParametrosScriptSql(@Context UriInfo info){
		ResultadoRestVO<Integer> resultado = new ResultadoRestVO<Integer>();
		ParametrosScriptSqlDTO parametrosScriptSql = transferUriInfo(info);
		 try {
    		resultado.setObjetoResultado(reporteServiceLocal.contarListarParametrosScriptSql(parametrosScriptSql));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/")
	public ResultadoRestVO<ParametrosScriptSqlDTO> inicializarParametrosScriptSql(@Context UriInfo info) throws Exception {
	     ParametrosScriptSqlDTO parametrosScriptSql = transferUriInfo(info);
		 ResultadoRestVO<ParametrosScriptSqlDTO> resultado = new ResultadoRestVO<ParametrosScriptSqlDTO>();
		 try {
    		resultado.setObjetoResultado(parametrosScriptSql);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private ParametrosScriptSqlDTO transferUriInfo(@Context UriInfo info) {
		ParametrosScriptSqlDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,ParametrosScriptSqlDTO.class);
		return resultado;
	}
}