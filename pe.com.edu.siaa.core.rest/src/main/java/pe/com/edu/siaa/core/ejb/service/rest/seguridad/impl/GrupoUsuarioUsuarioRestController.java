package pe.com.edu.siaa.core.ejb.service.rest.seguridad.impl;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.seguridad.jwt.rsa.util.AppHTTPHeaderNames;
import pe.com.edu.siaa.core.ejb.service.rest.impl.GenericServiceRestImpl;
import pe.com.edu.siaa.core.ejb.service.seguridad.local.SeguridadServiceLocal;
import pe.com.edu.siaa.core.ejb.util.cache.AppAuthenticator;
import pe.com.edu.siaa.core.model.dto.seguridad.GrupoUsuarioDTO;
import pe.com.edu.siaa.core.model.dto.seguridad.GrupoUsuarioUsuarioDTO;
import pe.com.edu.siaa.core.model.estate.EstadoGeneralState;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class GrupoUsuarioUsuarioRestController.
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
@Path("/grupoUsuarioUsuarioRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class GrupoUsuarioUsuarioRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient SeguridadServiceLocal seguridadServiceLocal;
	
	@POST
	public ResultadoRestVO<GrupoUsuarioUsuarioDTO> asociarGrupoUsuario(@Context HttpHeaders httpHeaders,List<GrupoUsuarioDTO> listaGrupoUsuario) throws Exception {
		ResultadoRestVO<GrupoUsuarioUsuarioDTO> resultado = new ResultadoRestVO<GrupoUsuarioUsuarioDTO>();
		 try {
			  String authToken = httpHeaders.getHeaderString( AppHTTPHeaderNames.AUTH_TOKEN );
			  String userName = AppAuthenticator.getInstance().getUserName(authToken);
			  seguridadServiceLocal.asociarGrupoUsuarioByUsuario(listaGrupoUsuario,userName);
			  resultado.setObjetoResultado(new GrupoUsuarioUsuarioDTO());
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<GrupoUsuarioDTO> listarGrupoUsuarioUsuario(@Context UriInfo info){
		ResultadoRestVO<GrupoUsuarioDTO> resultado = new ResultadoRestVO<GrupoUsuarioDTO>();
		GrupoUsuarioDTO grupoUsuario = transferUriInfo(info);
		 try {
			 resultado.setListaResultado(obtenerGrupoUsuarioCheck(grupoUsuario,info.getQueryParameters().getFirst("idUsuario")));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/contar")
	public ResultadoRestVO<GrupoUsuarioDTO> contarGrupoUsuarioUsuario(@Context UriInfo info){
		ResultadoRestVO<GrupoUsuarioDTO> resultado = new ResultadoRestVO<GrupoUsuarioDTO>();
		GrupoUsuarioDTO grupoUsuario = transferUriInfo(info);
		 try {
			 resultado.setContador(seguridadServiceLocal.contarListarGrupoUsuario(grupoUsuario));
			 if (resultado.isData()) {
				resultado.setListaResultado(obtenerGrupoUsuarioCheck(grupoUsuario,info.getQueryParameters().getFirst("idUsuario")));
			 }
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private List<GrupoUsuarioDTO> obtenerGrupoUsuarioCheck(GrupoUsuarioDTO grupoUsuario,String idUsuario) throws Exception {
		List<GrupoUsuarioDTO> resultado = null;
		Map<Long,GrupoUsuarioUsuarioDTO> grupoUsuarioMap = seguridadServiceLocal.listarGrupoUsuarioUsuarioMap(idUsuario,EstadoGeneralState.ACTIVO.getKey());
		resultado = seguridadServiceLocal.listarGrupoUsuario(grupoUsuario);
		for (GrupoUsuarioDTO objData : resultado) {
			if (grupoUsuarioMap.containsKey(objData.getIdGrupoUsuario())) {
				objData.setChecked(true);
			}
			
		}
		return resultado;
	}
	
	private GrupoUsuarioDTO transferUriInfo(@Context UriInfo info) {
		GrupoUsuarioDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,GrupoUsuarioDTO.class);
		return resultado;
	}
}