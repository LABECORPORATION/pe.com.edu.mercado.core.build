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
import pe.com.edu.siaa.core.model.dto.common.ListaItemsDTO;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class ListaItemsRestController.
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
@Path("/listaItemsRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class ListaItemsRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient CommonServiceLocal commonServiceLocal;
	
	@POST
	public ResultadoRestVO<ListaItemsDTO> crear(ListaItemsDTO listaItems) throws Exception {
		return controladorAccion(listaItems,AccionType.CREAR);
	}
	@PUT
	public ResultadoRestVO<ListaItemsDTO> modificar(ListaItemsDTO listaItems) throws Exception {
		return controladorAccion(listaItems,AccionType.MODIFICAR);
	}
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<ListaItemsDTO> eliminar(@PathParam("id") Long idListaItems) throws Exception {
		ListaItemsDTO listaItems = new ListaItemsDTO();
		listaItems.setIdListaItems(idListaItems);		
		return controladorAccion(listaItems,AccionType.ELIMINAR);
	}
	private ResultadoRestVO<ListaItemsDTO> controladorAccion(ListaItemsDTO listaItems, AccionType accionType){
		ResultadoRestVO<ListaItemsDTO> resultado = new ResultadoRestVO<ListaItemsDTO>();
		 try {
    		resultado.setObjetoResultado(commonServiceLocal.controladorAccionListaItems(listaItems,accionType));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<ListaItemsDTO> finById(@PathParam("id") Long idListaItems) throws Exception {
		ListaItemsDTO listaItems = new ListaItemsDTO();
		listaItems.setIdListaItems(idListaItems);
		return controladorAccion(listaItems,AccionType.FIND_BY_ID);
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<ListaItemsDTO> listarListaItems(@Context UriInfo info){
		ResultadoRestVO<ListaItemsDTO> resultado = new ResultadoRestVO<ListaItemsDTO>();
		ListaItemsDTO listaItems = transferUriInfo(info);
		 try {
			 resultado.setListaResultado(commonServiceLocal.listarListaItems(listaItems));			
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/contar")
	public ResultadoRestVO<ListaItemsDTO> contarListarListaItems(@Context UriInfo info){
		ResultadoRestVO<ListaItemsDTO> resultado = new ResultadoRestVO<ListaItemsDTO>();
		ListaItemsDTO listaItems = transferUriInfo(info);
		 try {
    		resultado.setContador(commonServiceLocal.contarListarListaItems(listaItems));
    		 if (resultado.isData()) {
				 resultado.setListaResultado(commonServiceLocal.listarListaItems(listaItems));
			 }
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/")
	public ResultadoRestVO<ListaItemsDTO> inicializarListaItems(@Context UriInfo info) throws Exception {
	     ListaItemsDTO listaItems = transferUriInfo(info);
		 ResultadoRestVO<ListaItemsDTO> resultado = new ResultadoRestVO<ListaItemsDTO>();
		 try {
    		resultado.setObjetoResultado(listaItems);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private ListaItemsDTO transferUriInfo(@Context UriInfo info) {
		ListaItemsDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,ListaItemsDTO.class);
		return resultado;
	}
}