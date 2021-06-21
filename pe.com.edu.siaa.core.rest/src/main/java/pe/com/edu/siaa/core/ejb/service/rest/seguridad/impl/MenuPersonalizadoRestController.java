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
import pe.com.edu.siaa.core.model.dto.seguridad.MenuDTO;
import pe.com.edu.siaa.core.model.dto.seguridad.MenuPersonalizadoDTO;
import pe.com.edu.siaa.core.model.dto.seguridad.SistemaDTO;
import pe.com.edu.siaa.core.model.estate.EstadoGeneralState;
import pe.com.edu.siaa.core.model.util.ObjectUtil;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class MenuPersonalizadoRestController.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Mon Oct 16 14:31:02 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
@Path("/menuPersonalizadoRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class MenuPersonalizadoRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient SeguridadServiceLocal seguridadServiceLocal;
	
	@POST
	public ResultadoRestVO<MenuPersonalizadoDTO> asociarMenuPersonalizado(@Context HttpHeaders httpHeaders,List<MenuDTO> listaMenu) throws Exception {
		ResultadoRestVO<MenuPersonalizadoDTO> resultado = new ResultadoRestVO<MenuPersonalizadoDTO>();
		 try {
			  String authToken = httpHeaders.getHeaderString( AppHTTPHeaderNames.AUTH_TOKEN );
			  String userName = AppAuthenticator.getInstance().getUserName(authToken);
			  seguridadServiceLocal.asociarMenuPersonalizadoByUsuario(listaMenu,userName);
			  resultado.setObjetoResultado(new MenuPersonalizadoDTO());
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<MenuDTO> listarMenu(@Context UriInfo info){
		ResultadoRestVO<MenuDTO> resultado = new ResultadoRestVO<MenuDTO>();
		MenuDTO menu = transferUriInfo(info);
		 try {
			resultado.setListaResultado(obtenerMenuCheck(menu,info.getQueryParameters().getFirst("idUsuario")));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/contar")
	public ResultadoRestVO<MenuDTO> contarMenu(@Context UriInfo info){
		ResultadoRestVO<MenuDTO> resultado = new ResultadoRestVO<MenuDTO>();
		MenuDTO menu = transferUriInfo(info);
		 try {
			 resultado.setContador(seguridadServiceLocal.contarListarMenu(menu));
			 if (resultado.isData()) {
				resultado.setListaResultado(obtenerMenuCheck(menu,info.getQueryParameters().getFirst("idUsuario")));
			 }
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private List<MenuDTO> obtenerMenuCheck(MenuDTO menu,String idUsuario) throws Exception {
		List<MenuDTO> resultado = null;
		Map<Long,MenuPersonalizadoDTO> menuPersonalizadoMap = seguridadServiceLocal.listarMenuPersonalizadoMap(idUsuario,EstadoGeneralState.ACTIVO.getKey());
		resultado = seguridadServiceLocal.listarMenu(menu);
		for (MenuDTO objData : resultado) {
			if (menuPersonalizadoMap.containsKey(objData.getIdMenu())) {
				objData.setChecked(true);
			}
			
		}
		return resultado;
	}
	
	private MenuDTO transferUriInfo(@Context UriInfo info) {
		MenuDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,MenuDTO.class);
		resultado.setSistema(new SistemaDTO());
		if (info.getQueryParameters().containsKey("idSistema")) {
			Long idSistema = ObjectUtil.objectToLong(info.getQueryParameters().getFirst("idSistema"));
			resultado.getSistema().setIdSistema(idSistema);
		}
		return resultado;
	}
}