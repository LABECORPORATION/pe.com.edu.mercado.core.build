package pe.com.edu.siaa.core.ejb.service.automatico.local;

import javax.ejb.Local;

@Local
public interface ProcesoAutomaticoActualizarCacheServiceLocal {

	/**
	 * Parar.
	 *
	 * @param name el name
	 * @throws Exception the exception
	 */
	void parar(String name) throws Exception;

	
	 /**
 	 * Iniciar.
 	 *
 	 * @param delay el delay
 	 * @param interval el interval
 	 * @param name el name
 	 * @throws Exception the exception
 	 */
 	void iniciar(long delay, long interval, String name)  throws Exception;
	
	
	/**
	 * Execute.
	 */
	void actualizarCache();
	
    /**
     * Actualizar cache Integracion tron 2000.
	*/
	String actualizarCacheIntegracionTron2000();
	
	String actualizarParametroCacheUtil();
	
	
	
	/**
	 * Actualizar cache configuracion:proxy,webservice.
	 */
	String actualizarCacheConfiguracion();
	
	/**
	 * Actualizar cache configuracion active directororyWS.
	 */
	String actualizarCacheConfiguracionActiveDirectoryWSUtil();
	
	String actualizarCacheSqlMaping();


	String actualizarCacheCadenaUtil();
		
}