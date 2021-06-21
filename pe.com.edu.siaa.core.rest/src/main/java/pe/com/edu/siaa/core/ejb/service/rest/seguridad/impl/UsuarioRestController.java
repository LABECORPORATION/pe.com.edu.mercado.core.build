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
import pe.com.edu.siaa.core.model.dto.seguridad.UsuarioDTO;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class UsuarioRestController.
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
@Path("/usuarioRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient SeguridadServiceLocal seguridadServiceLocal;
	
	@POST
	public ResultadoRestVO<UsuarioDTO> crear(UsuarioDTO usuario) throws Exception {
		return controladorAccion(usuario,AccionType.CREAR);
	}
	@PUT
	public ResultadoRestVO<UsuarioDTO> modificar(UsuarioDTO usuario) throws Exception {
		return controladorAccion(usuario,AccionType.MODIFICAR);
	}
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<UsuarioDTO> eliminar(@PathParam("id") String idUsuario) throws Exception {
		UsuarioDTO usuario = new UsuarioDTO();
		usuario.setIdUsuario(idUsuario);		
		return controladorAccion(usuario,AccionType.ELIMINAR);
	}
	private ResultadoRestVO<UsuarioDTO> controladorAccion(UsuarioDTO usuario, AccionType accionType){
		ResultadoRestVO<UsuarioDTO> resultado = new ResultadoRestVO<UsuarioDTO>();
		 try {
    		resultado.setObjetoResultado(seguridadServiceLocal.controladorAccionUsuario(usuario,accionType));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<UsuarioDTO> finById(@PathParam("id") String idUsuario) throws Exception {
		UsuarioDTO usuario = new UsuarioDTO();
		usuario.setIdUsuario(idUsuario);
		return controladorAccion(usuario,AccionType.FIND_BY_ID);
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<UsuarioDTO> listarUsuario(@Context UriInfo info){
		ResultadoRestVO<UsuarioDTO> resultado = new ResultadoRestVO<UsuarioDTO>();
		UsuarioDTO usuario = transferUriInfo(info);
		 try {
			resultado.setListaResultado(seguridadServiceLocal.listarUsuario(usuario));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/contar")
	public ResultadoRestVO<UsuarioDTO> contarUsuario(@Context UriInfo info){
		ResultadoRestVO<UsuarioDTO> resultado = new ResultadoRestVO<UsuarioDTO>();
		UsuarioDTO usuario = transferUriInfo(info);
		 try {
			 resultado.setContador(seguridadServiceLocal.contarListarUsuario(usuario));
			 if (resultado.isData()) {
				resultado.setListaResultado(seguridadServiceLocal.listarUsuario(usuario));
			 }
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/")
	public ResultadoRestVO<UsuarioDTO> inicializarUsuario(@Context UriInfo info) throws Exception {
	     UsuarioDTO usuario = transferUriInfo(info);
		 ResultadoRestVO<UsuarioDTO> resultado = new ResultadoRestVO<UsuarioDTO>();
		 try {
    		resultado.setObjetoResultado(usuario);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private UsuarioDTO transferUriInfo(@Context UriInfo info) {
		UsuarioDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,UsuarioDTO.class);
		return resultado;
	}
	
	@GET
	@Path("/findByUsuarioID/{idUsuario}")
	public ResultadoRestVO<UsuarioDTO> findByUsuario(@PathParam("idUsuario") String idUsuario) throws Exception {
		ResultadoRestVO<UsuarioDTO> resultado = new ResultadoRestVO<UsuarioDTO>();
		 try {
   		resultado.setObjetoResultado(seguridadServiceLocal.findByUsuarioID(idUsuario));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	
	
}