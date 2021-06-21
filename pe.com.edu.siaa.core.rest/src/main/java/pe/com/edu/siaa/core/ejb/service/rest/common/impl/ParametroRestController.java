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
import pe.com.edu.siaa.core.model.dto.common.ParametroDTO;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class ParametroRestController.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu Dec 14 00:27:42 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
@Path("/parametroRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class ParametroRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient CommonServiceLocal commonServiceLocal;
	
	@POST
	public ResultadoRestVO<ParametroDTO> crear(ParametroDTO parametro) throws Exception {
		return controladorAccion(parametro,AccionType.CREAR);
	}
	@PUT
	public ResultadoRestVO<ParametroDTO> modificar(ParametroDTO parametro) throws Exception {
		return controladorAccion(parametro,AccionType.MODIFICAR);
	}
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<ParametroDTO> eliminar(@PathParam("id") String idParametro) throws Exception {
		ParametroDTO parametro = new ParametroDTO();
		parametro.setIdParametro(idParametro);		
		return controladorAccion(parametro,AccionType.ELIMINAR);
	}
	private ResultadoRestVO<ParametroDTO> controladorAccion(ParametroDTO parametro, AccionType accionType){
		ResultadoRestVO<ParametroDTO> resultado = new ResultadoRestVO<ParametroDTO>();
		 try {
    		resultado.setObjetoResultado(commonServiceLocal.controladorAccionParametro(parametro,accionType));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<ParametroDTO> finById(@PathParam("id") String idParametro) throws Exception {
		ParametroDTO parametro = new ParametroDTO();
		parametro.setIdParametro(idParametro);
		return controladorAccion(parametro,AccionType.FIND_BY_ID);
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<ParametroDTO> listarParametro(@Context UriInfo info){
		ResultadoRestVO<ParametroDTO> resultado = new ResultadoRestVO<ParametroDTO>();
		ParametroDTO parametro = transferUriInfo(info);
		 try {
			resultado.setListaResultado(commonServiceLocal.listarParametro(parametro));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/contar")
	public ResultadoRestVO<ParametroDTO> contarParametro(@Context UriInfo info){
		ResultadoRestVO<ParametroDTO> resultado = new ResultadoRestVO<ParametroDTO>();
		ParametroDTO parametro = transferUriInfo(info);
		 try {
			 resultado.setContador(commonServiceLocal.contarListarParametro(parametro));
			 if (resultado.isData()) {
				resultado.setListaResultado(commonServiceLocal.listarParametro(parametro));
			 }
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/")
	public ResultadoRestVO<ParametroDTO> inicializarParametro(@Context UriInfo info) throws Exception {
	     ParametroDTO parametro = transferUriInfo(info);
		 ResultadoRestVO<ParametroDTO> resultado = new ResultadoRestVO<ParametroDTO>();
		 try {
    		resultado.setObjetoResultado(parametro);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private ParametroDTO transferUriInfo(@Context UriInfo info) {
		ParametroDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,ParametroDTO.class);
		return resultado;
	}
}