package pe.com.builderp.core.facturacion.ejb.service.rest.empresa.impl;

import java.util.ArrayList;
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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import pe.com.builderp.core.facturacion.ejb.service.empresa.local.EmpresaServiceLocal;
import pe.com.builderp.core.facturacion.model.dto.empresa.ConceptoPagoDTO;
import pe.com.builderp.core.facturacion.model.dto.empresa.DetPlanPagosDTO;
import pe.com.builderp.core.facturacion.model.dto.empresa.PuestoDTO;
import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil; 
import pe.com.edu.siaa.core.ejb.service.rest.impl.GenericServiceRestImpl;
import pe.com.edu.siaa.core.ejb.service.util.FechaUtil;
import pe.com.edu.siaa.core.ejb.util.date.FechaDateUtil; 
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.model.type.FlagConceptoPagoFraccionadoType;
import pe.com.edu.siaa.core.model.util.StringUtils;
import pe.com.edu.siaa.core.model.vo.DetallePlanPagosFiltroVO;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class DetPlanPagosRestController.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 13:43:25 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
@Path("/detPlanPagosRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class DetPlanPagosRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient EmpresaServiceLocal empresaServiceLocal;
	
	@POST
	public ResultadoRestVO<DetPlanPagosDTO> crear(DetPlanPagosDTO detPlanPagos) throws Exception {
		return controladorAccion(detPlanPagos,AccionType.CREAR);
	}
	
	@PUT
	public ResultadoRestVO<DetPlanPagosDTO> modificar(DetPlanPagosDTO detPlanPagos) throws Exception {
		return controladorAccion(detPlanPagos,AccionType.MODIFICAR);
	}
	
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<DetPlanPagosDTO> eliminar(@PathParam("id") String idDetPlanPagos) throws Exception {
		ResultadoRestVO<DetPlanPagosDTO> resultado = new ResultadoRestVO<DetPlanPagosDTO>();
		 try {
			 DetPlanPagosDTO detPlanPagos = new DetPlanPagosDTO();
			 detPlanPagos.setIdDetPlanPagos(idDetPlanPagos);	
			 resultado.setObjetoResultado(empresaServiceLocal.eliminarDetPlanPagos(detPlanPagos));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	
	private ResultadoRestVO<DetPlanPagosDTO> controladorAccion(DetPlanPagosDTO detPlanPagos, AccionType accionType){
		ResultadoRestVO<DetPlanPagosDTO> resultado = new ResultadoRestVO<DetPlanPagosDTO>();
		 try {
    		//resultado.setObjetoResultado(empresaServiceLocal.eliminarDetPlanPagos(detPlanPagos));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<DetPlanPagosDTO> finById(@PathParam("id") String idDetPlanPagos) throws Exception {
		DetPlanPagosDTO detPlanPagos = new DetPlanPagosDTO();
		detPlanPagos.setIdDetPlanPagos(idDetPlanPagos);
		return controladorAccion(detPlanPagos,AccionType.FIND_BY_ID);
	}
  

	@GET
	@Path("/listarConceptoPagoClienteSemestre/{idCliente}/{idAnhoSemestre}/{flagFaltaMontoResta}")
	public ResultadoRestVO<ConceptoPagoDTO> listarConceptoPagoClienteSemestre(@PathParam("idCliente") String idCliente,@PathParam("idAnhoSemestre") String idAnhoSemestre,@PathParam("flagFaltaMontoResta") boolean flagFaltaMontoResta) throws Exception {
		ResultadoRestVO<ConceptoPagoDTO> resultado = new ResultadoRestVO<ConceptoPagoDTO>();
		 try {
    		resultado.setListaResultado(listarConceptoPagoClienteSemestre(empresaServiceLocal.listarConceptoPagoAsociado(idCliente, flagFaltaMontoResta)));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	
	
	@GET
	@Path("/listarConceptoPagoClienteSemestreAPP/{idAsociado}/{idConcepto}")
	public ResultadoRestVO<ConceptoPagoDTO> listarConceptoPagoClienteSemestreAPP(@PathParam("idAsociado") String idAsociado,@PathParam("idConcepto") String idConcepto) throws Exception {
		ResultadoRestVO<ConceptoPagoDTO> resultado = new ResultadoRestVO<ConceptoPagoDTO>();
		 try {
    		resultado.setListaResultado(listarConceptoPagoClienteSemestre(empresaServiceLocal.listarConceptoPagoAsociadoAPP(idAsociado, idConcepto)));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	
	public List<ConceptoPagoDTO> listarConceptoPagoClienteSemestre(List<DetPlanPagosDTO> listaDetPlanPagos) throws Exception {
		List<ConceptoPagoDTO> listaConceptoPagoDTO = new ArrayList<ConceptoPagoDTO>();
		for (DetPlanPagosDTO conceptoClientePago : listaDetPlanPagos) {
			if (conceptoClientePago.getFlagFraccionado().equals(FlagConceptoPagoFraccionadoType.NO.getKey())) {
				ConceptoPagoDTO conceptoPagoDTO = new ConceptoPagoDTO();
				conceptoPagoDTO.setIdCuotaConcepto(conceptoClientePago.getCuotaConcepto().getIdCuotaConcepto());
				conceptoPagoDTO.setId(conceptoClientePago.getIdDetPlanPagos());
				if(StringUtils.isNullOrEmpty(conceptoClientePago.getNro()) ){
					conceptoClientePago.setNro("");
				}
				if(!StringUtils.isNullOrEmpty(conceptoClientePago.getIdPuesto()) ){
					PuestoDTO puesto = empresaServiceLocal.optenerByPuesto(conceptoClientePago.getIdPuesto());
					conceptoPagoDTO.setDescripcion(puesto.getDescripcion()+"-"+puesto.getCodigo()+"  "+ conceptoClientePago.getCuotaConcepto().getCuenta() + " " + conceptoClientePago.getNro() + " - (" + conceptoClientePago.getCuotaConcepto().getIdCuotaConcepto()  + ")" );
				}else {
					conceptoPagoDTO.setDescripcion(conceptoClientePago.getCuotaConcepto().getCuenta() + " " + conceptoClientePago.getNro() + " - (" + conceptoClientePago.getCuotaConcepto().getIdCuotaConcepto()  + ")" );
				}
				conceptoPagoDTO.setFechaVencimiento(conceptoClientePago.getFechaVencimiento());
				if (conceptoClientePago.getMontoResta() != null) {
					conceptoPagoDTO.setMonto(conceptoClientePago.getMontoResta());
				} else {
					conceptoPagoDTO.setMonto(conceptoClientePago.getCuota());
				}
				conceptoPagoDTO.setMontoTotal(conceptoPagoDTO.getMonto());
				conceptoPagoDTO.setMontoResta(null);
				conceptoPagoDTO.setFlagFraccionadoDescripcion(conceptoClientePago.getFlagFraccionadoDescripcion());
				conceptoPagoDTO.setEsFraccionado(false);
				if (conceptoClientePago.getFechaVencimiento() != null) {
					conceptoPagoDTO.setNumeroDiasRetrazo(FechaDateUtil.restaFechas(conceptoClientePago.getFechaVencimiento(), FechaUtil.obtenerFecha()));
				}
				listaConceptoPagoDTO.add(conceptoPagoDTO);
			} else if (conceptoClientePago.getFlagFraccionado().equals(FlagConceptoPagoFraccionadoType.SI.getKey())) {
				ConceptoPagoDTO conceptoPagoDTO = new ConceptoPagoDTO();
				conceptoPagoDTO.setIdCuotaConcepto(conceptoClientePago.getCuotaConcepto().getIdCuotaConcepto());
				conceptoPagoDTO.setId(conceptoClientePago.getIdDetPlanPagos());
				if(StringUtils.isNullOrEmpty(conceptoClientePago.getNro()) ){
					conceptoClientePago.setNro("");
				}
				if(!StringUtils.isNullOrEmpty(conceptoClientePago.getIdPuesto()) ){
					PuestoDTO puesto = empresaServiceLocal.optenerByPuesto(conceptoClientePago.getIdPuesto());
				    conceptoPagoDTO.setDescripcion(puesto.getDescripcion()+"-"+puesto.getCodigo()+"  "+ conceptoClientePago.getCuotaConcepto().getCuenta() + " " + conceptoClientePago.getNro() + " - (" + conceptoClientePago.getCuotaConcepto().getIdCuotaConcepto()  + ")" );
				}else {
				   conceptoPagoDTO.setDescripcion(conceptoClientePago.getCuotaConcepto().getCuenta() + " " + conceptoClientePago.getNro() + " - (" + conceptoClientePago.getCuotaConcepto().getIdCuotaConcepto()  + ")" );
				}
			       conceptoPagoDTO.setFechaVencimiento(conceptoClientePago.getFechaVencimiento());
				if (conceptoClientePago.getMontoResta() != null) {
				   conceptoPagoDTO.setMonto(conceptoClientePago.getMontoResta());
				} else {
					conceptoPagoDTO.setMonto(conceptoClientePago.getCuota());
				}
				conceptoPagoDTO.setMontoTotal(conceptoPagoDTO.getMonto());
				conceptoPagoDTO.setMontoResta(null);
				conceptoPagoDTO.setFlagFraccionadoDescripcion(conceptoClientePago.getFlagFraccionadoDescripcion());
				conceptoPagoDTO.setEsFraccionado(false);
				if (conceptoClientePago.getFechaVencimiento() != null) {
					conceptoPagoDTO.setNumeroDiasRetrazo(FechaDateUtil.restaFechas(conceptoClientePago.getFechaVencimiento(), FechaUtil.obtenerFecha()));
				}
				listaConceptoPagoDTO.add(conceptoPagoDTO);
			}
		}
		return listaConceptoPagoDTO;
	}
	

	@GET
    @Path("/listar")
	public ResultadoRestVO<DetPlanPagosDTO> listarDetPlanPagos(@Context UriInfo info){
		ResultadoRestVO<DetPlanPagosDTO> resultado = new ResultadoRestVO<DetPlanPagosDTO>();
		DetPlanPagosDTO detPlanPagos = transferUriInfo(info);
		 try {
    		resultado.setListaResultado(empresaServiceLocal.listarDetPlanPagos(detPlanPagos));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	
	@GET
    @Path("/contar")
	public ResultadoRestVO<DetPlanPagosDTO> contarListarDetPlanPagos(@Context UriInfo info){
		ResultadoRestVO<DetPlanPagosDTO> resultado = new ResultadoRestVO<DetPlanPagosDTO>();
		DetPlanPagosDTO detPlanPagos = transferUriInfo(info);
		 try {
    		resultado.setContador(empresaServiceLocal.contarListarDetPlanPagos(detPlanPagos));
    		if (resultado.isData()) {
    			resultado.setListaResultado(empresaServiceLocal.listarDetPlanPagos(detPlanPagos));
    		}
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	
	@GET
    @Path("/")
	public ResultadoRestVO<DetPlanPagosDTO> inicializarDetPlanPagos(@Context UriInfo info) throws Exception {
	     DetPlanPagosDTO detPlanPagos = transferUriInfo(info);
		 ResultadoRestVO<DetPlanPagosDTO> resultado = new ResultadoRestVO<DetPlanPagosDTO>();
		 try {
    		resultado.setObjetoResultado(detPlanPagos);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	
	private DetPlanPagosDTO transferUriInfo(@Context UriInfo info) {
		DetPlanPagosDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,DetPlanPagosDTO.class);
		return resultado;
	}
	
	@GET
	@Path("/optenerByDetPlanPagos/{idAlumno}")
	public ResultadoRestVO<DetPlanPagosDTO> optenerByDetPlanPagos(@PathParam("idAlumno") String idAlumno) throws Exception {
		ResultadoRestVO<DetPlanPagosDTO> resultado = new ResultadoRestVO<DetPlanPagosDTO>();
		 try {
   		resultado.setObjetoResultado(empresaServiceLocal.optenerByDetPlanPagos(idAlumno));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	

	@POST
    @Path("/obtenerDetallePlanPagosFiltroVO")
	public ResultadoRestVO<DetallePlanPagosFiltroVO> obtenerDetallePlanPagosFiltroVO(DetallePlanPagosFiltroVO filtro) throws Exception {
		 ResultadoRestVO<DetallePlanPagosFiltroVO> resultado = new ResultadoRestVO<DetallePlanPagosFiltroVO>();
		 try {
			resultado.setListaResultado(empresaServiceLocal.obtenerDetallePlanPagosFiltroVO(filtro));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
}