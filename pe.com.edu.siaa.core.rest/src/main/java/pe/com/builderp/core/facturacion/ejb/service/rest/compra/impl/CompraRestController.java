package pe.com.builderp.core.facturacion.ejb.service.rest.compra.impl;

import java.util.Date;

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

import pe.com.builderp.core.facturacion.ejb.service.compra.local.CompraServiceLocal;
import pe.com.builderp.core.facturacion.model.dto.compra.CompraDTO;
import pe.com.builderp.core.facturacion.model.dto.compra.DetalleCompraDTO;
import pe.com.builderp.core.facturacion.model.dto.venta.ModeloDTO;
import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.seguridad.jwt.rsa.util.AppHTTPHeaderNames;
import pe.com.edu.siaa.core.ejb.service.rest.impl.GenericServiceRestImpl;
import pe.com.edu.siaa.core.ejb.util.cache.AppAuthenticator;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class CompraRestController.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu Dec 28 12:02:16 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
@Path("/compraRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class CompraRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient CompraServiceLocal compraServiceLocal;
	
	@POST
	public ResultadoRestVO<CompraDTO> registrarCompra(@Context HttpHeaders httpHeaders,CompraDTO compra) throws Exception {
		ResultadoRestVO<CompraDTO> resultado = new ResultadoRestVO<CompraDTO>();
		String serviceKey = httpHeaders.getHeaderString( AppHTTPHeaderNames.SERVICE_KEY );
		String authToken = httpHeaders.getHeaderString( AppHTTPHeaderNames.AUTH_TOKEN );
		String ip = httpHeaders.getHeaderString( AppHTTPHeaderNames.ORIGIN );
		compra.setIpAcceso(ip);
		compra.setServiceKey(serviceKey);
		compra.setAuthToken(authToken);		
		 try {
			 resultado.setObjetoResultado(compraServiceLocal.registrarCompra(compra));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<String> eliminar(@Context HttpHeaders httpHeaders,@PathParam("id") String idCompra) throws Exception {		
		ResultadoRestVO<String> resultado = new ResultadoRestVO<String>();
		String authToken = httpHeaders.getHeaderString( AppHTTPHeaderNames.AUTH_TOKEN );
		 try {
			 String userName = AppAuthenticator.getInstance().getUserName(authToken);
			 compraServiceLocal.eliminarCompra(idCompra, userName);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	

	
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<CompraDTO> listarCompra(@Context UriInfo info){
		ResultadoRestVO<CompraDTO> resultado = new ResultadoRestVO<CompraDTO>();
		CompraDTO compra = transferUriInfo(info);
		 try {
			resultado.setListaResultado(compraServiceLocal.listarCompra(compra));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/contar")
	public ResultadoRestVO<CompraDTO> contarCompra(@Context UriInfo info){
		ResultadoRestVO<CompraDTO> resultado = new ResultadoRestVO<CompraDTO>();
		CompraDTO compra = transferUriInfo(info);
		 try {
			 resultado.setContador(compraServiceLocal.contarListarCompra(compra));
			 if (resultado.isData()) {
				resultado.setListaResultado(compraServiceLocal.listarCompra(compra));
			 }
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/")
	public ResultadoRestVO<CompraDTO> inicializarCompra(@Context UriInfo info) throws Exception {
	     CompraDTO compra = transferUriInfo(info);
		 ResultadoRestVO<CompraDTO> resultado = new ResultadoRestVO<CompraDTO>();
		 try {
    		resultado.setObjetoResultado(compra);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	
	@GET
    @Path("/generarReporteRegistroCompra/{idEntidad}/{idLibro}/{idSubLibro}/{fechaAsientoDesde}/{fechaAsientoHasta}")
	public ResultadoRestVO<String> generarReporteRegistroCompra(@Context HttpHeaders httpHeaders,@PathParam("idEntidad") String idEntidad,@PathParam("idLibro") Long idLibro,@PathParam("idSubLibro") Long idSubLibro, @PathParam("fechaAsientoDesde") Date fechaAsientoDesde, @PathParam("fechaAsientoHasta") Date fechaAsientoHasta){
		ResultadoRestVO<String> resultado = new ResultadoRestVO<String>();
		String serviceKey = httpHeaders.getHeaderString( AppHTTPHeaderNames.SERVICE_KEY );
		String authToken = httpHeaders.getHeaderString( AppHTTPHeaderNames.AUTH_TOKEN );
		 try {
			String userName = AppAuthenticator.getInstance().getUserName(authToken);
    		resultado.setObjetoResultado(compraServiceLocal.generarReporteRegistroCompra(userName,idEntidad, idLibro, idSubLibro,fechaAsientoDesde,fechaAsientoHasta));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	
	@GET
    @Path("/listarDetalleCompra")
	public ResultadoRestVO<DetalleCompraDTO> listarDetalleCompra(@Context UriInfo info){
		ResultadoRestVO<DetalleCompraDTO> resultado = new ResultadoRestVO<DetalleCompraDTO>();
		DetalleCompraDTO detalleCompra = transferUriInfoDetalle(info);
		 try {
			resultado.setListaResultado(compraServiceLocal.listarDetalleCompra(detalleCompra));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	
	private DetalleCompraDTO transferUriInfoDetalle(@Context UriInfo info) {
		DetalleCompraDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,DetalleCompraDTO.class);
		return resultado;
	}
	private CompraDTO transferUriInfo(@Context UriInfo info) {
		CompraDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,CompraDTO.class);
		return resultado;
	}
}