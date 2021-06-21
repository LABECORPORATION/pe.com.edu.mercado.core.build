package pe.com.builderp.core.facturacion.ejb.service.rest.empresa.impl;

import java.util.List;

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

import pe.com.builderp.core.academico.model.vo.nota.AsistenciaFiltroVO;
import pe.com.builderp.core.facturacion.ejb.service.empresa.local.EmpresaServiceLocal;
import pe.com.builderp.core.facturacion.model.dto.empresa.AsistenciaDTO;
import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.seguridad.jwt.rsa.util.AppHTTPHeaderNames; 
import pe.com.edu.siaa.core.ejb.service.rest.impl.GenericServiceRestImpl;
import pe.com.edu.siaa.core.ejb.util.cache.AppAuthenticator; 
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class AsistenciaRestController.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 13:43:27 COT 2017
 * @since SIAA-CORE 2.1
 */
//TODO:Revisar todos los metodos que no se usan
@Stateless
@Path("/asistenciaRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class AsistenciaRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient EmpresaServiceLocal empresaServiceLocal;
	
	@POST
	public ResultadoRestVO<AsistenciaDTO> registrarNota(@Context HttpHeaders httpHeaders,List<AsistenciaDTO> listaAsistencia) throws Exception {
		ResultadoRestVO<AsistenciaDTO> resultado = new ResultadoRestVO<AsistenciaDTO>();
		//String serviceKey = httpHeaders.getHeaderString( AppHTTPHeaderNames.SERVICE_KEY );
		String authToken = httpHeaders.getHeaderString( AppHTTPHeaderNames.AUTH_TOKEN );
		String userName = AppAuthenticator.getInstance().getUserName(authToken);
		empresaServiceLocal.registrarAsistencia(listaAsistencia,userName);
		return resultado;
	}
	@PUT
	public ResultadoRestVO<AsistenciaDTO> modificar(AsistenciaDTO asistencia) throws Exception {
		return controladorAccion(asistencia,AccionType.MODIFICAR);
	}
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<AsistenciaDTO> eliminar(@PathParam("id") String idAsistencia) throws Exception {
		AsistenciaDTO asistencia = new AsistenciaDTO();
		asistencia.setIdAsistencia(idAsistencia);		
		return controladorAccion(asistencia,AccionType.ELIMINAR);
	}
	private ResultadoRestVO<AsistenciaDTO> controladorAccion(AsistenciaDTO asistencia, AccionType accionType){
		ResultadoRestVO<AsistenciaDTO> resultado = new ResultadoRestVO<AsistenciaDTO>();
		 try {
    		resultado.setObjetoResultado(empresaServiceLocal.controladorAccionAsistencia(asistencia,accionType));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<AsistenciaDTO> finById(@PathParam("id") String idAsistencia) throws Exception {
		AsistenciaDTO asistencia = new AsistenciaDTO();
		asistencia.setIdAsistencia(idAsistencia);
		return controladorAccion(asistencia,AccionType.FIND_BY_ID);
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<AsistenciaDTO> listarAsistencia(@Context UriInfo info){
		ResultadoRestVO<AsistenciaDTO> resultado = new ResultadoRestVO<AsistenciaDTO>();
		AsistenciaDTO asistencia = transferUriInfo(info);
		 try {
    		resultado.setListaResultado(empresaServiceLocal.listarAsistencia(asistencia));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}

	@POST
    @Path("/obtenerAsistencia")
	public ResultadoRestVO<AsistenciaDTO> obtenerAsistencia(@Context HttpHeaders httpHeaders,AsistenciaFiltroVO asistenciaFiltroVO){
		ResultadoRestVO<AsistenciaDTO> resultado = new ResultadoRestVO<AsistenciaDTO>();
		 try {
			 //String serviceKey = httpHeaders.getHeaderString( AppHTTPHeaderNames.SERVICE_KEY );
			 String authToken = httpHeaders.getHeaderString( AppHTTPHeaderNames.AUTH_TOKEN );
			 String userName = AppAuthenticator.getInstance().getUserName(authToken);
    		 resultado.setListaResultado(empresaServiceLocal.obtenerAsistencia(false,asistenciaFiltroVO.getIdDetCargaLectiva(),asistenciaFiltroVO.getIdAlumno(),asistenciaFiltroVO.getFechaHorario(),userName));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	
	@POST
    @Path("/generarReporteRegistroAsistenciaByCurso")
	public ResultadoRestVO<String> generarReporteRegistroAsistenciaByCurso(@Context HttpHeaders httpHeaders,AsistenciaFiltroVO asistenciaFiltroVO){
		ResultadoRestVO<String> resultado = new ResultadoRestVO<String>();
		 try {
			 //String serviceKey = httpHeaders.getHeaderString( AppHTTPHeaderNames.SERVICE_KEY );
			 //String authToken = httpHeaders.getHeaderString( AppHTTPHeaderNames.AUTH_TOKEN );
			 //String userName = AppAuthenticator.getInstance().getUserName(authToken);
    		 //resultado.setObjetoResultado(empresaServiceLocal.generarReporteRegistroAsistenciaByCurso(asistenciaFiltroVO.getIdAnhoSemestre(), asistenciaFiltroVO.getIdDetCargaLectiva(),asistenciaFiltroVO.getIdEntidad(), asistenciaFiltroVO.getIdAlumno(),asistenciaFiltroVO.getFechaHorario(),userName));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	
	//agregado
	@POST
    @Path("/generarReporteRegistroAsistenciaByCursoTotalFecha")
	public ResultadoRestVO<String> generarReporteRegistroAsistenciaByCursoTotalFecha(@Context HttpHeaders httpHeaders,AsistenciaFiltroVO asistenciaFiltroVO){
		ResultadoRestVO<String> resultado = new ResultadoRestVO<String>();
		 try {
			 //String serviceKey = httpHeaders.getHeaderString( AppHTTPHeaderNames.SERVICE_KEY );
			 //String authToken = httpHeaders.getHeaderString( AppHTTPHeaderNames.AUTH_TOKEN );
			 //String userName = AppAuthenticator.getInstance().getUserName(authToken);
    		 //resultado.setObjetoResultado(empresaServiceLocal.generarReporteRegistroAsistenciaByCursoTotalFecha(asistenciaFiltroVO.getIdAnhoSemestre(), asistenciaFiltroVO.getIdDetCargaLectiva(),asistenciaFiltroVO.getIdEntidad(), asistenciaFiltroVO.getIdAlumno(),asistenciaFiltroVO.getFechaHorario(),userName));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	
	
	
	@GET
    @Path("/contar")
	public ResultadoRestVO<Integer> contarListarAsistencia(@Context UriInfo info){
		ResultadoRestVO<Integer> resultado = new ResultadoRestVO<Integer>();
		AsistenciaDTO asistencia = transferUriInfo(info);
		 try {
    		resultado.setObjetoResultado(empresaServiceLocal.contarListarAsistencia(asistencia));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/")
	public ResultadoRestVO<AsistenciaDTO> inicializarAsistencia(@Context UriInfo info) throws Exception {
	     AsistenciaDTO asistencia = transferUriInfo(info);
		 ResultadoRestVO<AsistenciaDTO> resultado = new ResultadoRestVO<AsistenciaDTO>();
		 try {
    		resultado.setObjetoResultado(asistencia);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private AsistenciaDTO transferUriInfo(@Context UriInfo info) {
		AsistenciaDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,AsistenciaDTO.class);
		return resultado;
	}
	
	//add
		@GET
		@Path("/obtenerAsistencia/{idDetcargaLectiva}/{idAlumno}")
		public ResultadoRestVO<AsistenciaDTO> obtenerAsistencia(@PathParam("idDetcargaLectiva") String idDetcargaLectiva,@PathParam("idAlumno") String idAlumno) throws Exception {
			ResultadoRestVO<AsistenciaDTO> resultado = new ResultadoRestVO<AsistenciaDTO>();
			 try {
	   		resultado.setListaResultado(empresaServiceLocal.obtenerAsistencia(true, idDetcargaLectiva, idAlumno, null, null));
			} catch (Exception e) {
				parsearResultadoError(e, resultado);
			}
			return resultado;
		}
		
		
		@POST
	    @Path("/porcentajeAsistenciaByCurso")
		public ResultadoRestVO<String> obtenerPorcentajeAsistencia(@Context HttpHeaders httpHeaders,AsistenciaFiltroVO asistenciaFiltroVO){
			ResultadoRestVO<String> resultado = new ResultadoRestVO<String>();
			 try {
				 //String serviceKey = httpHeaders.getHeaderString( AppHTTPHeaderNames.SERVICE_KEY );
				 //String authToken = httpHeaders.getHeaderString( AppHTTPHeaderNames.AUTH_TOKEN );
				 //String userName = AppAuthenticator.getInstance().getUserName(authToken);
	    		// resultado.setObjetoResultado(empresaServiceLocal.porcentajeAsistenciaByCurso(asistenciaFiltroVO.getIdAnhoSemestre(), asistenciaFiltroVO.getIdDetCargaLectiva(),asistenciaFiltroVO.getIdEntidad(), asistenciaFiltroVO.getIdAlumno(),asistenciaFiltroVO.getFechaHorario(),userName));
			} catch (Exception e) {
				parsearResultadoError(e, resultado);
			}
			return resultado;
		}
	
}