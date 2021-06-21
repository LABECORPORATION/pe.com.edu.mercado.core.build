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
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.seguridad.jwt.rsa.util.AppHTTPHeaderNames;
import pe.com.edu.siaa.core.ejb.service.rest.impl.GenericServiceRestImpl;
import pe.com.edu.siaa.core.ejb.service.seguridad.local.SeguridadServiceLocal;
import pe.com.edu.siaa.core.model.dto.seguridad.MenuDTO;
import pe.com.edu.siaa.core.model.dto.seguridad.SistemaDTO;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.model.util.ObjectUtil;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class MenuRestController.
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
@Path("/menuRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class MenuRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient SeguridadServiceLocal seguridadServiceLocal;
	
	@POST
	public ResultadoRestVO<MenuDTO> crear(@Context HttpHeaders httpHeaders,MenuDTO menu) throws Exception {
		String serviceKey = httpHeaders.getHeaderString( AppHTTPHeaderNames.SERVICE_KEY );
		String authToken = httpHeaders.getHeaderString( AppHTTPHeaderNames.AUTH_TOKEN );
		menu.setServiceKey(serviceKey);
		menu.setAuthToken(authToken);
		return controladorAccion(menu,AccionType.CREAR);
	}
	@PUT
	public ResultadoRestVO<MenuDTO> modificar(@Context HttpHeaders httpHeaders,MenuDTO menu) throws Exception {
		String serviceKey = httpHeaders.getHeaderString( AppHTTPHeaderNames.SERVICE_KEY );
		String authToken = httpHeaders.getHeaderString( AppHTTPHeaderNames.AUTH_TOKEN );
		menu.setServiceKey(serviceKey);
		menu.setAuthToken(authToken);
		return controladorAccion(menu,AccionType.MODIFICAR);
	}
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<MenuDTO> eliminar(@PathParam("id") Long idMenu) throws Exception {
		MenuDTO menu = new MenuDTO();
		menu.setIdMenu(idMenu);		
		return controladorAccion(menu,AccionType.ELIMINAR);
	}
	private ResultadoRestVO<MenuDTO> controladorAccion(MenuDTO menu, AccionType accionType){
		ResultadoRestVO<MenuDTO> resultado = new ResultadoRestVO<MenuDTO>();
		 try {
    		resultado.setObjetoResultado(seguridadServiceLocal.controladorAccionMenu(menu,accionType));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<MenuDTO> finById(@PathParam("id") Long idMenu) throws Exception {
		MenuDTO menu = new MenuDTO();
		menu.setIdMenu(idMenu);
		return controladorAccion(menu,AccionType.FIND_BY_ID);
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<MenuDTO> listarMenu(@Context UriInfo info){
		ResultadoRestVO<MenuDTO> resultado = new ResultadoRestVO<MenuDTO>();
		MenuDTO menu = transferUriInfo(info);
		 try {
			resultado.setListaResultado(seguridadServiceLocal.listarMenu(menu));
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
				resultado.setListaResultado(seguridadServiceLocal.listarMenu(menu));
			 }
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/")
	public ResultadoRestVO<MenuDTO> inicializarMenu(@Context UriInfo info) throws Exception {
	     MenuDTO menu = transferUriInfo(info);
		 ResultadoRestVO<MenuDTO> resultado = new ResultadoRestVO<MenuDTO>();
		 try {
    		resultado.setObjetoResultado(menu);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
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