package pe.com.edu.siaa.core.ejb.util.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pe.com.edu.siaa.core.ejb.service.helper.Referencia;
import pe.com.edu.siaa.core.ejb.service.seguridad.local.SeguridadServiceLocal;
import pe.com.edu.siaa.core.model.dto.seguridad.ConfiguracionMenuDTO;
import pe.com.edu.siaa.core.model.dto.seguridad.PropertiesDTO;
import pe.com.edu.siaa.core.model.type.TipoComponenteType;
import pe.com.edu.siaa.core.model.util.ConstantesConfiguracion;
import pe.com.edu.siaa.core.model.vo.ConfiguracionFormularioVO;
//import pe.edu.upp.siaa.fwk.constantes.ConstantesConfiguracion;
//import pe.edu.upp.siaa.type.TipoComponenteType;
import pe.com.edu.siaa.core.model.vo.ConfiguracionMenuVO;


/**
 * La Class CacheUtil.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 13:43:23 COT 2017
 * @since SIAA-CORE 2.1
 */
public class CacheUtil {

	/** El properties map. */
	//private Map<String,String> propertiesMap = new HashMap<String, String>();
	/** La configurador cache utl. */
	private static CacheUtil cacheUtil = null;
	
	/** El properties ideoma map. */
	private Map<String,Map<String,String>> propertiesIdeomaMap = new HashMap<String, Map<String,String>>();
	
	private ConfiguracionFormularioVO configuracionFormularioVO = new ConfiguracionFormularioVO();
	
	/** El flag cargo cache. */
	private boolean cargoCache = false;
	
	/**
	 * Instancia un nuevo cache util.
	 */
	public CacheUtil() {
		
	}
	
	/**
	 * Instanciar.
	 *
	 * @return the configurador cache utl
	 */
	public static CacheUtil getInstance() {
		if (cacheUtil == null) {
			createInstance();
		} else if (!cacheUtil.isCargoCache()) {
			cacheUtil.sincronizarData();
		}
		return cacheUtil;
	}
	 /**
     * Creates the instance.
     */
    private static synchronized void createInstance() {
    	if (cacheUtil == null) {
    		cacheUtil = new CacheUtil();
    		cacheUtil.sincronizarData();
		}
    }

	/**
	 * Sincronizar data.
	 *
	 * @return the string
	 */
	public   String sincronizarData() {
		return iniciarCache();
	}

	public ConfiguracionFormularioVO getConfiguracionFormularioVO () {
		return configuracionFormularioVO;
	}
	/**
	 * Actualizar properties cache.
	 *
	 * @param listaConfiguracionMenu el lista configuracion menu
	 */
	public void actualizarPropertiesCache(List<ConfiguracionMenuVO> listaConfiguracionMenu) {
		Map<String,Boolean> requiredInputMap = new HashMap<String, Boolean>();
		Map<String,Boolean> requiredGrillaMap = new HashMap<String, Boolean>();
		
		Map<String,Boolean> readonlyInputMap = new HashMap<String, Boolean>();
		Map<String,Boolean> readonlyGrillaMap = new HashMap<String, Boolean>();
		
		Map<String,Boolean> renderedLabelMap = new HashMap<String, Boolean>();
		Map<String,Boolean> renderedInputMap = new HashMap<String, Boolean>();
		Map<String,Boolean> renderedButtonMap = new HashMap<String, Boolean>();
		Map<String,Boolean> renderedGrillaMap = new HashMap<String, Boolean>();
		Map<String,Boolean> renderedButtonGrillaMap = new HashMap<String, Boolean>();
		
		Map<String,Boolean> disabledInputMap = new HashMap<String, Boolean>();
		Map<String,Boolean> disabledInputGrillaMap = new HashMap<String, Boolean>();
		Map<String,Boolean> disabledButtonMap = new HashMap<String, Boolean>();
		Map<String,Boolean> disabledButtonGrillaMap = new HashMap<String, Boolean>();
		
		for (ConfiguracionMenuVO configuracionMenuDTO : listaConfiguracionMenu) {
			 TipoComponenteType componenteType = TipoComponenteType.get(configuracionMenuDTO.getCodigo());
			for (ConfiguracionMenuDTO configuracionMenu : configuracionMenuDTO.getListaConfiguracionMenus()) {
				String key = configuracionMenu.getProperties().getName();
				switch (componenteType) {
					case LABEL:
						if (configuracionMenu.getProperties().getName().contains("grilla")) {
							renderedGrillaMap.put(key, configuracionMenu.getRendered());
						} else {
							renderedLabelMap.put(key, configuracionMenu.getRendered());
						}
						break;
					case INPUT:
						if (configuracionMenu.getProperties().getName().contains("grilla")) {
							requiredGrillaMap.put(key, configuracionMenu.getRequired());
							readonlyGrillaMap.put(key, configuracionMenu.getReadonly());
							disabledInputGrillaMap.put(key, configuracionMenu.getDisabled());
							renderedGrillaMap.put(key, configuracionMenu.getRendered());
						} else {
							requiredInputMap.put(key, configuracionMenu.getRequired());
							readonlyInputMap.put(key, configuracionMenu.getReadonly());
							renderedInputMap.put(key, configuracionMenu.getRendered());
							disabledInputMap.put(key, configuracionMenu.getDisabled());
						}
								
						break;
					case BUTTON:
						if (configuracionMenu.getProperties().getName().contains("grilla")) {
							renderedButtonGrillaMap.put(key, configuracionMenu.getRendered());
							disabledButtonGrillaMap.put(key, configuracionMenu.getDisabled());
						} else {
							renderedButtonMap.put(key, configuracionMenu.getRendered());
							disabledButtonMap.put(key, configuracionMenu.getDisabled());
						}
						break;

				default:
					break;
				}
				
				
			}
		}
		
		configuracionFormularioVO.getRequiredInputMap().putAll(requiredInputMap);
		configuracionFormularioVO.getRequiredGrillaMap().putAll(requiredGrillaMap);
		
		configuracionFormularioVO.getReadonlyInputMap().putAll(readonlyInputMap);
		configuracionFormularioVO.getReadonlyGrillaMap().putAll(readonlyGrillaMap);
		
		configuracionFormularioVO.getRenderedLabelMap().putAll(renderedLabelMap);
		configuracionFormularioVO.getRenderedInputMap().putAll(renderedInputMap);
		configuracionFormularioVO.getRenderedButtonMap().putAll(renderedButtonMap);
		configuracionFormularioVO.getRenderedGrillaMap().putAll(renderedGrillaMap);
		configuracionFormularioVO.getRenderedButtonGrillaMap().putAll(renderedButtonGrillaMap);
		
		configuracionFormularioVO.getDisabledInputMap().putAll(disabledInputMap);
		configuracionFormularioVO.getDisabledInputGrillaMap().putAll(disabledInputGrillaMap);
		configuracionFormularioVO.getDisabledButtonMap().putAll(disabledButtonMap);
		configuracionFormularioVO.getDisabledButtonGrillaMap().putAll(disabledButtonGrillaMap);
	
	}	

