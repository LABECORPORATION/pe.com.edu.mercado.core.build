package pe.com.builderp.core.facturacion.ejb.service.rest.venta.impl;

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


import pe.com.builderp.core.facturacion.ejb.service.venta.local.VentaServiceLocal;
import pe.com.builderp.core.facturacion.model.dto.venta.GuiaRemisionDTO;
import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.seguridad.jwt.rsa.util.AppHTTPHeaderNames;
import pe.com.edu.siaa.core.ejb.service.rest.impl.GenericServiceRestImpl;
import pe.com.edu.siaa.core.ejb.util.cache.AppAuthenticator;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;



/**
 * La Class GuiaRemisionRestController.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu May 02 10:20:30 COT 2019
 * @since SIAA-CORE 2.1
 */
@Stateless
@Path("/guiaRemisionRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class GuiaRemisionRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient VentaServiceLocal ventaServiceLocal;
	
	@POST
	public ResultadoRestVO<GuiaRemisionDTO> crear(GuiaRemisionDTO guiaRemision) throws Exception {
		return controladorAccion(guiaRemision,AccionType.CREAR);
	}
	@PUT
	public ResultadoRestVO<GuiaRemisionDTO> modificar(GuiaRemisionDTO guiaRemision) throws Exception {
		return controladorAccion(guiaRemision,AccionType.MODIFICAR);
	}
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<GuiaRemisionDTO> eliminar(@PathParam("id") String idGuiaRemision) throws Exception {
		GuiaRemisionDTO guiaRemision = new GuiaRemisionDTO();
		guiaRemision.setIdGuiaRemision(idGuiaRemision);		
		return controladorAccion(guiaRemision,AccionType.ELIMINAR);
	}
	private ResultadoRestVO<GuiaRemisionDTO> controladorAccion(GuiaRemisionDTO guiaRemision, AccionType accionType){
		ResultadoRestVO<GuiaRemisionDTO> resultado = new ResultadoRestVO<>();
		 try {
    		resultado.setObjetoResultado(ventaServiceLocal.controladorAccionGuiaRemision(guiaRemision,accionType));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<GuiaRemisionDTO> finById(@PathParam("id") String idGuiaRemision) throws Exception {
		GuiaRemisionDTO guiaRemision = new GuiaRemisionDTO();
		guiaRemision.setIdGuiaRemision(idGuiaRemision);
		return controladorAccion(guiaRemision,AccionType.FIND_BY_ID);
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<GuiaRemisionDTO> listarGuiaRemision(@Context UriInfo info){
		ResultadoRestVO<GuiaRemisionDTO> resultado = new ResultadoRestVO<>();
		GuiaRemisionDTO guiaRemision = transferUriInfo(info);
		 try {
			resultado.setListaResultado(ventaServiceLocal.listarGuiaRemision(guiaRemision));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/contar")
	public ResultadoRestVO<GuiaRemisionDTO> contarGuiaRemision(@Context UriInfo info){
		ResultadoRestVO<GuiaRemisionDTO> resultado = new ResultadoRestVO<>();
		GuiaRemisionDTO guiaRemision = transferUriInfo(info);
		 try {
			 resultado.setContador(ventaServiceLocal.contarListarGuiaRemision(guiaRemision));
			 if (resultado.isData()) {
				resultado.setListaResultado(ventaServiceLocal.listarGuiaRemision(guiaRemision));
			 }
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/")
	public ResultadoRestVO<GuiaRemisionDTO> inicializarGuiaRemision(@Context UriInfo info) throws Exception {
	     GuiaRemisionDTO guiaRemision = transferUriInfo(info);
		 ResultadoRestVO<GuiaRemisionDTO> resultado = new ResultadoRestVO<>();
		 try {
    		resultado.setObjetoResultado(guiaRemision);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private GuiaRemisionDTO transferUriInfo(@Context UriInfo info) {
		return TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,GuiaRemisionDTO.class);
	}
	
	//add
	
	@POST
    @Path("/generarReporteGuia")
	public ResultadoRestVO<String> generarReportePago(@Context HttpHeaders httpHeaders,GuiaRemisionDTO guia){
		ResultadoRestVO<String> resultado = new ResultadoRestVO<String>();
		String serviceKey = httpHeaders.getHeaderString( AppHTTPHeaderNames.SERVICE_KEY );
		String authToken = httpHeaders.getHeaderString( AppHTTPHeaderNames.AUTH_TOKEN );
		 try {
			 String userName = AppAuthenticator.getInstance().getUserName(authToken);
			 resultado.setObjetoResultado(ventaServiceLocal.generarReporteGuia(guia));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
}