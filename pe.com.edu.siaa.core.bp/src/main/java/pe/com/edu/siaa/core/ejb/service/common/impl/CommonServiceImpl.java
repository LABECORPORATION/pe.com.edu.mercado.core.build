package pe.com.edu.siaa.core.ejb.service.common.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import pe.com.edu.siaa.core.ejb.dao.common.local.ItemDaoLocal;
import pe.com.edu.siaa.core.ejb.dao.common.local.ListaItemsDaoLocal;
import pe.com.edu.siaa.core.ejb.dao.common.local.ListaValoresDaoLocal;
import pe.com.edu.siaa.core.ejb.dao.common.local.ParametroDaoLocal;
import pe.com.edu.siaa.core.ejb.dao.common.local.UbigeoDaoLocal;
import pe.com.edu.siaa.core.ejb.dao.contabilidad.local.ConfiguracionCuentaDaoLocal;
import pe.com.edu.siaa.core.ejb.factory.TransferDataObjectUtil;
import pe.com.edu.siaa.core.ejb.service.common.local.CommonServiceLocal;
import pe.com.edu.siaa.core.ejb.util.img.Base64ImageUtils;
import pe.com.edu.siaa.core.model.dto.common.ItemDTO;
import pe.com.edu.siaa.core.model.dto.common.ListaItemsDTO;
import pe.com.edu.siaa.core.model.dto.common.ListaValoresDTO;
import pe.com.edu.siaa.core.model.dto.common.ParametroDTO;
import pe.com.edu.siaa.core.model.dto.common.UbigeoDTO;
import pe.com.edu.siaa.core.model.dto.contabilidad.PlanContableDTO;
import pe.com.edu.siaa.core.model.estate.EstadoGeneralState;
import pe.com.edu.siaa.core.model.jpa.common.Item;
import pe.com.edu.siaa.core.model.jpa.common.ListaItems;
import pe.com.edu.siaa.core.model.jpa.common.ListaValores;
import pe.com.edu.siaa.core.model.jpa.common.Parametro;
import pe.com.edu.siaa.core.model.jpa.common.Ubigeo;
import pe.com.edu.siaa.core.model.jpa.contabilidad.ConfiguracionCuenta;
import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.model.type.ListaItemType;
import pe.com.edu.siaa.core.model.util.ConstanteConfigUtil;
import pe.com.edu.siaa.core.model.util.StringUtils;
import pe.com.edu.siaa.core.model.vo.FileVO;
import pe.com.edu.siaa.core.model.vo.SelectItemVO;


