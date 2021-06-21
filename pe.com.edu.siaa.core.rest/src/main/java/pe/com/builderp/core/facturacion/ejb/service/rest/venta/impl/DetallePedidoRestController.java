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
import pe.com.builderp.core.facturacion.model.dto.venta.DetallePedidoDTO;
import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.service.rest.impl.GenericServiceRestImpl;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class DetallePedidoRestController.
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
@Path("/detallePedidoRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class DetallePedidoRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient VentaServiceLocal ventaServiceLocal;
	
	@POST
	public ResultadoRestVO<DetallePedidoDTO> crear(DetallePedidoDTO detallePedido) throws Exception {
		return controladorAccion(detallePedido,AccionType.CREAR);
	}
	@PUT
	public ResultadoRestVO<DetallePedidoDTO> modificar(DetallePedidoDTO detallePedido) throws Exception {
		return controladorAccion(detallePedido,AccionType.MODIFICAR);
	}
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<DetallePedidoDTO> eliminar(@PathParam("id") String idDetallePedido) throws Exception {
		DetallePedidoDTO detallePedido = new DetallePedidoDTO();
		detallePedido.setIdDetallePedido(idDetallePedido);		
		return controladorAccion(detallePedido,AccionType.ELIMINAR);
	}
	private ResultadoRestVO<DetallePedidoDTO> controladorAccion(DetallePedidoDTO detallePedido, AccionType accionType){
		ResultadoRestVO<DetallePedidoDTO> resultado = new ResultadoRestVO<DetallePedidoDTO>();
		 try {
    		resultado.setObjetoResultado(ventaServiceLocal.controladorAccionDetallePedido(detallePedido,null,accionType));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<DetallePedidoDTO> finById(@PathParam("id") String idDetallePedido) throws Exception {
		DetallePedidoDTO detallePedido = new DetallePedidoDTO();
		detallePedido.setIdDetallePedido(idDetallePedido);
		return controladorAccion(detallePedido,AccionType.FIND_BY_ID);
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<DetallePedidoDTO> listarDetallePedido(@Context UriInfo info){
		ResultadoRestVO<DetallePedidoDTO> resultado = new ResultadoRestVO<DetallePedidoDTO>();
		DetallePedidoDTO detallePedido = transferUriInfo(info);
		 try {
			resultado.setListaResultado(ventaServiceLocal.listarDetallePedido(detallePedido));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/contar")
	public ResultadoRestVO<DetallePedidoDTO> contarDetallePedido(@Context UriInfo info){
		ResultadoRestVO<DetallePedidoDTO> resultado = new ResultadoRestVO<DetallePedidoDTO>();
		DetallePedidoDTO detallePedido = transferUriInfo(info);
		 try {
			 resultado.setContador(ventaServiceLocal.contarListarDetallePedido(detallePedido));
			 if (resultado.isData()) {
				resultado.setListaResultado(ventaServiceLocal.listarDetallePedido(detallePedido));
			 }
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/")
	public ResultadoRestVO<DetallePedidoDTO> inicializarDetallePedido(@Context UriInfo info) throws Exception {
	     DetallePedidoDTO detallePedido = transferUriInfo(info);
		 ResultadoRestVO<DetallePedidoDTO> resultado = new ResultadoRestVO<DetallePedidoDTO>();
		 try {
    		resultado.setObjetoResultado(detallePedido);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private DetallePedidoDTO transferUriInfo(@Context UriInfo info) {
		DetallePedidoDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,DetallePedidoDTO.class);
		return resultado;
	}
}