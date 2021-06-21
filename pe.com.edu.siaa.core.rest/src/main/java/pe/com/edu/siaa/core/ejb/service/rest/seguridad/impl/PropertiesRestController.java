package pe.com.edu.siaa.core.ejb.service.rest.seguridad.impl;

import java.util.HashMap;
import java.util.Map;

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
import pe.com.edu.siaa.core.ejb.util.cache.CacheUtil;
import pe.com.edu.siaa.core.model.dto.seguridad.PropertiesDTO;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class PropertiesRestController.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 13:43:33 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
@Path("/propertiesRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class PropertiesRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient SeguridadServiceLocal seguridadServiceLocal;
	
	@POST
	public ResultadoRestVO<PropertiesDTO> crear(PropertiesDTO properties) throws Exception {
		return controladorAccion(properties,AccionType.CREAR);
	}
	@PUT
	public ResultadoRestVO<PropertiesDTO> modificar(PropertiesDTO properties) throws Exception {
		return controladorAccion(properties,AccionType.MODIFICAR);
	}
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<PropertiesDTO> eliminar(@PathParam("id") Long idProperties) throws Exception {
		PropertiesDTO properties = new PropertiesDTO();
		properties.setIdProperties(idProperties);		
		return controladorAccion(properties,AccionType.ELIMINAR);
	}
	private ResultadoRestVO<PropertiesDTO> controladorAccion(PropertiesDTO properties, AccionType accionType){
		ResultadoRestVO<PropertiesDTO> resultado = new ResultadoRestVO<PropertiesDTO>();
		 try {
    		resultado.setObjetoResultado(seguridadServiceLocal.controladorAccionProperties(properties,accionType));
    		CacheUtil.getInstance().setCargoCache(false);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<PropertiesDTO> finById(@PathParam("id") Long idProperties) throws Exception {
		PropertiesDTO properties = new PropertiesDTO();
		properties.setIdProperties(idProperties);
		return controladorAccion(properties,AccionType.FIND_BY_ID);
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<PropertiesDTO> listarProperties(@Context UriInfo info){
		ResultadoRestVO<PropertiesDTO> resultado = new ResultadoRestVO<PropertiesDTO>();
		PropertiesDTO properties = transferUriInfo(info);
		 try {
    		resultado.setListaResultado(seguridadServiceLocal.listarProperties(properties));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	
	@GET
    @Path("/i18n/{langs}")
	public Map<String,String> i18n(@PathParam("langs") String langs){
		Map<String,String> resultado = new HashMap<String, String>();
		PropertiesDTO properties = new PropertiesDTO();
		 try {
			// resultado = SeguridadCacheUtil.getInstance().getPropertiesMap();
			 langs = langs.replace(".json", "");
			 resultado = CacheUtil.getInstance().getPropertiesIdeomaMap().get(langs);
			// ChatLauncher.main(null);
		} catch (Exception e) {
			resultado.put("error.general", e.getMessage());
		}
		return resultado;
	}
	
	@GET
    @Path("/contar")
	public ResultadoRestVO<PropertiesDTO> contarListarProperties(@Context UriInfo info){
		ResultadoRestVO<PropertiesDTO> resultado = new ResultadoRestVO<PropertiesDTO>();
		PropertiesDTO properties = transferUriInfo(info);
		 try {
    		resultado.setContador(seguridadServiceLocal.contarListarProperties(properties));
    		if (resultado.isData()) {
    			resultado.setListaResultado(seguridadServiceLocal.listarProperties(properties));
    		}
    		
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/")
	public ResultadoRestVO<PropertiesDTO> inicializarProperties(@Context UriInfo info) throws Exception {
	     PropertiesDTO properties = transferUriInfo(info);
		 ResultadoRestVO<PropertiesDTO> resultado = new ResultadoRestVO<PropertiesDTO>();
		 try {
    		resultado.setObjetoResultado(properties);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private PropertiesDTO transferUriInfo(@Context UriInfo info) {
		PropertiesDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,PropertiesDTO.class);
		return resultado;
	}
}