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
import pe.com.edu.siaa.core.model.dto.seguridad.ConfiguracionMenuDTO;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class ConfiguracionMenuRestController.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Mon Oct 16 14:31:00 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
@Path("/configuracionMenuRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class ConfiguracionMenuRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient SeguridadServiceLocal seguridadServiceLocal;
	
	@POST
	public ResultadoRestVO<ConfiguracionMenuDTO> crear(ConfiguracionMenuDTO configuracionMenu) throws Exception {
		return controladorAccion(configuracionMenu,AccionType.CREAR);
	}
	@PUT
	public ResultadoRestVO<ConfiguracionMenuDTO> modificar(ConfiguracionMenuDTO configuracionMenu) throws Exception {
		return controladorAccion(configuracionMenu,AccionType.MODIFICAR);
	}
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<ConfiguracionMenuDTO> eliminar(@PathParam("id") String idConfiguracionMenu) throws Exception {
		ConfiguracionMenuDTO configuracionMenu = new ConfiguracionMenuDTO();
		configuracionMenu.setIdConfiguracionMenu(idConfiguracionMenu);		
		return controladorAccion(configuracionMenu,AccionType.ELIMINAR);
	}
	private ResultadoRestVO<ConfiguracionMenuDTO> controladorAccion(ConfiguracionMenuDTO configuracionMenu, AccionType accionType){
		ResultadoRestVO<ConfiguracionMenuDTO> resultado = new ResultadoRestVO<ConfiguracionMenuDTO>();
		 try {
    		resultado.setObjetoResultado(seguridadServiceLocal.controladorAccionConfiguracionMenu(configuracionMenu,accionType));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<ConfiguracionMenuDTO> finById(@PathParam("id") String idConfiguracionMenu) throws Exception {
		ConfiguracionMenuDTO configuracionMenu = new ConfiguracionMenuDTO();
		configuracionMenu.setIdConfiguracionMenu(idConfiguracionMenu);
		return controladorAccion(configuracionMenu,AccionType.FIND_BY_ID);
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<ConfiguracionMenuDTO> listarConfiguracionMenu(@Context UriInfo info){
		ResultadoRestVO<ConfiguracionMenuDTO> resultado = new ResultadoRestVO<ConfiguracionMenuDTO>();
		ConfiguracionMenuDTO configuracionMenu = transferUriInfo(info);
		 try {
			resultado.setListaResultado(seguridadServiceLocal.listarConfiguracionMenu(configuracionMenu));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/contar")
	public ResultadoRestVO<ConfiguracionMenuDTO> contarConfiguracionMenu(@Context UriInfo info){
		ResultadoRestVO<ConfiguracionMenuDTO> resultado = new ResultadoRestVO<ConfiguracionMenuDTO>();
		ConfiguracionMenuDTO configuracionMenu = transferUriInfo(info);
		 try {
			 resultado.setContador(seguridadServiceLocal.contarListarConfiguracionMenu(configuracionMenu));
			 if (resultado.isData()) {
				resultado.setListaResultado(seguridadServiceLocal.listarConfiguracionMenu(configuracionMenu));
			 }
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/")
	public ResultadoRestVO<ConfiguracionMenuDTO> inicializarConfiguracionMenu(@Context UriInfo info) throws Exception {
	     ConfiguracionMenuDTO configuracionMenu = transferUriInfo(info);
		 ResultadoRestVO<ConfiguracionMenuDTO> resultado = new ResultadoRestVO<ConfiguracionMenuDTO>();
		 try {
    		resultado.setObjetoResultado(configuracionMenu);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private ConfiguracionMenuDTO transferUriInfo(@Context UriInfo info) {
		ConfiguracionMenuDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,ConfiguracionMenuDTO.class);
		return resultado;
	}
}