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
import pe.com.builderp.core.facturacion.model.dto.empresa.ActividadDTO; 
import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.service.rest.impl.GenericServiceRestImpl;
import pe.com.edu.siaa.core.model.type.AccionType; 
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class ActividadRestController.
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
@Path("/actividadRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class ActividadRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient EmpresaServiceLocal empresaServiceLocal;
	
	@POST
	public ResultadoRestVO<ActividadDTO> crear(ActividadDTO Actividad) throws Exception {
		return controladorAccion(Actividad,AccionType.CREAR);
	}
	@PUT
	public ResultadoRestVO<ActividadDTO> modificar(ActividadDTO Actividad) throws Exception {
		return controladorAccion(Actividad,AccionType.MODIFICAR);
	}
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<ActividadDTO> eliminar(@PathParam("id") String idActividad) throws Exception {
		ActividadDTO Actividad = new ActividadDTO();
		Actividad.setIdActividad(idActividad);		
		return controladorAccion(Actividad,AccionType.ELIMINAR);
	}
	private ResultadoRestVO<ActividadDTO> controladorAccion(ActividadDTO Actividad, AccionType accionType){
		ResultadoRestVO<ActividadDTO> resultado = new ResultadoRestVO<ActividadDTO>();
		 try {
    		resultado.setObjetoResultado(empresaServiceLocal.controladorAccionActividad(Actividad,accionType));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<ActividadDTO> finById(@PathParam("id") String idActividad) throws Exception {
		ActividadDTO Actividad = new ActividadDTO();
		Actividad.setIdActividad(idActividad);
		return controladorAccion(Actividad,AccionType.FIND_BY_ID);
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<ActividadDTO> listarActividad(@Context UriInfo info){
		ResultadoRestVO<ActividadDTO> resultado = new ResultadoRestVO<ActividadDTO>();
		ActividadDTO Actividad = transferUriInfo(info);
		 try {
			resultado.setListaResultado(empresaServiceLocal.listarActividad(Actividad));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/contar")
	public ResultadoRestVO<ActividadDTO> contarActividad(@Context UriInfo info){
		ResultadoRestVO<ActividadDTO> resultado = new ResultadoRestVO<ActividadDTO>();
		ActividadDTO Actividad = transferUriInfo(info);
		 try {
			 resultado.setContador(empresaServiceLocal.contarListarActividad(Actividad));
			 if (resultado.isData()) {
				resultado.setListaResultado(empresaServiceLocal.listarActividad(Actividad));
			 }
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/")
	public ResultadoRestVO<ActividadDTO> inicializarActividad(@Context UriInfo info) throws Exception {
	     ActividadDTO Actividad = transferUriInfo(info);
		 ResultadoRestVO<ActividadDTO> resultado = new ResultadoRestVO<ActividadDTO>();
		 try {
    		resultado.setObjetoResultado(Actividad);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private ActividadDTO transferUriInfo(@Context UriInfo info) {
		ActividadDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,ActividadDTO.class);
		return resultado;
	}
	
	 
}