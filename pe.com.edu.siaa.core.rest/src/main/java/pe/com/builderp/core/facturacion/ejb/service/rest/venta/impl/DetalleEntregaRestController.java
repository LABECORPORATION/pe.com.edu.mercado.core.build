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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import pe.com.builderp.core.facturacion.ejb.service.venta.local.VentaServiceLocal;
import pe.com.builderp.core.facturacion.model.dto.venta.DetalleEntregaDTO;
import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.service.rest.impl.GenericServiceRestImpl;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class DetalleEntregaRestController.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu Dec 28 12:02:22 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
@Path("/detalleEntregaRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class DetalleEntregaRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient VentaServiceLocal ventaServiceLocal;
	
	@POST
	public ResultadoRestVO<DetalleEntregaDTO> crear(DetalleEntregaDTO detalleEntrega) throws Exception {
		return controladorAccion(detalleEntrega,AccionType.CREAR);
	}
	@PUT
	public ResultadoRestVO<DetalleEntregaDTO> modificar(DetalleEntregaDTO detalleEntrega) throws Exception {
		return controladorAccion(detalleEntrega,AccionType.MODIFICAR);
	}
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<DetalleEntregaDTO> eliminar(@PathParam("id") String idDetalleEntrega) throws Exception {
		DetalleEntregaDTO detalleEntrega = new DetalleEntregaDTO();
		detalleEntrega.setIdDetalleEntrega(idDetalleEntrega);		
		return controladorAccion(detalleEntrega,AccionType.ELIMINAR);
	}
	private ResultadoRestVO<DetalleEntregaDTO> controladorAccion(DetalleEntregaDTO detalleEntrega, AccionType accionType){
		ResultadoRestVO<DetalleEntregaDTO> resultado = new ResultadoRestVO<DetalleEntregaDTO>();
		 try {
    		resultado.setObjetoResultado(ventaServiceLocal.controladorAccionDetalleEntrega(detalleEntrega,null,accionType));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<DetalleEntregaDTO> finById(@PathParam("id") String idDetalleEntrega) throws Exception {
		DetalleEntregaDTO detalleEntrega = new DetalleEntregaDTO();
		detalleEntrega.setIdDetalleEntrega(idDetalleEntrega);
		return controladorAccion(detalleEntrega,AccionType.FIND_BY_ID);
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<DetalleEntregaDTO> listarDetalleEntrega(@Context UriInfo info){
		ResultadoRestVO<DetalleEntregaDTO> resultado = new ResultadoRestVO<DetalleEntregaDTO>();
		DetalleEntregaDTO detalleEntrega = transferUriInfo(info);
		 try {
			resultado.setListaResultado(ventaServiceLocal.listarDetalleEntrega(detalleEntrega));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/contar")
	public ResultadoRestVO<DetalleEntregaDTO> contarDetalleEntrega(@Context UriInfo info){
		ResultadoRestVO<DetalleEntregaDTO> resultado = new ResultadoRestVO<DetalleEntregaDTO>();
		DetalleEntregaDTO detalleEntrega = transferUriInfo(info);
		 try {
			 resultado.setContador(ventaServiceLocal.contarListarDetalleEntrega(detalleEntrega));
			 if (resultado.isData()) {
				resultado.setListaResultado(ventaServiceLocal.listarDetalleEntrega(detalleEntrega));
			 }
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/")
	public ResultadoRestVO<DetalleEntregaDTO> inicializarDetalleEntrega(@Context UriInfo info) throws Exception {
	     DetalleEntregaDTO detalleEntrega = transferUriInfo(info);
		 ResultadoRestVO<DetalleEntregaDTO> resultado = new ResultadoRestVO<DetalleEntregaDTO>();
		 try {
    		resultado.setObjetoResultado(detalleEntrega);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private DetalleEntregaDTO transferUriInfo(@Context UriInfo info) {
		DetalleEntregaDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,DetalleEntregaDTO.class);
		return resultado;
	}
}