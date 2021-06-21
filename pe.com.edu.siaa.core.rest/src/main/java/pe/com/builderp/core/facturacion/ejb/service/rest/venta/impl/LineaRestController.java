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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import pe.com.builderp.core.facturacion.ejb.service.venta.local.VentaServiceLocal;
import pe.com.builderp.core.facturacion.model.dto.venta.LineaDTO;
import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.service.rest.impl.GenericServiceRestImpl;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class LineaRestController.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Thu Dec 28 12:02:23 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
@Path("/lineaRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class LineaRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient VentaServiceLocal ventaServiceLocal;
	
	@POST
	public ResultadoRestVO<LineaDTO> crear(LineaDTO linea) throws Exception {
		return controladorAccion(linea,AccionType.CREAR);
	}
	@PUT
	public ResultadoRestVO<LineaDTO> modificar(LineaDTO linea) throws Exception {
		return controladorAccion(linea,AccionType.MODIFICAR);
	}
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<LineaDTO> eliminar(@PathParam("id") Long idLinea) throws Exception {
		LineaDTO linea = new LineaDTO();
		linea.setIdLinea(idLinea);		
		return controladorAccion(linea,AccionType.ELIMINAR);
	}
	private ResultadoRestVO<LineaDTO> controladorAccion(LineaDTO linea, AccionType accionType){
		ResultadoRestVO<LineaDTO> resultado = new ResultadoRestVO<LineaDTO>();
		 try {
    		resultado.setObjetoResultado(ventaServiceLocal.controladorAccionLinea(linea,accionType));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<LineaDTO> finById(@PathParam("id") Long idLinea) throws Exception {
		LineaDTO linea = new LineaDTO();
		linea.setIdLinea(idLinea);
		return controladorAccion(linea,AccionType.FIND_BY_ID);
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<LineaDTO> listarLinea(@Context UriInfo info){
		ResultadoRestVO<LineaDTO> resultado = new ResultadoRestVO<LineaDTO>();
		LineaDTO linea = transferUriInfo(info);
		 try {
			resultado.setListaResultado(ventaServiceLocal.listarLinea(linea));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/contar")
	public ResultadoRestVO<LineaDTO> contarLinea(@Context UriInfo info){
		ResultadoRestVO<LineaDTO> resultado = new ResultadoRestVO<LineaDTO>();
		LineaDTO linea = transferUriInfo(info);
		 try {
			 resultado.setContador(ventaServiceLocal.contarListarLinea(linea));
			 if (resultado.isData()) {
				resultado.setListaResultado(ventaServiceLocal.listarLinea(linea));
			 }
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/")
	public ResultadoRestVO<LineaDTO> inicializarLinea(@Context UriInfo info) throws Exception {
	     LineaDTO linea = transferUriInfo(info);
		 ResultadoRestVO<LineaDTO> resultado = new ResultadoRestVO<LineaDTO>();
		 try {
    		resultado.setObjetoResultado(linea);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private LineaDTO transferUriInfo(@Context UriInfo info) {
		LineaDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,LineaDTO.class);
		return resultado;
	}
}