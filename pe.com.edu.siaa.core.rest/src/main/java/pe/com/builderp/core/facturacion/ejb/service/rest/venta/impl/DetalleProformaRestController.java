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
import pe.com.builderp.core.facturacion.model.dto.venta.DetalleProformaDTO;
import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.service.rest.impl.GenericServiceRestImpl;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class DetalleProformaRestController.
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
@Path("/detalleProformaRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class DetalleProformaRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient VentaServiceLocal ventaServiceLocal;
	
	@POST
	public ResultadoRestVO<DetalleProformaDTO> crear(DetalleProformaDTO detalleProforma) throws Exception {
		return controladorAccion(detalleProforma,AccionType.CREAR);
	}
	@PUT
	public ResultadoRestVO<DetalleProformaDTO> modificar(DetalleProformaDTO detalleProforma) throws Exception {
		return controladorAccion(detalleProforma,AccionType.MODIFICAR);
	}
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<DetalleProformaDTO> eliminar(@PathParam("id") String idDetalleProforma) throws Exception {
		DetalleProformaDTO detalleProforma = new DetalleProformaDTO();
		detalleProforma.setIdDetalleProforma(idDetalleProforma);		
		return controladorAccion(detalleProforma,AccionType.ELIMINAR);
	}
	private ResultadoRestVO<DetalleProformaDTO> controladorAccion(DetalleProformaDTO detalleProforma, AccionType accionType){
		ResultadoRestVO<DetalleProformaDTO> resultado = new ResultadoRestVO<DetalleProformaDTO>();
		 try {
    		//resultado.setObjetoResultado(ventaServiceLocal.controladorAccionDetalleProforma(detalleProforma,accionType));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<DetalleProformaDTO> finById(@PathParam("id") String idDetalleProforma) throws Exception {
		DetalleProformaDTO detalleProforma = new DetalleProformaDTO();
		detalleProforma.setIdDetalleProforma(idDetalleProforma);
		return controladorAccion(detalleProforma,AccionType.FIND_BY_ID);
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<DetalleProformaDTO> listarDetalleProforma(@Context UriInfo info){
		ResultadoRestVO<DetalleProformaDTO> resultado = new ResultadoRestVO<DetalleProformaDTO>();
		DetalleProformaDTO detalleProforma = transferUriInfo(info);
		 try {
			resultado.setListaResultado(ventaServiceLocal.listarDetalleProforma(detalleProforma));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/contar")
	public ResultadoRestVO<DetalleProformaDTO> contarDetalleProforma(@Context UriInfo info){
		ResultadoRestVO<DetalleProformaDTO> resultado = new ResultadoRestVO<DetalleProformaDTO>();
		DetalleProformaDTO detalleProforma = transferUriInfo(info);
		 try {
			 resultado.setContador(ventaServiceLocal.contarListarDetalleProforma(detalleProforma));
			 if (resultado.isData()) {
				resultado.setListaResultado(ventaServiceLocal.listarDetalleProforma(detalleProforma));
			 }
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/")
	public ResultadoRestVO<DetalleProformaDTO> inicializarDetalleProforma(@Context UriInfo info) throws Exception {
	     DetalleProformaDTO detalleProforma = transferUriInfo(info);
		 ResultadoRestVO<DetalleProformaDTO> resultado = new ResultadoRestVO<DetalleProformaDTO>();
		 try {
    		resultado.setObjetoResultado(detalleProforma);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private DetalleProformaDTO transferUriInfo(@Context UriInfo info) {
		DetalleProformaDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,DetalleProformaDTO.class);
		return resultado;
	}
}