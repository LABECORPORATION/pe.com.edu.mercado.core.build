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
import pe.com.builderp.core.facturacion.model.dto.compra.CuentaBancariaDTO;
import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.service.rest.impl.GenericServiceRestImpl;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class CuentaBancariaRestController.
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
@Path("/cuentaBancariaRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class CuentaBancariaRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient CompraServiceLocal compraServiceLocal;
	
	@POST
	public ResultadoRestVO<CuentaBancariaDTO> crear(CuentaBancariaDTO cuentaBancaria) throws Exception {
		return controladorAccion(cuentaBancaria,AccionType.CREAR);
	}
	@PUT
	public ResultadoRestVO<CuentaBancariaDTO> modificar(CuentaBancariaDTO cuentaBancaria) throws Exception {
		return controladorAccion(cuentaBancaria,AccionType.MODIFICAR);
	}
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<CuentaBancariaDTO> eliminar(@PathParam("id") String idCuentaBancaria) throws Exception {
		CuentaBancariaDTO cuentaBancaria = new CuentaBancariaDTO();
		cuentaBancaria.setIdCuentaBancaria(idCuentaBancaria);		
		return controladorAccion(cuentaBancaria,AccionType.ELIMINAR);
	}
	private ResultadoRestVO<CuentaBancariaDTO> controladorAccion(CuentaBancariaDTO cuentaBancaria, AccionType accionType){
		ResultadoRestVO<CuentaBancariaDTO> resultado = new ResultadoRestVO<CuentaBancariaDTO>();
		 try {
    		resultado.setObjetoResultado(compraServiceLocal.controladorAccionCuentaBancaria(cuentaBancaria,accionType));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<CuentaBancariaDTO> finById(@PathParam("id") String idCuentaBancaria) throws Exception {
		CuentaBancariaDTO cuentaBancaria = new CuentaBancariaDTO();
		cuentaBancaria.setIdCuentaBancaria(idCuentaBancaria);
		return controladorAccion(cuentaBancaria,AccionType.FIND_BY_ID);
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<CuentaBancariaDTO> listarCuentaBancaria(@Context UriInfo info){
		ResultadoRestVO<CuentaBancariaDTO> resultado = new ResultadoRestVO<CuentaBancariaDTO>();
		CuentaBancariaDTO cuentaBancaria = transferUriInfo(info);
		 try {
			resultado.setListaResultado(compraServiceLocal.listarCuentaBancaria(cuentaBancaria));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/contar")
	public ResultadoRestVO<CuentaBancariaDTO> contarCuentaBancaria(@Context UriInfo info){
		ResultadoRestVO<CuentaBancariaDTO> resultado = new ResultadoRestVO<CuentaBancariaDTO>();
		CuentaBancariaDTO cuentaBancaria = transferUriInfo(info);
		 try {
			 resultado.setContador(compraServiceLocal.contarListarCuentaBancaria(cuentaBancaria));
			 if (resultado.isData()) {
				resultado.setListaResultado(compraServiceLocal.listarCuentaBancaria(cuentaBancaria));
			 }
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/")
	public ResultadoRestVO<CuentaBancariaDTO> inicializarCuentaBancaria(@Context UriInfo info) throws Exception {
	     CuentaBancariaDTO cuentaBancaria = transferUriInfo(info);
		 ResultadoRestVO<CuentaBancariaDTO> resultado = new ResultadoRestVO<CuentaBancariaDTO>();
		 try {
    		resultado.setObjetoResultado(cuentaBancaria);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private CuentaBancariaDTO transferUriInfo(@Context UriInfo info) {
		CuentaBancariaDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,CuentaBancariaDTO.class);
		return resultado;
	}
}