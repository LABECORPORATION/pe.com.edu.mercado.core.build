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
import pe.com.builderp.core.facturacion.model.dto.venta.CategoriaDTO;
import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.service.rest.impl.GenericServiceRestImpl;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class CategoriaRestController.
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
@Path("/categoriaRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class CategoriaRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient VentaServiceLocal ventaServiceLocal;
	
	@POST
	public ResultadoRestVO<CategoriaDTO> crear(CategoriaDTO categoria) throws Exception {
		return controladorAccion(categoria,AccionType.CREAR);
	}
	@PUT
	public ResultadoRestVO<CategoriaDTO> modificar(CategoriaDTO categoria) throws Exception {
		return controladorAccion(categoria,AccionType.MODIFICAR);
	}
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<CategoriaDTO> eliminar(@PathParam("id") Long idCategoria) throws Exception {
		CategoriaDTO categoria = new CategoriaDTO();
		categoria.setIdCategoria(idCategoria);		
		return controladorAccion(categoria,AccionType.ELIMINAR);
	}
	private ResultadoRestVO<CategoriaDTO> controladorAccion(CategoriaDTO categoria, AccionType accionType){
		ResultadoRestVO<CategoriaDTO> resultado = new ResultadoRestVO<CategoriaDTO>();
		 try {
    		resultado.setObjetoResultado(ventaServiceLocal.controladorAccionCategoria(categoria,accionType));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<CategoriaDTO> finById(@PathParam("id") Long idCategoria) throws Exception {
		CategoriaDTO categoria = new CategoriaDTO();
		categoria.setIdCategoria(idCategoria);
		return controladorAccion(categoria,AccionType.FIND_BY_ID);
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<CategoriaDTO> listarCategoria(@Context UriInfo info){
		ResultadoRestVO<CategoriaDTO> resultado = new ResultadoRestVO<CategoriaDTO>();
		CategoriaDTO categoria = transferUriInfo(info);
		 try {
			resultado.setListaResultado(ventaServiceLocal.listarCategoria(categoria));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/contar")
	public ResultadoRestVO<CategoriaDTO> contarCategoria(@Context UriInfo info){
		ResultadoRestVO<CategoriaDTO> resultado = new ResultadoRestVO<CategoriaDTO>();
		CategoriaDTO categoria = transferUriInfo(info);
		 try {
			 resultado.setContador(ventaServiceLocal.contarListarCategoria(categoria));
			 if (resultado.isData()) {
				resultado.setListaResultado(ventaServiceLocal.listarCategoria(categoria));
			 }
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/")
	public ResultadoRestVO<CategoriaDTO> inicializarCategoria(@Context UriInfo info) throws Exception {
	     CategoriaDTO categoria = transferUriInfo(info);
		 ResultadoRestVO<CategoriaDTO> resultado = new ResultadoRestVO<CategoriaDTO>();
		 try {
    		resultado.setObjetoResultado(categoria);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private CategoriaDTO transferUriInfo(@Context UriInfo info) {
		CategoriaDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,CategoriaDTO.class);
		return resultado;
	}
}