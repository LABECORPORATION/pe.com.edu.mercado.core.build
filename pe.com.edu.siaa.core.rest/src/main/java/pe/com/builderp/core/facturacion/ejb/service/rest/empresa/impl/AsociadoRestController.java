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
import pe.com.builderp.core.facturacion.model.dto.compra.ProveedorDTO;
import pe.com.builderp.core.facturacion.model.dto.empresa.AsociadoDTO;
import pe.com.builderp.core.facturacion.model.dto.empresa.AsociadoFamiliaDTO;
import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.service.rest.impl.GenericServiceRestImpl;
import pe.com.edu.siaa.core.model.type.AccionType; 
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class AsociadoRestController.
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
@Path("/asociadoRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class AsociadoRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient EmpresaServiceLocal empresaServiceLocal;
	
	@POST
	public ResultadoRestVO<AsociadoDTO> crear(AsociadoDTO Asociado) throws Exception {
		return controladorAccion(Asociado,AccionType.CREAR);
	}
	@PUT
	public ResultadoRestVO<AsociadoDTO> modificar(AsociadoDTO Asociado) throws Exception {
		return controladorAccion(Asociado,AccionType.MODIFICAR);
	}
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<AsociadoDTO> eliminar(@PathParam("id") String idAsociado) throws Exception {
		AsociadoDTO Asociado = new AsociadoDTO();
		Asociado.setIdAsociado(idAsociado);		
		return controladorAccion(Asociado,AccionType.ELIMINAR);
	}
	private ResultadoRestVO<AsociadoDTO> controladorAccion(AsociadoDTO Asociado, AccionType accionType){
		ResultadoRestVO<AsociadoDTO> resultado = new ResultadoRestVO<AsociadoDTO>();
		 try {
    		resultado.setObjetoResultado(empresaServiceLocal.controladorAccionAsociado(Asociado,accionType));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<AsociadoDTO> finById(@PathParam("id") String idAsociado) throws Exception {
		AsociadoDTO Asociado = new AsociadoDTO();
		Asociado.setIdAsociado(idAsociado);
		return controladorAccion(Asociado,AccionType.FIND_BY_ID);
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<AsociadoDTO> listarAsociado(@Context UriInfo info){
		ResultadoRestVO<AsociadoDTO> resultado = new ResultadoRestVO<AsociadoDTO>();
		AsociadoDTO Asociado = transferUriInfo(info);
		 try {
			resultado.setListaResultado(empresaServiceLocal.listarAsociado(Asociado));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/contar")
	public ResultadoRestVO<AsociadoDTO> contarAsociado(@Context UriInfo info){
		ResultadoRestVO<AsociadoDTO> resultado = new ResultadoRestVO<AsociadoDTO>();
		AsociadoDTO Asociado = transferUriInfo(info);
		 try {
			 resultado.setContador(empresaServiceLocal.contarListarAsociado(Asociado));
			 if (resultado.isData()) {
				resultado.setListaResultado(empresaServiceLocal.listarAsociado(Asociado));
			 }
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/")
	public ResultadoRestVO<AsociadoDTO> inicializarAsociado(@Context UriInfo info) throws Exception {
	     AsociadoDTO Asociado = transferUriInfo(info);
		 ResultadoRestVO<AsociadoDTO> resultado = new ResultadoRestVO<AsociadoDTO>();
		 try {
    		resultado.setObjetoResultado(Asociado);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private AsociadoDTO transferUriInfo(@Context UriInfo info) {
		AsociadoDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,AsociadoDTO.class);
		return resultado;
	}
	
	
	@GET
    @Path("/listarAsociadoFamilia")
	public ResultadoRestVO<AsociadoFamiliaDTO> listarAsociadoFamilia(@Context UriInfo info){
		ResultadoRestVO<AsociadoFamiliaDTO> resultado = new ResultadoRestVO<AsociadoFamiliaDTO>();
		AsociadoFamiliaDTO asociadoFamilia = transferUriInfoDetalle(info);
		 try {
			resultado.setListaResultado(empresaServiceLocal.listarAsociadoFamilia(asociadoFamilia));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	
	private AsociadoFamiliaDTO transferUriInfoDetalle(@Context UriInfo info) {
		AsociadoFamiliaDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,AsociadoFamiliaDTO.class);
		return resultado;
	}
	
	@GET
	@Path("/findAsociadoByNro/{nroDco}")
	public ResultadoRestVO<AsociadoDTO> findAsociadoByNro(@PathParam("nroDco") String nroDco) throws Exception {
		ResultadoRestVO<AsociadoDTO> resultado = new ResultadoRestVO<AsociadoDTO>();
		 try {
   		resultado.setObjetoResultado(empresaServiceLocal.findAsociadoByNro(nroDco));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
}