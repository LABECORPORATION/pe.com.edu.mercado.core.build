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
import pe.com.builderp.core.facturacion.model.dto.empresa.AnhioDTO; 
import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.service.rest.impl.GenericServiceRestImpl;
import pe.com.edu.siaa.core.model.estate.EstadoAnhoSemestreState;
import pe.com.edu.siaa.core.model.type.AccionType; 
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class AnhioRestController.
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
@Path("/anhioRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class AnhioRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient EmpresaServiceLocal empresaServiceLocal;
	
	@POST
	public ResultadoRestVO<AnhioDTO> crear(AnhioDTO Anhio) throws Exception {
		return controladorAccion(Anhio,AccionType.CREAR);
	}
	@PUT
	public ResultadoRestVO<AnhioDTO> modificar(AnhioDTO Anhio) throws Exception {
		return controladorAccion(Anhio,AccionType.MODIFICAR);
	}
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<AnhioDTO> eliminar(@PathParam("id") Long idAnhio) throws Exception {
		AnhioDTO Anhio = new AnhioDTO();
		Anhio.setIdAnhio(idAnhio);		
		return controladorAccion(Anhio,AccionType.ELIMINAR);
	}
	private ResultadoRestVO<AnhioDTO> controladorAccion(AnhioDTO Anhio, AccionType accionType){
		ResultadoRestVO<AnhioDTO> resultado = new ResultadoRestVO<AnhioDTO>();
		 try {
    		resultado.setObjetoResultado(empresaServiceLocal.controladorAccionAnhio(Anhio,accionType));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<AnhioDTO> finById(@PathParam("id") Long idAnhio) throws Exception {
		AnhioDTO Anhio = new AnhioDTO();
		Anhio.setIdAnhio(idAnhio);
		return controladorAccion(Anhio,AccionType.FIND_BY_ID);
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<AnhioDTO> listarAnhio(@Context UriInfo info){
		ResultadoRestVO<AnhioDTO> resultado = new ResultadoRestVO<AnhioDTO>();
		AnhioDTO Anhio = transferUriInfo(info);
		 try {
			resultado.setListaResultado(empresaServiceLocal.listarAnhio(Anhio));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/contar")
	public ResultadoRestVO<AnhioDTO> contarAnhio(@Context UriInfo info){
		ResultadoRestVO<AnhioDTO> resultado = new ResultadoRestVO<AnhioDTO>();
		AnhioDTO Anhio = transferUriInfo(info);
		 try {
			 resultado.setContador(empresaServiceLocal.contarListarAnhio(Anhio));
			 if (resultado.isData()) {
				resultado.setListaResultado(empresaServiceLocal.listarAnhio(Anhio));
			 }
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/")
	public ResultadoRestVO<AnhioDTO> inicializarAnhio(@Context UriInfo info) throws Exception {
	     AnhioDTO Anhio = transferUriInfo(info);
		 ResultadoRestVO<AnhioDTO> resultado = new ResultadoRestVO<AnhioDTO>();
		 try {
    		resultado.setObjetoResultado(Anhio);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	
	@GET
    @Path("/obtenerAnhioByEstado")
	public ResultadoRestVO<AnhioDTO> obtenerAnhioByEstado(@Context UriInfo info) throws Exception {
		AnhioDTO anhoSemestre = transferUriInfo(info);
		 ResultadoRestVO<AnhioDTO> resultado = new ResultadoRestVO<AnhioDTO>();
		 try {
    		EstadoAnhoSemestreState estadoAnhoSemestreState = EstadoAnhoSemestreState.get(anhoSemestre.getEstado());
			resultado.setObjetoResultado(empresaServiceLocal.obtenerAnhioByEstado(estadoAnhoSemestreState ));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	
	private AnhioDTO transferUriInfo(@Context UriInfo info) {
		AnhioDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,AnhioDTO.class);
		return resultado;
	}
	
 
}