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
import pe.com.builderp.core.facturacion.model.dto.compra.DetalleOrdenCompraDTO;
import pe.com.builderp.core.facturacion.model.dto.compra.OrdenCompraDTO;
import pe.com.builderp.core.facturacion.model.dto.venta.DetallePedidoDTO;
import pe.com.builderp.core.facturacion.model.dto.venta.PedidoDTO;
import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.seguridad.jwt.rsa.util.AppHTTPHeaderNames;
import pe.com.edu.siaa.core.ejb.service.rest.impl.GenericServiceRestImpl;
import pe.com.edu.siaa.core.ejb.util.cache.AppAuthenticator;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class PedidoRestController.
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
@Path("/pedidoRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class PedidoRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient VentaServiceLocal ventaServiceLocal;
	
	@POST
	public ResultadoRestVO<PedidoDTO> crear(@Context HttpHeaders httpHeaders,PedidoDTO pedido) throws Exception {
		ResultadoRestVO<PedidoDTO> resultado = new ResultadoRestVO<PedidoDTO>();
		String serviceKey = httpHeaders.getHeaderString( AppHTTPHeaderNames.SERVICE_KEY );
		String authToken = httpHeaders.getHeaderString( AppHTTPHeaderNames.AUTH_TOKEN );
		String ip = httpHeaders.getHeaderString( AppHTTPHeaderNames.ORIGIN );
		pedido.setIpAcceso(ip);
		pedido.setServiceKey(serviceKey);
		pedido.setAuthToken(authToken);	
		 try {
			 resultado.setObjetoResultado(ventaServiceLocal.registrarPedidoDTO(pedido));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		 return resultado;
		//return controladorAccion(pedido,AccionType.CREAR);
	}
	@PUT
	public ResultadoRestVO<PedidoDTO> modificar(PedidoDTO pedido) throws Exception {
		return controladorAccion(pedido,AccionType.MODIFICAR);
	}
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<String> eliminar(@Context HttpHeaders httpHeaders,@PathParam("id") String idPedido) throws Exception {
		ResultadoRestVO<String> resultado = new ResultadoRestVO<String>();
		String authToken = httpHeaders.getHeaderString( AppHTTPHeaderNames.AUTH_TOKEN );
		 try {
			 String userName = AppAuthenticator.getInstance().getUserName(authToken);
			 ventaServiceLocal.eliminarPedido(idPedido,userName);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private ResultadoRestVO<PedidoDTO> controladorAccion(PedidoDTO pedido, AccionType accionType){
		ResultadoRestVO<PedidoDTO> resultado = new ResultadoRestVO<PedidoDTO>();
		 try {
    		resultado.setObjetoResultado(ventaServiceLocal.controladorAccionPedido(pedido,accionType));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<PedidoDTO> finById(@PathParam("id") String idPedido) throws Exception {
		PedidoDTO pedido = new PedidoDTO();
		pedido.setIdPedido(idPedido);
		return controladorAccion(pedido,AccionType.FIND_BY_ID);
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<PedidoDTO> listarPedido(@Context UriInfo info){
		ResultadoRestVO<PedidoDTO> resultado = new ResultadoRestVO<PedidoDTO>();
		PedidoDTO pedido = transferUriInfo(info);
		 try {
			resultado.setListaResultado(ventaServiceLocal.listarPedido(pedido));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/contar")
	public ResultadoRestVO<PedidoDTO> contarPedido(@Context UriInfo info){
		ResultadoRestVO<PedidoDTO> resultado = new ResultadoRestVO<PedidoDTO>();
		PedidoDTO pedido = transferUriInfo(info);
		 try {
			 resultado.setContador(ventaServiceLocal.contarListarPedido(pedido));
			 if (resultado.isData()) {
				resultado.setListaResultado(ventaServiceLocal.listarPedido(pedido));
			 }
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/")
	public ResultadoRestVO<PedidoDTO> inicializarPedido(@Context UriInfo info) throws Exception {
	     PedidoDTO pedido = transferUriInfo(info);
		 ResultadoRestVO<PedidoDTO> resultado = new ResultadoRestVO<PedidoDTO>();
		 try {
    		resultado.setObjetoResultado(pedido);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private PedidoDTO transferUriInfo(@Context UriInfo info) {
		PedidoDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,PedidoDTO.class);
		return resultado;
	}
	
	
	@GET
    @Path("/listarDetallePedido")
	public ResultadoRestVO<DetallePedidoDTO> listarDetalleOrdenVenta(@Context UriInfo info){
		ResultadoRestVO<DetallePedidoDTO> resultado = new ResultadoRestVO<DetallePedidoDTO>();
		DetallePedidoDTO detalleOrdenCompra = transferUriInfoDetalle(info);
		 try {
			resultado.setListaResultado(ventaServiceLocal.listarDetallePedido(detalleOrdenCompra));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	
	private DetallePedidoDTO transferUriInfoDetalle(@Context UriInfo info) {
		DetallePedidoDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,DetallePedidoDTO.class);
		return resultado;
	}
}