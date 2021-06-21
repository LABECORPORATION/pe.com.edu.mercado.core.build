package pe.com.builderp.core.facturacion.ejb.service.rest.venta.impl;

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
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import pe.com.builderp.core.facturacion.ejb.service.venta.local.VentaServiceLocal;
import pe.com.builderp.core.facturacion.model.dto.venta.CajaDTO;
import pe.com.builderp.core.facturacion.model.dto.venta.VentaDTO;
import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.seguridad.jwt.rsa.util.AppHTTPHeaderNames;
import pe.com.edu.siaa.core.ejb.service.rest.impl.GenericServiceRestImpl;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class ProformaRestController.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu Dec 28 12:02:24 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
@Path("/cajaRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class CajaRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient VentaServiceLocal ventaServiceLocal;
	
	@POST
	public ResultadoRestVO<CajaDTO> crear(@Context HttpHeaders httpHeaders,CajaDTO caja) throws Exception {
		String authToken = httpHeaders.getHeaderString( AppHTTPHeaderNames.AUTH_TOKEN );
		caja.setAuthToken(authToken);	
		return controladorAccion(caja,AccionType.CREAR);
	}
	@PUT
	public ResultadoRestVO<CajaDTO> modificar(CajaDTO Caja) throws Exception {
		return controladorAccion(Caja,AccionType.MODIFICAR);
	}
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<CajaDTO> eliminar(@PathParam("id") String idCaja) throws Exception {
		CajaDTO Caja = new CajaDTO();
		Caja.setIdCaja(idCaja);		
		return controladorAccion(Caja,AccionType.ELIMINAR);
	}
	private ResultadoRestVO<CajaDTO> controladorAccion(CajaDTO caja, AccionType accionType){
		ResultadoRestVO<CajaDTO> resultado = new ResultadoRestVO<CajaDTO>();
		 try {
    		resultado.setObjetoResultado(ventaServiceLocal.controladorAccionCaja(caja,accionType));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<CajaDTO> finById(@PathParam("id") String idCaja) throws Exception {
		CajaDTO Caja = new CajaDTO();
		Caja.setIdCaja(idCaja);
		return controladorAccion(Caja,AccionType.FIND_BY_ID);
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<CajaDTO> listarCaja(@Context UriInfo info){
		ResultadoRestVO<CajaDTO> resultado = new ResultadoRestVO<CajaDTO>();
		CajaDTO caja = transferUriInfo(info);
		 try {
			resultado.setListaResultado(ventaServiceLocal.listarCaja(caja));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/contar")
	public ResultadoRestVO<CajaDTO> contarCaja(@Context UriInfo info){
		ResultadoRestVO<CajaDTO> resultado = new ResultadoRestVO<CajaDTO>();
		CajaDTO caja = transferUriInfo(info);
		 try {
			 resultado.setContador(ventaServiceLocal.contarListarCaja(caja));
			 if (resultado.isData()) {
				resultado.setListaResultado(ventaServiceLocal.listarCaja(caja));
			 }
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/")
	public ResultadoRestVO<CajaDTO> inicializarCaja(@Context UriInfo info) throws Exception {
	     CajaDTO caja = transferUriInfo(info);
		 ResultadoRestVO<CajaDTO> resultado = new ResultadoRestVO<CajaDTO>();
		 try {
    		resultado.setObjetoResultado(caja);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private CajaDTO transferUriInfo(@Context UriInfo info) {
		CajaDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,CajaDTO.class);
		return resultado;
	}
	
	@POST
	@Path("/findByCaja")
	public ResultadoRestVO<CajaDTO> findByCaja(@Context UriInfo info, CajaDTO cajaTemp) throws Exception {
		ResultadoRestVO<CajaDTO> resultado = new ResultadoRestVO<CajaDTO>();
		 try {
   		resultado.setObjetoResultado(ventaServiceLocal.findByCaja(cajaTemp));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
}