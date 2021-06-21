package pe.com.builderp.core.facturacion.ejb.service.rest.empresa.impl;
 

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import pe.com.builderp.core.facturacion.ejb.service.empresa.local.EmpresaServiceLocal;
import pe.com.builderp.core.facturacion.model.dto.empresa.ControlPagoDTO;
import pe.com.builderp.core.facturacion.model.dto.venta.VentaDTO;
import pe.com.edu.siaa.core.ejb.dao.generic.impl.HibernateUtil;
import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.seguridad.jwt.rsa.util.AppHTTPHeaderNames; 
import pe.com.edu.siaa.core.ejb.service.rest.impl.GenericServiceRestImpl; 
import pe.com.edu.siaa.core.ejb.util.cache.AppAuthenticator; 
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.model.vo.VentaFiltroVO;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class ControlPagoRestController.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 13:43:36 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
@Path("/controlPagoRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class ControlPagoRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient EmpresaServiceLocal empresaServiceLocal;
	
	@POST
	public ResultadoRestVO<ControlPagoDTO> registrarPago(@Context HttpHeaders httpHeaders,ControlPagoDTO controlPago) throws Exception {
		ResultadoRestVO<ControlPagoDTO> resultado = new ResultadoRestVO<ControlPagoDTO>();
		String serviceKey = httpHeaders.getHeaderString( AppHTTPHeaderNames.SERVICE_KEY );
		String authToken = httpHeaders.getHeaderString( AppHTTPHeaderNames.AUTH_TOKEN );
		String ip = httpHeaders.getHeaderString( AppHTTPHeaderNames.ORIGIN );
		controlPago.setServiceKey(serviceKey);
		controlPago.setAuthToken(authToken);
		controlPago.setIp(ip);
		 try {
			 String userName = AppAuthenticator.getInstance().getUserName(authToken);
			 resultado.setObjetoResultado(empresaServiceLocal.registrarPago(controlPago));
			 String codigoGeneradoReporte = null;
			 if(controlPago.getAsociado() !=null) {
				 codigoGeneradoReporte = empresaServiceLocal.generarReportePagoBase64(controlPago.getIdControlPago(), controlPago.getAsociado().getIdAsociado(), userName);
			 } 
			 resultado.getObjetoResultado().setCodigoGeneradoReporte(codigoGeneradoReporte); 	 
			 HibernateUtil.setListaNull(resultado.getObjetoResultado());
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
			resultado.setMensajeError("El usuario o password es incorrecto ==> " + e.getMessage());
		}
		return resultado;
	}	
	
	@GET
    @Path("/generarReportePago/{idControlPago}/{idAlumno}")
	public ResultadoRestVO<String> generarReportePago(@Context HttpHeaders httpHeaders,@PathParam("idControlPago") String idControlPago,@PathParam("idAlumno") String idAlumno){
		ResultadoRestVO<String> resultado = new ResultadoRestVO<String>(); 
		String authToken = httpHeaders.getHeaderString( AppHTTPHeaderNames.AUTH_TOKEN );
		 try {
			 String userName = AppAuthenticator.getInstance().getUserName(authToken);
			 resultado.setObjetoResultado(empresaServiceLocal.generarReportePago(idControlPago, idAlumno, userName));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	
	
	@GET
    @Path("/generarReportePagoBase64/{idControlPago}/{idAlumno}")
	public ResultadoRestVO<String> generarReportePagoBase64(@Context HttpHeaders httpHeaders,@PathParam("idControlPago") String idControlPago,@PathParam("idAlumno") String idAlumno){
		ResultadoRestVO<String> resultado = new ResultadoRestVO<String>(); 
		String authToken = httpHeaders.getHeaderString( AppHTTPHeaderNames.AUTH_TOKEN );
		 try {
			 String userName = AppAuthenticator.getInstance().getUserName(authToken);
			 resultado.setObjetoResultado(empresaServiceLocal.generarReportePagoBase64(idControlPago, idAlumno, userName));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
 
	
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<ControlPagoDTO> eliminar(@PathParam("id") String idControlPago) throws Exception {
		ControlPagoDTO controlPago = new ControlPagoDTO();
		controlPago.setIdControlPago(idControlPago);		
		return controladorAccion(controlPago,AccionType.ELIMINAR);
	}
	private ResultadoRestVO<ControlPagoDTO> controladorAccion(ControlPagoDTO controlPago, AccionType accionType){
		ResultadoRestVO<ControlPagoDTO> resultado = new ResultadoRestVO<ControlPagoDTO>();
		 try {
    		resultado.setObjetoResultado(empresaServiceLocal.controladorAccionControlPago(controlPago,accionType));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<ControlPagoDTO> finById(@PathParam("id") String idControlPago) throws Exception {
		ControlPagoDTO controlPago = new ControlPagoDTO();
		controlPago.setIdControlPago(idControlPago);
		return controladorAccion(controlPago,AccionType.FIND_BY_ID);
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<ControlPagoDTO> listarControlPago(@Context UriInfo info){
		ResultadoRestVO<ControlPagoDTO> resultado = new ResultadoRestVO<ControlPagoDTO>();
		ControlPagoDTO controlPago = transferUriInfo(info);
		 try {
    		resultado.setListaResultado(empresaServiceLocal.listarControlPago(controlPago));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/contar")
	public ResultadoRestVO<ControlPagoDTO> contarListarControlPago(@Context UriInfo info){
		ResultadoRestVO<ControlPagoDTO> resultado = new ResultadoRestVO<ControlPagoDTO>();
		ControlPagoDTO controlPago = transferUriInfo(info);
		 try {
    		resultado.setContador(empresaServiceLocal.contarListarControlPago(controlPago));
    		if (resultado.isData()) {
    			resultado.setListaResultado(empresaServiceLocal.listarControlPago(controlPago));
    		}
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/")
	public ResultadoRestVO<ControlPagoDTO> inicializarControlPago(@Context UriInfo info) throws Exception {
	     ControlPagoDTO controlPago = transferUriInfo(info);
		 ResultadoRestVO<ControlPagoDTO> resultado = new ResultadoRestVO<ControlPagoDTO>();
		 try {
    		resultado.setObjetoResultado(controlPago);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private ControlPagoDTO transferUriInfo(@Context UriInfo info) {
		ControlPagoDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,ControlPagoDTO.class);
		return resultado;
	}
	 
 
	@POST
	@Path("/obtenerListaCajaFiltroPago")
	public ResultadoRestVO<ControlPagoDTO>  obtenerListaCajaFiltroPago(VentaFiltroVO cajafilterVo) throws Exception {
		ResultadoRestVO<ControlPagoDTO> resultado = new ResultadoRestVO<ControlPagoDTO>();
		 try {
   		resultado.setListaResultado(empresaServiceLocal.obtenerListaCajaFiltroPago(cajafilterVo));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	
}