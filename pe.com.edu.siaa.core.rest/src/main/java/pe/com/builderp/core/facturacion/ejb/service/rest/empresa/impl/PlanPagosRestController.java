package pe.com.builderp.core.facturacion.ejb.service.rest.empresa.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
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

import pe.com.builderp.core.facturacion.ejb.service.empresa.local.EmpresaServiceLocal;
import pe.com.builderp.core.facturacion.model.dto.empresa.AnhioDTO;
import pe.com.builderp.core.facturacion.model.dto.empresa.AsociadoDTO;
import pe.com.builderp.core.facturacion.model.dto.empresa.PlanPagosDTO; 
import pe.com.edu.siaa.core.ejb.factory.CollectionUtil;
import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.seguridad.jwt.rsa.util.AppHTTPHeaderNames; 
import pe.com.edu.siaa.core.ejb.service.rest.impl.GenericServiceRestImpl; 
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class PlanPagosRestController.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 13:43:35 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
@Path("/planPagosRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class PlanPagosRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient EmpresaServiceLocal empresaServiceLocal;
	
	@POST
	public ResultadoRestVO<PlanPagosDTO> crear(@Context HttpHeaders httpHeaders,PlanPagosDTO planPagos) throws Exception {
		return registrarPlanPagos(httpHeaders,planPagos);
	}
	@PUT
	public ResultadoRestVO<PlanPagosDTO> modificar(@Context HttpHeaders httpHeaders,PlanPagosDTO planPagos) throws Exception {
		return registrarPlanPagos(httpHeaders,planPagos);
	}

	private ResultadoRestVO<PlanPagosDTO> registrarPlanPagos(HttpHeaders httpHeaders,PlanPagosDTO planPagos){
		String serviceKey = httpHeaders.getHeaderString( AppHTTPHeaderNames.SERVICE_KEY );
		String authToken = httpHeaders.getHeaderString( AppHTTPHeaderNames.AUTH_TOKEN );
		planPagos.setServiceKey(serviceKey);
		planPagos.setAuthToken(authToken);
		ResultadoRestVO<PlanPagosDTO> resultado = new ResultadoRestVO<PlanPagosDTO>();
		 try {
    		resultado.setObjetoResultado(empresaServiceLocal.registrarPlanPagos(planPagos));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	
 
	 
	@GET
	@Path("/obtenerPlanPagosByIdCliente/{idAnhio}/{idAsociado}")
	public ResultadoRestVO<PlanPagosDTO> obtenerPlanPagosByIdCliente(@PathParam("idAnhio") Long idAnhio, @PathParam("idAsociado") String idAsociado ) throws Exception {
		ResultadoRestVO<PlanPagosDTO> resultado = new ResultadoRestVO<PlanPagosDTO>();
		PlanPagosDTO planPagos = new PlanPagosDTO(); 
		planPagos.setAsociado(new AsociadoDTO());
		planPagos.getAsociado().setIdAsociado(idAsociado);
		planPagos.setAnio(new AnhioDTO());
		planPagos.getAnio().setIdAnhio(idAnhio);
		 try {
			 List<PlanPagosDTO> listaPlanPagos = empresaServiceLocal.listarPlanPagos(planPagos);
			 if (!CollectionUtil.isEmpty(listaPlanPagos)) {
				 resultado.setObjetoResultado(listaPlanPagos.get(0));
			 }
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	
	@GET
	@Path("/generarPlanPagosMaisvo/{idCuotaConcepto}")
	public ResultadoRestVO<String> generarPlanPagosMaisvo(@Context HttpHeaders httpHeaders, @PathParam("idCuotaConcepto") String idCuotaConcepto ) throws Exception {
		ResultadoRestVO<String> resultado = new ResultadoRestVO<String>(); 
		String authToken = httpHeaders.getHeaderString( AppHTTPHeaderNames.AUTH_TOKEN );
		 try { 
				 resultado.setObjetoResultado(empresaServiceLocal.generarPlanPagosMaisvo(idCuotaConcepto,authToken)); 
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<PlanPagosDTO> listarPlanPagos(@Context UriInfo info){
		ResultadoRestVO<PlanPagosDTO> resultado = new ResultadoRestVO<PlanPagosDTO>();
		PlanPagosDTO planPagos = transferUriInfo(info);
		 try {
    		resultado.setListaResultado(empresaServiceLocal.listarPlanPagos(planPagos));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/contar")
	public ResultadoRestVO<PlanPagosDTO> contarListarPlanPagos(@Context UriInfo info){
		ResultadoRestVO<PlanPagosDTO> resultado = new ResultadoRestVO<PlanPagosDTO>();
		PlanPagosDTO planPagos = transferUriInfo(info);
		 try {
    		resultado.setContador(empresaServiceLocal.contarListarPlanPagos(planPagos));
    		if (resultado.isData()) {
    			resultado.setListaResultado(empresaServiceLocal.listarPlanPagos(planPagos));
    		}
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/")
	public ResultadoRestVO<PlanPagosDTO> inicializarPlanPagos(@Context UriInfo info) throws Exception {
	     PlanPagosDTO planPagos = transferUriInfo(info);
		 ResultadoRestVO<PlanPagosDTO> resultado = new ResultadoRestVO<PlanPagosDTO>();
		 try {
    		resultado.setObjetoResultado(planPagos);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private PlanPagosDTO transferUriInfo(@Context UriInfo info) {
		PlanPagosDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,PlanPagosDTO.class);
		return resultado;
	}
}