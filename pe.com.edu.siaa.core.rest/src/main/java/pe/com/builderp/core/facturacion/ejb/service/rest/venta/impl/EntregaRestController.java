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
import pe.com.builderp.core.facturacion.model.dto.venta.DetalleEntregaDTO;
import pe.com.builderp.core.facturacion.model.dto.venta.EntregaDTO;
import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.seguridad.jwt.rsa.util.AppHTTPHeaderNames;
import pe.com.edu.siaa.core.ejb.service.rest.impl.GenericServiceRestImpl;
import pe.com.edu.siaa.core.ejb.util.cache.AppAuthenticator;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class EntregaRestController.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu Dec 28 12:02:24 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
@Path("/entregaRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class EntregaRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient VentaServiceLocal ventaServiceLocal;
	
	@POST
	public ResultadoRestVO<EntregaDTO> crear(@Context HttpHeaders httpHeaders,EntregaDTO entrega) throws Exception {
		ResultadoRestVO<EntregaDTO> resultado = new ResultadoRestVO<EntregaDTO>();
		String serviceKey = httpHeaders.getHeaderString( AppHTTPHeaderNames.SERVICE_KEY );
		String authToken = httpHeaders.getHeaderString( AppHTTPHeaderNames.AUTH_TOKEN );			
		entrega.setServiceKey(serviceKey);
		entrega.setAuthToken(authToken);	
		 try {
			 resultado.setObjetoResultado(ventaServiceLocal.registrarEntregaDTO(entrega));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		 return resultado;
		//return controladorAccion(entrega,AccionType.CREAR);
	}
	@PUT
	public ResultadoRestVO<EntregaDTO> modificar(EntregaDTO entrega) throws Exception {
		return controladorAccion(entrega,AccionType.MODIFICAR);
	}
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<String> eliminar(@Context HttpHeaders httpHeaders,@PathParam("id") String idEntrega) throws Exception {
		ResultadoRestVO<String> resultado = new ResultadoRestVO<String>();
		String authToken = httpHeaders.getHeaderString( AppHTTPHeaderNames.AUTH_TOKEN );
		 try {
			 String userName = AppAuthenticator.getInstance().getUserName(authToken);
			 ventaServiceLocal.eliminarEntrega(idEntrega,userName);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private ResultadoRestVO<EntregaDTO> controladorAccion(EntregaDTO entrega, AccionType accionType){
		ResultadoRestVO<EntregaDTO> resultado = new ResultadoRestVO<EntregaDTO>();
		 try {
    		resultado.setObjetoResultado(ventaServiceLocal.controladorAccionEntrega(entrega,accionType));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<EntregaDTO> finById(@PathParam("id") String idEntrega) throws Exception {
		EntregaDTO entrega = new EntregaDTO();
		entrega.setIdEntrega(idEntrega);
		return controladorAccion(entrega,AccionType.FIND_BY_ID);
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<EntregaDTO> listarEntrega(@Context UriInfo info){
		ResultadoRestVO<EntregaDTO> resultado = new ResultadoRestVO<EntregaDTO>();
		EntregaDTO entrega = transferUriInfo(info);
		 try {
			resultado.setListaResultado(ventaServiceLocal.listarEntrega(entrega));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/contar")
	public ResultadoRestVO<EntregaDTO> contarEntrega(@Context UriInfo info){
		ResultadoRestVO<EntregaDTO> resultado = new ResultadoRestVO<EntregaDTO>();
		EntregaDTO entrega = transferUriInfo(info);
		 try {
			 resultado.setContador(ventaServiceLocal.contarListarEntrega(entrega));
			 if (resultado.isData()) {
				resultado.setListaResultado(ventaServiceLocal.listarEntrega(entrega));
			 }
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/")
	public ResultadoRestVO<EntregaDTO> inicializarEntrega(@Context UriInfo info) throws Exception {
	     EntregaDTO entrega = transferUriInfo(info);
		 ResultadoRestVO<EntregaDTO> resultado = new ResultadoRestVO<EntregaDTO>();
		 try {
    		resultado.setObjetoResultado(entrega);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private EntregaDTO transferUriInfo(@Context UriInfo info) {
		EntregaDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,EntregaDTO.class);
		return resultado;
	}
	
	
	@GET
    @Path("/listarDetalleEntrega")
	public ResultadoRestVO<DetalleEntregaDTO> listarDetalleEntrega(@Context UriInfo info){
		ResultadoRestVO<DetalleEntregaDTO> resultado = new ResultadoRestVO<DetalleEntregaDTO>();
		DetalleEntregaDTO detalleEntrega = transferUriInfoDetalle(info);
		 try {
			resultado.setListaResultado(ventaServiceLocal.listarDetalleEntrega(detalleEntrega));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	
	private DetalleEntregaDTO transferUriInfoDetalle(@Context UriInfo info) {
		DetalleEntregaDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,DetalleEntregaDTO.class);
		return resultado;
	}
}