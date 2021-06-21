package pe.com.edu.siaa.core.ejb.util.cache;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pe.com.edu.siaa.core.ejb.factory.CollectionUtil;

import pe.com.edu.siaa.core.ejb.service.common.local.CommonServiceLocal;
import pe.com.edu.siaa.core.ejb.service.helper.Referencia;

import pe.com.edu.siaa.core.ejb.util.log.Logger;

import pe.com.edu.siaa.core.model.dto.common.ItemDTO;
import pe.com.edu.siaa.core.model.dto.common.UbigeoDTO;

import pe.com.edu.siaa.core.model.type.AccionType;
import pe.com.edu.siaa.core.model.type.ListaItemType;
import pe.com.edu.siaa.core.model.type.TipoPersonaType;
import pe.com.edu.siaa.core.model.type.TipoUbigeoType;
import pe.com.edu.siaa.core.model.util.StringUtils;
import pe.com.edu.siaa.core.model.vo.SelectItemVO;

/**
 * <ul>
 * <li>Copyright 2012 BUILD SOFT S.A.C - BS. Todos los derechos reservados.</li>
 * </ul>
 * 
 * La Class SelectItemServiceCacheUtil.
 *
 * @author ndavilal.
 * @version 1.0 , 25/03/2012
 * @since SIAA 2.0
 */

public class SelectItemServiceCacheUtil implements Serializable {

	/** La Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** 
	 * Logger para el registro de errores.
	 */
	private Logger log = Logger.getLogger(SelectItemServiceCacheUtil.class);
	 
	/** La configurador cache utl. */
	private static SelectItemServiceCacheUtil selectItemServiceCacheUtil = null;
		
  	/** El flag cargo listado. */
	private boolean flagCargoListado = false;
	
	/** El service common impl. */
	private CommonServiceLocal commonServiceImpl;	
	
	
	/** El service matricula impl. */
	//private PagoServiceLocal pagoServiceImpl;
	
	
	
	/** La lista item select item map. */
	private Map<Long, List<SelectItemVO>> listaItemSelectItemVOMap = new HashMap<Long, List<SelectItemVO>>();
	
	/** La lista item cbo select one item map. */
//	private Map<Long, List<SelectItemVO>> listaItemCboSelectOneItemMap = new HashMap<Long, List<SelectItemVO>>();
		
	
	
	/** La escuela nro ciclo map. */
	private Map<String,Integer> escuelaNroCicloMap = new HashMap<String, Integer>(); 
	
	/** La item map. */
	private Map<Long,ItemDTO> itemMap = new HashMap<Long, ItemDTO>(); 
	
	/** La item map. */
	private Map<String,ItemDTO> itemByCodigoMap = new HashMap<String, ItemDTO>(); 
	
	/** La ubigeo map. */
	private Map<String,List<SelectItemVO>> ubigeoMap = new HashMap<String, List<SelectItemVO>>();

	/** La ubigeo all map. */
	private Map<String,UbigeoDTO> ubigeoAllMap = new HashMap<String, UbigeoDTO>();

	/** La lista item select item map. */
	private Map<String, List<SelectItemVO>> listaSelectItemVOMap = new HashMap<String, List<SelectItemVO>>();

	/**
	 * Instancia un nuevo select item service m bean.
	 */
	public SelectItemServiceCacheUtil() {
		
	} 
	
	/**
	 * Instanciar.
	 *
	 * @return the configurador cache utl
	 */
	public static SelectItemServiceCacheUtil getInstance() {
		if (selectItemServiceCacheUtil == null) {
			createInstance();
		} else if (!selectItemServiceCacheUtil.isFlagCargoListado()) {
			selectItemServiceCacheUtil.sincronizarData();
		}
		return selectItemServiceCacheUtil;
	}
	 /**
     * Creates the instance.
     */
    private static synchronized void createInstance() {
    	if (selectItemServiceCacheUtil == null) {
    		selectItemServiceCacheUtil = new SelectItemServiceCacheUtil();
    		selectItemServiceCacheUtil.sincronizarData();
		}
    }

