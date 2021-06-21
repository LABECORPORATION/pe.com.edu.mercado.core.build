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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import pe.com.builderp.core.facturacion.ejb.service.compra.local.CompraServiceLocal;
import pe.com.builderp.core.facturacion.model.dto.compra.DetalleOrdenCompraDTO;
import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.service.rest.impl.GenericServiceRestImpl;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class DetalleOrdenCompraRestController.
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
@Path("/detalleOrdenCompraRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class DetalleOrdenCompraRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient CompraServiceLocal compraServiceLocal;
	
	@POST
	public ResultadoRestVO<DetalleOrdenCompraDTO> crear(DetalleOrdenCompraDTO detalleOrdenCompra) throws Exception {
		return controladorAccion(detalleOrdenCompra,AccionType.CREAR);
	}
	@PUT
	public ResultadoRestVO<DetalleOrdenCompraDTO> modificar(DetalleOrdenCompraDTO detalleOrdenCompra) throws Exception {
		return controladorAccion(detalleOrdenCompra,AccionType.MODIFICAR);
	}
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<DetalleOrdenCompraDTO> eliminar(@PathParam("id") String idDetalleOrdenCompra) throws Exception {
		DetalleOrdenCompraDTO detalleOrdenCompra = new DetalleOrdenCompraDTO();
		detalleOrdenCompra.setIdDetalleOrdenCompra(idDetalleOrdenCompra);		
		return controladorAccion(detalleOrdenCompra,AccionType.ELIMINAR);
	}
	private ResultadoRestVO<DetalleOrdenCompraDTO> controladorAccion(DetalleOrdenCompraDTO detalleOrdenCompra, AccionType accionType){
		ResultadoRestVO<DetalleOrdenCompraDTO> resultado = new ResultadoRestVO<DetalleOrdenCompraDTO>();
		 try {
    		resultado.setObjetoResultado(compraServiceLocal.controladorAccionDetalleOrdenCompra(detalleOrdenCompra,null,accionType));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<DetalleOrdenCompraDTO> finById(@PathParam("id") String idDetalleOrdenCompra) throws Exception {
		DetalleOrdenCompraDTO detalleOrdenCompra = new DetalleOrdenCompraDTO();
		detalleOrdenCompra.setIdDetalleOrdenCompra(idDetalleOrdenCompra);
		return controladorAccion(detalleOrdenCompra,AccionType.FIND_BY_ID);
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<DetalleOrdenCompraDTO> listarDetalleOrdenCompra(@Context UriInfo info){
		ResultadoRestVO<DetalleOrdenCompraDTO> resultado = new ResultadoRestVO<DetalleOrdenCompraDTO>();
		DetalleOrdenCompraDTO detalleOrdenCompra = transferUriInfo(info);
		 try {
			resultado.setListaResultado(compraServiceLocal.listarDetalleOrdenCompra(detalleOrdenCompra));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/contar")
	public ResultadoRestVO<DetalleOrdenCompraDTO> contarDetalleOrdenCompra(@Context UriInfo info){
		ResultadoRestVO<DetalleOrdenCompraDTO> resultado = new ResultadoRestVO<DetalleOrdenCompraDTO>();
		DetalleOrdenCompraDTO detalleOrdenCompra = transferUriInfo(info);
		 try {
			 resultado.setContador(compraServiceLocal.contarListarDetalleOrdenCompra(detalleOrdenCompra));
			 if (resultado.isData()) {
				resultado.setListaResultado(compraServiceLocal.listarDetalleOrdenCompra(detalleOrdenCompra));
			 }
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/")
	public ResultadoRestVO<DetalleOrdenCompraDTO> inicializarDetalleOrdenCompra(@Context UriInfo info) throws Exception {
	     DetalleOrdenCompraDTO detalleOrdenCompra = transferUriInfo(info);
		 ResultadoRestVO<DetalleOrdenCompraDTO> resultado = new ResultadoRestVO<DetalleOrdenCompraDTO>();
		 try {
    		resultado.setObjetoResultado(detalleOrdenCompra);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private DetalleOrdenCompraDTO transferUriInfo(@Context UriInfo info) {
		DetalleOrdenCompraDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,DetalleOrdenCompraDTO.class);
		return resultado;
	}
}