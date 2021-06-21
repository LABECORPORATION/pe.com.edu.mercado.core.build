package pe.com.edu.siaa.core.ejb.service.rest.common.impl;

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
import pe.com.edu.siaa.core.ejb.service.common.local.CommonServiceLocal;
import pe.com.edu.siaa.core.ejb.service.rest.impl.GenericServiceRestImpl;
import pe.com.edu.siaa.core.model.dto.common.UbigeoDTO;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class UbigeoRestController.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 13:43:34 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
@Path("/ubigeoRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class UbigeoRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient CommonServiceLocal commonServiceLocal;
	
	@POST
	public ResultadoRestVO<UbigeoDTO> crear(UbigeoDTO ubigeo) throws Exception {
		return controladorAccion(ubigeo,AccionType.CREAR);
	}
	@PUT
	public ResultadoRestVO<UbigeoDTO> modificar(UbigeoDTO ubigeo) throws Exception {
		return controladorAccion(ubigeo,AccionType.MODIFICAR);
	}
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<UbigeoDTO> eliminar(@PathParam("id") String idUbigeo) throws Exception {
		UbigeoDTO ubigeo = new UbigeoDTO();
		ubigeo.setIdUbigeo(idUbigeo);		
		return controladorAccion(ubigeo,AccionType.ELIMINAR);
	}
	private ResultadoRestVO<UbigeoDTO> controladorAccion(UbigeoDTO ubigeo, AccionType accionType){
		ResultadoRestVO<UbigeoDTO> resultado = new ResultadoRestVO<UbigeoDTO>();
		 try {
    		resultado.setObjetoResultado(commonServiceLocal.controladorAccionUbigeo(ubigeo,accionType));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<UbigeoDTO> finById(@PathParam("id") String idUbigeo) throws Exception {
		UbigeoDTO ubigeo = new UbigeoDTO();
		ubigeo.setIdUbigeo(idUbigeo);
		return controladorAccion(ubigeo,AccionType.FIND_BY_ID);
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<UbigeoDTO> listarUbigeo(@Context UriInfo info){
		ResultadoRestVO<UbigeoDTO> resultado = new ResultadoRestVO<UbigeoDTO>();
		UbigeoDTO ubigeo = transferUriInfo(info);
		 try {
    		resultado.setListaResultado(commonServiceLocal.listarUbigeo(ubigeo));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/contar")
	public ResultadoRestVO<UbigeoDTO> contarListarUbigeo(@Context UriInfo info){
		ResultadoRestVO<UbigeoDTO> resultado = new ResultadoRestVO<UbigeoDTO>();
		UbigeoDTO ubigeo = transferUriInfo(info);
		 try {
    		resultado.setContador(commonServiceLocal.contarListarUbigeo(ubigeo));
    		if (resultado.isData()) {
    			resultado.setListaResultado(commonServiceLocal.listarUbigeo(ubigeo));
    		}
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/")
	public ResultadoRestVO<UbigeoDTO> inicializarUbigeo(@Context UriInfo info) throws Exception {
	     UbigeoDTO ubigeo = transferUriInfo(info);
		 ResultadoRestVO<UbigeoDTO> resultado = new ResultadoRestVO<UbigeoDTO>();
		 try {
    		resultado.setObjetoResultado(ubigeo);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private UbigeoDTO transferUriInfo(@Context UriInfo info) {
		UbigeoDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,UbigeoDTO.class);
		return resultado;
	}
}