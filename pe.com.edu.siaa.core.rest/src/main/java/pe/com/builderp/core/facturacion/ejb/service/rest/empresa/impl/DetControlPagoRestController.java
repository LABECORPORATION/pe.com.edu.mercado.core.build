package pe.com.builderp.core.facturacion.ejb.service.rest.empresa.impl;

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

import pe.com.builderp.core.facturacion.ejb.service.empresa.local.EmpresaServiceLocal;
import pe.com.builderp.core.facturacion.model.dto.empresa.DetControlPagoDTO;
import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil; 
import pe.com.edu.siaa.core.ejb.service.rest.impl.GenericServiceRestImpl; 
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class DetControlPagoRestController.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 13:43:37 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
@Path("/detControlPagoRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class DetControlPagoRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient EmpresaServiceLocal empresaServiceLocal;
	
	@POST
	public ResultadoRestVO<DetControlPagoDTO> crear(DetControlPagoDTO detControlPago) throws Exception {
		return controladorAccion(detControlPago,AccionType.CREAR);
	}
	@PUT
	public ResultadoRestVO<DetControlPagoDTO> modificar(DetControlPagoDTO detControlPago) throws Exception {
		return controladorAccion(detControlPago,AccionType.MODIFICAR);
	}
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<DetControlPagoDTO> eliminar(@PathParam("id") String idDetControlPago) throws Exception {
		DetControlPagoDTO detControlPago = new DetControlPagoDTO();
		detControlPago.setIdDetControlPago(idDetControlPago);		
		return controladorAccion(detControlPago,AccionType.ELIMINAR);
	}
	
	
	//add
	
	@DELETE
	@Path("eliminar_manual/{id}")
	public ResultadoRestVO<DetControlPagoDTO> eliminar_manual(@PathParam("id") String idDetControlPago) throws Exception {
		DetControlPagoDTO detControlPago = new DetControlPagoDTO();
		detControlPago.setIdDetControlPago(idDetControlPago);		
		return controladorAccion(detControlPago,AccionType.ELIMINAR_MANUAL);
	}
	
	private ResultadoRestVO<DetControlPagoDTO> controladorAccion(DetControlPagoDTO detControlPago, AccionType accionType){
		ResultadoRestVO<DetControlPagoDTO> resultado = new ResultadoRestVO<DetControlPagoDTO>();
		 try {
    		resultado.setObjetoResultado(empresaServiceLocal.controladorAccionDetControlPago(detControlPago,accionType));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<DetControlPagoDTO> finById(@PathParam("id") String idDetControlPago) throws Exception {
		DetControlPagoDTO detControlPago = new DetControlPagoDTO();
		detControlPago.setIdDetControlPago(idDetControlPago);
		return controladorAccion(detControlPago,AccionType.FIND_BY_ID);
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<DetControlPagoDTO> listarDetControlPago(@Context UriInfo info){
		ResultadoRestVO<DetControlPagoDTO> resultado = new ResultadoRestVO<DetControlPagoDTO>();
		DetControlPagoDTO detControlPago = transferUriInfo(info);
		 try {
    		resultado.setListaResultado(empresaServiceLocal.listarDetControlPago(detControlPago));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/contar")
	public ResultadoRestVO<DetControlPagoDTO> contarListarDetControlPago(@Context UriInfo info){
		ResultadoRestVO<DetControlPagoDTO> resultado = new ResultadoRestVO<DetControlPagoDTO>();
		DetControlPagoDTO detControlPago = transferUriInfo(info);
		 try {
    		resultado.setContador(empresaServiceLocal.contarListarDetControlPago(detControlPago));
    		if (resultado.isData()) {
    			resultado.setListaResultado(empresaServiceLocal.listarDetControlPago(detControlPago));
    		}
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/")
	public ResultadoRestVO<DetControlPagoDTO> inicializarDetControlPago(@Context UriInfo info) throws Exception {
	     DetControlPagoDTO detControlPago = transferUriInfo(info);
		 ResultadoRestVO<DetControlPagoDTO> resultado = new ResultadoRestVO<DetControlPagoDTO>();
		 try {
    		resultado.setObjetoResultado(detControlPago);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private DetControlPagoDTO transferUriInfo(@Context UriInfo info) {
		DetControlPagoDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,DetControlPagoDTO.class);
		return resultado;
	}
	//agregando
		@GET
		@Path("/verDetallePagosRealizados/{idControlPago}")
		public ResultadoRestVO<DetControlPagoDTO>  verDetallePagosRealizados(@PathParam("idControlPago") String idControlPago) throws Exception {
			ResultadoRestVO<DetControlPagoDTO> resultado = new ResultadoRestVO<DetControlPagoDTO>();
			 try {
				 resultado.setListaResultado(empresaServiceLocal.verDetallePagosRealizados(idControlPago));
			} catch (Exception e) {
				parsearResultadoError(e, resultado);
			}
			return resultado;
		}
	
		@GET
		@Path("/obtenerDetControlPagoAlumno/{idAsociado}/{idAnio}")
		public ResultadoRestVO<DetControlPagoDTO> obtenerDetControlPagoAlumno(@PathParam("idAsociado") String idAsociado,@PathParam("idAnio") String idAnio)
				throws Exception {
			ResultadoRestVO<DetControlPagoDTO> resultado = new ResultadoRestVO<DetControlPagoDTO>();
			try {
				resultado.setListaResultado(empresaServiceLocal.obtenerDetControlPagoAsociado(idAsociado, idAnio));
			} catch (Exception e) {
				parsearResultadoError(e, resultado);
			}
			return resultado;
		}
}