	/**
	 * Sincronizar data.
	 *
	 * @return the string
	 */
	public String sincronizarData() {
		return inicializar();
	}

	
	/**
	 * Inicializar.
	 */
	public String inicializar() {
		String resultado = "";
		try {
			commonServiceImpl = Referencia.getReference(CommonServiceLocal.class);			
		} catch (Exception e) {
			log.error(e);
			resultado = e.getMessage();
		}
		
		//cargarTipoDocSunat();		
		cargarUbigeo();
		try {
			
			List<ListaItemType> listaItemType = new ArrayList<ListaItemType>();
			for (ListaItemType objType : ListaItemType.values()) {
				listaItemType.add(objType);
			}
			itemMap = new HashMap<Long, ItemDTO>();
			itemByCodigoMap = new HashMap<String, ItemDTO>();
			List<ItemDTO> listaItemTemp = commonServiceImpl.listarItem();
			for (ItemDTO item : listaItemTemp) {
				itemMap.put(item.getIdItem(), item);
				itemByCodigoMap.put(item.getListaItems().getIdListaItems() + "" + item.getCodigoExterno(), item);
			}
			listaItemSelectItemVOMap = commonServiceImpl.convertirListaItemSelectItemMap(listaItemTemp);
			generarListaLenguajeSelect();
			this.flagCargoListado = true;
		} catch (Exception e) {
			log.error(e);
			resultado = e.getMessage();
		}
		return resultado;
	}
	
	/**
	 * Cargar tipo doc sunat.
	 */
	/*
	public void cargarTipoDocSunat() {
		try {
			List<SelectItemVO> listaTipoDocSunat = pagoServiceImpl.listarTipoDocSunatSelectItem();
			this.listaSelectItemVOMap.put("tipoDocSunat", listaTipoDocSunat);
		} catch (Exception e) {
			log.error(e);
		}
	}*/
	
	
	/**
	 * Cargar ubigeo.
	 */
	public void  cargarUbigeo() {
		try {
			ubigeoMap = new HashMap<String, List<SelectItemVO>>();
			UbigeoDTO ubigeoFiltro = new UbigeoDTO();
	 		List<UbigeoDTO> listaUbigeoTempAll = commonServiceImpl.listarUbigeo(ubigeoFiltro);
	 		List<UbigeoDTO> listaDepartamentoTemp = new ArrayList<UbigeoDTO>();
	 		List<UbigeoDTO> listaUbigeoProvincia = new ArrayList<UbigeoDTO>();
	 		List<UbigeoDTO> listaUbigeoDistrito = new ArrayList<UbigeoDTO>();
	 		for (UbigeoDTO ubigeo : listaUbigeoTempAll) {
	 			ubigeoAllMap.put(ubigeo.getIdUbigeo(), ubigeo);
				if (TipoUbigeoType.DEPARTAMENTO.getKey().equals(ubigeo.getTipo())) {
					listaDepartamentoTemp.add(ubigeo);
				}
				if (TipoUbigeoType.PROVINCIA.getKey().equals(ubigeo.getTipo())) {
					listaUbigeoProvincia.add(ubigeo);
				}
				if (TipoUbigeoType.DISTRITO.getKey().equals(ubigeo.getTipo())) {
					listaUbigeoDistrito.add(ubigeo);
				}
			}
	 		List<SelectItemVO> listaDepatamento = commonServiceImpl.listarUbigeoSelectItem(listaDepartamentoTemp);
	 		this.listaSelectItemVOMap.put("ubigeoDepartamento", listaDepatamento);
			ubigeoFiltro.setTipo(TipoUbigeoType.PROVINCIA.getKey());
			//List<Ubigeo> listaUbigeoProvincia = admisionServiceImpl.listarUbigeo(ubigeoFiltro);
			for (SelectItemVO departamento : listaDepatamento) {
				if (departamento.getId() == null) {
					continue;
				}
				boolean encontroDependencia = false;
				for (UbigeoDTO ubigeo : listaUbigeoProvincia) {
					if (ubigeo.getUbigeoByDependencia() == null) {
						continue;
					}
					if (departamento.getId().toString().equals(ubigeo.getUbigeoByDependencia().getIdUbigeo())) {
						encontroDependencia = true;
						if (!ubigeoMap.containsKey(departamento.getId().toString())) {
							List<SelectItemVO> listaProvinciaValue = new ArrayList<SelectItemVO>();
							//listaProvinciaValue.add(Utilidades.darSelectItemVOVacio());
							listaProvinciaValue.add(new SelectItemVO(ubigeo.getIdUbigeo(), ubigeo.getDescripcion(), ubigeo.getUbigeoByDependencia().getIdUbigeo()));
							ubigeoMap.put(departamento.getId().toString(), listaProvinciaValue);
						} else {
							List<SelectItemVO> listaProvinciaValue = ubigeoMap.get(departamento.getId().toString());
							listaProvinciaValue.add(new SelectItemVO(ubigeo.getIdUbigeo(), ubigeo.getDescripcion(), ubigeo.getUbigeoByDependencia().getIdUbigeo()));
							ubigeoMap.put(departamento.getId().toString(), listaProvinciaValue);
						}
					}
				}
				if (!encontroDependencia) {
					List<SelectItemVO> listaProvinciaValue = new ArrayList<SelectItemVO>();
					//listaProvinciaValue.add(Utilidades.darSelectItemVOVacio());
					ubigeoMap.put(departamento.getId().toString(), listaProvinciaValue);
				}
			}
			ubigeoFiltro.setTipo(TipoUbigeoType.DISTRITO.getKey());
			//List<Ubigeo> listaUbigeoDistrito = admisionServiceImpl.listarUbigeo(ubigeoFiltro);
		
			for (UbigeoDTO provincia : listaUbigeoProvincia) {
				boolean encontroDependencia = false;
				for (UbigeoDTO ubigeo : listaUbigeoDistrito) {
					if (ubigeo.getUbigeoByDependencia() == null) {
						continue;
					}
					if (provincia.getIdUbigeo().equals(ubigeo.getUbigeoByDependencia().getIdUbigeo())) {
						encontroDependencia = true;
						if (!ubigeoMap.containsKey(provincia.getIdUbigeo())) {
							List<SelectItemVO> listaDistritoValue = new ArrayList<SelectItemVO>();
							//listaDistritoValue.add(Utilidades.darSelectItemVOVacio());
							listaDistritoValue.add(new SelectItemVO(ubigeo.getIdUbigeo(), ubigeo.getDescripcion(), ubigeo.getUbigeoByDependencia().getIdUbigeo()));
							ubigeoMap.put(provincia.getIdUbigeo(), listaDistritoValue);
						} else {
							List<SelectItemVO> listaDistritoValue = ubigeoMap.get(provincia.getIdUbigeo());
							listaDistritoValue.add(new SelectItemVO(ubigeo.getIdUbigeo(), ubigeo.getDescripcion(), ubigeo.getUbigeoByDependencia().getIdUbigeo()));
							ubigeoMap.put(provincia.getIdUbigeo(), listaDistritoValue);
						}
					}
				}
				if (!encontroDependencia) {
					List<SelectItemVO> listaDistritoValue = new ArrayList<SelectItemVO>();
					//listaDistritoValue.add(Utilidades.darSelectItemVOVacio());
					ubigeoMap.put(provincia.getIdUbigeo(), listaDistritoValue);
				}
			}
		} catch (Exception e) {
			log.error(e);
		}
	}
	/**
	 * Generar lista lenguaje select.
	 */
	private void generarListaLenguajeSelect() {
		List<SelectItemVO> listaLenguajeSelect = new ArrayList<SelectItemVO>();
		List<SelectItemVO> listaLenguajeSelectTemp = listaItemSelectItemVOMap.get(ListaItemType.LENGUAJE.getKey());
		for (SelectItemVO selectItemVO : listaLenguajeSelectTemp) {
			if (selectItemVO.getId() == null) {
				listaLenguajeSelect.add(selectItemVO);
			} else {
				ItemDTO item = itemMap.get(Long.valueOf(selectItemVO.getId().toString()));
				listaLenguajeSelect.add(new SelectItemVO(item.getCodigoExterno(), item.getNombre(), selectItemVO.getDescripcion()));
			}
		}
		this.listaSelectItemVOMap.put("lenguaje", listaLenguajeSelect);
	}
	
	
	
	
	
