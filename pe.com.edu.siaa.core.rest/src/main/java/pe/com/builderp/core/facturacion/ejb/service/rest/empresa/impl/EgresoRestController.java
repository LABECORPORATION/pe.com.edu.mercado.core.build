package pe.com.builderp.core.facturacion.ejb.service.rest.empresa.impl;

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

import pe.com.builderp.core.facturacion.ejb.service.empresa.local.EmpresaServiceLocal;
import pe.com.builderp.core.facturacion.model.dto.empresa.EgresoDTO;
import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.service.rest.impl.GenericServiceRestImpl;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class EgresoRestController.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu Dec 28 12:02:21 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
@Path("/egresoRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class EgresoRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient EmpresaServiceLocal empresaServiceLocal;
	
	@POST
	public ResultadoRestVO<EgresoDTO> crear(EgresoDTO Egreso) throws Exception {
		return controladorAccion(Egreso,AccionType.CREAR);
	}
	@PUT
	public ResultadoRestVO<EgresoDTO> modificar(EgresoDTO Egreso) throws Exception {
		return controladorAccion(Egreso,AccionType.MODIFICAR);
	}
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<EgresoDTO> eliminar(@PathParam("id") String idEgreso) throws Exception {
		EgresoDTO Egreso = new EgresoDTO();
		Egreso.setIdEgreso(idEgreso);		
		return controladorAccion(Egreso,AccionType.ELIMINAR);
	}
	private ResultadoRestVO<EgresoDTO> controladorAccion(EgresoDTO Egreso, AccionType accionType){
		ResultadoRestVO<EgresoDTO> resultado = new ResultadoRestVO<EgresoDTO>();
		 try {
    		resultado.setObjetoResultado(empresaServiceLocal.controladorAccionEgreso(Egreso,accionType));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<EgresoDTO> finById(@PathParam("id") String idEgreso) throws Exception {
		EgresoDTO Egreso = new EgresoDTO();
		Egreso.setIdEgreso(idEgreso);
		return controladorAccion(Egreso,AccionType.FIND_BY_ID);
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<EgresoDTO> listarEgreso(@Context UriInfo info){
		ResultadoRestVO<EgresoDTO> resultado = new ResultadoRestVO<EgresoDTO>();
		EgresoDTO Egreso = transferUriInfo(info);
		 try {
			resultado.setListaResultado(empresaServiceLocal.listarEgreso(Egreso));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/contar")
	public ResultadoRestVO<EgresoDTO> contarEgreso(@Context UriInfo info){
		ResultadoRestVO<EgresoDTO> resultado = new ResultadoRestVO<EgresoDTO>();
		EgresoDTO Egreso = transferUriInfo(info);
		 try {
			 resultado.setContador(empresaServiceLocal.contarListarEgreso(Egreso));
			 if (resultado.isData()) {
				resultado.setListaResultado(empresaServiceLocal.listarEgreso(Egreso));
			 }
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/")
	public ResultadoRestVO<EgresoDTO> inicializarEgreso(@Context UriInfo info) throws Exception {
	     EgresoDTO Egreso = transferUriInfo(info);
		 ResultadoRestVO<EgresoDTO> resultado = new ResultadoRestVO<EgresoDTO>();
		 try {
    		resultado.setObjetoResultado(Egreso);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private EgresoDTO transferUriInfo(@Context UriInfo info) {
		EgresoDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,EgresoDTO.class);
		return resultado;
	}
}