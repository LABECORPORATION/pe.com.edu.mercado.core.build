package pe.com.edu.siaa.core.ejb.service.rest.seguridad.impl;

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
import pe.com.edu.siaa.core.ejb.service.rest.impl.GenericServiceRestImpl;
import pe.com.edu.siaa.core.ejb.service.seguridad.local.SeguridadServiceLocal;
import pe.com.edu.siaa.core.model.dto.seguridad.EntidadDTO;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class EntidadRestController.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu Dec 14 00:27:42 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
@Path("/entidadRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class EntidadRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient SeguridadServiceLocal seguridadServiceLocal;
	
	@POST
	public ResultadoRestVO<EntidadDTO> crear(EntidadDTO entidad) throws Exception {
		return controladorAccion(entidad,AccionType.CREAR);
	}
	@PUT
	public ResultadoRestVO<EntidadDTO> modificar(EntidadDTO entidad) throws Exception {
		return controladorAccion(entidad,AccionType.MODIFICAR);
	}
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<EntidadDTO> eliminar(@PathParam("id") String idEntidad) throws Exception {
		EntidadDTO entidad = new EntidadDTO();
		entidad.setIdEntidad(idEntidad);		
		return controladorAccion(entidad,AccionType.ELIMINAR);
	}
	private ResultadoRestVO<EntidadDTO> controladorAccion(EntidadDTO entidad, AccionType accionType){
		ResultadoRestVO<EntidadDTO> resultado = new ResultadoRestVO<EntidadDTO>();
		 try {
    		resultado.setObjetoResultado(seguridadServiceLocal.controladorAccionEntidad(entidad,accionType));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<EntidadDTO> finById(@PathParam("id") String idEntidad) throws Exception {
		EntidadDTO entidad = new EntidadDTO();
		entidad.setIdEntidad(idEntidad);
		return controladorAccion(entidad,AccionType.FIND_BY_ID);
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<EntidadDTO> listarEntidad(@Context UriInfo info){
		ResultadoRestVO<EntidadDTO> resultado = new ResultadoRestVO<EntidadDTO>();
		EntidadDTO entidad = transferUriInfo(info);
		 try {
			resultado.setListaResultado(seguridadServiceLocal.listarEntidad(entidad));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/contar")
	public ResultadoRestVO<EntidadDTO> contarEntidad(@Context UriInfo info){
		ResultadoRestVO<EntidadDTO> resultado = new ResultadoRestVO<EntidadDTO>();
		EntidadDTO entidad = transferUriInfo(info);
		 try {
			 resultado.setContador(seguridadServiceLocal.contarListarEntidad(entidad));
			 if (resultado.isData()) {
				resultado.setListaResultado(seguridadServiceLocal.listarEntidad(entidad));
			 }
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/")
	public ResultadoRestVO<EntidadDTO> inicializarEntidad(@Context UriInfo info) throws Exception {
	     EntidadDTO entidad = transferUriInfo(info);
		 ResultadoRestVO<EntidadDTO> resultado = new ResultadoRestVO<EntidadDTO>();
		 try {
    		resultado.setObjetoResultado(entidad);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private EntidadDTO transferUriInfo(@Context UriInfo info) {
		EntidadDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,EntidadDTO.class);
		return resultado;
	}
}