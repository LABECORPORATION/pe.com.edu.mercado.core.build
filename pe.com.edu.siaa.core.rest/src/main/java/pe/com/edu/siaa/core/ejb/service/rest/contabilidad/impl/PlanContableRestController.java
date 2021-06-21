package pe.com.edu.siaa.core.ejb.service.rest.contabilidad.impl;

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

import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.service.contabilidad.local.ContabilidadServiceLocal;
import pe.com.edu.siaa.core.ejb.service.rest.impl.GenericServiceRestImpl;
import pe.com.edu.siaa.core.model.dto.contabilidad.PlanContableDTO;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class PlanContableRestController.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Fri Sep 08 16:33:19 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
@Path("/planContableRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class PlanContableRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient ContabilidadServiceLocal contabilidadServiceLocal;
	
	@POST
	public ResultadoRestVO<PlanContableDTO> crear(PlanContableDTO planContable) throws Exception {
		return controladorAccion(planContable,AccionType.CREAR);
	}
	@PUT
	public ResultadoRestVO<PlanContableDTO> modificar(PlanContableDTO planContable) throws Exception {
		return controladorAccion(planContable,AccionType.MODIFICAR);
	}
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<PlanContableDTO> eliminar(@PathParam("id") String idPlanContable) throws Exception {
		PlanContableDTO planContable = new PlanContableDTO();
		planContable.setIdPlanContable(idPlanContable);		
		return controladorAccion(planContable,AccionType.ELIMINAR);
	}
	private ResultadoRestVO<PlanContableDTO> controladorAccion(PlanContableDTO planContable, AccionType accionType){
		ResultadoRestVO<PlanContableDTO> resultado = new ResultadoRestVO<PlanContableDTO>();
		 try {
    		resultado.setObjetoResultado(contabilidadServiceLocal.controladorAccionPlanContable(planContable,accionType));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<PlanContableDTO> finById(@PathParam("id") String idPlanContable) throws Exception {
		PlanContableDTO planContable = new PlanContableDTO();
		planContable.setIdPlanContable(idPlanContable);
		return controladorAccion(planContable,AccionType.FIND_BY_ID);
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<PlanContableDTO> listarPlanContable(@Context UriInfo info){
		ResultadoRestVO<PlanContableDTO> resultado = new ResultadoRestVO<PlanContableDTO>();
		PlanContableDTO planContable = transferUriInfo(info);
		 try {
			resultado.setListaResultado(contabilidadServiceLocal.listarPlanContable(planContable));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/contar")
	public ResultadoRestVO<PlanContableDTO> contarPlanContable(@Context UriInfo info){
		ResultadoRestVO<PlanContableDTO> resultado = new ResultadoRestVO<PlanContableDTO>();
		PlanContableDTO planContable = transferUriInfo(info);
		 try {
			 resultado.setContador(contabilidadServiceLocal.contarListarPlanContable(planContable));
			 if (resultado.isData()) {
				resultado.setListaResultado(contabilidadServiceLocal.listarPlanContable(planContable));
			 }
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/")
	public ResultadoRestVO<PlanContableDTO> inicializarPlanContable(@Context UriInfo info) throws Exception {
	     PlanContableDTO planContable = transferUriInfo(info);
		 ResultadoRestVO<PlanContableDTO> resultado = new ResultadoRestVO<PlanContableDTO>();
		 try {
    		resultado.setObjetoResultado(planContable);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private PlanContableDTO transferUriInfo(@Context UriInfo info) {
		PlanContableDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,PlanContableDTO.class);
		return resultado;
	}
}