	/**
	 * Actualizar item.
	 *
	 * @param item el item
	 * @param accionType el accion type
	 */
	public void actualizarItem(ItemDTO item,AccionType accionType) {
		List<SelectItemVO> listaAgregado = new ArrayList<SelectItemVO>();
		List<SelectItemVO> listaSelectItemVOs = listaItemSelectItemVOMap.get(item.getListaItems().getIdListaItems());
		if (listaSelectItemVOs == null) {
			listaSelectItemVOs = new ArrayList<SelectItemVO>();
		}
		itemMap.put(item.getIdItem(), item);
		boolean existe = false;
		int index = -1;
		int posicion = -1;
		for (SelectItemVO selectItemVO : listaSelectItemVOs) {
			posicion++;
			if (selectItemVO.getId() == null) {
				continue;
			}
			Long key = Long.valueOf(selectItemVO.getId().toString());
			if (item.getIdItem().equals(key)) {
				selectItemVO.setNombre(item.getNombre());
				selectItemVO.setDescripcion(item.getCodigo() + "");
				index = posicion;
				existe = true;
				break;
			}
		}
		if (!existe && !AccionType.ELIMINAR.equals(accionType)) {
			listaAgregado.add(new SelectItemVO(item.getIdItem(), item.getNombre(), item.getCodigo() + ""));
		}
		if (AccionType.ELIMINAR.equals(accionType) && index >= 0) {
			listaSelectItemVOs.remove(index);
		}
		listaSelectItemVOs.addAll(listaAgregado);
		CollectionUtil.ordenador(false, listaSelectItemVOs, "nombre");
		listaItemSelectItemVOMap.put(item.getListaItems().getIdListaItems(), listaSelectItemVOs);
		//listaItemCboSelectOneItemMap.put(item.getListaItems().getIdListaItems(), Utilidades.obtenerSelectItemVO(item.getListaItems().getIdListaItems(), listaItemSelectItemVOMap));
		if (ListaItemType.LENGUAJE.getKey().equals(item.getListaItems().getIdListaItems())) {
			generarListaLenguajeSelect();
		}
	}
	
	
	
