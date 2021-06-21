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
import pe.com.edu.siaa.core.model.dto.seguridad.TipoUsuarioDTO;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class TipoUsuarioRestController.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu Dec 14 00:27:45 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
@Path("/tipoUsuarioRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class TipoUsuarioRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient SeguridadServiceLocal seguridadServiceLocal;
	
	@POST
	public ResultadoRestVO<TipoUsuarioDTO> crear(TipoUsuarioDTO tipoUsuario) throws Exception {
		return controladorAccion(tipoUsuario,AccionType.CREAR);
	}
	@PUT
	public ResultadoRestVO<TipoUsuarioDTO> modificar(TipoUsuarioDTO tipoUsuario) throws Exception {
		return controladorAccion(tipoUsuario,AccionType.MODIFICAR);
	}
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<TipoUsuarioDTO> eliminar(@PathParam("id") Long idTipoUsuario) throws Exception {
		TipoUsuarioDTO tipoUsuario = new TipoUsuarioDTO();
		tipoUsuario.setIdTipoUsuario(idTipoUsuario);		
		return controladorAccion(tipoUsuario,AccionType.ELIMINAR);
	}
	private ResultadoRestVO<TipoUsuarioDTO> controladorAccion(TipoUsuarioDTO tipoUsuario, AccionType accionType){
		ResultadoRestVO<TipoUsuarioDTO> resultado = new ResultadoRestVO<TipoUsuarioDTO>();
		 try {
    		resultado.setObjetoResultado(seguridadServiceLocal.controladorAccionTipoUsuario(tipoUsuario,accionType));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<TipoUsuarioDTO> finById(@PathParam("id") Long idTipoUsuario) throws Exception {
		TipoUsuarioDTO tipoUsuario = new TipoUsuarioDTO();
		tipoUsuario.setIdTipoUsuario(idTipoUsuario);
		return controladorAccion(tipoUsuario,AccionType.FIND_BY_ID);
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<TipoUsuarioDTO> listarTipoUsuario(@Context UriInfo info){
		ResultadoRestVO<TipoUsuarioDTO> resultado = new ResultadoRestVO<TipoUsuarioDTO>();
		TipoUsuarioDTO tipoUsuario = transferUriInfo(info);
		 try {
			resultado.setListaResultado(seguridadServiceLocal.listarTipoUsuario(tipoUsuario));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/contar")
	public ResultadoRestVO<TipoUsuarioDTO> contarTipoUsuario(@Context UriInfo info){
		ResultadoRestVO<TipoUsuarioDTO> resultado = new ResultadoRestVO<TipoUsuarioDTO>();
		TipoUsuarioDTO tipoUsuario = transferUriInfo(info);
		 try {
			 resultado.setContador(seguridadServiceLocal.contarListarTipoUsuario(tipoUsuario));
			 if (resultado.isData()) {
				resultado.setListaResultado(seguridadServiceLocal.listarTipoUsuario(tipoUsuario));
			 }
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/")
	public ResultadoRestVO<TipoUsuarioDTO> inicializarTipoUsuario(@Context UriInfo info) throws Exception {
	     TipoUsuarioDTO tipoUsuario = transferUriInfo(info);
		 ResultadoRestVO<TipoUsuarioDTO> resultado = new ResultadoRestVO<TipoUsuarioDTO>();
		 try {
    		resultado.setObjetoResultado(tipoUsuario);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private TipoUsuarioDTO transferUriInfo(@Context UriInfo info) {
		TipoUsuarioDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,TipoUsuarioDTO.class);
		return resultado;
	}
}