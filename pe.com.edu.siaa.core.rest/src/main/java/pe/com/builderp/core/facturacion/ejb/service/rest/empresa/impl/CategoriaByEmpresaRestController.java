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
import pe.com.builderp.core.facturacion.model.dto.empresa.CategoriaByEmpresaDTO;
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
@Path("/categoriaEmpresaRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class CategoriaByEmpresaRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient EmpresaServiceLocal empresaServiceLocal;
	
	@POST
	public ResultadoRestVO<CategoriaByEmpresaDTO> crear(CategoriaByEmpresaDTO categoria) throws Exception {
		return controladorAccion(categoria,AccionType.CREAR);
	}
	@PUT
	public ResultadoRestVO<CategoriaByEmpresaDTO> modificar(CategoriaByEmpresaDTO categoria) throws Exception {
		return controladorAccion(categoria,AccionType.MODIFICAR);
	}
	
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<CategoriaByEmpresaDTO> eliminar(@PathParam("id") String idCategoria) throws Exception {
		CategoriaByEmpresaDTO categoria = new CategoriaByEmpresaDTO();
		categoria.setIdCategoria(idCategoria);		
		return controladorAccion(categoria,AccionType.ELIMINAR);
	}
	
	private ResultadoRestVO<CategoriaByEmpresaDTO> controladorAccion(CategoriaByEmpresaDTO categoria, AccionType accionType){
		ResultadoRestVO<CategoriaByEmpresaDTO> resultado = new ResultadoRestVO<CategoriaByEmpresaDTO>();
		 try {
    		resultado.setObjetoResultado(empresaServiceLocal.controladorAccionCategoria(categoria,accionType));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<CategoriaByEmpresaDTO> finById(@PathParam("id") String idCategoria) throws Exception {
		CategoriaByEmpresaDTO categoria = new CategoriaByEmpresaDTO();
		categoria.setIdCategoria(idCategoria);
		return controladorAccion(categoria,AccionType.FIND_BY_ID);
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<CategoriaByEmpresaDTO> listarCategoria(@Context UriInfo info){
		ResultadoRestVO<CategoriaByEmpresaDTO> resultado = new ResultadoRestVO<CategoriaByEmpresaDTO>();
		CategoriaByEmpresaDTO categoria = transferUriInfo(info);
		 try {
			resultado.setListaResultado(empresaServiceLocal.listarCategoria(categoria));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	
	@GET
    @Path("/contar")
	public ResultadoRestVO<CategoriaByEmpresaDTO> contarCategoria(@Context UriInfo info){
		ResultadoRestVO<CategoriaByEmpresaDTO> resultado = new ResultadoRestVO<CategoriaByEmpresaDTO>();
		CategoriaByEmpresaDTO categoria = transferUriInfo(info);
		 try {
			 resultado.setContador(empresaServiceLocal.contarListarCategoria(categoria));
			 if (resultado.isData()) {
				resultado.setListaResultado(empresaServiceLocal.listarCategoria(categoria));
			 }
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	
	@GET
    @Path("/")
	public ResultadoRestVO<CategoriaByEmpresaDTO> inicializarCategoria(@Context UriInfo info) throws Exception {
	     CategoriaByEmpresaDTO categoria = transferUriInfo(info);
		 ResultadoRestVO<CategoriaByEmpresaDTO> resultado = new ResultadoRestVO<CategoriaByEmpresaDTO>();
		 try {
    		resultado.setObjetoResultado(categoria);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	
	private CategoriaByEmpresaDTO transferUriInfo(@Context UriInfo info) {
		CategoriaByEmpresaDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,CategoriaByEmpresaDTO.class);
		return resultado;
	}
	
	@GET
    @Path("/obtenerCategoriaByParameter")
	public ResultadoRestVO<CategoriaByEmpresaDTO> obtenerCategoriaByParameter(@Context UriInfo info) throws Exception {
		CategoriaByEmpresaDTO anhoSemestre = transferUriInfo(info);
		 ResultadoRestVO<CategoriaByEmpresaDTO> resultado = new ResultadoRestVO<CategoriaByEmpresaDTO>();
		 try { 
			resultado.setObjetoResultado(empresaServiceLocal.obtenerCategoriaByParameter(anhoSemestre));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
}