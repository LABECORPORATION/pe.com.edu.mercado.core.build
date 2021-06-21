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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import pe.com.builderp.core.facturacion.ejb.service.empresa.local.EmpresaServiceLocal;
import pe.com.builderp.core.facturacion.model.dto.empresa.PersonalDTO; 
import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.service.rest.impl.GenericServiceRestImpl;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.model.vo.PagoPersonalVO;
import pe.com.edu.siaa.core.model.vo.VentaFiltroVO;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class PersonalRestController.
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
@Path("/personalRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class PersonalRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient EmpresaServiceLocal empresaServiceLocal;
	
	@POST
	public ResultadoRestVO<PersonalDTO> crear(PersonalDTO Personal) throws Exception {
		return controladorAccion(Personal,AccionType.CREAR);
	}
	@PUT
	public ResultadoRestVO<PersonalDTO> modificar(PersonalDTO Personal) throws Exception {
		return controladorAccion(Personal,AccionType.MODIFICAR);
	}
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<PersonalDTO> eliminar(@PathParam("id") String idPersonal) throws Exception {
		PersonalDTO Personal = new PersonalDTO();
		Personal.setIdPersonal(idPersonal);		
		return controladorAccion(Personal,AccionType.ELIMINAR);
	}
	private ResultadoRestVO<PersonalDTO> controladorAccion(PersonalDTO Personal, AccionType accionType){
		ResultadoRestVO<PersonalDTO> resultado = new ResultadoRestVO<PersonalDTO>();
		 try {
    		resultado.setObjetoResultado(empresaServiceLocal.controladorAccionPersonal(Personal,accionType));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<PersonalDTO> finById(@PathParam("id") String idPersonal) throws Exception {
		PersonalDTO Personal = new PersonalDTO();
		Personal.setIdPersonal(idPersonal);
		return controladorAccion(Personal,AccionType.FIND_BY_ID);
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<PersonalDTO> listarPersonal(@Context UriInfo info){
		ResultadoRestVO<PersonalDTO> resultado = new ResultadoRestVO<PersonalDTO>();
		PersonalDTO Personal = transferUriInfo(info);
		 try {
			resultado.setListaResultado(empresaServiceLocal.listarPersonal(Personal));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/contar")
	public ResultadoRestVO<PersonalDTO> contarPersonal(@Context UriInfo info){
		ResultadoRestVO<PersonalDTO> resultado = new ResultadoRestVO<PersonalDTO>();
		PersonalDTO Personal = transferUriInfo(info);
		 try {
			 resultado.setContador(empresaServiceLocal.contarListarPersonal(Personal));
			 if (resultado.isData()) {
				resultado.setListaResultado(empresaServiceLocal.listarPersonal(Personal));
			 }
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/")
	public ResultadoRestVO<PersonalDTO> inicializarPersonal(@Context UriInfo info) throws Exception {
	     PersonalDTO Personal = transferUriInfo(info);
		 ResultadoRestVO<PersonalDTO> resultado = new ResultadoRestVO<PersonalDTO>();
		 try {
    		resultado.setObjetoResultado(Personal);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private PersonalDTO transferUriInfo(@Context UriInfo info) {
		PersonalDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,PersonalDTO.class);
		return resultado;
	}
	
	//
	
	@POST
    @Path("/listarPersonalTemp")
	public ResultadoRestVO<PagoPersonalVO> listarPersonalTemp(@Context UriInfo info, VentaFiltroVO filtro){
		ResultadoRestVO<PagoPersonalVO> resultado = new ResultadoRestVO<PagoPersonalVO>();
		//DetalleEntregaDTO detalleEntrega = transferUriInfoDetalle(info);
		 try {
			resultado.setListaResultado(empresaServiceLocal.listarPersonalTemp(filtro));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	
	//
	
	@POST
    @Path("/listoCrear")
	public ResultadoRestVO<PagoPersonalVO> listoCrear(@Context UriInfo info,List<PagoPersonalVO> listaPagoPersonalVO){
		ResultadoRestVO<PagoPersonalVO> resultado = new ResultadoRestVO<PagoPersonalVO>();
		//DetalleEntregaDTO detalleEntrega = transferUriInfoDetalle(info);
		 try {
			 empresaServiceLocal.registrarPagoPersonal(listaPagoPersonalVO);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	
	
	@GET
	@Path("/findPersonalByNro/{nroDco}")
	public ResultadoRestVO<PersonalDTO> findPersonalByNro(@PathParam("nroDco") String nroDco) throws Exception {
		ResultadoRestVO<PersonalDTO> resultado = new ResultadoRestVO<PersonalDTO>();
		 try {
   		resultado.setObjetoResultado(empresaServiceLocal.findPersonalByNro(nroDco));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
}