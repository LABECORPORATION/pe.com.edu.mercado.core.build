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
import pe.com.builderp.core.facturacion.model.dto.venta.MarcaDTO;
import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.service.rest.impl.GenericServiceRestImpl;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class MarcaRestController.
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
@Path("/marcaRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class MarcaRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient VentaServiceLocal ventaServiceLocal;
	
	@POST
	public ResultadoRestVO<MarcaDTO> crear(MarcaDTO marca) throws Exception {
		return controladorAccion(marca,AccionType.CREAR);
	}
	@PUT
	public ResultadoRestVO<MarcaDTO> modificar(MarcaDTO marca) throws Exception {
		return controladorAccion(marca,AccionType.MODIFICAR);
	}
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<MarcaDTO> eliminar(@PathParam("id") Long idMarca) throws Exception {
		MarcaDTO marca = new MarcaDTO();
		marca.setIdMarca(idMarca);		
		return controladorAccion(marca,AccionType.ELIMINAR);
	}
	private ResultadoRestVO<MarcaDTO> controladorAccion(MarcaDTO marca, AccionType accionType){
		ResultadoRestVO<MarcaDTO> resultado = new ResultadoRestVO<MarcaDTO>();
		 try {
    		resultado.setObjetoResultado(ventaServiceLocal.controladorAccionMarca(marca,accionType));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<MarcaDTO> finById(@PathParam("id") Long idMarca) throws Exception {
		MarcaDTO marca = new MarcaDTO();
		marca.setIdMarca(idMarca);
		return controladorAccion(marca,AccionType.FIND_BY_ID);
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<MarcaDTO> listarMarca(@Context UriInfo info){
		ResultadoRestVO<MarcaDTO> resultado = new ResultadoRestVO<MarcaDTO>();
		MarcaDTO marca = transferUriInfo(info);
		 try {
			resultado.setListaResultado(ventaServiceLocal.listarMarca(marca));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/contar")
	public ResultadoRestVO<MarcaDTO> contarMarca(@Context UriInfo info){
		ResultadoRestVO<MarcaDTO> resultado = new ResultadoRestVO<MarcaDTO>();
		MarcaDTO marca = transferUriInfo(info);
		 try {
			 resultado.setContador(ventaServiceLocal.contarListarMarca(marca));
			 if (resultado.isData()) {
				resultado.setListaResultado(ventaServiceLocal.listarMarca(marca));
			 }
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/")
	public ResultadoRestVO<MarcaDTO> inicializarMarca(@Context UriInfo info) throws Exception {
	     MarcaDTO marca = transferUriInfo(info);
		 ResultadoRestVO<MarcaDTO> resultado = new ResultadoRestVO<MarcaDTO>();
		 try {
    		resultado.setObjetoResultado(marca);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private MarcaDTO transferUriInfo(@Context UriInfo info) {
		MarcaDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,MarcaDTO.class);
		return resultado;
	}
}