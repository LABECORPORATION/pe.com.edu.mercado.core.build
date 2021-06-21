package pe.com.builderp.core.facturacion.ejb.service.rest.compra.impl;

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

import pe.com.builderp.core.facturacion.ejb.service.compra.local.CompraServiceLocal;
import pe.com.builderp.core.facturacion.model.dto.compra.CuentaTipoDocumentoDTO;
import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.service.rest.impl.GenericServiceRestImpl;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class CuentaTipoDocumentoRestController.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu Dec 28 12:02:17 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
@Path("/cuentaTipoDocumentoRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class CuentaTipoDocumentoRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient CompraServiceLocal compraServiceLocal;
	
	@POST
	public ResultadoRestVO<CuentaTipoDocumentoDTO> crear(CuentaTipoDocumentoDTO cuentaTipoDocumento) throws Exception {
		return controladorAccion(cuentaTipoDocumento,AccionType.CREAR);
	}
	@PUT
	public ResultadoRestVO<CuentaTipoDocumentoDTO> modificar(CuentaTipoDocumentoDTO cuentaTipoDocumento) throws Exception {
		return controladorAccion(cuentaTipoDocumento,AccionType.MODIFICAR);
	}
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<CuentaTipoDocumentoDTO> eliminar(@PathParam("id") String idCuentaTipoDocumento) throws Exception {
		CuentaTipoDocumentoDTO cuentaTipoDocumento = new CuentaTipoDocumentoDTO();
		cuentaTipoDocumento.setIdCuentaTipoDocumento(idCuentaTipoDocumento);		
		return controladorAccion(cuentaTipoDocumento,AccionType.ELIMINAR);
	}
	private ResultadoRestVO<CuentaTipoDocumentoDTO> controladorAccion(CuentaTipoDocumentoDTO cuentaTipoDocumento, AccionType accionType){
		ResultadoRestVO<CuentaTipoDocumentoDTO> resultado = new ResultadoRestVO<CuentaTipoDocumentoDTO>();
		 try {
    		resultado.setObjetoResultado(compraServiceLocal.controladorAccionCuentaTipoDocumento(cuentaTipoDocumento,accionType));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<CuentaTipoDocumentoDTO> finById(@PathParam("id") String idCuentaTipoDocumento) throws Exception {
		CuentaTipoDocumentoDTO cuentaTipoDocumento = new CuentaTipoDocumentoDTO();
		cuentaTipoDocumento.setIdCuentaTipoDocumento(idCuentaTipoDocumento);
		return controladorAccion(cuentaTipoDocumento,AccionType.FIND_BY_ID);
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<CuentaTipoDocumentoDTO> listarCuentaTipoDocumento(@Context UriInfo info){
		ResultadoRestVO<CuentaTipoDocumentoDTO> resultado = new ResultadoRestVO<CuentaTipoDocumentoDTO>();
		CuentaTipoDocumentoDTO cuentaTipoDocumento = transferUriInfo(info);
		 try {
			resultado.setListaResultado(compraServiceLocal.listarCuentaTipoDocumento(cuentaTipoDocumento));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/contar")
	public ResultadoRestVO<CuentaTipoDocumentoDTO> contarCuentaTipoDocumento(@Context UriInfo info){
		ResultadoRestVO<CuentaTipoDocumentoDTO> resultado = new ResultadoRestVO<CuentaTipoDocumentoDTO>();
		CuentaTipoDocumentoDTO cuentaTipoDocumento = transferUriInfo(info);
		 try {
			 resultado.setContador(compraServiceLocal.contarListarCuentaTipoDocumento(cuentaTipoDocumento));
			 if (resultado.isData()) {
				resultado.setListaResultado(compraServiceLocal.listarCuentaTipoDocumento(cuentaTipoDocumento));
			 }
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/")
	public ResultadoRestVO<CuentaTipoDocumentoDTO> inicializarCuentaTipoDocumento(@Context UriInfo info) throws Exception {
	     CuentaTipoDocumentoDTO cuentaTipoDocumento = transferUriInfo(info);
		 ResultadoRestVO<CuentaTipoDocumentoDTO> resultado = new ResultadoRestVO<CuentaTipoDocumentoDTO>();
		 try {
    		resultado.setObjetoResultado(cuentaTipoDocumento);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private CuentaTipoDocumentoDTO transferUriInfo(@Context UriInfo info) {
		CuentaTipoDocumentoDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,CuentaTipoDocumentoDTO.class);
		return resultado;
	}
}