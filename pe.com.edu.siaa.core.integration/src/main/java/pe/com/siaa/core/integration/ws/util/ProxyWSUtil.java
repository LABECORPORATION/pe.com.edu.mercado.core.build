package pe.com.siaa.core.integration.ws.util;


import java.io.Serializable;

import pe.com.siaa.core.integration.active.directory.ConfiguracionActiveDirectoryWSUtil;


/**
 * <ul>
 * <li>Copyright 2017 SIAA. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class ProxyWSUtil.
 *
 * @author ndavilal
 * @version 1.0 , 27/10/2015
 * @since SIAA-CORE 2.0
 */
public class ProxyWSUtil implements Serializable {

    /** La Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	
	/**
	 * Instancia un nuevo usuario active directory util.
	 */
	public ProxyWSUtil() {
		super();
	}

	/**
     * Proxy.
     */
    public  static void proxy() {
		if (ConfiguracionActiveDirectoryWSUtil.getProxyBoolean(ConfiguracionActiveDirectoryWSUtil.PROXY_ENABLED)) {
			String proxyHost = ConfiguracionActiveDirectoryWSUtil.getProxy(ConfiguracionActiveDirectoryWSUtil.PROXY_HOST_LOCAL);
			String proxyPort = ConfiguracionActiveDirectoryWSUtil.getProxy(ConfiguracionActiveDirectoryWSUtil.PROXY_HOST_PORT_LOCAL);
			String proxyUser = ConfiguracionActiveDirectoryWSUtil.getProxy(ConfiguracionActiveDirectoryWSUtil.PROXY_HOST_USER_LOCAL);
			String proxyPassword = ConfiguracionActiveDirectoryWSUtil.getProxy(ConfiguracionActiveDirectoryWSUtil.PROXY_PASSWORD_LOCAL);
			String proxySet = ConfiguracionActiveDirectoryWSUtil.getProxy(ConfiguracionActiveDirectoryWSUtil.PROXY_SET_LOCAL);
	
			System.getProperties().put("http.proxyHost", proxyHost);
			System.getProperties().put("http.proxyPort", proxyPort);
			if (proxyPassword != null && !proxyPassword.equals("")) {
				System.getProperties().put("http.proxyUser", proxyUser);
				System.getProperties().put("http.proxyPassword", proxyPassword);
			}
			if (proxySet != null && !proxySet.equals("")) {
				System.getProperties().put("http.proxySet", proxySet);
			}
		}
	}
  
   
}