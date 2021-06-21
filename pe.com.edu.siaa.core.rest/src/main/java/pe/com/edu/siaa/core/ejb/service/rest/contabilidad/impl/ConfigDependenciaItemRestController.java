package pe.com.edu.siaa.core.ejb.service.rest.contabilidad.impl;

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
import pe.com.edu.siaa.core.ejb.service.contabilidad.local.ContabilidadServiceLocal;
import pe.com.edu.siaa.core.ejb.service.rest.impl.GenericServiceRestImpl;
import pe.com.edu.siaa.core.model.dto.contabilidad.ConfigDependenciaItemDTO;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class ConfigDependenciaItemRestController.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Mon Oct 16 10:52:27 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
@Path("/configDependenciaItemRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class ConfigDependenciaItemRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient ContabilidadServiceLocal contabilidadServiceLocal;
	
	@POST
	public ResultadoRestVO<ConfigDependenciaItemDTO> crear(ConfigDependenciaItemDTO configDependenciaItem) throws Exception {
		return controladorAccion(configDependenciaItem,AccionType.CREAR);
	}
	@PUT
	public ResultadoRestVO<ConfigDependenciaItemDTO> modificar(ConfigDependenciaItemDTO configDependenciaItem) throws Exception {
		return controladorAccion(configDependenciaItem,AccionType.MODIFICAR);
	}
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<ConfigDependenciaItemDTO> eliminar(@PathParam("id") String idConfigDependenciaItem) throws Exception {
		ConfigDependenciaItemDTO configDependenciaItem = new ConfigDependenciaItemDTO();
		configDependenciaItem.setIdConfigDependenciaItem(idConfigDependenciaItem);		
		return controladorAccion(configDependenciaItem,AccionType.ELIMINAR);
	}
	private ResultadoRestVO<ConfigDependenciaItemDTO> controladorAccion(ConfigDependenciaItemDTO configDependenciaItem, AccionType accionType){
		ResultadoRestVO<ConfigDependenciaItemDTO> resultado = new ResultadoRestVO<ConfigDependenciaItemDTO>();
		 try {
    		resultado.setObjetoResultado(contabilidadServiceLocal.controladorAccionConfigDependenciaItem(configDependenciaItem,accionType));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<ConfigDependenciaItemDTO> finById(@PathParam("id") String idConfigDependenciaItem) throws Exception {
		ConfigDependenciaItemDTO configDependenciaItem = new ConfigDependenciaItemDTO();
		configDependenciaItem.setIdConfigDependenciaItem(idConfigDependenciaItem);
		return controladorAccion(configDependenciaItem,AccionType.FIND_BY_ID);
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<ConfigDependenciaItemDTO> listarConfigDependenciaItem(@Context UriInfo info){
		ResultadoRestVO<ConfigDependenciaItemDTO> resultado = new ResultadoRestVO<ConfigDependenciaItemDTO>();
		ConfigDependenciaItemDTO configDependenciaItem = transferUriInfo(info);
		 try {
			resultado.setListaResultado(contabilidadServiceLocal.listarConfigDependenciaItem(configDependenciaItem));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/contar")
	public ResultadoRestVO<ConfigDependenciaItemDTO> contarConfigDependenciaItem(@Context UriInfo info){
		ResultadoRestVO<ConfigDependenciaItemDTO> resultado = new ResultadoRestVO<ConfigDependenciaItemDTO>();
		ConfigDependenciaItemDTO configDependenciaItem = transferUriInfo(info);
		 try {
			 resultado.setContador(contabilidadServiceLocal.contarListarConfigDependenciaItem(configDependenciaItem));
			 if (resultado.isData()) {
				resultado.setListaResultado(contabilidadServiceLocal.listarConfigDependenciaItem(configDependenciaItem));
			 }
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/")
	public ResultadoRestVO<ConfigDependenciaItemDTO> inicializarConfigDependenciaItem(@Context UriInfo info) throws Exception {
	     ConfigDependenciaItemDTO configDependenciaItem = transferUriInfo(info);
		 ResultadoRestVO<ConfigDependenciaItemDTO> resultado = new ResultadoRestVO<ConfigDependenciaItemDTO>();
		 try {
    		resultado.setObjetoResultado(configDependenciaItem);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private ConfigDependenciaItemDTO transferUriInfo(@Context UriInfo info) {
		ConfigDependenciaItemDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,ConfigDependenciaItemDTO.class);
		return resultado;
	}
}