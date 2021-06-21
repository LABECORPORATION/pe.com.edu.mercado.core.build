package pe.com.siaa.core.integration.active.directory;

import java.io.Serializable;

import pe.com.siaa.core.ejb.active.directory.cache.ConfiguracionCacheActiveDirectoryWSUtil;

/**
 * <ul>
 * <li>Copyright 2017 SIAA. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class ConfiguracionActiveDirectoryWSUtil.
 *
 * @author ndavilal
 * @version 1.0 , 01/04/2015
 * @since SIAA-CORE 2.0
 */
public final class  ConfiguracionActiveDirectoryWSUtil implements Serializable {
	
	
	/** La Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	//Sincronizado con el properties :webservice.properties
	/** El URI_SERVICIO_WEB. */
	public static final String URI_SERVICIO_WEB = "webservice.url";//properties
	
	/** El URI_SERVICIO_WEB. */
	public static final String URI_SERVICIO_WEB_NUMERO_LOTE = "numero.lote.webservice.url";//properties
	
	/** El URI_SERVICIO_WEB. */
	public static final String URI_SERVICIO_WEB_SGSM_GENERAR_TRAMA = "sgsm.generar.trama.webservice.url";//properties
	
	/** La Constante URI_SERVICIO_CONSULTA_AD_WEBMETHOD. */
	public static final String URI_SERVICIO_CONSULTA_AD_WEBMETHOD = "webservice.consulta.activeDirectory.webMethod.url";
	
	//Sincronicar con el propertis:proxy.properties
	/** La Constante PROXY_ENABLED. */
	public static final String PROXY_ENABLED = "proxy.enabled";
	
	/** La Constante PROXY_HOST_LOCAL. */
	public static final  String PROXY_HOST_LOCAL = "proxy.host.local";
	
	/** La Constante PROXY_HOST_PORT_LOCAL. */
	public static final  String PROXY_HOST_PORT_LOCAL = "proxy.host.port.local";
	
	/** La Constante PROXY_HOST_USER_LOCAL. */
	public static final  String PROXY_HOST_USER_LOCAL = "proxy.host.user.local";
	
	/** La Constante PROXY_PASSWORD_LOCAL. */
	public static final  String PROXY_PASSWORD_LOCAL = "proxy.host.password.local";
	
	/** La Constante PROXY_SET_LOCAL. */
	public static final  String PROXY_SET_LOCAL = "proxy.host.set.local";
	
	
	public static final String URI_SERVICIO_WEB_ESTADO_ENVIO = "webservice.WSEstadoEnvioPE";//properties
	public static final String URI_SERVICIO_WEB_MONITOREO_ENVIO = "webservice.WSMonitoreoEnvioPE";//properties
	
	/**
	 * Instancia un nuevo configuracion active directory ws util.
	 */
	private  ConfiguracionActiveDirectoryWSUtil() {
	}

	/**
	 * Obtiene web service.
	 *
	 * @param key el key
	 * @return web service
	 */
	public static String getWebService(String key) {
		return ConfiguracionCacheActiveDirectoryWSUtil.getInstance().getWebService(key);
	}
	
	/**
	 * Obtiene proxy.
	 *
	 * @param key el key
	 * @return proxy
	 */
	public static String getProxy(String key) {
		return ConfiguracionCacheActiveDirectoryWSUtil.getInstance().getProxy(key);
	}
	
	/**
	 * Obtiene proxy boolean.
	 *
	 * @param key el key
	 * @return proxy boolean
	 */
	public static boolean getProxyBoolean(String key) {
		return ConfiguracionCacheActiveDirectoryWSUtil.getInstance().getProxy(key).equalsIgnoreCase("true");
	}
}
