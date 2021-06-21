package pe.com.edu.siaa.core.ejb.service.rest.contabilidad.impl;

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

import pe.com.builderp.core.contabilidad.model.vo.RegistroAsientoFiltroVO;
import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.seguridad.jwt.rsa.util.AppHTTPHeaderNames;
import pe.com.edu.siaa.core.ejb.service.contabilidad.local.ContabilidadServiceLocal;
import pe.com.edu.siaa.core.ejb.service.rest.impl.GenericServiceRestImpl;
import pe.com.edu.siaa.core.ejb.util.cache.AppAuthenticator;
import pe.com.edu.siaa.core.model.dto.contabilidad.AsientoContableDTO;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class AsientoContableRestController.
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
@Path("/asientoContableRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class AsientoContableRestController extends GenericServiceRestImpl {

	@EJB
	private transient ContabilidadServiceLocal contabilidadServiceLocal;
	
	@POST
	public ResultadoRestVO<AsientoContableDTO> crear(@Context HttpHeaders httpHeaders,AsientoContableDTO asientoContable) throws Exception {
		String ip = httpHeaders.getHeaderString( AppHTTPHeaderNames.ORIGIN );
		asientoContable.setIpAcceso(ip);
		return controladorAccion(httpHeaders,asientoContable,AccionType.CREAR);
	}
	
	@PUT
	public ResultadoRestVO<AsientoContableDTO> modificar(@Context HttpHeaders httpHeaders, AsientoContableDTO asientoContable) throws Exception {
		String ip = httpHeaders.getHeaderString( AppHTTPHeaderNames.ORIGIN );
		asientoContable.setIpAcceso(ip);
		return controladorAccion(httpHeaders,asientoContable,AccionType.MODIFICAR);
	}
	
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<AsientoContableDTO> eliminar(@Context HttpHeaders httpHeaders,@PathParam("id") String idAsientoContable) throws Exception {
		AsientoContableDTO asientoContable = new AsientoContableDTO();
		asientoContable.setIdAsientoContable(idAsientoContable);		
		return controladorAccion(httpHeaders,asientoContable,AccionType.ELIMINAR);
	}
	private ResultadoRestVO<AsientoContableDTO> controladorAccion(HttpHeaders httpHeaders,AsientoContableDTO asientoContable, AccionType accionType){
		ResultadoRestVO<AsientoContableDTO> resultado = new ResultadoRestVO<AsientoContableDTO>();
		String serviceKey = httpHeaders.getHeaderString( AppHTTPHeaderNames.SERVICE_KEY );
		String authToken = httpHeaders.getHeaderString( AppHTTPHeaderNames.AUTH_TOKEN );
		asientoContable.setServiceKey(serviceKey);
		asientoContable.setAuthToken(authToken);
		 try {
			 String userName = AppAuthenticator.getInstance().getUserName(asientoContable.getAuthToken());
    		resultado.setObjetoResultado(contabilidadServiceLocal.registrarAsientoContable(asientoContable,userName,accionType));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<AsientoContableDTO> finById(@Context HttpHeaders httpHeaders,@PathParam("id") String idAsientoContable) throws Exception {
		AsientoContableDTO asientoContable = new AsientoContableDTO();
		asientoContable.setIdAsientoContable(idAsientoContable);
		return controladorAccion(httpHeaders,asientoContable,AccionType.FIND_BY_ID);
	}
	
	@POST
    @Path("/listar")
	public ResultadoRestVO<AsientoContableDTO> listarAsientoContable(@Context HttpHeaders httpHeaders,RegistroAsientoFiltroVO registroVentaFiltro){
		ResultadoRestVO<AsientoContableDTO> resultado = new ResultadoRestVO<AsientoContableDTO>();
		 try {
			resultado.setListaResultado(contabilidadServiceLocal.listarAsientoContable(registroVentaFiltro));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@POST
    @Path("/contar")
	public ResultadoRestVO<AsientoContableDTO> contarAsientoContable(@Context HttpHeaders httpHeaders,RegistroAsientoFiltroVO registroVentaFiltro){
		ResultadoRestVO<AsientoContableDTO> resultado = new ResultadoRestVO<AsientoContableDTO>();
		 try {
			 resultado.setContador(contabilidadServiceLocal.contarListarAsientoContable(registroVentaFiltro));
			 if (resultado.isData()) {
				resultado.setListaResultado(contabilidadServiceLocal.listarAsientoContable(registroVentaFiltro));
			 }
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/")
	public ResultadoRestVO<AsientoContableDTO> inicializarAsientoContable(@Context UriInfo info) throws Exception {
	     AsientoContableDTO asientoContable = transferUriInfo(info);
		 ResultadoRestVO<AsientoContableDTO> resultado = new ResultadoRestVO<AsientoContableDTO>();
		 try {
    		resultado.setObjetoResultado(asientoContable);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	
	@POST
    @Path("/generarReporteAsientoContableDiario")
	public ResultadoRestVO<String> generarReporteAsientoContableDiario(@Context HttpHeaders httpHeaders,RegistroAsientoFiltroVO registroVentaFiltro){
		ResultadoRestVO<String> resultado = new ResultadoRestVO<String>();
		String serviceKey = httpHeaders.getHeaderString( AppHTTPHeaderNames.SERVICE_KEY );
		String authToken = httpHeaders.getHeaderString( AppHTTPHeaderNames.AUTH_TOKEN );
		 try {
			String userName = AppAuthenticator.getInstance().getUserName(authToken);
    		resultado.setObjetoResultado(contabilidadServiceLocal.generarReporteAsientoContableDiario(userName,registroVentaFiltro));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	
	@POST
    @Path("/generarReporteAsientoContableLibroMayor")
	public ResultadoRestVO<String> generarReporteAsientoContableLibroMayor(@Context HttpHeaders httpHeaders,RegistroAsientoFiltroVO registroVentaFiltro){
		ResultadoRestVO<String> resultado = new ResultadoRestVO<String>();
		String serviceKey = httpHeaders.getHeaderString( AppHTTPHeaderNames.SERVICE_KEY );
		String authToken = httpHeaders.getHeaderString( AppHTTPHeaderNames.AUTH_TOKEN );
		 try {
			String userName = AppAuthenticator.getInstance().getUserName(authToken);
    		resultado.setObjetoResultado(contabilidadServiceLocal.generarReporteAsientoContableLibroMayor(userName,registroVentaFiltro));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private AsientoContableDTO transferUriInfo(@Context UriInfo info) {
		AsientoContableDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,AsientoContableDTO.class);
		return resultado;
	}
}