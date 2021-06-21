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
import pe.com.builderp.core.facturacion.model.dto.empresa.IngresoDTO;
import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.service.rest.impl.GenericServiceRestImpl;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class IngresoRestController.
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
@Path("/ingresoRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class IngresoRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient EmpresaServiceLocal empresaServiceLocal;
	
	@POST
	public ResultadoRestVO<IngresoDTO> crear(IngresoDTO Ingreso) throws Exception {
		return controladorAccion(Ingreso,AccionType.CREAR);
	}
	@PUT
	public ResultadoRestVO<IngresoDTO> modificar(IngresoDTO Ingreso) throws Exception {
		return controladorAccion(Ingreso,AccionType.MODIFICAR);
	}
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<IngresoDTO> eliminar(@PathParam("id") String idIngreso) throws Exception {
		IngresoDTO Ingreso = new IngresoDTO();
		Ingreso.setIdIngreso(idIngreso);		
		return controladorAccion(Ingreso,AccionType.ELIMINAR);
	}
	private ResultadoRestVO<IngresoDTO> controladorAccion(IngresoDTO Ingreso, AccionType accionType){
		ResultadoRestVO<IngresoDTO> resultado = new ResultadoRestVO<IngresoDTO>();
		 try {
    		resultado.setObjetoResultado(empresaServiceLocal.controladorAccionIngreso(Ingreso,accionType));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<IngresoDTO> finById(@PathParam("id") String idIngreso) throws Exception {
		IngresoDTO Ingreso = new IngresoDTO();
		Ingreso.setIdIngreso(idIngreso);
		return controladorAccion(Ingreso,AccionType.FIND_BY_ID);
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<IngresoDTO> listarIngreso(@Context UriInfo info){
		ResultadoRestVO<IngresoDTO> resultado = new ResultadoRestVO<IngresoDTO>();
		IngresoDTO Ingreso = transferUriInfo(info);
		 try {
			resultado.setListaResultado(empresaServiceLocal.listarIngreso(Ingreso));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/contar")
	public ResultadoRestVO<IngresoDTO> contarIngreso(@Context UriInfo info){
		ResultadoRestVO<IngresoDTO> resultado = new ResultadoRestVO<IngresoDTO>();
		IngresoDTO Ingreso = transferUriInfo(info);
		 try {
			 resultado.setContador(empresaServiceLocal.contarListarIngreso(Ingreso));
			 if (resultado.isData()) {
				resultado.setListaResultado(empresaServiceLocal.listarIngreso(Ingreso));
			 }
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/")
	public ResultadoRestVO<IngresoDTO> inicializarIngreso(@Context UriInfo info) throws Exception {
	     IngresoDTO Ingreso = transferUriInfo(info);
		 ResultadoRestVO<IngresoDTO> resultado = new ResultadoRestVO<IngresoDTO>();
		 try {
    		resultado.setObjetoResultado(Ingreso);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private IngresoDTO transferUriInfo(@Context UriInfo info) {
		IngresoDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,IngresoDTO.class);
		return resultado;
	}
}