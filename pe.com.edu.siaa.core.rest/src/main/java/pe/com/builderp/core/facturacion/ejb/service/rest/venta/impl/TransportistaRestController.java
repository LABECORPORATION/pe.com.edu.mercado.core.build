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
import pe.com.builderp.core.facturacion.model.dto.venta.TransportistaDTO;
import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.service.rest.impl.GenericServiceRestImpl;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;



/**
 * La Class TransportistaRestController.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu May 02 10:20:31 COT 2019
 * @since SIAA-CORE 2.1
 */
@Stateless
@Path("/transportistaRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class TransportistaRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient VentaServiceLocal ventaServiceLocal;
	
	@POST
	public ResultadoRestVO<TransportistaDTO> crear(TransportistaDTO transportista) throws Exception {
		return controladorAccion(transportista,AccionType.CREAR);
	}
	@PUT
	public ResultadoRestVO<TransportistaDTO> modificar(TransportistaDTO transportista) throws Exception {
		return controladorAccion(transportista,AccionType.MODIFICAR);
	}
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<TransportistaDTO> eliminar(@PathParam("id") String idTransportistaConductor) throws Exception {
		TransportistaDTO transportista = new TransportistaDTO();
		transportista.setIdTransportistaConductor(idTransportistaConductor);		
		return controladorAccion(transportista,AccionType.ELIMINAR);
	}
	private ResultadoRestVO<TransportistaDTO> controladorAccion(TransportistaDTO transportista, AccionType accionType){
		ResultadoRestVO<TransportistaDTO> resultado = new ResultadoRestVO<>();
		 try {
    		resultado.setObjetoResultado(ventaServiceLocal.controladorAccionTransportista(transportista,accionType));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<TransportistaDTO> finById(@PathParam("id") String idTransportistaConductor) throws Exception {
		TransportistaDTO transportista = new TransportistaDTO();
		transportista.setIdTransportistaConductor(idTransportistaConductor);
		return controladorAccion(transportista,AccionType.FIND_BY_ID);
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<TransportistaDTO> listarTransportista(@Context UriInfo info){
		ResultadoRestVO<TransportistaDTO> resultado = new ResultadoRestVO<>();
		TransportistaDTO transportista = transferUriInfo(info);
		 try {
			resultado.setListaResultado(ventaServiceLocal.listarTransportista(transportista));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/contar")
	public ResultadoRestVO<TransportistaDTO> contarTransportista(@Context UriInfo info){
		ResultadoRestVO<TransportistaDTO> resultado = new ResultadoRestVO<>();
		TransportistaDTO transportista = transferUriInfo(info);
		 try {
			 resultado.setContador(ventaServiceLocal.contarListarTransportista(transportista));
			 if (resultado.isData()) {
				resultado.setListaResultado(ventaServiceLocal.listarTransportista(transportista));
			 }
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/")
	public ResultadoRestVO<TransportistaDTO> inicializarTransportista(@Context UriInfo info) throws Exception {
	     TransportistaDTO transportista = transferUriInfo(info);
		 ResultadoRestVO<TransportistaDTO> resultado = new ResultadoRestVO<>();
		 try {
    		resultado.setObjetoResultado(transportista);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private TransportistaDTO transferUriInfo(@Context UriInfo info) {
		return TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,TransportistaDTO.class);
	}
}