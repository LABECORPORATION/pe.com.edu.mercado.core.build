package pe.com.builderp.core.facturacion.ejb.service.rest.compra.impl;

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

import pe.com.builderp.core.facturacion.ejb.service.compra.local.CompraServiceLocal;
import pe.com.builderp.core.facturacion.model.dto.compra.DetalleOrdenCompraDTO;
import pe.com.builderp.core.facturacion.model.dto.compra.OrdenCompraDTO;
import pe.com.builderp.core.facturacion.model.dto.venta.DetalleVentaDTO;
import pe.com.builderp.core.facturacion.model.dto.venta.ProductoDTO;
import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.seguridad.jwt.rsa.util.AppHTTPHeaderNames;
import pe.com.edu.siaa.core.ejb.service.rest.impl.GenericServiceRestImpl;
import pe.com.edu.siaa.core.ejb.util.cache.AppAuthenticator;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class OrdenCompraRestController.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu Dec 28 12:02:20 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
@Path("/ordenCompraRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class OrdenCompraRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient CompraServiceLocal compraServiceLocal;
	
	@POST
	public ResultadoRestVO<OrdenCompraDTO> crear(@Context HttpHeaders httpHeaders,OrdenCompraDTO ordenCompra) throws Exception {
		ResultadoRestVO<OrdenCompraDTO> resultado = new ResultadoRestVO<OrdenCompraDTO>();
		String serviceKey = httpHeaders.getHeaderString( AppHTTPHeaderNames.SERVICE_KEY );
		String authToken = httpHeaders.getHeaderString( AppHTTPHeaderNames.AUTH_TOKEN );
		String ip = httpHeaders.getHeaderString( AppHTTPHeaderNames.ORIGIN );
		ordenCompra.setIpAcceso(ip);
		ordenCompra.setServiceKey(serviceKey);
		ordenCompra.setAuthToken(authToken);	
		 try {
			 resultado.setObjetoResultado(compraServiceLocal.registrarOrdenCompraDTO(ordenCompra));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		 return resultado;
		//return controladorAccion(ordenCompra,AccionType.CREAR);
	}
	@PUT
	public ResultadoRestVO<OrdenCompraDTO> modificar(OrdenCompraDTO ordenCompra) throws Exception {
		return controladorAccion(ordenCompra,AccionType.MODIFICAR);
	}
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<String> eliminar(@Context HttpHeaders httpHeaders,@PathParam("id") String idOrdenCompra) throws Exception {
		ResultadoRestVO<String> resultado = new ResultadoRestVO<String>();
		String authToken = httpHeaders.getHeaderString( AppHTTPHeaderNames.AUTH_TOKEN );
		 try {
			 String userName = AppAuthenticator.getInstance().getUserName(authToken);
			 compraServiceLocal.eliminarOrdenCompra(idOrdenCompra,userName);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private ResultadoRestVO<OrdenCompraDTO> controladorAccion(OrdenCompraDTO ordenCompra, AccionType accionType){
		ResultadoRestVO<OrdenCompraDTO> resultado = new ResultadoRestVO<OrdenCompraDTO>();
		 try {
    		resultado.setObjetoResultado(compraServiceLocal.controladorAccionOrdenCompra(ordenCompra,accionType));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<OrdenCompraDTO> finById(@PathParam("id") String idOrdenCompra) throws Exception {
		OrdenCompraDTO ordenCompra = new OrdenCompraDTO();
		ordenCompra.setIdOrdenCompra(idOrdenCompra);
		return controladorAccion(ordenCompra,AccionType.FIND_BY_ID);
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<OrdenCompraDTO> listarOrdenCompra(@Context UriInfo info){
		ResultadoRestVO<OrdenCompraDTO> resultado = new ResultadoRestVO<OrdenCompraDTO>();
		OrdenCompraDTO ordenCompra = transferUriInfo(info);
		 try {
			resultado.setListaResultado(compraServiceLocal.listarOrdenCompra(ordenCompra));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/contar")
	public ResultadoRestVO<OrdenCompraDTO> contarOrdenCompra(@Context UriInfo info){
		ResultadoRestVO<OrdenCompraDTO> resultado = new ResultadoRestVO<OrdenCompraDTO>();
		OrdenCompraDTO ordenCompra = transferUriInfo(info);
		 try {
			 resultado.setContador(compraServiceLocal.contarListarOrdenCompra(ordenCompra));
			 if (resultado.isData()) {
				resultado.setListaResultado(compraServiceLocal.listarOrdenCompra(ordenCompra));
			 }
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/")
	public ResultadoRestVO<OrdenCompraDTO> inicializarOrdenCompra(@Context UriInfo info) throws Exception {
	     OrdenCompraDTO ordenCompra = transferUriInfo(info);
		 ResultadoRestVO<OrdenCompraDTO> resultado = new ResultadoRestVO<OrdenCompraDTO>();
		 try {
    		resultado.setObjetoResultado(ordenCompra);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private OrdenCompraDTO transferUriInfo(@Context UriInfo info) {
		OrdenCompraDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,OrdenCompraDTO.class);
		return resultado;
	}
	
	@GET
    @Path("/generarReporteOrdenCompra/{idOrdenCompra}")
	public ResultadoRestVO<String> generarReporteOrdenCompra(@Context HttpHeaders httpHeaders,@PathParam("idOrdenCompra") String idOrdenCompra){
		ResultadoRestVO<String> resultado = new ResultadoRestVO<String>();
		 try {
    		resultado.setObjetoResultado(compraServiceLocal.generarReporteOrdenCompra(idOrdenCompra));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	
	@GET
    @Path("/listarDetalleOrdenVenta")
	public ResultadoRestVO<DetalleOrdenCompraDTO> listarDetalleOrdenVenta(@Context UriInfo info){
		ResultadoRestVO<DetalleOrdenCompraDTO> resultado = new ResultadoRestVO<DetalleOrdenCompraDTO>();
		DetalleOrdenCompraDTO detalleOrdenCompra = transferUriInfoDetalle(info);
		 try {
			resultado.setListaResultado(compraServiceLocal.listarDetalleOrdenCompra(detalleOrdenCompra));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	
	private DetalleOrdenCompraDTO transferUriInfoDetalle(@Context UriInfo info) {
		DetalleOrdenCompraDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,DetalleOrdenCompraDTO.class);
		return resultado;
	}
}