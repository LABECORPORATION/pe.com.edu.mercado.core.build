package pe.com.edu.siaa.core.ejb.service.automatico.impl;

import java.util.Collection;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import pe.com.edu.siaa.core.ejb.cache.ConfiguracionCacheUtil;
import pe.com.edu.siaa.core.ejb.factory.SqlMapingCacheUtil;
import pe.com.edu.siaa.core.ejb.service.automatico.local.ProcesoAutomaticoActualizarCacheServiceLocal;
import pe.com.edu.siaa.core.ejb.service.util.FechaUtil;
import pe.com.edu.siaa.core.ejb.util.cache.ParametroCacheUtil;
import pe.com.edu.siaa.core.ejb.util.log.Logger;
import pe.com.edu.siaa.core.model.util.CadenaCacheUtil;


/**
 * <ul>
 * <li>Copyright 2017 ndavilal. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class ProcesoAutomaticoActualizarCacheServiceImpl.
 *
 * @author ndavilal
 * @version 1.0 , 07/04/2015
 * @since SIAA-CORE 2.1
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ProcesoAutomaticoActualizarCacheServiceImpl implements ProcesoAutomaticoActualizarCacheServiceLocal {
	
	/** El log. */
	private static Logger log = Logger.getLogger(ProcesoAutomaticoActualizarCacheServiceImpl.class);
	
	 /** El ctx. */
    @Resource
    private TimerService timerService;
    
    /** La context. */
	@Resource
	private SessionContext context;
	
	/**
	 * Instancia un nuevo proceso automatico seleccion.
	 */
	public ProcesoAutomaticoActualizarCacheServiceImpl() {
		
	}
	
	 
    /* (non-Javadoc)
     * @see pe.gob.osce.seace.pro.sel.ejb.service.ProcesoAutomaticoSeleccionServiceLocal#parar(java.lang.String)
     */
    @Override
    public void parar(String name) throws Exception {
        Collection<Timer> timers = timerService.getTimers();
        for (Timer t : timers) {
            if (t.getInfo().equals(name)) {
                t.cancel();

            }
        }
    }

    /* (non-Javadoc)
     * @see pe.gob.osce.seace.pro.sel.ejb.service.ProcesoAutomaticoActualizarCacheServiceLocal#iniciar(long, long, java.lang.String)
     */
    @Override
    public void iniciar(long delay, long interval, String name)
            throws Exception {
    	timerService.createTimer(delay, interval, name);
    }
    /**
     * Ejecutar cierre automatico.
     *
     * @param timer el timer
     */
    @Timeout
    public void ejecutaTimerAutomatico(Timer timer) {
    	log.error("ProcesoAutomaticoActualizarCache :  (" + FechaUtil.obtenerFechaActual() + ") Se esta ejecutnado el Timer :  PROCESO_AUTOMATICO_SELECCION_ACTUALIZAR_CACHE ");
    	log.error("ProcesoAutomaticoActualizarCache Inicio (" + FechaUtil.obtenerFechaActual() + ") Se esta ejecutnado el Timer :  PROCESO_AUTOMATICO_SELECCION_ACTUALIZAR_CACHE ");
    	actualizarCache();
    	log.error("ProcesoAutomaticoActualizarCache : Fin (" + FechaUtil.obtenerFechaActual() + ") del Timer : PROCESO_AUTOMATICO_SELECCION_ACTUALIZAR_CACHE ");
    }
	
	
	/* (non-Javadoc)
	 * @see pe.gob.osce.seace.pro.sel.ejb.service.ProcesoAutomaticoActualizarCacheServiceLocal#actualizarCache()
	 */
    @Override
	public void actualizarCache() {
			try {
				 log.error("El servicio se inicia a " + FechaUtil.obtenerFechaActual() + " " );
				 actualizarCacheIntegracionTron2000();
				 actualizarParametroCacheUtil();
				 actualizarCacheConfiguracion();
				 actualizarCacheConfiguracionActiveDirectoryWSUtil();
				 actualizarCacheSqlMaping();
				 actualizarCacheCadenaUtil();
		} catch (Exception e) {
			log.error(e);
		}		
	}		
    
  
    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.service.automatico.local.ProcesoAutomaticoActualizarCacheServiceLocal#actualizarCacheIntegracionTron2000()
     */
    @Override
	public String actualizarCacheIntegracionTron2000() {
    	return "";//IntegracionTron2000CacheUtil.getInstance().sincronizarData();	
		
	}
    @Override
   	public  String actualizarParametroCacheUtil() {
    	return ParametroCacheUtil.getInstance().sincronizarData();	
    }
	
    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.service.automatico.local.ProcesoAutomaticoActualizarCacheServiceLocal#actualizarCacheConfiguracion()
     */
    @Override
	public String actualizarCacheConfiguracion() {
    	return ConfiguracionCacheUtil.getInstance().sincronizarData();
	}
    
    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.service.automatico.local.ProcesoAutomaticoActualizarCacheServiceLocal#actualizarCacheConfiguracionActiveDirectoryWSUtil()
     */
    @Override
	public String actualizarCacheConfiguracionActiveDirectoryWSUtil() {
    	return "";//ConfiguracionCacheActiveDirectoryWSUtil.getInstance().sincronizarData();
	}
    
    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.service.automatico.local.ProcesoAutomaticoActualizarCacheServiceLocal#actualizarCacheSqlMaping()
     */
    @Override
    public String actualizarCacheSqlMaping() {
    	return SqlMapingCacheUtil.getInstance().sincronizarData();
    }
    
    /* (non-Javadoc)
     * @see pe.com.edu.siaa.core.ejb.service.automatico.local.ProcesoAutomaticoActualizarCacheServiceLocal#actualizarCacheCadenaUtil()
     */
    @Override
    public String actualizarCacheCadenaUtil() {
    	return CadenaCacheUtil.getInstance().sincronizarData();
    }
}
