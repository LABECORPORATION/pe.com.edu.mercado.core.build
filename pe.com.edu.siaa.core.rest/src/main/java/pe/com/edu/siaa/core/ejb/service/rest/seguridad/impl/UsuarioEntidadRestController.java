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
import pe.com.edu.siaa.core.model.dto.seguridad.UsuarioEntidadDTO;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class UsuarioEntidadRestController.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Mon Oct 16 14:31:03 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
@Path("/usuarioEntidadRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioEntidadRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient SeguridadServiceLocal seguridadServiceLocal;
	
	@POST
	public ResultadoRestVO<UsuarioEntidadDTO> crear(UsuarioEntidadDTO usuarioEntidad) throws Exception {
		return controladorAccion(usuarioEntidad,AccionType.CREAR);
	}
	@PUT
	public ResultadoRestVO<UsuarioEntidadDTO> modificar(UsuarioEntidadDTO usuarioEntidad) throws Exception {
		return controladorAccion(usuarioEntidad,AccionType.MODIFICAR);
	}
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<UsuarioEntidadDTO> eliminar(@PathParam("id") String idUsuarioEntidad) throws Exception {
		UsuarioEntidadDTO usuarioEntidad = new UsuarioEntidadDTO();
		usuarioEntidad.setIdUsuarioEntidad(idUsuarioEntidad);		
		return controladorAccion(usuarioEntidad,AccionType.ELIMINAR);
	}
	private ResultadoRestVO<UsuarioEntidadDTO> controladorAccion(UsuarioEntidadDTO usuarioEntidad, AccionType accionType){
		ResultadoRestVO<UsuarioEntidadDTO> resultado = new ResultadoRestVO<UsuarioEntidadDTO>();
		 try {
    		resultado.setObjetoResultado(seguridadServiceLocal.controladorAccionUsuarioEntidad(usuarioEntidad,accionType));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<UsuarioEntidadDTO> finById(@PathParam("id") String idUsuarioEntidad) throws Exception {
		UsuarioEntidadDTO usuarioEntidad = new UsuarioEntidadDTO();
		usuarioEntidad.setIdUsuarioEntidad(idUsuarioEntidad);
		return controladorAccion(usuarioEntidad,AccionType.FIND_BY_ID);
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<UsuarioEntidadDTO> listarUsuarioEntidad(@Context UriInfo info){
		ResultadoRestVO<UsuarioEntidadDTO> resultado = new ResultadoRestVO<UsuarioEntidadDTO>();
		UsuarioEntidadDTO usuarioEntidad = transferUriInfo(info);
		 try {
			resultado.setListaResultado(seguridadServiceLocal.listarUsuarioEntidad(usuarioEntidad));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/contar")
	public ResultadoRestVO<UsuarioEntidadDTO> contarUsuarioEntidad(@Context UriInfo info){
		ResultadoRestVO<UsuarioEntidadDTO> resultado = new ResultadoRestVO<UsuarioEntidadDTO>();
		UsuarioEntidadDTO usuarioEntidad = transferUriInfo(info);
		 try {
			 resultado.setContador(seguridadServiceLocal.contarListarUsuarioEntidad(usuarioEntidad));
			 if (resultado.isData()) {
				resultado.setListaResultado(seguridadServiceLocal.listarUsuarioEntidad(usuarioEntidad));
			 }
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/")
	public ResultadoRestVO<UsuarioEntidadDTO> inicializarUsuarioEntidad(@Context UriInfo info) throws Exception {
	     UsuarioEntidadDTO usuarioEntidad = transferUriInfo(info);
		 ResultadoRestVO<UsuarioEntidadDTO> resultado = new ResultadoRestVO<UsuarioEntidadDTO>();
		 try {
    		resultado.setObjetoResultado(usuarioEntidad);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private UsuarioEntidadDTO transferUriInfo(@Context UriInfo info) {
		UsuarioEntidadDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,UsuarioEntidadDTO.class);
		return resultado;
	}
}