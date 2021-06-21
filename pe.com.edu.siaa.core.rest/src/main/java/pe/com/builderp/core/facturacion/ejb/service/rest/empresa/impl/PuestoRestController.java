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
import pe.com.builderp.core.facturacion.model.dto.empresa.PuestoDTO; 
import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.service.rest.impl.GenericServiceRestImpl;
import pe.com.edu.siaa.core.model.type.AccionType; 
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class PuestoRestController.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu Dec 28 12:02:21 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
@Path("/puestoRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class PuestoRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient EmpresaServiceLocal empresaServiceLocal;
	
	@POST
	public ResultadoRestVO<PuestoDTO> crear(PuestoDTO Puesto) throws Exception {
		return controladorAccion(Puesto,AccionType.CREAR);
	}
	@PUT
	public ResultadoRestVO<PuestoDTO> modificar(PuestoDTO Puesto) throws Exception {
		return controladorAccion(Puesto,AccionType.MODIFICAR);
	}
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<PuestoDTO> eliminar(@PathParam("id") String idPuesto) throws Exception {
		PuestoDTO Puesto = new PuestoDTO();
		Puesto.setIdPuesto(idPuesto);		
		return controladorAccion(Puesto,AccionType.ELIMINAR);
	}
	private ResultadoRestVO<PuestoDTO> controladorAccion(PuestoDTO Puesto, AccionType accionType){
		ResultadoRestVO<PuestoDTO> resultado = new ResultadoRestVO<PuestoDTO>();
		 try {
    		resultado.setObjetoResultado(empresaServiceLocal.controladorAccionPuesto(Puesto,accionType));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<PuestoDTO> finById(@PathParam("id") String idPuesto) throws Exception {
		PuestoDTO Puesto = new PuestoDTO();
		Puesto.setIdPuesto(idPuesto);
		return controladorAccion(Puesto,AccionType.FIND_BY_ID);
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<PuestoDTO> listarPuesto(@Context UriInfo info){
		ResultadoRestVO<PuestoDTO> resultado = new ResultadoRestVO<PuestoDTO>();
		PuestoDTO Puesto = transferUriInfo(info);
		 try {
			resultado.setListaResultado(empresaServiceLocal.listarPuesto(Puesto));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/contar")
	public ResultadoRestVO<PuestoDTO> contarPuesto(@Context UriInfo info){
		ResultadoRestVO<PuestoDTO> resultado = new ResultadoRestVO<PuestoDTO>();
		PuestoDTO Puesto = transferUriInfo(info);
		 try {
			 resultado.setContador(empresaServiceLocal.contarListarPuesto(Puesto));
			 if (resultado.isData()) {
				resultado.setListaResultado(empresaServiceLocal.listarPuesto(Puesto));
			 }
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/")
	public ResultadoRestVO<PuestoDTO> inicializarPuesto(@Context UriInfo info) throws Exception {
	     PuestoDTO Puesto = transferUriInfo(info);
		 ResultadoRestVO<PuestoDTO> resultado = new ResultadoRestVO<PuestoDTO>();
		 try {
    		resultado.setObjetoResultado(Puesto);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private PuestoDTO transferUriInfo(@Context UriInfo info) {
		PuestoDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,PuestoDTO.class);
		return resultado;
	}
	
 
}