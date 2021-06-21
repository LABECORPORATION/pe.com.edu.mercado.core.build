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
import pe.com.edu.siaa.core.model.dto.seguridad.SistemaDTO;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class SistemaRestController.
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
@Path("/sistemaRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class SistemaRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient SeguridadServiceLocal seguridadServiceLocal;
	
	@POST
	public ResultadoRestVO<SistemaDTO> crear(SistemaDTO sistema) throws Exception {
		return controladorAccion(sistema,AccionType.CREAR);
	}
	@PUT
	public ResultadoRestVO<SistemaDTO> modificar(SistemaDTO sistema) throws Exception {
		return controladorAccion(sistema,AccionType.MODIFICAR);
	}
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<SistemaDTO> eliminar(@PathParam("id") Long idSistema) throws Exception {
		SistemaDTO sistema = new SistemaDTO();
		sistema.setIdSistema(idSistema);		
		return controladorAccion(sistema,AccionType.ELIMINAR);
	}
	private ResultadoRestVO<SistemaDTO> controladorAccion(SistemaDTO sistema, AccionType accionType){
		ResultadoRestVO<SistemaDTO> resultado = new ResultadoRestVO<SistemaDTO>();
		 try {
    		resultado.setObjetoResultado(seguridadServiceLocal.controladorAccionSistema(sistema,accionType));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<SistemaDTO> finById(@PathParam("id") Long idSistema) throws Exception {
		SistemaDTO sistema = new SistemaDTO();
		sistema.setIdSistema(idSistema);
		return controladorAccion(sistema,AccionType.FIND_BY_ID);
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<SistemaDTO> listarSistema(@Context UriInfo info){
		ResultadoRestVO<SistemaDTO> resultado = new ResultadoRestVO<SistemaDTO>();
		SistemaDTO sistema = transferUriInfo(info);
		 try {
			resultado.setListaResultado(seguridadServiceLocal.listarSistema(sistema));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/contar")
	public ResultadoRestVO<SistemaDTO> contarSistema(@Context UriInfo info){
		ResultadoRestVO<SistemaDTO> resultado = new ResultadoRestVO<SistemaDTO>();
		SistemaDTO sistema = transferUriInfo(info);
		 try {
			 resultado.setContador(seguridadServiceLocal.contarListarSistema(sistema));
			 if (resultado.isData()) {
				resultado.setListaResultado(seguridadServiceLocal.listarSistema(sistema));
			 }
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/")
	public ResultadoRestVO<SistemaDTO> inicializarSistema(@Context UriInfo info) throws Exception {
	     SistemaDTO sistema = transferUriInfo(info);
		 ResultadoRestVO<SistemaDTO> resultado = new ResultadoRestVO<SistemaDTO>();
		 try {
    		resultado.setObjetoResultado(sistema);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private SistemaDTO transferUriInfo(@Context UriInfo info) {
		SistemaDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,SistemaDTO.class);
		return resultado;
	}
}