/**
 * La Class CommonServiceImpl.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 13:43:23 COT 2017
 * @since SIAA-CORE 2.1
 */
 @Stateless
 @EJB(name = "java:app/CommonService", beanInterface = CommonServiceLocal.class)
 @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class CommonServiceImpl implements CommonServiceLocal{
	
	/** El servicio lista items dao impl. */
	@EJB
	private ListaItemsDaoLocal listaItemsDaoImpl; 
	
	/** El servicio item dao impl. */
	@EJB
	private ItemDaoLocal itemDaoImpl; 
	
	/** El servicio ubigeo dao impl. */
	@EJB
	private UbigeoDaoLocal ubigeoDaoImpl; 
	
	/** El servicio lista valores dao impl. */
	@EJB
	private ListaValoresDaoLocal listaValoresDaoImpl; 

	/** El servicio configuracion cuenta dao impl. */
	@EJB
	private ConfiguracionCuentaDaoLocal configuracionCuentaDaoImpl; 
	
	/** El servicio parametro dao impl. */
	@EJB
	private ParametroDaoLocal parametroDaoImpl; 
	
	@Override
	public void subirImagen(FileVO fileVO)  throws Exception {
		Base64ImageUtils.decoder(fileVO.getDataBase64(), fileVO.getRuta());
	}
	
	@Override
	public String obtenerImagenEncodeBase64(FileVO fileVO)  throws Exception {
		return Base64ImageUtils.encoder(fileVO.getRuta());
	}
	@Override
	public ParametroDTO controladorAccionParametro(ParametroDTO parametro, AccionType accionType) throws Exception {
		ParametroDTO resultado = null;
		Parametro resultadoEntity = null;
		switch (accionType) {
			case CREAR:
				parametro.setIdParametro(this.parametroDaoImpl.generarIdParametro());
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(parametro, Parametro.class,"idEntidad@PK@");
				this.parametroDaoImpl.save(resultadoEntity);	
				resultado = parametro;
				break;				
			case MODIFICAR:
			    resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(parametro, Parametro.class,"idEntidad@PK@");
				this.parametroDaoImpl.update(resultadoEntity);
				resultado = parametro;	
				break;
				
			case ELIMINAR:
				resultadoEntity = this.parametroDaoImpl.find(Parametro.class, parametro.getIdParametro());
				this.parametroDaoImpl.delete(resultadoEntity);
				resultado = parametro;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.parametroDaoImpl.find(Parametro.class, parametro.getIdParametro());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,ParametroDTO.class);
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.parametroDaoImpl.findByNombre(parametro),ParametroDTO .class);
				break;*/
				
			default:
				break;
		}
		
		return resultado;
	}
	@Override
	public List<ParametroDTO> listarParametro(ParametroDTO parametro) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityDTOList(this.parametroDaoImpl.listarParametro(parametro),ParametroDTO.class,"entidad");
	}
	@Override
	public int contarListarParametro(ParametroDTO parametro){
		return  this.parametroDaoImpl.contarListarParametro(parametro);
	}
	
	@Override
	public List<SelectItemVO> listarUbigeoSelectItem(List<UbigeoDTO> listaUbigeo) throws Exception {
		List<SelectItemVO> resultado = new ArrayList<SelectItemVO>();
		for (UbigeoDTO ubigeoResul : listaUbigeo) {
			resultado.add(new SelectItemVO(ubigeoResul.getIdUbigeo(), ubigeoResul.getDescripcion(), ubigeoResul.getDescripcion()));
		}
		return resultado;
	}
	@Override
	public Map<Long,List<SelectItemVO>> convertirListaItemSelectItemMap(List<ItemDTO> listaItem) throws Exception {
		Map<Long,List<SelectItemVO>> resultado = new HashMap<Long, List<SelectItemVO>>();		
		if (listaItem != null && listaItem.size() > 0) {
			for (ItemDTO items : listaItem) {
				Long idListaItemActual = items.getListaItems().getIdListaItems();
				if (!resultado.containsKey(idListaItemActual)) {
					List<SelectItemVO> value = new ArrayList<SelectItemVO>();
					value.add(new SelectItemVO(items.getIdItem(), items.getNombre(), items.getCodigo() + ""));
					resultado.put(idListaItemActual, value);
				} else {
					List<SelectItemVO> value = resultado.get(idListaItemActual);
					value.add(new SelectItemVO(items.getIdItem(), items.getNombre(), items.getCodigo() + ""));
					resultado.put(idListaItemActual, value);
				}
			}
			
		}
		return resultado;
	}
	
	@Override
	public List<ItemDTO> listarItem() throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityDTOList(this.itemDaoImpl.listarItem(),ItemDTO.class,"listaItems");
	}
	
	
	@Override
	public ListaItemsDTO controladorAccionListaItems(ListaItemsDTO listaItems, AccionType accionType) throws Exception {
		ListaItemsDTO resultado = null;
		ListaItems resultadoEntity = null;
		switch (accionType) {
			case CREAR:
				listaItems.setIdListaItems(this.listaItemsDaoImpl.generarIdListaItems());
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(listaItems, ListaItems.class);
				this.listaItemsDaoImpl.save(resultadoEntity);	
				resultado = listaItems;
				break;				
			case MODIFICAR:
			    resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(listaItems, ListaItems.class);
				this.listaItemsDaoImpl.update(resultadoEntity);
				resultado = listaItems;	
				break;
				
			case ELIMINAR:
				resultadoEntity = this.listaItemsDaoImpl.find(ListaItems.class, listaItems.getIdListaItems());
				this.listaItemsDaoImpl.delete(resultadoEntity);
				resultado = listaItems;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.listaItemsDaoImpl.find(ListaItems.class, listaItems.getIdListaItems());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,ListaItemsDTO.class);
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.listaItemsDaoImpl.findByNombre(listaItems),ListaItemsDTO .class);
				break;*/
				
			default:
				break;
		}
		
		return resultado;
	}
	@Override
	public List<ListaItemsDTO> listarListaItems(ListaItemsDTO listaItems) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityDTOList(this.listaItemsDaoImpl.listarListaItems(listaItems),ListaItemsDTO.class);
	}
	@Override
	public int contarListarListaItems(ListaItemsDTO listaItems){
		return  this.listaItemsDaoImpl.contarListarListaItems(listaItems);
	}
	
	
	@Override
	public ItemDTO controladorAccionItem(ItemDTO item, AccionType accionType) throws Exception {
		ItemDTO resultado = null;
		Item resultadoEntity = null;
		switch (accionType) {
			case CREAR:
				item.setIdItem(this.itemDaoImpl.generarIdItem());
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(item, Item.class,"listaItems@PK@");
				this.itemDaoImpl.save(resultadoEntity);	
				resultado = item;
				break;				
			case MODIFICAR:
			    resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(item, Item.class,"listaItems@PK@");
				this.itemDaoImpl.update(resultadoEntity);
				resultado = item;	
				break;
				
			case ELIMINAR:
				resultadoEntity = this.itemDaoImpl.find(Item.class, item.getIdItem());
				this.itemDaoImpl.delete(resultadoEntity);
				resultado = item;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.itemDaoImpl.find(Item.class, item.getIdItem());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,ItemDTO.class);
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.itemDaoImpl.findByNombre(item),ItemDTO .class);
				break;*/
				
			default:
				break;
		}
		
		return resultado;
	}
	@Override
	public List<ItemDTO> listarItem(ItemDTO item) throws Exception {
		List<ItemDTO> resultado = TransferDataObjectUtil.transferObjetoEntityDTOList(this.itemDaoImpl.listarItem(item),ItemDTO.class);
		if (StringUtils.isNotNullOrBlank(item.getTipo())) {
			if (!StringUtils.isNullOrEmpty(item.getTipo())) {
				if ("LIBRO".equalsIgnoreCase(item.getTipo()) || "SUBLIBRO".equalsIgnoreCase(item.getTipo()) ) {
					List<Long> listaIdItem = new ArrayList<Long>();
					for (ItemDTO itemDTO : resultado) {
						listaIdItem.add(itemDTO.getIdItem());						
					}
					Map<Long,ConfiguracionCuenta> configuracionCuentaMap = this.configuracionCuentaDaoImpl.listarConfiguracionCuentaMap(listaIdItem,EstadoGeneralState.ACTIVO);
					for (ItemDTO itemDTO : resultado) {
						if (configuracionCuentaMap.containsKey(itemDTO.getIdItem())) {
							itemDTO.setPlanContable(TransferDataObjectUtil.transferObjetoEntityDTO(configuracionCuentaMap.get(itemDTO.getIdItem()).getPlanContable(), PlanContableDTO.class));
						}
					}
			    }
	 	     }
		}
		return  resultado;
	}
	@Override
	public int contarListarItem(ItemDTO item){
		return  this.itemDaoImpl.contarListarItem(item);
	}
	
	@Override
	public UbigeoDTO controladorAccionUbigeo(UbigeoDTO ubigeo, AccionType accionType) throws Exception {
		UbigeoDTO resultado = null;
		Ubigeo resultadoEntity = null;
		switch (accionType) {
			case CREAR:
				ubigeo.setIdUbigeo(this.ubigeoDaoImpl.generarIdUbigeo());
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(ubigeo, Ubigeo.class,"ubigeoByDependencia@PK@");
				this.ubigeoDaoImpl.save(resultadoEntity);	
				resultado = ubigeo;
				break;				
			case MODIFICAR:
			    resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(ubigeo, Ubigeo.class,"ubigeoByDependencia@PK@");
				this.ubigeoDaoImpl.update(resultadoEntity);
				resultado = ubigeo;	
				break;
				
			case ELIMINAR:
				resultadoEntity = this.ubigeoDaoImpl.find(Ubigeo.class, ubigeo.getIdUbigeo());
				this.ubigeoDaoImpl.delete(resultadoEntity);
				resultado = ubigeo;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.ubigeoDaoImpl.find(Ubigeo.class, ubigeo.getIdUbigeo());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,UbigeoDTO.class);
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.ubigeoDaoImpl.findByNombre(ubigeo),UbigeoDTO .class);
				break;*/
				
			default:
				break;
		}
		
		return resultado;
	}
	@Override
	public List<UbigeoDTO> listarUbigeo(UbigeoDTO ubigeo) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityDTOList(this.ubigeoDaoImpl.listarUbigeo(ubigeo),UbigeoDTO.class,"ubigeoByDependencia");
	}
	@Override
	public int contarListarUbigeo(UbigeoDTO ubigeo){
		return  this.ubigeoDaoImpl.contarListarUbigeo(ubigeo);
	}

	@Override
	public ListaValoresDTO controladorAccionListaValores(ListaValoresDTO listaValores, AccionType accionType) throws Exception {
		ListaValoresDTO resultado = null;
		ListaValores resultadoEntity = null;
		switch (accionType) {
			case CREAR:
				listaValores.setIdListaValores(this.listaValoresDaoImpl.generarIdListaValores());
				resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(listaValores, ListaValores.class);
				this.listaValoresDaoImpl.save(resultadoEntity);	
				resultado = listaValores;
				break;				
			case MODIFICAR:
			    resultadoEntity = TransferDataObjectUtil.transferObjetoEntity(listaValores, ListaValores.class);
				this.listaValoresDaoImpl.update(resultadoEntity);
				resultado = listaValores;	
				break;
				
			case ELIMINAR:
				resultadoEntity = this.listaValoresDaoImpl.find(ListaValores.class, listaValores.getIdListaValores());
				this.listaValoresDaoImpl.delete(resultadoEntity);
				resultado = listaValores;
				break;
				
			case FIND_BY_ID:
				resultadoEntity = this.listaValoresDaoImpl.find(ListaValores.class, listaValores.getIdListaValores());
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(resultadoEntity,ListaValoresDTO.class);
				break;
				
			/*case FIND_BY_NOMBRE:
				resultado = TransferDataObjectUtil.transferObjetoEntityDTO(this.listaValoresDaoImpl.findByNombre(listaValores),ListaValoresDTO .class);
				break;*/
				
			default:
				break;
		}
		
		return resultado;
	}
	@Override
	public List<ListaValoresDTO> listarListaValores(ListaValoresDTO listaValores) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityDTOList(this.listaValoresDaoImpl.listarListaValores(listaValores),ListaValoresDTO.class);
	}
	@Override
	public int contarListarListaValores(ListaValoresDTO listaValores){
		return  this.listaValoresDaoImpl.contarListarListaValores(listaValores);
	}
	
	
	@Override
	public ItemDTO obteneItemId(ListaItemType itemState) throws Exception {
		return TransferDataObjectUtil.transferObjetoEntityDTO(itemDaoImpl.obteneItemId(itemState),ItemDTO.class);
	}
}