	/**
	 * Iniciar cache.
	 */
	public synchronized String iniciarCache() {
		String resultado = "";
		try {   
			if (!cargoCache) {
				SeguridadServiceLocal seguridadServiceLocal = Referencia.getReference(SeguridadServiceLocal.class);
				//Se obtienen los parametros del web.xml
				List<ConfiguracionMenuVO> listaComponente = new ArrayList<ConfiguracionMenuVO>();
				listaComponente = seguridadServiceLocal.obtenerConfiguracionMenu(null);
				actualizarPropertiesCache(listaComponente);
				listaComponente = null;
				
				Map<String,Map<String,String>> propertiesLenguajeMap = seguridadServiceLocal.obtenerPropertiesLenguajeAllMap();
				propertiesIdeomaMap.putAll(propertiesLenguajeMap);
				List<PropertiesDTO> listaPropertiesTemp = seguridadServiceLocal.obtenerPropertiesLenguaje(new PropertiesDTO());
				actualizarPropertiesCacheIdeomaAll(listaPropertiesTemp);
				cargoCache = true;
				propertiesLenguajeMap = null;
				listaPropertiesTemp = null;
			}
				
		} catch (Exception e) {			
			e.printStackTrace();
			cargoCache = false;
			resultado = e.getMessage();
		}
		try {
			//ReglaCacheUtil.getInstance();	
		} catch (Exception e) {
			e.printStackTrace();
			resultado = e.getMessage();
		}
		return resultado;
	}
	
	/**
	 * Actualizar properties cache ideoma all.
	 *
	 * @param listaPropertiesTemp el lista properties temp
	 * @param propertiesLenguajeMap el properties lenguaje map
	 */
	public void actualizarPropertiesCacheIdeomaAll(List<PropertiesDTO> listaPropertiesTemp) {
		String keyIdeoma = ConstantesConfiguracion.ES_PE;
		for (PropertiesDTO propertiesDTO : listaPropertiesTemp) {
			if (!propertiesIdeomaMap.containsKey(keyIdeoma)) {
				Map<String, String> ideomaBDMap =  new HashMap<String,String>();
				ideomaBDMap.put(propertiesDTO.getName(), propertiesDTO.getValue());
				propertiesIdeomaMap.put(keyIdeoma,  ideomaBDMap );
			} else {
				Map<String, String> ideomaBDMap =  propertiesIdeomaMap.get(keyIdeoma);
				if (!ideomaBDMap.containsKey(propertiesDTO.getName())) {
					ideomaBDMap.put(propertiesDTO.getName(), propertiesDTO.getValue());	
				}				
				propertiesIdeomaMap.put(keyIdeoma,  ideomaBDMap );
			}
		}
	}
	
// get y set
	public Map<String, Map<String, String>> getPropertiesIdeomaMap() {
		return propertiesIdeomaMap;
	}

	public void setPropertiesIdeomaMap(
			Map<String, Map<String, String>> propertiesIdeomaMap) {
		this.propertiesIdeomaMap = propertiesIdeomaMap;
	}

	/**
	 * Comprueba si es cargo cache.
	 *
	 * @return true, si es cargo cache
	 */
	public boolean isCargoCache() {
		return cargoCache;
	}

	/**
	 * Establece el cargo cache.
	 *
	 * @param cargoCache el new cargo cache
	 */
	public void setCargoCache(boolean cargoCache) {
		this.cargoCache = cargoCache;
	}
	
	
	
}