	/**
	 * Obtener item.
	 *
	 * @param id el id
	 * @return the item
	 */
	public ItemDTO obtenerItem(Long id) {
		return itemMap.get(id);
	}
	
	public ItemDTO obtenerItemByCodigo(String codigo) {
		return itemByCodigoMap.get(codigo);
	}
	
	/**
	 * Obtener ubigeo by id.
	 *
	 * @param id el id
	 * @return the ubigeo
	 */
	public UbigeoDTO obtenerUbigeoById(String id) {
		return ubigeoAllMap.get(id);
	}
	/**
	 * Obtener selec cbo item.
	 *
	 * @param itemType el item type
	 * @return the list
	 */
	public List<SelectItemVO> obtenerSelecCboItem(ListaItemType itemType) {
		return listaItemSelectItemVOMap.get(itemType.getKey());
	}
	
	public List<SelectItemVO> obtenerSelecCboItem(Long idListaItem) {
		return listaItemSelectItemVOMap.get(idListaItem);
	}
	
	public List<SelectItemVO> obtenerSeleItemVOByKey(String keySelectItemVO) {
		return listaSelectItemVOMap.get(keySelectItemVO);
	}
	
	/**
	 * Obtener selec cbo item.
	 *
	 * @param itemType el item type
	 * @param id el id
	 * @return the list
	 */
	public List<SelectItemVO> obtenerSelecCboItem(ListaItemType itemType, Object id) {
		if (ListaItemType.CICLO.equals(itemType)) {
			List<SelectItemVO> resultado = new ArrayList<SelectItemVO>();
			//resultado.add(Utilidades.darSelectItemVOVacio());
			List<SelectItemVO> resultadoTemp = listaItemSelectItemVOMap.get(itemType.getKey());
			Integer nroCiclo = escuelaNroCicloMap.get(id.toString());
			for (SelectItemVO selectItemVO : resultadoTemp) {
				if (StringUtils.isNotNullOrBlank(selectItemVO.getDescripcion())) {
					Integer codigo = Integer.parseInt(selectItemVO.getDescripcion());
					if (codigo <= nroCiclo ) {
						resultado.add(selectItemVO);
					}
					
				}
			}
			return resultado;
		}
		return listaItemSelectItemVOMap.get(itemType.getKey());
	}
	
	/**
	 * Obtener selec rb item.
	 *
	 * @param itemType el item type
	 * @return the list
	 */
	public List<SelectItemVO> obtenerSelecRbItem(ListaItemType itemType) {
		return listaItemSelectItemVOMap.get(itemType.getKey());
	}  
	
	/**
	 * Obtener selec ubigeo dependencia.
	 *
	 * @param id el id
	 * @return the list
	 */
	public List<SelectItemVO> obtenerSelecUbigeoDependencia(String id) {
		return ubigeoMap.get(id);
	}  


	
	
	/**
	 * Comprueba si es flag cargo listado.
	 *
	 * @return true, si es flag cargo listado
	 */
	public boolean isFlagCargoListado() {
		return flagCargoListado;
	}

	/**
	 * Establece el flag cargo listado.
	 *
	 * @param flagCargoListado el new flag cargo listado
	 */
	public void setFlagCargoListado(boolean flagCargoListado) {
		this.flagCargoListado = flagCargoListado;
	}

	public List<ItemDTO> converItemDTO(List<SelectItemVO> listaSelectItemVO) {
		List<ItemDTO> resultado = new ArrayList<ItemDTO>();
		for (SelectItemVO selectItemVO : listaSelectItemVO) {
			Long id = Long.valueOf(selectItemVO.getId() + "");
			resultado.add(obtenerItem(id));			
		}
		return resultado;
	}
}