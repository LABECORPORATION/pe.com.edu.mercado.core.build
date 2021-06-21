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
import pe.com.builderp.core.facturacion.model.dto.empresa.CuotaConceptoDTO;
import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil; 
import pe.com.edu.siaa.core.ejb.service.rest.impl.GenericServiceRestImpl; 
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class CuotaConceptoRestController.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 13:43:24 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
@Path("/cuotaConceptoRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class CuotaConceptoRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient EmpresaServiceLocal empresaServiceLocal;
	
	@POST
	public ResultadoRestVO<CuotaConceptoDTO> crear(CuotaConceptoDTO cuotaConcepto) throws Exception {
		//CuotaConceptoDTO filtro = new  CuotaConceptoDTO(); 
		return controladorAccion(cuotaConcepto,AccionType.CREAR);
		/*Integer cantidad = empresaServiceLocal.contarListarCuotaConcepto(filtro);
		if (cantidad == 0) {
			return controladorAccion(cuotaConcepto,AccionType.CREAR);
		} else {
			ResultadoRestVO<CuotaConceptoDTO> resultado = new ResultadoRestVO<CuotaConceptoDTO>();
			resultado.setError(true);
			resultado.setCodigoError("MSG");
			resultado.setMensajeError("El Concepto ya esta registrado");
			return resultado;
		}*/
	}
	@PUT
	public ResultadoRestVO<CuotaConceptoDTO> modificar(CuotaConceptoDTO cuotaConcepto) throws Exception {
		return controladorAccion(cuotaConcepto,AccionType.MODIFICAR);
	}
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<CuotaConceptoDTO> eliminar(@PathParam("id") String idCuotaConcepto) throws Exception {
		CuotaConceptoDTO cuotaConcepto = new CuotaConceptoDTO();
		cuotaConcepto.setIdCuotaConcepto(idCuotaConcepto);		
		return controladorAccion(cuotaConcepto,AccionType.ELIMINAR);
	}
	private ResultadoRestVO<CuotaConceptoDTO> controladorAccion(CuotaConceptoDTO cuotaConcepto, AccionType accionType){
		ResultadoRestVO<CuotaConceptoDTO> resultado = new ResultadoRestVO<CuotaConceptoDTO>();
		 try {
    		resultado.setObjetoResultado(empresaServiceLocal.controladorAccionCuotaConcepto(cuotaConcepto,accionType));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<CuotaConceptoDTO> finById(@PathParam("id") String idCuotaConcepto) throws Exception {
		CuotaConceptoDTO cuotaConcepto = new CuotaConceptoDTO();
		cuotaConcepto.setIdCuotaConcepto(idCuotaConcepto);
		return controladorAccion(cuotaConcepto,AccionType.FIND_BY_ID);
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<CuotaConceptoDTO> listarCuotaConcepto(@Context UriInfo info){
		ResultadoRestVO<CuotaConceptoDTO> resultado = new ResultadoRestVO<CuotaConceptoDTO>();
		CuotaConceptoDTO cuotaConcepto = transferUriInfo(info);
		 try {
    		resultado.setListaResultado(empresaServiceLocal.listarCuotaConcepto(cuotaConcepto));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/contar")
	public ResultadoRestVO<CuotaConceptoDTO> contarListarCuotaConcepto(@Context UriInfo info){
		ResultadoRestVO<CuotaConceptoDTO> resultado = new ResultadoRestVO<CuotaConceptoDTO>();
		CuotaConceptoDTO cuotaConcepto = transferUriInfo(info);
		 try {
    		resultado.setContador(empresaServiceLocal.contarListarCuotaConcepto(cuotaConcepto));
    		if (resultado.isData()) {
    			resultado.setListaResultado(empresaServiceLocal.listarCuotaConcepto(cuotaConcepto));
    		}
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/")
	public ResultadoRestVO<CuotaConceptoDTO> inicializarCuotaConcepto(@Context UriInfo info) throws Exception {
	     CuotaConceptoDTO cuotaConcepto = transferUriInfo(info);
		 ResultadoRestVO<CuotaConceptoDTO> resultado = new ResultadoRestVO<CuotaConceptoDTO>();
		 try {
    		resultado.setObjetoResultado(cuotaConcepto);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private CuotaConceptoDTO transferUriInfo(@Context UriInfo info) {
		CuotaConceptoDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,CuotaConceptoDTO.class);
 
		return resultado;
	}
}