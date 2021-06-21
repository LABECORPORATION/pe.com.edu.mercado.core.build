/*
 * 
 */
package pe.com.edu.siaa.core.ejb.util.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pe.com.edu.siaa.core.ejb.service.contabilidad.local.ContabilidadServiceLocal;
import pe.com.edu.siaa.core.ejb.service.helper.Referencia;
import pe.com.edu.siaa.core.ejb.service.util.FechaUtil;
import pe.com.edu.siaa.core.ejb.util.log.Logger;
import pe.com.edu.siaa.core.model.dto.contabilidad.ConfiguracionCuentaDTO;
import pe.com.edu.siaa.core.model.dto.contabilidad.PlanContableDTO;
import pe.com.edu.siaa.core.model.estate.EstadoGeneralState;



/**
 * La Class ParametroCacheUtil.
 * <ul>
 * <li>Copyright 2017 ndavilal -
 * ndavilal. Todos los derechos reservados.</li>
 * </ul>
 *
 * @author ndavilal
 * @version 2.1, Tue Apr 18 13:43:23 COT 2017
 * @since SIAA-CORE 2.1
 */
public class ContabilidadCacheUtil {

	/** El service common impl. */
	private ContabilidadServiceLocal contabilidadServiceImpl;
	
	private Logger log = Logger.getLogger(ContabilidadCacheUtil.class);
	/** La configurador cache utl. */
	private static ContabilidadCacheUtil parametroCacheUtil = null;
		
  	/** El flag cargo listado. */
	private boolean flagCargoListado = false;
	
	private Map<String,ConfiguracionCuentaDTO> configuracionCuentaMap = new HashMap<String, ConfiguracionCuentaDTO>();
	
	private Map<String,PlanContableDTO> planContableMap = new HashMap<String, PlanContableDTO>();
	
	/**
	 * Instancia un nuevo administracion cache utl.
	 */
	public ContabilidadCacheUtil() {
		
	}
	
	/**
	 * Instanciar.
	 *
	 * @return the configurador cache utl
	 */
	public static ContabilidadCacheUtil getInstance() {
		if (parametroCacheUtil == null) {
			createInstance();
		} else if (!parametroCacheUtil.isFlagCargoListado()) {
			parametroCacheUtil.sincronizarData();
		}
		return parametroCacheUtil;
	}
	 /**
     * Creates the instance.
     */
    private static synchronized void createInstance() {
    	if (parametroCacheUtil == null) {
			parametroCacheUtil = new ContabilidadCacheUtil();
			parametroCacheUtil.sincronizarData();
		}
    }

	/**
	 * Sincronizar data.
	 *
	 * @return the string
	 */
	public   String sincronizarData() {
		return sincronizarProperties();
	}

	
	/**
	 * Sincronizar properties.
	 *
	 * @return the string
	 */
	private String sincronizarProperties() {
		try {
			contabilidadServiceImpl = Referencia.getReference(ContabilidadServiceLocal.class);
			PlanContableDTO planContableFiltro = new PlanContableDTO();
			planContableFiltro.setAnho(Long.valueOf(FechaUtil.anio(FechaUtil.obtenerFecha())));
			planContableFiltro.setEstado(EstadoGeneralState.ACTIVO.getKey());
			List<PlanContableDTO> listaPlanContable = contabilidadServiceImpl.listarPlanContable(planContableFiltro );
			for (PlanContableDTO planContableDTO : listaPlanContable) {
				planContableMap.put(planContableDTO.getIdPlanContable(), planContableDTO);
			}

			ConfiguracionCuentaDTO configuracionCuentaFiltro = new ConfiguracionCuentaDTO();
			List<ConfiguracionCuentaDTO> listaConfiguracionCuenta = contabilidadServiceImpl.listarConfiguracionCuenta(configuracionCuentaFiltro );

			for (ConfiguracionCuentaDTO configuracionCuentaDTO : listaConfiguracionCuenta) {
				String key = (configuracionCuentaDTO.getEntidad().getIdEntidad() == null ?  "" : configuracionCuentaDTO.getEntidad().getIdEntidad()) + configuracionCuentaDTO.getItemByLibro().getCodigoExterno();//TODO:VER_NATAN_CONTA
				configuracionCuentaMap.put(key, configuracionCuentaDTO);
			}
			
			flagCargoListado = true;
		} catch (Exception e) {
			log.error(e);
			flagCargoListado = false;
			return e.toString();
		}
		return null;
	}
	
	
	//get y set
	
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

	public Map<String, PlanContableDTO> getPlanContableMap() {
		return planContableMap;
	}

	public Map<String, ConfiguracionCuentaDTO> getConfiguracionCuentaMap() {
		return configuracionCuentaMap;
	}

}
