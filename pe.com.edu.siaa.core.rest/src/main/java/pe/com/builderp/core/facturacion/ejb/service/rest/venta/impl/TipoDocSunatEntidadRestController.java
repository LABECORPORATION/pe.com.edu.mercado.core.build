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
import pe.com.builderp.core.facturacion.model.dto.venta.TipoDocSunatEntidadDTO;
import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.service.rest.impl.GenericServiceRestImpl;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class TipoDocSunatEntidadRestController.
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
@Path("/tipoDocSunatEntidadRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class TipoDocSunatEntidadRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient VentaServiceLocal ventaServiceLocal;
	
	@POST
	public ResultadoRestVO<TipoDocSunatEntidadDTO> crear(TipoDocSunatEntidadDTO tipoDocSunatEntidad) throws Exception {
		return controladorAccion(tipoDocSunatEntidad,AccionType.CREAR);
	}
	@PUT
	public ResultadoRestVO<TipoDocSunatEntidadDTO> modificar(TipoDocSunatEntidadDTO tipoDocSunatEntidad) throws Exception {
		return controladorAccion(tipoDocSunatEntidad,AccionType.MODIFICAR);
	}
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<TipoDocSunatEntidadDTO> eliminar(@PathParam("id") String idTipoDocSunatEntidad) throws Exception {
		TipoDocSunatEntidadDTO tipoDocSunatEntidad = new TipoDocSunatEntidadDTO();
		tipoDocSunatEntidad.setIdTipoDocSunatEntidad(idTipoDocSunatEntidad);		
		return controladorAccion(tipoDocSunatEntidad,AccionType.ELIMINAR);
	}
	private ResultadoRestVO<TipoDocSunatEntidadDTO> controladorAccion(TipoDocSunatEntidadDTO tipoDocSunatEntidad, AccionType accionType){
		ResultadoRestVO<TipoDocSunatEntidadDTO> resultado = new ResultadoRestVO<TipoDocSunatEntidadDTO>();
		 try {
    		resultado.setObjetoResultado(ventaServiceLocal.controladorAccionTipoDocSunatEntidad(tipoDocSunatEntidad,accionType));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<TipoDocSunatEntidadDTO> finById(@PathParam("id") String idTipoDocSunatEntidad) throws Exception {
		TipoDocSunatEntidadDTO tipoDocSunatEntidad = new TipoDocSunatEntidadDTO();
		tipoDocSunatEntidad.setIdTipoDocSunatEntidad(idTipoDocSunatEntidad);
		return controladorAccion(tipoDocSunatEntidad,AccionType.FIND_BY_ID);
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<TipoDocSunatEntidadDTO> listarTipoDocSunatEntidad(@Context UriInfo info){
		ResultadoRestVO<TipoDocSunatEntidadDTO> resultado = new ResultadoRestVO<TipoDocSunatEntidadDTO>();
		TipoDocSunatEntidadDTO tipoDocSunatEntidad = transferUriInfo(info);
		 try {
			resultado.setListaResultado(ventaServiceLocal.listarTipoDocSunatEntidad(tipoDocSunatEntidad));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/contar")
	public ResultadoRestVO<TipoDocSunatEntidadDTO> contarTipoDocSunatEntidad(@Context UriInfo info){
		ResultadoRestVO<TipoDocSunatEntidadDTO> resultado = new ResultadoRestVO<TipoDocSunatEntidadDTO>();
		TipoDocSunatEntidadDTO tipoDocSunatEntidad = transferUriInfo(info);
		 try {
			 resultado.setContador(ventaServiceLocal.contarListarTipoDocSunatEntidad(tipoDocSunatEntidad));
			 if (resultado.isData()) {
				resultado.setListaResultado(ventaServiceLocal.listarTipoDocSunatEntidad(tipoDocSunatEntidad));
			 }
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/")
	public ResultadoRestVO<TipoDocSunatEntidadDTO> inicializarTipoDocSunatEntidad(@Context UriInfo info) throws Exception {
	     TipoDocSunatEntidadDTO tipoDocSunatEntidad = transferUriInfo(info);
		 ResultadoRestVO<TipoDocSunatEntidadDTO> resultado = new ResultadoRestVO<TipoDocSunatEntidadDTO>();
		 try {
    		resultado.setObjetoResultado(tipoDocSunatEntidad);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private TipoDocSunatEntidadDTO transferUriInfo(@Context UriInfo info) {
		TipoDocSunatEntidadDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,TipoDocSunatEntidadDTO.class);
		return resultado;
	}
}