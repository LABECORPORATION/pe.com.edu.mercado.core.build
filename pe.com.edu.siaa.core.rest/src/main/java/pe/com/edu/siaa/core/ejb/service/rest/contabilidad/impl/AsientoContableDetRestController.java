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
import pe.com.edu.siaa.core.model.dto.contabilidad.AsientoContableDetDTO;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class AsientoContableDetRestController.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Fri Sep 08 16:33:19 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
@Path("/asientoContableDetRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class AsientoContableDetRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient ContabilidadServiceLocal contabilidadServiceLocal;
	
	@POST
	public ResultadoRestVO<AsientoContableDetDTO> crear(AsientoContableDetDTO asientoContableDet) throws Exception {
		return controladorAccion(asientoContableDet,AccionType.CREAR);
	}
	@PUT
	public ResultadoRestVO<AsientoContableDetDTO> modificar(AsientoContableDetDTO asientoContableDet) throws Exception {
		return controladorAccion(asientoContableDet,AccionType.MODIFICAR);
	}
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<AsientoContableDetDTO> eliminar(@PathParam("id") String idAsientoContableDet) throws Exception {
		AsientoContableDetDTO asientoContableDet = new AsientoContableDetDTO();
		asientoContableDet.setIdAsientoContableDet(idAsientoContableDet);		
		return controladorAccion(asientoContableDet,AccionType.ELIMINAR);
	}
	private ResultadoRestVO<AsientoContableDetDTO> controladorAccion(AsientoContableDetDTO asientoContableDet, AccionType accionType){
		ResultadoRestVO<AsientoContableDetDTO> resultado = new ResultadoRestVO<AsientoContableDetDTO>();
		 try {
    		//resultado.setObjetoResultado(contabilidadServiceLocal.controladorAccionAsientoContableDet(asientoContableDet,accionType));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<AsientoContableDetDTO> finById(@PathParam("id") String idAsientoContableDet) throws Exception {
		AsientoContableDetDTO asientoContableDet = new AsientoContableDetDTO();
		asientoContableDet.setIdAsientoContableDet(idAsientoContableDet);
		return controladorAccion(asientoContableDet,AccionType.FIND_BY_ID);
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<AsientoContableDetDTO> listarAsientoContableDet(@Context UriInfo info){
		ResultadoRestVO<AsientoContableDetDTO> resultado = new ResultadoRestVO<AsientoContableDetDTO>();
		AsientoContableDetDTO asientoContableDet = transferUriInfo(info);
		 try {
			resultado.setListaResultado(contabilidadServiceLocal.listarAsientoContableDet(asientoContableDet));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/contar")
	public ResultadoRestVO<AsientoContableDetDTO> contarAsientoContableDet(@Context UriInfo info){
		ResultadoRestVO<AsientoContableDetDTO> resultado = new ResultadoRestVO<AsientoContableDetDTO>();
		AsientoContableDetDTO asientoContableDet = transferUriInfo(info);
		 try {
			 resultado.setContador(contabilidadServiceLocal.contarListarAsientoContableDet(asientoContableDet));
			 if (resultado.isData()) {
				resultado.setListaResultado(contabilidadServiceLocal.listarAsientoContableDet(asientoContableDet));
			 }
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/")
	public ResultadoRestVO<AsientoContableDetDTO> inicializarAsientoContableDet(@Context UriInfo info) throws Exception {
	     AsientoContableDetDTO asientoContableDet = transferUriInfo(info);
		 ResultadoRestVO<AsientoContableDetDTO> resultado = new ResultadoRestVO<AsientoContableDetDTO>();
		 try {
    		resultado.setObjetoResultado(asientoContableDet);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private AsientoContableDetDTO transferUriInfo(@Context UriInfo info) {
		AsientoContableDetDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,AsientoContableDetDTO.class);
		return resultado;
	}
}