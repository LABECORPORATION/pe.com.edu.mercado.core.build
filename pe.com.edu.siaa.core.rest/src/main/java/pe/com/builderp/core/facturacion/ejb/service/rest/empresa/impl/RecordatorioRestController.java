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
import pe.com.builderp.core.facturacion.model.dto.empresa.RecordatorioDTO;
import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.service.rest.impl.GenericServiceRestImpl;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class RecordatorioRestController.
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
@Path("/recordatorioRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class RecordatorioRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient EmpresaServiceLocal empresaServiceLocal;
	
	@POST
	public ResultadoRestVO<RecordatorioDTO> crear(RecordatorioDTO Recordatorio) throws Exception {
		return controladorAccion(Recordatorio,AccionType.CREAR);
	}
	@PUT
	public ResultadoRestVO<RecordatorioDTO> modificar(RecordatorioDTO Recordatorio) throws Exception {
		return controladorAccion(Recordatorio,AccionType.MODIFICAR);
	}
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<RecordatorioDTO> eliminar(@PathParam("id") String idRecordatorio) throws Exception {
		RecordatorioDTO Recordatorio = new RecordatorioDTO();
		Recordatorio.setIdRecordatorio(idRecordatorio);		
		return controladorAccion(Recordatorio,AccionType.ELIMINAR);
	}
	private ResultadoRestVO<RecordatorioDTO> controladorAccion(RecordatorioDTO Recordatorio, AccionType accionType){
		ResultadoRestVO<RecordatorioDTO> resultado = new ResultadoRestVO<RecordatorioDTO>();
		 try {
    		resultado.setObjetoResultado(empresaServiceLocal.controladorAccionRecordatorio(Recordatorio,accionType));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<RecordatorioDTO> finById(@PathParam("id") String idRecordatorio) throws Exception {
		RecordatorioDTO Recordatorio = new RecordatorioDTO();
		Recordatorio.setIdRecordatorio(idRecordatorio);
		return controladorAccion(Recordatorio,AccionType.FIND_BY_ID);
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<RecordatorioDTO> listarRecordatorio(@Context UriInfo info){
		ResultadoRestVO<RecordatorioDTO> resultado = new ResultadoRestVO<RecordatorioDTO>();
		RecordatorioDTO Recordatorio = transferUriInfo(info);
		 try {
			resultado.setListaResultado(empresaServiceLocal.listarRecordatorio(Recordatorio));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/contar")
	public ResultadoRestVO<RecordatorioDTO> contarRecordatorio(@Context UriInfo info){
		ResultadoRestVO<RecordatorioDTO> resultado = new ResultadoRestVO<RecordatorioDTO>();
		RecordatorioDTO Recordatorio = transferUriInfo(info);
		 try {
			 resultado.setContador(empresaServiceLocal.contarListarRecordatorio(Recordatorio));
			 if (resultado.isData()) {
				resultado.setListaResultado(empresaServiceLocal.listarRecordatorio(Recordatorio));
			 }
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/")
	public ResultadoRestVO<RecordatorioDTO> inicializarRecordatorio(@Context UriInfo info) throws Exception {
	     RecordatorioDTO Recordatorio = transferUriInfo(info);
		 ResultadoRestVO<RecordatorioDTO> resultado = new ResultadoRestVO<RecordatorioDTO>();
		 try {
    		resultado.setObjetoResultado(Recordatorio);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private RecordatorioDTO transferUriInfo(@Context UriInfo info) {
		RecordatorioDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,RecordatorioDTO.class);
		return resultado;
	}
}