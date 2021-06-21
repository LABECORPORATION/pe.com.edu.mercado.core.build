package pe.com.edu.siaa.core.ejb.service.rest.common.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import pe.com.edu.siaa.core.ejb.util.cache.SelectItemServiceCacheUtil;
import pe.com.edu.siaa.core.model.dto.common.ItemDTO;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.model.type.ListaItemType;
import pe.com.edu.siaa.core.model.vo.SelectItemVO;
import pe.com.edu.siaa.core.vo.ResultadoRestVO;


/**
 * La Class ItemRestController.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 13:43:26 COT 2017
 * @since SIAA-CORE 2.1
 */
@Stateless
@Path("/itemRestController")
@Consumes(MediaType.APPLICATION_JSON )
@Produces(MediaType.APPLICATION_JSON)
public class ItemRestController extends GenericServiceRestImpl {
 
	@EJB
	private transient CommonServiceLocal commonServiceLocal;
	
	@POST
	public ResultadoRestVO<ItemDTO> crear(ItemDTO item) throws Exception {
		return controladorAccion(item,AccionType.CREAR);
	}
	@PUT
	public ResultadoRestVO<ItemDTO> modificar(ItemDTO item) throws Exception {
		return controladorAccion(item,AccionType.MODIFICAR);
	}
	@DELETE
	@Path("/{id}")
	public ResultadoRestVO<ItemDTO> eliminar(@PathParam("id") Long idItem) throws Exception {
		ItemDTO item = new ItemDTO();
		item.setIdItem(idItem);		
		return controladorAccion(item,AccionType.ELIMINAR);
	}
	private ResultadoRestVO<ItemDTO> controladorAccion(ItemDTO item, AccionType accionType){
		ResultadoRestVO<ItemDTO> resultado = new ResultadoRestVO<ItemDTO>();
		 try {
    		resultado.setObjetoResultado(commonServiceLocal.controladorAccionItem(item,accionType));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
	@Path("/get/{id}")
	public ResultadoRestVO<ItemDTO> finById(@PathParam("id") Long idItem) throws Exception {
		ItemDTO item = new ItemDTO();
		item.setIdItem(idItem);
		return controladorAccion(item,AccionType.FIND_BY_ID);
	}
	
	@GET
    @Path("/listar")
	public ResultadoRestVO<ItemDTO> listarItem(@Context UriInfo info){
		ResultadoRestVO<ItemDTO> resultado = new ResultadoRestVO<ItemDTO>();
		ItemDTO item = transferUriInfo(info);
		 try {
    		resultado.setListaResultado(commonServiceLocal.listarItem(item));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	
	@GET
    @Path("/contar")
	public ResultadoRestVO<ItemDTO> contarItem(@Context UriInfo info){
		ResultadoRestVO<ItemDTO> resultado = new ResultadoRestVO<ItemDTO>();
		ItemDTO item = transferUriInfo(info);
		 try {
    		resultado.setContador(commonServiceLocal.contarListarItem(item));
			if (resultado.isData()) {
				resultado.setListaResultado(commonServiceLocal.listarItem(item));
			}
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/listaItemSelectItem/{idListaItems}")
	public ResultadoRestVO<SelectItemVO> listaItemSelectItem(@PathParam("idListaItems") Long idListaItems){
		ResultadoRestVO<SelectItemVO> resultado = new ResultadoRestVO<SelectItemVO>();
		 try {
			resultado.setListaResultado(SelectItemServiceCacheUtil.getInstance().obtenerSelecCboItem(idListaItems));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/listaItemSelectItemMap")
	public ResultadoRestVO<Map<Long,List<SelectItemVO>> > listaItemSelectItemMap(@Context UriInfo info){
		ResultadoRestVO<Map<Long,List<SelectItemVO>> > resultado = new ResultadoRestVO<Map<Long,List<SelectItemVO>> >();
		List<String> listaIdItems = info.getQueryParameters().get("idListaItems");
		 try {
			 Map<Long,List<SelectItemVO>> resul = new HashMap<Long,List<SelectItemVO>>();
			 for (String id : listaIdItems) {
				 Long idListaItems = Long.valueOf(id);
				 resul.put(idListaItems, SelectItemServiceCacheUtil.getInstance().obtenerSelecCboItem(idListaItems));
			}
			 resultado.setObjetoResultado(resul);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/listaSelectItemVOMap")
	public ResultadoRestVO<Map<String,List<SelectItemVO>> > listaSelectItemVOMap(@Context UriInfo info){
		ResultadoRestVO<Map<String,List<SelectItemVO>> > resultado = new ResultadoRestVO<Map<String,List<SelectItemVO>> >();
		List<String> listaKeySelectItemVO = info.getQueryParameters().get("keySelectItem");
		 try {
			 Map<String,List<SelectItemVO>> resul = new HashMap<String,List<SelectItemVO>>();
			 for (String key : listaKeySelectItemVO) {
				 resul.put(key, SelectItemServiceCacheUtil.getInstance().obtenerSeleItemVOByKey(key));
			 }
			 resultado.setObjetoResultado(resul);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	@GET
    @Path("/")
	public ResultadoRestVO<ItemDTO> inicializarItem(@Context UriInfo info) throws Exception {
	     ItemDTO item = transferUriInfo(info);
		 ResultadoRestVO<ItemDTO> resultado = new ResultadoRestVO<ItemDTO>();
		 try {
    		resultado.setObjetoResultado(item);
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
	private ItemDTO transferUriInfo(@Context UriInfo info) {
		ItemDTO resultado = TransferDataObjectUtil.transferObjetoEntityGetRestDTO(info,ItemDTO.class);
		return resultado;
	}
	
	//agregado
	@GET
    @Path("/obteneItemId")
	public ResultadoRestVO<ItemDTO> obteneItemId(@Context UriInfo info) throws Exception {
		ItemDTO item = transferUriInfo(info);
		 ResultadoRestVO<ItemDTO> resultado = new ResultadoRestVO<ItemDTO>();
		 try {
			 ListaItemType itemState = ListaItemType.get(item.getIdItem());
			resultado.setObjetoResultado(commonServiceLocal.obteneItemId(itemState));
		} catch (Exception e) {
			parsearResultadoError(e, resultado);
		}
		return resultado;
	}
}