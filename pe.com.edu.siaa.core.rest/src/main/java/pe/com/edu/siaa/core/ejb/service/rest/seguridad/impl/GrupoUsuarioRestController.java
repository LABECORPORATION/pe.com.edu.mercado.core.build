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
import pe.com.edu.siaa.core.model.dto.seguridad.GrupoUsuarioDTO;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class GrupoUsuarioRestController.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Mon Oct 16 14:31:01 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
@Path("/grupoUsuarioRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class GrupoUsuarioRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient SeguridadServiceLocal seguridadServiceLocal;
	
	@POST
	public ResultadoRestVO<GrupoUsuarioDTO> crear(GrupoUsuarioDTO grupoUsuario) throws Exception {
		return controladorAccion(grupoUsuario,AccionType.CREAR);
	}
	@PUT
	public ResultadoRestVO<GrupoUsuarioDTO> modificar(GrupoUsuarioDTO grupoUsuario) throws Exception {
		return controladorAccion(grupoUsuario,AccionType.MODIFICAR);
	}
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<GrupoUsuarioDTO> eliminar(@PathParam("id") Long idGrupoUsuario) throws Exception {
		GrupoUsuarioDTO grupoUsuario = new GrupoUsuarioDTO();
		grupoUsuario.setIdGrupoUsuario(idGrupoUsuario);		
		return controladorAccion(grupoUsuario,AccionType.ELIMINAR);
	}
	private ResultadoRestVO<GrupoUsuarioDTO> controladorAccion(GrupoUsuarioDTO grupoUsuario, AccionType accionType){
		ResultadoRestVO<GrupoUsuarioDTO> resultado = new ResultadoRestVO<GrupoUsuarioDTO>();
		 try {
    		resultado.setObjetoResultado(seguridadServiceLocal.controladorAccionGrupoUsuario(grupoUsuario,accionType));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<GrupoUsuarioDTO> finById(@PathParam("id") Long idGrupoUsuario) throws Exception {
		GrupoUsuarioDTO grupoUsuario = new GrupoUsuarioDTO();
		grupoUsuario.setIdGrupoUsuario(idGrupoUsuario);
		return controladorAccion(grupoUsuario,AccionType.FIND_BY_ID);
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<GrupoUsuarioDTO> listarGrupoUsuario(@Context UriInfo info){
		ResultadoRestVO<GrupoUsuarioDTO> resultado = new ResultadoRestVO<GrupoUsuarioDTO>();
		GrupoUsuarioDTO grupoUsuario = transferUriInfo(info);
		 try {
			resultado.setListaResultado(seguridadServiceLocal.listarGrupoUsuario(grupoUsuario));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/contar")
	public ResultadoRestVO<GrupoUsuarioDTO> contarGrupoUsuario(@Context UriInfo info){
		ResultadoRestVO<GrupoUsuarioDTO> resultado = new ResultadoRestVO<GrupoUsuarioDTO>();
		GrupoUsuarioDTO grupoUsuario = transferUriInfo(info);
		 try {
			 resultado.setContador(seguridadServiceLocal.contarListarGrupoUsuario(grupoUsuario));
			 if (resultado.isData()) {
				resultado.setListaResultado(seguridadServiceLocal.listarGrupoUsuario(grupoUsuario));
			 }
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/")
	public ResultadoRestVO<GrupoUsuarioDTO> inicializarGrupoUsuario(@Context UriInfo info) throws Exception {
	     GrupoUsuarioDTO grupoUsuario = transferUriInfo(info);
		 ResultadoRestVO<GrupoUsuarioDTO> resultado = new ResultadoRestVO<GrupoUsuarioDTO>();
		 try {
    		resultado.setObjetoResultado(grupoUsuario);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private GrupoUsuarioDTO transferUriInfo(@Context UriInfo info) {
		GrupoUsuarioDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,GrupoUsuarioDTO.class);
		return resultado;
	}
}