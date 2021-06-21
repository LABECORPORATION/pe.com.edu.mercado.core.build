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
import pe.com.builderp.core.facturacion.model.dto.venta.ModeloDTO;
import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.service.rest.impl.GenericServiceRestImpl;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class ModeloRestController.
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
@Path("/modeloRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class ModeloRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient VentaServiceLocal ventaServiceLocal;
	
	@POST
	public ResultadoRestVO<ModeloDTO> crear(ModeloDTO modelo) throws Exception {
		return controladorAccion(modelo,AccionType.CREAR);
	}
	@PUT
	public ResultadoRestVO<ModeloDTO> modificar(ModeloDTO modelo) throws Exception {
		return controladorAccion(modelo,AccionType.MODIFICAR);
	}
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<ModeloDTO> eliminar(@PathParam("id") Long idModelo) throws Exception {
		ModeloDTO modelo = new ModeloDTO();
		modelo.setIdModelo(idModelo);		
		return controladorAccion(modelo,AccionType.ELIMINAR);
	}
	private ResultadoRestVO<ModeloDTO> controladorAccion(ModeloDTO modelo, AccionType accionType){
		ResultadoRestVO<ModeloDTO> resultado = new ResultadoRestVO<ModeloDTO>();
		 try {
    		resultado.setObjetoResultado(ventaServiceLocal.controladorAccionModelo(modelo,accionType));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<ModeloDTO> finById(@PathParam("id") Long idModelo) throws Exception {
		ModeloDTO modelo = new ModeloDTO();
		modelo.setIdModelo(idModelo);
		return controladorAccion(modelo,AccionType.FIND_BY_ID);
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<ModeloDTO> listarModelo(@Context UriInfo info){
		ResultadoRestVO<ModeloDTO> resultado = new ResultadoRestVO<ModeloDTO>();
		ModeloDTO modelo = transferUriInfo(info);
		 try {
			resultado.setListaResultado(ventaServiceLocal.listarModelo(modelo));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/contar")
	public ResultadoRestVO<ModeloDTO> contarModelo(@Context UriInfo info){
		ResultadoRestVO<ModeloDTO> resultado = new ResultadoRestVO<ModeloDTO>();
		ModeloDTO modelo = transferUriInfo(info);
		 try {
			 resultado.setContador(ventaServiceLocal.contarListarModelo(modelo));
			 if (resultado.isData()) {
				resultado.setListaResultado(ventaServiceLocal.listarModelo(modelo));
			 }
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/")
	public ResultadoRestVO<ModeloDTO> inicializarModelo(@Context UriInfo info) throws Exception {
	     ModeloDTO modelo = transferUriInfo(info);
		 ResultadoRestVO<ModeloDTO> resultado = new ResultadoRestVO<ModeloDTO>();
		 try {
    		resultado.setObjetoResultado(modelo);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private ModeloDTO transferUriInfo(@Context UriInfo info) {
		ModeloDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,ModeloDTO.class);
		return resultado;
	}
}