package pe.com.edu.siaa.core.ejb.service.rest.common.impl;

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

import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.service.common.local.CommonServiceLocal;
import pe.com.edu.siaa.core.ejb.service.rest.impl.GenericServiceRestImpl;
import pe.com.edu.siaa.core.model.dto.common.ListaValoresDTO;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class ListaValoresRestController.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 13:43:39 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
@Path("/listaValoresRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class ListaValoresRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient CommonServiceLocal commonServiceLocal;
	
	@POST
	public ResultadoRestVO<ListaValoresDTO> crear(ListaValoresDTO listaValores) throws Exception {
		return controladorAccion(listaValores,AccionType.CREAR);
	}
	@PUT
	public ResultadoRestVO<ListaValoresDTO> modificar(ListaValoresDTO listaValores) throws Exception {
		return controladorAccion(listaValores,AccionType.MODIFICAR);
	}
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<ListaValoresDTO> eliminar(@PathParam("id") Long idListaValores) throws Exception {
		ListaValoresDTO listaValores = new ListaValoresDTO();
		listaValores.setIdListaValores(idListaValores);		
		return controladorAccion(listaValores,AccionType.ELIMINAR);
	}
	private ResultadoRestVO<ListaValoresDTO> controladorAccion(ListaValoresDTO listaValores, AccionType accionType){
		ResultadoRestVO<ListaValoresDTO> resultado = new ResultadoRestVO<ListaValoresDTO>();
		 try {
    		resultado.setObjetoResultado(commonServiceLocal.controladorAccionListaValores(listaValores,accionType));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<ListaValoresDTO> finById(@PathParam("id") Long idListaValores) throws Exception {
		ListaValoresDTO listaValores = new ListaValoresDTO();
		listaValores.setIdListaValores(idListaValores);
		return controladorAccion(listaValores,AccionType.FIND_BY_ID);
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<ListaValoresDTO> listarListaValores(@Context UriInfo info){
		ResultadoRestVO<ListaValoresDTO> resultado = new ResultadoRestVO<ListaValoresDTO>();
		ListaValoresDTO listaValores = transferUriInfo(info);
		 try {
			 resultado.setContador(commonServiceLocal.contarListarListaValores(listaValores));
			 if (resultado.isData()) {
				 resultado.setListaResultado(commonServiceLocal.listarListaValores(listaValores));
			 }
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	
	@GET
    @Path("/")
	public ResultadoRestVO<ListaValoresDTO> inicializarListaValores(@Context UriInfo info) throws Exception {
	     ListaValoresDTO listaValores = transferUriInfo(info);
		 ResultadoRestVO<ListaValoresDTO> resultado = new ResultadoRestVO<ListaValoresDTO>();
		 try {
    		resultado.setObjetoResultado(listaValores);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private ListaValoresDTO transferUriInfo(@Context UriInfo info) {
		ListaValoresDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,ListaValoresDTO.class);
		return resultado;
	}
}