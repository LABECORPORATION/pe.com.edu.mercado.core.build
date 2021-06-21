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
import pe.com.edu.siaa.core.model.dto.seguridad.PropertiesLenguajeDTO;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class PropertiesLenguajeRestController.
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
@Path("/propertiesLenguajeRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class PropertiesLenguajeRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient SeguridadServiceLocal seguridadServiceLocal;
	
	@POST
	public ResultadoRestVO<PropertiesLenguajeDTO> crear(PropertiesLenguajeDTO propertiesLenguaje) throws Exception {
		return controladorAccion(propertiesLenguaje,AccionType.CREAR);
	}
	@PUT
	public ResultadoRestVO<PropertiesLenguajeDTO> modificar(PropertiesLenguajeDTO propertiesLenguaje) throws Exception {
		return controladorAccion(propertiesLenguaje,AccionType.MODIFICAR);
	}
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<PropertiesLenguajeDTO> eliminar(@PathParam("id") String idPropertiesLenguaje) throws Exception {
		PropertiesLenguajeDTO propertiesLenguaje = new PropertiesLenguajeDTO();
		propertiesLenguaje.setIdPropertiesLenguaje(idPropertiesLenguaje);		
		return controladorAccion(propertiesLenguaje,AccionType.ELIMINAR);
	}
	private ResultadoRestVO<PropertiesLenguajeDTO> controladorAccion(PropertiesLenguajeDTO propertiesLenguaje, AccionType accionType){
		ResultadoRestVO<PropertiesLenguajeDTO> resultado = new ResultadoRestVO<PropertiesLenguajeDTO>();
		 try {
    		resultado.setObjetoResultado(seguridadServiceLocal.controladorAccionPropertiesLenguaje(propertiesLenguaje,accionType));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<PropertiesLenguajeDTO> finById(@PathParam("id") String idPropertiesLenguaje) throws Exception {
		PropertiesLenguajeDTO propertiesLenguaje = new PropertiesLenguajeDTO();
		propertiesLenguaje.setIdPropertiesLenguaje(idPropertiesLenguaje);
		return controladorAccion(propertiesLenguaje,AccionType.FIND_BY_ID);
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<PropertiesLenguajeDTO> listarPropertiesLenguaje(@Context UriInfo info){
		ResultadoRestVO<PropertiesLenguajeDTO> resultado = new ResultadoRestVO<PropertiesLenguajeDTO>();
		PropertiesLenguajeDTO propertiesLenguaje = transferUriInfo(info);
		 try {
			resultado.setListaResultado(seguridadServiceLocal.listarPropertiesLenguaje(propertiesLenguaje));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/contar")
	public ResultadoRestVO<PropertiesLenguajeDTO> contarPropertiesLenguaje(@Context UriInfo info){
		ResultadoRestVO<PropertiesLenguajeDTO> resultado = new ResultadoRestVO<PropertiesLenguajeDTO>();
		PropertiesLenguajeDTO propertiesLenguaje = transferUriInfo(info);
		 try {
			 resultado.setContador(seguridadServiceLocal.contarListarPropertiesLenguaje(propertiesLenguaje));
			 if (resultado.isData()) {
				resultado.setListaResultado(seguridadServiceLocal.listarPropertiesLenguaje(propertiesLenguaje));
			 }
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/")
	public ResultadoRestVO<PropertiesLenguajeDTO> inicializarPropertiesLenguaje(@Context UriInfo info) throws Exception {
	     PropertiesLenguajeDTO propertiesLenguaje = transferUriInfo(info);
		 ResultadoRestVO<PropertiesLenguajeDTO> resultado = new ResultadoRestVO<PropertiesLenguajeDTO>();
		 try {
    		resultado.setObjetoResultado(propertiesLenguaje);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private PropertiesLenguajeDTO transferUriInfo(@Context UriInfo info) {
		PropertiesLenguajeDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,PropertiesLenguajeDTO.class);
		return resultado;
	}
}